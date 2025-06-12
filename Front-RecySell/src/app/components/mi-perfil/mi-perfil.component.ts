import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../service/usuario-service.service';
import { ProductoFavorito } from '../../interfaces/product/product-fav.interface';

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


  constructor(private usuarioService: UsuarioService) { }

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

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('tipo');
    localStorage.removeItem('profileImage');
    // Redirige al login o home
    window.location.href = '/home';
  }
}