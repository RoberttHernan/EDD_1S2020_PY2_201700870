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
        while (i < n && (k.getIsbn() > keys[i].getIsbn())) {
            i++;
        }

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
                z.getC()[j] = y.getC()[j + t];
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

    static String aux;

    private String retorna() {

        int i;
        for (i = 0; i < n; i++) {
            if (leaf == false) {
                C[i].retorna();
            }
            aux += keys[i] + "\n";
        }
        if (leaf == false) {
            C[i].retorna();
        }
        return aux;
    }

    public String retornaTexto() {
        aux = "";
        return retorna();
    }

    public boolean searchISBN(int isbn) {
        int i = 0;
        while (i < n && (isbn > keys[i].getIsbn())) {
            i++;
        }
        if (keys[i].getIsbn() == isbn) {
            return true;
        }

        if (leaf == true) {
            return false;
        }

        return C[i].searchISBN(isbn);
    }

    public int FindKey(int isbn) {
        int idx = 0;
        while (idx < n && keys[idx].getIsbn() < isbn) {
            idx++;
        }
        return idx;
    }

    public void remove(int isbn) {
        int idx = FindKey(isbn);

        if (idx < n && keys[idx].getIsbn() == isbn) {
            if (leaf) {
                removeFromLeaf(idx);
            } else {
                removeFromNonLeaf(idx);
            }
        } else {
            // If this node is a leaf node, then the key is not present in tree 
            if (leaf) {
                System.out.println("No existe el libro");
                return;
            }
            boolean flag = ((idx == n)); // redundante

            if (C[idx].getN() < t) {
                Fill(idx);
            }
            
            if (flag && idx > n) 
            C[idx-1].remove(isbn); 
        else
            C[idx].remove(isbn); 
        }
    }

    private void removeFromLeaf(int idx) {
        for (int i = idx + 1; i < n; i++) {
            keys[i - 1] = keys[i];
        }
        n--;
        return;
    }

    private void removeFromNonLeaf(int idx) {
        int k = keys[idx].getIsbn();

        // If the child that precedes k (C[idx]) has atleast t keys, 
        // find the predecessor 'pred' of k in the subtree rooted at 
        // C[idx]. Replace k by pred. Recursively delete pred 
        // in C[idx] 
        if (C[idx].getN() >= t) {
            Libro pred = getPred(idx);
            keys[idx] = pred;
            C[idx].remove(pred.getIsbn());
        } // If the child C[idx] has less that t keys, examine C[idx+1]. 
        // If C[idx+1] has atleast t keys, find the successor 'succ' of k in 
        // the subtree rooted at C[idx+1] 
        // Replace k by succ 
        // Recursively delete succ in C[idx+1] 
        else if (C[idx + 1].getN() >= t) {
            Libro succ = getSucc(idx);
            keys[idx] = succ;
            C[idx + 1].remove(succ.getIsbn());
        } // If both C[idx] and C[idx+1] has less that t keys,merge k and all of C[idx+1] 
        // into C[idx] 
        // Now C[idx] contains 2t-1 keys 
        // Free C[idx+1] and recursively delete k from C[idx] 
        else {
            merge(idx);
            C[idx].remove(k);
        }

    }

    private Libro getPred(int idx) {
        // Keep moving to the right most node until we reach a leaf 
        BTreeNode cur = C[idx];
        while (!cur.leaf) {
            cur = cur.getC()[cur.getN()];
        }

        return cur.getKeys()[cur.getN() - 1];
    }

    private Libro getSucc(int idx) {
        // Keep moving the left most node starting from C[idx+1] until we reach a leaf 
        BTreeNode cur = C[idx + 1];
        while (!cur.leaf) {
            cur = cur.getC()[0];
        }
        return cur.getKeys()[0];
    }

    private void Fill(int idx) {
        // If the previous child(C[idx-1]) has more than t-1 keys, borrow a key 
        // from that child 
        if (idx != 0 && C[idx - 1].getN() >= t) {
            BorrowFromPrev(idx);
        } else if (idx != n && C[idx + 1].getN() >= t) {
            BorrowFromNext(idx);
        } // Merge C[idx] with its sibling 
        // If C[idx] is the last child, merge it with with its previous sibling 
        // Otherwise merge it with its next sibling 
        else {
            if (idx != n) {
                merge(idx);
            } else {
                merge(idx - 1);
            }
        }
    }

    private void BorrowFromPrev(int idx) {
        BTreeNode child = C[idx];
        BTreeNode sibling = C[idx - 1];

        // The last key from C[idx-1] goes up to the parent and key[idx-1] 
        // from parent is inserted as the first key in C[idx]. Thus, the  loses 
        // sibling one key and child gains one key 
        // Moving all key in C[idx] one step ahead
        for (int i = child.getN() - 1; i >= 0; --i) {
            child.getKeys()[i + 1] = child.getKeys()[i];
        }

        // If C[idx] is not a leaf, move all its child pointers one step ahead 
        if (!child.leaf) {
            for (int i = child.getN(); i >= 0; --i) {
                child.getC()[i + 1] = child.getC()[i];
            }
        }
        // Setting child's first key equal to keys[idx-1] from the current node 
        child.getKeys()[0] = keys[idx - 1];

        // Moving sibling's last child as C[idx]'s first child 
        if (!child.leaf) {
            child.getC()[0] = sibling.getC()[sibling.getN()];
        }

        // Moving the key from the sibling to the parent 
        // This reduces the number of keys in the sibling 
        keys[idx - 1] = sibling.getKeys()[sibling.getN() - 1];
        child.n += 1;
        sibling.n -= 1;

    }

    private void BorrowFromNext(int idx) {
        BTreeNode child = C[idx];
        BTreeNode sibling = C[idx + 1];

        // keys[idx] is inserted as the last key in C[idx] 
        child.getKeys()[child.getN()] = keys[idx];

        // Sibling's first child is inserted as the last child 
        // into C[idx] 
        if (!child.leaf) {
            child.getC()[child.getN() + 1] = sibling.getC()[0];
        }

        //The first key from sibling is inserted into keys[idx]
        keys[idx] = sibling.getKeys()[0];

        // Moving all keys in sibling one step behind 
        for (int i = 1; i < sibling.getN(); ++i) {
            sibling.getKeys()[i - 1] = sibling.getKeys()[i];
        }

        // Moving all keys in sibling one step behind 
        for (int i = 1; i < sibling.getN(); ++i) {
            sibling.getKeys()[i - 1] = sibling.getKeys()[i];
        }

        // Moving the child pointers one step behind 
        if (!sibling.leaf) {
            for (int i = 1; i <= sibling.getN(); ++i) {
                sibling.getC()[i - 1] = sibling.getC()[i];
            }
        }
        // Increasing and decreasing the key count of C[idx] and C[idx+1] 
        // respectively 
        child.setN(n + 1);
        sibling.setN(n - 1);

    }

    private void merge(int idx) {
        BTreeNode child = C[idx];
        BTreeNode sibling = C[idx + 1];

        child.getKeys()[t - 1] = keys[idx];

        // Copying the keys from C[idx+1] to C[idx] at the end
        for (int i = 0; i < sibling.getN(); ++i) {
            child.getKeys()[i + t] = sibling.getKeys()[i];
        }

        // Copying the child pointers from C[idx+1] to C[idx]
        if (!child.leaf) {
            for (int i = 0; i < sibling.getN(); i++) {
                child.getC()[i + t] = sibling.getC()[i];
            }
        }
        // Moving all keys after idx in the current node one step before - 
        // to fill the gap created by moving keys[idx] to C[idx]

        for (int i = idx + 1; i < n; i++) {
            keys[i - 1] = keys[i];

        }

        // Moving the child pointers after (idx+1) in the current node one 
        // step before
        for (int i = idx + 2; i <= n; i++) {
            C[i - 1] = C[i];
        }
        child.setN(child.getN() + sibling.getN() + 1);
        n--;

        return;

    }

    
    
}
