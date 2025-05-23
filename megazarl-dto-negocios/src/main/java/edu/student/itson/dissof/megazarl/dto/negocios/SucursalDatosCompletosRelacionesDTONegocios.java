
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import java.util.LinkedList;
import java.util.List;


public class SucursalDatosCompletosRelacionesDTONegocios extends SucursalDTONegocios{

    /**
     * Objeto DireccionDTONegocios que representa la dirección de la sucursal.
     */
    private DireccionDTONegocios direccion;
    
    private List<ProductoInventarioDTONegocios> productosInventario;
    
    public SucursalDatosCompletosRelacionesDTONegocios(
            IdEntidadGenericoNegocios id,
            Boolean esMatriz,
            DireccionDTONegocios direccion,
            List<ProductoInventarioDTONegocios> productosInventario){
        
        super(
              id,
              esMatriz);
        
        this.direccion = direccion;
        this.productosInventario = productosInventario;
    }
    
    public SucursalDatosCompletosRelacionesDTONegocios(
            Boolean esMatriz,
            DireccionDTONegocios direccion,
            List<ProductoInventarioDTONegocios> productosInventario) {
        
        super(
              esMatriz);
        
        this.direccion = direccion;
        this.productosInventario = productosInventario;
    }
    
    public List<ProductoInventarioDTONegocios> getProductosInventario(){
        return productosInventario;
    }

    @Override
    public List<IdEntidadGenericoNegocios> getIdsProductosInventario() {
        
        List<IdEntidadGenericoNegocios> idsProductoInventario = new LinkedList<>();
        
        for(ProductoInventarioDTONegocios productoInventario: productosInventario){
            idsProductoInventario.add(productoInventario.getId());
        }
        
        return idsProductoInventario;
    }
    
    @Override
    public IdEntidadGenericoNegocios getIdDireccion() {
        return direccion.getId();
    }

    
    
}
