
package edu.student.itson.dissof.administradorproveedores;

import edu.student.itson.dissof.administradorproveedores.excepciones.ProveedoresIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProveedorON;
import java.util.List;

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
    private List<ProveedorON> listaProveedores;

    /**
     * Constructor que inicializa un administrador de proveedores con la lista de proveedores.
     *
     * @param listaProveedores Objeto List que contiene los proveedores registrados en el sistema.
     */
    public FAdministradorProveedores(List<ProveedorON> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }

    /**
     * Implementación del método validarProveedor(), de la interfaz
     * {@link IAdministradorProveedores}, que permite verificar si el ID
     * de un proveedor corresponde a un objeto Proveedor real.
     *
     * @param idProveedor Objeto Integer que representa el ID del proveedor a validar.
     * @return true si existe un objeto Proveedor con el ID del parámetro, false en caso contrario.
     */
    @Override
    public boolean validarProveedor(Integer idProveedor){
        for(ProveedorON proveedor: listaProveedores){
            if(proveedor.getId() == idProveedor){
                return true;
            }
        }
        
        return false;
    }

    /**
     * Implementación del método obtenerProveedor(), de la interfaz
     * {@link IAdministradorProveedores}, que permite obtener un objeto Proveedor
     * específico a partir de su ID.
     *
     * @param idProveedor Objeto Integer que representa el ID del proveedor a obtener.
     * @return Objeto Proveedor que representa el proveedor con el ID especificado,
     * o null si no se encuentra un proveedor con ese ID.
     */
    public ProveedorON obtenerProveedor(Integer idProveedor){
        ProveedorON proveedorRecuperado = null;
        for(ProveedorON proveedor: listaProveedores){
            if(proveedor.getId() == idProveedor){
                proveedorRecuperado = proveedor;
            }
        }
        return proveedorRecuperado;
    }

    /**
     * Implementación del método obtenerDireccionImagenProveedor(), de la interfaz
     * {@link IAdministradorProveedores}, que permite obtener la ruta de la imagen
     * o logotipo de un proveedor específico.
     *
     * @param idProveedor Objeto Integer que representa el ID del proveedor.
     * @return Objeto String que representa la ruta de la imagen del proveedor.
     * @throws ProveedoresIdProveedorInvalidoException Se lanza si se comprueba que el ID
     * del proveedor es inválido o no existe en el sistema.
     */
    @Override
    public String obtenerDireccionImagenProveedor(Integer idProveedor) throws ProveedoresIdProveedorInvalidoException{
        
        // Se valida el ID del proevedor.
        if(!validarProveedor(idProveedor)){
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor: " + idProveedor + " es inválido.");
        }
        
        ProveedorON proveedor = obtenerProveedor(idProveedor);
        
        if(proveedor == null){
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor: " + idProveedor + " es inválido.");
        }
        
        // Se obtiene y devuelve la imagen del proveedor.
        return proveedor.getDireccionImagenProveedor();
    }

    /**
     * Implementación del método obtenerNombreProveedor(), de la interfaz
     * {@link IAdministradorProveedores}, que permite obtener el nombre
     * de un proveedor específico.
     *
     * @param idProveedor Objeto Integer que representa el ID del proveedor.
     * @return Objeto String que representa el nombre del proveedor.
     * @throws ProveedoresIdProveedorInvalidoException Se lanza si se comprueba que el ID
     * del proveedor es inválido o no existe en el sistema.
     */
    @Override
    public String obtenerNombreProveedor(Integer idProveedor) throws ProveedoresIdProveedorInvalidoException{
        // Se valida el ID del proevedor.
        if(!validarProveedor(idProveedor)){
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor: " + idProveedor + " es inválido.");
        }
        
        ProveedorON proveedor = obtenerProveedor(idProveedor);
        
        if(proveedor == null){
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor: " + idProveedor + " es inválido.");
        }
        
        // Se obtiene y devuelve el nombre del proveedor.
        return proveedor.getNombre();

    }
}
