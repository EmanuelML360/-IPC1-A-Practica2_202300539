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
    
    public MatrizEnemigos matrizEnemigos;
    public Serializar serializar;
    public PantallaJuego pantallaJuego;
    public NaveJugador naveJugador;
    public Temporizador temporizador; 
    public Bala bala;
    public volatile boolean activo = true;
    
    public AccionesTeclas(PantallaJuego pantallaJuego, Temporizador temporizador, NaveJugador naveJugador, Bala bala, MatrizEnemigos matrizEnemigos){
       this.pantallaJuego = pantallaJuego;
       this.naveJugador = naveJugador;
       this.temporizador = temporizador;
       this.matrizEnemigos = matrizEnemigos;
       this.bala = bala;
    }
    
    public void run(){
        try{
            while (activo) {
                sleep(250);
                this.pantallaJuego.panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "sAction");
                this.pantallaJuego.panel.getActionMap().put("sAction", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        temporizador.detenerTemporizador();
                        temporizador.interrupt();
                        pantallaJuego.Item.Detener();
                        pantallaJuego.Item.interrupt();
                        pantallaJuego.NaveJugador.Detener();
                        pantallaJuego.NaveJugador.interrupt();
                        pantallaJuego.Bala.DetenerBala();
                        pantallaJuego.Bala.interrupt();
                        pantallaJuego.matrizEnemigos.Detener();
                        pantallaJuego.matrizEnemigos.interrupt();
                        pantallaJuego.accionesTeclas.Detener();
                        pantallaJuego.accionesTeclas.interrupt();
                        pantallaJuego.dispose();
                        serializar = new Serializar(pantallaJuego);
                        serializar.EscribirArchivoBIN();
                        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
                        pantallaPrincipal.setVisible(true);
                        pantallaJuego.dispose();    
                    }
                });
                this.pantallaJuego.panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "escAction");
                this.pantallaJuego.panel.getActionMap().put("escAction", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {  
                        temporizador.detenerTemporizador();
                        temporizador.interrupt();
                        naveJugador.Detener();
                        naveJugador.interrupt();
                        bala.DetenerBala();
                        bala.interrupt();
                        matrizEnemigos.Detener();
                        matrizEnemigos.interrupt();
                        pantallaJuego.Item.Detener();
                        pantallaJuego.Item.interrupt();
                        Detener();
                        interrupt();
                        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
                        pantallaPrincipal.setVisible(true);
                        pantallaJuego.removeAll();
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
