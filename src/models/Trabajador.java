package models;

public class Trabajador {
    private String nombre;
    private String usuario;
    private String clave;
    private String idTrabajador;
    private String correo;
    private Pedido pedido1;
    private Pedido pedido2;

    // Constructor

    public Trabajador(String nombre, String usuario, String clave, String idTrabajador, String correo) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.clave = clave;
        this.idTrabajador = idTrabajador;
        this.correo = correo;
        pedido1 = null;
        pedido2 = null;
    }

    //Constructor copia

    public Trabajador (Trabajador trabajador) {
        nombre = trabajador.nombre;
        usuario = trabajador.usuario;
        clave = trabajador.clave;
        idTrabajador = trabajador.idTrabajador;
        correo = trabajador.correo;
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

    // Metodos

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




}
