
package edu.student.itson.dissof.megazarl.direcciones;

import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.dto.InformacionDerivadaCPDireccionEnvioDTO;

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
public class FAdministradorDirecciones {
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
    public static InformacionDerivadaCPDireccionEnvioDTO obtenerDatosDireccionDerivados(String codigoPostalBuscar)
            throws DireccionesAccesoArchivoCodigosPostalesFallidoException, DireccionesArchivoCodigosPostalesVacioException{

        return AdministradorDirecciones.INSTANCIA.obtenerDatosDireccionDerivados(codigoPostalBuscar);
    }
}
