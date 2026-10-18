package org.example.carritogt_api.dto;

/** Datos que el frontend envía para agregar un producto al carrito. */
public class AgregarCarritoRequest {

    private String codUsuario;
    private Integer idProducto;
    private Integer cantidad;

    public AgregarCarritoRequest() {
    }

    public String getCodUsuario() { return codUsuario; }
    public void setCodUsuario(String codUsuario) { this.codUsuario = codUsuario; }

    public Integer getIdProducto() { return idProducto; }
    public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}
