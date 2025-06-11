import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthServiceService } from '../../service/auth-service.service';
import { Router } from '@angular/router'; // <-- Importa Router

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
    private router: Router // <-- Inyecta Router
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
        // Redirige a /home si el login es exitoso
        this.router.navigate(['/home']);
      },
      error: (err) => {
        this.errorMsg = 'Usuario o contraseña incorrectos';
      }
    });
  }
}