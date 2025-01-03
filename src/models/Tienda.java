package models;

public class Tienda {
    // Atributos
    private Cliente cliente1;
    private Cliente cliente2;
    private Trabajador trabajador1;
    private Trabajador trabajador2;
    private Trabajador trabajador3;
    private Admin admin;

    //todo preguntar a Carlos si todos los trabajadores y clientes deben empezar como null

    // Constructor
    public Tienda () {
        cliente1 = null;
        cliente2 = null;
        trabajador1 = null;
        trabajador2 = null;
        trabajador3 = null;
        admin = new Admin();
    }

    // Getters y Setters


    public Cliente getCliente1() {
        return cliente1;
    }

    public void setCliente1(Cliente cliente1) {
        this.cliente1 = cliente1;
    }

    public Cliente getCliente2() {
        return cliente2;
    }

    public void setCliente2(Cliente cliente2) {
        this.cliente2 = cliente2;
    }

    public Trabajador getTrabajador1() {
        return trabajador1;
    }

    public void setTrabajador1(Trabajador trabajador1) {
        this.trabajador1 = trabajador1;
    }

    public Trabajador getTrabajador2() {
        return trabajador2;
    }

    public void setTrabajador2(Trabajador trabajador2) {
        this.trabajador2 = trabajador2;
    }

    public Trabajador getTrabajador3() {
        return trabajador3;
    }

    public void setTrabajador3(Trabajador trabajador3) {
        this.trabajador3 = trabajador3;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    // Metodos

    public void mock () {
        cliente1 = new Cliente("Carlos","Cañada","Carlos","amai@gmail.com","1234","Calle hola",122344556,"Martos","Jaen");
        //cliente2 = new Cliente("Lara","Camara","Lara","lara@gmail.com","0405","Calle La Cerca",4557656,"Torredonjimeno","Jaen");
        trabajador1 = new Trabajador("Rosendo", "Rosendo","1234","123454","rosendo@gmail.com");
    }

    public String login (String usuario, String clave) {
        if (cliente1 != null && cliente1.login(usuario,clave)) return "cliente";
        if (cliente2 != null && cliente2.login(usuario,clave)) return "cliente";
        if (trabajador1 != null && trabajador1.login(usuario,clave)) return "trabajador";
        if (trabajador2 != null && trabajador2.login(usuario,clave)) return "trabajador";
        if (trabajador3 != null && trabajador3.login(usuario,clave)) return "trabajador";
        if (admin.login(usuario, clave)) return "admin";
        return "error";
    }

    public Cliente loginCliente (String usuario, String clave) {
        if (!login(usuario,clave).equals("cliente")) return null;
        if (cliente1.login(usuario,clave)) return cliente1;
        if (cliente2.login(usuario,clave)) return cliente2;
        return null;
    }

    public boolean registroCliente (Cliente clienteRegistrado) {
        if (cliente1 == null) {
            cliente1 = new Cliente(clienteRegistrado);
            return true;
        }
        if (cliente2 == null) {
            cliente2 = new Cliente(clienteRegistrado);
            return true;
        }
        return false;
    }

    public boolean registraPedido (Pedido pedido) {
     if (admin.pedidosCompletos()) return false;
     admin.registraPedido(pedido);
     return true;
    }

    public Trabajador loginTrabajador (String usuario, String clave) {
        if (!login(usuario, clave).equals("trabajador")) return null;
        if (trabajador1.login(usuario, clave)) return trabajador1;
        if (trabajador2.login(usuario, clave)) return trabajador2;
        if (trabajador3.login(usuario, clave)) return trabajador3;
        return null;
    }

    public Admin loginAdmin (String usuario, String clave) {
        if (login(usuario, clave).equals("admin")) return admin;
        return null;
    }

    private int numeroTrabajadores () {
        int salida = 0;
        if (trabajador1 != null) salida++;
        if (trabajador2 != null) salida++;
        if (trabajador3 != null) salida++;
        return salida;
    }

    public boolean asignacionAutomatica (Pedido pedido) {
        if (numeroTrabajadores() == 0) return false;
        if (numeroTrabajadores() == 1) {
            trabajador1.insertaPedido(pedido);
            return true;
        }
        if (numeroTrabajadores() == 2) {
            if (trabajador1.numeroPedidos() < trabajador2.numeroPedidos()) {
                trabajador1.insertaPedido(pedido);
                return true;
            }
            if (trabajador2.numeroPedidos() < trabajador1.numeroPedidos()) {
                trabajador2.insertaPedido(pedido);
                return true;
            }
            return false;
        }
        if (numeroTrabajadores() == 3) {
            if (trabajador1.numeroPedidos() < trabajador2.numeroPedidos() && trabajador1.numeroPedidos() < trabajador3.numeroPedidos()) {
                trabajador1.insertaPedido(pedido);
                return true;
            }
            if (trabajador2.numeroPedidos() < trabajador1.numeroPedidos() && trabajador2.numeroPedidos() < trabajador3.numeroPedidos()) {
                trabajador2.insertaPedido(pedido);
                return true;
            }
            if (trabajador3.numeroPedidos() < trabajador2.numeroPedidos() && trabajador3.numeroPedidos() < trabajador1.numeroPedidos()) {
                trabajador3.insertaPedido(pedido);
                return true;
            }
            return false;
        }
        return false;

    }

    /*public String pintaAsinacionPedido () {
        String salida = "";
        if (!cliente1.pedidosVacios()) salida += cliente1.pintaAsinacionPedido();
        if (!cliente2.pedidosVacios()) salida += cliente2.pintaAsinacionPedido();
        return salida;
    }*/

    @Override
    public String toString() {
        return "Tienda{" +
                "cliente1=" + cliente1 +
                ", cliente2=" + cliente2 +
                ", trabajador1=" + trabajador1 +
                ", trabajador2=" + trabajador2 +
                ", trabajador3=" + trabajador3 +
                ", admin=" + admin +
                '}';
    }
}
