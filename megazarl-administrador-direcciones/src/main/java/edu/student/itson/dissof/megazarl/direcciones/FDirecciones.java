
package edu.student.itson.dissof.megazarl.direcciones;

import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.dto.InformacionDerivadaCPDireccionEnvioDTO;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * FDirecciones.java
 *
 * Clase Fachada que representa la implementación de la interfaz {@link IDirecciones}
 * del subsistema Direcciones.
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
public class FDirecciones implements IDirecciones{
    private final AdministradorDirecciones administrador = new AdministradorDirecciones();

    /**
     * Implementación del método obtenerDatosDireccionDerivados() de la interfaz {@link IDirecciones},
     * permite obtener el Estado, Ciudad y Colonia asociados al Código Postal del parámetro.
     * @param codigoPostalBuscar Objeto String que representa el Código Postal a buscar.
     * @return Objeto InformacionDerivadaCPDireccionEnvioDTO, un DTO que contiene el Estado, Ciudad
    y Colonia asociados al Código Postal del parámetro.
     * @throws DireccionesAccesoArchivoCodigosPostalesFallidoException Se lanza si ocurre un error
     * al acceder al archivo que contiene la información de los Códigos Postales y sus datos asociados.
     * @throws DireccionesArchivoCodigosPostalesVacioException Se lanza si el archivo que contiene
     * la información de los Códigos Postales y sus datos asociados está vacío.
     */
    @Override
    public InformacionDerivadaCPDireccionEnvioDTO obtenerDatosDireccionDerivados(String codigoPostalBuscar)
            throws DireccionesAccesoArchivoCodigosPostalesFallidoException, DireccionesArchivoCodigosPostalesVacioException{
<<<<<<< Updated upstream:megazarl-direcciones/src/main/java/edu/student/itson/dissof/megazarl/direcciones/FDirecciones.java
        return administrador.obtenerDatosDireccionDerivados(codigoPostalBuscar);
=======

        String colonia;
        String ciudad;
        String estado;
        
        // Se obtiene la dirección del archivo.
        URL resource = FDirecciones.class.getResource("/codigosPostalesMexico.txt");

        try {
            // Se leen todas las líneas del archivo sin considerar aviso ni encabezado.
            Path path = Paths.get(resource.toURI());
            List<String> lineasArchivo = Files.readAllLines(path, StandardCharsets.ISO_8859_1);
            lineasArchivo.remove(0);
            lineasArchivo.remove(1);

            // Se instancia un objeto informacionDerivadaDireccionEnvioDTO como null.
            InformacionDerivadaCPDireccionEnvioDTO informacionDerivadaDireccionEnvioDTO = null;
                
            int i = 0;
            
            // Se recorren las líneas del archivo.
            while(i < lineasArchivo.size()){
            
                // Por cada línea se crea un arreglo para almacenar cada uno de los
                // valores de los diferentes campos.
                
                String linea = lineasArchivo.get(i);

                String[] partes = linea.split("\\|");

                String codigoPostal = partes[0];

                // Si el Código Postal recibido coincide con uno dentro del archivo, 
                // se guardan sus datos asociados, en la misma fila.
                if (codigoPostal.equals(codigoPostalBuscar)) {

                    colonia = partes[1];
                    estado = partes[4];
                    ciudad = "";

                    if(partes[5].isBlank()){
                        ciudad = partes[3];
                    } else{
                        ciudad = partes[5];
                    }

                    // Se asignan los datos a una nueva DTO de tipo InformacionDerivadaCPDireccionEnvioDTO;
                    informacionDerivadaDireccionEnvioDTO = new InformacionDerivadaCPDireccionEnvioDTO(colonia, ciudad, estado);

                }  
                
                i++;
            }

            if(i == 0){
                // Se lanza una excepcion si el archivo no contiene líneas de texto, es decir, si está vacío.    
                throw new DireccionesArchivoCodigosPostalesVacioException("El archivo que contiene la información"
                        + "relacionada con los Códigos Postales está vacío.");
            }
            
            return informacionDerivadaDireccionEnvioDTO;
            
        // Se lanza una excepción si no se pudo leer el archivo con los Códigos Postales.
        } catch (URISyntaxException|IOException ex) {
            throw new DireccionesAccesoArchivoCodigosPostalesFallidoException("No se pudo acceder al archivo que contiene la información relacionada"
                    + "con los Códigos Postales");
        }

 
>>>>>>> Stashed changes:megazarl-administrador-direcciones/src/main/java/edu/student/itson/dissof/megazarl/direcciones/FDirecciones.java
    }
}
