package models;

public class Cliente {
    // Atributos
    private String nombreCompleto;
    private String usuario;
    private String correo;
    private String clave;
    private String direccion;
    private int telefono;
    private String localidad;
    private String provincia;
    private Pedido pedido1;
    private Pedido pedido2;

    // Constructor

    public Cliente(String nombreCompleto,String usuario, String correo, String clave, String direccion, int telefono, String localidad, String provincia) {
        this.nombreCompleto = nombreCompleto;
        this.usuario = usuario;
        this.correo = correo;
        this.clave = clave;
        this.direccion = direccion;
        this.telefono = telefono;
        this.localidad = localidad;
        this.provincia = provincia;
        pedido1 = null;
        pedido2 = null;
    }

    // Constructor copia

    public Cliente (Cliente clienteCopiado) {
        nombreCompleto = clienteCopiado.nombreCompleto;
        usuario = clienteCopiado.usuario;
        correo = clienteCopiado.correo;
        clave = clienteCopiado.clave;
        direccion = clienteCopiado.direccion;
        telefono = clienteCopiado.telefono;
        localidad = clienteCopiado.localidad;
        provincia = clienteCopiado.provincia;
        pedido1 = clienteCopiado.pedido1;
        pedido2 = clienteCopiado.pedido2;
    }

    // Getters y Setters


    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
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

    // Metodos

    public boolean login(String clave, String usuario) {
        return this.usuario.equals(usuario) && this.clave.equals(clave);
    }

    private boolean pedidosCompletos () {
        return pedido2 != null && pedido1 != null;
    }

    private boolean pedidosVacios () {
        return pedido2 == null && pedido1 == null;
    }

    public boolean insertaPedidos (Pedido pedido) {
        if (pedidosCompletos()) return false;
        if (pedido1 == null) {
            pedido1 = new Pedido(pedido);
            return true;
        }
        pedido2 = new Pedido(pedido);
        return true;
    }


    public String pintaCliente() {
        return "Cliente: " + nombreCompleto + "\n" +
                "Dirección: " + direccion +  "\n" +
                "Localidad: " + localidad +  "\n" +
                "Provincia: " + provincia +  "\n" +
                "Teléfono: " + telefono +  "\n" +
                "Correo: " + correo +  "\n";
    }

    public String pintaPedidoConCliente () {
        String salida = "";
        if (pedidosVacios()) return "No hay pedidos todavía";
        if (pedido1 != null) {
            salida += "============   " + pedido1.getId() + "   ============\n";
            salida += "Estado: " + pedido1.getEstado() + "\n";
            salida += pintaCliente();
            salida += pedido1.pintaPedidoParaCliente();
        }
        if (pedido2 != null) {
            salida += "============   " + pedido2.getId() + "   ============\n";
            salida += "Estado: " + pedido2.getEstado() + "\n";
            salida += pintaCliente();
            salida += pedido2.pintaPedidoParaCliente();
        }
        return salida;
    }
}
