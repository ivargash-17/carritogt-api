package org.example.carritogt_api.exception;

/** Se lanza cuando un producto o un elemento del carrito no existe (HTTP 404). */
public class RecursoNoEncontradoException extends RuntimeException {
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
