
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import java.util.List;


public class CarritoComprasDTONegocios{

    private IdEntidadGenericoNegocios id;
    
    private Boolean esVigente;

    private IdEntidadGenericoNegocios idCliente;

    private IdEntidadGenericoNegocios idPaqueteria;

    private List<ProductoCarritoDTONegocios> productosCarrito;

    public CarritoComprasDTONegocios(
            IdEntidadGenericoNegocios id,
            Boolean esVigente,
            IdEntidadGenericoNegocios idCliente,
            IdEntidadGenericoNegocios idPaqueteria,
            List<ProductoCarritoDTONegocios> productosCarrito) {
        
        this.id = id;
        this.esVigente = esVigente;
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
        this.productosCarrito = productosCarrito;
    }
    
    public CarritoComprasDTONegocios(
            IdEntidadGenericoNegocios id,
            Boolean esVigente,
            IdEntidadGenericoNegocios idCliente,
            List<ProductoCarritoDTONegocios> idsProductosCarrito) {

        this.id = id;
        this.esVigente = esVigente;
        this.idCliente = idCliente;
        this.productosCarrito = idsProductosCarrito;
    }
    
    public CarritoComprasDTONegocios(
            Boolean esVigente,
            IdEntidadGenericoNegocios idCliente,
            List<ProductoCarritoDTONegocios> idsProductosCarrito) {

        this.esVigente = esVigente;
        this.idCliente = idCliente;
        this.productosCarrito = idsProductosCarrito;
    }
    

    public void setIdPaqueteria(IdEntidadGenericoNegocios idPaqueteria){
        this.idPaqueteria = idPaqueteria;
    }

    public IdEntidadGenericoNegocios getIdCliente() {
        return idCliente;
    }

    public IdEntidadGenericoNegocios getIdPaqueteria() {
        return idPaqueteria;
    }

    public IdEntidadGenericoNegocios getId() {
        return id;
    }

    public Boolean getEsVigente() {
        return esVigente;
    }
    
    public List<ProductoCarritoDTONegocios> getProductosCarrito() {
        return productosCarrito;
    }
    
}
