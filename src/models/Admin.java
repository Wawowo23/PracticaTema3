package models;

public class Admin {
    private  String usuario;
    private  String clave;
    private Pedido pedido1;
    private Pedido pedido2;
    private Pedido pedido3;
    private Pedido pedido4;

    public Admin() {
        usuario = "Wiwi";
        clave = "1234";
        pedido1 = null;
        pedido2 = null;
        pedido3 = null;
        pedido4 = null;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

     // Metodos

    public boolean login (String usuario, String clave) {
        return this.usuario.equals(usuario) && this.clave.equals(clave);
    }

    public boolean registraPedido (Pedido pedido) {
        if (pedidosCompletos()) return false;
        if (pedido1 == null) {
            pedido1 = new Pedido(pedido);
            return true;
        }
        if (pedido2 == null) {
            pedido2 = new Pedido(pedido);
            return true;
        }
        if (pedido3 == null) {
            pedido3 = new Pedido(pedido);
            return true;
        }
        if (pedido4 == null) {
            pedido4 = new Pedido(pedido);
            return true;
        }
        return false;
    }

    public int numeroPedidos () {
        int salida = 0;
        if (pedido1 != null) salida++;
        if (pedido2 != null) salida++;
        if (pedido3 != null) salida++;
        if (pedido4 != null) salida++;
        return salida;
    }

    public boolean pedidosCompletos () {
        return numeroPedidos() == 4;
    }

    private boolean pedidosVacios () {
        return numeroPedidos() == 0;
    }

    public String pintaEstadosPedidos () {
        String salida = "";
        if (pedido1 != null) salida += pedido1.pintaPedidoParaTrabajadorAdmin();
        if (pedido2 != null) salida += pedido2.pintaPedidoParaTrabajadorAdmin();
        if (pedido3 != null) salida += pedido3.pintaPedidoParaTrabajadorAdmin();
        if (pedido4 != null) salida += pedido4.pintaPedidoParaTrabajadorAdmin();
        return salida;
    }

    public void cambiaEstadoPedido (int op, String idPedido) {
        if (pedido1 != null && idPedido.equals(pedido1.getId())) pedido1.cambiaEstado(op);
        if (pedido2 != null && idPedido.equals(pedido2.getId())) pedido2.cambiaEstado(op);
        if (pedido3 != null && idPedido.equals(pedido3.getId())) pedido3.cambiaEstado(op);
        if (pedido4 != null && idPedido.equals(pedido4.getId())) pedido4.cambiaEstado(op);

    }
}
