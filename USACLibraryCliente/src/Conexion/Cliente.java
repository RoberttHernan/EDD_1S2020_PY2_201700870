package Conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Robert Hernandez
 */
public class Cliente  {

    private Socket socket;
    private DataInputStream bufferDeEntrada = null;
    private DataOutputStream bufferDeSalida = null;
    
    
    public void levantarConexion(String ip, int puerto) {
        try {
            socket = new Socket(ip, puerto);
            JOptionPane.showMessageDialog(null, "Conectado a :" + socket.getInetAddress().getHostName());
        } catch (Exception e) {
            System.out.println("Excepción al levantar conexión: " + e.getMessage());
            System.exit(0);
        }
        
    }
    public void abrirFlujos() {
        try {
            bufferDeEntrada = new DataInputStream(socket.getInputStream());
            bufferDeSalida = new DataOutputStream(socket.getOutputStream());
            bufferDeSalida.flush();
        } catch (IOException e) {
            System.out.println("Error en la apertura de flujos");
        }
    }
    public void enviar(String s) {
        try {
            bufferDeSalida.writeUTF(s);
            bufferDeSalida.flush();
        } catch (IOException e) {
            System.out.println("Erro on enviar");
        }
    }
     public void cerrarConexion() {
        try {
            bufferDeEntrada.close();
            bufferDeSalida.close();
            socket.close();
            JOptionPane.showMessageDialog(null, "Conexión terminada");
        } catch (IOException e) {
            System.out.println("Error al cerrar conexion");
        }finally{
            System.exit(0);
        }
    }
     public void ejecutarConexion(String ip, int puerto) {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    
                    levantarConexion(ip, puerto);
                    abrirFlujos();
                    recibirDatos();
                    
                } finally {
                    cerrarConexion();
                }
            }
        });
        hilo.start();
    }

   public void recibirDatos() {
        String st = "";
        try {
            do{
                st = (String) bufferDeEntrada.readUTF();
                JOptionPane.showMessageDialog(null, st);
            }while(true);
            
        } catch (IOException e) {}
    }
   

}
