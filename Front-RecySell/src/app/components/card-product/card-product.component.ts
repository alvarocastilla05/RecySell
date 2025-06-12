import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Producto } from '../../interfaces/product/product-list.inteface';

@Component({
  selector: 'app-card-product',
  templateUrl: './card-product.component.html',
  styleUrl: './card-product.component.css'
})
export class CardProductComponent {
  @Input() producto!: Producto;

  imageUrl: string = '';

  constructor(private router: Router) {}

  ngOnChanges() {
    this.setImageUrl();
  }

  setImageUrl() {
    this.imageUrl = this.producto.imagenes && this.producto.imagenes.length > 0
      ? 'http://localhost:8080/uploads/' + this.producto.imagenes[0]
      : 'https://cdn-icons-png.flaticon.com/512/2748/2748558.png';
  }

  goToDetails() {
  const token = localStorage.getItem('token');
  if (!token) {
    this.router.navigate(['/login']);
    return;
  }
  this.router.navigate(['/producto', this.producto.id]);
}

  getEstadoLegible(estado: string): string {
  switch (estado) {
    case 'COMO_NUEVO': return 'Como nuevo';
    case 'BUEN_ESTADO': return 'Buen estado';
    case 'ESTADO_REGULAR': return 'Estado regular';
    case 'CON_DANIOS': return 'Con daños';
    default: return estado.replace(/_/g, ' ').toLowerCase();
  }
}
}