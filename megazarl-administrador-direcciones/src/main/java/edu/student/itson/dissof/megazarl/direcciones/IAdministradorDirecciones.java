
package edu.student.itson.dissof.megazarl.direcciones;

import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.dto.infraestructura.DireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.CodigoPostalDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDerivadaCPDireccionDTO;

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
    
    public abstract DireccionDTO obtenerDireccion(IdDireccionDTO idDireccionDTO);
    
    public abstract boolean validarDireccion(IdDireccionDTO idDireccionDTO);
    
    public abstract DireccionDTO registrarDireccion(DireccionDTO direccionDTO) 
            throws DireccionesAccesoArchivoCodigosPostalesFallidoException,
            DireccionesArchivoCodigosPostalesVacioException;
    
    /**
     * Método que permite obtener el Estado, Ciudad y Colonia asociados al Código Postal del parámetro.
     * @param codigoPostalDTO Objeto String que representa el Código Postal a buscar.
     * @return Objeto InformacionDerivadaCPDireccionEnvioDTO, un DTO que contiene el Estado, Ciudad
     *  y Colonia asociados al Código Postal del parámetro; nul si el código postal es inválido.
     * @throws DireccionesAccesoArchivoCodigosPostalesFallidoException Se lanza si ocurre un error
     * al acceder al archivo que contiene la información de los Códigos Postales y sus datos asociados.
     * @throws DireccionesArchivoCodigosPostalesVacioException Se lanza si el archivo que contiene 
     * la información de los Códigos Postales y sus datos asociados está vacío.
     */
    public abstract InformacionDerivadaCPDireccionDTO obtenerDatosDireccionDerivados(CodigoPostalDTO codigoPostalDTO)
            throws DireccionesAccesoArchivoCodigosPostalesFallidoException, 
            DireccionesArchivoCodigosPostalesVacioException;
    
    
}
