import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from '../../service/auth-service.service';
import { jwtDecode } from 'jwt-decode';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu-nav',
  templateUrl: './menu-nav.component.html',
  styleUrl: './menu-nav.component.css'
})
export class MenuNavComponent implements OnInit {

  isLoggedIn = false;
  userProfileImage = '';
  esTrabajador = false;

  constructor(private authService: AuthServiceService, private router: Router) { }

  ngOnInit() {
    this.isLoggedIn = this.checkToken();
    this.esTrabajador = localStorage.getItem('tipo') === 'trabajador';
    this.userProfileImage = localStorage.getItem('profileImage') || '';

    this.authService.isLoggedIn$.subscribe(status => {
      this.isLoggedIn = this.checkToken();
      this.esTrabajador = localStorage.getItem('tipo') === 'trabajador';
      this.userProfileImage = localStorage.getItem('profileImage') || '';
    });
  }

  checkToken(): boolean {
    const token = localStorage.getItem('token');
    if (!token) return false;
    try {
      const decoded: any = jwtDecode(token);
      // exp está en segundos desde Epoch
      if (decoded.exp && Date.now() >= decoded.exp * 1000) {
        // Token expirado, lo eliminamos
        localStorage.removeItem('token');
        return false;
      }
      return true;
    } catch (e) {
      // Token inválido
      localStorage.removeItem('token');
      return false;
    }
  }

  irAVender() {
    this.authService.isLoggedIn$.subscribe(isLogged => {
      if (isLogged) {
        this.router.navigate(['/vender']);
      } else {
        this.router.navigate(['/login']);
      }
    }).unsubscribe();
  }

  irADonar() {
    this.authService.isLoggedIn$.subscribe(isLogged => {
      if (isLogged) {
        this.router.navigate(['/donar']);
      } else {
        this.router.navigate(['/login']);
      }
    }).unsubscribe();
  }
}