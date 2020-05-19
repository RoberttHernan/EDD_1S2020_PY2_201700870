package Estructuras;

import PaquetesEnvio.Libro;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase que representa un nodo AVL
 *
 * @author Roberto Hernandez
 */
public class ArbolAvl {

    private NodoAvl raiz;
    boolean opcion = false;
    static int contador_claves;

    static String textoInorden;
    static String nodosPreordenGraphviz;
    static String textoPosOrden;
    int contador_temp = 0;
    

    public ArbolAvl() {
        this.contador_claves = 0;
    }

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
        contador_claves++;
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
        contador_claves--;
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

    static String aux;

    private String textouno(NodoAvl nodo) {
        if (nodo != null) {

            aux += nodo.retornar() + "\n";
            textouno(nodo.getIzquierda());
            textouno(nodo.getDerecha());

        }
        return aux;
    }

    public String retornaLibros() {
        aux = "";
        return textouno(raiz);

    }

    static boolean bandera = false;

    private boolean buscarIS(NodoAvl nodo, int isbn) {

        if (nodo != null) {
            if (nodo.searchIsbn(isbn)) {
                bandera = true;
            }
            buscarIS(nodo.getIzquierda(), isbn);
            buscarIS(nodo.getDerecha(), isbn);

        }
        return bandera;
    }

    public boolean buscarIsbn(int isbn) {
        bandera = false;
        return buscarIS(raiz, isbn);
    }

    static Libro libro = null;

    private Libro obtenerLibroprivate(NodoAvl nodo, int isbn) {
        if (nodo != null) {
            if (nodo.obtenerLibro(isbn) != null) {
                libro = nodo.obtenerLibro(isbn);
            }
            obtenerLibroprivate(nodo.getIzquierda(), isbn);
            obtenerLibroprivate(nodo.getDerecha(), isbn);

        }
        return libro;
    }

    public Libro obtenerLibro(int isbn) {
        return obtenerLibroprivate(raiz, isbn);
    }

    static NodoAvl nodoaux = null;

    private NodoAvl buscarNodo_(NodoAvl nodo, int isbn) {

        if (nodo != null) {
            if (nodo.searchIsbn(isbn)) {
                nodoaux = nodo;
            }
            buscarNodo_(nodo.getIzquierda(), isbn);
            buscarNodo_(nodo.getDerecha(), isbn);

        }
        return nodoaux;
    }

    public NodoAvl buscarNodo(int isbn) {
        nodoaux = null;
        return buscarNodo_(raiz, isbn);
    }

    static String texto;

    private String Coincidencia(NodoAvl nodo, String coincidencia) {
        if (nodo != null) {
            texto += nodo.obtenerCoincidencia(coincidencia);
            Coincidencia(nodo.getIzquierda(), coincidencia);
            Coincidencia(nodo.getDerecha(), coincidencia);
        }
        return texto;
    }

    public String retornarCoincidencia(String coincidencia) {
        texto = "";
        return Coincidencia(raiz, coincidencia);
    }

    private String PreordenGraphviz(NodoAvl nodo) {

        if (nodo != null) {
            if (nodo == raiz) {
                nodosPreordenGraphviz += "\"" + nodo.getCategoria() + "\"";
            } else {
                nodosPreordenGraphviz += "->\"" + nodo.getCategoria() + "\"";
            }
            PreordenGraphviz(nodo.getIzquierda());
            PreordenGraphviz(nodo.getDerecha());
        }
        return nodosPreordenGraphviz;

    }

    public String TextoPreOrdenGraphviz() {
        nodosPreordenGraphviz = "";
        return "digraph g {\n rankdir=LR; \n node [shape = record]\n"
                + PreordenGraphviz(raiz) + "\n}";
    }

    public void graficarPreorden(String pInput, String pOutput) throws IOException {
        FileWriter f = new FileWriter("graficas//graficoPreorden.dot");

        f.write(TextoPreOrdenGraphviz());
        f.close();
        doDot(pInput, pOutput);
    }
//------------------------------------------------------------------------------
    private String inOrdenGraphviz(NodoAvl nodo) {
        int n = contarClaves();
        if (nodo != null) {

            inOrdenGraphviz(nodo.getIzquierda());

            if (contador_temp == n - 1) {
                textoInorden += "\"" + nodo.getCategoria() + "\"";
            } else {
                textoInorden += "\"" + nodo.getCategoria() + "\"->";
                contador_temp++;
            }
            inOrdenGraphviz(nodo.getDerecha());
        }
        contador_temp = 0;
        contador_claves =0;
        return textoInorden;

    }

    public String TextoInOrdenGraphviz() {
        textoInorden = "";
        return "digraph g {\n rankdir=LR; \n node [shape = record]\n"
                + inOrdenGraphviz(raiz) + "\n}";
    }

    public void graficarInorden(String pInput, String pOutput) throws IOException {
        FileWriter f = new FileWriter("graficas//graficoInorden.dot");

        f.write(TextoInOrdenGraphviz());
        f.close();
        doDot(pInput, pOutput);
    }
//------------------------------------------------------------------------------
   
    static void doDot(String pInput, String pOutput) {
        try {

            String dotPath
                    = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";

            String fileInputPath = pInput;
            String fileOutputPath = pOutput;

            String tParam = "-Tjpg";
            String tOParam = "-o";

            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;

            Runtime rt = Runtime.getRuntime();

            rt.exec(cmd);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
        try {

            String[] cmd = new String[4];
            cmd[0] = "cmd";
            cmd[1] = "/C";
            cmd[2] = "start";
            cmd[3] = pOutput;

            Runtime rt = Runtime.getRuntime();

            rt.exec(cmd);

        } catch (Exception e) {
        }
    }

    private int contadorClaves(NodoAvl nodo) {
        if (nodo != null) {
            contador_claves++;
            contadorClaves(nodo.getIzquierda());
            contadorClaves(nodo.getDerecha());
        }
        return contador_claves;

    }

    public int contarClaves() {
        contador_claves = 0;
        return contadorClaves(raiz);
    }

    private String posOrden(NodoAvl nodo) {
        int n = contarClaves();
        if (nodo!=null){
        
            posOrden(nodo.getIzquierda());
            posOrden(nodo.getDerecha());
            
            if (contador_temp== n-1){
            textoPosOrden += "\""+nodo.getCategoria() +"\"";
            }
            else {
            textoPosOrden += "\""+nodo.getCategoria() +"\"->";
            contador_temp++;
            }
        }
        return textoPosOrden;

    }
    
    public String TextoPosOrden (){
    textoPosOrden ="";
     return "digraph g {\n rankdir=LR; \n node [shape = record]\n"
                + posOrden(raiz) + "\n}";
    }
    
    public void graficarPosOrden(String pInput, String pOutput) throws IOException {
        FileWriter f = new FileWriter("graficas//graficoPosorden.dot");

        f.write(TextoPosOrden());
        f.close();
        doDot(pInput, pOutput);
    }
   
}
