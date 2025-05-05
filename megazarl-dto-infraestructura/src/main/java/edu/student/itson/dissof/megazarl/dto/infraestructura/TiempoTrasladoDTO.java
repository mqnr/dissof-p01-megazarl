
package edu.student.itson.dissof.megazarl.dto.infraestructura;

/**
 * 
 * TiempoTrasladoDTO.java
 * 
 * Clase DTO que contiene el tiempo de traslado obtenido de un punto A a un punto
 * B en el mapa.
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
public class TiempoTrasladoDTO {
    
    /**
     * Objeto Float que representa el tiempo de traslado de un punto A a un
     * punto B en el mapa.
     */
    private Float tiempoTraslado;

    /**
     * Constructor de la clase que recibe el tiempo de traslado como parámetro.
     * @param tiempoTraslado Objeto Float que representa el tiempo de traslado de un punto A a un
     * punto B en el mapa.
     */
    public TiempoTrasladoDTO(Float tiempoTraslado) {
        this.tiempoTraslado = tiempoTraslado;
    }

    /**
     * Método que permite obtener el tiempo de traslado de un punto A a un
     * punto B en el mapa.
     * @return Objeto Float que representa el tiempo de traslado de un punto A a un
     * punto B en el mapa.
     */
    public Float getTiempoTraslado() {
        return tiempoTraslado;
    }
    
    
}
