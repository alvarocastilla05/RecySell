import { Component } from '@angular/core';
import { UsuarioService } from '../../service/usuario-service.service';

@Component({
  selector: 'app-usuarios-trabajador',
  templateUrl: './usuarios-trabajador.component.html',
  styleUrl: './usuarios-trabajador.component.css'
})
export class UsuariosTrabajadorComponent {

  filtro: 'trabajadores' | 'clientes' = 'trabajadores';

  trabajadores: any[] = [];
  clientes: any[] = [];
  page = 0;
  size = 8;
  totalPages = 1;

  // Modal trabajador
  mostrarModal = false;
  editando = false;
  trabajadorActual: any = {
    username: '', email: '', password: '', verifyPassword: '', nombre: '', apellido: '', puesto: ''
  };
  mensaje = '';
  error = '';
  mostrarPassword: boolean = false;
  mostrarPassword2: boolean = false;

  // Confirmación eliminar
  mostrarConfirmacion = false;
  trabajadorAEliminar: any = null;

  constructor(private usuarioService: UsuarioService) { }

  ngOnInit() {
    this.cargarUsuarios();
  }

  cargarUsuarios() {
    if (this.filtro === 'trabajadores') {
      this.usuarioService.listarTrabajadores(this.page, this.size).subscribe(resp => {
        this.trabajadores = resp.content;
        this.totalPages = resp.totalPages;
      });
    } else {
      this.usuarioService.listarClientes(this.page, this.size).subscribe(resp => {
        this.clientes = resp.content;
        this.totalPages = resp.totalPages;
      });
    }
  }

  cambiarFiltro(f: 'trabajadores' | 'clientes') {
    this.filtro = f;
    this.page = 0;
    this.cargarUsuarios();
  }

  anteriorPagina() {
    if (this.page > 0) {
      this.page--;
      this.cargarUsuarios();
    }
  }

  siguientePagina() {
    if (this.page < this.totalPages - 1) {
      this.page++;
      this.cargarUsuarios();
    }
  }

  abrirModal(trabajador: any = null) {
    this.editando = !!trabajador;
    if (trabajador) {
      // Si viene de la API como 'apellidos', lo pasamos a 'apellido'
      this.trabajadorActual = {
        ...trabajador,
        apellido: trabajador.apellido || trabajador.apellidos || '',
        password: '',
        verifyPassword: ''
      };
    } else {
      this.trabajadorActual = {
        username: '', email: '', password: '', verifyPassword: '', nombre: '', apellido: '', puesto: ''
      };
    }
    this.mensaje = '';
    this.error = '';
    this.mostrarModal = true;
  }

  cerrarModal() {
    this.mostrarModal = false;
  }

  guardarTrabajador() {
    // Validación de campos obligatorios
    if (
      !this.trabajadorActual.username ||
      !this.trabajadorActual.email ||
      !this.trabajadorActual.nombre ||
      !this.trabajadorActual.apellido ||
      !this.trabajadorActual.puesto
    ) {
      this.error = 'Rellena todos los campos obligatorios.';
      return;
    }

    // Si se va a cambiar la contraseña, valida que coincidan
    if (
      (this.trabajadorActual.password || this.trabajadorActual.verifyPassword) &&
      this.trabajadorActual.password !== this.trabajadorActual.verifyPassword
    ) {
      this.error = 'Las contraseñas no coinciden.';
      return;
    }

    const data = { ...this.trabajadorActual };

    // Si los campos de contraseña están vacíos, no los envíes
    if (!data.password) delete data.password;
    if (!data.verifyPassword) delete data.verifyPassword;

    // Si el backend espera 'apellido' pero el objeto tiene 'apellidos', asegúrate de enviar 'apellido'
    if (data.apellidos && !data.apellido) {
      data.apellido = data.apellidos;
      delete data.apellidos;
    }

    if (this.editando) {
      this.usuarioService.editarTrabajador(this.trabajadorActual.id, data).subscribe({
        next: () => {
          this.mensaje = '¡Trabajador editado!';
          setTimeout(() => {
            this.cerrarModal();
            this.cargarUsuarios();
          }, 1000);
        },
        error: (err) => {
          this.error = err?.error?.message || 'Error al editar el trabajador.';
        }
      });
    } else {
      this.usuarioService.crearTrabajador(data).subscribe({
        next: () => {
          this.mensaje = '¡Trabajador registrado!';
          setTimeout(() => {
            this.cerrarModal();
            this.cargarUsuarios();
          }, 1000);
        },
        error: (err) => {
          this.error = err?.error?.message || 'Error al registrar el trabajador.';
        }
      });
    }
  }

  confirmarEliminar(trabajador: any) {
    this.trabajadorAEliminar = trabajador;
    this.mostrarConfirmacion = true;
  }

  cancelarEliminar() {
    this.mostrarConfirmacion = false;
    this.trabajadorAEliminar = null;
  }

  eliminarTrabajador() {
    if (!this.trabajadorAEliminar) return;
    this.usuarioService.eliminarTrabajador(this.trabajadorAEliminar.id).subscribe({
      next: () => {
        this.mostrarConfirmacion = false;
        this.cargarUsuarios();
      },
      error: () => {
        this.error = 'Error al eliminar el trabajador.';
      }
    });
  }
}

