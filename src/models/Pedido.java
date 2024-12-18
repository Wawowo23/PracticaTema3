package models;

import java.time.LocalDate;

public class Pedido {
    // Atributos
    private String id;
    private float precioTotal;
    private String estado;
    private String comentario;
    private LocalDate fechaPedido;
    private Producto producto1;
    private Producto producto2;
    private Producto producto3;

    private static int pedidosCreados = 0;

    //Constructor


    public Pedido() {
        id = generaIdPedido();
        precioTotal = calculaPrecioPedido();
        estado = "";
        comentario = "";
        fechaPedido = LocalDate.now();
        producto1 = null;
        producto2 = null;
        producto3 = null;
        pedidosCreados++;
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Producto getProducto1() {
        return producto1;
    }

    public void setProducto1(Producto producto1) {
        this.producto1 = producto1;
    }

    public Producto getProducto2() {
        return producto2;
    }

    public void setProducto2(Producto producto2) {
        this.producto2 = producto2;
    }

    public Producto getProducto3() {
        return producto3;
    }

    public void setProducto3(Producto producto3) {
        this.producto3 = producto3;
    }

    public static int getPedidosCreados() {
        return pedidosCreados;
    }

    public static void setPedidosCreados(int pedidosCreados) {
        Pedido.pedidosCreados = pedidosCreados;
    }

    // Metodos

    private String generaIdPedido(){
        String salida = "";
        int codigo = 0;
        codigo +=  LocalDate.now().getMonthValue();
        codigo += getPedidosCreados() * 100;
        codigo += LocalDate.now().getDayOfYear() * 10000;
        codigo += (codigo / (getPedidosCreados() + 1));
        while (codigo > 99999999) {
            codigo /= 10;
        }
        salida = String.valueOf(codigo);
        return salida;
    }

    private float calculaPrecioPedido() {
        float precioFinal = 0;
        precioFinal += ((producto1 == null) ? 0:producto1.getPrecio());
        precioFinal += ((producto2 == null) ? 0:producto2.getPrecio());
        precioFinal += ((producto3 == null) ? 0:producto3.getPrecio());
        return precioFinal;
    }

    private String calculaFechaEstimada() {
        String fechaEstimada = String.valueOf(LocalDate.now().plusDays(5));
        return fechaEstimada;
    }

    public String pintaPedidoParaCliente() {
        String salida = "";
        salida += "Fecha del pedido: " + fechaPedido + "\n";
        salida += "Fecha de entrega estimada: " + calculaFechaEstimada() + "\n";
        salida += "Comentario del pedido: " + comentario + "\n";
        salida += ((producto1 == null)? "": producto1.pintaProductoPedido());
        salida += ((producto2 == null)? "": producto2.pintaProductoPedido());
        salida += ((producto3 == null)? "": producto3.pintaProductoPedido());
        salida += "Total del pedido: " + precioTotal + "E";
        return salida;
    }

    public boolean insertaProducto(Producto producto) {
        if (pedidoLleno()) return false;
        if (producto1 == null) {
                producto1 = producto;
                return true;
        }
        if (producto2 == null) {
            producto2 = producto;
            return true;
        }
        producto3 = producto;
        return true;

    }

    public boolean pedidoLleno () {
        if (producto1 != null && producto2 != null && producto3 != null) return true;
        return false;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id='" + id + '\'' +
                ", precioTotal=" + precioTotal +
                ", estado='" + estado + '\'' +
                ", comentario='" + comentario + '\'' +
                ", fechaPedido=" + fechaPedido +
                ", producto1=" + producto1 +
                ", producto2=" + producto2 +
                ", producto3=" + producto3 +
                '}';
    }
}
