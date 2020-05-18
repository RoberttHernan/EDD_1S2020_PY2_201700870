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
import javax.swing.text.TabableView;

/**
 *
 * @author Robert HErnandez
 */
public class MainPrueba {

   static ArbolAvl arbol = new ArbolAvl();

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

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

        
      if ( arbol.buscarIsbn(1080)){
          System.out.println("Encontrado");
      }
      else {
          System.out.println("No encontrado");
      }
    }

     static void InsertarLibroEnAvl(Libro libro) {

        arbol.Add(libro.getCategoria());
        NodoAvl aux = arbol.search(libro.getCategoria());
        aux.getValor().insert(libro);

    }
}
