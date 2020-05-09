package Conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Robert Hernandez Clase con los metodos que realizaran la conexion del
 * servidor de la aplicación
 */
public class Servidor {

    private Socket socket;
    private ServerSocket serverSocket;
    private DataInputStream bufferDeEntrada;
    private DataOutputStream bufferDeSalida;

    public Servidor() {
        this.bufferDeEntrada = null;
        this.bufferDeSalida = null;
    }

    /**
     * @param puerto puerto que se establecera en el servidor
     */
    public void LevantarConexion(int puerto) {
        try {
            serverSocket = new ServerSocket(puerto);
            socket = serverSocket.accept();
            JOptionPane.showMessageDialog(null, "Conexión establecida con: " + socket.getInetAddress().getHostName());
        } catch (Exception e) {
            System.out.println("Error en levantarConexion(): " + e.getMessage());
        }
    }

    /**
     * Funcion que habilita el flujo de datos entre cliente y servidor
     */
    public void flujos() {
        try {
            bufferDeEntrada = new DataInputStream(socket.getInputStream());
            bufferDeSalida = new DataOutputStream(socket.getOutputStream());
            bufferDeSalida.flush();
        } catch (IOException e) {
            System.out.println("Error en la apertura de flujos");
        }
    }
public void recibirDatos() {
        String st = "";
        try {
            while (true) {
                st = (String) bufferDeEntrada.readUTF();
                JOptionPane.showMessageDialog(null, st);
            } 
        } catch (IOException e) {
            CerrarConexion();
        }
    }
     
    /**
     * Funcion para enviar datos (Strings)
     *
     * @param s String a enviar
     */
    public void Enviar(String s) {
        try {
            bufferDeSalida.writeUTF(s);
            bufferDeSalida.flush();
        } catch (IOException ex) {
            System.out.println("Error al enviar datos" + ex.getMessage());
        }
    }

    /**
     * Funcion que cierra la conexion entre Servidor Cliente
     */
    public void CerrarConexion() {

        try {
            bufferDeEntrada.close();
            bufferDeSalida.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Eroor al cerrar conexion" + ex.getMessage());
        } finally {
            System.out.println("Conexion Finalizada...");

        }
    }
    
    /**
     * Funcion que ejecuta la conexion entre servidor y cliente
     * @param puerto    puerto en el que se realizara la conexion
     */
    public void ejecutarConexion ( int puerto){
    Thread hilo = new Thread(new Runnable() {

        @Override
        public void run() {
            while(true){
                try{
                LevantarConexion(puerto);
                flujos();
                }finally {
                CerrarConexion();
                }
            }
        }
    });
    
    }
}
