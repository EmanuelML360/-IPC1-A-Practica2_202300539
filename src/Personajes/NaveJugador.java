package Personajes;

import ComponentesDeJuego.*;
import Pantallas.*;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import javax.swing.*;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */

public class NaveJugador extends Thread implements Serializable{
    
    public PantallaJuego pantallaJuego;
    public int posicionY, posicionX, points, time;;
    public volatile boolean activo = true;
    
    public NaveJugador(PantallaJuego pantallaJuego){
        this.pantallaJuego = pantallaJuego;
        posicionY = this.pantallaJuego.naveJugador.getY();
        posicionX = this.pantallaJuego.naveJugador.getX();
        this.pantallaJuego.nave = this.pantallaJuego.naveJugador.getBounds();      
    }

    public void run() {    
        try {   
            while (activo){
                sleep(20);
                this.pantallaJuego.panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed UP"), "UPAction");
                this.pantallaJuego.panel.getActionMap().put("UPAction", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (posicionY > 110){
                            posicionY -= 30;
                            pantallaJuego.naveJugador.setLocation(posicionX, posicionY);
                            pantallaJuego.nave = pantallaJuego.naveJugador.getBounds();
                            pantallaJuego.naveJugador.repaint();
                        }
                    }
                });
                this.pantallaJuego.panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed DOWN"), "DOWNAction");
                this.pantallaJuego.panel.getActionMap().put("DOWNAction", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (posicionY < 630){
                            posicionY += 30;
                            pantallaJuego.naveJugador.setLocation(posicionX, posicionY);
                            pantallaJuego.nave = pantallaJuego.naveJugador.getBounds();
                            pantallaJuego.naveJugador.repaint();
                        }
                    }
                });
                this.pantallaJuego.panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed SPACE"), "SPACEAction");
                this.pantallaJuego.panel.getActionMap().put("SPACEAction", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (pantallaJuego.Bala.balaX > 1260){
                            pantallaJuego.bala.setLocation(30, posicionY);
                            Bala Bala = new Bala(pantallaJuego);
                            Bala.start();
                        }  
                    }
                });
                for (int i = 0; i < 8; i++){
                    for (int j = 0; j < 5; j++){
                        Rectangle Enemigo = this.pantallaJuego.matrizEnemigos.enemigos[i][j].getEnemigo_().getBounds();
                        if (this.pantallaJuego.nave.intersects(Enemigo)){
                            this.pantallaJuego.temporizador.detenerTemporizador();
                            this.pantallaJuego.temporizador.interrupt();
                            this.pantallaJuego.Item.Detener();
                            this.pantallaJuego.Item.interrupt();
                            this.pantallaJuego.NaveJugador.Detener();
                            this.pantallaJuego.NaveJugador.interrupt();
                            this.pantallaJuego.Bala.DetenerBala();
                            this.pantallaJuego.Bala.interrupt();
                            this.pantallaJuego.matrizEnemigos.Detener();
                            this.pantallaJuego.matrizEnemigos.interrupt();
                            this.pantallaJuego.dispose();
                            int score_ = Integer.parseInt(this.pantallaJuego.points.getText());
                            VentanaFinal ventanaFinal = new VentanaFinal(score_);
                        }   
                    }
                }
                if (this.pantallaJuego.nave.intersects(this.pantallaJuego.item_)) {
                    try {
                        sleep(250);
                        int points = Integer.parseInt(this.pantallaJuego.points.getText());
                        int time = Integer.parseInt(this.pantallaJuego.time.getText());
                        this.pantallaJuego.item.setVisible(false);
                        this.pantallaJuego.item.setLocation(-40, 0);
                        this.pantallaJuego.repaint();
                        synchronized (this.pantallaJuego.listaItems) {
                            for (int i = 0; i < this.pantallaJuego.listaItems.getSize(); i++) {
                                Item Item_ = this.pantallaJuego.listaItems.getItem(i);
                                int Tipo_ = Item_.getTipo();
                                if (Tipo_ == 1) {
                                    points += 10;
                                    this.pantallaJuego.listaItems.eliminar(i);
                                } else if (Tipo_ == 2 && points > 0) {
                                    points -= 10;
                                    this.pantallaJuego.listaItems.eliminar(i);
                                } else if (Tipo_ == 3) {
                                    time += 10;
                                    this.pantallaJuego.listaItems.eliminar(i);
                                    this.pantallaJuego.temporizador.setSegundo(time);
                                } else if (Tipo_ == 4 && time > 0) {
                                    time -= 10;
                                    if ( time < 0){
                                        time = 0;
                                    }
                                    this.pantallaJuego.listaItems.eliminar(i);
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
