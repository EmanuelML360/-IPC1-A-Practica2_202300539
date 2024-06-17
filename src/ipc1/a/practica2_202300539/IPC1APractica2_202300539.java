package ipc1.a.practica2_202300539;

import Pantallas.PantallaPrincipal;
import javax.swing.*;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */
public class IPC1APractica2_202300539 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PantallaPrincipal nuevaPantalla = new PantallaPrincipal();
                nuevaPantalla.setVisible(true);
            }
        });
    }
    
}
