/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Conexion.Servidor;
import Estructuras.TablaHash;
import PaquetesEnvio.Estudiante;
import PaquetesEnvio.PaqueteUsuario;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.corba.se.impl.io.OutputStreamHook;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.ParseConversionEvent;

/**
 *
 * @author robea
 */
public class VentanaPrincipal extends javax.swing.JFrame implements Runnable {

    private TablaHash tabla = new TablaHash(45);


    Ventana_ConfiguracionPuerto Ventanaconfiguracion = new Ventana_ConfiguracionPuerto(this, true);

    static int opcion = 0;
    private int puerto = 5050;
    Thread miHilo = new Thread(this);

    public VentanaPrincipal() {

        initComponents();
        this.setLocationRelativeTo(null);
        miHilo.start();

    }

    public TablaHash getTabla() {
        return tabla;
    }

    public void setTabla(TablaHash tabla) {
        this.tabla = tabla;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabelMostrarPuerto = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/LogoProyecto.PNG"))); // NOI18N

        jMenu1.setText("Carga Masiva");

        jMenuItem1.setText("Usuarios");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Configuracion");

        jMenuItem2.setText("Configurar Puerto");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(356, Short.MAX_VALUE)
                .addComponent(jLabelMostrarPuerto)
                .addGap(81, 81, 81))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelMostrarPuerto)
                .addGap(9, 9, 9)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Json", "json");
        fileChooser.setFileFilter(filter);

        int seleccion = fileChooser.showDialog(jLabel1, null);
        JsonParser parser = new JsonParser();
        try {
            FileReader fr = new FileReader(fileChooser.getSelectedFile());

            JsonObject gsonObj = parser.parse(fr).getAsJsonObject();

            JsonArray usuarios = gsonObj.get("Usuarios").getAsJsonArray();

            for (JsonElement user : usuarios) {
                JsonObject g = user.getAsJsonObject();

                int carnet = g.get("Carnet").getAsInt();
                String nombre = g.get("Nombre").getAsString();
                String apellido = g.get("Apellido").getAsString();
                String carrera = g.get("Carrera").getAsString();
                String pass = g.get("Password").getAsString();
                System.out.println(pass);
                Estudiante estudiante = new Estudiante(carnet, nombre, apellido, carrera, pass);
                tabla.insert(estudiante);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabla.print();


    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Ventanaconfiguracion.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                puerto = Ventanaconfiguracion.getPuerto();
                jLabelMostrarPuerto.setText("Puerto: " + puerto);
            }

        });
        Ventanaconfiguracion.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelMostrarPuerto;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    // End of variables declaration//GEN-END:variables

    public static String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value 
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            ServerSocket servidor = new ServerSocket(this.puerto);
            int Carnet;
            String password;
            String respuesta;

            PaqueteUsuario user;

            while (true) { 
                Socket misocket = servidor.accept();
                ObjectInputStream flujo_entrada = new ObjectInputStream(misocket.getInputStream());

                byte tipo = flujo_entrada.readByte();
                if (tipo == 1) {
                    user = (PaqueteUsuario) flujo_entrada.readObject();
                    Estudiante estudent = tabla.buscar(user.getCarnet());
                    ObjectOutputStream flujo_salida = new ObjectOutputStream(misocket.getOutputStream());
                    if (estudent != null && estudent.getPassword().equals(user.getPassword())) {
                        flujo_salida.writeObject(estudent);
                    } else {
                        flujo_salida.writeObject(null);
                        flujo_salida.flush();
                    }

                }
                if (tipo == 2) {
                    Estudiante aux = (Estudiante) flujo_entrada.readObject();
                    if (aux!= null){
                    tabla.EditarEstudiante(aux);
                    tabla.buscar(aux.getCarnet());
                     ObjectOutputStream flujo_salida = new ObjectOutputStream(misocket.getOutputStream());
                     flujo_salida.writeObject(aux);
                     flujo_salida.flush();
                    }
                }
                if (tipo == 3){
                    Estudiante aux = (Estudiante) flujo_entrada.readObject();
                    if ( aux!=null){
                    tabla.Eliminar(aux.getCarnet());
                    }
                }

                misocket.close();
            }
        } catch (IOException | ClassNotFoundException ex) {
           // Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Error "+ ex);
        }
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public JLabel getjLabelMostrarPuerto() {
        return jLabelMostrarPuerto;
    }

    public void setjLabelMostrarPuerto(JLabel jLabelMostrarPuerto) {
        this.jLabelMostrarPuerto = jLabelMostrarPuerto;
    }

}
