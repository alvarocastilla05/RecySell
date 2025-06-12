import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class UsuarioService {
  private urlMe = 'http://localhost:8080/me';
  private urlCliente = 'http://localhost:8080/cliente';

  constructor(private http: HttpClient) {}

  getUsuarioMe() {
    return this.http.get<any>(this.urlMe);
  }

  getClienteById(id: string) {
    return this.http.get<any>(`${this.urlCliente}/${id}`);
  }

  editarCliente(id: string, usuario: any) {
    return this.http.put<any>(`${this.urlCliente}/${id}`, usuario);
  }
}