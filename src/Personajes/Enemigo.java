package Personajes;

import java.awt.Rectangle;
import java.io.Serializable;
import javax.swing.JLabel;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */

public class Enemigo extends JLabel implements Serializable{
    
    private int salud;
    private int puntos;
    private int tipo;
    private Rectangle enemigo_;
    
    public Enemigo(int salud, int puntos, int tipo){
        this.puntos = puntos;
        this.salud = salud;
        this.tipo = tipo;
    }
    
    public int getSalud(){
        return salud;
    }
    
    public void setSalud(int aSalud){
        this.salud = aSalud;
    }
    
    public int getPuntos(){
        return puntos;
    }
    
    public void setPuntos(int aPuntos){
        this.puntos = aPuntos;
    }
    
    public int getTipo(){
        return tipo;
    }
    
    public void setTipo(int aTipo){
        this.tipo = tipo;
    }
    
    public Rectangle getEnemigo_() {
        return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    
    public Rectangle getEnemigo__(){
        return enemigo_;
    }

    public void setEnemigo_(Rectangle aEnemigo_){
        this.enemigo_ = aEnemigo_;
    }
}
