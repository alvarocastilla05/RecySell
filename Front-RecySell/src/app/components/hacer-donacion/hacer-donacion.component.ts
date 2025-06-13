import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DonacionService } from '../../service/donacion-service.service';
import { UsuarioService } from '../../service/usuario-service.service';
import { ProductoListResponse } from '../../interfaces/product/product-list.inteface';

@Component({
  selector: 'app-hacer-donacion',
  templateUrl: './hacer-donacion.component.html',
  styleUrls: ['./hacer-donacion.component.css']
})
export class HacerDonacionComponent implements OnInit {
  organizaciones: any[] = [];
  productos: any[] = [];
  selectedOrganizacion: any = null;
  selectedProducto: any = null;
  mensaje = '';
  error = '';

  constructor(
    private donacionService: DonacionService,
    private usuarioService: UsuarioService,
    private router: Router
  ) { }

  ngOnInit() {
    this.cargarOrganizaciones();
    this.cargarProductos();
  }

  cargarOrganizaciones() {
    this.donacionService.getOrganizaciones().subscribe(resp => {
      this.organizaciones = resp.content || resp;
    });
  }

  cargarProductos() {
    let userId = localStorage.getItem('userId');
    if (!userId) {
      userId = this.getUserIdFromToken();
    }
    this.usuarioService.getMisProductos(userId).subscribe(resp => {
      this.productos = Array.isArray(resp) ? resp : resp || [];
    });
  }

  selectOrganizacion(org: any) {
    this.selectedOrganizacion = org;
  }

  selectProducto(prod: any) {
    this.selectedProducto = prod;
  }

  confirmarDonacion() {
    this.mensaje = '';
    this.error = '';
    if (!this.selectedOrganizacion || !this.selectedProducto) {
      this.error = 'Debes seleccionar una organización y un producto.';
      return;
    }
    const donacion = {
      productoId: this.selectedProducto.id,
      organizacionId: this.selectedOrganizacion.id
    };
    this.donacionService.crearDonacion(donacion).subscribe({
      next: () => {
        this.mensaje = '¡Donación realizada con éxito!';
        this.error = '';
        setTimeout(() => this.router.navigate(['/']), 1500);
      },
      error: () => {
        this.error = 'Error al realizar la donación.';
      }
    });
  }

  getUserIdFromToken(): string | null {
    const token = localStorage.getItem('token');
    if (!token) return null;
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      // Ajusta el campo según tu backend: id, userId, sub, etc.
      return payload.id || payload.userId || payload.sub || null;
    } catch {
      return null;
    }
  }
}