
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import java.util.HashMap;

/**
 * InformacionCrearPedidoDTONegocios.java

 Clase DTO que contiene toda la información necesaria para crear un nuevo pedido 
 en el sistema, incluyendo el carrito de compras, el cliente, la paquetería seleccionada 
 y un mapa con los identificadores de productos y sus cantidades respectivas.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 *
 */
public class InformacionCrearPedidoDTONegocios {
    
    /**
     * Objeto Long que representa el ID del cliente que realiza el pedido.
     */
    private IdEntidadGenericoNegocios idCliente;
    
    /**
     * Objeto Long que representa el ID de la paquetería con la que se enviarán
     * los productos del pedido.
     */
    private IdEntidadGenericoNegocios idPaqueteria;
    
    /**
     * Mapa que representa la cantidad requerida por producto, las llaves son
     * los ID de los productos seleccionados, el valor es la cantidad seleciconada
     * de estos productos.
     */
    private HashMap<IdEntidadGenericoNegocios, Integer> mapaIdsProductosCantidad;


    /**
     * Constructor de la clase que recibe como parámetros
     * @param idCliente                     Objeto Long que representa el ID del cliente que realiza el pedido.
     * @param idPaqueteria                  Objeto Long que representa el ID de la paquetería con la que se enviarán
     *                                      los productos del pedido.
     * @param mapaIdsProductosCantidad      Mapa que representa la cantidad requerida por producto, las llaves son
     *                                      los ID de los productos seleccionados, el valor es la cantidad seleciconada
     *                                      de estos productos.
     */
    public InformacionCrearPedidoDTONegocios(
            IdEntidadGenericoNegocios idCliente,
            IdEntidadGenericoNegocios idPaqueteria,
            HashMap<IdEntidadGenericoNegocios, Integer> mapaIdsProductosCantidad) {
        
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
        this.mapaIdsProductosCantidad = mapaIdsProductosCantidad;
    }


    /**
     * Método que permite obtener el ID del cliente que realiza el pedido.
     * @return Objeto Long que representa el ID del cliente que realiza el pedido.
     */
    public IdEntidadGenericoNegocios getIdCliente() {
        return idCliente;
    }

    /**
     * Método que permite obtener el ID de la paquetería con la que se enviarán
     * los productos del pedido.
     * @return Objeto Long que representa el ID de la paquetería con la que se enviarán
     * los productos del pedido.
     */
    public IdEntidadGenericoNegocios getIdPaqueteria() {
        return idPaqueteria;
    }

    /**
     * Método que permite obtener el mapa que contiene los productos solicitados por
     * el pedido junto con la cantidad seleccionada de cada uno.
     * @return Mapa que representa la cantidad requerida por producto, las llaves son
     * los ID de los productos seleccionados, el valor es la cantidad seleciconada
     * de estos productos.
     */
    public HashMap<IdEntidadGenericoNegocios, Integer> getMapaIdsProductosCantidad() {
        return mapaIdsProductosCantidad;
    }
    
}
