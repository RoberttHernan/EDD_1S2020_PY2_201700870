package Conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
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
    private DataInputStream bufferEntrada;
    private DataOutputStream bufferSalida;

    public void levantarConexion(int puerto) {
        try {
            serverSocket = new ServerSocket(puerto);
            System.out.println("Esperando conexion en el puerto " + puerto + "...");
            socket = serverSocket.accept();
            System.out.println("Conexion establecida con: " + socket.getInetAddress().getHostName());

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Flujos() {
        try {
            bufferEntrada = new DataInputStream(socket.getInputStream());
            bufferSalida = new DataOutputStream(socket.getOutputStream());
            bufferSalida.flush();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void RecibirDatos() {
        String st = "";
        try {
            do {
                st = (String) bufferEntrada.readUTF();
                JOptionPane.showMessageDialog(null, st);

            } while (true);
        } catch (IOException e) {
            cerrarConexion();
        }
    }

    public void enviar(String s) {
        try {
            bufferSalida.writeUTF(s);
            bufferSalida.flush();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarConexion() {
        try {
            bufferEntrada.close();
            bufferSalida.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
}
