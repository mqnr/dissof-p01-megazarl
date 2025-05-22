
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;
import java.util.List;


public class CarritoComprasIdsRelacionesDTO extends CarritoComprasDTO{

    private IdEntidadGenerico idCliente;

    private IdEntidadGenerico idPaqueteria;

    private List<IdEntidadGenerico> idsProductosCarrito;

    public CarritoComprasIdsRelacionesDTO(
            IdEntidadGenerico id,
            Boolean esVigente,
            IdEntidadGenerico idCliente,
            IdEntidadGenerico idPaqueteria,
            List<IdEntidadGenerico> idsProductosCarrito) {
        
        super(id, esVigente);
        
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
        this.idsProductosCarrito = idsProductosCarrito;
    }
    
    public CarritoComprasIdsRelacionesDTO(
            IdEntidadGenerico id,
            Boolean esVigente,
            IdEntidadGenerico idCliente,
            List<IdEntidadGenerico> idsProductosCarrito) {
        
        super(id, esVigente);
        
        this.idCliente = idCliente;
        this.idsProductosCarrito = idsProductosCarrito;
    }
    

    public void setIdPaqueteria(IdEntidadGenerico idPaqueteria){
        this.idPaqueteria = idPaqueteria;
    }

    @Override
    public IdEntidadGenerico getIdCliente() {
        return idCliente;
    }

    @Override
    public IdEntidadGenerico getIdPaqueteria() {
        return idPaqueteria;
    }

    @Override
    public List<IdEntidadGenerico> getIdsProductosCarrito() {
        return idsProductosCarrito;
    }
    
}
