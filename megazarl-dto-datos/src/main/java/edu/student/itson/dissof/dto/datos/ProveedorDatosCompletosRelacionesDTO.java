
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenerico;
import java.util.LinkedList;
import java.util.List;


public class ProveedorDatosCompletosRelacionesDTO extends ProveedorDTO {
    
    private List<ProductoDTO> productosOfrecidos;
    
    private DireccionDTO direccion;
    
    
    public ProveedorDatosCompletosRelacionesDTO(
            IdEntidadGenerico id, 
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
    public List<IdEntidadGenerico> getListaIdsProductosOfrecidos() {
        
        List<IdEntidadGenerico> idsProductosOfrecidos = new LinkedList<>();
        
        for(ProductoDTO productoDTO: productosOfrecidos){
            idsProductosOfrecidos.add(productoDTO.getId());
        }
        
        return idsProductosOfrecidos;
    }

    @Override
    public IdEntidadGenerico getIdDireccion() {
        return direccion.getId();
    }
    
}
