package edu.student.itson.dissof.megazarl.repositorio.actualizaciones;

public class ActualizacionPaqueteria {
    private String nombre;
    private boolean nombreE;
    private Float cobroKg;
    private boolean cobroKgE;
    private Float cobroHora;
    private boolean cobroHoraE;
    private String direccionImagenPaqueteria;
    private boolean direccionImagenPaqueteriaE;

    public ActualizacionPaqueteria nombre(String nombre) {
        this.nombre = nombre;
        nombreE = true;
        return this;
    }

    public ActualizacionPaqueteria cobroKg(Float cobroKg) {
        this.cobroKg = cobroKg;
        cobroKgE = true;
        return this;
    }

    public ActualizacionPaqueteria cobroHora(Float cobroHora) {
        this.cobroHora = cobroHora;
        cobroHoraE = true;
        return this;
    }

    public ActualizacionPaqueteria direccionImagenPaqueteria(String direccionImagenPaqueteria) {
        this.direccionImagenPaqueteria = direccionImagenPaqueteria;
        direccionImagenPaqueteriaE = true;
        return this;
    }

    public String getNombre() { return nombre; }
    public Float getCobroKg() { return cobroKg; }
    public Float getCobroHora() { return cobroHora; }
    public String getDireccionImagenPaqueteria() { return direccionImagenPaqueteria; }

    public boolean tieneNombre() { return nombreE; }
    public boolean tieneCobroKg() { return cobroKgE; }
    public boolean tieneCobroHora() { return cobroHoraE; }
    public boolean tieneDireccionImagenPaqueteria() { return direccionImagenPaqueteriaE; }
}
