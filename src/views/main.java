package views;

import models.Tienda;
import utils.Menus;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        var s = new Scanner(System.in);
        Tienda tienda = new Tienda();
        String tipoUsuario;


        tienda.mock();

        Menus.inicio();
        System.out.print("Introduzca su usuario: ");
        String usuarioTemp = s.nextLine();
        System.out.print("Introduzca su contraseña: ");
        String claveTemp = s.nextLine();
        tipoUsuario = tienda.login(usuarioTemp,claveTemp);
        System.out.println(tipoUsuario);
    }
}
