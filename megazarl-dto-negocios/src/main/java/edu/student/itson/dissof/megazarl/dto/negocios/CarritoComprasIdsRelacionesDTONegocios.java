
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import java.util.List;


public class CarritoComprasIdsRelacionesDTONegocios extends CarritoComprasDTONegocios{

    private IdEntidadGenericoNegocios idCliente;

    private IdEntidadGenericoNegocios idPaqueteria;

    private List<IdEntidadGenericoNegocios> idsProductosCarrito;

    public CarritoComprasIdsRelacionesDTONegocios(
            IdEntidadGenericoNegocios id,
            Boolean esVigente,
            IdEntidadGenericoNegocios idCliente,
            IdEntidadGenericoNegocios idPaqueteria,
            List<IdEntidadGenericoNegocios> idsProductosCarrito) {
        
        super(id, esVigente);
        
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
        this.idsProductosCarrito = idsProductosCarrito;
    }
    
    public CarritoComprasIdsRelacionesDTONegocios(
            IdEntidadGenericoNegocios id,
            Boolean esVigente,
            IdEntidadGenericoNegocios idCliente,
            List<IdEntidadGenericoNegocios> idsProductosCarrito) {
        
        super(id, esVigente);
        
        this.idCliente = idCliente;
        this.idsProductosCarrito = idsProductosCarrito;
    }
    

    public void setIdPaqueteria(IdEntidadGenericoNegocios idPaqueteria){
        this.idPaqueteria = idPaqueteria;
    }

    @Override
    public IdEntidadGenericoNegocios getIdCliente() {
        return idCliente;
    }

    @Override
    public IdEntidadGenericoNegocios getIdPaqueteria() {
        return idPaqueteria;
    }

    @Override
    public List<IdEntidadGenericoNegocios> getIdsProductosCarrito() {
        return idsProductosCarrito;
    }
    
}
