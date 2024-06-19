package ComponentesDeJuego;

import Pantallas.PantallaJuego;
import Personajes.*;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */
public class Temporizador extends Thread {
    
    public PantallaJuego pantallaJuego;
    public NaveJugador naveJugador;
    private volatile boolean activo = true;
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
                if (segundos == 0) {
                    detenerTemporizador();
                    naveJugador.Detener();
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
