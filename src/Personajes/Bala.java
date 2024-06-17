package Personajes;

import Pantallas.PantallaJuego;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */
public class Bala extends Thread {
    
    PantallaJuego pantallaJuego;
    public int balaY, balaX, points = 0;
    public volatile boolean activo = true;
    Random random = new Random();
    
    public Bala(PantallaJuego pantallaJuego){
        this.pantallaJuego = pantallaJuego;
        balaY = this.pantallaJuego.naveJugador.getY();
        balaX = this.pantallaJuego.bala.getX();
        
        
    }
    
    
    public void run() {
        try {   
            while (activo){
                sleep(10);
                if (balaX < 1280){
                            
                    balaX += 10;
                    this.pantallaJuego.bala.setLocation(balaX, balaY);
                    this.pantallaJuego.bala.repaint();
                    this.pantallaJuego.bala_ = this.pantallaJuego.bala.getBounds();
                    for (int i = 0; i < 8; i++){
                        for (int j = 0; j < 5; j++){
                            Enemigo enemigoActual = this.pantallaJuego.matrizEnemigos.enemigos[i][j];
                            Rectangle balaRect = this.pantallaJuego.bala_.getBounds();
                            Rectangle enemigoRect = enemigoActual.getEnemigo_();
                            
                            
                            
                            if (balaRect.getBounds().getX() == enemigoRect.getBounds().getX() && balaRect.getBounds().getY() == enemigoRect.getBounds().getY() && enemigoActual.getSalud() > 0){
                                System.out.println("Bala: " + balaRect);
                                System.out.println("Enemigo[" + i + "][" + j + "]: " + enemigoRect);
                                if (this.pantallaJuego.bala.getBounds().getX() == enemigoRect.getBounds().getX() && this.pantallaJuego.bala.getBounds().getY() == enemigoRect.getBounds().getY()){
                                    this.pantallaJuego.capas.remove(this.pantallaJuego.bala);
                                    balaRect.setBounds(0,0,0,0);
                                }
                                int puntos = Integer.parseInt(this.pantallaJuego.points.getText());
                                if (this.pantallaJuego.matrizEnemigos.enemigos[i][j].getSalud() == 1){
                                    this.pantallaJuego.matrizEnemigos.enemigos[i][j].setVisible(false);
                                    this.pantallaJuego.capas.remove(enemigoActual);
                                    int masPuntos = this.pantallaJuego.matrizEnemigos.enemigos[i][j].getPuntos();
                                    puntos += masPuntos;
                                    String resultado = String.valueOf(puntos);
                                    this.pantallaJuego.points.setText(resultado);
                                    this.pantallaJuego.explosion.setBounds(enemigoRect);
                                    enemigoRect.setBounds(0,0,0,0);
                                }
                                if (this.pantallaJuego.matrizEnemigos.enemigos[i][j].getSalud() > 0){
                                    int salud = this.pantallaJuego.matrizEnemigos.enemigos[i][j].getSalud();
                                salud -= 1;
                                this.pantallaJuego.matrizEnemigos.enemigos[i][j].setSalud(salud);
                                Detener();
                                }
                            }
                        }
                    }
                    
                    if (balaX > 1270){   
                        Detener();
                    }
                }
                  
                
                    
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void Detener() throws InterruptedException {
        
        pantallaJuego.explosion.setVisible(true);
        sleep(200);
        this.pantallaJuego.explosion.setBounds(0,0,0,0);
        pantallaJuego.explosion.setVisible(false);
        pantallaJuego.capas.add(pantallaJuego.bala);
        pantallaJuego.bala.setLocation(-50, -50);
        activo = false;
    }
}

