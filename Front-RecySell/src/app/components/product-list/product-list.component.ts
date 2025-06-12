import { Component, OnInit } from '@angular/core';
import { ProductoService } from '../../service/producto-service.service';
import { CategoriaService } from '../../service/categoria-service.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html'
})
export class ProductListComponent implements OnInit {
  productos: any[] = [];
  currentPage = 0;
  totalPages = 1;

  estados: { value: string, label: string }[] = [
    { value: 'COMO_NUEVO', label: 'Como nuevo' },
    { value: 'BUEN_ESTADO', label: 'Buen estado' },
    { value: 'ESTADO_REGULAR', label: 'Estado regular' },
    { value: 'CON_DANIOS', label: 'Con daños' } // label con ñ, value como en el backend
  ]; categorias: any[] = [];
  filtroEstado: string = '';
  filtroCategoria: string = '';
  filtroPrecioMin: number | null = null;
  filtroPrecioMax: number | null = null;

  constructor(
    private productoService: ProductoService,
    private categoriaService: CategoriaService
  ) { }

  ngOnInit() {
    this.cargarCategorias();
    this.cargarProductos();
  }

  cargarCategorias() {
    this.categoriaService.getCategorias().subscribe(resp => {
      this.categorias = resp.content || [];
    });
  }

  construirSearch(): string {
    let filtros: string[] = [];
    if (this.filtroCategoria) filtros.push(`categoria:${this.filtroCategoria}`);
    if (this.filtroPrecioMin != null && this.filtroPrecioMin !== undefined) filtros.push(`precio>${this.filtroPrecioMin}`);
    if (this.filtroPrecioMax != null && this.filtroPrecioMax !== undefined) filtros.push(`precio<${this.filtroPrecioMax}`);
    if (this.filtroEstado) filtros.push(`estado:${this.filtroEstado}`);
    return filtros.join(',');
  }

  cargarProductos(page: number = 0) {
    const search = this.construirSearch();
    const params: any = { page };
    if (search) params.search = search;
    this.productoService.getProductos(params).subscribe(
      resp => {
        this.productos = resp.content || [];
        this.totalPages = resp.totalPages || Math.ceil((resp.totalElements || 0) / (resp.size || 1));
        this.currentPage = resp.number || 0;
      },
      error => {
        this.productos = []; // Si hay error, vacía el array
      }
    );
  }

  aplicarFiltros() {
    this.cargarProductos(0);
  }

  cambiarPagina(page: number) {
    this.cargarProductos(page);
  }

  limpiarFiltros() {
    this.filtroEstado = '';
    this.filtroCategoria = '';
    this.filtroPrecioMin = null;
    this.filtroPrecioMax = null;
    this.cargarProductos(0);
  }
}