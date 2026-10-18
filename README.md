# CarritoGT API (Spring Boot)

Migración del carrito de compra PHP (mvelasquezo/carrito-compra-basico) a una API REST con Spring Boot,
arquitectura monolítica por capas: Controller -> Service -> Repository -> Entity.

## Requisitos
- Java 17+ (o la versión definida en tu pom.xml)
- Maven (se usa `./mvnw`)
- MariaDB/MySQL con la base del catedrático importada (tablas `producto` y `shopping_cart`)
- Dependencias en pom.xml: spring-boot-starter-web, spring-boot-starter-data-jpa, mariadb-java-client

## Configuración
Editar `src/main/resources/application.properties`: nombre de la base, usuario y contraseña.

## Ejecución
```bash
./mvnw spring-boot:run
```
Abrir http://localhost:8080/ (el frontend está en `src/main/resources/static/index.html`).

## Endpoints
| Método | URL | Descripción |
|--------|-----|-------------|
| GET | /api/productos | Lista los productos |
| GET | /api/productos/{id} | Un producto (404 si no existe) |
| GET | /api/carrito/{codUsuario} | Contenido del carrito con subtotales |
| POST | /api/carrito | Agrega producto. Body: `{"codUsuario":"usuario1","idProducto":1,"cantidad":1}` |
| DELETE | /api/carrito/{idShoppingCart} | Elimina un renglón (404 si no existe) |

## Pruebas rápidas con cURL
```bash
curl http://localhost:8080/api/productos
curl -X POST http://localhost:8080/api/carrito -H "Content-Type: application/json" \
     -d '{"codUsuario":"usuario1","idProducto":1,"cantidad":1}'
curl http://localhost:8080/api/carrito/usuario1
curl -X DELETE http://localhost:8080/api/carrito/1
```

## Errores
Respuestas JSON: `{"status":404,"error":"Not Found","mensaje":"..."}`

## Estructura
```
entity/      Producto, ShoppingCart
repository/  ProductoRepository, ShoppingCartRepository
service/     ProductoService, CarritoService
controller/  ProductoController, CarritoController
dto/         AgregarCarritoRequest, CarritoItemDTO
exception/   RecursoNoEncontradoException, GlobalExceptionHandler
config/      CorsConfig
```
