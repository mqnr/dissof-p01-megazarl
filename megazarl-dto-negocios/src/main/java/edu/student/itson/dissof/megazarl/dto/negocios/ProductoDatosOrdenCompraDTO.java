package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;
import java.util.List;

/**
 * 
 * ProductoDatosOrdenCompraDTO.java
 * 
 * Clase DTO que representa los datos de un producto ofrecido por la empresa 
 * (adaptado para las necesidades del caso de uso Orden de COMPRA)
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * 
 */
public class ProductoDatosOrdenCompraDTO extends ProductoOrdenCompraDTO{
    
    private ProveedorDTO proveedor;
    
    public ProductoDatosOrdenCompraDTO(
            IdEntidadGenerico id, 
            String nombre,
            String variedad, 
            String descripcion,
            Integer milesSemillas,
            Double precio,
            Double pesoKg, 
            String direccionImagen,
            ProveedorDTO proveedor) {
        
        super(
            id, 
            nombre,
            variedad,
            descripcion,
            milesSemillas,
            precio,
            pesoKg,
            direccionImagen);
        
        this.proveedor = proveedor;
    }
    
    public ProductoDatosOrdenCompraDTO(
            String nombre,
            String variedad, 
            String descripcion,
            Integer milesSemillas,
            Double precio,
            Double pesoKg, 
            String direccionImagen,
            ProveedorDTO proveedor) {
        
        super(
            nombre,
            variedad,
            descripcion,
            milesSemillas,
            precio,
            pesoKg,
            direccionImagen);
        
        this.proveedor = proveedor;
    }
    
    @Override
    public IdEntidadGenerico getIdProveedor() {
        return proveedor.getId();
    }
    
}
