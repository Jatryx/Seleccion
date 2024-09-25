import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Ofertas } from '../models/modelOfertas/ofertas.model';
import { OfertasService } from '../services/serviceOfertas/ofertas.service';


@Component({
  selector: 'app-candidatos-ofertados',
  templateUrl: './candidatos-ofertados.component.html',
  styleUrls: ['./candidatos-ofertados.component.css'],
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule]
})
export class CandidatosOfertadosComponent {

 

  constructor(private fb: FormBuilder, private ofertaService : OfertasService) {
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

  ofertaLista:Ofertas[] = [];

	candidatoForm: FormGroup;

  /*ngOnInit(){
      this.cargarOfertas();
  }

  cargarOfertas(){
    this.ofertaService.getOfertas.subscribe(data => {
      this.tipos = data;
      this.tipoBuscar = data;
    })
    this.cambiarMensajeActivos()
  }*/
  onSubmit() {
    if (this.candidatoForm.valid) {
      console.log(this.candidatoForm.value);
    } else {
      console.log('Formulario no válido');
    }
  }
}
