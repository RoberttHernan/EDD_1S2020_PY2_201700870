package Estructuras;

import PaquetesEnvio.Libro;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Clase que representa el nodo de un arbol AVL
 *
 * @author Robert Hernandez
 */
public class NodoAvl {

    private BTree valor;
    private int altura;
    private String categoria;
    private NodoAvl izquierda, derecha;

    private static int correlativo = 1;
    private final int id;

    public NodoAvl(String categoria) {
        this.valor = new BTree(3);
        this.altura = 1;
        this.categoria = categoria;
        this.id = correlativo++;
    }

    public BTree getValor() {
        return valor;
    }

    public void setValor(BTree valor) {
        this.valor = valor;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public NodoAvl getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoAvl izquierda) {
        this.izquierda = izquierda;
    }

    public NodoAvl getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoAvl derecha) {
        this.derecha = derecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    private String getCodigoGraphviz() {
        return "digraph grafica{\n"
                + "rankdir=TB;\n"
                + "node [shape = record, style=filled, fillcolor=seashell2];\n"
                + getCodigoInterno()
                + "}\n";
    }

    private String getCodigoInterno() {

        String etiqueta;
        if (izquierda == null && derecha == null) {
            etiqueta = "nodo" + id + " [ label =\"" + categoria + valor.getNoClaves() + "\"];\n";
        } else {
            etiqueta = "nodo" + id + " [ label =\"<C0>|" + categoria + valor.getNoClaves() + "|<C1>\"];\n";
        }
        if (izquierda != null) {
            etiqueta += izquierda.getCodigoInterno()
                    + "nodo" + id + ":C0->nodo" + izquierda.id + "\n";
        }
        if (derecha != null) {
            etiqueta += derecha.getCodigoInterno()
                    + "nodo" + id + ":C1->nodo" + derecha.id + "\n";
        }
        return etiqueta;
    }

    public void graficar(String path) {
        FileWriter fichero = null;
        PrintWriter escritor;

        try {
            fichero = new FileWriter("graficas//Avl_grafico.dot");
            escritor = new PrintWriter(fichero);
            escritor.print(getCodigoGraphviz());
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo AVL_grafico.dot");
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                System.err.println("Error al cerrar el archivo Avl_grafico.dot");
            }
        }
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("dot -Tjpg -o " + path + "Avl_grafico.dot");
          //Esperamos medio segundo para dar tiempo a que la imagen se genere.
            //Para que no sucedan errores en caso de que se decidan graficar varios
            //árboles sucesivamente.
            Thread.sleep(500);
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo Avl_grafico.dot");
        }

    }

    public String retornar() {

        String texto = categoria+":\n"+ valor.retorna();
        return texto;

    }
    
    public boolean searchIsbn (int isbn){
     return valor.searchISBN(isbn);
    }
    
    public Libro obtenerLibro (int isbn){
    return valor.searchLibro(isbn);
    }
    
    public String obtenerCoincidencia (String texto ){
    return valor.retornaCoincidencia(texto);
    }
    
    
    
    

}
