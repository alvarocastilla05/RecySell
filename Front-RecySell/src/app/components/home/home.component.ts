import { Component, OnInit } from '@angular/core';
import { ProductoService } from '../../service/producto-service.service';
import { Producto } from '../../interfaces/product/product-list.inteface';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  productos: Producto[] = [];

  constructor(private productoService: ProductoService) {}

  ngOnInit() {
    this.productoService.getProductos().subscribe(res => {
      this.productos = res.content.slice(0, 4); // Solo los primeros 5
    });
  }
}