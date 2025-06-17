# RecySell

RecySell es una plataforma web para la compra, venta y donación de dispositivos reacondicionados. Permite a los usuarios publicar productos, donar dispositivos, gestionar compras y acceder a funcionalidades avanzadas según su rol (cliente, trabajador o administrador). El proyecto está construido con **Angular** (frontend), **Spring Boot** (backend), **PostgreSQL** y **Docker** para facilitar el despliegue y la escalabilidad.

---

## Características principales

- **Compra y venta de productos reacondicionados**
- **Donación de dispositivos**
- **Gestión de usuarios por roles:** cliente, trabajador y administrador
- **Panel de trabajador para gestión avanzada**
- **Sistema de favoritos**
- **Autenticación y verificación por email**
- **Gestión de categorías y organizaciones**
- **Valoración de productos**
- **Carrito de compra**
- **Interfaz moderna y responsive**

---

## Versión actual

Esta versión corresponde a la **versión final** de RecySell, no una demo. Incluye todas las funcionalidades principales de la plataforma:

- Publicar productos a la venta
- Donar productos
- Añadir productos a favoritos
- Comprar productos y gestionar el carrito
- Panel de trabajador para gestión de donaciones, compras, categorías, organizaciones y usuarios
- Registro y edición de trabajadores (solo admin)
- Activación/desactivación de cuentas
- Valoración de productos
- Seguridad por roles en las rutas
- Gestión de imágenes de productos

---

## Configuración de Variables de Entorno

Antes de ejecutar el proyecto, asegúrate de configurar las siguientes variables de entorno para el backend:

- `SECRET`: Clave secreta para la seguridad JWT.
- `USERNAME`: Cuenta de email que enviará los correos de verificación.
- `PASSWORD`: Contraseña de la cuenta de email.

**Ejemplo en PowerShell:**
```powershell
$env:SECRET="mysecretkey"
$env:USERNAME="myusername"
$env:PASSWORD="mypassword"
```

### Ejecución
Después de los pasos anteriores, para ejecutar el proyecto debes hacer:
```bash
docker-compose up -d
```

### Prueba
En la colección de Postman hay una carpeta User, dónde podrás encontrar un inicio de sesión de cada role para poder probar posteriormente las distintas funciones. Además, si registras un trabajador o un cliente, para activar la cuenta debes de acceder a la request de la carpeta User.

Además, a la hora de añadir un Producto, hay adjuntado un archivo.json y una imagen. Para que esta no de error tienes que desadjuntarlo y volver a adjuntar otros archivos que tengan la ruta de tu dispositivo. El archivo .json de ejemplo esta en el repositorio adjuntado, la imagen puedes coger la que desees.


