import { Component, OnInit } from '@angular/core';
import { CategoriaService } from '../../service/categoria-service.service';
import { Categoria, CategoriaListResponse } from '../../interfaces/categoria/categoria-list.interface';

@Component({
  selector: 'app-categorias-trabajador',
  templateUrl: './categorias-trabajador.component.html',
  styleUrl: './categorias-trabajador.component.css'
})
export class CategoriasTrabajadorComponent implements OnInit {

 categorias: any[] = [];
  page = 0;
  size = 8;
  totalPages = 1;

  // Modal
  mostrarModal = false;
  nuevaCategoriaNombre = '';
  nuevaCategoriaArchivo: File | null = null;
  nuevaCategoriaPreview: string | null = null;
  mensaje = '';
  error = '';
  imagenesError = '';

  constructor(private categoriaService: CategoriaService) {}

  ngOnInit() {
    this.cargarCategorias();
  }

  cargarCategorias() {
    this.categoriaService.listarCategorias(this.page, this.size).subscribe(resp => {
      this.categorias = resp.content;
      this.totalPages = resp.totalPages;
    });
  }

  anteriorPagina() {
    if (this.page > 0) {
      this.page--;
      this.cargarCategorias();
    }
  }

  siguientePagina() {
    if (this.page < this.totalPages - 1) {
      this.page++;
      this.cargarCategorias();
    }
  }

  abrirModal() {
    this.nuevaCategoriaNombre = '';
    this.nuevaCategoriaArchivo = null;
    this.nuevaCategoriaPreview = null;
    this.mensaje = '';
    this.error = '';
    this.imagenesError = '';
    this.mostrarModal = true;
  }

  cerrarModal() {
    this.mostrarModal = false;
  }

  onFileChange(event: any) {
    const file = event.target.files && event.target.files[0];
    this.setImagenCategoria(file);
  }

  onDrop(event: DragEvent) {
    event.preventDefault();
    const file = event.dataTransfer?.files && event.dataTransfer.files[0] ? event.dataTransfer.files[0] : null;
    this.setImagenCategoria(file);
  }

  onDragOver(event: DragEvent) {
    event.preventDefault();
  }

  onDragLeave(event: DragEvent) {
    event.preventDefault();
  }

  setImagenCategoria(file: File | null) {
    this.imagenesError = '';
    if (!file) return;
    if (!file.type.startsWith('image/')) {
      this.imagenesError = 'Solo se permiten imágenes.';
      return;
    }
    this.nuevaCategoriaArchivo = file;
    const reader = new FileReader();
    reader.onload = (e: any) => {
      this.nuevaCategoriaPreview = e.target.result;
    };
    reader.readAsDataURL(file);
  }

  eliminarImagen() {
    this.nuevaCategoriaArchivo = null;
    this.nuevaCategoriaPreview = null;
  }

  crearCategoria() {
    if (!this.nuevaCategoriaNombre || !this.nuevaCategoriaArchivo) {
      this.error = 'Rellena todos los campos.';
      return;
    }
    const formData = new FormData();
    formData.append('categoria', new Blob([JSON.stringify({ nombre: this.nuevaCategoriaNombre })], { type: 'application/json' }));
    formData.append('file', this.nuevaCategoriaArchivo);

    this.categoriaService.crearCategoria(formData).subscribe({
      next: () => {
        this.mensaje = '¡Categoría añadida!';
        setTimeout(() => {
          this.cerrarModal();
          this.cargarCategorias();
        }, 1000);
      },
      error: () => {
        this.error = 'Error al crear la categoría.';
      }
    });
  }
}