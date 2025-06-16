import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class RoleGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRoles: string[] = route.data['roles'];
    const userRole = (localStorage.getItem('tipo') || '').toUpperCase();

    // Permitir si el usuario no está logueado y la ruta es para CLIENTE
    if (expectedRoles.includes('CLIENTE') && !userRole) {
      return true;
    }
    // Permitir si el rol del usuario está en los roles permitidos
    if (expectedRoles.includes(userRole)) {
      return true;
    }

    // Si no cumple, redirige
    this.router.navigate(['/acceso-denegado']);
    return false;
  }
}