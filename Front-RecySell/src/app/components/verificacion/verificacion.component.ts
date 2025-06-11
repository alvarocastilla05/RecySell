import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthServiceService } from '../../service/auth-service.service';

@Component({
  selector: 'app-verificacion',
  templateUrl: './verificacion.component.html',
  styleUrl: './verificacion.component.css'
})
export class VerificacionComponent {
  verificacionForm: FormGroup;
  errorMsg: string = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthServiceService,
    private router: Router
  ) {
    this.verificacionForm = this.fb.group({
      token: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.verificacionForm.invalid) return;

    const token = this.verificacionForm.value.token;

    this.authService.verifyAccount(token).subscribe({
      next: () => this.router.navigate(['/login']),
      error: (err) => {
        this.errorMsg = err.error?.message || 'Token inválido o expirado';
      }
    });
  }
}