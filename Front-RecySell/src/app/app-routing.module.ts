import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InicioSesionComponent } from './components/inicio-sesion/inicio-sesion.component';
import { HomeComponent } from './components/home/home.component';
import { RegistroComponent } from './components/registro/registro.component';
import { VerificacionComponent } from './components/verificacion/verificacion.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: InicioSesionComponent },
  { path: 'home', component: HomeComponent},
  { path: 'registro', component: RegistroComponent },
  { path: 'registro-trabajador', component: RegistroComponent },
  { path: 'verificacion', component: VerificacionComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
