
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import java.util.List;


public class CarritoComprasDTODatos{

    private IdEntidadGenericoDatos id;
    
    private Boolean esVigente;
    
    private IdEntidadGenericoDatos idCliente;

    private IdEntidadGenericoDatos idPaqueteria;

    private List<ProductoCarritoDTODatos> productosCarrito;

    public CarritoComprasDTODatos(
            IdEntidadGenericoDatos id,
            Boolean esVigente,
            IdEntidadGenericoDatos idCliente,
            IdEntidadGenericoDatos idPaqueteria,
            List<ProductoCarritoDTODatos> idsProductosCarrito) {
        
        this.id = id;
        this.esVigente = esVigente;
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
        this.productosCarrito = idsProductosCarrito;
    }
    
    public CarritoComprasDTODatos(
            IdEntidadGenericoDatos id,
            Boolean esVigente,
            IdEntidadGenericoDatos idCliente,
            List<ProductoCarritoDTODatos> productosCarrito) {
        
        this.id = id;
        this.esVigente = esVigente;
        this.idCliente = idCliente;
        this.productosCarrito = productosCarrito;
    }
    
    public CarritoComprasDTODatos(
            Boolean esVigente,
            IdEntidadGenericoDatos idCliente,
            IdEntidadGenericoDatos idPaqueteria,
            List<ProductoCarritoDTODatos> productosCarrito) {
        
        this.esVigente = esVigente;
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
        this.productosCarrito = productosCarrito;
    }
    

    public void setIdPaqueteria(IdEntidadGenericoDatos idPaqueteria){
        this.idPaqueteria = idPaqueteria;
    }

    public IdEntidadGenericoDatos getId() {
        return id;
    }

    public Boolean getEsVigente() {
        return esVigente;
    }

    public IdEntidadGenericoDatos getIdCliente() {
        return idCliente;
    }


    public IdEntidadGenericoDatos getIdPaqueteria() {
        return idPaqueteria;
    }

    public List<ProductoCarritoDTODatos> getProductosCarrito() {
        return productosCarrito;
    }
    
}
