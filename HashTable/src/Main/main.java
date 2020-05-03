package Main;


import Hash.TablaHash;
import OtraClases.Estudiante;
import SLL.SLL;


/**
 *
 * @author Robert Hernandez
 */
public class main {

    public static void main(String[] args) throws Exception {
        Estudiante st1 = new Estudiante(201700870, "Roberto", "Hernandez", "Ciencias y Sistemas");
        Estudiante st2 = new Estudiante(201700825, "Nez", "Del cid", "Civil");
        Estudiante st3 = new Estudiante(201700871, "Roberto", "Hernandez", "Ciencias y Sistemas");
       
      SLL lista = new SLL();
      lista.insert(st1);
      lista.insert(st2);
      
      
      if (lista.buscar(st3)!=null){
          System.out.println("encontrado");
      }
       
  
     
       
      
        
    }

}
