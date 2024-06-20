package AccionesArchivos;

import Pantallas.PantallaJuego;
import Personajes.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Serializar implements Serializable{
    
    public PantallaJuego pantallaJuego;
    
    public Serializar(PantallaJuego pantallaJuego){
        this.pantallaJuego = pantallaJuego;
    }
    
    public void EscribirArchivoBIN(){
        try{
            String fechaFormateada = new SimpleDateFormat("dd_MM_yyyy_HH_mm").format(new Date());
            
            String basePath = "./Juegos/";
            String carpetaConFecha = basePath + fechaFormateada;
            
            File directorio = new File(carpetaConFecha);
            if (!directorio.exists()) {
                if (directorio.mkdirs()) {
                    System.out.println("Estructura de carpetas creada: " + carpetaConFecha);
                } else {
                    System.err.println("Error al crear la estructura de carpetas.");
                    return;
                }
            }
            
            String archivoPath = carpetaConFecha + ".bin";
            
            
            
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream( archivoPath));
            out.writeObject(pantallaJuego);
            out.close();         
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
