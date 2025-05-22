
package edu.student.itson.dissof.megazarl.dto.negocios;

import java.util.LinkedList;
import java.util.List;


public class ProveedorDatosCompletosRelacionesDTO extends ProveedorDTO {
    
    private List<ProductoDTO> productosOfrecidos;
    
    private DireccionDTO direccion;
    
    
    public ProveedorDatosCompletosRelacionesDTO(
            Long id, 
            String nombre,
            String telefono,
            String correoElectronico, 
            String direccionImagen, 
            List<ProductoDTO> productosOfrecidos, 
            DireccionDTO direccion) {
        
        super(
            id, 
            nombre,
            telefono,
            correoElectronico,
            direccionImagen);
        
        this.productosOfrecidos = productosOfrecidos;
        this.direccion = direccion;
    }
    
    public ProveedorDatosCompletosRelacionesDTO(
            String nombre,
            String telefono,
            String correoElectronico, 
            String direccionImagen, 
            List<ProductoDTO> productosOfrecidos, 
            DireccionDTO direccion) {
        
        super(
            nombre,
            telefono,
            correoElectronico,
            direccionImagen);
        
        this.productosOfrecidos = productosOfrecidos;
        this.direccion = direccion;
    }

    @Override
    public List<Long> getListaIdsProductosOfrecidos() {
        
        List<Long> idsProductosOfrecidos = new LinkedList<>();
        
        for(ProductoDTO productoDTO: productosOfrecidos){
            idsProductosOfrecidos.add(productoDTO.getId());
        }
        
        return idsProductosOfrecidos;
    }

    @Override
    public Long getIdDireccion() {
        return direccion.getId();
    }
    
}
