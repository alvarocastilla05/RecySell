import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class OrganizacionService {
  private baseUrl = 'http://localhost:8080/organizacion';

  constructor(private http: HttpClient) {}

  listarOrganizaciones(page = 0, size = 8): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}?page=${page}&size=${size}`);
  }

  crearOrganizacion(org: { nombre: string, descripcion: string, direccion: string }): Observable<any> {
    return this.http.post<any>(this.baseUrl, org);
  }

  editarOrganizacion(id: number, org: { nombre: string, descripcion: string, direccion: string }): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/${id}`, org);
  }

  eliminarOrganizacion(id: number): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/${id}`);
  }
}