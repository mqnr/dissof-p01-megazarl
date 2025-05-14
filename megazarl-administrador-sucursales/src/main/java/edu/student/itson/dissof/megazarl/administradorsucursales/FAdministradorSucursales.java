
package edu.student.itson.dissof.megazarl.administradorsucursales;

import edu.student.itson.dissof.megazarl.administradorsucursales.excepciones.SucursalesIdSucursalException;
import edu.student.itson.dissof.megazarl.dto.infraestructura.SucursalDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.CodigosSucursalesDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdSucursalDTO;


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
    
    public FAdministradorSucursales(){
        
        this.administradorSucursales = new AdministradorSucursales();
 
    }
    
    /**
     * Método que permite verificar si el ID de una sucursal es válido.
     *
     * @param idSucursalDTO Objeto IdSucursalDTO que contiene el ID de la sucursal a validar.
     * @return true si el ID de la sucursal es válido y existe en el sistema, false en caso contrario.
     */
    @Override
    public boolean validarSucursal(IdSucursalDTO idSucursalDTO) {
        return administradorSucursales.validarSucursal(idSucursalDTO);
    }

    /**
     * Método que permite obtener la lista de IDs de todas las sucursales registradas en el sistema.
     *
     * @return Objeto CodigosSucursalesDTO que contiene la lista de IDs de las sucursales.
     */
    @Override
    public CodigosSucursalesDTO obtenerCodigosSucursales() throws SucursalesIdSucursalException {
        return administradorSucursales.obtenerCodigosSucursales();
    }

    /**
     * Método que permite obtener el código postal de una sucursal específica.
     *
     * @param idSucursalDTO Objeto IdSucursalDTO que contiene el ID de la sucursal.
     * @return Objeto String que representa el código postal de la sucursal.
     * @throws SucursalesIdSucursalException Se lanza si el ID de la sucursal es inválido o
     * no existe en el sistema.
     */
    @Override
    public String obtenerCodigoPostal(IdSucursalDTO idSucursalDTO) throws SucursalesIdSucursalException {
        return administradorSucursales.obtenerCodigoPostal(idSucursalDTO);
    }

    /**
     * Método que permite obtener la calle donde se ubica una sucursal específica.
     *
     * @param idSucursalDTO Objeto IdSucursalDTO que contiene el ID de la sucursal.
     * @return Objeto String que representa la calle donde se ubica la sucursal.
     * @throws SucursalesIdSucursalException Se lanza si el ID de la sucursal es inválido o
     * no existe en el sistema.
     */
    @Override
    public String obtenerCalle(IdSucursalDTO idSucursalDTO) throws SucursalesIdSucursalException {
        return administradorSucursales.obtenerCalle(idSucursalDTO);
    }

    /**
     * Método que permite obtener el número de domicilio de una sucursal específica.
     *
     * @param idSucursalDTO Objeto IdSucursalDTO que contiene el ID de la sucursal.
     * @return Objeto String que representa el número de domicilio de la sucursal.
     * @throws SucursalesIdSucursalException Se lanza si el ID de la sucursal es inválido o
     * no existe en el sistema.
     */
    @Override
    public String obtenerNumero(IdSucursalDTO idSucursalDTO) throws SucursalesIdSucursalException {
        return administradorSucursales.obtenerNumero(idSucursalDTO);
    }

    /**
     * Método que permite obtener la dirección completa de la sucursal matriz.
     *
     * @return Objeto SucursalDTO que contiene la información de la sucursal matriz
     * de la empresa.
     */
    @Override
    public SucursalDTO obtenerSucursalMatriz() {
        return administradorSucursales.obtenerSucursalMatriz();
    }

    /**
     * Método que permite obtener una sucursal específica a partir de su ID.
     *
     * @param idSucursalDTO Objeto IdSucursalDTO que representa el ID de la sucursal a obtener.
     * @return Objeto Sucursal que representa la sucursal con el ID especificado,
     * o null si no se encuentra una sucursal con ese ID.
     */
    @Override
    public SucursalDTO obtenerSucursal(IdSucursalDTO idSucursalDTO) {
        return administradorSucursales.obtenerSucursal(idSucursalDTO);
    }
}
