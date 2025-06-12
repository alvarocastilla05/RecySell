import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from '../../service/auth-service.service';

@Component({
  selector: 'app-menu-nav',
  templateUrl: './menu-nav.component.html',
  styleUrl: './menu-nav.component.css'
})
export class MenuNavComponent implements OnInit {

  isLoggedIn = false;
  userProfileImage = '';
  esTrabajador = false;

  constructor(private authService: AuthServiceService) {}

  ngOnInit() {
    // Comprobar si hay token al iniciar el componente
    this.isLoggedIn = !!localStorage.getItem('token');
    this.esTrabajador = localStorage.getItem('tipo') === 'trabajador';
    this.userProfileImage = localStorage.getItem('profileImage') || '';

    // Suscribirse a cambios de login para actualizar el menú dinámicamente
    this.authService.isLoggedIn$.subscribe(status => {
      this.isLoggedIn = status || !!localStorage.getItem('token');
      this.esTrabajador = localStorage.getItem('tipo') === 'trabajador';
      this.userProfileImage = localStorage.getItem('profileImage') || '';
    });
  }
}