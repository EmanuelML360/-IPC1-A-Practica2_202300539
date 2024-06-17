package Personajes;

import Pantallas.PantallaJuego;
import static java.lang.Thread.sleep;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */
public class Item extends Thread{
  
    
    PantallaJuego pantallaJuego;
    public int itemY, itemX;
    public volatile boolean activo = true;
    public Random random = new Random();
    
    public Item(PantallaJuego pantallaJuego){
        this.pantallaJuego = pantallaJuego;
        itemY = this.pantallaJuego.item.getY();
        itemX = this.pantallaJuego.item.getX();
        this.pantallaJuego.item_ = this.pantallaJuego.item.getBounds();
    }
    
    
    public void run() {
        try {   
            while (activo){
                sleep(25);
                if (itemX < 1280){
                    this.pantallaJuego.item.setVisible(true);       
                    itemX -= 25;
                    this.pantallaJuego.item.setLocation(itemX, itemY);
                    this.pantallaJuego.item.repaint();
                    this.pantallaJuego.item_ = this.pantallaJuego.item.getBounds();
                    if (itemX < 0){
                        Detener();
                        pantallaJuego.item.setLocation(1279, itemY = random.nextInt(611) + 100);
                        pantallaJuego.item.repaint();
                        Item Item = new Item(pantallaJuego);
                        Item.start();
                    }
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
