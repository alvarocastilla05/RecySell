import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class CompraService {

    private apiUrl = 'http://localhost:8080/compra';

    constructor(private http: HttpClient) { }

    getComprasPorCliente(idCliente: string) {
        return this.http.get<any>(`${this.apiUrl}/cliente/${idCliente}`);
    }

    getCompraById(id: number) {
        return this.http.get<any>(`${this.apiUrl}/${id}`);
    }

    confirmarCompra(id: number) {
        return this.http.put<any>(`${this.apiUrl}/${id}/confirmar`, {});
    }

    cancelarCompra(id: number) {
        return this.http.put<any>(`${this.apiUrl}/${id}/cancelar`, {});
    }
}