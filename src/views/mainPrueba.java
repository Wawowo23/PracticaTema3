package views;

import data.Catalogo;
import models.Cliente;
import models.Pedido;

import java.util.Scanner;

public class mainPrueba {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        Cliente c1 = new Cliente("Miguel Angel","amai@gmail.com","1234","aqui",688569596,"Torredonjimeno","Jaen");

        Pedido temp = new Pedido();
        temp.insertaProducto(Catalogo.libroAkira,12);
        c1.insertaPedidos(temp);

        System.out.println(c1.pintaPedidoConCliente());
    }
}
