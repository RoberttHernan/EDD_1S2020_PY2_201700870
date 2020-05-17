package Estructuras;

import PaquetesEnvio.Estudiante;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
            if (elementos[i] == null) {
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

    public void Eliminar(int carnet) {
        int casilla = funcionHash(carnet);

        elementos[casilla - 1].Borrar(carnet);

    }

    private String getCodigoInterno() {
        String texto = "";
        texto += "digraph { \n"
                + " node [shape = rectangle]; \n"
                + " rankdir=TB;\n";

        for (int i = 0; i < 45; i++) {
            texto += "node" + i + "[label= \"" + i + "\"];\n";
        }

        for (int i = 0; i < 45; i++) {
            if (elementos[i] != null) {
                texto += elementos[i].textoGraphviz1() + "\n";
            }
        }

        for (int i = 0; i < 45; i++) {
            if (elementos[i] != null) {
                texto += "node" + i+"->" + elementos[i].textoGraphviz2() + "\n";
            }
        }

        texto += "}";

        return texto;

    }

    public void graficar(String path) {
        FileWriter fichero = null;
        PrintWriter escritor;

        try {
            fichero = new FileWriter("graficas//Tabla_Grafico.dot");
            escritor = new PrintWriter(fichero);
            escritor.print(getCodigoInterno());
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo Tabla_Grafico.dot");
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                System.err.println("Error al cerrar el archivo Tabla_Grafico.dot");
            }
        }
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("dot -Tjpg -o " + path + "Tabla_Grafico.dot");
          //Esperamos medio segundo para dar tiempo a que la imagen se genere.
            //Para que no sucedan errores en caso de que se decidan graficar varios
            //árboles sucesivamente.
            Thread.sleep(500);
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo Tabla_Grafico.dot");
        }
    }

}
