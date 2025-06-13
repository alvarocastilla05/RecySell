import { Component, OnInit } from '@angular/core';
import { CompraService } from '../../service/compra-service.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-crear-compra',
  templateUrl: './crear-compra.component.html',
  styleUrls: ['./crear-compra.component.css']
})
export class CrearCompraComponent implements OnInit {
  compra = {
    direccionEntrega: '',
    provincia: '',
    codigoPostal: ''
  };
  mensaje = '';
  error = '';
  esEdicion = false;
  compraId: number | null = null;

  constructor(
    private compraService: CompraService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    // Detecta si es edición por la ruta /edita-compra/:id
    this.compraId = Number(this.route.snapshot.paramMap.get('id'));
    if (this.compraId) {
      this.esEdicion = true;
      this.compraService.getCompraById(this.compraId).subscribe({
        next: (data: any) => this.compra = data,
        error: () => this.error = 'No se pudo cargar la compra.'
      });
    }
  }

  crearCompra() {
    this.mensaje = '';
    this.error = '';
    this.compraService.crearCompra(this.compra).subscribe({
      next: () => {
        this.mensaje = '¡Compra creada con éxito!';
        setTimeout(() => {
          this.router.navigate(['/mi-perfil'], { queryParams: { apartado: 'compras' } });
        }, 1200);
      },
      error: () => {
        this.error = 'Error al crear la compra. Inténtalo de nuevo.';
      }
    });
  }

  actualizarCompra() {
    if (!this.compraId) return;
    this.mensaje = '';
    this.error = '';
    this.compraService.editarCompra(this.compraId, this.compra).subscribe({
      next: () => {
        this.mensaje = '¡Compra actualizada!';
        setTimeout(() => {
          this.router.navigate(['/mi-perfil'], { queryParams: { apartado: 'compras' } });
        }, 1200);
      },
      error: () => {
        this.error = 'Error al actualizar la compra.';
      }
    });
  }
}