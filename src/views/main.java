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
            Menus.inicio();
            System.out.print("""
                1.- Registrarse como cliente
                2.- Iniciar Sesión
                Introduzca la opción deseada:\s""");
            String op = s.nextLine();
            switch (op) {
                case "1":
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
                                        break;
                                    case "2":
                                        if (clienteTemporal.pedidosCompletos()) System.out.println("No puede realizar más pedidos");
                                        else {
                                            Pedido pedidoAgregado = new Pedido();
                                            do {
                                                Producto productoAgregado = Menus.seleccionaProducto();
                                                if (productoAgregado == null) System.out.println("El producto que ha introducido no existe");
                                                else {
                                                    System.out.print("Introduzca la cantidad de este producto que quiere comprar");
                                                    int cantidad = Integer.parseInt(s.nextLine());
                                                    System.out.println(((pedidoAgregado.insertaProducto(productoAgregado,cantidad)) ? 
                                                            "Producto agregado correctamente"
                                                            :"ERROR: Se ha producido un error al agregar el producto al pedido"));
                                                    System.out.print("Quiere introducir otro producto al pedido (S/N): ");
                                                    quiereSeguir = s.nextLine();
                                                    
                                                }
                                            } while (!quiereSeguir.equalsIgnoreCase("n"));
                                            clienteTemporal.insertaPedidos(pedidoAgregado);
                                        }
                                        break;
                                    case "3":
                                        if (clienteTemporal.pedidosVacios()) System.out.println("Aun no ha realizado pedidos");
                                        else {
                                            clienteTemporal.pintaPedidoConCliente();
                                        }
                                        break;
                                    case "4":
                                        clienteTemporal.pintaCliente();
                                        break;
                                }
                            } while (!opMenusUsuarios.equals("6"));

                            break;
                        case "trabajador":
                            Trabajador trabajadorTemporal = tienda.loginTrabajador(usuarioTemp,claveTemp);
                            break;
                        case "admin":
                            Admin adminTemporal = tienda.loginAdmin(usuarioTemp,claveTemp);
                            break;
                        case "error":
                            System.out.println("Usuario y/o contraseña introducidos incorrectos");
                            break;
                    }
                    break;
                default:
                    System.out.println("Opción introducida incorrecta.");
                    break;
            }

        } while (true);

    }
}
