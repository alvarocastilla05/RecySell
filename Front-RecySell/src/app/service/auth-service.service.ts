import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  private loginUrl = 'http://localhost:8080/auth/login';
  private clienteUrl = 'http://localhost:8080/cliente/register';
  private trabajadorUrl = 'http://localhost:8080/trabajador/register';

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    // Puedes enviar el tipo si tu backend lo requiere, si no, omite el campo
    return this.http.post(this.loginUrl, { username, password });
  }

   registerCliente(data: any): Observable<any> {
    return this.http.post(this.clienteUrl, data);
  }

  registerTrabajador(data: any): Observable<any> {
    return this.http.post(this.trabajadorUrl, data);
  }

}