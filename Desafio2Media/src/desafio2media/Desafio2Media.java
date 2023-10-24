/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio2media;

import desafio2media.conexion.ConexionBD;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Josue
 */
public class Desafio2Media extends JApplet {
    
    private static final int JFXPANEL_WIDTH_INT = 300;
    private static final int JFXPANEL_HEIGHT_INT = 250;
    private static JFXPanel fxContainer;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         // Intentar establecer una conexión
        Connection connection = ConexionBD.getConnection();

        // Si la conexión es exitosa, connection no será null
        if (connection != null) {
            System.out.println("¡Conexión exitosa!");
            // Aquí puedes realizar operaciones con la base de datos si lo deseas

            // Cerrar la conexión cuando hayas terminado
            ConexionBD.close(connection);
        } else {
            System.out.println("No se pudo establecer la conexión. Revisa los mensajes de error anteriores.");
        }
    }
    
  
    
}
