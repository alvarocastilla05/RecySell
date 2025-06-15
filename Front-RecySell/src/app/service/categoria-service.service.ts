import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CategoriaListResponse } from '../interfaces/categoria/categoria-list.interface';

@Injectable({ providedIn: 'root' })
export class CategoriaService {
  private url = 'http://localhost:8080/categoria'; // ajusta si tu endpoint es diferente

  constructor(private http: HttpClient) {}

  getCategorias(): Observable<CategoriaListResponse> {
    return this.http.get<CategoriaListResponse>(this.url);
  }

  listarCategorias(page = 0, size = 8): Observable<CategoriaListResponse> {
    return this.http.get<CategoriaListResponse>(`${this.url}?page=${page}&size=${size}`);
  }

  crearCategoria(formData: FormData): Observable<any> {
    return this.http.post<any>(this.url, formData);
  }
}