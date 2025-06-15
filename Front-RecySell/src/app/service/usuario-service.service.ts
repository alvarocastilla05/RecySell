import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProductoFavorito } from '../interfaces/product/product-fav.interface';

@Injectable({ providedIn: 'root' })
export class UsuarioService {
  private urlMe = 'http://localhost:8080/me';
  private urlCliente = 'http://localhost:8080/cliente';
  private baseTrabajador = 'http://localhost:8080/trabajador';
  

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


  listarTrabajadores(page = 0, size = 8): Observable<any> {
    return this.http.get<any>(`${this.baseTrabajador}?page=${page}&size=${size}`);
  }

  listarClientes(page = 0, size = 8): Observable<any> {
    return this.http.get<any>(`${this.urlCliente}?page=${page}&size=${size}`);
  }

  crearTrabajador(data: any): Observable<any> {
    // Cambiado a /register
    return this.http.post<any>(`${this.baseTrabajador}/register`, data);
  }

  editarTrabajador(id: string, data: any): Observable<any> {
    return this.http.put<any>(`${this.baseTrabajador}/${id}`, data);
  }

  eliminarTrabajador(id: string): Observable<any> {
    return this.http.delete<any>(`${this.baseTrabajador}/${id}`);
  }
}
