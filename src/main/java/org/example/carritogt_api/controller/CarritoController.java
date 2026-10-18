package org.example.carritogt_api.controller;

import org.example.carritogt_api.dto.AgregarCarritoRequest;
import org.example.carritogt_api.dto.CarritoItemDTO;
import org.example.carritogt_api.entity.ShoppingCart;
import org.example.carritogt_api.service.CarritoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/** Endpoints REST del carrito. */
@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    /** GET /api/carrito/{codUsuario} -> contenido del carrito (RF-04) */
    @GetMapping("/{codUsuario}")
    public List<CarritoItemDTO> ver(@PathVariable String codUsuario) {
        return carritoService.obtenerCarrito(codUsuario);
    }

    /** POST /api/carrito -> agrega un producto (RF-03). Responde 201 Created. */
    @PostMapping
    public ResponseEntity<ShoppingCart> agregar(@RequestBody AgregarCarritoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carritoService.agregar(request));
    }

    /** DELETE /api/carrito/{idShoppingCart} -> elimina un renglón (RF-05) */
    @DeleteMapping("/{idShoppingCart}")
    public Map<String, String> eliminar(@PathVariable Integer idShoppingCart) {
        carritoService.eliminar(idShoppingCart);
        return Map.of("mensaje", "Producto eliminado del carrito");
    }
}
