
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;


public class ProductoCarritoIdsRelacionesDTONegocios extends ProductoCarritoDTONegocios{


    private IdEntidadGenericoNegocios idCarritoCompras;
    
    /**
     * Objeto ProductoDTO que representa el producto dentro del carrito.
     */
    private IdEntidadGenericoNegocios idProducto;

    public ProductoCarritoIdsRelacionesDTONegocios(
            IdEntidadGenericoNegocios id, 
            Integer cantidad,
            IdEntidadGenericoNegocios idCarritoCompras,
            IdEntidadGenericoNegocios idProducto) {
        
        super(
            id,
            cantidad);

        this.idCarritoCompras = idCarritoCompras;
        this.idProducto = idProducto;

    }
    
    public ProductoCarritoIdsRelacionesDTONegocios(
            Integer cantidad,
            IdEntidadGenericoNegocios idCarritoCompras,
            IdEntidadGenericoNegocios idProducto) {
        
        super(
            cantidad);

        this.idCarritoCompras = idCarritoCompras;
        this.idProducto = idProducto;

    }


    @Override
    public IdEntidadGenericoNegocios getIdCarritoCompras() {
        return idCarritoCompras;
    }

    @Override
    public IdEntidadGenericoNegocios getIdProducto() {
        return idProducto;
    }

    
}
