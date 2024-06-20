package Pantallas;

import Personajes.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */

public class PantallaPuntuacionMax extends JFrame{
  
    public JLayeredPane capas;
    public JLabel fondoPantalla, Pantalla, PantallaFondo, Lugar1, Lugar2, Lugar3, Lugar4, Lugar5;
    public JPanel panel;
    private ListaJugadores listaJugadores = ListaJugadores.getInstance();
    
    public PantallaPuntuacionMax(){
        
        this.listaJugadores = ListaJugadores.getInstance();
        capas = new JLayeredPane();
        this.setContentPane(capas);
        
        this.setTitle("TOP 5 - Best Players");
        this.setLayout(null);
        this.setSize(500, 720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        
        panel = new JPanel(new GridBagLayout());
        panel.setOpaque(true);
        panel.setBounds(0, 0, 500, 720);
        capas.add(panel, Integer.valueOf(1));
        
        ImageIcon Img = new ImageIcon(PantallaCargaJuego.class.getResource("/Imagenes/copa.png"));
        Image tamañoImg = Img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(tamañoImg);
        setIconImage(img.getImage());
        
        Img = new ImageIcon(PantallaCargaJuego.class.getResource("/Imagenes/fondo_.jpg"));
        tamañoImg = Img.getImage().getScaledInstance(500, 720, Image.SCALE_SMOOTH);
        img = new ImageIcon(tamañoImg);
        PantallaFondo = new JLabel();
        PantallaFondo.setBounds(0, 0, 500, 720);
        PantallaFondo.setVisible(true);
        PantallaFondo.setIcon(img);
        capas.add(PantallaFondo, Integer.valueOf(2));
        
        Font font = new Font("Arial", Font.BOLD, 14);
        
        JButton salir = new JButton("Salir");
        salir.setVisible(true);
        salir.setFont(font);
        salir.setBackground(new Color(138, 149, 160));
        salir.setBounds(10, 10, 100, 40);
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
        
        Img = new ImageIcon(PantallaCargaJuego.class.getResource("/Imagenes/copa.png"));
        tamañoImg = Img.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
        img = new ImageIcon(tamañoImg);
        Pantalla = new JLabel();
        Pantalla.setBounds(150, 20, 200, 300);
        Pantalla.setVisible(true);
        Pantalla.setIcon(img);
        capas.add(Pantalla, Integer.valueOf(3));
            
        font = new Font("Arial", Font.BOLD, 20);
        
        Lugar1 = new JLabel();
        Lugar1.setBounds(20, 330, 440, 40);
        Lugar1.setBackground(new Color(204, 204, 204));
        Lugar1.setHorizontalAlignment(SwingConstants.CENTER);
        Lugar1.setVisible(true);
        Lugar1.setOpaque(true);
        Lugar1.setFont(font);
        capas.add(Lugar1, Integer.valueOf(3));
        
        Lugar2 = new JLabel();
        Lugar2.setBounds(20, 380, 440, 40);
        Lugar2.setBackground(new Color(204, 204, 204));
        Lugar2.setHorizontalAlignment(SwingConstants.CENTER);
        Lugar2.setVisible(true);
        Lugar2.setOpaque(true);
        Lugar2.setFont(font);
        capas.add(Lugar2, Integer.valueOf(3));
        
        Lugar3 = new JLabel();
        Lugar3.setBounds(20, 430, 440, 40);
        Lugar3.setBackground(new Color(204, 204, 204));
        Lugar3.setHorizontalAlignment(SwingConstants.CENTER);
        Lugar3.setVisible(true);
        Lugar3.setOpaque(true);
        Lugar3.setFont(font);
        capas.add(Lugar3, Integer.valueOf(3));
        
        Lugar4 = new JLabel();
        Lugar4.setBounds(20, 480, 440, 40);
        Lugar4.setBackground(new Color(204, 204, 204));
        Lugar4.setHorizontalAlignment(SwingConstants.CENTER);
        Lugar4.setVisible(true);
        Lugar4.setOpaque(true);
        Lugar4.setFont(font);
        capas.add(Lugar4, Integer.valueOf(3));
        
        Lugar5 = new JLabel();
        Lugar5.setBounds(20, 530, 440, 40);
        Lugar5.setBackground(new Color(204, 204, 204));
        Lugar5.setHorizontalAlignment(SwingConstants.CENTER);
        Lugar5.setVisible(true);
        Lugar5.setOpaque(true);
        Lugar5.setFont(font);
        capas.add(Lugar5, Integer.valueOf(3));
        
        ordenarPuestos();
    }
    
    public void ordenarPuestos() {
        Jugador primerLugar = new Jugador("", 0);
        Jugador segundoLugar = new Jugador("", 0);
        Jugador tercerLugar = new Jugador("", 0);
        Jugador cuartoLugar = new Jugador("", 0);
        Jugador quintoLugar = new Jugador("", 0);

        for (Jugador jugador : listaJugadores.listaJugadores) {
            int puntuacion = jugador.getPuntuacion();
            if (puntuacion > primerLugar.getPuntuacion()) {
                quintoLugar = cuartoLugar;
                cuartoLugar = tercerLugar;
                tercerLugar = segundoLugar;
                segundoLugar = primerLugar;
                primerLugar = jugador;
            } else if (puntuacion > segundoLugar.getPuntuacion()) {
                quintoLugar = cuartoLugar;
                cuartoLugar = tercerLugar;
                tercerLugar = segundoLugar;
                segundoLugar = jugador;
            } else if (puntuacion > tercerLugar.getPuntuacion()) {
                quintoLugar = cuartoLugar;
                cuartoLugar = tercerLugar;
                tercerLugar = jugador;
            } else if (puntuacion > cuartoLugar.getPuntuacion()) {
                quintoLugar = cuartoLugar;
                cuartoLugar = jugador;
            } else if (puntuacion > quintoLugar.getPuntuacion()) {
                quintoLugar = jugador;
            }
        }

        Lugar1.setText("1. " + primerLugar.getNombre() + " - Score: " + primerLugar.getPuntuacion());
        Lugar2.setText("2. " + segundoLugar.getNombre() + " - Score: " + segundoLugar.getPuntuacion());
        Lugar3.setText("3. " + tercerLugar.getNombre() + " - Score: " + tercerLugar.getPuntuacion());
        Lugar4.setText("4. " + cuartoLugar.getNombre() + " - Score: " + cuartoLugar.getPuntuacion());
        Lugar5.setText("5. " + quintoLugar.getNombre() + " - Score: " + quintoLugar.getPuntuacion());
    }
}
