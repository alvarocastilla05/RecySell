import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  private apiUrl = 'http://localhost:8080/auth/login';

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    // Puedes enviar el tipo si tu backend lo requiere, si no, omite el campo
    return this.http.post(this.apiUrl, { username, password });
  }
}