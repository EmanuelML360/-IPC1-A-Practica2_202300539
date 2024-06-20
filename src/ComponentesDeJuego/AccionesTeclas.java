package ComponentesDeJuego;

import AccionesArchivos.*;
import Pantallas.PantallaJuego;
import Pantallas.PantallaPrincipal;
import Personajes.Bala;
import Personajes.MatrizEnemigos;
import Personajes.NaveJugador;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */

public class AccionesTeclas extends Thread implements Serializable{
    
    public Serializar serializar;
    public PantallaJuego pantallaJuego;
    public volatile boolean activo = true;
    
    public AccionesTeclas(PantallaJuego pantallaJuego){
       this.pantallaJuego = pantallaJuego;
    }
    
    public void run(){
        try{
            while (activo) {
                sleep(250);
                this.pantallaJuego.panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "sAction");
                this.pantallaJuego.panel.getActionMap().put("sAction", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pantallaJuego.temporizador.detenerTemporizador();
                        pantallaJuego.Item.Detener();
                        pantallaJuego.NaveJugador.Detener();
                        pantallaJuego.Bala.DetenerBala();
                        pantallaJuego.matrizEnemigos.Detener();
                        pantallaJuego.accionesTeclas.Detener();
                        serializar = new Serializar(pantallaJuego);
                        serializar.EscribirArchivoBIN();
                        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal(1);
                        pantallaPrincipal.setVisible(true);
                        pantallaJuego.dispose();    
                    }
                });
                this.pantallaJuego.panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "escAction");
                this.pantallaJuego.panel.getActionMap().put("escAction", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {  
                        pantallaJuego.temporizador.detenerTemporizador();
                        pantallaJuego.NaveJugador.Detener();
                        pantallaJuego.Bala.DetenerBala();
                        pantallaJuego.matrizEnemigos.Detener();
                        pantallaJuego.Item.Detener();
                        pantallaJuego.accionesTeclas.Detener();
                        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal(1);
                        pantallaPrincipal.setVisible(true);
                        pantallaJuego.dispose();                        
                    }
                });  
            }
        } catch (Exception e) {
        }
    }
    
    public void Detener(){
        activo = false;
    }
}
