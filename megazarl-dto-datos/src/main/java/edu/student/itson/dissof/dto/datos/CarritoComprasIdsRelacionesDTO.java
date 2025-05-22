
package edu.student.itson.dissof.dto.datos;

import java.util.List;


public class CarritoComprasIdsRelacionesDTO extends CarritoComprasDTO{

    private Long idCliente;

    private Long idPaqueteria;

    private List<Long> idsProductosCarrito;

    public CarritoComprasIdsRelacionesDTO(
            Long id,
            Boolean esVigente,
            Long idCliente,
            Long idPaqueteria,
            List<Long> idsProductosCarrito) {
        
        super(id, esVigente);
        
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
        this.idsProductosCarrito = idsProductosCarrito;
    }
    
    public CarritoComprasIdsRelacionesDTO(
            Long id,
            Boolean esVigente,
            Long idCliente,
            List<Long> idsProductosCarrito) {
        
        super(id, esVigente);
        
        this.idCliente = idCliente;
        this.idsProductosCarrito = idsProductosCarrito;
    }
    

    public void setIdPaqueteria(Long idPaqueteria){
        this.idPaqueteria = idPaqueteria;
    }

    @Override
    public Long getIdCliente() {
        return idCliente;
    }

    @Override
    public Long getIdPaqueteria() {
        return idPaqueteria;
    }

    @Override
    public List<Long> getIdsProductosCarrito() {
        return idsProductosCarrito;
    }
    
}
