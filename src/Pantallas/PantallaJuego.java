package Pantallas;

import ComponentesDeJuego.*;
import Personajes.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */

public class PantallaJuego extends JFrame {
    
    public JLabel naveJugador, time, timeImg, points, pointsImg , fondo, bala, item, explosion;
    public JPanel panel;
    public JLayeredPane capas;
    public Rectangle nave, item_, bala_;
    public Temporizador temporizador;
    public NaveJugador NaveJugador;
    public Bala Bala;
    public Item Item;
    public MatrizEnemigos matrizEnemigos;
    public ListaItems listaItems;
    public ListaJugadores listaJugadores;
    public AccionesTeclas accionesTeclas;
    
    public PantallaJuego() {
        
        capas = new JLayeredPane();
        this.setContentPane(capas);
        
        this.setTitle("Space invaders");
        this.setLayout(null);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);        
        
        naveJugador = new JLabel();
        naveJugador.setBounds(30, 333, 40, 40);
        ImageIcon img = new ImageIcon(getClass().getResource("/Imagenes/Nave.gif"));
        Image imgTamaño = img.getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT);
        naveJugador.setHorizontalAlignment(SwingConstants.CENTER);
        naveJugador.setVerticalAlignment(SwingConstants.CENTER);
        ImageIcon imgNave = new ImageIcon(imgTamaño);
        naveJugador.setIcon(imgNave);
        naveJugador.setVisible(true);
        capas.add(naveJugador, Integer.valueOf(3));
        nave = naveJugador.getBounds();

        img = new ImageIcon(getClass().getResource("/Imagenes/Titulo.png"));
        imgTamaño = img.getImage().getScaledInstance(51, 20, Image.SCALE_DEFAULT);
        ImageIcon imgIcono = new ImageIcon(imgTamaño);
        setIconImage(imgIcono.getImage());
        
        bala = new JLabel();
        bala.setBounds(1280, 370, 30, 30);
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
        
    }
    
    public void iniciarJuego(){
        try{
            
            if (NaveJugador == null){
                System.out.println("El hilo ha terminado.");
                this.temporizador = new Temporizador(this);
                this.temporizador.start();
                this.matrizEnemigos = MatrizEnemigos.getInstance(this);
                this.listaItems = ListaItems.getInstance();
                this.listaJugadores = ListaJugadores.getInstance();
                this.matrizEnemigos.crearMatriz();
                this.matrizEnemigos.start();
                this.Bala = new Bala(this);
                this.Bala.start();
                this.Item = new Item(this);
                this.Item.start();
                this.NaveJugador = new NaveJugador(this);
                this.NaveJugador.start();
                this.accionesTeclas = new AccionesTeclas(this);
                this.accionesTeclas.start();         
            }
            
            Thread.State estado = NaveJugador.getState();
            System.out.println("Estado del hilo: " + estado);
            
            if (estado == Thread.State.RUNNABLE) {
                System.out.println("El hilo está en ejecución.");
                this.temporizador.activo = true;
                this.matrizEnemigos.activo = true;
                this.Bala.activo = true;
                this.Item.activo = true;
                this.NaveJugador.activo = true;
                this.accionesTeclas.start();
                this.accionesTeclas.activo = true;
            }
                
        } catch (Exception e){
            Thread.currentThread().interrupt();
        }
    }
    
     public void inicializarDespuesDeDeserializar(Enemigo[][] listaEnemigos, ArrayList <Item> listaItems_, NaveJugador naveJugador, AccionesTeclas accionesTeclas, Item Item, Bala Bala) {
        
        
        Thread.State estado = NaveJugador.getState();

        if (estado == Thread.State.RUNNABLE) {
            this.temporizador = new Temporizador(this);
            this.temporizador.activo = true;

            this.matrizEnemigos.enemigos = listaEnemigos;
            ImageIcon img = new ImageIcon(getClass().getResource("/Imagenes/EnemigoClase1.png"));
            Image imgTamaño = img.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
            this.matrizEnemigos.enemigo1.setHorizontalAlignment(SwingConstants.CENTER);
            this.matrizEnemigos.enemigo1.setVerticalAlignment(SwingConstants.CENTER);
            ImageIcon imgenemigo1 = new ImageIcon(imgTamaño);
            this.matrizEnemigos.enemigo1.setIcon(imgenemigo1);
            this.matrizEnemigos.enemigo1.setVisible(true);
            this.matrizEnemigos.activo = true;

            this.listaItems.items = listaItems_;
            this.Bala = Bala;
            this.Bala.activo = true;

            this.Item = Item;
            this.Item.activo = true;

            this.NaveJugador = naveJugador;
            this.NaveJugador.activo = true;

            this.accionesTeclas = accionesTeclas;
            this.accionesTeclas.activo = true;
        } else {
            this.temporizador = new Temporizador(this);
            this.temporizador.start();
            this.temporizador.activo = true;

            this.matrizEnemigos.enemigos = listaEnemigos;
            ImageIcon img = new ImageIcon(getClass().getResource("/Imagenes/EnemigoClase1.png"));
            Image imgTamaño = img.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
            this.matrizEnemigos.enemigo1.setHorizontalAlignment(SwingConstants.CENTER);
            this.matrizEnemigos.enemigo1.setVerticalAlignment(SwingConstants.CENTER);
            ImageIcon imgenemigo1 = new ImageIcon(imgTamaño);
            this.matrizEnemigos.enemigo1.setIcon(imgenemigo1);
            this.matrizEnemigos.enemigo1.setVisible(true);
            this.matrizEnemigos.start();
            this.matrizEnemigos.activo = true;

            this.listaItems.items = listaItems_;
            this.Bala = Bala;
            this.Bala.start();
            this.Bala.activo = true;

            this.Item = Item;
            this.Item.start();
            this.Item.activo = true;

            this.NaveJugador = naveJugador;
            this.NaveJugador.start();
            this.NaveJugador.activo = true;

            this.accionesTeclas = accionesTeclas;
            this.accionesTeclas.start();
            this.accionesTeclas.activo = true;
        }
        
        
    }
}
