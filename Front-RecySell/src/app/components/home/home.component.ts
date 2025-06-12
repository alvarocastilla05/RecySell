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

  constructor(private productoService: ProductoService) { }

  ngOnInit() {
    this.productoService.getProductos({ size: 100 }).subscribe(res => {
      this.productos = res.content
        .sort((a, b) => new Date(b.fechaRegistro).getTime() - new Date(a.fechaRegistro).getTime())
        .slice(0, 4);
    });
  }
}