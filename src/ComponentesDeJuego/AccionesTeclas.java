package ComponentesDeJuego;

import AccionesArchivos.CreacionArchivos;
import Pantallas.PantallaJuego;
import Pantallas.PantallaPrincipal;
import Personajes.NaveJugador;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */
public class AccionesTeclas extends Thread {
    
    CreacionArchivos creacionArchivos;
    PantallaJuego pantallaJuego;
    NaveJugador naveJugador;
    Temporizador temporizador; 
    public volatile boolean activo = true;
    
    public AccionesTeclas(PantallaJuego pantallaJuego, Temporizador temporizador, NaveJugador naveJugador){
       this.pantallaJuego = pantallaJuego;
       this.naveJugador = naveJugador;
       this.temporizador = temporizador;

    }
    
    public void run(){
        try{
            while (activo) {
                sleep(250);
                this.pantallaJuego.panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "sAction");
                this.pantallaJuego.panel.getActionMap().put("sAction", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        creacionArchivos = new CreacionArchivos("Hola mundo");
                        creacionArchivos.CrearArchivo();
                        temporizador.detenerTemporizador();
                        naveJugador.Detener();
                        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
                        pantallaPrincipal.setVisible(true);
                        pantallaJuego.dispose();
                        
                    }
                });  
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
