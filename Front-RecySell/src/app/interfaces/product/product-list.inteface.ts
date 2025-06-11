export interface ProductoListResponse {
    content: Producto[]
    pageable: Pageable
    last: boolean
    totalPages: number
    totalElements: number
    first: boolean
    size: number
    number: number
    sort: Sort2
    numberOfElements: number
    empty: boolean
}

export interface Producto {
    id: number
    nombre: string
    descripcion: string
    precio: number
    imagenes: string[]
    estado: string
    fechaRegistro: string
    categoria: Categoria
    clienteVendedor: ClienteVendedor
}

export interface Categoria {
    nombre: string
    imagen: any
}

export interface ClienteVendedor {
    username: string
    email: string
    nombre: string
    apellidos: string
}

export interface Pageable {
    pageNumber: number
    pageSize: number
    sort: Sort
    offset: number
    paged: boolean
    unpaged: boolean
}

export interface Sort {
    empty: boolean
    sorted: boolean
    unsorted: boolean
}

export interface Sort2 {
    empty: boolean
    sorted: boolean
    unsorted: boolean
}
