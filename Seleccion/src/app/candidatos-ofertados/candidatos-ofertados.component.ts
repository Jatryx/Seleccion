import { CommonModule } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { OfertasService } from '../services/serviceOfertas/ofertas.service';
import { HttpClientModule } from '@angular/common/http';
import { Ofertas } from '../models/modelOfertas/ofertas.model';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { UbicacionService } from '../services/serviceUbicacion/ubicacion.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatToolbarModule } from '@angular/material/toolbar';
import { PuestoService } from '../services/servicePuesto/puesto.service';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { map, Observable, startWith } from 'rxjs';
import { EstadoService } from '../services/serviceEstado/estado.service';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { faHome } from '@fortawesome/free-solid-svg-icons';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { CandidatosService } from '../services/serviceCandidatos/candidatos.service';
import { RecruitingService } from '../services/serviceRecruiting/recruiting.service';

@Component({
  selector: 'app-candidatos-ofertados',
  templateUrl: './candidatos-ofertados.component.html',
  styleUrls: ['./candidatos-ofertados.component.css'],
  standalone: true,
  imports: [
    ReactiveFormsModule, 
    CommonModule, 
    HttpClientModule, 
    MatButtonModule, 
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatToolbarModule, 
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatAutocompleteModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatIconModule,
    MatMenuModule
  ],
  providers: [OfertasService, UbicacionService, PuestoService, EstadoService, CandidatosService, RecruitingService]
})
export class CandidatosOfertadosComponent implements OnInit {
  faHome = faHome;
  candidatoForm: FormGroup;
  ofertaLista = new MatTableDataSource<Ofertas>([]);
  provinciaLista: string[];
  perfilesLista: string[];
  estadoLista: string[];
  tecnologiaLista: string[];
  candidatoLista: string[];
  recruitingLista: number[];
  proyectoLista: string[];
  filtroProvincias!: Observable<string[]>;  // Observable para el autocompletado
  filtroPerfiles!: Observable<string[]>;  // Observable para el autocompletado
  filtroTecnologias!: Observable<string[]>; 
  filtroCandidatos!: Observable<string[]>;
  filtroRecruitings!: Observable<number[]>;
  filtroEstados!: Observable<string[]>;
  fecha: Date = new Date();  
  mostrarCampoOtro = false;
  displayedColumns: string[] = [
    'candidato', 
    'telefono',
    'codope', 
    'idPeticion', 
    'proyecto', 
    'cliente', 
    'ubicacion', 
    'perfil', 
    'tecnologia', 
    'estado', 
    'fechaActualizacion', 
    'resumen'
  ];

  constructor(
    private fb: FormBuilder, 
    private ofertaService: OfertasService, 
    private ubicacionService: UbicacionService,
    private puestoService: PuestoService,
    private estadoService: EstadoService,
    private candidatoService: CandidatosService,
    private recruitingService: RecruitingService,
    private dialog: MatDialog
  ) {
    this.candidatoForm = this.fb.group({
      candidato: ['', Validators.required],
      telefono: ['', [Validators.required, Validators.pattern('[0-9]{9}')]],
      idPeticion: ['', [Validators.required, Validators.min(0)]],
      proyecto: ['', Validators.required],
      cliente: ['', Validators.required],
      ubicacion: ['', Validators.required],
      perfil: ['', Validators.required],
      tecnologia: ['', Validators.required],
      experiencia: ['', [Validators.required, Validators.min(0)]],
      salario: ['', [Validators.required, Validators.min(0)]],
      estado: [new Date(), Validators.required],
      fechaActualizacion: ['', Validators.required],
      resumen: ['']
    });
  }

  ngOnInit() {
    this.cargarOfertas();
    this.cargarProvincias();
    this.cargarPerfiles();   
    this.cargarEstados(); 
    this.cargarCandidato();
    this.cargarRecruiting();
  }

  // -------------------------------------------------------------------------------------------------------------

  //      Carga de datos

  // -------------------------------------------------------------------------------------------------------------

  cargarOfertas() {
    this.ofertaService.getOfertas().subscribe(
      data => {
        this.ofertaLista.data = data

        this.tecnologiaLista = data.flatMap(oferta => oferta.tecnologias.split(',').map(t => t.trim()));
        this.tecnologiaLista = Array.from(new Set(this.tecnologiaLista)); // Elimina duplicados
      },
      error => {
        console.error('Error al cargar las ofertas:', error);
      }
    );
  }



  cargarProvincias() {
    this.ubicacionService.getUbicaciones().subscribe(
      data => {
        this.provinciaLista = data.map(provincia => provincia.nombreProvincia);
        // Llama a filtrado aquí luego de cargar provincias
        this.filtradoProvincias();
      },
      error => {
        console.error('Error al cargar las provincias:', error);
      }
    );
  }

  cargarPerfiles() {
    this.puestoService.getPuestos().subscribe(
      data => {
        this.perfilesLista = data.map(puesto => puesto.nombrePuesto);
        // Llama a filtrado aquí después de cargar perfiles
        this.filtradoPerfiles();
      },
      error => {
        console.error('Error al cargar los perfiles:', error);
      }
    );
  }

