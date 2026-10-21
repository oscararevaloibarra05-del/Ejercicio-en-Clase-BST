
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Cliente[] clientes = {
            new Cliente("1001", 34, "Ana Torres",     850000f),
            new Cliente("1002", 28, "Luis Prado",     1200000f),
            new Cliente("1003", 45, "Marta Ríos",     430000f),
            new Cliente("1004", 28, "Camilo Vélez",   990000f),   // misma edad que Luis
            new Cliente("1005", 52, "Sofía Mejía",    2100000f),
            new Cliente("1006", 31, "Julián Ortiz",   670000f),
            new Cliente("1007", 23, "Valentina Cruz", 310000f),
            new Cliente("1008", 39, "Andrés Lozano",  1750000f),
            new Cliente("1009", 34, "Paola Gil",      540000f)    // misma edad que Ana
        };

        BinarySearchTree arbolCompras = new BinarySearchTree();
        BinarySearchTree arbolEdad = new BinarySearchTree();

        // Creación e inserción
        for (Cliente c : clientes) {
            // TODO 1: cree un nodo con clave = (int) c.getTotal_compras() y valor = c,
            //         e insértelo en arbolCompras.

            Node nodoCompras = new Node(
                (int) c.getTotal_compras(), c
            );
            arbolCompras.insertNode(nodoCompras);

            // TODO 2: cree un nodo con clave = c.getEdad() y valor = c,
            //         e insértelo en arbolEdad.

            Node nodoEdad = new Node(c.getEdad(), c);
            arbolEdad.insertNode(nodoEdad);
        }

        // Verificación de la construcción
        System.out.println("Clientes registrados: " + clientes.length);
        System.out.println("Nodos en arbolCompras: " + arbolCompras.getKeyList().size());
        System.out.println("Nodos en arbolEdad: " + arbolEdad.getKeyList().size());
        // ¿Coinciden? Si no, vea la Parte C.

        // Comprobación adicional de las claves ordenadas
        System.out.println("Claves de compras: " + arbolCompras.getKeyList());
        System.out.println("Claves de edad: " + arbolEdad.getKeyList());

        // R1: listado ascendente por compras
        System.out.println("\n== R1: clientes por compras (ascendente) ==");
        // TODO: imprima los clientes de arbolCompras.clientesAscendente()

        ArrayList<Cliente> clientesAscendentes =
            arbolCompras.clientesAscendente();

        for (Cliente c : clientesAscendentes) {
            System.out.println(c);
        }

        // R2: tres mayores compradores
        System.out.println("\n== R2: Top 3 compradores ==");
        // TODO: imprima los clientes de arbolCompras.mayoresN(3)

        ArrayList<Cliente> top3 = arbolCompras.mayoresN(3);

        for (Cliente c : top3) {
            System.out.println(c);
        }

        // R3: clientes entre 25 y 35 años
        System.out.println("\n== R3: clientes entre 25 y 35 años ==");
        // TODO: imprima los clientes de arbolEdad.clientesEnRango(25, 35)

        ArrayList<Cliente> clientesEnRango =
            arbolEdad.clientesEnRango(25, 35);

        for (Cliente c : clientesEnRango) {
            System.out.println(c);
        }

        // R4: búsqueda exacta por total de compras
        System.out.println("\n== R4: búsqueda por total de compras ==");
        int buscado = 990000;
        // TODO: use buscarNodo; si existe, imprima el cliente; si no, informe que no existe.

        Node encontrado = arbolCompras.buscarNodo(
            buscado, arbolCompras.getRoot()
        );

        if (encontrado != null) {
            Cliente clienteEncontrado =
                (Cliente) encontrado.getValue();

            System.out.println("Cliente encontrado:");
            System.out.println(clienteEncontrado);
        } else {
            System.out.println(
                "No existe un cliente con compras de $" + buscado
            );
        }

        // R5: extremos por edad
        System.out.println("\n== R5: más joven y mayor ==");
        // TODO: imprima arbolEdad.minimo() y arbolEdad.maximo()

        Cliente masJoven = arbolEdad.minimo();
        Cliente mayorEdad = arbolEdad.maximo();

        if (masJoven != null) {
            System.out.println("Cliente más joven:");
            System.out.println(masJoven);
        } else {
            System.out.println("No hay clientes en el árbol de edades.");
        }

        if (mayorEdad != null) {
            System.out.println("Cliente de mayor edad:");
            System.out.println(mayorEdad);
        } else {
            System.out.println("No hay clientes en el árbol de edades.");
        }

        // Prueba adicional para la Parte D: altura del árbol de edades
        System.out.println("\n== Parte D: altura del árbol de edades ==");
        System.out.println(
            "Altura: " + arbolEdad.altura(arbolEdad.getRoot())
        );
    }
}
