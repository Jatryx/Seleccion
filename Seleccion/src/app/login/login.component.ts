import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Usuario } from '../models/modelUsuario/usuario.model';
import { UsuarioService } from '../services/serviceUsuario/usuario.service';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true,
  imports: [
    HttpClientModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatButtonModule, 
    CommonModule
  ],
  providers: [UsuarioService]
})
export class LoginComponent {
  @Output() loginSuccess = new EventEmitter<void>();
  loginForm: FormGroup;
  errorMessage: string;

  constructor(private fb: FormBuilder, private router: Router, private usuarioService: UsuarioService) {  // Asegúrate de inyectar Router aquí
    this.loginForm = this.fb.group({
      codope: ['', [Validators.required]],
      contraseña: ['', [Validators.required]],
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {

      const usuario: Usuario = {
        codope: this.loginForm.value.codope,
        contraseña: this.loginForm.value.contraseña,
        activo: true
      }

      this.usuarioService.login(usuario).subscribe({
        next: (response: boolean) => {
          console.log('Respuesta del servidor:', response);
          if (response) {
            localStorage.setItem('isLoggedIn', 'true');
            localStorage.setItem('codopeActual', usuario.codope);
            this.router.navigate(['/candidatos-ofertados']);
          } else {
            this.errorMessage = 'Usuario o contraseña incorrectos';
          }
        },
        error: (error) => {
          console.error('Error en el login:', error);
          this.errorMessage = 'Error al intentar iniciar sesión';
        }
      });
    }
  }
}