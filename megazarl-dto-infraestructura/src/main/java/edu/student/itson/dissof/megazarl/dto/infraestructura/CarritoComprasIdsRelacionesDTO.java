
package edu.student.itson.dissof.megazarl.dto.infraestructura;

import java.util.List;


public class CarritoComprasIdsRelacionesDTO extends CarritoComprasDTO{

    private IdClienteDTO idCliente;

    private IdPaqueteriaDTO idPaqueteria;

    private List<IdProductoCarritoDTO> idsProductosCarrito;

    public CarritoComprasIdsRelacionesDTO(
            Long id,
            Boolean esVigente,
            IdClienteDTO idCliente,
            IdPaqueteriaDTO idPaqueteria,
            List<IdProductoCarritoDTO> idsProductosCarrito) {
        
        super(id, esVigente);
        
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
        this.idsProductosCarrito = idsProductosCarrito;
    }
    
    public CarritoComprasIdsRelacionesDTO(
            Long id,
            Boolean esVigente,
            IdClienteDTO idCliente,
            List<IdProductoCarritoDTO> idsProductosCarrito) {
        
        super(id, esVigente);
        
        this.idCliente = idCliente;
    }

    public void setIdPaqueteria(IdPaqueteriaDTO idPaqueteria){
        this.idPaqueteria = idPaqueteria;
    }

    @Override
    public IdClienteDTO getIdCliente() {
        return idCliente;
    }

    @Override
    public IdPaqueteriaDTO getIdPaqueteria() {
        return idPaqueteria;
    }

    @Override
    public List<IdProductoCarritoDTO> getIdsProductosCarrito() {
        return idsProductosCarrito;
    }
    
}
