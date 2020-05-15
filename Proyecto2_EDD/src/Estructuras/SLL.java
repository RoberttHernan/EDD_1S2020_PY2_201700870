package Estructuras;

import PaquetesEnvio.Estudiante;
import PaquetesEnvio.PaqueteUsuario;

/**
 *
 * @author Robert Hernandez
 */
public class SLL {

    private NodeSLL root;

    public SLL() {
        this.root = null;
    }

    public void insert(Estudiante estudiante) {
        NodeSLL nuevo = new NodeSLL(estudiante);

        if (root == null) {
            root = nuevo;

        } else {
            nuevo.setSig(root);
            root = nuevo;
        }

    }

    public Estudiante buscar(int carnet) {
        NodeSLL aux = root;
        if (!isEmpty()) {
            while (aux != null) {
                if (aux.getEstudiante().getCarnet() == carnet) {
                    return aux.getEstudiante();
                }
                aux = aux.getSig();
            }
        }
        return null;
    }

    public boolean buscarBol(int carnet) {
        NodeSLL aux = root;
        if (isEmpty()) {
            return false;
        } else {
            while (aux != null) {
                if (aux.getEstudiante().getCarnet() == carnet) {
                    return true;
                }
                aux = aux.getSig();
            }

        }
        return false;
    }

    public void print() {
        if (root == null) {
            //System.out.println("La lista esta vacia");
            return;
        }

        NodeSLL aux = root;

        while (aux != null) {
            System.out.println(aux.getEstudiante());
            aux = aux.getSig();
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void Borrar(int carnet) {
        NodeSLL aux = root;

        if (root.getEstudiante().getCarnet() == carnet) {
            if (root.getSig() == null) {
                root = null;
            } else {
                root = root.getSig();
                aux.setSig(null);

            }

        } else {
            while (aux != null) {
                if (aux.getSig().getEstudiante().getCarnet() == carnet) {
                    if (aux.getSig().getSig() == null) {
                        aux.setSig(null);
                    } else {
                        aux.setSig(aux.getSig());
                        aux.getSig().setSig(null);
                    }

                }
                aux = aux.getSig();
            }
        }
    }
}
