package Personajes;

import ComponentesDeJuego.ListaItems;
import ComponentesDeJuego.Temporizador;
import Pantallas.PantallaJuego;
import java.awt.event.ActionEvent;
import static java.lang.Thread.sleep;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */

public class NaveJugador extends Thread {
    
    public PantallaJuego pantallaJuego;
    public int posicionY, posicionX, points, time;
    public Temporizador temporizador;
    public ListaItems listaItems = ListaItems.getInstance();
    public Bala bala;
    private volatile boolean activo = true;
    
    public NaveJugador(PantallaJuego pantallaJuego, Temporizador temporizador, Bala bala){
        this.pantallaJuego = pantallaJuego;
        this.temporizador = temporizador;
        this.listaItems = ListaItems.getInstance();
        this.bala = bala; 
        posicionY = this.pantallaJuego.naveJugador.getY();
        posicionX = this.pantallaJuego.naveJugador.getX();
        this.pantallaJuego.nave = this.pantallaJuego.naveJugador.getBounds();      
    }

    public void run() {    
        try {   
            while (activo){
                sleep(20);
                this.pantallaJuego.panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "UPAction");
                this.pantallaJuego.panel.getActionMap().put("UPAction", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (posicionY > 110){
                            posicionY -= 20;
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
                        if (posicionY < 630){
                            posicionY += 20;
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
                if (temporizador.segundos < 0){
                    temporizador.detenerTemporizador();
                    this.Detener();
                    this.pantallaJuego.Bala.Detener();
                }
                if (this.pantallaJuego.nave.intersects(this.pantallaJuego.item_)) {
                    try {
                        sleep(250);
                        int points = Integer.parseInt(this.pantallaJuego.points.getText());
                        int time = Integer.parseInt(this.pantallaJuego.time.getText());
                        this.pantallaJuego.item.setVisible(false);
                        this.pantallaJuego.item.setLocation(-40, 0);
                        this.pantallaJuego.repaint();
                        synchronized (listaItems) {
                            for (int i = 0; i < listaItems.getSize(); i++) {
                                Item currentItem = listaItems.getItem(i);
                                int currentTipo = currentItem.getTipo();
                                if (currentTipo == 1) {
                                    points += 10;
                                    listaItems.eliminar(i);
                                } else if (currentTipo == 2) {
                                    points -= 10;
                                    listaItems.eliminar(i);
                                } else if (currentTipo == 3) {
                                    time += 10;
                                    listaItems.eliminar(i);
                                    this.pantallaJuego.temporizador.setSegundo(time);
                                } else if (currentTipo == 4) {
                                    time -= 10;
                                    listaItems.eliminar(i);
                                    this.pantallaJuego.temporizador.setSegundo(time);
                                }
                            }
                        }
                        this.pantallaJuego.points.setText(String.valueOf(points));
                        this.pantallaJuego.time.setText(String.valueOf(time));
                        this.pantallaJuego.points.repaint();
                        this.pantallaJuego.time.repaint();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al actualizar puntos o tiempo: " + e.getMessage());
                    }
                }

            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
 
    public void Detener() {
        activo = false;
    }
}
