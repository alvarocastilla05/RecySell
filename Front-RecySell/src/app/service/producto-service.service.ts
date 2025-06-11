import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProductoListResponse } from '../interfaces/product/product-list.inteface';

@Injectable({ providedIn: 'root' })
export class ProductoService {
  private listarUrl = 'http://localhost:8080/producto/listar';

  constructor(private http: HttpClient) {}

  getProductos(): Observable<ProductoListResponse> {
    return this.http.get<ProductoListResponse>(this.listarUrl);
  }
}