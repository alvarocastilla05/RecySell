import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home-trabajador',
  templateUrl: './home-trabajador.component.html',
  styleUrl: './home-trabajador.component.css'
})
export class HomeTrabajadorComponent implements OnInit {
  
  usuarioRol: string = '';
  mostrarModalLogout = false;

  ngOnInit(): void {
    this.usuarioRol = (localStorage.getItem('tipo') || '').toUpperCase();
    
  }


  cerrarSesion() {
    this.mostrarModalLogout = true;
  }

  confirmarLogout() {
    this.mostrarModalLogout = false;
    localStorage.removeItem('token');
    localStorage.removeItem('tipo');
    localStorage.removeItem('profileImage');
    window.location.href = '/home';
  }
}