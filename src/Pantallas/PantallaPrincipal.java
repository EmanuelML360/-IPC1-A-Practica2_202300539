package Pantallas;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */

public class PantallaPrincipal extends javax.swing.JFrame {
    
    public PantallaPrincipal() {
        
        initComponents();
        this.setLocationRelativeTo(null);

        ImageIcon Img = new ImageIcon(PantallaPrincipal.class.getResource("/Imagenes/EnemigoClase1.png"));
        Image tamañoImg = Img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(tamañoImg);
        setIconImage(img.getImage());
        
        Img = new ImageIcon(PantallaPrincipal.class.getResource("/Imagenes/EnemigoClase1.png"));
        tamañoImg = Img.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        img = new ImageIcon(tamañoImg);
        navePantalla.setIcon(img);
        
        nuevoJuego.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                nuevoJuego.setBackground(new Color(100, 149, 160));
                repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                nuevoJuego.setBackground(new Color(204, 204, 204));
                repaint();
            }
        });
        
        cargarJuego.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cargarJuego.setBackground(new Color(100, 149, 160));
                repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                cargarJuego.setBackground(new Color(204, 204, 204));
                repaint();
            }
        });
        
        puntuacionMax.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                puntuacionMax.setBackground(new Color(100, 149, 160));
                repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                puntuacionMax.setBackground(new Color(204, 204, 204));
                repaint();
            }
        });
        
        salir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                salir.setBackground(new Color(250, 0, 10));
                repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                salir.setBackground(new Color(204, 0, 51));
                repaint();
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        salir = new javax.swing.JButton();
        cargarJuego = new javax.swing.JButton();
        puntuacionMax = new javax.swing.JButton();
        nuevoJuego = new javax.swing.JButton();
        Titulo = new javax.swing.JLabel();
        navePantalla = new javax.swing.JLabel();
        fondoPantalla1 = new javax.swing.JLabel();
        fondoPantalla = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Space Invaders");
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        salir.setBackground(new java.awt.Color(204, 0, 51));
        salir.setFont(new java.awt.Font("Unispace", 1, 24)); // NOI18N
        salir.setForeground(new java.awt.Color(255, 255, 255));
        salir.setText("Salir");
        salir.setActionCommand("");
        salir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        jPanel1.add(salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 560, 290, 70));

        cargarJuego.setBackground(new java.awt.Color(204, 204, 204));
        cargarJuego.setFont(new java.awt.Font("Unispace", 1, 24)); // NOI18N
        cargarJuego.setForeground(new java.awt.Color(255, 255, 255));
        cargarJuego.setText("Cargar juego");
        cargarJuego.setActionCommand("");
        cargarJuego.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cargarJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarJuegoActionPerformed(evt);
            }
        });
        jPanel1.add(cargarJuego, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, 290, 70));

        puntuacionMax.setBackground(new java.awt.Color(204, 204, 204));
        puntuacionMax.setFont(new java.awt.Font("Unispace", 1, 24)); // NOI18N
        puntuacionMax.setForeground(new java.awt.Color(255, 255, 255));
        puntuacionMax.setText("Puntuación Máxima");
        puntuacionMax.setActionCommand("");
        puntuacionMax.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        puntuacionMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puntuacionMaxActionPerformed(evt);
            }
        });
        jPanel1.add(puntuacionMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 460, 290, 70));

        nuevoJuego.setBackground(new java.awt.Color(204, 204, 204));
        nuevoJuego.setFont(new java.awt.Font("Unispace", 1, 24)); // NOI18N
        nuevoJuego.setForeground(new java.awt.Color(255, 255, 255));
        nuevoJuego.setText("Nuevo Juego");
        nuevoJuego.setActionCommand("");
        nuevoJuego.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        nuevoJuego.setPreferredSize(new java.awt.Dimension(169, 25));
        nuevoJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoJuegoActionPerformed(evt);
            }
        });
        jPanel1.add(nuevoJuego, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 290, 70));

        Titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Titulo.png"))); // NOI18N
        jPanel1.add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));
        jPanel1.add(navePantalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 390, 300, 300));

        fondoPantalla1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.gif"))); // NOI18N
        jPanel1.add(fondoPantalla1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 500, 500));

        fondoPantalla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.gif"))); // NOI18N
        jPanel1.add(fondoPantalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoJuegoActionPerformed
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                dispose();
                PantallaJuego pantallaJuego = new PantallaJuego();
                pantallaJuego.setVisible(true);
                pantallaJuego.iniciarJuego();
            }
        });
    }//GEN-LAST:event_nuevoJuegoActionPerformed

    private void cargarJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarJuegoActionPerformed
        this.dispose();
        PantallaCargaJuego pantallaCargaJuego = new PantallaCargaJuego();
    }//GEN-LAST:event_cargarJuegoActionPerformed

    private void puntuacionMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puntuacionMaxActionPerformed
        this.dispose();
        PantallaPuntuacionMax pantallaCargaJuego = new PantallaPuntuacionMax();
    }//GEN-LAST:event_puntuacionMaxActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        dispose();
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    public static void main(String args[]) {
           
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Titulo;
    private javax.swing.JButton cargarJuego;
    private javax.swing.JLabel fondoPantalla;
    private javax.swing.JLabel fondoPantalla1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel navePantalla;
    private javax.swing.JButton nuevoJuego;
    private javax.swing.JButton puntuacionMax;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
