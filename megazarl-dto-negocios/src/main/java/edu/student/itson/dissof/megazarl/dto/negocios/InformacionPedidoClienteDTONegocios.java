package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import java.util.HashMap;

/**
 * InformacionPedidoClienteDTONegocios.java

 Clase DTO que contiene toda la información necesaria para calcular el costo total de un pedido,
 incluyendo el cliente, la paquetería seleccionada y un mapa con los
 productos y sus cantidades respectivas.
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
public class InformacionPedidoClienteDTONegocios {
    
    IdEntidadGenericoNegocios idCliente;
    IdEntidadGenericoNegocios idPaqueteria;
    HashMap<IdEntidadGenericoNegocios, Integer> mapaProductosCantidades;

    public InformacionPedidoClienteDTONegocios(
            IdEntidadGenericoNegocios idCliente, 
            IdEntidadGenericoNegocios idPaqueteria, 
            HashMap<IdEntidadGenericoNegocios, Integer> mapaProductosCantidades) {
        
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
        this.mapaProductosCantidades = mapaProductosCantidades;
    }

    public IdEntidadGenericoNegocios getIdCliente() {
        return idCliente;
    }

    public IdEntidadGenericoNegocios getIdPaqueteria() {
        return idPaqueteria;
    }

    public HashMap<IdEntidadGenericoNegocios, Integer> getMapaProductosCantidades() {
        return mapaProductosCantidades;
    }
}
