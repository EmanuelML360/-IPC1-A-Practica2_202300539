package Personajes;

import java.io.Serializable;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */

public class Jugador implements Serializable{
    
    private String nombre;
    private int puntuacion;
    
    public Jugador(String nombre, int puntuacion){
        this.nombre = nombre;
        this.puntuacion = puntuacion;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String newNombre){
        this.nombre = newNombre;
    }
    
    public int getPuntuacion(){
        return puntuacion;
    }
    
    public void setPuntuacion(int newPuntuacion){
        this.puntuacion = newPuntuacion;
    }
}
