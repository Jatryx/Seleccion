import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-candidatos-ofertados',
  templateUrl: './candidatos-ofertados.component.html',
  styleUrls: ['./candidatos-ofertados.component.css'],
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule]
})
export class CandidatosOfertadosComponent {
  candidatoForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.candidatoForm = this.fb.group({
      candidato: ['', Validators.required],
      proyecto: ['', Validators.required],
      cliente: ['', Validators.required],
      ubicacion: ['', Validators.required],
      perfil: ['', Validators.required],
      tecnologia: ['', Validators.required],
      experiencia: ['', [Validators.required, Validators.min(0)]],
      salario: ['', [Validators.required, Validators.min(0)]],
      estado: ['', Validators.required],
      fechaActualizacion: ['', Validators.required],
      resumen: ['']
    });
  }

  onSubmit() {
    if (this.candidatoForm.valid) {
      console.log(this.candidatoForm.value);
    } else {
      console.log('Formulario no válido');
    }
  }
}
