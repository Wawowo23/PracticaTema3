package models;

public class Producto {
    // Atributos
    private String nombre;
    private float precio;
    private int cantidad;
    private String id;
    private int productosRegistrados;

    // Constructor

    public Producto(String nombre, float precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.id = generaId();
        productosRegistrados++;
    }

    public Producto(Producto producto){
        nombre = producto.nombre;
        precio = producto.precio;
        cantidad = producto.cantidad;
        id = producto.id;
    }



    // Getters Y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Metodos

    private String generaId () {
        String codigo = "";
        codigo += String.valueOf(productosRegistrados);
        while (codigo.length() < 8) {
            codigo = "0" + codigo;
        }
        return codigo;
    }

    public boolean salidaProducto (int cantidad) {
        if (cantidad > this.cantidad) return false;
        this.cantidad -= cantidad;
        return true;
    }

    public String pintaProducto () {
        return "Id del producto: " + id + "\n" +
                "Nombre: " + nombre + "\n" +
                "Precio: " + precio + "€" + "\n" +
                "Cantidad restante: " + cantidad + "\n";
    }

    public String pintaProductoCatalogo () {
        return nombre + ": " + precio + "E. " + ((cantidad < 1) ? "Agotado" : cantidad + "restantes");
    }

    public String pintaProductoPedido() {
        return "\t - " + nombre + "(" + precio + "E)\n";
    }
}
