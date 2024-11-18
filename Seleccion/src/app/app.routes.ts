// app-routing.module.ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CandidatosOfertadosComponent } from './candidatos-ofertados/candidatos-ofertados.component';
import { LoginComponent } from './login/login.component';

// Definimos las rutas
const routes: Routes = [
  { path: 'candidatos-ofertados', component: CandidatosOfertadosComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],  // Configuramos las rutas
  exports: [RouterModule]  // Exportamos el RouterModule para que pueda ser utilizado en otros módulos
})
export class AppRoutingModule { }
