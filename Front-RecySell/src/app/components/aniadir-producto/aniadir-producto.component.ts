import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductoService } from '../../service/producto-service.service';
import { CategoriaService } from '../../service/categoria-service.service';

@Component({
  selector: 'app-aniadir-producto',
  templateUrl: './aniadir-producto.component.html',
  styleUrls: ['./aniadir-producto.component.css']
})
export class AniadirProductoComponent implements OnInit {
  categorias: any[] = [];
  selectedCategory: any = null;
  imagenes: File[] = [];
  imagenesPreview: string[] = [];
  maxTotalSizeMB = 5;
  imagenesError = '';
  mensaje = '';
  error = '';
  editMode = false;
  productoId: number | null = null;
  categoriaIdPendiente: any = null;

  producto: any = {
    nombre: '',
    descripcion: '',
    precio: null,
    estado: '',
    categoriaId: null
  };

  estados = [
    { valor: 'COMO_NUEVO', label: 'Como nuevo', descripcion: 'Sin marcas de uso, funciona perfectamente.' },
    { valor: 'BUEN_ESTADO', label: 'Buen estado', descripcion: 'Con signos leves de uso, pero en buen estado.' },
    { valor: 'ESTADO_REGULAR', label: 'Estado regular', descripcion: 'Con signos evidentes de uso, pero funcional.' },
    { valor: 'CON_DANIOS', label: 'Con daños', descripcion: 'Con daños visibles o problemas funcionales.' }
  ];

  defaultCategoryImg = `data:image/svg+xml;utf8,<svg width='64' height='64' viewBox='0 0 64 64' fill='none' xmlns='http://www.w3.org/2000/svg'><rect width='64' height='64' rx='16' fill='%2353d22c'/><g><circle cx='32' cy='32' r='18' fill='white'/><path d='M32 20a12 12 0 1 1 0 24 12 12 0 0 1 0-24zm0 2a10 10 0 1 0 0 20 10 10 0 0 0 0-20zm0 3a7 7 0 1 1 0 14 7 7 0 0 1 0-14z' fill='%230e110f'/></g></svg>`;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private productoService: ProductoService,
    private categoriaService: CategoriaService
  ) {}

  ngOnInit() {
    this.cargarCategorias();
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.editMode = true;
        this.productoId = +id;
        this.cargarProducto(this.productoId);
      }
    });
  }

  cargarCategorias() {
    this.categoriaService.getCategorias().subscribe(resp => {
      this.categorias = resp.content || resp;
      if (this.categoriaIdPendiente) {
        this.selectedCategory = this.categorias.find(cat => String(cat.id) === String(this.categoriaIdPendiente));
        this.producto.categoriaId = this.selectedCategory?.id;
        this.categoriaIdPendiente = null;
      }
    });
  }

  async cargarProducto(id: number) {
    this.productoService.getProductoById(String(id)).subscribe(async producto => {
      this.producto = { ...producto };
      // Selección de categoría
      if (this.categorias.length) {
        this.selectedCategory = this.categorias.find(cat => String(cat.id) === String(producto.categoriaId));
        this.producto.categoriaId = this.selectedCategory?.id;
      } else {
        this.categoriaIdPendiente = producto.categoriaId;
      }
      // Previsualización y adjuntado de imágenes antiguas
      if (producto.imagenes && producto.imagenes.length) {
        this.imagenesPreview = producto.imagenes.map(
          (img: string) => `http://localhost:8080/uploads/${img}`
        );
        // Convierte las imágenes antiguas a File y añádelas a this.imagenes
        this.imagenes = [];
        for (const imgName of producto.imagenes) {
          const url = `http://localhost:8080/uploads/${imgName}`;
          const file = await this.fetchImageAsFile(url, imgName);
          this.imagenes.push(file);
        }
      }
    });
  }

  async fetchImageAsFile(url: string, filename: string): Promise<File> {
    const response = await fetch(url);
    const blob = await response.blob();
    return new File([blob], filename, { type: blob.type });
  }

  selectCategory(category: any) {
    this.selectedCategory = category;
    this.producto.categoriaId = category.id;
  }

  onFileSelected(event: any) {
    this.imagenesError = '';
    const files: File[] = Array.from(event.target.files);
    const totalSize = [...this.imagenes, ...files].reduce((acc, file) => acc + file.size, 0) / (1024 * 1024);
    if (totalSize > this.maxTotalSizeMB) {
      this.imagenesError = `El tamaño total de las imágenes no puede superar ${this.maxTotalSizeMB} MB.`;
      return;
    }
    // Añade las nuevas imágenes a las ya seleccionadas
    this.imagenes = [...this.imagenes, ...files];
    for (let file of files) {
      const reader = new FileReader();
      reader.onload = (e: any) => this.imagenesPreview.push(e.target.result);
      reader.readAsDataURL(file);
    }
  }

  eliminarImagen(index: number) {
    this.imagenes.splice(index, 1);
    this.imagenesPreview.splice(index, 1);
  }

  onImgError(event: any) {
    event.target.src = this.defaultCategoryImg;
  }

  guardar() {
    this.mensaje = '';
    this.error = '';
    if (!this.producto.categoriaId) {
      this.error = 'Debes seleccionar una categoría.';
      return;
    }
    const formData = new FormData();
    const productoParaEnviar = {
      ...this.producto,
      categoriaId: this.producto.categoriaId
    };
    formData.append('producto', new Blob([JSON.stringify(productoParaEnviar)], { type: 'application/json' }));
    for (let i = 0; i < this.imagenes.length; i++) {
      formData.append('files', this.imagenes[i]);
    }
    if (this.editMode && this.productoId) {
      this.productoService.editarProducto(this.productoId, formData).subscribe({
        next: () => {
          this.mensaje = 'Producto editado correctamente';
          this.router.navigate(['/']);
        },
        error: (err) => {
          this.error = 'Error al editar el producto';
        }
      });
    } else {
      this.productoService.anadirProducto(formData).subscribe({
        next: () => {
          this.mensaje = 'Producto añadido correctamente';
          this.router.navigate(['/']);
        },
        error: (err) => {
          this.error = 'Error al añadir el producto';
        }
      });
    }
  }
}