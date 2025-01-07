package views;

import data.Catalogo;
import models.*;
import utils.Menus;
import utils.Utils;

import java.awt.*;
import java.time.LocalDate;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        var s = new Scanner(System.in);
        Tienda tienda = new Tienda();
        String tipoUsuario, opMenusUsuarios, quiereSeguir = "";


        // Iniciamos el programa teniendo ya un cliente y un trabajador creados
        tienda.mock();

        do { // Creamos un bucle para que no se termine el programa
            Utils.limpiaPantalla();
            Menus.inicio();
            System.out.print("""
                1.- Registrarse como cliente
                2.- Iniciar Sesión
                Introduzca la opción deseada:\s""");
            String op = s.nextLine();
            switch (op) {
                case "1": // Damos la opción de que un cliente se registre
                    System.out.println((tienda.registroCliente(Menus.menuRegistro())) ?
                            "Se ha registrado correctamente"
                            : "Ha ocurrido un error al registrarse");
                    Utils.pulsaParaContinuar();
                    break;
                case "2": // Damos la opción de que el usuario inicie sesión
                    Utils.limpiaPantalla();
                    System.out.print("Introduzca su usuario: ");
                    String usuarioTemp = s.nextLine();
                    System.out.print("Introduzca su contraseña: ");
                    String claveTemp = s.nextLine();
                    tipoUsuario = tienda.login(usuarioTemp,claveTemp);
                    Utils.limpiaPantalla();
                    switch (tipoUsuario) { // Según el tipo de usuario que inicie sesión mostramos distintos menús
                        case "cliente": // Menú para clientes
                            Cliente clienteTemporal = tienda.loginCliente(usuarioTemp,claveTemp);
                            do {
                                Menus.menuCliente(clienteTemporal);
                                opMenusUsuarios = s.nextLine();
                                switch (opMenusUsuarios) {
                                    case "1": // Pintamos el catálogo
                                        Catalogo.pintaCatalogo();
                                        Utils.pulsaParaContinuar();
                                        break;
                                    case "2": // El cliente realiza un pedido
                                        // Comprobamos si el cliente puede realizar más pedidos
                                        if (clienteTemporal.pedidosCompletos()) System.out.println("No puede realizar más pedidos");
                                        else {
                                            Pedido pedidoAgregado = new Pedido();
                                            do { // Creamos un bucle para que el cliente añada productos a su pedido
                                                Producto productoAgregado = Menus.seleccionaProducto();
                                                if (productoAgregado == null) System.out.println("El producto que ha introducido no existe");
                                                // Comprobamos si el producto seleccionado existe
                                                else {
                                                    System.out.print("Introduzca la cantidad de este producto que quiere comprar: ");
                                                    int cantidad = Integer.parseInt(s.nextLine());
                                                    System.out.println(((pedidoAgregado.insertaProducto(productoAgregado,cantidad)) ? 
                                                            "Producto agregado correctamente"
                                                            :"ERROR: Se ha producido un error al agregar el producto al pedido"));
                                                    if (pedidoAgregado.pedidoLleno()) System.out.println("Su pedido está completo. No puede agregar más de 3 productos a su pedido");
                                                    else { //Preguntamos si el cliente quiere seguir añadiendo productos a su pedido
                                                        System.out.print("Quiere introducir otro producto al pedido (S/N): ");
                                                        quiereSeguir = s.nextLine();
                                                    }

                                                    
                                                }
                                            } while (quiereSeguir.equalsIgnoreCase("s") && !pedidoAgregado.pedidoLleno());
                                            // Tras haber completado el pedido lo agregamos al perfil del cliente,
                                            // lo registramos y lo asignamos a un trabajador
                                            clienteTemporal.insertaPedidos(pedidoAgregado);
                                            tienda.registraPedido(pedidoAgregado);
                                            tienda.asignacionAutomatica(pedidoAgregado);
                                            Utils.pulsaParaContinuar();
                                        }
                                        break;
                                    case "3": // Pintamos los pedidos realizados por un cliente
                                        if (clienteTemporal.pedidosVacios()) System.out.println("Aun no ha realizado pedidos");
                                        else {
                                            System.out.println(clienteTemporal.pintaPedidoConCliente());
                                        }
                                        Utils.pulsaParaContinuar();
                                        break;
                                    case "4": // Pintamos los datos del cliente
                                        System.out.println(clienteTemporal.pintaCliente());
                                        Utils.pulsaParaContinuar();
                                        break;
                                    case "5": // Permitimos cambiar los datos de un cliente
                                            System.out.println("Estos son tus datos personales registrados: ");
                                            System.out.println(clienteTemporal.pintaCliente());
                                            System.out.print("Que dato personal quieres cambiar(nombre/dirección/teléfono...): ");
                                            String datoCambiado = s.nextLine();
                                            Menus.cambiaDato(clienteTemporal,datoCambiado);

                                        break;
                                    case "6": // Cerramos la sesión del cliente
                                        Utils.cerrarSesion();
                                        break;
                                    default:
                                        System.out.println("Opción introducida no válida");
                                        break;
                                }
                            } while (!opMenusUsuarios.equals("6"));

                            break;
                        case "trabajador": // Menú para trabajadores
                            Trabajador trabajadorTemporal = tienda.loginTrabajador(usuarioTemp,claveTemp);
                            do {
                                Utils.limpiaPantalla();
                                Menus.menuTrabajador(trabajadorTemporal);
                                opMenusUsuarios = s.nextLine();
                                switch (opMenusUsuarios) {
                                    case "1": // Pintamos los pedidos que tiene asignado un trabajador
                                        System.out.println("Bienvenido al apartado de visualización de pedidos");
                                        System.out.println(trabajadorTemporal.pintaPedidosAsignados());
                                        Utils.pulsaParaContinuar();
                                        break;
                                    case "2": // Permitimos el cambio de estado, fecha estimada y comentario del pedido
                                        System.out.println("Bienvenido al apartado de cambio de estados de los pedidos");
                                        System.out.println(trabajadorTemporal.pintaPedidosAsignados());
                                        if (trabajadorTemporal.numeroPedidos() != 0) { // Comprobamos si el trabajador tiene pedidos asignados
                                            System.out.print("Introduce el id del pedido que quieres modificar: ");
                                            String idTemp = s.nextLine();
                                            int estadoNuevo = Menus.seleccionaEstado(idTemp);
                                            trabajadorTemporal.cambiaEstadoPedido(estadoNuevo,idTemp);
                                            System.out.println("Estado del pedido cambiado correctamente");
                                            System.out.print("¿Quiere indicar una nueva fecha de entrega?(S/N): ");
                                            String opCambiaFecha = s.nextLine();
                                            if (opCambiaFecha.equalsIgnoreCase("s")) {
                                                System.out.print("Introduzca la nueva fecha de entrega (yyyy-mm-dd): ");
                                                LocalDate fechaNueva = LocalDate.parse(s.nextLine());
                                                trabajadorTemporal.cambiaFechaPedido(fechaNueva,idTemp);
                                                System.out.println("Fecha guardada correctamente");
                                            }
                                            System.out.println("¿Quiere añadir un comentario al pedido?(S/N)");
                                            String opInsertaComentario = s.nextLine();
                                            if (opInsertaComentario.equalsIgnoreCase("s")) {
                                                System.out.print("Introduzca el nuevo comentario: ");
                                                String comentarioNuevo = s.nextLine();
                                                trabajadorTemporal.insertaComentarioPedido(comentarioNuevo,idTemp);
                                                System.out.println("Comentario guardado correctamente");
                                            }

                                        }

                                        Utils.pulsaParaContinuar();
                                        break;
                                    case "3": // Pintamos el catálogo
                                        System.out.println("Bienvenido al catálogo de nuestra tienda");
                                        Catalogo.pintaCatalogo();
                                        Utils.pulsaParaContinuar();
                                        break;
                                    case "4": // Permitimos cambiar los datos de los productos del catálogo
                                        System.out.println("Bienvenido al apartado de cambio de productos");
                                        Menus.cambioProducto();
                                        Utils.pulsaParaContinuar();
                                        break;
                                    case "5": // Pintamos los datos del trabajador
                                        System.out.println("Bienvenido al apartado de visualización del perfil");
                                        System.out.println(trabajadorTemporal.pintaTrabajador());
                                        Utils.pulsaParaContinuar();
                                        break;
                                    case "6": // Permitimos cambiar los datos del trabajador
                                        System.out.println("Bienvenido al apartado de cambio de datos personales");
                                        System.out.println("Estos son tus datos personales");
                                        System.out.println(trabajadorTemporal.pintaTrabajador());
                                        System.out.print("Que dato personal quieres cambiar(nombre/dirección/teléfono...): ");
                                        String datoCambiado = s.nextLine();
                                        Menus.cambiaDatoTrabajador(trabajadorTemporal,datoCambiado);
                                        Utils.pulsaParaContinuar();
                                        break;
                                    case "7": // Cerramos la sesión del trabajador
                                        Utils.cerrarSesion();
                                        break;
                                    default:
                                        System.out.println("Opción no válida");
                                        break;

                                }
                            } while (!opMenusUsuarios.equals("7"));
                            break;
                        case "admin": // Menú para administrador
                            Admin adminTemporal = tienda.loginAdmin(usuarioTemp,claveTemp);
                            do {
                                Utils.limpiaPantalla();
                                Menus.menuAdministrador(adminTemporal,tienda);
                                opMenusUsuarios = s.nextLine();
                                switch (opMenusUsuarios) {
                                    case "1": // Asignación manual de pedidos a trabajadores
                                        Pedido pedidoSeleccionado;
                                        System.out.println("Bienvenido al apartado de asignación de pedidos");
                                        do { // Creamos un bucle que asegure de que no se asigne un pedido que no existe
                                            // Pintamos los pedidos
                                            System.out.println(adminTemporal.pintaSeleccionPedido());
                                            System.out.print("Seleccione el pedido a asignar: ");
                                            int opPedido = Integer.parseInt(s.nextLine());
                                            pedidoSeleccionado = adminTemporal.seleccionaPedido(opPedido);
                                            if (pedidoSeleccionado == null) System.out.println("El pedido que ha seleccionado no existe");
                                        } while (pedidoSeleccionado == null);
                                        // Pintamos los trabajadores

                                        System.out.println(tienda.pintaTrabajadoresParaSeleccion());
                                        System.out.print("Seleccione el trabajador: ");
                                        int opTrabajador = Integer.parseInt(s.nextLine());
                                        // Comprobamos si el pedido ha sido asignado correctamente
                                        System.out.println((tienda.asignaPedidoTrabajador(opTrabajador,pedidoSeleccionado)) ?
                                                "El pedido ha sido asignado correctamente"
                                                : "Ha ocurrido un problema al asignar el pedido");

                                        break;
                                    case "2": // Permitimos el cambio de estado, fecha estimada y comentario del pedido
                                        System.out.println(adminTemporal.pintaEstadosPedidos());
                                        System.out.print("Introduce el id del pedido que quieres modificar: ");
                                        String idTemp = s.nextLine();
                                        int estadoNuevo = Menus.seleccionaEstado(idTemp);
                                        adminTemporal.cambiaEstadoPedido(estadoNuevo,idTemp);
                                        System.out.println("Estado del pedido cambiado correctamente");
                                        System.out.print("¿Quiere indicar una nueva fecha de entrega?(S/N): ");
                                        String opCambiaFecha = s.nextLine();
                                        if (opCambiaFecha.equalsIgnoreCase("s")) {
                                            System.out.print("Introduzca la nueva fecha de entrega (yyyy-mm-dd): ");
                                            LocalDate fechaNueva = LocalDate.parse(s.nextLine());
                                            adminTemporal.cambiaFechaPedido(fechaNueva,idTemp);
                                            System.out.println("Fecha guardada correctamente");
                                        }
                                        System.out.println("¿Quiere añadir un comentario al pedido?(S/N)");
                                        String opInsertaComentario = s.nextLine();
                                        if (opInsertaComentario.equalsIgnoreCase("s")) {
                                            System.out.print("Introduzca el nuevo comentario: ");
                                            String comentarioNuevo = s.nextLine();
                                            adminTemporal.insertaComentarioPedido(comentarioNuevo,idTemp);
                                            System.out.println("Comentario guardado correctamente");
                                        }
                                        break;
                                    case "3": // Permitimos dar de alta a un trabajador
                                        System.out.println("Bienvenido al apartado de registro de trabajadores");
                                        Utils.pulsaParaContinuar();
                                        // Comprobamos si se puede dar de alta a más trabajadores
                                        if (tienda.trabajadoresLlenos()) System.out.println("No se puede dar de alta a más trabajadores");
                                        else {
                                            System.out.println(((tienda.registroTrabajador(Menus.registroTrabajador())) ?
                                                    "El trabajador ha sido registrado correctamente"
                                                    :"Ha habido un problema en el registro del trabajador"));
                                        }
                                        break;
                                    case "4": // Pintamos todos los pedidos registrados
                                        System.out.println("Bienvenido al apartado de visualización de pedidos");
                                        System.out.println(tienda.pintaPedidosParaAdmin());
                                        Utils.pulsaParaContinuar();
                                        break;
                                    case "5": // Pintamos todos los clientes registrados
                                        System.out.println("Bienvenido al apartado de visualización de clientes");
                                        System.out.println(tienda.pintaClientes());
                                        Utils.pulsaParaContinuar();
                                        break;
                                    case "6": // Pintamos todos los trabajadores registrados
                                        System.out.println("Bienvenido al apartado de visualización de trabajadores");
                                        System.out.println(tienda.pintaTrabajadores());
                                        Utils.pulsaParaContinuar();
                                        break;
                                    case "7": // Cerramos la sesión del administrador
                                        Utils.cerrarSesion();
                                        break;
                                    default:
                                        System.out.println("Opción introducida incorrecta");
                                        break;

                                }
                            } while (!opMenusUsuarios.equals("7"));
                            break;
                        case "error":
                            System.out.println("Usuario y/o contraseña introducidos incorrectos");
                            Utils.pulsaParaContinuar();
                            break;
                    }
                    break;
                default:

                    System.out.println("Opción introducida incorrecta.");
                    Utils.pulsaParaContinuar();
                    break;
            }

        } while (true);

    }
}
