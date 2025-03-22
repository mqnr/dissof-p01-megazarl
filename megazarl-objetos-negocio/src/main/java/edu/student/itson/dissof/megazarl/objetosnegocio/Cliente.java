package edu.student.itson.dissof.megazarl.objetosnegocio;

public class Cliente {

    private Integer id;
    private String nombre;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String codigoPostal;
    private String calle;
    private String numeroDomicilio;

    public Cliente(Integer id, String nombre, String apellidoMaterno, String apellidoPaterno, String codigoPostal, String calle, String numeroDomicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.codigoPostal = codigoPostal;
        this.calle = calle;
        this.numeroDomicilio = numeroDomicilio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroDomicilio() {
        return numeroDomicilio;
    }

    public void setNumeroDomicilio(String numeroDomicilio) {
        this.numeroDomicilio = numeroDomicilio;
    }

    

    
}
