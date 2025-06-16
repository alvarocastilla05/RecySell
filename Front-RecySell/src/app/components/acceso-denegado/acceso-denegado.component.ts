import { Component } from '@angular/core';

@Component({
  selector: 'app-acceso-denegado',
  templateUrl: './acceso-denegado.component.html',
  styleUrl: './acceso-denegado.component.css'
})
export class AccesoDenegadoComponent {

  usuarioRol: string = '';

ngOnInit() {
  this.usuarioRol = (localStorage.getItem('tipo') || '').toUpperCase();
}

}
