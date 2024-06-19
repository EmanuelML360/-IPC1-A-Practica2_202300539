package ComponentesDeJuego;

import AccionesArchivos.CreacionArchivos;
import Pantallas.PantallaJuego;
import Pantallas.PantallaPrincipal;
import Personajes.Bala;
import Personajes.MatrizEnemigos;
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
    
    public MatrizEnemigos matrizEnemigos;
    public CreacionArchivos creacionArchivos;
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
                        creacionArchivos = new CreacionArchivos("Hola mundo");
                        creacionArchivos.CrearArchivo();
                        temporizador.detenerTemporizador();
                        naveJugador.Detener();
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
                        temporizador.currentThread().interrupt();
                        naveJugador.Detener();
                        naveJugador.currentThread().interrupt();
                        bala.DetenerBala();
                        bala.currentThread().interrupt();
                        matrizEnemigos.Detener();
                        matrizEnemigos.currentThread().interrupt();
                        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
                        pantallaPrincipal.setVisible(true);
                        pantallaJuego.removeAll();
                        pantallaJuego.dispose();                        
                    }
                });  
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
