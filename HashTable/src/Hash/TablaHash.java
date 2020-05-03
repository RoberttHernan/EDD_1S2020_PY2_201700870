package Hash;

import OtraClases.Estudiante;
import SLL.SLL;

/**
 *
 * @author Robert Hernandez
 */
public class TablaHash {

    private SLL[] elementos;
    private int tamanio;

    public TablaHash(int tamanio) {
        elementos = new SLL[tamanio];
        this.tamanio = tamanio;
    }

    public void insert(Estudiante estudiante) {
        int casilla = funcionHash(estudiante.getCarnet());
        if (casilla <= 45) {
            if (elementos[casilla - 1] == null) {
                elementos[casilla - 1] = new SLL();
            }

            elementos[casilla - 1].insert(estudiante);
        }
    }
    public Estudiante search (Estudiante estudiante){
    int casilla = funcionHash(estudiante.getCarnet());
    }
    static int funcionHash(int n) {
        return (n % 45);
    }

    public SLL[] getElementos() {
        return elementos;
    }

    public void setElementos(SLL[] elementos) {
        this.elementos = elementos;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

}
