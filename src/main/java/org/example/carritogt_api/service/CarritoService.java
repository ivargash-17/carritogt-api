package org.example.carritogt_api.service;

import org.example.carritogt_api.dto.AgregarCarritoRequest;
import org.example.carritogt_api.dto.CarritoItemDTO;
import org.example.carritogt_api.entity.Producto;
import org.example.carritogt_api.entity.ShoppingCart;
import org.example.carritogt_api.exception.RecursoNoEncontradoException;
import org.example.carritogt_api.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/** Lógica de negocio del carrito: ver, agregar y eliminar elementos. */
@Service
public class CarritoService {

    private final ShoppingCartRepository carritoRepository;
    private final ProductoService productoService;

    public CarritoService(ShoppingCartRepository carritoRepository, ProductoService productoService) {
        this.carritoRepository = carritoRepository;
        this.productoService = productoService;
    }

    /** RF-04: contenido actual del carrito de un usuario, con datos del producto. */
    public List<CarritoItemDTO> obtenerCarrito(String codUsuario) {
        List<CarritoItemDTO> resultado = new ArrayList<>();
        for (ShoppingCart item : carritoRepository.findByCodUsuario(codUsuario)) {
            Producto p = productoService.buscarPorId(item.getIdProducto());
            resultado.add(new CarritoItemDTO(item.getIdShoppingCart(), p.getIdProducto(),
                    p.getNombre(), p.getImagen(), p.getPrecio(), item.getCantidad()));
        }
        return resultado;
    }

    /** RF-03: agrega un producto; si ya estaba en el carrito, suma la cantidad. */
    @Transactional
    public ShoppingCart agregar(AgregarCarritoRequest req) {
        if (req.getCodUsuario() == null || req.getCodUsuario().isBlank()) {
            throw new IllegalArgumentException("codUsuario es obligatorio");
        }
        if (req.getIdProducto() == null) {
            throw new IllegalArgumentException("idProducto es obligatorio");
        }
        int cantidad = (req.getCantidad() == null) ? 1 : req.getCantidad();
        if (cantidad < 1) {
            throw new IllegalArgumentException("La cantidad debe ser mayor o igual a 1");
        }

        // Verifica que el producto exista (lanza 404 si no)
        productoService.buscarPorId(req.getIdProducto());

        ShoppingCart item = carritoRepository
                .findByCodUsuarioAndIdProducto(req.getCodUsuario(), req.getIdProducto())
                .orElse(null);

        if (item == null) {
            item = new ShoppingCart();
            item.setCodUsuario(req.getCodUsuario());
            item.setIdProducto(req.getIdProducto());
            item.setCantidad(cantidad);
        } else {
            item.setCantidad(item.getCantidad() + cantidad);
        }
        return carritoRepository.save(item);
    }

    /** RF-05: elimina un renglón del carrito. */
    @Transactional
    public void eliminar(Integer idShoppingCart) {
        if (!carritoRepository.existsById(idShoppingCart)) {
            throw new RecursoNoEncontradoException("No existe el elemento del carrito con id " + idShoppingCart);
        }
        carritoRepository.deleteById(idShoppingCart);
    }
}
