package org.example.carritogt_api.service;

import org.example.carritogt_api.entity.Producto;
import org.example.carritogt_api.exception.RecursoNoEncontradoException;
import org.example.carritogt_api.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/** Lógica de negocio relacionada con el catálogo de productos. */
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    public Producto buscarPorId(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el producto con id " + id));
    }
}
