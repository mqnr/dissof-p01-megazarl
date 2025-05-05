
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.CarritoComprasDTO;
import java.util.HashMap;

/**
 * InformacionCrearPedidoDTO.java
 *
 * Clase DTO que contiene toda la información necesaria para crear un nuevo pedido 
 * en el sistema, incluyendo el carrito de compras, el cliente, la paquetería seleccionada 
 * y un mapa con los identificadores de productos y sus cantidades respectivas.
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
public class InformacionCrearPedidoDTO {
    
    /**
     * Objeto Long que representa el ID del cliente que realiza el pedido.
     */
    private Long idCliente;
    
    /**
     * Objeto Long que representa el ID de la paquetería con la que se enviarán
     * los productos del pedido.
     */
    private Long idPaqueteria;
    
    /**
     * Mapa que representa la cantidad requerida por producto, las llaves son
     * los ID de los productos seleccionados, el valor es la cantidad seleciconada
     * de estos productos.
     */
    private HashMap<Long, Integer> mapaIdsProductosCantidad;
    
    /**
     * Objeto CarritoComprasDTO que representa el carrito de compras que está asociado al
     * pedido.
     */
    private CarritoComprasDTO carritoCompras;

    /**
     * Constructor de la clase que recibe como parámetros
     * @param idCliente                     Objeto Long que representa el ID del cliente que realiza el pedido.
     * @param idPaqueteria                  Objeto Long que representa el ID de la paquetería con la que se enviarán
     *                                      los productos del pedido.
     * @param mapaIdsProductosCantidad      Mapa que representa la cantidad requerida por producto, las llaves son
     *                                      los ID de los productos seleccionados, el valor es la cantidad seleciconada
     *                                      de estos productos.
     */
    public InformacionCrearPedidoDTO(
            Long idCliente,
            Long idPaqueteria,
            HashMap<Long, Integer> mapaIdsProductosCantidad) {
        
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
        this.mapaIdsProductosCantidad = mapaIdsProductosCantidad;
    }


    /**
     * Método que permite obtener el ID del cliente que realiza el pedido.
     * @return Objeto Long que representa el ID del cliente que realiza el pedido.
     */
    public Long getIdCliente() {
        return idCliente;
    }

    /**
     * Método que permite obtener el ID de la paquetería con la que se enviarán
     * los productos del pedido.
     * @return Objeto Long que representa el ID de la paquetería con la que se enviarán
     * los productos del pedido.
     */
    public Long getIdPaqueteria() {
        return idPaqueteria;
    }

    /**
     * Método que permite obtener el mapa que contiene los productos solicitados por
     * el pedido junto con la cantidad seleccionada de cada uno.
     * @return Mapa que representa la cantidad requerida por producto, las llaves son
     * los ID de los productos seleccionados, el valor es la cantidad seleciconada
     * de estos productos.
     */
    public HashMap<Long, Integer> getMapaIdsProductosCantidad() {
        return mapaIdsProductosCantidad;
    }
    
    /**
     * Método que permite obtener el carrito de compras asociado al pedido.
     * @return Objeto CarritoComprasDTO que representa el ID del carrito de compras que está asociado al
     * pedido.
     */
    public CarritoComprasDTO getCarritoCompras() {
        return carritoCompras;
    }
}
