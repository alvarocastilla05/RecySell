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
}