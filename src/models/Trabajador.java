package models;

import java.time.LocalDate;

public class Trabajador {
    private String nombre;
    private String apellidos;
    private String usuario;
    private String clave;
    private String idTrabajador;
    private String correo;
    private String direccion;
    private int telefono;
    private Pedido pedido1;
    private Pedido pedido2;

    private static int trabajadoresCreados = 0;

    // Constructor

    public Trabajador(String nombre, String apellidos, String usuario, String clave, String correo, String direccion, int telefono) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.clave = clave;
        this.idTrabajador = generadorIdTrabajador();
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
        pedido1 = null;
        pedido2 = null;
        trabajadoresCreados++;
    }

    //Constructor copia

    public Trabajador (Trabajador trabajador) {
        nombre = trabajador.nombre;
        apellidos = trabajador.apellidos;
        usuario = trabajador.usuario;
        clave = trabajador.clave;
        idTrabajador = trabajador.idTrabajador;
        correo = trabajador.correo;
        direccion = trabajador.direccion;
        telefono = trabajador.telefono;
        pedido1 = trabajador.pedido1;
        pedido2 = trabajador.pedido2;
    }

    // Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(String idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Pedido getPedido1() {
        return pedido1;
    }

    public void setPedido1(Pedido pedido1) {
        this.pedido1 = pedido1;
    }

    public Pedido getPedido2() {
        return pedido2;
    }

    public void setPedido2(Pedido pedido2) {
        this.pedido2 = pedido2;
    }

    public static int getTrabajadoresCreados() {
        return trabajadoresCreados;
    }

    public static void setTrabajadoresCreados(int trabajadoresCreados) {
        Trabajador.trabajadoresCreados = trabajadoresCreados;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    // Metodos

    private String generadorIdTrabajador () {
        String salida = "";
        salida += (getTrabajadoresCreados() + 1);
        while (salida.length() < 3) {
            salida = "0" + salida;
        }
        salida += getNombre().toUpperCase().charAt(0);
        return salida;
    }

    public boolean login (String usuario, String clave) {
        return this.usuario.equals(usuario) && this.clave.equals(clave);
    }

    public int numeroPedidos () {
        int salida = 0;
        if (pedido1 != null) salida++;
        if (pedido2 != null) salida++;
        return salida;
    }

    private boolean pedidosCompletos () {
        return numeroPedidos() == 2;
    }

    private boolean pedidosVacios () {
        return numeroPedidos() == 0;
    }

    public boolean insertaPedido (Pedido pedido) {
        if (pedidosCompletos()) return false;
        if (pedido1 == null) {
            pedido1 = new Pedido(pedido);
            return true;
        }
        pedido2 = new Pedido(pedido);
        return true;
    }

    public void insertaComentarioPedido(String comentario, String idPedido) {
        if (idPedido.equals(pedido1.getId()) && pedido1 != null) pedido1.insertaComentario(comentario);
        if (idPedido.equals(pedido2.getId()) && pedido2 != null) pedido2.insertaComentario(comentario);
    }

    public void cambiaEstadoPedido (int op, String idPedido) {
        if (idPedido.equals(pedido1.getId()) && pedido1 != null) pedido1.cambiaEstado(op);
        if (idPedido.equals(pedido2.getId()) && pedido2 != null) pedido2.cambiaEstado(op);

    }

    public void cambiaFechaPedido (LocalDate fechaNueva, String idPedido) {
        if (idPedido.equals(pedido1.getId()) && pedido1 != null) pedido1.cambiaFecha(fechaNueva);
        if (idPedido.equals(pedido2.getId()) && pedido2 != null) pedido2.cambiaFecha(fechaNueva);
    }

    public String pintaTrabajador () {
        String salida = "";
        salida += "=====  Trabajador: " + idTrabajador + "  =====" + "\n";
        salida += "Usuario: " + usuario + "\n";
        salida += "Nombre: " + nombre + "\n";
        salida += "Apellidos: " + apellidos + "\n";
        salida += "Correo: " + correo + "\n";
        salida += "Dirección: " + direccion + "\n";
        salida += "Teléfono: " + telefono + "\n";
        return salida;
    }

    public String pintaTrabajadorSeleccion  () {
        return nombre + " - " + numeroPedidos() + " pedidos en proceso";
    }

    public String pintaPedidosAsignados () {
        if (pedidosVacios()) return "Aún no tienes pedidos asignados";
        String salida = "";
        if (pedido1 != null) salida += pedido1.pintaPedidoParaTrabajadorAdmin();
        if (pedido2 != null) salida += pedido2.pintaPedidoParaTrabajadorAdmin();
        return salida;
    }
}
