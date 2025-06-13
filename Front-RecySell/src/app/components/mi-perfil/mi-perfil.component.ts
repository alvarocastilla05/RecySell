import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../service/usuario-service.service';
import { ProductoFavorito } from '../../interfaces/product/product-fav.interface';
import { Router, ActivatedRoute } from '@angular/router'; // <-- Importa ActivatedRoute
import { DonacionService } from '../../service/donacion-service.service';
import { CompraService } from '../../service/compra-service.service';

@Component({
  selector: 'app-mi-perfil',
  templateUrl: './mi-perfil.component.html',
  styleUrl: './mi-perfil.component.css'
})
export class MiPerfilComponent implements OnInit {
  selectedSection: string = 'usuario';
  usuario: any = null;
  editMode = false;
  usuarioId: string = '';
  showPassword = false;
  showLogoutModal = false;

  favoritos: ProductoFavorito[] = [];
  misProductos: any[] = [];
  misDonaciones: any[] = [];
  misCompras: any[] = [];

  // Para el modal de eliminar producto
  showDeleteModal = false;
  productoAEliminar: any = null;

  constructor(
    private usuarioService: UsuarioService,
    public router: Router,
    private route: ActivatedRoute, // <-- Añade esto
    private donacionService: DonacionService,
    private compraService: CompraService
  ) { }

  ngOnInit() {
  // 1. Carga el usuario primero
  this.usuarioService.getUsuarioMe().subscribe(me => {
    this.usuarioId = me.id;
    this.usuarioService.getClienteById(this.usuarioId).subscribe(cliente => {
      this.usuario = { ...cliente };

      // 2. Ahora lee los query params y carga la sección correcta
      this.route.queryParams.subscribe(params => {
        if (params['apartado']) {
          this.selectedSection = params['apartado'];
          if (params['apartado'] === 'compras') {
            this.cargarMisCompras();
          }
          if (params['apartado'] === 'favoritos') {
            this.cargarFavoritos();
          }
          if (params['apartado'] === 'productos') {
            this.cargarMisProductos();
          }
          if (params['apartado'] === 'donaciones') {
            this.cargarMisDonaciones();
          }
        }
      });
    });
  });
}

  cargarUsuario() {
    // 1. Obtener el UUID del usuario logeado
    this.usuarioService.getUsuarioMe().subscribe(me => {
      this.usuarioId = me.id;
      // 2. Obtener los datos completos del usuario
      this.usuarioService.getClienteById(this.usuarioId).subscribe(cliente => {
        this.usuario = { ...cliente };
      });
    });
  }

  guardarCambios() {
    this.usuarioService.editarCliente(this.usuarioId, this.usuario).subscribe({
      next: data => {
        this.usuario = { ...data.usuario };
        this.editMode = false;
        setTimeout(() => this.cargarUsuario(), 500);
      },
      error: err => {
        this.editMode = false;
      }
    });
  }

  cancelarEdicion() {
    this.editMode = false;
    this.cargarUsuario();
  }

  cargarFavoritos() {
    this.usuarioService.getFavoritos().subscribe(favs => {
      this.favoritos = favs;
    });
  }

  cargarMisProductos() {
    this.usuarioService.getMisProductos(this.usuarioId).subscribe(productos => {
      this.misProductos = productos;
    });
  }

  // Modal de eliminar producto
  abrirModalEliminar(producto: any) {
    this.productoAEliminar = producto;
    this.showDeleteModal = true;
  }

  confirmarEliminarProducto() {
    if (!this.productoAEliminar) return;
    this.usuarioService.eliminarProducto(this.productoAEliminar.id).subscribe(() => {
      this.misProductos = this.misProductos.filter(p => p.id !== this.productoAEliminar.id);
      this.showDeleteModal = false;
      this.productoAEliminar = null;
    });
  }

  cargarMisDonaciones() {
    this.donacionService.getDonaciones().subscribe(resp => {
      this.misDonaciones = (resp.content || []).filter((d: any) =>
        d.productoDonado?.clienteDonante?.id &&
        d.productoDonado.clienteDonante.id === this.usuarioId
      );
    });
  }

  cargarMisCompras() {
  this.compraService.getComprasPorCliente(this.usuarioId).subscribe(resp => {
    this.misCompras = (resp.content || []).sort((a: any, b: any) => b.id - a.id);
  });
}

  irADetalleProducto(id: number) {
    this.router.navigate(['/producto', id]);
  }

  editarCompra(event: MouseEvent, id: number) {
  event.preventDefault();
  event.stopPropagation();
  this.router.navigate(['/editar-compra', id]);
}

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('tipo');
    localStorage.removeItem('profileImage');
    window.location.href = '/home';
  }
}