package views;

import data.Catalogo;
import models.*;
import utils.Menus;
import utils.Utils;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        var s = new Scanner(System.in);
        Tienda tienda = new Tienda();
        String tipoUsuario, opMenusUsuarios, quiereSeguir = "";


        tienda.mock();

        do {
            Utils.limpiaPantalla();
            Menus.inicio();
            System.out.print("""
                1.- Registrarse como cliente
                2.- Iniciar Sesión
                Introduzca la opción deseada:\s""");
            String op = s.nextLine();
            switch (op) {
                case "1":
                    System.out.println((tienda.registroCliente(Menus.menuRegistro())) ?
                            "Se ha registrado correctamente"
                            : "Ha ocurrido un error al registrarse");
                    Utils.pulsaParaContinuar();
                    break;
                case "2":
                    Utils.limpiaPantalla();
                    System.out.print("Introduzca su usuario: ");
                    String usuarioTemp = s.nextLine();
                    System.out.print("Introduzca su contraseña: ");
                    String claveTemp = s.nextLine();
                    tipoUsuario = tienda.login(usuarioTemp,claveTemp);
                    Utils.limpiaPantalla();
                    switch (tipoUsuario) {
                        case "cliente":
                            Cliente clienteTemporal = tienda.loginCliente(usuarioTemp,claveTemp);
                            do {
                                Menus.menuCliente(clienteTemporal);
                                opMenusUsuarios = s.nextLine();
                                switch (opMenusUsuarios) {
                                    case "1":
                                        Catalogo.pintaCatalogo();
                                        Utils.pulsaParaContinuar();
                                        break;
                                    case "2":
                                        if (clienteTemporal.pedidosCompletos()) System.out.println("No puede realizar más pedidos");
                                        else {
                                            Pedido pedidoAgregado = new Pedido();
                                            do {
                                                Producto productoAgregado = Menus.seleccionaProducto();
                                                if (productoAgregado == null) System.out.println("El producto que ha introducido no existe");
                                                else {
                                                    System.out.print("Introduzca la cantidad de este producto que quiere comprar: ");
                                                    int cantidad = Integer.parseInt(s.nextLine());
                                                    System.out.println(((pedidoAgregado.insertaProducto(productoAgregado,cantidad)) ? 
                                                            "Producto agregado correctamente"
                                                            :"ERROR: Se ha producido un error al agregar el producto al pedido"));
                                                    if (pedidoAgregado.pedidoLleno()) System.out.println("Su pedido está completo. No puede agregar más de 3 productos a su pedido");
                                                    else {
                                                        System.out.print("Quiere introducir otro producto al pedido (S/N): ");
                                                        quiereSeguir = s.nextLine();
                                                    }

                                                    
                                                }
                                            } while (!quiereSeguir.equalsIgnoreCase("n") && !pedidoAgregado.pedidoLleno());
                                            clienteTemporal.insertaPedidos(pedidoAgregado);
                                            tienda.registraPedido(pedidoAgregado);
                                            tienda.asignacionAutomatica(pedidoAgregado);
                                            Utils.pulsaParaContinuar();
                                        }
                                        break;
                                    case "3":
                                        if (clienteTemporal.pedidosVacios()) System.out.println("Aun no ha realizado pedidos");
                                        else {
                                            System.out.println(clienteTemporal.pintaPedidoConCliente());
                                        }
                                        Utils.pulsaParaContinuar();
                                        break;
                                    case "4":
                                        System.out.println(clienteTemporal.pintaCliente());
                                        Utils.pulsaParaContinuar();
                                        break;
                                    case "5":
                                        String seguimos = "n";

                                            System.out.println("Estos son tus datos personales registrados: ");
                                            System.out.println(clienteTemporal.pintaCliente());
                                            System.out.print("Que dato personal quieres cambiar(nombre/dirección/teléfono...): ");
                                            String datoCambiado = s.nextLine();
                                            Menus.cambiaDato(clienteTemporal,datoCambiado);

                                        break;
                                }
                            } while (!opMenusUsuarios.equals("6"));

                            break;
                        case "trabajador":
                            Trabajador trabajadorTemporal = tienda.loginTrabajador(usuarioTemp,claveTemp);
                            do {
                                Menus.menuTrabajador(trabajadorTemporal);
                                opMenusUsuarios = s.nextLine();
                                switch (opMenusUsuarios) {
                                    case "1":

                                        break;
                                    case "2":
                                        break;
                                    case "3":
                                        break;
                                    case "4":
                                        break;
                                    case "5":
                                        break;
                                    case "6":
                                        break;
                                    case "7":
                                        break;
                                    default:
                                        System.out.println("Opción no válida");
                                        break;

                                }
                            } while (!opMenusUsuarios.equals("7"));
                            break;
                        case "admin":
                            Admin adminTemporal = tienda.loginAdmin(usuarioTemp,claveTemp);
                            do {
                                Menus.menuAdministrador(adminTemporal);
                                opMenusUsuarios = s.nextLine();
                                switch (opMenusUsuarios) {
                                    case "1":
                                        break;
                                    case "2":
                                        System.out.println(adminTemporal.pintaEstadosPedidos());
                                        System.out.print("Introduce el id del pedido que quieres modificar: ");
                                        String idTemp = s.nextLine();
                                        int estadoNuevo = Menus.seleccionaEstado(idTemp);
                                        adminTemporal.cambiaEstadoPedido(estadoNuevo,idTemp);
                                        break;
                                    case "3":
                                        break;
                                    case "4":
                                        break;
                                    case "5":
                                        break;
                                    case "6":
                                        break;
                                    case "7":
                                        System.out.println("Cerrando sesión...");
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
