import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../service/usuario-service.service';
import { ProductoFavorito } from '../../interfaces/product/product-fav.interface';
import { Router } from '@angular/router';
import { DonacionService } from '../../service/donacion-service.service';

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

  // Para el modal de eliminar producto
  showDeleteModal = false;
  productoAEliminar: any = null;

  constructor(
    private usuarioService: UsuarioService, 
    public router: Router,
    private donacionService: DonacionService
  ) { }

  ngOnInit() {
    this.cargarUsuario();
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
        // alert('Error al editar usuario: ' + (err.error?.detail || err.message));
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
    // Filtra solo las donaciones del usuario logueado por id
    this.misDonaciones = (resp.content || []).filter((d: any) =>
      d.productoDonado?.clienteDonante?.id &&
      d.productoDonado.clienteDonante.id === this.usuarioId
    );
  });
}

  irADetalleProducto(id: number) {
    this.router.navigate(['/producto', id]);
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('tipo');
    localStorage.removeItem('profileImage');
    window.location.href = '/home';
  }
}