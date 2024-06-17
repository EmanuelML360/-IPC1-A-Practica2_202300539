package ComponentesDeJuego;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */
public class Partida {
    
    private int time, posicionY, posicionX, points, balaX, balaY;
    
    public Partida(int time, int posicionY, int posicionX, int points, int balaX, int balaY){
        this.time = time;
        this.posicionY = posicionY;
        this.posicionX = posicionX;
        this.points = points;
        this.balaX = balaX;
        this.balaY = balaY;
    }
}
