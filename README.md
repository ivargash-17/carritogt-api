# CarritoGT

Migración del carrito de compra PHP (repositorio base `mvelasquezo/carrito-compra-basico`) a una API REST con Spring Boot, con un frontend en HTML y JavaScript que la consume.

## Tecnologías
- Java 21, Spring Boot 4.0.8, Maven (wrapper), Spring Data JPA, Hibernate
- MariaDB 10.11
- Frontend: HTML y JavaScript (`fetch`)
- Versión PHP 7 orientada a objetos con Twig, en la carpeta `php/`

## Estructura del repositorio
- `src/` API Spring Boot: `controller`, `service`, `repository`, `entity`, `dto`, `exception`, `config`
- `src/main/resources/static/` frontend e imágenes de productos
- `database/` script SQL con las dos tablas (`producto`, `shopping_cart`)
- `php/` versión PHP original

## Requisitos
- Java 21, Maven wrapper incluido (`./mvnw`)
- MariaDB en funcionamiento
- Para la versión PHP: Apache, PHP 7 y MariaDB

## Instalación y configuración
1. Crear la base `cc` e importar el script (si el script ya crea la base, omitir la creación):
   ```bash
   mariadb -u carrito_app -p -e "CREATE DATABASE IF NOT EXISTS cc;"
   mariadb -u carrito_app -p cc < database/cc.sql
   ```
2. Revisar `src/main/resources/application.properties`: base `cc`, usuario `carrito_app`.
3. La contraseña no está en el código; se pasa con la variable de entorno `DB_PASSWORD`.

## Cómo iniciar la API
```bash
DB_PASSWORD='su_contraseña' ./mvnw spring-boot:run
```
Abrir http://localhost:8080/ en el navegador.

## Archivos que deben modificarse en otro equipo
- `application.properties`: nombre de la base, usuario y puerto
- `index.html`: constantes `API` y `USUARIO`

## Endpoints principales
| Método | URL | Descripción |
|--------|-----|-------------|
| GET | /api/productos | Lista los productos |
| GET | /api/productos/{id} | Un producto (404 si no existe) |
| GET | /api/carrito/{codUsuario} | Carrito con subtotales |
| POST | /api/carrito | Agrega un producto: `{"codUsuario":"usuario1","idProducto":1,"cantidad":1}` |
| DELETE | /api/carrito/{idShoppingCart} | Elimina un renglón (404 si no existe) |

Los errores devuelven JSON: `{"status":404,"error":"Not Found","mensaje":"..."}`.

## Pruebas rápidas
```bash
curl http://localhost:8080/api/productos
curl -X POST http://localhost:8080/api/carrito -H "Content-Type: application/json" -d '{"codUsuario":"usuario1","idProducto":1,"cantidad":1}'
curl http://localhost:8080/api/carrito/usuario1
curl -i -X DELETE http://localhost:8080/api/carrito/9999
```

## Problemas conocidos
- El frontend usa un usuario fijo (`usuario1`); no hay autenticación.
- La consola muestra un aviso de `favicon.ico`; no afecta el funcionamiento.
- La consola imprime la URL JDBC con las credenciales; no mostrarla en capturas públicas.
- El `idProducto` del carrito es un entero; no se declaró una clave foránea JPA.
