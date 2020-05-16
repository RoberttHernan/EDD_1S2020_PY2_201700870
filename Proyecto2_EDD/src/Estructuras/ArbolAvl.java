package Estructuras;

/**
 * Clase que representa un nodo AVL
 *
 * @author Roberto Hernandez
 */
public class ArbolAvl {

    private NodoAvl raiz;
    boolean opcion = false;

    int altura(NodoAvl N) {
        if (N == null) {
            return 0;

        }
        return N.getAltura();

    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    NodoAvl miValueNode(NodoAvl nodo) {
        NodoAvl current = nodo;
        while (current.getIzquierda() != null) {
            current = current.getIzquierda();
        }
        return current;
    }

    NodoAvl RightRotate(NodoAvl y) {
        NodoAvl x = y.getIzquierda();
        NodoAvl t2 = x.getDerecha();

        x.setDerecha(y);
        y.setIzquierda(t2);

        y.setAltura(max(altura(y.getIzquierda()), altura(y.getDerecha())) + 1);
        x.setAltura(max(altura(x.getIzquierda()), altura(x.getDerecha())) + 1);

        return x;
    }

    NodoAvl LeftRotate(NodoAvl x) {

        NodoAvl y = x.getDerecha();
        NodoAvl t2 = y.getIzquierda();

        y.setIzquierda(x);
        x.setDerecha(t2);

        x.setAltura(max(altura(x.getIzquierda()), altura(x.getDerecha())) + 1);
        y.setAltura(max(altura(y.getIzquierda()), altura(y.getDerecha())) + 1);

        return y;
    }

    int getBalance(NodoAvl N) {
        if (N == null) {
            return 0;

        }
        return altura(N.getIzquierda()) - altura(N.getDerecha());

    }

   private NodoAvl insert(NodoAvl nodo, String clave) {
        if (nodo == null) {
            return (new NodoAvl(clave));

        }
        if (clave.compareTo(nodo.getCategoria()) < 0) {
            nodo.setIzquierda(insert(nodo.getIzquierda(), clave));
        } else if (clave.compareTo(nodo.getCategoria()) > 0) {
            nodo.setDerecha(insert(nodo.getDerecha(), clave));
        } else {
            return nodo;
        }

        /*2*/
        nodo.setAltura(1 + max(altura(nodo.getIzquierda()), altura(nodo.getDerecha())));

        //3
        int balance = getBalance(nodo);

        // si esta desbalanceado hay 4 caso
        //Left Left case
        if (balance > 1 && clave.compareTo(nodo.getIzquierda().getCategoria()) < 0) {
            return RightRotate(nodo);
        }
        //Right right case
        if (balance < -1 && clave.compareTo(nodo.getDerecha().getCategoria()) > 0) {
            return LeftRotate(nodo);
        }

        //left right case 
        if (balance > 1 && clave.compareTo(nodo.getIzquierda().getCategoria()) > 0) {
            nodo.setIzquierda(LeftRotate(nodo.getIzquierda()));
            return RightRotate(nodo);
        }
        //Right left case
        if (balance < -1 && clave.compareTo(nodo.getDerecha().getCategoria()) < 0) {
            nodo.setDerecha(RightRotate(nodo.getDerecha()));
            return LeftRotate(nodo);
        }
        return nodo;

    }

    public NodoAvl borrar(NodoAvl raiz, String categoria) {
        if (raiz == null) {
            return raiz;
        }

        if (categoria.compareTo(raiz.getCategoria()) < 0) {
            raiz.setIzquierda(borrar(raiz.getIzquierda(), categoria));
        } else if (categoria.compareTo(raiz.getCategoria()) > 0) {
            raiz.setDerecha(borrar(raiz.getDerecha(), categoria));
        } else {
            if (raiz.getDerecha() == null || raiz.getIzquierda() == null) {
                NodoAvl temp = null;
                if (temp == raiz.getIzquierda()) {
                    temp = raiz.getDerecha();
                } else {
                    temp = raiz.getIzquierda();
                }

                if (temp == null) {
                    temp = raiz;
                    raiz = null;

                } else {
                    raiz = temp;
                }

            } else {
                NodoAvl temp = miValueNode(raiz.getDerecha());
                raiz.setCategoria(temp.getCategoria());

                raiz.setDerecha(borrar(raiz.getDerecha(), temp.getCategoria()));
            }
        }
        if (raiz == null) {
            return raiz;
        }
        raiz.setAltura(1 + max(altura(raiz.getIzquierda()), altura(raiz.getDerecha())));
        int balance = getBalance(raiz);

        //left left case
        if (balance > 1 && getBalance(raiz.getIzquierda()) >= 0) {
            return RightRotate(raiz);
        }
        //left right case
        if (balance > 1 && getBalance(raiz.getIzquierda()) < 0) {
            raiz.setIzquierda(LeftRotate(raiz.getIzquierda()));
            return RightRotate(raiz);
        }
        //Right Right case
        if (balance < -1 && getBalance(raiz.getDerecha()) <= 0) {
            return LeftRotate(raiz);
        }
        //Right left case
        if (balance < -1 && getBalance(raiz.getDerecha()) > 0) {
            raiz.setDerecha(RightRotate(raiz.getDerecha()));
            return LeftRotate(raiz);
        }
        return raiz;
    }

    public void preOrder(NodoAvl nodo) {
        if (nodo != null) {
            System.out.println(nodo.getCategoria());
            preOrder(nodo.getIzquierda());
            preOrder(nodo.getDerecha());

        }
    }

    public NodoAvl getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoAvl raiz) {
        this.raiz = raiz;
    }

    public void graficar(String path) {
        raiz.graficar(path);
    }

    public boolean EstaVacio() {
        return raiz == null;
    }

    private NodoAvl buscar(NodoAvl nodo, String Categoria) {
        if (nodo == null) {
            return null;
        }
        if (Categoria.equals(nodo.getCategoria())) {
            return nodo;
        }

        return Categoria.compareTo(nodo.getCategoria()) < 0
                ? buscar(nodo.getIzquierda(), Categoria)
                : buscar(nodo.getDerecha(), Categoria);

    }

    public NodoAvl search(String categoria) {
        return buscar(raiz, categoria);
    }

    public void Add(String categoria) {
        raiz = insert(raiz, categoria);
    }

}
