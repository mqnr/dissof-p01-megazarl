
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import java.util.LinkedList;
import java.util.List;


public class SucursalDTODatos {

    
    /**
     * Objeto IdEntidadGenericoDatos que representa el ID de la sucursal
     */
    private IdEntidadGenericoDatos id;
    
    /**
     * Objeto Boolean que indica si la sucursal es la Matriz
     */
    private Boolean esMatriz;
    
    /**
     * Objeto DireccionDTODatos que representa la dirección de la sucursal.
     */
    private DireccionDTODatos direccion;
    
    private List<ProductoInventarioDTODatos> productosInventario;
    
    public SucursalDTODatos(
            IdEntidadGenericoDatos id,
            Boolean esMatriz,
            DireccionDTODatos direccion,
            List<ProductoInventarioDTODatos> productosInventario){
        
        this.id = id;
        this.esMatriz = esMatriz;
        this.direccion = direccion;
        this.productosInventario = productosInventario;
    }
    
    public SucursalDTODatos(
            IdEntidadGenericoDatos id,
            Boolean esMatriz,
            DireccionDTODatos direccion){
        
        this.id = id;
        this.esMatriz = esMatriz;
        this.direccion = direccion;
    }
    
    public SucursalDTODatos(
            Boolean esMatriz,
            DireccionDTODatos direccion) {
        
        this.esMatriz = esMatriz;
        this.direccion = direccion;
    }

    public Boolean getEsMatriz() {
        return esMatriz;
    }
    
    public List<ProductoInventarioDTODatos> getProductosInventario(){
        return productosInventario;
    }

    public IdEntidadGenericoDatos getId() {
        return id;
    }
    
    public DireccionDTODatos getDireccion() {
        return direccion;
    }

    
    
}
