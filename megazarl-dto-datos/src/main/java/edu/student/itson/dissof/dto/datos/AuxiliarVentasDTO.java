
package edu.student.itson.dissof.dto.datos;


public class AuxiliarVentasDTO {
    
    /**
     * Objeto Long que representa el ID del Auxiliar de ventas.
     */
    private Long id;
    
    /**
     * Objeto String que representa el o lo nombres del Auxiliar de ventas.
     */
    private String nombres;
    
    /**
     * Objeto String que representa el apellido paterno del Auxiliar de ventas.
     */
    private String apellidoPaterno;
    
    /**
     * Objeto String que representa el apellido materno del Auxiliar de ventas.
     */
    private String apellidoMaterno;

    /**
     * Constructor de la clase que recibe valores para todos los atributos.
     * @param id                Objeto Long que representa el ID del Auxiliar de ventas.
     * @param nombres           Objeto String que representa el o lo nombres del Auxiliar de ventas.
     * @param apellidoPaterno   Objeto String que representa el apellido paterno del Auxiliar de ventas.
     * @param apellidoMaterno   Objeto String que representa el apellido materno del Auxiliar de ventas.
     */
    public AuxiliarVentasDTO(
            Long id,
            String nombres,
            String apellidoPaterno, 
            String apellidoMaterno) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }
    
    /**
     * Constructor de la clase que recibe valores para todos los atributos, excepto el ID.
     * @param nombres           Objeto String que representa el o lo nombres del Auxiliar de ventas.
     * @param apellidoPaterno   Objeto String que representa el apellido paterno del Auxiliar de ventas.
     * @param apellidoMaterno   Objeto String que representa el apellido materno del Auxiliar de ventas.
     */
    public AuxiliarVentasDTO(
            String nombres, 
            String apellidoPaterno, 
            String apellidoMaterno) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Método que permite obtener el ID del Auxiliar de ventas.
     * @return Objeto Long que representa el ID del Auxiliar de ventas.
     */
    public Long getId() {
        return id;
    }

    /**
     * Método que permite recuperar los nombres del Auxiliar de ventas.
     * @return Objeto String que representa el o lo nombres del Auxiliar de ventas.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Método que permite recuperar el apellido paterno del Auxiliar de ventas.
     * @return Objeto String que representa el apellido paterno del Auxiliar de ventas.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Método que permite recuperar el apellido materno del Auxiliar de ventas.
     * @return Objeto String que representa el apellido materno del Auxiliar de ventas.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Método que permite establecer el ID del Auxiliar de ventas.
     * @param id Objeto Long que representa el nuevo ID del Auxiliar de ventas.
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    
    
    
}
