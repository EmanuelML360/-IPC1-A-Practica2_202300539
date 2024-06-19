package Pantallas;

import ComponentesDeJuego.AccionesTeclas;
import ComponentesDeJuego.Temporizador;
import Personajes.Bala;
import Personajes.Enemigo;
import Personajes.Item;
import Personajes.MatrizEnemigos;
import Personajes.NaveJugador;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */

public class PantallaJuego extends JFrame {
    
    public JLabel naveJugador, time, timeImg, points, pointsImg , fondo, bala, item, explosion;
    public JPanel panel;
    public JLayeredPane capas;
    public Enemigo enemigo1, enemigo2, enemigo3;
    public Rectangle nave, item_, bala_, enemigo_;
    public Temporizador temporizador;
    public NaveJugador NaveJugador;
    public Bala Bala;
    public Item Item;
    public MatrizEnemigos matrizEnemigos = MatrizEnemigos.getInstance(this);
    public AccionesTeclas accionesTeclas;
    
    public PantallaJuego() {
        this.matrizEnemigos = MatrizEnemigos.getInstance(this);
        capas = new JLayeredPane();
        this.setContentPane(capas);
        
        this.setTitle("Space invaders");
        this.setLayout(null);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        
        enemigo1 = new Enemigo(2, 10, 1);
        ImageIcon img = new ImageIcon(getClass().getResource("/Imagenes/EnemigoClase1.png"));
        Image imgTamaño = img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        enemigo1.setHorizontalAlignment(SwingConstants.CENTER);
        enemigo1.setVerticalAlignment(SwingConstants.CENTER);
        ImageIcon imgenemigo1 = new ImageIcon(imgTamaño);
        enemigo1.setIcon(imgenemigo1);
        enemigo1.setVisible(true);
        
        enemigo2 = new Enemigo(3, 20,2);
        img = new ImageIcon(getClass().getResource("/Imagenes/EnemigoClase2.png"));
        imgTamaño = img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        enemigo2.setHorizontalAlignment(SwingConstants.CENTER);
        enemigo2.setVerticalAlignment(SwingConstants.CENTER);
        ImageIcon imgenemigo2 = new ImageIcon(imgTamaño);
        enemigo2.setIcon(imgenemigo2);
        enemigo2.setVisible(true);
        
        enemigo3 = new Enemigo(4, 30,3);
        img = new ImageIcon(getClass().getResource("/Imagenes/EnemigoClase3.png"));
        imgTamaño = img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        enemigo3.setHorizontalAlignment(SwingConstants.CENTER);
        enemigo3.setVerticalAlignment(SwingConstants.CENTER);
        ImageIcon imgenemigo3 = new ImageIcon(imgTamaño);
        enemigo3.setIcon(imgenemigo3);
        enemigo3.setVisible(true);
        
        naveJugador = new JLabel();
        naveJugador.setBounds(30, 330, 40, 40);
        img = new ImageIcon(getClass().getResource("/Imagenes/Nave.gif"));
        imgTamaño = img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        naveJugador.setHorizontalAlignment(SwingConstants.CENTER);
        naveJugador.setVerticalAlignment(SwingConstants.CENTER);
        ImageIcon imgNave = new ImageIcon(imgTamaño);
        naveJugador.setIcon(imgNave);
        naveJugador.setVisible(true);
        capas.add(naveJugador, Integer.valueOf(3));
        nave = naveJugador.getBounds();

        bala = new JLabel();
        bala.setBounds(1280, 370, 40, 40);
        img = new ImageIcon(getClass().getResource("/Imagenes/bala.png"));
        imgTamaño = img.getImage().getScaledInstance(51, 20, Image.SCALE_DEFAULT);
        bala.setHorizontalAlignment(SwingConstants.CENTER);
        bala.setVerticalAlignment(SwingConstants.CENTER);
        ImageIcon imgBala = new ImageIcon(imgTamaño);
        bala.setIcon(imgBala);
        bala.setVisible(true);
        capas.add(bala, Integer.valueOf(3));
        bala_ = bala.getBounds();
        
        item = new JLabel();
        item.setBounds(1279, 370, 40, 40);
        img = new ImageIcon(getClass().getResource("/Imagenes/Moneda.png"));
        imgTamaño = img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        item.setHorizontalAlignment(SwingConstants.CENTER);
        item.setVerticalAlignment(SwingConstants.CENTER);
        ImageIcon imgitem = new ImageIcon(imgTamaño);
        item.setIcon(imgitem);
        item.setVisible(true);
        item_ = item.getBounds();
        capas.add(item, Integer.valueOf(3));
        
        explosion = new JLabel();
        img = new ImageIcon(getClass().getResource("/Imagenes/Explosion.gif"));
        imgTamaño = img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        explosion.setHorizontalAlignment(SwingConstants.CENTER);
        explosion.setVerticalAlignment(SwingConstants.CENTER);
        ImageIcon imgExplosion = new ImageIcon(imgTamaño);
        explosion.setIcon(imgExplosion);
        explosion.setVisible(false);
        capas.add(explosion, Integer.valueOf(4));
        
        img = new ImageIcon(getClass().getResource("/Imagenes/fondo.gif"));
        imgTamaño = img.getImage().getScaledInstance(1280, 630, Image.SCALE_DEFAULT);
        ImageIcon imgFondo = new ImageIcon(imgTamaño);
        fondo = new JLabel();
        fondo.setOpaque(true);
        fondo.setBounds(0, 90, 1280, 630);
        fondo.setBackground(new Color(6, 57, 112));
        fondo.setIcon(imgFondo);
        capas.add(fondo, Integer.valueOf(2));
        
        panel = new JPanel(new GridBagLayout());
        panel.setOpaque(true);
        panel.setBounds(0, 0, 1280, 90);
        panel.setBackground(new Color(6, 57, 112));
        capas.add(panel, Integer.valueOf(2));
         
        Font font = new Font("Arial", Font.BOLD, 30);
        time = new JLabel("90");
        time.setFont(font);
        time.setBounds(1000, 40, 80, 30);
        time.setBackground(new Color(6, 57, 112));
        time.setOpaque(true);
        capas.add(time,Integer.valueOf(3));
        
        img = new ImageIcon(getClass().getResource("/Imagenes/reloj.png"));
        imgTamaño = img.getImage().getScaledInstance(60, 65, Image.SCALE_DEFAULT);
        ImageIcon imgTime = new ImageIcon(imgTamaño);
        timeImg = new JLabel();
        timeImg.setBounds(920, 15, 60, 65);
        timeImg.setBackground(new Color(6, 57, 112));
        timeImg.setOpaque(true);
        timeImg.setIcon(imgTime);
        capas.add(timeImg,Integer.valueOf(3));
        
        points = new JLabel("0");
        points.setFont(font);
        points.setBounds(270, 40, 70, 30);
        points.setBackground(new Color(6, 57, 112));
        points.setOpaque(true);
        capas.add(points,Integer.valueOf(3));
        
        img = new ImageIcon(getClass().getResource("/Imagenes/point.gif"));
        imgTamaño = img.getImage().getScaledInstance(65, 60, Image.SCALE_DEFAULT);
        ImageIcon imgPoints = new ImageIcon(imgTamaño);
        pointsImg = new JLabel();
        pointsImg.setBounds(200, 15, 47, 60);
        pointsImg.setBackground(new Color(6, 57, 112));
        pointsImg.setOpaque(true);
        pointsImg.setHorizontalAlignment(SwingConstants.CENTER);
        pointsImg.setIcon(imgPoints);
        capas.add(pointsImg,Integer.valueOf(3));
        
        iniciarJuego();
    }
    
    public void iniciarJuego(){
        try{
        temporizador = new Temporizador(this);
        temporizador.start();
        matrizEnemigos.crearMatriz();
        matrizEnemigos.start();
        Bala = new Bala(this);
        Bala.start();
        Item = new Item(this);
        Item.start();
        NaveJugador = new NaveJugador(this, temporizador, Bala);
        NaveJugador.start();
        accionesTeclas = new AccionesTeclas(this, temporizador, NaveJugador, Bala, matrizEnemigos);
        accionesTeclas.start();
        } catch (Exception e){
            Thread.currentThread().interrupt();
            temporizador = new Temporizador(this);
            temporizador.start();
            matrizEnemigos.crearMatriz();
            matrizEnemigos.start();
            Bala = new Bala(this);
            Bala.start();
            Item = new Item(this);
            Item.start();
            NaveJugador = new NaveJugador(this, temporizador, Bala);
            NaveJugador.start();
            accionesTeclas = new AccionesTeclas(this, temporizador, NaveJugador, Bala, matrizEnemigos);
            accionesTeclas.start();
        }
    }   
}
