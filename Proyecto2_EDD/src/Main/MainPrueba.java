/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Estructuras.ArbolAvl;
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

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        TablaHash tabla = new TablaHash(45);
        Estudiante uno = new Estudiante(201700870, "Robert", "Hernandez", "Cienciasf y Sistemas", "123456");
         Estudiante dos = new Estudiante(201700825, "Enrique", "Henan", "Electronica", "1236");
        tabla.insert(uno);
        tabla.insert(dos);
        tabla.graficar("graficas//Tabla_Grafico.dot");
        
    }
}
