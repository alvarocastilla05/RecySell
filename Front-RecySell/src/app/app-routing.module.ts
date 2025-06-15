import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InicioSesionComponent } from './components/inicio-sesion/inicio-sesion.component';
import { HomeComponent } from './components/home/home.component';
import { RegistroComponent } from './components/registro/registro.component';
import { VerificacionComponent } from './components/verificacion/verificacion.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { HacerDonacionComponent } from './components/hacer-donacion/hacer-donacion.component';
import { ReciclarComponent } from './components/reciclar/reciclar.component';
import { SobreNosotrosComponent } from './components/sobre-nosotros/sobre-nosotros.component';
import { MiPerfilComponent } from './components/mi-perfil/mi-perfil.component';
import { AniadirProductoComponent } from './components/aniadir-producto/aniadir-producto.component';
import { CarritoComponent } from './components/carrito/carrito.component';
import { CrearCompraComponent } from './components/crear-compra/crear-compra.component';
import { HomeTrabajadorComponent } from './components/home-trabajador/home-trabajador.component';
import { DonacionTrabajadorComponent } from './components/donacion-trabajador/donacion-trabajador.component';
import { CategoriasTrabajadorComponent } from './components/categorias-trabajador/categorias-trabajador.component';
import { OrganizacionTrabajadorComponent } from './components/organizacion-trabajador/organizacion-trabajador.component';
import { UsuariosTrabajadorComponent } from './components/usuarios-trabajador/usuarios-trabajador.component';
import { ListadoComprasComponent } from './components/listado-compras/listado-compras.component';
import { ValoracionComponent } from './components/valoracion/valoracion.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'login', component: InicioSesionComponent },
  { path: 'home', component: HomeComponent},
  { path: 'registro', component: RegistroComponent },
  { path: 'registro-trabajador', component: RegistroComponent },
  { path: 'verificacion', component: VerificacionComponent },
  { path: 'producto/:id', component: ProductDetailsComponent},
  { path: 'productos-venta', component: ProductListComponent},
  { path: 'vender', component: AniadirProductoComponent },
  { path: 'editar-producto/:id', component: AniadirProductoComponent },
  { path: 'donar', component: HacerDonacionComponent},
  { path: 'reciclar', component: ReciclarComponent },
  { path: 'sobre-nosotros', component: SobreNosotrosComponent} ,
  { path: 'mi-perfil', component: MiPerfilComponent },
  { path: 'carrito/:id', component: CarritoComponent },
  { path: 'nueva-compra', component: CrearCompraComponent },
  { path: 'editar-compra/:id', component: CrearCompraComponent },
  {
    path: 'trabajador',
    component: HomeTrabajadorComponent,
    children: [
      { path: 'donaciones', component: DonacionTrabajadorComponent },
      { path: 'compras', component: ListadoComprasComponent },
      { path: 'categorias', component: CategoriasTrabajadorComponent },
      { path: 'organizaciones', component: OrganizacionTrabajadorComponent },
      { path: 'usuario', component: UsuariosTrabajadorComponent },
      { path: 'valoracion', component: ValoracionComponent },
      { path: '', redirectTo: 'donaciones', pathMatch: 'full' }
    ]
  },
  { path: '**', redirectTo: 'home' } // Redirige a home para rutas no encontradas
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
