# RecySell

RecySell es una plataforma de ventas de dispositivos reacondicionados, donde los usuarios pueden comprar y vender productos de segunda mano, así como donar productos. Este proyecto utiliza tecnologías como Spring Boot, PostgreSQL, y Docker para crear una solución eficiente y escalable.

Este es una demo de la aplicación final. En esta versión solo podrás realizar algunas funcionalidades básicas que engloban la plataforma de ventas de productos reacondicionados, como:

- Poner productos a la venta
- Donar productos
- Añadir productos a la lista de favoritos

La versión final de la aplicación contará con más funcionalidades y mejoras, pero este demo te permite interactuar con las funcionalidades esenciales de la plataforma.


## Configuración de Variables de Entorno

Antes de ejecutar el proyecto, asegúrate de configurar las siguientes variables de entorno:

- `SECRET`: Una clave secreta utilizada en la configuración de seguridad.
- `USERNAME`: Cuenta de email que se va a encargar de enviar los correo de verificación
- `PASSWORD`: Contraseña asociada al gmail.


### Ejemplo para configurar en el terminal (PowerShell):

```bash
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


