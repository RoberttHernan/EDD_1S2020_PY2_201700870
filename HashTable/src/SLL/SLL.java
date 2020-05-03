package SLL;

import OtraClases.Estudiante;

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

    public void print() {
        if (root == null) {
            System.out.println("La lista esta vacia");
            return;
        }

        NodeSLL aux = root;

        while (aux != null) {
            System.out.println(aux.getEstudiante().getNombre());
            aux = aux.getSig();
        }
    }

    public boolean isEmpty() {
        return root == null;
    }
}
