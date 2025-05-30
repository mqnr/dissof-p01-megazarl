
package edu.student.itson.dissof.administradorproveedores;

import edu.student.itson.dissof.administradorproveedores.excepciones.ProveedoresIdProveedorInvalidoException;
import edu.student.itson.dissof.administradorproveedores.excepciones.ProveedoresPersistenciaException;

import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProveedorInicioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDTONegocios;
import java.util.List;

/**
 * IAdministradorProveedores.java
 *
 * Interfaz del subsistema AdministradorProveedores, que se encarga de gestionar
 * los proveedores de semillas registrados en el sistema, permitiendo validar su
 * existencia y obtener información detallada.
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

public interface IAdministradorProveedores {

    /**
     * Método que permite obtener la ruta de la imagen o logotipo de un proveedor
     * específico identificado por su ID.
     *
     * @param idProveedorDTO Objeto IdProveedorDTONegocios que contiene el ID del proveedor.
     * @return Objeto String que representa la ruta de la imagen del proveedor.
     * @throws ProveedoresIdProveedorInvalidoException Se lanza si se comprueba que el ID
     * del proveedor es inválido, dentro de este subsistema.
     */
    public abstract String obtenerDireccionImagenProveedor(IdProveedorDTONegocios idProveedorDTO)
            throws ProveedoresIdProveedorInvalidoException,
            ProveedoresPersistenciaException;

    /**
     * Método que permite obtener el nombre de un proveedor específico identificado por su ID.
     *
     * @param idProveedorDTO Objeto IdProveedorDTONegocios que contiene el ID del proveedor.
     * @return Objeto String que representa el nombre del proveedor.
     * @throws ProveedoresIdProveedorInvalidoException Se lanza si se comprueba que el ID
     * del proveedor es inválido, dentro de este subsistema.
     */
    public abstract String obtenerNombreProveedor(IdProveedorDTONegocios idProveedorDTO) 
            throws ProveedoresIdProveedorInvalidoException,
            ProveedoresPersistenciaException;

    /**
     * Método que permite obtener un proveedor específico identificado por su ID.
     *
     * @param idProveedorDTO Objeto IdProveedorDTONegocios que representa el ID del proveedor a obtener.
     * @return Objeto Proveedor que representa el proveedor con el ID especificado.
     */
    public abstract ProveedorDTONegocios obtenerProveedor(IdProveedorDTONegocios idProveedorDTO) throws ProveedoresPersistenciaException;
    
    /**
     * Método que permite verificar si el ID de un proveedor es válido.
     *
     * @param idProveedorDTO Objeto IdProveedorDTONegocios que contiene el ID del proveedor a validar.
     * @return true si el ID del proveedor es válido, false en caso contrario.
     */
    public abstract boolean validarProveedor(IdProveedorDTONegocios idProveedorDTO) throws ProveedoresPersistenciaException;

    /**
     * Método que permite obtener la lista de todos los proveedores registrados en el sistema.
     *
     * @return Objeto List de InformacionProveedorInicioDTONegocios que contiene la información
 resumida de los proveedores disponibles.
     */
    public abstract List<InformacionProveedorInicioDTONegocios> obtenerProveedores();
}
