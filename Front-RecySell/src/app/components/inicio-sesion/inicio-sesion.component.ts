import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthServiceService } from '../../service/auth-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inicio-sesion',
  templateUrl: './inicio-sesion.component.html',
  styleUrl: './inicio-sesion.component.css'
})
export class InicioSesionComponent {
  loginForm: FormGroup;
  errorMsg: string = '';
  showPassword = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthServiceService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.invalid) return;

    const { username, password } = this.loginForm.value;

    this.authService.login(username, password).subscribe({
      next: (res) => {
        // Guarda el token y actualiza el estado de login
        console.log('Login exitoso:', res);
        this.authService.loginSuccess(res.token);
        // Guarda el tipo de usuario para el nav
        localStorage.setItem('tipo', res.role?.toLowerCase() || 'cliente');
        // Redirección según el rol recibido
        if (res.role === 'CLIENTE') {
          this.router.navigate(['/hora-creada']);
        } else if (res.role === 'TRABAJADOR') {
          this.router.navigate(['/home-trabajador']); // Cambia la ruta según tu app
        } else {
          this.router.navigate(['/home']);
        }
      },
      error: (err) => {
        this.errorMsg = 'Usuario o contraseña incorrectos';
      }
    });
  }
}