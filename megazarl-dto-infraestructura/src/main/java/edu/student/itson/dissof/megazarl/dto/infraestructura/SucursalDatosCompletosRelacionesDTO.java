
package edu.student.itson.dissof.megazarl.dto.infraestructura;

import java.util.LinkedList;
import java.util.List;


public class SucursalDatosCompletosRelacionesDTO extends SucursalDTO{

    /**
     * Objeto DireccionDTO que representa la dirección de la sucursal.
     */
    private DireccionDTO direccion;
    
    private List<ProductoInventarioDTO> productosInventario;
    
    public SucursalDatosCompletosRelacionesDTO(
            Long id,
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
    public List<IdProductoInventarioDTO> getIdsProductosInventario() {
        
        List<IdProductoInventarioDTO> idsProductoInventario = new LinkedList<>();
        
        for(ProductoInventarioDTO productoInventario: productosInventario){
            idsProductoInventario.add(new IdProductoInventarioDTO(productoInventario.getId()));
        }
        
        return idsProductoInventario;
    }
    
    @Override
    public IdDireccionDTO getIdDireccion() {
        return new IdDireccionDTO(direccion.getId());
    }

    
    
}
