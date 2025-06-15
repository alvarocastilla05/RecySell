import { Component, OnInit } from '@angular/core';
import { OrganizacionService } from '../../service/organacizacion.service';

@Component({
  selector: 'app-organizacion-trabajador',
  templateUrl: './organizacion-trabajador.component.html',
  styleUrl: './organizacion-trabajador.component.css'
})
export class OrganizacionTrabajadorComponent implements OnInit {

   organizaciones: any[] = [];
  page = 0;
  size = 8;
  totalPages = 1;

  // Modal
  mostrarModal = false;
  editando = false;
  organizacionActual: any = { nombre: '', descripcion: '', direccion: '' };
  mensaje = '';
  error = '';

  // Confirmación eliminar
  mostrarConfirmacion = false;
  organizacionAEliminar: any = null;

  constructor(private organizacionService: OrganizacionService) {}

  ngOnInit() {
    this.cargarOrganizaciones();
  }

  cargarOrganizaciones() {
    this.organizacionService.listarOrganizaciones(this.page, this.size).subscribe(resp => {
      this.organizaciones = resp.content;
      this.totalPages = resp.totalPages;
    });
  }

  anteriorPagina() {
    if (this.page > 0) {
      this.page--;
      this.cargarOrganizaciones();
    }
  }

  siguientePagina() {
    if (this.page < this.totalPages - 1) {
      this.page++;
      this.cargarOrganizaciones();
    }
  }

  abrirModal(organizacion: any = null) {
    this.editando = !!organizacion;
    this.organizacionActual = organizacion
      ? { ...organizacion }
      : { nombre: '', descripcion: '', direccion: '' };
    this.mensaje = '';
    this.error = '';
    this.mostrarModal = true;
  }

  cerrarModal() {
    this.mostrarModal = false;
  }

  guardarOrganizacion() {
    if (!this.organizacionActual.nombre || !this.organizacionActual.descripcion || !this.organizacionActual.direccion) {
      this.error = 'Rellena todos los campos.';
      return;
    }
    if (this.editando) {
      this.organizacionService.editarOrganizacion(this.organizacionActual.id, this.organizacionActual).subscribe({
        next: () => {
          this.mensaje = '¡Organización editada!';
          setTimeout(() => {
            this.cerrarModal();
            this.cargarOrganizaciones();
          }, 1000);
        },
        error: () => {
          this.error = 'Error al editar la organización.';
        }
      });
    } else {
      this.organizacionService.crearOrganizacion(this.organizacionActual).subscribe({
        next: () => {
          this.mensaje = '¡Organización añadida!';
          setTimeout(() => {
            this.cerrarModal();
            this.cargarOrganizaciones();
          }, 1000);
        },
        error: () => {
          this.error = 'Error al crear la organización.';
        }
      });
    }
  }

  confirmarEliminar(organizacion: any) {
    this.organizacionAEliminar = organizacion;
    this.mostrarConfirmacion = true;
  }

  cancelarEliminar() {
    this.mostrarConfirmacion = false;
    this.organizacionAEliminar = null;
  }

  eliminarOrganizacion() {
    if (!this.organizacionAEliminar) return;
    this.organizacionService.eliminarOrganizacion(this.organizacionAEliminar.id).subscribe({
      next: () => {
        this.mostrarConfirmacion = false;
        this.cargarOrganizaciones();
      },
      error: () => {
        this.error = 'Error al eliminar la organización.';
      }
    });
  }
}




