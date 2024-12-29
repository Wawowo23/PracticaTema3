package utils;

import data.Catalogo;
import models.Cliente;
import models.Producto;
import models.Tienda;

import java.util.Scanner;

public class Menus {
    public static void inicio () {

        System.out.println("""
                
                   ___ _                           _     _            \s
                  / __(_) ___ _ ____   _____ _ __ (_) __| | ___       \s
                 /__\\// |/ _ \\ '_ \\ \\ / / _ \\ '_ \\| |/ _` |/ _ \\      \s
                / \\/  \\ |  __/ | | \\ V /  __/ | | | | (_| | (_) |     \s
                \\_____/_|\\___|_| |_|\\_/ \\___|_| |_|_|\\__,_|\\___/      \s
                                                                      \s
                                                                      \s
                                   __ _                               \s
                                  / _` |                              \s
                                 | (_| |                              \s
                                  \\__,_|                              \s
                                                                      \s
                   ___                               _                \s
                  / __\\__ _ __ _ __   __ _ _ __  ___| |__   ___  _ __ \s
                 / _\\/ _ \\ '__| '_ \\ / _` | '_ \\/ __| '_ \\ / _ \\| '_ \\\s
                / / |  __/ |  | | | | (_| | | | \\__ \\ | | | (_) | |_) |
                \\/   \\___|_|  |_| |_|\\__,_|_| |_|___/_| |_|\\___/| .__/\s
                                                                |_|   \s
                
                
                
                """);


    }

    public static void menuCliente (Cliente cliente) {
        System.out.printf("""
                FERNANSHOP
                Bienvenido %s
                1.- Consultar el catálogo de productos
                2.- Realizar un pedido
                3.- Ver mis pedidos realizados
                4.- Ver mis datos personales
                5.- Modificar mis datos personales
                6.- Cerrar sesión""",cliente.getUsuario());
    }

    public static Producto seleccionaProducto () {
        var s = new Scanner(System.in);
        int op;
        Catalogo.pintaCatalogo();
        System.out.print("Introduce el pedido que quiere agregar: ");
        op = Integer.parseInt(s.nextLine());
        return switch (op) {
            case 1 -> Catalogo.libroRecetas;
            case 2 -> Catalogo.libroBricolaje;
            case 3 -> Catalogo.libroInformatica;
            case 4 -> Catalogo.libroHarryPotter;
            case 5 -> Catalogo.libroGeronimoStilton;
            case 6 -> Catalogo.libroSeniorAnillos;
            case 7 -> Catalogo.libroCorazonNavidad;
            case 8 -> Catalogo.libroAkira;
            default -> null;
        };
    }
}
