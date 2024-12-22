package models;

public class Admin {
    private  String usuario;
    private  String clave;

    public Admin() {
        usuario = "Wiwi";
        clave = "1234";
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
}
