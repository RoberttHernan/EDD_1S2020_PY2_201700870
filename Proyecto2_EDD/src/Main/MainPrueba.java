/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Estructuras.ArbolAvl;
import Estructuras.BTree;
import Estructuras.NodoAvl;
import Estructuras.TablaHash;
import PaquetesEnvio.Estudiante;
import PaquetesEnvio.Libro;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.text.TabableView;

/**
 *
 * @author Robert HErnandez
 */
public class MainPrueba {

    static ArbolAvl arbol = new ArbolAvl();

    public static void main(String[] args) throws FileNotFoundException, InterruptedException, IOException {
       BTree arbolB = new BTree(3);
        JsonParser parser = new JsonParser();

        FileReader fr = new FileReader("Libros.json");

        JsonObject gsonObj = parser.parse(fr).getAsJsonObject();
        JsonArray libros = gsonObj.get("libros").getAsJsonArray();

        for (JsonElement lib : libros) {
            JsonObject g = lib.getAsJsonObject();

            int isbn = g.get("ISBN").getAsInt();
            int anio = g.get("Año").getAsInt();
            String idioma = g.get("Idioma").getAsString();
            String titulo = g.get("Titulo").getAsString();
            String editorial = g.get("Editorial").getAsString();
            String autor = g.get("Autor").getAsString();
            int edicion = g.get("Edicion").getAsInt();
            String categoria = g.get("Categoria").getAsString();
            Libro libro = new Libro(isbn, titulo, autor, editorial, anio, edicion, categoria, idioma, 0);
            InsertarLibroEnAvl(libro);

        }
            arbol.graficarInorden("graficas//graficoPreorden.dot", "graficas//graficoPreorden.jpg");
           arbol.graficarPreorden("graficas//graficoInorden.dot", "graficas//graficoInorden.jpg");
       arbol.graficarPosOrden( "graficas//graficoPosorden.dot",  "graficas//graficoPosorden.jpg");
           
           
          
        
        
        

      
        
       
        
       
    
        
        
    }

    static void InsertarLibroEnAvl(Libro libro) {

        arbol.Add(libro.getCategoria());
        NodoAvl aux = arbol.search(libro.getCategoria());
        aux.getValor().insert(libro);

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
