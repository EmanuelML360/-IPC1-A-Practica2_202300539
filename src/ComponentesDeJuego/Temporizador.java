package ComponentesDeJuego;

import Pantallas.*;
import java.io.Serializable;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */

public class Temporizador extends Thread implements Serializable {
    
    public PantallaJuego pantallaJuego;
    public volatile boolean activo = true;
    public int segundos = 90;
    
    public Temporizador(PantallaJuego pantallaJuego){
        this.pantallaJuego = pantallaJuego;
        this.pantallaJuego.repaint();      
    }
    
    @Override
    public void run() {
        try {
            while (activo) {
                sleep(1000);
                segundos--;
                if (segundos <= 0) {
                    detenerTemporizador();
                    this.interrupt();
                    this.pantallaJuego.Item.Detener();
                    this.pantallaJuego.Item.interrupt();
                    this.pantallaJuego.NaveJugador.Detener();
                    this.pantallaJuego.NaveJugador.interrupt();
                    this.pantallaJuego.Bala.Detener();
                    this.pantallaJuego.Bala.interrupt();
                    this.pantallaJuego.matrizEnemigos.Detener();
                    this.pantallaJuego.matrizEnemigos.interrupt();
                    this.pantallaJuego.dispose();
                    int score_ = Integer.parseInt(this.pantallaJuego.points.getText());
                    VentanaFinal ventanaFinal = new VentanaFinal(score_);
                }
                actualizarTemporizador();
            }
        } catch (Exception e){
            Thread.currentThread().interrupt();
        }
    }
    
    public void detenerTemporizador() {
        activo = false;
    }
    
    private void actualizarTemporizador() {
        String tiempo = String.format("%d", segundos);
        pantallaJuego.time.setText(tiempo);
    }
    
    public void setSegundo(int nuevoS){
        this.segundos = nuevoS;
    }
}
