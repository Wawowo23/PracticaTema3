package utils;

import data.Catalogo;
import models.*;

import java.util.Scanner;

public class Menus {
    public static void inicio () {

        System.out.println("""
                ██████╗ ██╗███████╗███╗   ██╗██╗   ██╗███████╗███╗   ██╗██╗██████╗  ██████╗      █████╗     ███████╗███████╗██████╗ ███╗   ██╗ █████╗ ███╗   ██╗███████╗██╗  ██╗ ██████╗ ██████╗\s
                ██╔══██╗██║██╔════╝████╗  ██║██║   ██║██╔════╝████╗  ██║██║██╔══██╗██╔═══██╗    ██╔══██╗    ██╔════╝██╔════╝██╔══██╗████╗  ██║██╔══██╗████╗  ██║██╔════╝██║  ██║██╔═══██╗██╔══██╗
                ██████╔╝██║█████╗  ██╔██╗ ██║██║   ██║█████╗  ██╔██╗ ██║██║██║  ██║██║   ██║    ███████║    █████╗  █████╗  ██████╔╝██╔██╗ ██║███████║██╔██╗ ██║███████╗███████║██║   ██║██████╔╝
                ██╔══██╗██║██╔══╝  ██║╚██╗██║╚██╗ ██╔╝██╔══╝  ██║╚██╗██║██║██║  ██║██║   ██║    ██╔══██║    ██╔══╝  ██╔══╝  ██╔══██╗██║╚██╗██║██╔══██║██║╚██╗██║╚════██║██╔══██║██║   ██║██╔═══╝\s
                ██████╔╝██║███████╗██║ ╚████║ ╚████╔╝ ███████╗██║ ╚████║██║██████╔╝╚██████╔╝    ██║  ██║    ██║     ███████╗██║  ██║██║ ╚████║██║  ██║██║ ╚████║███████║██║  ██║╚██████╔╝██║    \s
                ╚═════╝ ╚═╝╚══════╝╚═╝  ╚═══╝  ╚═══╝  ╚══════╝╚═╝  ╚═══╝╚═╝╚═════╝  ╚═════╝     ╚═╝  ╚═╝    ╚═╝     ╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝  ╚═══╝╚══════╝╚═╝  ╚═╝ ╚═════╝ ╚═╝    \s
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
                6.- Cerrar sesión
                Introduzca la opción deseada:\s""",cliente.getUsuario());
    }

    public static Cliente menuRegistro () {
        var s = new Scanner(System.in);
        String nombre, apellidos, usuario, correo, clave, direccion, localidad, provincia;
        int telefono;
        System.out.print("Introduzca su usuario: ");
        usuario = s.nextLine();
        System.out.print("Introduzca su contraseña: ");
        clave = s.nextLine();
        System.out.println("Usuario y clave registrado correctamente");
        Utils.pulsaParaContinuar();
        System.out.println("A continuación deberá introducir sus datos personales");
        System.out.print("Introduzca su nombre: ");
        nombre = s.nextLine();
        System.out.print("Introduzca su apellido: ");
        apellidos = s.nextLine();
        System.out.print("Introduzca su correo: ");
        correo = s.nextLine();
        System.out.print("Introduzca su dirección: ");
        direccion = s.nextLine();
        System.out.print("Introduzca su provincia: ");
        provincia = s.nextLine();
        System.out.print("Introduzca su localidad: ");
        localidad = s.nextLine();
        do {
            System.out.print("Introduzca su número de teléfono: ");
            telefono = Integer.parseInt(s.nextLine());
            if (telefono < 100000000 || telefono > 999999999) System.out.println("El número introducido no cumple con los requisitos establecidos");
        } while (telefono < 100000000 || telefono > 999999999);
        return new Cliente(nombre,apellidos,usuario,correo,clave,direccion,telefono,localidad,provincia);

    }

    public static void cambiaDato (Cliente clienteTemporal, String datoCambiado) {
        var s = new Scanner(System.in);
        switch (datoCambiado.toLowerCase()) {
            case "nombre":
                System.out.print("Introduzca su nuevo nombre: ");
                clienteTemporal.setNombre(s.nextLine());
                break;
            case "apellidos":
                System.out.print("Introduzca sus nuevos apellidos: ");
                clienteTemporal.setApellidos(s.nextLine());
                break;
            case "direccion":
                System.out.print("Introduzca su nueva direccion: ");
                clienteTemporal.setDireccion(s.nextLine());
                break;
            case "localidad":
                System.out.print("Introduzca su nueva localidad: ");
                clienteTemporal.setLocalidad(s.nextLine());
                break;
            case "provincia":
                System.out.print("Introduzca su nueva provincia: ");
                clienteTemporal.setProvincia(s.nextLine());
                break;
            case "telefono":
                int telefonoNuevo;
                do {
                    System.out.print("Introduzca su número de teléfono: ");
                    telefonoNuevo = Integer.parseInt(s.nextLine());
                    if (telefonoNuevo < 100000000 || telefonoNuevo > 999999999)
                        System.out.println("El número introducido no cumple con los requisitos establecidos");
                } while (telefonoNuevo < 100000000 || telefonoNuevo > 999999999);
                clienteTemporal.setTelefono(telefonoNuevo);

                break;
            default:
                System.out.print("Dato introducido incorrecto");
                break;


        }
    }

    public static void menuTrabajador(Trabajador trabajador){

        System.out.printf("""
                                   FERNANSHOP
                ==================================================
                = Bienvenido %s. Tienes %s pedidos que gestionar =
                ==================================================
                1. Consultar los pedidos que tengo asignados
                2. Modificar el estado de un pedido
                3. Consultar el catálogo de productos
                4. Modificar un producto del catálogo
                5. Ver mi perfil
                6. Modificar mis datos personales
                7. Cerrar sesión
                Introduzca la opción a realizar:\s""",trabajador.getNombre(),trabajador.numeroPedidos());
    }

    public static void menuAdministrador(Admin admin){
        System.out.printf("""
                                   FERNANSHOP
                ==================================================
                = Bienvenido %s. Tienes %s pedido por asignar    =
                ==================================================
                1. Asignar un pedido a un trabajador
                2. Modificar el estado de un pedido
                3. Dar de alta un trabajador
                4. Ver todos los pedidos
                5. Ver todos los clientes
                6. Ver todos los trabajadores
                7. Cerrar sesión
                Introduzca la opción a realizar:\s""",admin.getUsuario(),admin.numeroPedidos());
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

    public static int seleccionaEstado (String id) {
        var s = new Scanner(System.in);
        int opcion;
        System.out.printf("""
                === Actualización del pedido %s ===
                
                Nuevo estado:
                
                1.- Recibido
                2.- En preparación
                3.- Retrasado
                4.- Cancelado
                5.- Enviado
                Selecciona el nuevo estado:\s""",id);
        opcion = Integer.parseInt(s.nextLine());
        return opcion;
    }

}
