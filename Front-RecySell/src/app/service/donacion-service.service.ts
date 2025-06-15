import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class DonacionService {

    constructor(private http: HttpClient) { }


    getDonaciones(): Observable<any> {
        return this.http.get<any>('http://localhost:8080/donacion');
    }

    getDonacionesTrabajador(page: number = 0, size: number = 10): Observable<any> {
        return this.http.get<any>(`http://localhost:8080/donacion?page=${page}&size=${size}`);
    }

    crearDonacion(donacion: { productoId: number, organizacionId: number }): Observable<any> {
        return this.http.post('http://localhost:8080/donacion', donacion);
    }

    getOrganizaciones(): Observable<any> {
        return this.http.get<any>('http://localhost:8080/organizacion');
    }
}