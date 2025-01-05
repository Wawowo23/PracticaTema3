package utils;

import java.util.Scanner;

public class Utils {
    public static void limpiaPantalla () {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void pulsaParaContinuar () {
        var s = new Scanner(System.in);
        System.out.println("Pulse para continuar...");
        s.nextLine();
    }

    public static void cerrarSesion () {
        System.out.print("Cerrando sesión ");
        for (int i = 3; i >= 0; i--) {
            System.out.print(".");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
