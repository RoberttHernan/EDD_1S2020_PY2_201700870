/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Estructuras.ArbolAvl;
import Estructuras.NodoAvl;
import Estructuras.SLL;
import Estructuras.TablaHash;
import Otras_Clases.Libro;
import PaquetesEnvio.Estudiante;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.accessibility.AccessibleState;
import javax.swing.text.TabableView;

/**
 *
 * @author Robert HErnandez
 */
public class MainPrueba {
    
    public static void main(String[] args) {
        ArbolAvl arbolAvl = new ArbolAvl();
        arbolAvl.Add("Miedo");
        arbolAvl.Add("Ficcion");
        
      NodoAvl aux = arbolAvl.search("Miedo");
      
      Libro uno = new Libro(12345, "Fake volt", "Robert", "3m", 1998, 1, "Miedo", "Espanio", 0);
       Libro dos = new Libro(124, "Fake ", "Robert", "3m", 1998, 1, "Miedo", "Espanio", 0);
      aux.getValor().insert(uno);
      aux.getValor().insert(uno);
      aux.getValor().insert(dos);
      
      
        try {
            aux.getValor().graficar("graficas//arbolB.dot", "graficas//arbolB.jpg");
            
        } catch (IOException ex) {
            Logger.getLogger(MainPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        
    }
}
