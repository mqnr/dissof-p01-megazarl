
package edu.student.itson.dissof.megazarl.dto.negocios;

/**
 * Clase DTO que contiene la información de criterio para la búsqueda
 * de productos.
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
public class InformacionBusquedaProductoDTO {

    /**
     * Objeto String que representa el nombre de los productos a buscar.
     */
    private String nombreProducto;

    /**
     * Constructor de la clase que recine el nombre de los productos a buscar.
     * @param nombreProducto Objeto String que representa el nombre de los productos a buscar.
     */
    public InformacionBusquedaProductoDTO(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * Método que permite obtener el nombre de los productos a buscar.
     * @return Objeto String que representa el nombre de los productos a buscar.
     */
    public String getNombreProducto() {
        return nombreProducto;
    }
    
    
}
