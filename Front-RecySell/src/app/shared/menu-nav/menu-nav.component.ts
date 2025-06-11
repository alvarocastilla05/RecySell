import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from '../../service/auth-service.service';

@Component({
  selector: 'app-menu-nav',
  templateUrl: './menu-nav.component.html',
  styleUrl: './menu-nav.component.css'
})
export class MenuNavComponent implements OnInit {

  isLoggedIn = false;
  userProfileImage = ''; // URL de la imagen de perfil del usuario
  esTrabajador = false; // Indica si el usuario es un trabajador

  constructor(private authService: AuthServiceService) {}

  ngOnInit() {
    this.authService.isLoggedIn$.subscribe(status => {
      this.isLoggedIn = status;
      // Lee el tipo de usuario del localStorage cada vez que cambia el estado de login
      this.esTrabajador = localStorage.getItem('tipo') === 'trabajador';
      this.userProfileImage = localStorage.getItem('profileImage') || '';
    });
  }
}