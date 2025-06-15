import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MaterialModule } from '../modules/material.module';
import { CardProductComponent } from './components/card-product/card-product.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { HomeComponent } from './components/home/home.component';
import { SobreNosotrosComponent } from './components/sobre-nosotros/sobre-nosotros.component';
import { ReciclarComponent } from './components/reciclar/reciclar.component';
import { AniadirProductoComponent } from './components/aniadir-producto/aniadir-producto.component';
import { CarritoComponent } from './components/carrito/carrito.component';
import { InicioSesionComponent } from './components/inicio-sesion/inicio-sesion.component';
import { RegistroComponent } from './components/registro/registro.component';
import { MiPerfilComponent } from './components/mi-perfil/mi-perfil.component';
import { HacerDonacionComponent } from './components/hacer-donacion/hacer-donacion.component';
import { VerificacionComponent } from './components/verificacion/verificacion.component';
import { ListadoComprasComponent } from './components/listado-compras/listado-compras.component';
import { ListadoDonacionesComponent } from './components/listado-donaciones/listado-donaciones.component';
import { ValoracionComponent } from './components/valoracion/valoracion.component';
import { MenuNavComponent } from './shared/menu-nav/menu-nav.component';
import { LoadingComponent } from './shared/loading/loading.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { CrearCompraComponent } from './components/crear-compra/crear-compra.component';
import { HomeTrabajadorComponent } from './components/home-trabajador/home-trabajador.component';
import { DonacionTrabajadorComponent } from './components/donacion-trabajador/donacion-trabajador.component';
import { ComprasTrabajadorComponent } from './components/compras-trabajador/compras-trabajador.component';
import { CategoriasTrabajadorComponent } from './components/categorias-trabajador/categorias-trabajador.component';
import { OrganizacionTrabajadorComponent } from './components/organizacion-trabajador/organizacion-trabajador.component';
import { UsuariosTrabajadorComponent } from './components/usuarios-trabajador/usuarios-trabajador.component';

@NgModule({
  declarations: [
    AppComponent,
    CardProductComponent,
    ProductDetailsComponent,
    ProductListComponent,
    HomeComponent,
    SobreNosotrosComponent,
    ReciclarComponent,
    AniadirProductoComponent,
    CarritoComponent,
    InicioSesionComponent,
    RegistroComponent,
    MiPerfilComponent,
    HacerDonacionComponent,
    VerificacionComponent,
    ListadoComprasComponent,
    ListadoDonacionesComponent,
    ValoracionComponent,
    MenuNavComponent,
    LoadingComponent,
    CrearCompraComponent,
    HomeTrabajadorComponent,
    DonacionTrabajadorComponent,
    ComprasTrabajadorComponent,
    CategoriasTrabajadorComponent,
    OrganizacionTrabajadorComponent,
    UsuariosTrabajadorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,

  ],
  providers: [
    provideAnimationsAsync(),
    provideHttpClient(withInterceptors([AuthInterceptor]))
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
