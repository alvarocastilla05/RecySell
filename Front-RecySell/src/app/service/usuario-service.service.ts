import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProductoFavorito } from '../interfaces/product/product-fav.interface';

@Injectable({ providedIn: 'root' })
export class UsuarioService {
  private urlMe = 'http://localhost:8080/me';
  private urlCliente = 'http://localhost:8080/cliente';

  constructor(private http: HttpClient) { }

  getUsuarioMe() {
    return this.http.get<any>(this.urlMe);
  }

  getClienteById(id: string) {
    return this.http.get<any>(`${this.urlCliente}/${id}`);
  }

  editarCliente(id: string, usuario: any) {
    return this.http.put<any>(`${this.urlCliente}/${id}`, usuario);
  }

  getFavoritos(): Observable<ProductoFavorito[]> {
    return this.http.get<ProductoFavorito[]>('http://localhost:8080/cliente/producto');
  }

  getMisProductos(idCliente: string | null): Observable<any[]> {
    return this.http.get<any[]>(`http://localhost:8080/producto/cliente/${idCliente}`);
  }

  eliminarProducto(idProducto: number) {
    return this.http.delete(`http://localhost:8080/producto/${idProducto}`);
  }
}