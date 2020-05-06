package Main;

import Estructuras.ArbolAvl;
import Estructuras.BTree;
import Estructuras.TablaHash;
import Otras_Clases.Estudiante;
import Otras_Clases.Libro;
import Vistas.VentanaPrincipal;
import java.io.FileWriter;

/**
 *
 * @author Robert Hernandez
 * Proyecto 2 Estructura de datos
 */
public class main {

    public static void main(String[] args) {
        /*Estudiante estudiante1 = new Estudiante(201700870, "Roberto", "Hernandez", "Sistemas", "1456");
        Estudiante estudiante2 = new Estudiante(201700914, "Enrique", "Calvillo", "Civil", "14871");
        Estudiante estudiante3 = new Estudiante(201704512, "Ericka", "Rivera", "Civil", "144781");
        Estudiante estudiante4 = new Estudiante(201700825, "Danni", "Phantom", "Sistemas", "14756");
        
        TablaHash tablaH = new TablaHash(45);
        tablaH.insert(estudiante1);
        tablaH.insert(estudiante2);
        tablaH.insert(estudiante3);
        tablaH.insert(estudiante4);
        tablaH.print();*/
        VentanaPrincipal ventanaPrincpal = new VentanaPrincipal();
        ventanaPrincpal.setVisible(true);
        ventanaPrincpal.setLocationRelativeTo(null);
        

        
        
        
        

    }

    static void doDot(String pInput, String pOutput) {
        try {

            String dotPath
                    = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";

            String fileInputPath = pInput;
            String fileOutputPath = pOutput;

            String tParam = "-Tjpg";
            String tOParam = "-o";

            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;

            Runtime rt = Runtime.getRuntime();

            rt.exec(cmd);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
        try {

            String[] cmd = new String[4];
            cmd[0] = "cmd";
            cmd[1] = "/C";
            cmd[2] = "start";
            cmd[3] = pOutput;

            Runtime rt = Runtime.getRuntime();

            rt.exec(cmd);

        } catch (Exception e) {
        }
    }

}
