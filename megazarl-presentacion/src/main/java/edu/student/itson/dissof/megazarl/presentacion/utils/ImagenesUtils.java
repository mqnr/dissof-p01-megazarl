
package edu.student.itson.dissof.megazarl.presentacion.utils;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Clase que permite cargar imagenes del sistema y modificar su tamaño para
 * mostrarlas adecuadamente.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 * 
 */
public class ImagenesUtils {
    
    public static ImageIcon obtenerImagen(String direccionImagen){
        
        ImageIcon imagenRecuperada = new ImageIcon(ImagenesUtils.class.getResource(direccionImagen));
        
        return imagenRecuperada;
        
    }
    
    public static ImageIcon redimensionarImagen(ImageIcon imagenOriginal, int nuevoAncho, int nuevoAlto){
        
        Image imagenProducto = imagenOriginal.getImage().getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
        
        ImageIcon nuevoIconoImagenProducto = new ImageIcon(imagenProducto);
        
        return nuevoIconoImagenProducto;
    }
}
