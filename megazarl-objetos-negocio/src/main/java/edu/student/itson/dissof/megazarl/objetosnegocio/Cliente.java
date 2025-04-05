package edu.student.itson.dissof.megazarl.objetosnegocio;

public class Cliente {

    private Integer id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String codigoPostalEnvio;
    private String calleEnvio;
    private String numeroDomicilioEnvio;

    public Cliente(Integer id, String nombres, String apellidoPaterno, String apellidoMaterno, String codigoPostalEnvio, String calleEnvio, String numeroDomicilioEnvio) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.codigoPostalEnvio = codigoPostalEnvio;
        this.calleEnvio = calleEnvio;
        this.numeroDomicilioEnvio = numeroDomicilioEnvio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombre) {
        this.nombres = nombre;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getCodigoPostalEnvio() {
        return codigoPostalEnvio;
    }

    public void setCodigoPostalEnvio(String codigoPostalEnvio) {
        this.codigoPostalEnvio = codigoPostalEnvio;
    }

    public String getCalleEnvio() {
        return calleEnvio;
    }

    public void setCalleEnvio(String calleEnvio) {
        this.calleEnvio = calleEnvio;
    }

    public String getNumeroDomicilioEnvio() {
        return numeroDomicilioEnvio;
    }

    public void setNumeroDomicilioEnvio(String numeroDomicilioEnvio) {
        this.numeroDomicilioEnvio = numeroDomicilioEnvio;
    }

    
}
