import { Routes } from '@angular/router';
import { CandidatosOfertadosComponent } from './candidatos-ofertados/candidatos-ofertados.component';

export const routes: Routes = [
  { path: 'candidatos-ofertados', component: CandidatosOfertadosComponent },
  { path: '', redirectTo: '/candidatos-ofertados', pathMatch: 'full' }
];