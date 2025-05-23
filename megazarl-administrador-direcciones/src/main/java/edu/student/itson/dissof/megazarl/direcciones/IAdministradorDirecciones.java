
package edu.student.itson.dissof.megazarl.direcciones;

import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.CodigoPostalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDerivadaCPDireccionDTONegocios;

/**
 * IAdministradorDirecciones.java
 
 Interfaz del subsistema Direcciones, que puede acceder a la información asociada
 a los Códigos Postales de los Estados Unidos Mexicanos.
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
public interface IAdministradorDirecciones {
    
    public abstract DireccionDTONegocios obtenerDireccion(IdDireccionDTONegocios idDireccionDTONegocios);
    
    public abstract boolean validarDireccion(IdDireccionDTONegocios idDireccionDTONegocios);
    
    public abstract DireccionDTONegocios registrarDireccion(DireccionDTONegocios direccionDTONegocios) 
            throws DireccionesAccesoArchivoCodigosPostalesFallidoException,
            DireccionesArchivoCodigosPostalesVacioException;
    
    /**
     * Método que permite obtener el Estado, Ciudad y Colonia asociados al Código Postal del parámetro.
     * @param codigoPostalDTONegocios Objeto CodigoPostalDTONegocios que representa el Código Postal a buscar.
     * @return Objeto InformacionDerivadaCPDireccionEnvioDTO, un DTO que contiene el Estado, Ciudad
     *  y Colonia asociados al Código Postal del parámetro; nul si el código postal es inválido.
     * @throws DireccionesAccesoArchivoCodigosPostalesFallidoException Se lanza si ocurre un error
     * al acceder al archivo que contiene la información de los Códigos Postales y sus datos asociados.
     * @throws DireccionesArchivoCodigosPostalesVacioException Se lanza si el archivo que contiene 
     * la información de los Códigos Postales y sus datos asociados está vacío.
     */
    public abstract InformacionDerivadaCPDireccionDTONegocios obtenerDatosDireccionDerivados(CodigoPostalDTONegocios codigoPostalDTONegocios)
            throws DireccionesAccesoArchivoCodigosPostalesFallidoException, 
            DireccionesArchivoCodigosPostalesVacioException;
    
    
}
