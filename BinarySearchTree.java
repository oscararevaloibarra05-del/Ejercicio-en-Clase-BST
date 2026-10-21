
import java.util.ArrayList;

public class BinarySearchTree {

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    /* ---------- INSERCION ---------- */

    public void insertNode(Node newNode) {
        root = insertRecursive(newNode, root);
    }

    public Node insertRecursive(Node nodeToInsert, Node rootNode) {

        if (rootNode == null) {
            return nodeToInsert;
        }

        if (nodeToInsert.getKey() < rootNode.getKey()) {

            rootNode.setLeft(
                insertRecursive(nodeToInsert, rootNode.getLeft())
            );

        } else {

            // Las claves iguales se insertan por la derecha.
            // Asi no se pierden clientes con la misma edad.
            rootNode.setRight(
                insertRecursive(nodeToInsert, rootNode.getRight())
            );
        }

        return rootNode;
    }

    /* ---------- BUSQUEDA ---------- */

    public boolean searchNodeRecursive(int key, Node rootNode) {

        if (rootNode == null) {
            return false;
        }

        if (rootNode.getKey() == key) {
            return true;
        }

        if (key < rootNode.getKey()) {
            return searchNodeRecursive(key, rootNode.getLeft());
        }

        return searchNodeRecursive(key, rootNode.getRight());
    }

    public Node buscarNodo(int key, Node rootNode) {

        if (rootNode == null) {
            return null;
        }

        if (rootNode.getKey() == key) {
            return rootNode;
        }

        if (key < rootNode.getKey()) {
            return buscarNodo(key, rootNode.getLeft());
        }

        return buscarNodo(key, rootNode.getRight());
    }

    /* ---------- RECORRIDOS ---------- */

    public void InOrder(Node root) {

        if (root == null) {
            return;
        }

        InOrder(root.getLeft());
        System.out.println("->" + root.getKey());
        InOrder(root.getRight());
    }

    public void preOrder(Node root) {

        if (root == null) {
            return;
        }

        System.out.println("->" + root.getKey());
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }

    public void posOrder(Node root) {

        if (root == null) {
            return;
        }

        posOrder(root.getLeft());
        posOrder(root.getRight());
        System.out.println("->" + root.getKey());
    }

    /* ---------- PARTE A.2: LISTA DE CLAVES ---------- */

    public ArrayList<Integer> getKeyList() {

        ArrayList<Integer> keyList = new ArrayList<>();
        agregarClavesInOrder(root, keyList);

        return keyList;
    }

    private void agregarClavesInOrder(
            Node nodo, ArrayList<Integer> lista) {

        if (nodo == null) {
            return;
        }

        agregarClavesInOrder(nodo.getLeft(), lista);
        lista.add(nodo.getKey());
        agregarClavesInOrder(nodo.getRight(), lista);
    }

    /* ---------- R1: CLIENTES ASCENDENTES ---------- */

    public ArrayList<Cliente> clientesAscendente() {

        ArrayList<Cliente> lista = new ArrayList<>();
        agregarClientesInOrder(root, lista);

        return lista;
    }

    private void agregarClientesInOrder(
            Node nodo, ArrayList<Cliente> lista) {

        if (nodo == null) {
            return;
        }

        agregarClientesInOrder(nodo.getLeft(), lista);
        lista.add((Cliente) nodo.getValue());
        agregarClientesInOrder(nodo.getRight(), lista);
    }

    /* ---------- R2: MAYORES N COMPRADORES ---------- */

    public ArrayList<Cliente> mayoresN(int n) {

        ArrayList<Cliente> lista = new ArrayList<>();

        if (n <= 0) {
            return lista;
        }

        agregarMayoresN(root, n, lista);

        return lista;
    }

    private void agregarMayoresN(
            Node nodo, int n, ArrayList<Cliente> lista) {

        if (nodo == null || lista.size() >= n) {
            return;
        }

        // Primero se visitan las claves mayores.
        agregarMayoresN(nodo.getRight(), n, lista);

        // Detenerse si ya se encontraron suficientes clientes.
        if (lista.size() >= n) {
            return;
        }

        lista.add((Cliente) nodo.getValue());

        if (lista.size() >= n) {
            return;
        }

        agregarMayoresN(nodo.getLeft(), n, lista);
    }

    /* ---------- R3: CLIENTES EN UN RANGO ---------- */

    public ArrayList<Cliente> clientesEnRango(int min, int max) {

        ArrayList<Cliente> lista = new ArrayList<>();

        if (min > max) {
            return lista;
        }

        agregarClientesEnRango(root, min, max, lista);

        return lista;
    }

    private void agregarClientesEnRango(
            Node nodo, int min, int max,
            ArrayList<Cliente> lista) {

        if (nodo == null) {
            return;
        }

        // La izquierda solo puede contener claves menores.
        // Si la clave actual es min o menor, esa rama se poda.
        if (nodo.getKey() > min) {
            agregarClientesEnRango(
                nodo.getLeft(), min, max, lista
            );
        }

        // El rango incluye ambos extremos.
        if (nodo.getKey() >= min && nodo.getKey() <= max) {
            lista.add((Cliente) nodo.getValue());
        }

        // La derecha contiene claves iguales o mayores.
        // Si la clave actual supera max, esa rama se poda.
        if (nodo.getKey() <= max) {
            agregarClientesEnRango(
                nodo.getRight(), min, max, lista
            );
        }
    }

    /* ---------- R5: MINIMO Y MAXIMO ---------- */

    public Cliente minimo() {

        if (root == null) {
            return null;
        }

        Node actual = root;

        while (actual.getLeft() != null) {
            actual = actual.getLeft();
        }

        return (Cliente) actual.getValue();
    }

    public Cliente maximo() {

        if (root == null) {
            return null;
        }

        Node actual = root;

        while (actual.getRight() != null) {
            actual = actual.getRight();
        }

        return (Cliente) actual.getValue();
    }

    /* ---------- PARTE D: ALTURA ---------- */

    public int altura(Node rootNode) {

        if (rootNode == null) {
            return -1;
        }

        int alturaIzquierda = altura(rootNode.getLeft());
        int alturaDerecha = altura(rootNode.getRight());

        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }
}
