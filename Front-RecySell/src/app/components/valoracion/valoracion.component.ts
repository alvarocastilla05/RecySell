import { Component, OnInit } from '@angular/core';
import { ProductoService } from '../../service/producto-service.service';
import { firstValueFrom } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-valoracion',
  templateUrl: './valoracion.component.html',
  styleUrl: './valoracion.component.css'
})
export class ValoracionComponent implements OnInit {
  todosLosProductos: any[] = [];
  productosFiltrados: any[] = [];
  productosPagina: any[] = [];
  cargando = true;
  page = 0;
  size = 10;
  totalPages = 1;

  filtro: 'todos' | 'valorados' | 'noValorados' = 'todos';

  // Modal valoración
  productoAValorar: any = null;
  mostrarModalValoracion: boolean = false;
  puntuacion: number = 5;
  comentario: string = '';
  mensaje: string = '';
  error: string = '';
  editandoValoracion: boolean = false;

  trabajadorId: string = '';

  constructor(
    private productoService: ProductoService,
    private http: HttpClient
  ) {}

  getUserIdFromToken(): string | null {
    const token = localStorage.getItem('token');
    if (!token) return null;
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      // Ajusta el campo según tu backend, normalmente suele ser 'id', 'userId', 'sub', etc.
      return payload.id || payload.userId || payload.sub || null;
    } catch {
      return null;
    }
  }

  async ngOnInit() {
    this.cargando = true;
    this.trabajadorId = (this.getUserIdFromToken() || '').trim();
    console.log('ID del trabajador logueado:', this.trabajadorId);

    this.productoService.getProductos({ page: 0, size: 1000 }).subscribe(async resp => {
      const productos = resp.content;
      const detalles = await Promise.all(
        productos.map(prod =>
          firstValueFrom(this.productoService.getProductoById(String(prod.id)))
            .then(det => ({ ...det, id: prod.id }))
            .catch(() => null)
        )
      );
      // Depuración: muestra los IDs de trabajador en las valoraciones
      detalles.forEach(det => {
        if (det?.valora?.trabajador) {
          const idProd = String(det.valora.trabajador.id || '').trim();
          console.log(
            'Producto:', det.nombre,
            '| ID trabajador en producto:', idProd,
            '| Coincide:', idProd === this.trabajadorId
          );
        }
      });
      // Filtra: solo productos no valorados o valorados por el trabajador logueado (por ID)
      this.todosLosProductos = detalles.filter(det =>
        det && (
          !det.valora ||
          (det.valora.trabajador &&
            String(det.valora.trabajador.id || '').trim() === this.trabajadorId)
        )
      );
      this.aplicarFiltro();
      this.cargando = false;
    });
  }

  aplicarFiltro() {
    if (this.filtro === 'valorados') {
      this.productosFiltrados = this.todosLosProductos.filter(p => p.valora);
    } else if (this.filtro === 'noValorados') {
      this.productosFiltrados = this.todosLosProductos.filter(p => !p.valora);
    } else {
      this.productosFiltrados = this.todosLosProductos;
    }
    this.totalPages = Math.ceil(this.productosFiltrados.length / this.size);
    this.page = 0;
    this.setPagina();
  }

  setPagina() {
    const start = this.page * this.size;
    this.productosPagina = this.productosFiltrados.slice(start, start + this.size);
  }

  anteriorPagina() {
    if (this.page > 0) {
      this.page--;
      this.setPagina();
    }
  }

  siguientePagina() {
    if (this.page < this.totalPages - 1) {
      this.page++;
      this.setPagina();
    }
  }

  abrirModalValoracion(producto: any) {
    this.productoAValorar = producto;
    this.mensaje = '';
    this.error = '';
    if (producto.valora) {
      this.editandoValoracion = true;
      this.puntuacion = producto.valora.puntacion;
      this.comentario = producto.valora.comentario;
    } else {
      this.editandoValoracion = false;
      this.puntuacion = 5;
      this.comentario = '';
    }
    this.mostrarModalValoracion = true;
  }

  cerrarModalValoracion() {
    this.mostrarModalValoracion = false;
  }

  valorarProductoModal() {
    this.mensaje = '';
    this.error = '';
    const body = {
      productoId: this.productoAValorar.id,
      puntuacion: this.puntuacion,
      comentario: this.comentario
    };
    if (this.editandoValoracion) {
      // PUT con id de producto
      this.http.put(`http://localhost:8080/valora/${this.productoAValorar.id}`, body).subscribe({
        next: () => {
          this.mensaje = '¡Valoración editada correctamente!';
          setTimeout(() => {
            this.cerrarModalValoracion();
            this.ngOnInit();
          }, 1200);
        },
        error: (err) => {
          if (
            err.status === 404 ||
            err.status === 403
          ) {
            this.error = 'No puedes editar esta valoración porque no es tuya.';
          } else {
            this.error = 'Error al editar la valoración.';
          }
        }
      });
    } else {
      // POST nueva valoración
      this.http.post('http://localhost:8080/valora', body).subscribe({
        next: () => {
          this.mensaje = '¡Producto valorado correctamente!';
          setTimeout(() => {
            this.cerrarModalValoracion();
            this.ngOnInit();
          }, 1200);
        },
        error: () => {
          this.error = 'Error al valorar el producto.';
        }
      });
    }
  }
}