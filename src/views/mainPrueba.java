package views;

import data.Catalogo;
import models.Pedido;

public class mainPrueba {
    public static void main(String[] args) {

        System.out.println(Catalogo.libroAkira.pintaProducto());

        Pedido p1 = new Pedido();


        System.out.println(p1);
        p1.insertaProducto(Catalogo.libroAkira,12);
        System.out.println(p1);
        System.out.println(p1.pintaPedidoParaCliente());

        System.out.println(Catalogo.libroAkira.pintaProducto());

        System.out.println(((p1.insertaProducto(Catalogo.libroAkira,12) ?
                "Producto añadido correctamente"
                : "Ha habido un error al añadir el producto")));

        System.out.println(Catalogo.libroAkira.pintaProducto());


    }
}
