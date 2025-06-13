import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LineaServiceService {

  private apiUrl = 'http://localhost:8080/linea-venta';

  constructor(private http: HttpClient) { }

  eliminarLineaVenta(idLineaVenta: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${idLineaVenta}`);
  }
}