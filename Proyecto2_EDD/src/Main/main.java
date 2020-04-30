package Main;

import Estructuras.ArbolAvl;
import Estructuras.BTree;
import Otras_Clases.Libro;
import java.io.FileWriter;

/**
 *
 * @author Robert Hernandez
 * Proyecto 2 Estructura de datos
 */
public class main {

    public static void main(String[] args) {
       /* Libro lib1 = new Libro(215, "Harry potter", 201700870);
        Libro lib2 = new Libro(300, "The hunger games", 201708890);
        Libro lib3 = new Libro(150, "Java para dummies", 201708890);

        BTree arbol = new BTree((3));

        arbol.insert(lib1);
        arbol.insert(lib2);
        arbol.insert(lib3);

        try {

            FileWriter f = new FileWriter("grafo1.txt");

            f.write(arbol.toDot());

            f.close();

        } catch (Exception e) {
        }
        doDot("grafo1.txt", "grafo1.jpg");*/
        
        ArbolAvl avlTree = new ArbolAvl();
        avlTree.insertar("Robert");
        avlTree.insertar("Henry");
        avlTree.insertar("Danie");
        avlTree.insertar("Robert");
        avlTree.graficar("graficas//arbol.jpg");
        doDot("graficas//aux_grafico.dot", "graficas//arbol.jpg");
        avlTree.inorden();

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
