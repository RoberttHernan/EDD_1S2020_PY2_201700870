/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Estructuras.ArbolAvl;
import PaquetesEnvio.Libro;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * @author Robert HErnandez
 */
public class MainPrueba {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        ArbolAvl arbolAvl = new ArbolAvl();

        JsonParser parser = new JsonParser();
        FileReader fr = new FileReader("Libros.json");

        JsonObject gsonObj = parser.parse(fr).getAsJsonObject();
        JsonArray libros = gsonObj.get("libros").getAsJsonArray();
        /*
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
         arbolAvl.Add(libro.getIdioma());

         }*/

        arbolAvl.Add("Consulta");
        arbolAvl.Add("Informativos");
        arbolAvl.Add("Consulta");
        arbolAvl.Add("Biografia");
        arbolAvl.Add("Biografia");
        arbolAvl.Add("Informativos");
        arbolAvl.Add("Consulta");

        arbolAvl.preOrder(arbolAvl.getRaiz());

        arbolAvl.graficar("graficas//tuPutamaddre.jpg");

        /*if (aux.getValor().search(dos)!=null){
         System.out.println("Encontrado");
         }
         //System.out.println(aux.getValor().getNoClaves());
      
         /*try {
         aux.getValor().graficar("graficas//arbolB.dot", "graficas//arbolB.jpg");
            
         } catch (IOException ex) {
         Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
         }*/
    }
}