  cargarEstados() {
    this.estadoService.getEstados().subscribe(
      data => {
        this.estadoLista = data.map(estado => estado.estado);
        // Llama a filtrado aquí después de cargar estados
        this.filtradoEstados();
      },
      error => {
        console.error('Error al cargar los estados:', error);
      }
    );
  }

  cargarCandidato() {
    this.candidatoService.getCandidatos().subscribe(
      data => {
        this.candidatoLista = data.map(candidato => candidato.nombreCandidato);
        // Llama a filtrado aquí después de cargar estados
        this.filtradoCandidato();
      },
      error => {
        console.error('Error al cargar los estados:', error);
      }
    );
  }

  cargarRecruiting() {
    this.recruitingService.getRecruitings().subscribe(
      data => {
        this.recruitingLista = data.map(recruiting => recruiting.idRecruiting);
        // Llama a filtrado aquí después de cargar estados
        this.filtradoRecruiting();
      },
      error => {
        console.error('Error al cargar los estados:', error);
      }
    );
  }

  /* cargarProyectoPorID(id:number){
    this.recruitingService.getRecruitingIdrecruiting(id).subscribe(
      data => {
        this.proyectoLista = 
        data.map(recruiting => recruiting.proyecto);
      }
    )
  } */

  // -------------------------------------------------------------------------------------------------------------

  //      Filtro Selects

  // -------------------------------------------------------------------------------------------------------------

  filtroCandidato(value: string): string[] {
    const filtroCandidato = value.toLowerCase();
    return this.candidatoLista.filter(option => option.toLowerCase().includes(filtroCandidato));
  }

  filtradoCandidato() {
    this.filtroCandidatos = this.candidatoForm.get('candidato')!.valueChanges.pipe(
      startWith(''),
      map(value => this.filtroCandidato(value || ''))
    );
  }

  filtroRecruiting(value: number): number[] {
    return this.recruitingLista.filter(option => option === value);
  }
  
  filtradoRecruiting() {
    this.filtroRecruitings = this.candidatoForm.get('idPeticion')!.valueChanges.pipe(
      startWith(null), 
      map(value => {
        const recruitingValue = Number(value);
        return this.filtroRecruiting(recruitingValue);
      })
    );
  }
  
  filtroProvincia(value: string): string[] {
    const filtroProvincia = value.toLowerCase();
    return this.provinciaLista.filter(option => option.toLowerCase().includes(filtroProvincia));
  }

  filtradoProvincias() {
    this.filtroProvincias = this.candidatoForm.get('ubicacion')!.valueChanges.pipe(
      startWith(''),
      map(value => this.filtroProvincia(value || ''))
    );
  }

  filtroPerfil(value: string): string[] {
    const filtroPerfil = value.toLowerCase();
    return this.perfilesLista.filter(option => option.toLowerCase().includes(filtroPerfil));
  }

  filtradoPerfiles() {
    this.filtroPerfiles = this.candidatoForm.get('perfil')!.valueChanges.pipe(
      startWith(''),
      map(value => this.filtroPerfil(value || ''))
    );
  }

  filtroEstado(value: string): string[] {
    const filtroEstado = value.toLowerCase();
    return this.estadoLista.filter(option => option.toLowerCase().includes(filtroEstado));
  }

  filtradoEstados() {
    this.filtroEstados = this.candidatoForm.get('estado')!.valueChanges.pipe(
      startWith(''),
      map(value => this.filtroEstado(value || ''))
    );
  }

  onSubmit() {
    if (this.candidatoForm.valid) {
      const nuevoCandidato = this.candidatoForm.value;
      this.ofertaLista.data = [...this.ofertaLista.data, nuevoCandidato];
      this.candidatoForm.reset();
    } else {
      console.log('Formulario no válido');
    }
  }

  // -------------------------------------------------------------------------------------------------------------

  //     PopUP observaciones

  // -------------------------------------------------------------------------------------------------------------

  openPopup(prueba: Ofertas): void {
    const dialogRef = this.dialog.open(DialogContent, {
      width: '400px',
      data: {
        observaciones: prueba.observaciones,
        salario: prueba.salario,
        experiencia: prueba.experiencia,
      }
    });
  }
}

// Componente del diálogo
@Component({
  selector: 'dialog-content',
  template: `
<h1 mat-dialog-title class="dialog-title" style="text-align: center; color: #007bff; font-size: 24px; margin-bottom: 20px;">
  Resumen
</h1>
<div mat-dialog-content class="dialog-content" style="padding: 20px; line-height: 1.6; font-size: 16px; color: #333;">
  <p style=" margin-bottom: 10px;">
    <strong>Observaciones:</strong> {{ data.observaciones }}
  </p>
  <p style="margin-bottom: 10px;">
    <strong>Salario:</strong> {{ data.salario}} €
  </p>
  <p style="margin-bottom: 20px;">
    <strong>Experiencia:</strong> {{ data.experiencia }}
  </p>
</div>
<div mat-dialog-actions class="dialog-actions" style="display: flex; justify-content: center; padding: 10px;">
  <button mat-stroked-button color="warn" (click)="onClose()" style="padding: 10px 20px; font-weight: bold;">
    Cerrar
  </button>
</div>
  `,
})
export class DialogContent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private dialogRef: MatDialogRef<DialogContent>) {}

  onClose(): void {
    this.dialogRef.close();
  }
}