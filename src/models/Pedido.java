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
        precioTotal = 0;
        estado = "Preparando";
        comentario = "";
        fechaPedido = LocalDate.now();
        producto1 = null;
        producto2 = null;
        producto3 = null;
        pedidosCreados++;
    }

    // Constructor copia
    public Pedido (Pedido pedido) {
        this.id = pedido.id;
        this.precioTotal = pedido.precioTotal;
        this.estado = pedido.estado;
        this.comentario = pedido.comentario;
        this.fechaPedido = pedido.fechaPedido;
        this.producto1 = pedido.producto1;
        this.producto2 = pedido.producto2;
        this.producto3 = pedido.producto3;
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
        salida += "Total del pedido: " + precioTotal + "E\n";
        return salida;
    }

    public String pintaPedidoParaTrabajadorAdmin() {
        String salida = "";
        salida += "==========  Pedido: " + id + "  ==========" + "\n";
        salida += "Fecha del pedido: " + fechaPedido + "\n";
        salida += "Fecha de entrega estimada: " + calculaFechaEstimada() + "\n";
        salida += "Comentario del pedido: " + comentario + "\n";
        salida += ((producto1 == null)? "": producto1.pintaProductoPedido());
        salida += ((producto2 == null)? "": producto2.pintaProductoPedido());
        salida += ((producto3 == null)? "": producto3.pintaProductoPedido());
        salida += "Total del pedido: " + precioTotal + "E\n";
        salida += "Estado del pedido: " + estado + "\n";
        return salida;
    }

    public boolean insertaProducto(Producto producto, int cantidad) {
        if (pedidoLleno()) return false;
        if (!producto.salidaProducto(cantidad)) return false;
        if (producto1 == null) {
                producto1 = producto;
                precioTotal += (producto1.getPrecio() * cantidad);
                return true;
        }
        if (producto2 == null) {
            producto2 = producto;
            precioTotal += (producto2.getPrecio() * cantidad);
            return true;
        }
        producto3 = producto;
        precioTotal += (producto3.getPrecio() * cantidad);
        return true;

    }

    public int cantidadProductos () {
        int salida = 0;
        if (producto1 != null) salida++;
        if (producto2 != null) salida++;
        if (producto3 != null) salida++;
        return salida;
    }

    public boolean pedidoLleno () {
        return cantidadProductos() == 3;
    }



    public void cambiaEstado (int op) {
        switch (op) {
            case 1:
               estado = "Recibido";
               break;
            case 2:
                estado = "En preparación";
                break;
            case 3:
                estado = "Retrasado";
                break;
            case 4:
                estado = "Cancelado";
                break;
            case 5:
                estado = "Enviado";
                break;
        }

    }

    public void cambiaFecha(LocalDate fechaNueva) {
        setFechaPedido(fechaNueva.minusDays(5));
    }

    public void insertaComentario (String comentario) {
        this.comentario = comentario;
    }

    public String pintaSeleccionado () {
        return id + " - " + cantidadProductos() + " producto - " + precioTotal + "E";
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
