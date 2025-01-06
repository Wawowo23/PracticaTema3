package data;

import models.Producto;

// Aquí tenemos creados los distintos productos que están en venta
public class Catalogo {

    public static Producto libroRecetas = new Producto("Libro de Recetas",5.75f,15);
    public static Producto libroBricolaje = new Producto("Libro de bricolaje",6.50f,17);
    public static Producto libroInformatica = new Producto("Libro de informática",8.90f,10);
    public static Producto libroHarryPotter = new Producto("Harry Potter y la piedra filosofal",15.00f,40);
    public static Producto libroGeronimoStilton = new Producto("Gerónimo Stilton en el Reino de la Fantasía",12.15f,30);
    public static Producto libroSeniorAnillos = new Producto("El Señor de los Anillos: El Retorno del Rey",18.00f,30);
    public static Producto libroCorazonNavidad = new Producto("Un Corazón por Navidad",16.95f,20);
    public static Producto libroAkira = new Producto("Akira: Vol I",19.00f,13);

    // Metodo para pintar el catálogo completo
    public static void pintaCatalogo () {
        System.out.printf("""
                1.- %s
                2.- %s
                3.- %s
                4.- %s
                5.- %s
                6.- %s
                7.- %s
                8.- %s
                """,libroRecetas.pintaProductoCatalogo(),libroBricolaje.pintaProductoCatalogo()
                ,libroInformatica.pintaProductoCatalogo(),libroHarryPotter.pintaProductoCatalogo(),
                libroGeronimoStilton.pintaProductoCatalogo(),libroSeniorAnillos.pintaProductoCatalogo(),
                libroCorazonNavidad.pintaProductoCatalogo(),libroAkira.pintaProductoCatalogo());
    }
}
