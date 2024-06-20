package Pantallas;

import ComponentesDeJuego.AccionesTeclas;
import Personajes.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */

public class PantallaCargaJuego extends JFrame implements Serializable{
    
    public JLayeredPane capas;
    public JLabel fondoPantalla, Pantalla;
    private String filePath;
    
    public PantallaCargaJuego(){
        
        capas = new JLayeredPane();
        this.setContentPane(capas);
        
        this.setTitle("Cargar juego");
        this.setLayout(null);
        this.setSize(500, 720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        
        ImageIcon Img = new ImageIcon(PantallaCargaJuego.class.getResource("/Imagenes/EnemigoClase3.png"));
        Image tamañoImg = Img.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(tamañoImg);
        setIconImage(img.getImage());
        
        Font font = new Font("Arial", Font.BOLD, 20);
        
        JButton buscarJuego = new JButton("BuscarJuego");
        buscarJuego.setVisible(true);
        buscarJuego.setFont(font);
        buscarJuego.setBackground(new Color(138, 149, 160));
        buscarJuego.setBounds(100, 300, 300, 70);
        buscarJuego.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buscarJuego.setBackground(new Color(100, 149, 160));
                repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                buscarJuego.setBackground(new Color(138, 149, 160));
                repaint();
            }
        });
        capas.add(buscarJuego, Integer.valueOf(3));
        buscarJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFileChooser fileChooser = new JFileChooser();
                        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos BIN", "bin");
                        fileChooser.setFileFilter(filter);
                        fileChooser.setAcceptAllFileFilterUsed(false); 
                        int returnVal = fileChooser.showOpenDialog(null);
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            filePath = fileChooser.getSelectedFile().getPath();
                            JOptionPane.showMessageDialog(null, "Archivo seleccionado: " + filePath);  
                        }
                    }
                });
            }
        });
        
        JButton cargarJuego = new JButton("Cargar juego");
        cargarJuego.setVisible(true);
        cargarJuego.setFont(font);
        cargarJuego.setBackground(new Color(138, 149, 160));
        cargarJuego.setBounds(100, 390, 300, 70);
        cargarJuego.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cargarJuego.setBackground(new Color(100, 149, 160));
                repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                cargarJuego.setBackground(new Color(138, 149, 160));
                repaint();
            }
        });
        capas.add(cargarJuego, Integer.valueOf(3));
        cargarJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        cargarPartida(filePath );   
                    }
                });
            }
        });
        
        JButton salir = new JButton("Salir");
        salir.setVisible(true);
        salir.setFont(font);
        salir.setBackground(new Color(138, 149, 160));
        salir.setBounds(100, 480, 300, 70);
        salir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                salir.setBackground(new Color(100, 149, 160));
                repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                salir.setBackground(new Color(138, 149, 160));
                repaint();
            }
        });
        capas.add(salir, Integer.valueOf(3));
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal(1);
                        pantallaPrincipal.setVisible(true);
                        dispose();  
                    }
                });
            }
        });
        
        Img = new ImageIcon(PantallaCargaJuego.class.getResource("/Imagenes/fondo_.jpg"));
        tamañoImg = Img.getImage().getScaledInstance(500, 720, Image.SCALE_SMOOTH);
        img = new ImageIcon(tamañoImg);
        fondoPantalla = new JLabel();
        fondoPantalla.setBounds(0, 0, 500, 720);
        fondoPantalla.setOpaque(true);
        fondoPantalla.setVisible(true);
        fondoPantalla.setIcon(img);
        capas.add(fondoPantalla, Integer.valueOf(2));
        
        Img = new ImageIcon(PantallaCargaJuego.class.getResource("/Imagenes/Titulo.png"));
        tamañoImg = Img.getImage().getScaledInstance(450, 200, Image.SCALE_SMOOTH);
        img = new ImageIcon(tamañoImg);
        Pantalla = new JLabel();
        Pantalla.setBounds(10, 10, 450, 200);
        Pantalla.setVisible(true);
        Pantalla.setIcon(img);
        capas.add(Pantalla, Integer.valueOf(3));
    }
    
    private void cargarPartida(String rutaArchivo) {
        if (rutaArchivo == null || rutaArchivo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ningún archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }       
        File archivo = new File(rutaArchivo);
        if (archivo.exists() && archivo.canRead()) {
            try (FileInputStream fileInputStream = new FileInputStream(archivo);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                PantallaJuego pantallaJuego = (PantallaJuego) objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
                
                Enemigo[][] listaEnemigos= pantallaJuego.matrizEnemigos.enemigos;
                
                ArrayList<Item> listaItems_ = pantallaJuego.listaItems.items;
                
                NaveJugador naveJugador = pantallaJuego.NaveJugador;
                
                Bala Bala = pantallaJuego.Bala;
                
                Item Item = pantallaJuego.Item;
                
                AccionesTeclas accionesTeclas = pantallaJuego.accionesTeclas;
                
                ImageIcon img = new ImageIcon(getClass().getResource("/Imagenes/fondo.gif"));
                Image imgTamaño = img.getImage().getScaledInstance(1280, 630, Image.SCALE_DEFAULT);
                ImageIcon imgFondo = new ImageIcon(imgTamaño);
                pantallaJuego.fondo.setIcon(imgFondo);
                
                img = new ImageIcon(getClass().getResource("/Imagenes/point.gif"));
                imgTamaño = img.getImage().getScaledInstance(65, 60, Image.SCALE_DEFAULT);
                ImageIcon imgPoints = new ImageIcon(imgTamaño);
                pantallaJuego.pointsImg.setIcon(imgPoints);
                
                img = new ImageIcon(getClass().getResource("/Imagenes/Explosion.gif"));
                imgTamaño = img.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
                ImageIcon imgExplosion = new ImageIcon(imgTamaño);
                pantallaJuego.explosion.setIcon(imgExplosion);
                
                img = new ImageIcon(getClass().getResource("/Imagenes/Nave.gif"));
                imgTamaño = img.getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT);
                ImageIcon imgNave = new ImageIcon(imgTamaño);
                pantallaJuego.naveJugador.setIcon(imgNave);
                
                pantallaJuego.inicializarDespuesDeDeserializar(listaEnemigos, listaItems_, naveJugador, accionesTeclas, Item, Bala);
                pantallaJuego.setVisible(true);
                dispose();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cargar la partida: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error al intentar leer el archivo. Verifique el archivo y vuelva a intentarlo.");
        }
    }
}
