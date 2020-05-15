package Estructuras;

import PaquetesEnvio.Estudiante;

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
        if (buscar(estudiante.getCarnet()) != null) {
            System.out.println("Carnet: " + estudiante.getCarnet() + " Ya registrado");
        } else {
            int casilla = funcionHash(estudiante.getCarnet());
            if (casilla <= 45) {
                if (elementos[casilla - 1] == null) {
                    elementos[casilla - 1] = new SLL();
                }

                elementos[casilla - 1].insert(estudiante);
            }
        }
    }

    static int funcionHash(int n) {
        return (n % 45);
    }

    public Estudiante buscar(int carnet) {
        int casillas = funcionHash(carnet);
        if (elementos[casillas - 1] == null) {
            return null;
        } else {
            return elementos[casillas - 1].buscar(carnet);
        }

    }

    public void print() {
        int i = 0;
        while (i <= elementos.length - 1) {
            if (elementos[i]== null) {
                i++;
            } else {
                
               System.out.println("Casilla" + i);
                elementos[i].print();
                i++;
            }
        }

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

    public void EditarEstudiante(Estudiante auxiliar) {
        Estudiante aux = buscar(auxiliar.getCarnet());

        if (aux != null) {
            aux.setNombre(auxiliar.getNombre());
            aux.setApellido(auxiliar.getApellido());
            aux.setCarrera(auxiliar.getCarrera());
            aux.setPassword(auxiliar.getPassword());
        }

    }

    public void Eliminar (int carnet){
        int casilla = funcionHash(carnet);
        
        
        elementos[casilla-1].Borrar(carnet);
    
    }
}
