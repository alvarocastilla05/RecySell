import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthServiceService } from '../../service/auth-service.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrl: './registro.component.css'
})
export class RegistroComponent implements OnInit {
  registroForm: FormGroup;
  esTrabajador: boolean = false;
  errorMsg: string = '';
  showPassword = false;
  showConfirmPassword = false;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthServiceService
  ) {
    this.registroForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      verifyPassword: ['', Validators.required],
      nombre: ['', Validators.required],
      apellidos: ['', Validators.required],
      puesto: ['']
    });
  }

  ngOnInit() {
    this.esTrabajador = this.router.url.includes('registro-trabajador');
    if (!this.esTrabajador) {
      this.registroForm.get('puesto')?.clearValidators();
      this.registroForm.get('puesto')?.updateValueAndValidity();
    } else {
      this.registroForm.get('puesto')?.setValidators([Validators.required]);
      this.registroForm.get('puesto')?.updateValueAndValidity();
    }
  }

  onSubmit() {
    if (this.registroForm.invalid) return;

    const { username, email, password, verifyPassword, nombre, apellidos, puesto } = this.registroForm.value;

    if (password !== verifyPassword) {
      this.errorMsg = 'Las contraseñas no coinciden';
      return;
    }

    const payload: any = { username, email, password, verifyPassword, nombre, apellidos };
    if (this.esTrabajador) payload.puesto = puesto;

    const obs = this.esTrabajador
      ? this.authService.registerTrabajador(payload)
      : this.authService.registerCliente(payload);

    obs.subscribe({
      next: () => this.router.navigate(['/verificacion']), 
      error: (err) => {
        this.errorMsg = err.error?.message || 'Error al registrar usuario';
      }
    });

  }
}