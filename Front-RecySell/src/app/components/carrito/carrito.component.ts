import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CompraService } from '../../service/compra-service.service';
import { HttpClient } from '@angular/common/http';
import { LineaServiceService } from '../../service/linea-service.service';

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrl: './carrito.component.css'
})
export class CarritoComponent implements OnInit {
  compra: any;
  compraId: number | null = null;

  // Modals
  showEliminarLineaModal = false;
  showConfirmarCompraModal = false;
  showCancelarCompraModal = false;
  showCompraFinalizadaModal = false;
  showCompraCanceladaModal = false;

  lineaAEliminar: any = null;

  constructor(
    private route: ActivatedRoute,
    private compraService: CompraService,
    private router: Router,
    private http: HttpClient,
    private lineaService: LineaServiceService
  ) {}

  ngOnInit() {
    this.compraId = Number(this.route.snapshot.paramMap.get('id'));
    if (this.compraId) {
      this.cargarCompra();
    }
  }

  cargarCompra() {
    if (!this.compraId) return;
    this.compraService.getCompraById(this.compraId).subscribe({
      next: (data) => this.compra = data,
      error: () => this.router.navigate(['/'])
    });
  }

  // Estado legible
  getEstadoCompraLegible(estado: string | undefined): string {
    switch (estado) {
      case 'CARRITO': return 'En carrito';
      case 'ENVIADO': return 'En envío';
      case 'ENTREGADA': return 'Entregada';
      case 'CANCELADO': return 'Cancelada';
      default: return estado || '';
    }
  }

  // Eliminar línea de venta
  abrirModalEliminarLinea(linea: any) {
    this.lineaAEliminar = linea;
    this.showEliminarLineaModal = true;
  }

  confirmarEliminarLinea() {
    if (!this.lineaAEliminar) return;
    // Ajusta aquí según el campo real de tu backend
    const idLinea = this.lineaAEliminar.idLineaVenta || this.lineaAEliminar.id || this.lineaAEliminar.lineaVentaId;
    this.lineaService.eliminarLineaVenta(idLinea).subscribe({
      next: () => {
        this.showEliminarLineaModal = false;
        this.lineaAEliminar = null;
        this.cargarCompra();
      },
      error: () => {
        this.showEliminarLineaModal = false;
        // Puedes mostrar un modal de error si quieres
      }
    });
  }

  // Finalizar compra
  abrirModalConfirmarCompra() {
    this.showConfirmarCompraModal = true;
  }

  confirmarFinalizarCompra() {
    if (!this.compraId) return;
    this.compraService.confirmarCompra(this.compraId).subscribe({
      next: () => {
        this.showConfirmarCompraModal = false;
        this.showCompraFinalizadaModal = true;
        this.cargarCompra();
      },
      error: () => {
        this.showConfirmarCompraModal = false;
        // Puedes mostrar un modal de error si quieres
      }
    });
  }

  // Cancelar compra
  abrirModalCancelarCompra() {
    this.showCancelarCompraModal = true;
  }

  confirmarCancelarCompra() {
    if (!this.compraId) return;
    this.compraService.cancelarCompra(this.compraId).subscribe({
      next: () => {
        this.showCancelarCompraModal = false;
        this.showCompraCanceladaModal = true;
        this.cargarCompra();
      },
      error: () => {
        this.showCancelarCompraModal = false;
        // Puedes mostrar un modal de error si quieres
      }
    });
  }
}