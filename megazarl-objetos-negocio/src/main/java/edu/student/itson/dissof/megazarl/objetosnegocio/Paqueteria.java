package edu.student.itson.dissof.megazarl.objetosnegocio;

public class Paqueteria {

    private Integer id;
    private String nombre;
    private Float cobroKg;
    private Float cobroHora;
    private String direccionImagenPaqueteria;

    public Paqueteria(Integer id, String nombre, Float cobroKg, Float cobroHora, String direccionImagenPaqueteria) {
        this.id = id;
        this.nombre = nombre;
        this.cobroKg = cobroKg;
        this.cobroHora = cobroHora;
        this.direccionImagenPaqueteria = direccionImagenPaqueteria;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Float getCobroKg() {
        return cobroKg;
    }

    public Float getCobroHora() {
        return cobroHora;
    }
    
    public String getDireccionImagenPaqueteria() {
        return direccionImagenPaqueteria;
    }
}
