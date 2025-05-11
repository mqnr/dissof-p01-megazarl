package edu.student.itson.dissof.megazarl.dto.infraestructura;

/**
 * PaqueteriaDTO.java
 *
 * Clase DTO que representa una paquetería o servicio de envío disponible en el sistema,
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
public class PaqueteriaDTO {

    /**
     * Objeto Long que representa el ID de la paquetería.
     */
    private Long id;

    /**
     * Objeto String que representa el nombre de la paquetería.
     */
    private String nombre;

    /**
     * Objeto Float que representa el costo de envío por kilogramo.
     */
    private Float cobroKg;

    /**
     * Objeto Float que representa el costo de envío por hora.
     */
    private Float cobroHora;
    
    /**
     * Objeto String que representa la dirección de la imagen de la paquetería.
     */
    private String direccionImagenPaqueteria;
    
    /**
     * Objeto DireccionDTO que representa la dirección de la paquetería.
     */
    private DireccionDTO direccion;
    

    /**
     * Constructor que permite instanciar un objeto de tipo PaqueteriaDTO, con toda
     * la información necesaria.
     * @param id                        Objeto Long que representa el id de la paquetería.
     * @param nombre                    Objeto String que representa el nombre de la paquetería.
     * @param cobroKg                   Objeto Float que representa el costo de envío por kilogramo.
     * @param cobroHora                 Objeto Float que representa el costo de envío por hora.
     * @param direccionImagenPaqueteria Objeto String que representa la dirección de la imagen de la paquetería.
     * @param direccion                 Objeto DireccionDTO que representa la dirección de la paquetería.
     * 
     */
    public PaqueteriaDTO(  
            Long id,
            String nombre, 
            Float cobroKg, 
            Float cobroHora, 
            String direccionImagenPaqueteria,
            DireccionDTO direccion) {

        this.id = id;
        this.nombre = nombre;
        this.cobroKg = cobroKg;
        this.cobroHora = cobroHora;
        this.direccionImagenPaqueteria = direccionImagenPaqueteria;
        this.direccion = direccion;
    }
    

    /**
     * Método que permite obtener el ID de la paquetería.
     *
     * @return Objeto Long que representa el id de la paquetería.
     */
    public Long getId() {
        return id;
    }

    /**
     * Método que permite obtener el nombre de la paquetería
     *
     * @return Objeto String que representa el nombre de la paquetería
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que permite obtener el costo de envío por kilogramo
     *
     * @return Objeto Float que representa el costo de envío por kilogramo
     */
    public Float getCobroKg() {
        return cobroKg;
    }

    /**
     * Método que permite obtener el costo de envío por hora
     *
     * @return Objeto Float que representa el costo de envío por hora
     */
    public Float getCobroHora() {
        return cobroHora;
    }

    /**
     * Método que permite obtener la dirección de la imagen de la paquetería.
     * @return Objeto String que representa la dirección de la imagen de la paquetería.
     */
    public String getDireccionImagenPaqueteria() {
        return direccionImagenPaqueteria;
    }

    /**
     * Método que permite obtener la dirección de la paquetería.
     * @return Objeto DireccionDTO que representa la dirección de la paquetería.
     */
    public DireccionDTO getDireccion() {
        return direccion;
    }

    /**
     * Método que permite obtener el hash code de la paquetería, a partir de su ID.
     * @return Dato int que representa el hash code de la paquetería.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + java.util.Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Método que permite verificar si el objeto del parámetro es igual 
     * a esta paquetería, basándose en su ID.
     * @param obj Objeto a determinar si es igual a esta paquetería.
     * @return Dato boolean, true si el objeto es igual a la paquetería, false en
     * caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PaqueteriaDTO other = (PaqueteriaDTO) obj;
        return java.util.Objects.equals(this.id, other.id);
    }
}