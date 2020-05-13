package Estructuras;

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

    public NodoAvl(String categoria) {
        this.valor = new BTree(2);
        this.altura = 1;
        this.categoria = categoria;
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
            etiqueta = "nodo" + hashCode() + " [ label =\"" + categoria + "\"];\n";
        } else {
            etiqueta = "nodo" + hashCode() + " [ label =\"<C0>|" + categoria + "|<C1>\"];\n";
        }
        if(izquierda!=null){
            etiqueta+= izquierda.getCodigoInterno() +
               "nodo"+hashCode()+":C0->nodo"+izquierda.hashCode()+"\n";
        }
        if(derecha!=null){
            etiqueta=etiqueta + derecha.getCodigoInterno() +
               "nodo"+hashCode()+":C1->nodo"+derecha.hashCode()+"\n";                    
        }
        return etiqueta;
    }
    
    public void graficar(String path){
    FileWriter fichero = null;
        PrintWriter escritor;
        
          try
        {
            fichero = new FileWriter("graficas//Avl_grafico.dot");
            escritor = new PrintWriter(fichero);
            escritor.print(getCodigoGraphviz());
        } catch (Exception e){
            System.err.println("Error al escribir el archivo aux_grafico.dot");
        }finally{
           try {
                if (null != fichero)
                    fichero.close();
           }catch (Exception e2){
               System.err.println("Error al cerrar el archivo Avl_grafico.dot");
           } 
        } try{
          Runtime rt = Runtime.getRuntime();
          rt.exec( "dot -Tjpg -o "+path+" Avl_grafico.dot");
          //Esperamos medio segundo para dar tiempo a que la imagen se genere.
          //Para que no sucedan errores en caso de que se decidan graficar varios
          //árboles sucesivamente.
          Thread.sleep(500);
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo Avl_grafico.dot");
        }       
    
    }

}
