package edu.student.itson.dissof.megazarl.objetosnegocio;

/**
 * Cliente.java
 *
 * Clase que representa un cliente registrado en el sistema, con su información
 * personal y datos de dirección de envío para realizar pedidos de semillas.
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
public class Cliente {

    private Integer id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String codigoPostalEnvio;
    private String calleEnvio;
    private String numeroDomicilioEnvio;

    /**
     * Constructor que inicializa un cliente con todos sus atributos.
     * @param id Objeto Integer que representa el ID único del cliente.
     * @param nombres Objeto String que representa el o los nombres del cliente.
     * @param apellidoPaterno Objeto String que representa el apellido paterno del cliente.
     * @param apellidoMaterno Objeto String que representa el apellido materno del cliente.
     * @param codigoPostalEnvio Objeto String que representa el código postal de la dirección de envío.
     * @param calleEnvio Objeto String que representa la calle de la dirección de envío.
     * @param numeroDomicilioEnvio Objeto String que representa el número de domicilio de la dirección de envío.
     */
    public Cliente(Integer id, String nombres, String apellidoPaterno, String apellidoMaterno, String codigoPostalEnvio, String calleEnvio, String numeroDomicilioEnvio) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.codigoPostalEnvio = codigoPostalEnvio;
        this.calleEnvio = calleEnvio;
        this.numeroDomicilioEnvio = numeroDomicilioEnvio;
    }

    /**
     * Método que permite obtener el ID del cliente.
     * @return Objeto Integer que representa el ID único del cliente.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Método que permite establecer el ID del cliente.
     * @param id Objeto Integer que representa el ID único del cliente.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Método que permite obtener el o los nombres del cliente.
     * @return Objeto String que representa el o los nombres del cliente.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Método que permite establecer el o los nombres del cliente.
     * @param nombre Objeto String que representa el o los nombres del cliente.
     */
    public void setNombres(String nombre) {
        this.nombres = nombre;
    }

    /**
     * Método que permite obtener el apellido materno del cliente.
     * @return Objeto String que representa el apellido materno del cliente.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Método que permite establecer el apellido materno del cliente.
     * @param apellidoMaterno Objeto String que representa el apellido materno del cliente.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Método que permite obtener el apellido paterno del cliente.
     * @return Objeto String que representa el apellido paterno del cliente.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Método que permite establecer el apellido paterno del cliente.
     * @param apellidoPaterno Objeto String que representa el apellido paterno del cliente.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Método que permite obtener el código postal de la dirección de envío del cliente.
     * @return Objeto String que representa el código postal de la dirección de envío.
     */
    public String getCodigoPostalEnvio() {
        return codigoPostalEnvio;
    }

    /**
     * Método que permite establecer el código postal de la dirección de envío del cliente.
     * @param codigoPostalEnvio Objeto String que representa el código postal de la dirección de envío.
     */
    public void setCodigoPostalEnvio(String codigoPostalEnvio) {
        this.codigoPostalEnvio = codigoPostalEnvio;
    }

    /**
     * Método que permite obtener la calle de la dirección de envío del cliente.
     * @return Objeto String que representa la calle de la dirección de envío.
     */
    public String getCalleEnvio() {
        return calleEnvio;
    }

    /**
     * Método que permite establecer la calle de la dirección de envío del cliente.
     * @param calleEnvio Objeto String que representa la calle de la dirección de envío.
     */
    public void setCalleEnvio(String calleEnvio) {
        this.calleEnvio = calleEnvio;
    }

    /**
     * Método que permite obtener el número de domicilio de la dirección de envío del cliente.
     * @return Objeto String que representa el número de domicilio de la dirección de envío.
     */
    public String getNumeroDomicilioEnvio() {
        return numeroDomicilioEnvio;
    }

    /**
     * Método que permite establecer el número de domicilio de la dirección de envío del cliente.
     * @param numeroDomicilioEnvio Objeto String que representa el número de domicilio de la dirección de envío.
     */
    public void setNumeroDomicilioEnvio(String numeroDomicilioEnvio) {
        this.numeroDomicilioEnvio = numeroDomicilioEnvio;
    }
}
