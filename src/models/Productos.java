package models;

public class Productos {
    // Atributos
    private String nombre;
    private float precio;
    private int cantidad;
    private int id;

    // Constructor

    public Productos(String nombre, float precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.id = generaId();
    }

    public Productos(Productos producto){
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Metodos

    private int generaId () {
        int random;
        random = (int) ((Math.random() * 40000) + 10000);
        return random ;
    }

    public String pintaProducto () {
        return "Id del producto: " + id + "\n" +
                "Nombre: " + nombre + "\n" +
                "Precio: " + precio + "€" + "\n" +
                "Cantidad restante: " + cantidad + "\n";
    }
}
