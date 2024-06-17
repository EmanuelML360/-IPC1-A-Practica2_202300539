package Personajes;

import ComponentesDeJuego.ContadorDePuntos;
import ComponentesDeJuego.Temporizador;
import Pantallas.PantallaJuego;
import java.awt.event.ActionEvent;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */
public class NaveJugador extends Thread {
    
    PantallaJuego pantallaJuego;
    String points_;
    int posicionY, posicionX, points;
    Temporizador temporizador;
    ContadorDePuntos contadorDePuntos;
    Bala bala;
    private volatile boolean activo = true;
    
    public NaveJugador(PantallaJuego pantallaJuego, Temporizador temporizador, Bala bala){
        this.pantallaJuego = pantallaJuego;
        this.temporizador = temporizador;
        this.bala = bala;
        points_ = this.pantallaJuego.points.getText();
        points = Integer.parseInt(points_);
        posicionY = this.pantallaJuego.naveJugador.getY();
        posicionX = this.pantallaJuego.naveJugador.getX();
        this.pantallaJuego.nave = this.pantallaJuego.naveJugador.getBounds();
        
    }
    
    
    public void run() {
        
        try {   
            while (activo){
                
                sleep(50);
                this.pantallaJuego.panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "UPAction");
                this.pantallaJuego.panel.getActionMap().put("UPAction", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (posicionY > 85){
                            posicionY -= 60;
                            pantallaJuego.naveJugador.setLocation(posicionX, posicionY);
                            pantallaJuego.nave = pantallaJuego.naveJugador.getBounds();
                            pantallaJuego.naveJugador.repaint();
                        }
                    }
                });

                this.pantallaJuego.panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "DOWNAction");
                this.pantallaJuego.panel.getActionMap().put("DOWNAction", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (posicionY < 638){
                            posicionY += 60;
                            pantallaJuego.naveJugador.setLocation(posicionX, posicionY);
                            pantallaJuego.nave = pantallaJuego.naveJugador.getBounds();
                            pantallaJuego.naveJugador.repaint();
                        }
                    }
                });
                
                this.pantallaJuego.panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "SPACEAction");
                this.pantallaJuego.panel.getActionMap().put("SPACEAction", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (pantallaJuego.Bala.balaX > 1270){
                            pantallaJuego.bala.setLocation(30, posicionY);
                            pantallaJuego.bala.repaint();
                            Bala Bala = new Bala(pantallaJuego);
                            Bala.start();
                            
                        }else {
                            pantallaJuego.Bala.activo = true;
                            pantallaJuego.bala.setLocation(30, posicionY);
                            pantallaJuego.bala.repaint();
                            pantallaJuego.Bala.balaX = pantallaJuego.bala.getX();
                        }
                        
                    }
                });
                if (temporizador.segundos == 0){
                    temporizador.detenerTemporizador();
                }
                
                if (this.pantallaJuego.nave.intersects(this.pantallaJuego.item_)){
                    sleep(250);
                    this.pantallaJuego.item.setVisible(false);
                    this.pantallaJuego.item.setLocation(-1, posicionY);
                    this.pantallaJuego.repaint();
                    points += 10;
                    String _points = String.valueOf(points);
                    this.pantallaJuego.points.setText(_points);
                    this.pantallaJuego.points.repaint();
                    
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }


    
    public void Detener() {
        activo = false;
    }
}
