import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class CompraService {

    constructor(private http: HttpClient) { }


    getComprasPorCliente(idCliente: string) {
        return this.http.get<any>(`http://localhost:8080/compra/cliente/${idCliente}`);
    }
}