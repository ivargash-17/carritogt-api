package org.example.carritogt_api.dto;

/** Un renglón del carrito ya combinado con los datos del producto, listo para mostrarse. */
public class CarritoItemDTO {

    private Integer idShoppingCart;
    private Integer idProducto;
    private String nombre;
    private String imagen;
    private Double precio;
    private Integer cantidad;
    private Double subtotal;

    public CarritoItemDTO() {
    }

    public CarritoItemDTO(Integer idShoppingCart, Integer idProducto, String nombre,
                          String imagen, Double precio, Integer cantidad) {
        this.idShoppingCart = idShoppingCart;
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
        this.cantidad = cantidad;
        this.subtotal = (precio == null ? 0.0 : precio) * (cantidad == null ? 0 : cantidad);
    }

    public Integer getIdShoppingCart() { return idShoppingCart; }
    public Integer getIdProducto() { return idProducto; }
    public String getNombre() { return nombre; }
    public String getImagen() { return imagen; }
    public Double getPrecio() { return precio; }
    public Integer getCantidad() { return cantidad; }
    public Double getSubtotal() { return subtotal; }
}
