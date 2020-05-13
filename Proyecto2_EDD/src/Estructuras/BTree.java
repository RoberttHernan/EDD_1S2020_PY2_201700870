package Estructuras;

import Otras_Clases.Libro;

/**
 *
 * @author Robert Hernandez Clase que modela un arbol-B
 */
public class BTree {

    BTreeNode root;
    int t; // grado minimo

    public BTree(int t) {
        this.root = null;
        this.t = t;
    }

    public void traverse() {
        if (root != null) {
            root.traverse();
        }
    }

    public BTreeNode search(Libro k) {
        if (root == null) {
            return null;
        }
        return root.search(k);
    }

    public void insert(Libro k) {
        if (root == null) {
            root = new BTreeNode(t, true);
            root.getKeys()[0] = k;
            root.setN(1);
        } else {// si el arbol no esta vacio
            //si el arbol esta lleno, entonces el arbol crece en altura
            if (root.getN() == 2 * t - 1) {
                BTreeNode s = new BTreeNode(t, false);
                s.getC()[0] = root;

                //splitea la vieja raiz y mueve una clave a la nueva raiz
                s.splitChild(0, root);

                // la nueva raiz tiene dos hijos ahora, decide cual de los dos hijos
                // tiene que tener una nueva llave
                int i = 0;
                if (s.getKeys()[0].getIsbn() < k.getIsbn()) {
                    i++;
                }
                s.getC()[i].insertNonFull(k);
                // cambia la raiz
                root = s;

            } else// si la raiz no esta llena, llamar a insertNonfull para raiz
            {
                root.insertNonFull(k);
            }
        }

    }

    //Getters y setters
    public BTreeNode getRoot() {
        return root;
    }

    public void setRoot(BTreeNode root) {
        this.root = root;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public String toDot() {
        StringBuilder b = new StringBuilder();

        b.append("digraph g { \n node [shape=record];\n");

        b.append(root.toDot());

        b.append("}");

        return b.toString();
    }

  

   

}


/*
 *Graficar

 try {

 FileWriter f = new FileWriter("grafo1.txt");

 f.write(t.toDot());

 f.close();

 } catch (Exception e) {
 }
 doDot("grafo1.txt", "grafo1.jpg");

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

 */
