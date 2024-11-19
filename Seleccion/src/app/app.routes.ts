// app-routing.module.ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CandidatosOfertadosComponent } from './candidatos-ofertados/candidatos-ofertados.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  { 
    path: 'candidatos-ofertados', 
    component: CandidatosOfertadosComponent,
    canActivate: [AuthGuard]  // Añade el guard aquí
  },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],  // Configuramos las rutas
  exports: [RouterModule]  // Exportamos el RouterModule para que pueda ser utilizado en otros módulos
})
export class AppRoutingModule { }
