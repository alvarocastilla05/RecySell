import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProductoListResponse } from '../interfaces/product/product-list.inteface';

@Injectable({ providedIn: 'root' })
export class ProductoService {
  private listarUrl = 'http://localhost:8080/producto/listar';

  constructor(private http: HttpClient) { }

  getProductos(params?: any): Observable<ProductoListResponse> {
    return this.http.get<ProductoListResponse>(this.listarUrl, { params });
  }

  getProductoById(id: string) {
    return this.http.get<any>(`http://localhost:8080/producto/${id}`);
  }

  anadirAFavoritos(idProducto: string) {
    return this.http.put(`http://localhost:8080/cliente/producto/${idProducto}`, {});
  }

  eliminarDeFavoritos(idProducto: string) {
    return this.http.delete(`http://localhost:8080/cliente/producto/${idProducto}`);
  }

  anadirAlCarrito(compraId: number, productoId: number) {
    const body = { compraId, productoId };
    return this.http.post('http://localhost:8080/linea-venta', body);
  }

  eliminarProducto(idProducto: number) {
  return this.http.delete(`http://localhost:8080/producto/${idProducto}`);
}


}