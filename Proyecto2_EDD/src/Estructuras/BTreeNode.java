package Estructuras;

import PaquetesEnvio.Libro;


/**
 *
 * @author Robert Hernandez Clase que representa el nodo de un arbol -B
 */
public class BTreeNode {

    private Libro[] keys; // array de claves
    private int t; // Grado minimo ( define el numero de llaves )
    private BTreeNode[] C; // Array de hijos
    private int n; // numero actual de llaves
    private boolean leaf; // es verdadero cuando el nodo es un hijo

    public BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        keys = new Libro[2 * t - 1];
        C = new BTreeNode[2 * t];
        n = 0;
    }

    public void traverse() {
        int i;
        for (i = 0; i < n; i++) {
            if (leaf == false) {
                C[i].traverse();
            }
            System.out.println(" " + keys[i]);
        }
        if (leaf == false) {
            C[i].traverse();
        }

    }

    public BTreeNode search(Libro k) {
        int i = 0;
        while (i < n && (k.getIsbn() > keys[i].getIsbn())) 
            i++;
        

        if (keys[i].getIsbn() == k.getIsbn()) {
            return this;
        }

        if (leaf == true) {
            return null;
        }
       
        
         return C[i].search(k);
    }

    public void splitChild(int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(y.getT(), y.isLeaf());
        z.setN(t - 1);

        // copiando las pasadas (t-1) claves de y a z
        for (int j = 0; j < t - 1; j++) {
            z.getKeys()[j] = y.getKeys()[j + t];

        }
        // copiando los pasados t hijos de y a z
        if (y.isLeaf() == false) {
            for (int j = 0; j < t; j++) {
                z.getC()[j] = y.getC()[j+t];
            }
        }
        
        // reducir el numero de claves en y 
        y.setN(t - 1);

        // desde que el nodo va a tener un hijo nuevo, creamos espacio
        //para un nuevo hijo
        for (int j = n; j >= i + 1; j--) {
            C[j + 1] = C[j];
        }

        // enlazando el nuevo en hijo a su nodo
        C[i + 1] = z;

        // una clave de y se va a mover a este nodo
        //encuentra el lugar de esta nueva clave y mueve todos 
        // los nodos mayores un espacion al frente
        for (int j = n - 1; j >= i; j--) {
            keys[j + 1] = keys[j];

        }

        keys[i] = y.getKeys()[t - 1];

        n = n + 1;

    }

    public void insertNonFull(Libro k) {
        int i = n - 1;
        // si el nodo es hoja
        if (leaf == true) {
            //El siguiente loop hara dos cosas
            //a)encontrara el lugar de la nueva llave a insertar
            //b) movera todos las llaves mayores un espacion hacia adelante
            while (i >= 0 && keys[i].getIsbn() > k.getIsbn()) {
                keys[i + 1] = keys[i];
                i--;
            }

            // inserta la nueva clave en el lugar encontrado
            keys[i + 1] = k;
            n = n + 1;

        } else { // si el nodo no es hoja
            // Encuentra el hijo que contendra la nueva llave
            while (i >= 0 && keys[i].getIsbn() > k.getIsbn()) {
                i--;
            }
            if (C[i + 1].getN() == 2 * t - 1) {

                // ve si el hijo esta lleno, entonces lo splitea
                splitChild(i + 1, C[i + 1]);

                // Luego de splitear, la llave media de C[i] va arriba y 
                // C[i] is splitteada en dos. ve cual de las dos va a tener la nueva llave
                if (keys[i + 1].getIsbn() < k.getIsbn()) {
                    i++;
                }
            }
            C[i + 1].insertNonFull(k);
        }
    }
    /*
     *Getters y Setters xd
     *
     */

    public Libro[] getKeys() {
        return keys;
    }

    public void setKeys(Libro[] keys) {
        this.keys = keys;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public BTreeNode[] getC() {
        return C;
    }

    public void setC(BTreeNode[] C) {
        this.C = C;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public String getDotName() {
        return "Nodo" + this.hashCode();
    }

    public String toDot() {

        StringBuilder b = new StringBuilder();

        b.append(getDotName());
        b.append("[label=\"<P0>");
        for (int i = 0; i < n; i++) {
            b.append("|").append(keys[i].getTitulo());
            b.append("|<P").append(i + 1).append(">");
        }

        b.append("\"];\n");

        for (int i = 0; i <= n; i++) {
            if (C[i] != null) {
                b.append(C[i].toDot());
                b.append(getDotName()).append(":P").append(i).append(" -> ").append(C[i].getDotName()).append(";\n");
            }
        }

        return b.toString();

    }
    
    

}
