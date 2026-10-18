package org.example.carritogt_api.controller;

import org.example.carritogt_api.entity.Producto;
import org.example.carritogt_api.service.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** Endpoints REST del catálogo. */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /** GET /api/productos -> lista todos los productos (RF-01, RF-02, RF-07) */
    @GetMapping
    public List<Producto> listar() {
        return productoService.listar();
    }

    /** GET /api/productos/{id} -> un producto */
    @GetMapping("/{id}")
    public Producto obtener(@PathVariable Integer id) {
        return productoService.buscarPorId(id);
    }
}
