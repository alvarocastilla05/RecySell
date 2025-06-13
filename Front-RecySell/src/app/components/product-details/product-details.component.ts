import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductoService } from '../../service/producto-service.service';
import { AuthServiceService } from '../../service/auth-service.service';
import { UsuarioService } from '../../service/usuario-service.service';
import { CompraService } from '../../service/compra-service.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  encapsulation: ViewEncapsulation.None

})
export class ProductDetailsComponent implements OnInit {
  producto: any;
  isLoggedIn = false;
  esPropietario = false;
  imagenActual = 0;

  showModal = false;
  showEliminadoModal = false;
  showErrorModal = false;
  showCarritoModal = false;
  showYaEnCarritoModal = false;
  enFavoritos = false;
  showConfirmarEliminarModal = false;

  carritosDisponibles: any[] = [];
  mostrarSelectorCarrito = false;
  carritoSeleccionadoId: number | null = null;


  constructor(
    private route: ActivatedRoute,
    private productoService: ProductoService,
    private authService: AuthServiceService,
    private usuarioService: UsuarioService,
    private compraService: CompraService,
    private router: Router // <-- añade esto

  ) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (!id) return;
    this.productoService.getProductoById(id).subscribe(prod => {
      this.producto = prod;
      this.comprobarPropietario();
      this.comprobarFavorito(id);
    });
    this.isLoggedIn = !!localStorage.getItem('token');
  }

  getUserIdFromToken(): string | null {
    const token = localStorage.getItem('token');
    if (!token) return null;
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      // Ajusta el campo según tu backend: id, userId, sub, etc.
      return payload.id || payload.userId || payload.sub || null;
    } catch {
      return null;
    }
  }

  comprobarPropietario() {
    let userId = localStorage.getItem('userId');
    if (!userId) {
      userId = this.getUserIdFromToken();
    }
    const vendedorId = this.producto?.clienteVendedor?.id;

    this.esPropietario = !!userId && !!vendedorId && userId.toString() === vendedorId.toString();
  }

  comprobarFavorito(idProducto: string) {
    this.usuarioService.getFavoritos().subscribe({
      next: (favoritos) => {
        // Busca por el campo identificador correcto (id, productoId, etc.)
        this.enFavoritos = favoritos.some((p: any) =>
          (p.id || p.productoId || p._id) == idProducto
        );
      },
      error: () => this.enFavoritos = false
    });
  }



  getEstadoLegible(estado: string): string {
    switch (estado) {
      case 'COMO_NUEVO': return 'Como nuevo';
      case 'BUEN_ESTADO': return 'Buen estado';
      case 'ESTADO_REGULAR': return 'Estado regular';
      case 'CON_DANIOS': return 'Con daños';
      default: return estado ? estado.replace(/_/g, ' ').toLowerCase() : '';
    }
  }


  cambiarImagen(direccion: number) {
    if (!this.producto?.imagenes?.length) return;
    this.imagenActual = (this.imagenActual + direccion + this.producto.imagenes.length) % this.producto.imagenes.length;
  }

  // Añadir a favoritos
  anadirAFavoritos() {
    const idProducto = this.producto?.id || this.route.snapshot.paramMap.get('id');
    if (!idProducto) return;
    this.productoService.anadirAFavoritos(idProducto).subscribe({
      next: () => {
        this.showModal = true;
        this.enFavoritos = true;
      },
      error: () => {
        this.showErrorModal = true;
      }
    });
  }

  // Eliminar de favoritos
  eliminarDeFavoritos() {
    const idProducto = this.producto?.id || this.route.snapshot.paramMap.get('id');
    if (!idProducto) return;
    this.productoService.eliminarDeFavoritos(idProducto).subscribe({
      next: () => {
        this.enFavoritos = false;
        this.showEliminadoModal = true;
      },
      error: () => {
        this.showErrorModal = true;
      }
    });
  }

  anadirAlCarrito() {
    const userId = localStorage.getItem('userId') || this.getUserIdFromToken();
    if (!userId) {
      this.showErrorModal = true;
      return;
    }
    this.compraService.getComprasPorCliente(userId).subscribe({
      next: (compras) => {
        let listaCompras: any[] = [];
        if (Array.isArray(compras)) {
          listaCompras = compras;
        } else if (compras && Array.isArray(compras.content)) {
          listaCompras = compras.content;
        } else if (compras && Array.isArray(compras.compras)) {
          listaCompras = compras.compras;
        } else if (compras && Array.isArray(compras.data)) {
          listaCompras = compras.data;
        } else {
          this.showErrorModal = true;
          return;
        }
        this.carritosDisponibles = listaCompras.filter((c: any) =>
          c.estadoCompra && c.estadoCompra.toUpperCase() === 'CARRITO'
        );
        if (!this.carritosDisponibles.length) {
          this.router.navigate(['/nueva-compra']);
          return;
        }
        this.mostrarSelectorCarrito = true;
        this.carritoSeleccionadoId = this.carritosDisponibles[0].id;
      },
      error: (err) => {
        // Si es un 404, redirige a nueva compra
        if (err.status === 404) {
          this.router.navigate(['/nueva-compra']);
        } else {
          this.showErrorModal = true;
        }
      }
    });
  }

  confirmarAnadirAlCarrito() {
    if (!this.carritoSeleccionadoId) return;
    const productoId = this.producto?.id || this.route.snapshot.paramMap.get('id');
    this.productoService.anadirAlCarrito(this.carritoSeleccionadoId, Number(productoId)).subscribe({
      next: () => {
        this.showCarritoModal = true;
        this.mostrarSelectorCarrito = false;
      },
      error: (err) => {
        // Detecta el error 400 con el mensaje específico
        if (
          err.status === 400 &&
          (
            (err.error && typeof err.error === 'string' && err.error.includes('ya está en el carrito')) ||
            (err.error && err.error.message && err.error.message.includes('ya está en el carrito')) ||
            (err.error && err.error.error && err.error.error.includes('ya en carrito'))
          )
        ) {
          this.showYaEnCarritoModal = true;
        } else {
          this.showErrorModal = true;
        }
        this.mostrarSelectorCarrito = false;
      }
    });
  }

  irAEditarProducto() {
  const idProducto = this.producto?.id || this.route.snapshot.paramMap.get('id');
  if (idProducto) {
    this.router.navigate(['/editar-producto', idProducto]);
  } else {
    console.error('El producto o su id no están definidos');
  }
}

  confirmarEliminarProducto() {
    const idProducto = this.producto?.id || this.route.snapshot.paramMap.get('id');
    if (!idProducto) return;
    this.productoService.eliminarProducto(idProducto).subscribe({
      next: () => {
        this.showConfirmarEliminarModal = false;
        this.router.navigate(['/']); // O la ruta que prefieras tras eliminar
      },
      error: () => {
        this.showConfirmarEliminarModal = false;
        this.showErrorModal = true;
      }
    });
  }
}