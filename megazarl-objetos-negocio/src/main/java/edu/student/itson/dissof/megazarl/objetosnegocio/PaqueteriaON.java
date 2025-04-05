package edu.student.itson.dissof.megazarl.objetosnegocio;

/**
 * Paqueteria.java
 *
 * Clase que representa una paquetería o servicio de envío disponible en el sistema,
 * con sus tarifas y datos de identificación para el envío de pedidos.
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
public class PaqueteriaON {

    private Integer id;
    private String nombre;
    private Float cobroKg;
    private Float cobroHora;
    private String direccionImagenPaqueteria;

    /**
     * Método que permite obtener el ID de la paquetería.
     * @return Objeto Integer que representa el ID único de la paquetería.
     */
    public PaqueteriaON(Integer id, String nombre, Float cobroKg, Float cobroHora, String direccionImagenPaqueteria) {
        this.id = id;
        this.nombre = nombre;
        this.cobroKg = cobroKg;
        this.cobroHora = cobroHora;
        this.direccionImagenPaqueteria = direccionImagenPaqueteria;
    }

    /**
     * Método que permite obtener el nombre de la paquetería.
     * @return Objeto String que representa el nombre de la paquetería.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Método que permite obtener el nombre de la paquetería.
     * @return Objeto String que representa el nombre de la paquetería.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que permite obtener el cobro por kilogramo de la paquetería.
     * @return Objeto Float que representa el cobro por kilogramo de la paquetería.
     */
    public Float getCobroKg() {
        return cobroKg;
    }

    /**
     * Método que permite obtener el cobro por hora de la paquetería.
     * @return Objeto Float que representa el cobro por hora de la paquetería.
     */
    public Float getCobroHora() {
        return cobroHora;
    }

    /**
     * Método que permite obtener la ruta de la imagen de la paquetería.
     * @return Objeto String que representa la ruta de la imagen de la paquetería.
     */
    public String getDireccionImagenPaqueteria() {
        return direccionImagenPaqueteria;
    }
}
