package AccionesArchivos;

import ComponentesDeJuego.Partida;
import Pantallas.PantallaJuego;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author José Emanuel Monzón Lémus - 202300539
 */
public class CreacionArchivos {
    
    String texto;
    public ArrayList<Partida> partida;
    
    public CreacionArchivos(String texto) {
        partida = new ArrayList<Partida>();
        this.texto = texto;
    }
    public void CrearArchivo() {
        try{
            FileWriter archivo = new FileWriter("src/ArchivosSistema/archivo.txt");
            archivo.write(texto);
            archivo.close();
            JOptionPane.showMessageDialog(null, "Correcto al generar el archivo");
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Error al generar el archivo");
        }
    }
}
