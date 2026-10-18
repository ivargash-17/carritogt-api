package org.example.carritogt_api.repository;

import org.example.carritogt_api.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/** Acceso a datos de la tabla "shopping_cart". Spring genera las consultas a partir del nombre del método. */
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    List<ShoppingCart> findByCodUsuario(String codUsuario);

    Optional<ShoppingCart> findByCodUsuarioAndIdProducto(String codUsuario, Integer idProducto);
}
