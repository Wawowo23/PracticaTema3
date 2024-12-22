package views;

import data.Catalogo;
import models.Cliente;
import models.Pedido;

import java.util.Scanner;

public class mainPrueba {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        Cliente c1 = new Cliente("Miguel Angel","amai@gmail.com","1234","aqui",688569596,"Torredonjimeno","Jaen");

        System.out.println("Inserta tu correo:");
        String correo = s.nextLine();
        System.out.println("Inserta tu clave:");
        String clave = s.nextLine();

        System.out.println((c1.login(clave,correo)) ? "Sesión iniciada correctamente":"Error al iniciar sesion");
    }
}
