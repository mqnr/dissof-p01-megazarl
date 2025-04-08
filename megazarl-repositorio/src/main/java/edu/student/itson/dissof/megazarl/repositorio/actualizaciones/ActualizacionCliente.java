package edu.student.itson.dissof.megazarl.repositorio.actualizaciones;

public class ActualizacionCliente {
    private String nombres;
    private boolean nombresE;
    private String apellidoPaterno;
    private boolean apellidoPaternoE;
    private String apellidoMaterno;
    private boolean apellidoMaternoE;
    private String codigoPostalEnvio;
    private boolean codigoPostalEnvioE;
    private String calleEnvio;
    private boolean calleEnvioE;
    private String numeroDomicilioEnvio;
    private boolean numeroDomicilioEnvioE;

    public ActualizacionCliente nombres(String nombres) {
        this.nombres = nombres;
        nombresE = true;
        return this;
    }

    public ActualizacionCliente apellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
        apellidoPaternoE = true;
        return this;
    }

    public ActualizacionCliente apellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
        apellidoMaternoE = true;
        return this;
    }

    public ActualizacionCliente codigoPostalEnvio(String codigoPostalEnvio) {
        this.codigoPostalEnvio = codigoPostalEnvio;
        codigoPostalEnvioE = true;
        return this;
    }

    public ActualizacionCliente calleEnvio(String calleEnvio) {
        this.calleEnvio = calleEnvio;
        calleEnvioE = true;
        return this;
    }

    public ActualizacionCliente numeroDomicilioEnvio(String numeroDomicilioEnvio) {
        this.numeroDomicilioEnvio = numeroDomicilioEnvio;
        numeroDomicilioEnvioE = true;
        return this;
    }

    public String getNombres() { return nombres; }
    public String getApellidoPaterno() { return apellidoPaterno; }
    public String getApellidoMaterno() { return apellidoMaterno; }
    public String getCodigoPostalEnvio() { return codigoPostalEnvio; }
    public String getCalleEnvio() { return calleEnvio; }
    public String getNumeroDomicilioEnvio() { return numeroDomicilioEnvio; }

    public boolean tieneNombres() { return nombresE; }
    public boolean tieneApellidoPaterno() { return apellidoPaternoE; }
    public boolean tieneApellidoMaterno() { return apellidoMaternoE; }
    public boolean tieneCodigoPostalEnvio() { return codigoPostalEnvioE; }
    public boolean tieneCalleEnvio() { return calleEnvioE; }
    public boolean tieneNumeroDomicilioEnvio() { return numeroDomicilioEnvioE; }
}
