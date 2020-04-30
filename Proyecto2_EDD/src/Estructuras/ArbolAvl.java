
package Estructuras;

/**
 * Clase que representa un nodo AVL
 * @author Roberto Hernandez
 */
public class ArbolAvl {
    private NodoAvl raiz;
    
    public ArbolAvl(){
    this.raiz = null;
    }
     /**
     * Método que genera una imagen del árbol binario de búsqueda en la ruta 
     * que se le indica. 
     * @param path Ruta específica en la que se guardará la imagen generada.
     */
    public void graficar(String path) {
        raiz.graficar(path);
    }
     /**
     * Método que imprime el recorrido inorden del árbol binario de búsqueda.
     */
    public void inorden(){
        System.out.println("Recorrido inorden del árbol binario de búsqueda:");
        inorden(raiz);
        System.out.println();
    }
     /**
     * Método privado que ejecuta la tarea de hacer un recorrido inorden del 
     * árbol binario de búsqueda.
     * @param a Nodo específico que se recorrerá conforme el método se llama 
     * recursivamente.
     */
    private void inorden(NodoAvl a){
        if(a==null)
            return;
        inorden(a.getIzq());
        System.out.print(a.valor+",");
        inorden(a.getDer());
    }
    /**
     * Método que se encarga de insertar un valor en el árbol AVL.
     * @param valor Valor específico que se desea insertar.
     */
    public void insertar(Comparable valor) {
        raiz = insertar(valor,raiz);
    }
    
    /**
     * Método que inserta un nuevo nodo en el árbol.
     * @param valor
     * @param raiz
     * @return 
     */
    private NodoAvl insertar(Comparable valor, NodoAvl raiz){
        //Si en nodo recibido fuera nulo entonces el nuevo nodo se puede insertar 
        //en esa posición y se terminan las llamadas recursivas a este método.
        if(raiz == null){
            raiz = new NodoAvl(valor);
        //Si el nuevo valor fuera menor que el nodo de actual entonces
        }else if(valor.compareTo(raiz.valor) < 0){
            //Se llama recursivamente al método para explorar el subarbol izquierdo
            //porque el valor a insertar es menor que el del nodo actual.
            raiz.setIzq(insertar(valor, raiz.getIzq()));         
            if(altura(raiz.getDer())-altura(raiz.getIzq()) == -2)
                //Si el factor de equilibrio esta desbalanceado, hay que hacer 
                //rotación de nodos, como el fe=-2 hay dos posibilidades de 
                //rotación dependiendo de:
                if(valor.compareTo(raiz.getIzq().valor) < 0)
                    //Si el nuevo valor fuera menor que la izquierda del nodo des-
                    //balanceado, se sabe que el nuevo nodo será insertado a la 
                    //izquierda de la actual izquierda, entonces tenemos una rotación 
                    //simple por la izquierda o sea una IzquierdaIzquierda.
                    raiz = IzquierdaIzquierda(raiz);
                else
                    //de lo contrario, se sabe que el nuevo nodo será insertado 
                    //a la derecha del la actual izquierda, por lo que se tiene 
                    //un caso de rotación doble por la izquierda 
                    //o sea una IzquierdaDerecha.
                    raiz = IzquierdaDerecha(raiz);
        }
        else if(valor.compareTo(raiz.valor)>0)
        //Si el nuevo valor fuera mayor que el nodo de la actual entonces:
        {
            //Se llama recursivamente al método para explorar el subarbol derecho
            //porque el valor a insertar es mayor que el del nodo actual.            
            raiz.setDer(insertar(valor, raiz.getDer()));
            if(altura(raiz.getDer())-altura(raiz.getIzq()) == 2)
                //Si el factor de equilibrio esta desbalanceado, hay que hacer 
                //rotación de nodos, como el fe=2 hay dos posibilidades de 
                //rotación dependiendo de:                
                if(valor.compareTo(raiz.getDer().valor) > 0)
                    //Si el nuevo valor fuera mayor que la derecha del nodo des-
                    //balanceado, se sabe que el nuevo nodo será insertado a la 
                    //derecha de la actual derecha, entonces tenemos una rotación 
                    //simple por la derecha o sea una DerechaDerecha.                    
                    raiz = DerechaDerecha(raiz);
                else
                    //de lo contrario, se sabe que el nuevo nodo será insertado 
                    //a la izquierda del la actual derecha, por lo que se tiene 
                    //un caso de rotación doble por la derecha
                    //o sea una DerechaIzquierda.
                    raiz = DerechaIzquierda(raiz);
        }
        else  
        // De lo contrario signifca que el valor que se quiere insertar ya existe, 
        //como no se permite la duplicidad de este dato no se hace nada.
        System.err.println("No se permiten los valores duplicados: \"" 
                +  String.valueOf(valor)+"\".");        
        
        //finalmente, por cada llamada recursiva debe hacerse una reasignacion 
        //de la altura esta se hará hasta para los nodos que no cambiaron de nivel 
        //en el transcurso porque no hay forma de saber cuales cambiaron de nivel 
        //y cuales no. La altura,será la altura del hijo que tiene
        //la altura más grande, es decir, la rama mas profunda, más 1.
        raiz.setAltura(mayor(altura(raiz.getIzq()), altura(raiz.getDer()))+1); 
        return raiz;
    }
    
     /**
     * Método que devuelve la altura de un nodo.
     * @param nodo
     * @return 
     */
    private int altura( NodoAvl nodo )
    {
        if(nodo==null)
            return -1;
        else
            return nodo.getAltura();
    }
    /**
     * Método que recibe como parámetro dos numeros y devuelve el mayor.
     * @param n1
     * @param n2
     * @return 
     */
    private int mayor(int n1, int n2)
    {
        if(n1 > n2)
            return n1;
        return n2;
    }
    
    /**
     * Rotación simple izquierda izquierda para el proceso de balanceo.
     * @param n1
     * @return 
     */
    private NodoAvl IzquierdaIzquierda(NodoAvl n1){
        NodoAvl n2 = n1.getIzq();
        n1.setIzq(n2.getDer());
        n2.setDer(n1);
        n1.setAltura(mayor(altura(n1.getIzq()), altura(n1.getDer()))+1);
        n2.setAltura(mayor(altura(n2.getIzq()), n1.getAltura())+1);
        return n2;
    }
    
    /**
     * Rotación simple derecha derecha para el proceso de balanceo.
     * @param n1
     * @return 
     */
    private NodoAvl DerechaDerecha( NodoAvl n1 ){
        NodoAvl n2 = n1.getDer();
        n1.setDer(n2.getIzq());
        n2.setIzq(n1);
        n1.setAltura(mayor(altura(n1.getIzq()), altura(n1.getDer()))+1);
        n2.setAltura(mayor(altura(n2.getDer()), n1.getAltura())+1);
        return n2;
    }
    /**
     * Rotación doble izuquierda derecha para el proceso de balanceo.
     * @param n1
     * @return 
     */
    private NodoAvl IzquierdaDerecha(NodoAvl n1){
        n1.setIzq(DerechaDerecha(n1.getIzq()));
        return IzquierdaIzquierda(n1);
    }
    
    /**
     * Rotación doble derecha izquierda para el proceso de balanceo.
     * @param n1
     * @return 
     */
    private NodoAvl DerechaIzquierda(NodoAvl n1 ){
        n1.setDer(IzquierdaIzquierda(n1.getDer())) ;
        return DerechaDerecha(n1);
    }
}
