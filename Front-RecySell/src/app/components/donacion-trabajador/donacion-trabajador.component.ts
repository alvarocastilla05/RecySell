import { Component, OnInit } from '@angular/core';
import { DonacionService } from '../../service/donacion-service.service';

@Component({
  selector: 'app-donacion-trabajador',
  templateUrl: './donacion-trabajador.component.html',
  styleUrl: './donacion-trabajador.component.css'
})
export class DonacionTrabajadorComponent implements OnInit{
donaciones: any[] = [];
  page = 0;
  size = 5;
  totalPages = 1;
  totalElements = 0;

  constructor(private donacionService: DonacionService) {}

  ngOnInit() {
    this.cargarDonaciones();
  }

  cargarDonaciones(page: number = 0) {
    this.donacionService.getDonacionesTrabajador(page, this.size).subscribe(resp => {
      this.donaciones = resp.content;
      this.page = resp.number;
      this.size = resp.size;
      this.totalPages = resp.totalPages;
      this.totalElements = resp.totalElements;
    });
  }

  anteriorPagina() {
    if (this.page > 0) this.cargarDonaciones(this.page - 1);
  }

  siguientePagina() {
    if (this.page < this.totalPages - 1) this.cargarDonaciones(this.page + 1);
  }
}

