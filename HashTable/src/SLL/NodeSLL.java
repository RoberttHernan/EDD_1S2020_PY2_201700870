package SLL;

import OtraClases.Estudiante;

/**
 *
 * @author Robert Hernandez
 */
public class NodeSLL {
    private NodeSLL sig;
    private Estudiante estudiante;
    
    public NodeSLL (){
    this.sig = null;
    this.estudiante = null;
    }
    public NodeSLL (Estudiante estudiante){
    this.estudiante = estudiante;
    }

    public NodeSLL getSig() {
        return sig;
    }

    public void setSig(NodeSLL sig) {
        this.sig = sig;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    
    
}
