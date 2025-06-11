import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  private loginUrl = 'http://localhost:8080/auth/login';
  private clienteUrl = 'http://localhost:8080/cliente/register';
  private trabajadorUrl = 'http://localhost:8080/trabajador/register';

  private loggedIn = new BehaviorSubject<boolean>(this.hasToken());
  isLoggedIn$ = this.loggedIn.asObservable();

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    return this.http.post(this.loginUrl, { username, password });
  }

  loginSuccess(token: string) {
    localStorage.setItem('token', token);
    this.loggedIn.next(true);
  }

  logout() {
    localStorage.removeItem('token');
    this.loggedIn.next(false);
  }

  private hasToken(): boolean {
    return !!localStorage.getItem('token');
  }

  registerCliente(data: any): Observable<any> {
    return this.http.post(this.clienteUrl, data);
  }

  registerTrabajador(data: any): Observable<any> {
    return this.http.post(this.trabajadorUrl, data);
  }

  verifyAccount(token: string) {
    return this.http.post('http://localhost:8080/activate/account/', { token });
  }
}