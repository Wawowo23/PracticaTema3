package utils;

import data.Catalogo;
import models.*;

import java.util.Scanner;

public class Menus {

    // Metodo que pinta el logo de la empresa
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

    // Metodo que pinta el menu del cliente
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

    // Metodo que devuelve un cliente tras reunir sus datos de registro
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
        do { // Bucle que comprueba que el correo contenga un @
            System.out.print("Introduzca su correo: ");
            correo = s.nextLine();
            if (!correo.contains("@")) System.out.println("El correo introducido no cumple los requisitos");
        } while (!correo.contains("@"));
        System.out.print("Introduzca su dirección: ");
        direccion = s.nextLine();
        System.out.print("Introduzca su provincia: ");
        provincia = s.nextLine();
        System.out.print("Introduzca su localidad: ");
        localidad = s.nextLine();
        do { //Bucle que comprueba que el teléfono tiene 9 dígitos
            System.out.print("Introduzca su número de teléfono: ");
            telefono = Integer.parseInt(s.nextLine());
            if (telefono < 100000000 || telefono > 999999999) System.out.println("El número introducido no cumple con los requisitos establecidos");
        } while (telefono < 100000000 || telefono > 999999999);
        return new Cliente(nombre,apellidos,usuario,correo,clave,direccion,telefono,localidad,provincia);

    }

    // Metodo que devuelve un trabajador tras reunir los datos necesarios para darlo de alta
    public static Trabajador registroTrabajador () {
        var s = new Scanner(System.in);
        String usuario,clave,nombre,apellidos,correo,direccion;
        int telefono;
        System.out.print("Introduzca el usuario: ");
        usuario = s.nextLine();
        System.out.print("Introduzca la contraseña: ");
        clave = s.nextLine();
        System.out.print("Introduzca el nombre: ");
        nombre = s.nextLine();
        System.out.print("Introduzca los apellidos: ");
        apellidos = s.nextLine();
        do {
            System.out.print("Introduzca su correo: ");
            correo = s.nextLine();
            if (!correo.contains("@")) System.out.println("El correo introducido no cumple los requisitos");
        } while (!correo.contains("@"));
        System.out.print("Introduzca la dirección: ");
        direccion = s.nextLine();
        do {
            System.out.print("Introduzca el número de teléfono: ");
            telefono = Integer.parseInt(s.nextLine());
            if (telefono < 100000000 || telefono > 999999999) System.out.println("El número introducido no cumple con los requisitos establecidos");
        } while (telefono < 100000000 || telefono > 999999999 );
        return new Trabajador(nombre,apellidos,usuario,clave,correo,direccion,telefono);
    }

    // Metodo que se encarga de cambiar un dato personal del cliente que sea introducido
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
            case "correo":
                String correoNuevo;
                do {
                    System.out.print("Introduzca su nuevo correo: ");
                    correoNuevo = s.nextLine();
                    if (!correoNuevo.contains("@")) System.out.println("El correo que introduzca debe contener un @");
                } while (!correoNuevo.contains("@"));
                clienteTemporal.setCorreo(correoNuevo);
                break;
            default:
                System.out.print("Dato introducido incorrecto");
                break;


        }
    }

    // Metodo que pinta el menú de trabajador
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

    // Metodo que pinta el menú de administrador
    public static void menuAdministrador(Admin admin, Tienda tienda){
        System.out.printf("""
                                   FERNANSHOP
                ==================================================
                = Bienvenido %s. Tienes %d pedido por asignar    =
                ==================================================
                1. Asignar un pedido a un trabajador
                2. Modificar el estado de un pedido
                3. Dar de alta un trabajador
                4. Ver todos los pedidos
                5. Ver todos los clientes
                6. Ver todos los trabajadores
                7. Cerrar sesión
                Introduzca la opción a realizar:\s""",admin.getUsuario(),tienda.pedidosSinAsignar());
    }

    // Metodo que devuelve un producto que haya sido seleccionado del catálogo
    public static Producto seleccionaProducto () {
        var s = new Scanner(System.in);
        int op;
        Catalogo.pintaCatalogo();
        do { // Bucle que se asegura de que se seleccione un producto que existe
            System.out.print("Introduce el producto que quieres seleccionar: ");
            op = Integer.parseInt(s.nextLine());
            if (op < 1 || op > 8) System.out.println("El producto seleccionado no existe");
            else {
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

        } while (op < 1 || op > 8);

        return null;
    }

    // Metodo que se utiliza para cambiar los datos de un producto
    public static void cambioProducto () {
        var s = new Scanner(System.in);
        Producto productoCambio = seleccionaProducto();
        System.out.print("Introduce lo que quieres cambiar de este producto (nombre/precio/cantidad): ");
        String opcion = s.nextLine();
        switch (opcion.toLowerCase()) {
            case "nombre":
                System.out.print("Introduce el nuevo nombre para el producto: ");
                productoCambio.setNombre(s.nextLine());
                break;
            case "precio":
                float nuevoPrecio;
                do { // Comprobamos que el nuevo precio introducido no sea menor de 0
                    System.out.print("Introduce el nuevo precio para el producto: ");
                    nuevoPrecio = Float.parseFloat(s.nextLine());
                    if (nuevoPrecio < 0) System.out.println("No se puede cambiar el precio establecido al introducido");
                } while (nuevoPrecio < 0);
                productoCambio.setPrecio((nuevoPrecio));
                break;
            case "cantidad":
                int cantidadNueva;
                do { // Comprobamos que la nueva cantidad introducida no sea menor de 0
                    System.out.print("Introduce la nueva cantidad para el producto: ");
                    cantidadNueva = Integer.parseInt(s.nextLine());
                    if (cantidadNueva < 0) System.out.println("No se puede cambiar la cantidad a la introducida");
                } while (cantidadNueva < 0);
                productoCambio.setCantidad(cantidadNueva);
                break;
            default:
                System.out.println("La opción que ha introducido no existe");
                break;
        }

    }

    // Metodo que muestra los distintos estados que se le pueden dar a un pedido y devuelve una de las opciones
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

    // Metodo que se encarga de cambiar un dato personal del trabajador que sea introducido
    public static void cambiaDatoTrabajador(Trabajador trabajadorTemporal, String datoCambiado) {
        var s = new Scanner(System.in);
        switch (datoCambiado.toLowerCase()) {
            case "nombre":
                System.out.print("Introduzca su nuevo nombre: ");
                trabajadorTemporal.setNombre(s.nextLine());
                break;
            case "apellidos":
                System.out.print("Introduzca sus nuevos apellidos: ");
                trabajadorTemporal.setApellidos(s.nextLine());
                break;
            case "direccion":
                System.out.print("Introduzca su nueva direccion: ");
                trabajadorTemporal.setDireccion(s.nextLine());
                break;
            case "telefono":
                int telefonoNuevo;
                do {
                    System.out.print("Introduzca su número de teléfono: ");
                    telefonoNuevo = Integer.parseInt(s.nextLine());
                    if (telefonoNuevo < 100000000 || telefonoNuevo > 999999999)
                        System.out.println("El número introducido no cumple con los requisitos establecidos");
                } while (telefonoNuevo < 100000000 || telefonoNuevo > 999999999);
                trabajadorTemporal.setTelefono(telefonoNuevo);
                break;
            case "correo":
                String correoNuevo;
                do {
                    System.out.print("Introduzca su nuevo correo: ");
                    correoNuevo = s.nextLine();
                    if (!correoNuevo.contains("@")) System.out.println("El correo que introduzca debe contener un @");
                } while (!correoNuevo.contains("@"));
                trabajadorTemporal.setCorreo(correoNuevo);
                break;
            default:
                System.out.print("Dato introducido incorrecto");
                break;


        }
    }
}
