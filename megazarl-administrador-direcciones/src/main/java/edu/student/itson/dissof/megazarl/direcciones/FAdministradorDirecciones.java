
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
 * FAdministradorDirecciones.java

 Clase Fachada que representa la implementación de la interfaz {@link IAdministradorDirecciones}
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
public class FAdministradorDirecciones implements IAdministradorDirecciones{

    private final AdministradorDirecciones administrador = new AdministradorDirecciones();
    
    /**
     * Implementación del método obtenerDatosDireccionDerivados() de la interfaz {@link IAdministradorDirecciones},
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

        return administrador.obtenerDatosDireccionDerivados(codigoPostalBuscar);
        
    }
}
