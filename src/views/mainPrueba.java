package views;

import data.Catalogo;
import models.Pedido;

public class mainPrueba {
    public static void main(String[] args) {

        System.out.println(Catalogo.libroAkira.pintaProducto());

        Pedido p1 = new Pedido();


        System.out.println(p1);
        p1.insertaProducto(Catalogo.libroAkira);
        System.out.println(p1);
        System.out.println(p1.pintaPedidoParaCliente());

    }
}
