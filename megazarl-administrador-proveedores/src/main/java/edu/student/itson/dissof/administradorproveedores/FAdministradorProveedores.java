
package edu.student.itson.dissof.administradorproveedores;

import edu.student.itson.dissof.administradorproveedores.excepciones.ProveedoresIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTO;

/**
 * FAdministradorProveedores.java
 *
 * Clase que implementa la interfaz IAdministradorProveedores, proporcionando
 * la funcionalidad para gestionar los proveedores de semillas registrados en el sistema,
 * validar su existencia y obtener información detallada.
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
public class FAdministradorProveedores implements IAdministradorProveedores{

    private final AdministradorProveedores administradorProveedores;
    
    public FAdministradorProveedores(){
        
        this.administradorProveedores = new AdministradorProveedores();
        
    }
    
    /**
     * Implementación del método validarProveedor(), de la interfaz
     * {@link IAdministradorProveedores}, que permite verificar si el ID
     * de un proveedor corresponde a un objeto Proveedor real.
     *
     * @param idProveedorDTO Objeto IdProveedorDTO que contiene el ID del proveedor a validar.
     * @return true si existe un objeto Proveedor con el ID del parámetro, false en caso contrario.
     */
    @Override
    public boolean validarProveedor(IdProveedorDTO idProveedorDTO) {
        return administradorProveedores.validarProveedor(idProveedorDTO);
    }

    /**
     * Implementación del método obtenerProveedor(), de la interfaz
     * {@link IAdministradorProveedores}, que permite obtener un objeto Proveedor
     * específico a partir de su ID.
     *
     * @param idProveedorDTO Objeto IdProveedorDTO que representa el ID del proveedor a obtener.
     * @return Objeto Proveedor que representa el proveedor con el ID especificado,
     * o null si no se encuentra un proveedor con ese ID.
     */
    @Override
    public ProveedorDTO obtenerProveedor(IdProveedorDTO idProveedorDTO) {
        return administradorProveedores.obtenerProveedor(idProveedorDTO);
    }

    /**
     * Implementación del método obtenerDireccionImagenProveedor(), de la interfaz
     * {@link IAdministradorProveedores}, que permite obtener la ruta de la imagen
     * o logotipo de un proveedor específico.
     *
     * @param idProveedorDTO Objeto IdProveedorDTO que contiene el ID del proveedor.
     * @return Objeto String que representa la ruta de la imagen del proveedor.
     * @throws ProveedoresIdProveedorInvalidoException Se lanza si se comprueba que el ID
     * del proveedor es inválido o no existe en el sistema.
     */
    @Override
    public String obtenerDireccionImagenProveedor(IdProveedorDTO idProveedorDTO) throws ProveedoresIdProveedorInvalidoException {
        return administradorProveedores.obtenerDireccionImagenProveedor(idProveedorDTO);
    }

    /**
     * Implementación del método obtenerNombreProveedor(), de la interfaz
     * {@link IAdministradorProveedores}, que permite obtener el nombre
     * de un proveedor específico.
     *
     * @param idProveedorDTO Objeto IdProveedorDTO que contiene el ID del proveedor.
     * @return Objeto String que representa el nombre del proveedor.
     * @throws ProveedoresIdProveedorInvalidoException Se lanza si se comprueba que el ID
     * del proveedor es inválido o no existe en el sistema.
     */
    @Override
    public String obtenerNombreProveedor(IdProveedorDTO idProveedorDTO) throws ProveedoresIdProveedorInvalidoException {
        return administradorProveedores.obtenerNombreProveedor(idProveedorDTO);
    }

        /**
     * Implementación del método agregarProveedor(), de la interfaz
     * {@link IAdministradorProveedores}, que permite registrar un proveedor.
     * @param proveedorDTO objeto ProveedorDTO que contiene el proveedor
     * @return objeto boolean que representa el proveedor 
     * @throws ProveedorNoRegistradoException  se lanza si no se registra el proveedor
     */
    
    /**
    @Override
    public boolean agregarProveedor(ProveedorDTO proveedorDTO) throws ProveedorNoRegistradoException {
        return administradorProveedores.agregarProveedor(proveedorDTO);
    }
    * */
}
