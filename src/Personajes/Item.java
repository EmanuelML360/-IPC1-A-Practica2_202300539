package Personajes;

import ComponentesDeJuego.ListaItems;
import Pantallas.PantallaJuego;
import java.awt.Image;
import static java.lang.Thread.sleep;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */

public class Item extends Thread {
    
    PantallaJuego pantallaJuego;
    ListaItems listaItems = ListaItems.getInstance();
    public int itemY, itemX;
    public volatile boolean activo = true;
    public Random random = new Random();
    private int tipo;
    public int tipo_;
    int posicion = 0;
    
    public Item(PantallaJuego pantallaJuego){
        this.pantallaJuego = pantallaJuego;
        this.listaItems = ListaItems.getInstance();
        itemY = this.pantallaJuego.item.getY();
        itemX = this.pantallaJuego.item.getX();
        this.pantallaJuego.item_ = this.pantallaJuego.item.getBounds();
    }
    
    public void run() {
        try {   
            while (activo){
                sleep(20);
                if (itemX < 1280){
                    this.pantallaJuego.item.setVisible(true);       
                    itemX -= 20;
                    this.pantallaJuego.item.setLocation(itemX, itemY);
                    this.pantallaJuego.item.repaint();
                    this.pantallaJuego.item_ = this.pantallaJuego.item.getBounds();
                    if (itemX < 0){
                        Detener();
                        for (int i = 0; i < listaItems.getSize() - 1; i++){
                            listaItems.eliminar(i);
                        }
                        tipo_ = random.nextInt(4) + 1;
                        switch(tipo_){
                            case 1:
                                ImageIcon img = new ImageIcon(getClass().getResource("/Imagenes/Moneda.png"));
                                Image imgTamaño = img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
                                ImageIcon imgitem = new ImageIcon(imgTamaño);
                                this.pantallaJuego.item.setIcon(imgitem);
                                this.pantallaJuego.item.setLocation(1279, itemY = random.nextInt(590) + 100);
                                this.nuevoItem(1);
                                break;
                            case 2:
                                img = new ImageIcon(getClass().getResource("/Imagenes/MenosModeda.png"));
                                imgTamaño = img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
                                ImageIcon imgitem2 = new ImageIcon(imgTamaño);
                                this.pantallaJuego.item.setIcon(imgitem2);
                                this.pantallaJuego.item.setLocation(1279, itemY = random.nextInt(590) + 100);
                                this.nuevoItem(2);
                                break;
                            case 3:
                                img = new ImageIcon(getClass().getResource("/Imagenes/reloj_.png"));
                                imgTamaño = img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
                                ImageIcon imgitem3 = new ImageIcon(imgTamaño);
                                this.pantallaJuego.item.setIcon(imgitem3);
                                this.pantallaJuego.item.setLocation(1279, itemY = random.nextInt(590) + 100);
                                this.nuevoItem(3);
                                break;
                            case 4:
                                img = new ImageIcon(getClass().getResource("/Imagenes/MenosTiempo.png"));
                                imgTamaño = img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
                                ImageIcon imgitem4 = new ImageIcon(imgTamaño);
                                this.pantallaJuego.item.setIcon(imgitem4);
                                this.pantallaJuego.item.setLocation(1279, itemY = random.nextInt(590) + 100);
                                this.nuevoItem(4);
                                break;
                        }    
                    }
                }
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public Item nuevoItem(int tipo){
        Item Item = new Item(pantallaJuego);
        Item.setTipo(tipo);
        listaItems.agregarItem(Item);
        posicion++;
        Item.start();
        return Item;
    }
    
    public void Detener() {
        activo = false;
    }
    
    public int getTipo(){
        return tipo;
    }
    
    public void setTipo(int nuevoTipo){
        this.tipo = nuevoTipo;
    }
}
