package Personajes;

import Pantallas.PantallaJuego;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */
public class MatrizEnemigos extends Thread{
    
    public Enemigo[][] enemigos;
    public static MatrizEnemigos instance;
    public Enemigo enemigo1, enemigo2, enemigo3;
    public PantallaJuego pantallaJuego;
    public volatile boolean activo = true;
    public int movimiento = 0;
  
    public MatrizEnemigos(PantallaJuego pantallaJuego){
        this.enemigos = new Enemigo[8][5];
        this.pantallaJuego = pantallaJuego;
    }
    
    public void run(){
        try {
            while (activo){
                sleep(1000);
                for (int i = 0; i < 8; i++){
                    for (int j = 0; j < 5; j++){
                        int posX = (int) (this.enemigos[i][j].getBounds().getX());
                        int posY = (int) (this.enemigos[i][j].getBounds().getY());
                        switch (movimiento){
                            case 0:
                                posX -= 60;
                                posY -= 0;
                                this.enemigos[i][j].setBounds(posX, posY, 50, 50);
                                break;
                            case 1:
                                posY -= 60;
                                posX -= 0;
                                this.enemigos[i][j].setBounds(posX, posY, 50, 50);
                                break;
                            case 2:
                                posX -= 60;
                                posY -= 0;
                                this.enemigos[i][j].setBounds(posX, posY, 50, 50);
                                break;
                            case 3:
                                posY += 60;
                                posX += 0;
                                this.enemigos[i][j].setBounds(posX, posY, 50, 50);
                                break;
                            case 4:
                                posX -= 60;
                                posY -= 0;
                                this.enemigos[i][j].setBounds(posX, posY, 50, 50);
                                break;
                            case 5:
                                posY += 60;
                                posX += 0;
                                this.enemigos[i][j].setBounds(posX, posY, 50, 50);
                                break;
                            case 6:
                                posX -= 60;
                                posY -= 0;
                                this.enemigos[i][j].setBounds(posX, posY, 50, 50);
                                break;
                            case 7:
                                posY -= 60;
                                posX -= 0;
                                this.enemigos[i][j].setBounds(posX, posY, 50, 50);
                                break;
                            case 8:
                                posX -= 60;
                                posY -= 0;
                                this.enemigos[i][j].setBounds(posX, posY, 50, 50);
                                movimiento = 0;
                                break;
                            default:
                        }
                        if (i == 7 && j == 4){
                            movimiento++;
                        }
                    }
                }
                sleep(2300);
                if (enemigos.length <= 0){
                    Detener();
                }
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static synchronized MatrizEnemigos getInstance(PantallaJuego pantallaJuego_) {
        if (instance == null) {
            instance = new MatrizEnemigos(pantallaJuego_);
        }
        return instance;
    }
    
    public Enemigo getEnemigo(int i, int j){
        return enemigos[i][j];
    }
    
    public void crearMatriz(){
        int posX = 950, posY = 150;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 5; j++){
                if (j < 1){
                    enemigo1 = new Enemigo(2, 10, 1);
                    enemigo1.setBounds(posX, posY, 40, 40);
                    ImageIcon img = new ImageIcon(getClass().getResource("/Imagenes/EnemigoClase1.png"));
                    Image imgTamaño = img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
                    enemigo1.setHorizontalAlignment(SwingConstants.CENTER);
                    enemigo1.setVerticalAlignment(SwingConstants.CENTER);
                    ImageIcon imgenemigo1 = new ImageIcon(imgTamaño);
                    enemigo1.setIcon(imgenemigo1);
                    enemigo1.setVisible(true);
                    enemigo1.setEnemigo_(enemigo1.getBounds());
                    enemigos[i][j] = enemigo1;
                    this.pantallaJuego.capas.add(enemigos[i][j], Integer.valueOf(3));
                    posX += 60;
                    
                } else if (j < 3){
                    enemigo2 = new Enemigo(3, 20,2);
                    enemigo2.setBounds(posX, posY, 40, 40);
                    ImageIcon img = new ImageIcon(getClass().getResource("/Imagenes/EnemigoClase2.png"));
                    Image imgTamaño = img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
                    enemigo2.setHorizontalAlignment(SwingConstants.CENTER);
                    enemigo2.setVerticalAlignment(SwingConstants.CENTER);
                    ImageIcon imgenemigo2 = new ImageIcon(imgTamaño);
                    enemigo2.setIcon(imgenemigo2);
                    enemigo2.setVisible(true);
                    enemigo2.setEnemigo_(enemigo2.getBounds());
                    enemigos[i][j] = enemigo2;
                    this.pantallaJuego.capas.add(enemigos[i][j], Integer.valueOf(3));
                    posX += 60;
                } else {
                    enemigo3 = new Enemigo(4, 30,3);
                    enemigo3.setBounds(posX, posY, 40, 40);
                    ImageIcon img = new ImageIcon(getClass().getResource("/Imagenes/EnemigoClase3.png"));
                    Image imgTamaño = img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
                    enemigo3.setHorizontalAlignment(SwingConstants.CENTER);
                    enemigo3.setVerticalAlignment(SwingConstants.CENTER);
                    ImageIcon imgenemigo3 = new ImageIcon(imgTamaño);
                    enemigo3.setIcon(imgenemigo3);
                    enemigo3.setVisible(true);
                    enemigo3.setEnemigo_(enemigo3.getBounds());
                    enemigos[i][j] = enemigo3;
                    this.pantallaJuego.capas.add(enemigos[i][j], Integer.valueOf(3));
                    posX += 60;
                }
            }
            posY += 60;
            posX -= 300;
        }
    }
       
    public void Detener(){
        activo = false;
    }
}
