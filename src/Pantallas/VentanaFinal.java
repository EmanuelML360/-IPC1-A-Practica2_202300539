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

public class VentanaFinal extends JFrame{
    
    public JLayeredPane capas;
    public JLabel nombre, score, _score;
    public JTextField nombre_;
    public JButton boton;
    private ListaJugadores listaJugadores = ListaJugadores.getInstance();
    public JPanel panel;
    public int score_;
    
    public VentanaFinal(int score_){
        
        this.score_ = score_;
        this.listaJugadores = ListaJugadores.getInstance();
        
        capas = new JLayeredPane();
        this.setContentPane(capas);
        
        this.setTitle("Player");
        this.setLayout(null);
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        
        panel = new JPanel(new GridBagLayout());
        panel.setOpaque(true);
        panel.setBounds(0, 0, 500, 720);
        panel.setBackground(Color.GRAY);
        capas.add(panel, Integer.valueOf(1));
        
        ImageIcon Img = new ImageIcon(PantallaCargaJuego.class.getResource("/Imagenes/copa.png"));
        Image tamañoImg = Img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(tamañoImg);
        setIconImage(img.getImage());
        
        Font font = new Font("Arial", Font.BOLD, 14);
        nombre = new JLabel("NickName:");
        nombre.setFont(font);
        nombre.setForeground(Color.white);
        nombre.setBounds(10, 50, 80, 30);
        capas.add(nombre,Integer.valueOf(3));
        
        nombre_ = new JTextField();
        nombre_.setBounds(92, 50, 150, 30);
        capas.add(nombre_,Integer.valueOf(3));
        
        score = new JLabel("Score: " + score_);
        score.setFont(font);
        score.setForeground(Color.white);
        score.setBounds(100, 120, 150, 30);
        capas.add(score,Integer.valueOf(3));
        
        Img = new ImageIcon(PantallaCargaJuego.class.getResource("/Imagenes/Moneda.png"));
        tamañoImg = Img.getImage().getScaledInstance(50, 60, Image.SCALE_SMOOTH);
        img = new ImageIcon(tamañoImg);
        _score = new JLabel();
        _score.setFont(font);
        _score.setForeground(Color.white);
        _score.setBounds(40, 100, 50, 60);
        _score.setIcon(img);
        capas.add(_score,Integer.valueOf(3));
        
        JButton salir = new JButton("OK");
        salir.setVisible(true);
        salir.setFont(font);
        salir.setBackground(new Color(138, 149, 160));
        salir.setBounds(80, 200, 100, 40);
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
                        Jugador jugador;
                        listaJugadores.agregarJugador( jugador = new Jugador(nombre_.getText(), score_));
                        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal(1);
                        pantallaPrincipal.setVisible(true);
                        dispose();  
                    }
                });
            }
        });
    }    
}
