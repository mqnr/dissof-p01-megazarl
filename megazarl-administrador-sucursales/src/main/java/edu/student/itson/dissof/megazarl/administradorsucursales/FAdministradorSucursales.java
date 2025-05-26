
package edu.student.itson.dissof.megazarl.administradorsucursales;

import edu.student.itson.dissof.megazarl.administradorsucursales.excepciones.SucursalesIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.administradorsucursales.excepciones.SucursalesIdSucursalInvalidoException;
import edu.student.itson.dissof.megazarl.administradorsucursales.excepciones.SucursalesPersistenciaException;
import edu.student.itson.dissof.megazarl.direcciones.IAdministradorDirecciones;

import edu.student.itson.dissof.megazarl.dto.negocios.SucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.CodigosSucursalesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdSucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionSucursalInicioDTONegocios;
import java.util.List;


/**
 * FAdministradorSucursales.java
 *
 * Clase que implementa la interfaz IAdministradorSucursales, proporcionando
 * la funcionalidad para administrar las sucursales registradas en el sistema
 * y obtener información sobre ellas.
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
public class FAdministradorSucursales implements IAdministradorSucursales {
    
    private final AdministradorSucursales administradorSucursales;
    
    public FAdministradorSucursales(IAdministradorDirecciones administradorDirecciones){
        
        this.administradorSucursales = new AdministradorSucursales(administradorDirecciones);
 
    }
    
    /**
     * Método que permite verificar si el ID de una sucursal es válido.
     * @param idSucursalDTONegocios Objeto IdSucursalDTONegocios que contiene el ID de la sucursal a validar.
     * @return true si el ID de la sucursal es válido y existe en el sistema, false en caso contrario.
     */
    @Override
    public boolean validarSucursal(IdSucursalDTONegocios idSucursalDTONegocios) 
            throws SucursalesPersistenciaException {
        
        return administradorSucursales.validarSucursal(idSucursalDTONegocios);
    }

    /**
     * Método que permite obtener la lista de IDs de todas las sucursales registradas en el sistema.
     * @return Objeto CodigosSucursalesDTO que contiene la lista de IDs de las sucursales.
     */
    @Override
    public CodigosSucursalesDTONegocios obtenerCodigosSucursales() throws SucursalesIdSucursalInvalidoException {
        return administradorSucursales.obtenerCodigosSucursales();
    }

    /**
     * Método que permite obtener el código postal de una sucursal específica.
     * @param idSucursalDTONegocios Objeto IdSucursalDTONegocios que contiene el ID de la sucursal.
     * @return Objeto String que representa el código postal de la sucursal.
     * @throws SucursalesIdSucursalInvalidoException Se lanza si el ID de la sucursal es inválido o
     * no existe en el sistema.
     */
    @Override
    public String obtenerCodigoPostal(IdSucursalDTONegocios idSucursalDTONegocios) 
            throws SucursalesIdSucursalInvalidoException,
            SucursalesIdDireccionInvalidoException,
            SucursalesPersistenciaException {
        
        return administradorSucursales.obtenerCodigoPostal(idSucursalDTONegocios);
    }

    /**
     * Método que permite obtener la calle donde se ubica una sucursal específica.
     * @param idSucursalDTO Objeto IdSucursalDTO que contiene el ID de la sucursal.
     * @return Objeto String que representa la calle donde se ubica la sucursal.
     * @throws SucursalesIdSucursalInvalidoException Se lanza si el ID de la sucursal es inválido o
     * no existe en el sistema.
     */
    @Override
    public String obtenerCalle(IdSucursalDTONegocios idSucursalDTO)
            throws SucursalesIdSucursalInvalidoException,
            SucursalesIdDireccionInvalidoException,
            SucursalesPersistenciaException{
        
        return administradorSucursales.obtenerCalle(idSucursalDTO);
    }

    /**
     * Método que permite obtener el número de domicilio de una sucursal específica.
     * @param idSucursalDTO Objeto IdSucursalDTO que contiene el ID de la sucursal.
     * @return Objeto String que representa el número de domicilio de la sucursal.
     * @throws SucursalesIdSucursalInvalidoException Se lanza si el ID de la sucursal es inválido o
     * no existe en el sistema.
     */
    @Override
    public String obtenerNumero(IdSucursalDTONegocios idSucursalDTO)
            throws SucursalesIdSucursalInvalidoException,
            SucursalesIdDireccionInvalidoException,
            SucursalesPersistenciaException {
        
        return administradorSucursales.obtenerNumero(idSucursalDTO);
    }

    /**
     * Método que permite obtener la dirección completa de la sucursal matriz.
     * @return Objeto SucursalDTO que contiene la información de la sucursal matriz
     * de la empresa.
     */
    @Override
    public SucursalDTONegocios obtenerSucursalMatriz() throws SucursalesPersistenciaException {
        
        return administradorSucursales.obtenerSucursalMatriz();
    }

    /**
     * Método que permite obtener una sucursal específica a partir de su ID.
     * @param idSucursalDTONegocios Objeto IdSucursalDTONegocios que representa el ID de la sucursal a obtener.
     * @return Objeto Sucursal que representa la sucursal con el ID especificado,
     * o null si no se encuentra una sucursal con ese ID.
     */
    @Override
    public SucursalDTONegocios obtenerSucursal(IdSucursalDTONegocios idSucursalDTONegocios)
            throws SucursalesPersistenciaException {
        
        return administradorSucursales.obtenerSucursal(idSucursalDTONegocios);
    }

    @Override
    public List<InformacionSucursalInicioDTONegocios> obtenerSucursales() {
        return administradorSucursales.obtenerSucursales();
    }
}