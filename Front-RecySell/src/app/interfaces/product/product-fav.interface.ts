export interface ProductoFavorito {
  id: number;
  nombre: string;
  descripcion: string;
  precio: number;
  imagenes: string[];
  estado: string;
  fechaRegistro: string;
  categoria: {
    nombre: string;
    imagen: string | null;
  };
  clienteVendedor: {
    username: string;
    email: string;
    nombre: string;
    apellidos: string;
  };
}