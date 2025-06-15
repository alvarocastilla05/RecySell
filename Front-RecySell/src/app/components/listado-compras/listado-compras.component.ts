import { Component, OnInit } from '@angular/core';
import { CompraService } from '../../service/compra-service.service';

@Component({
  selector: 'app-listado-compras',
  templateUrl: './listado-compras.component.html',
  styleUrl: './listado-compras.component.css'
})
export class ListadoComprasComponent implements OnInit{
  todasLasCompras: any[] = [];
  comprasFiltradas: any[] = [];
  comprasPagina: any[] = [];
  page = 0;
  size = 5;
  totalPages = 1;
  totalElements = 0;

  estadoFiltro: string = '';
  estadoDropdown = false;
  estadosDisponibles: { valor: string, label: string, color: string }[] = [
    { valor: 'CARRITO', label: 'En carrito', color: 'bg-yellow-400 text-black' },
    { valor: 'EN_ENVIO', label: 'En envío', color: 'bg-blue-400 text-white' },
    { valor: 'ENTREGADO', label: 'Entregado', color: 'bg-green-500 text-white' },
    { valor: 'CANCELADO', label: 'Cancelado', color: 'bg-red-500 text-white' }
  ];

  constructor(private compraService: CompraService) {}

  ngOnInit() {
    // Trae todas las compras (ajusta el size si tienes muchas)
    this.compraService.getCompras(0, 1000).subscribe(resp => {
      this.todasLasCompras = resp.content;
      this.aplicarFiltroYPaginacion();
    });
  }

  aplicarFiltroYPaginacion() {
    // Filtrar por estado
    if (!this.estadoFiltro) {
      this.comprasFiltradas = this.todasLasCompras;
    } else {
      this.comprasFiltradas = this.todasLasCompras.filter(c => c.estadoCompra === this.estadoFiltro);
    }
    // Paginar
    this.totalElements = this.comprasFiltradas.length;
    this.totalPages = Math.ceil(this.totalElements / this.size);
    const start = this.page * this.size;
    this.comprasPagina = this.comprasFiltradas.slice(start, start + this.size);
  }

  filtrarPorEstado() {
    this.page = 0;
    this.aplicarFiltroYPaginacion();
    this.estadoDropdown = false;
  }

  anteriorPagina() {
    if (this.page > 0) {
      this.page--;
      this.aplicarFiltroYPaginacion();
    }
  }

  siguientePagina() {
    if (this.page < this.totalPages - 1) {
      this.page++;
      this.aplicarFiltroYPaginacion();
    }
  }

  getEstadoLabel(estado: string): string {
    const found = this.estadosDisponibles.find(e => e.valor === estado);
    return found ? found.label : estado;
  }

  getEstadoColor(estado: string): string {
    const found = this.estadosDisponibles.find(e => e.valor === estado);
    return found ? found.color : 'bg-gray-400 text-white';
  }
}

