package Estructuras;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Clase generica que representa un nodo de un arbol AVL
 *
 * @author Robert Hernandez
 */
public class NodoAvl {

    /**
     * Variable que almacena el valor específico del nodo.
     */
    final Comparable valor;

    /**
     * Variable que apunta hacia el nodo derecho de este nodo.
     */
    private NodoAvl der;
    /**
     * Variable que apunta hacia el nodo izquierdo de este nodo.
     */
    private NodoAvl izq;
    /**
     * Variable que se utiliza para poder balancear el árbol cuando sea
     * necesario
     */
    private int altura;

    /*
     * Variable privada con la que lleva el control de un correlativo  que se le 
     * asignará a cada nodo que es creado, este será único para cada nodo y 
     * servirá para hacer la gráfica del árbol con graphviz.
     */
    private static int correlativo = 1;
    /**
     * Constante privada que posee cada nodo y es única, funciona como
     * identificador y será útil para hacer la gráfica del árbol con graphviz.
     */
    private int id = 1;

    /**
     * Constructor de la clase NodoAvl
     *
     * @param valor Valor específico que el nodo almacenará.
     */
    public NodoAvl(Comparable valor) {
        this.valor = valor;
        this.izq = null;
        this.der = null;
        this.id = correlativo++;
    }

    /**
     * Método que inserta un Nodo en el árbol binario de búsqueda.
     *
     * @param val Valor que se desea insertar.
     */
    void insertar(Comparable val) {
        if (val.compareTo(valor) < 0) {
            if (izq == null) {
                izq = new NodoAvl(val);
            } else {
                izq.insertar(val);
            }
        } else if (val.compareTo(valor) > 0) {
            if (der == null) {
                der = new NodoAvl(val);
            } else {
                der.insertar(val);
            }
        } else {
            System.err.println("No se permiten los valores duplicados: \""
                    + String.valueOf(val) + "\".");
        }
    }
    
    /**
     * Método que genera el gráfico del árbol binario de búsqueda con graphviz,
     * considerando como la raíz de dicho árbol el actual Nodo.
     * @param path Ruta de la imagen que se generará.
     */
    public void graficar(String path) {
        FileWriter fichero = null;
        PrintWriter escritor;
        try
        {
            fichero = new FileWriter("graficas//aux_grafico.dot");
            escritor = new PrintWriter(fichero);
            escritor.print(getCodigoGraphviz());
        } 
        catch (Exception e){
            System.err.println("Error al escribir el archivo aux_grafico.dot");
        }finally{
           try {
                if (null != fichero)
                    fichero.close();
           }catch (Exception e2){
               System.err.println("Error al cerrar el archivo aux_grafico.dot");
           } 
        }                        
        try{
          Runtime rt = Runtime.getRuntime();
          rt.exec( "dot -Tjpg -o "+path+" aux_grafico.dot");
          //Esperamos medio segundo para dar tiempo a que la imagen se genere.
          //Para que no sucedan errores en caso de que se decidan graficar varios
          //árboles sucesivamente.
          Thread.sleep(500);
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo aux_grafico.dot");
        }            
    }
    
    /**
     * Método que retorna el código que grapviz usará para generar la imagen 
     * del árbol binario de búsqueda.
     * @return 
     */
    private String getCodigoGraphviz() {
        return "digraph grafica{\n" +
               "rankdir=TB;\n" +
               "node [shape = record, style=filled, fillcolor=seashell2];\n"+
                getCodigoInterno()+
                "}\n";
    }
    
     /**
     * Genera el código interior de graphviz
     * @return 
     */
    private String getCodigoInterno() {
        String etiqueta;
        if(izq==null && der==null){
            etiqueta="nodo"+id+" [ label =\""+valor+"\"];\n";
        }else{
            etiqueta="nodo"+id+" [ label =\"<C0>|"+valor+"|<C1>\"];\n";
        }
        if(izq!=null){
            etiqueta=etiqueta + izq.getCodigoInterno() +
               "nodo"+id+":C0->nodo"+izq.id+"\n";
        }
        if(der!=null){
            etiqueta=etiqueta + der.getCodigoInterno() +
               "nodo"+id+":C1->nodo"+der.id+"\n";                    
        }
        return etiqueta;
    } 
    
    
    /*
    *Getters y setters
    *
    */

    public NodoAvl getDer() {
        return der;
    }

    public void setDer(NodoAvl der) {
        this.der = der;
    }

    public NodoAvl getIzq() {
        return izq;
    }

    public void setIzq(NodoAvl izq) {
        this.izq = izq;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public static int getCorrelativo() {
        return correlativo;
    }

    public static void setCorrelativo(int correlativo) {
        NodoAvl.correlativo = correlativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
