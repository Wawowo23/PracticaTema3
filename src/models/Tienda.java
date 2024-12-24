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
        cliente1 = new Cliente()
    }

    public String login (String usuario, String clave) {
        if (cliente1.login(usuario,clave)) return "cliente";
        if (cliente2.login(usuario,clave)) return "cliente";
        if (trabajador1.login(usuario,clave)) return "trabajador";
        if (trabajador2.login(usuario,clave)) return "trabajador";
        if (trabajador3.login(usuario,clave)) return "trabajador";
        if (admin.login(usuario, clave)) return "admin";
        return "error";
    }

    public Cliente loginCliente (String usuario, String clave) {
        if (!login(usuario,clave).equals("cliente")) return null;
        if (cliente1.login(usuario,clave)) return cliente1;
        if (cliente2.login(usuario,clave)) return cliente2;
        return null;
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
}
