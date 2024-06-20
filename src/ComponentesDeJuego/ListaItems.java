package ComponentesDeJuego;

import Personajes.Item;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */

public class ListaItems implements Serializable{
    
    public ArrayList <Item> items;
    public static ListaItems instance;
    
    public ListaItems(){
        this.items = new ArrayList<Item>();
    }
    
    public static synchronized ListaItems getInstance() {
        if (instance == null) {
            instance = new ListaItems();
        }
        return instance;
    }
    
    public void agregarItem(Item item){
        items.add(item);
    }
    
    public Item getItem(int posicion){
        return items.get(posicion);
    }
    
    public int getSize(){
        return items.size();
    }
    
    public void eliminar(int posicion){
        items.remove(posicion);
    }
}
