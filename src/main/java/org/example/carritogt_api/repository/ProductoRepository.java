package org.example.carritogt_api.repository;

import org.example.carritogt_api.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Acceso a datos de la tabla "producto". JpaRepository ya trae findAll, findById, etc. */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
