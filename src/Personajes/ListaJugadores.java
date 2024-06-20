package Personajes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */

public class ListaJugadores implements Serializable{
    
    public ArrayList<Jugador> listaJugadores;
    public static ListaJugadores instance;
    
    public ListaJugadores(){
        this.listaJugadores = new ArrayList<Jugador>();
    }
    
    public static synchronized ListaJugadores getInstance() {
        if (instance == null) {
            instance = new ListaJugadores();
        }
        return instance;
    }
    
    public Jugador getJugador(int posicion){
        return listaJugadores.get(posicion);
    }
    
    public void eleminarJugador(int posicion){
        listaJugadores.remove(posicion);
    }
    
    public void agregarJugador(Jugador jugador){
        listaJugadores.add(jugador);
    }
}

