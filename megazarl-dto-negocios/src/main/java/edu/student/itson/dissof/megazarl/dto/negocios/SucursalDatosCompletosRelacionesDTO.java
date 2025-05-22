
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;
import java.util.LinkedList;
import java.util.List;


public class SucursalDatosCompletosRelacionesDTO extends SucursalDTO{

    /**
     * Objeto DireccionDTO que representa la dirección de la sucursal.
     */
    private DireccionDTO direccion;
    
    private List<ProductoInventarioDTO> productosInventario;
    
    public SucursalDatosCompletosRelacionesDTO(
            IdEntidadGenerico id,
            Boolean esMatriz,
            DireccionDTO direccion,
            List<ProductoInventarioDTO> productosInventario){
        
        super(
              id,
              esMatriz);
        
        this.direccion = direccion;
        this.productosInventario = productosInventario;
    }
    
    public SucursalDatosCompletosRelacionesDTO(
            Boolean esMatriz,
            DireccionDTO direccion,
            List<ProductoInventarioDTO> productosInventario) {
        
        super(
              esMatriz);
        
        this.direccion = direccion;
        this.productosInventario = productosInventario;
    }
    
    public List<ProductoInventarioDTO> getProductosInventario(){
        return productosInventario;
    }

    @Override
    public List<IdEntidadGenerico> getIdsProductosInventario() {
        
        List<IdEntidadGenerico> idsProductoInventario = new LinkedList<>();
        
        for(ProductoInventarioDTO productoInventario: productosInventario){
            idsProductoInventario.add(productoInventario.getId());
        }
        
        return idsProductoInventario;
    }
    
    @Override
    public IdEntidadGenerico getIdDireccion() {
        return direccion.getId();
    }

    
    
}
