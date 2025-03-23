package edu.student.itson.dissof.megazarl.objetosnegocio;

public class Paqueteria {

    private Integer id;
    private String nombre;
    private String direccionImagenPaqueteria;
    private Double cobroKg;
    private Double cobroHora;

    public Paqueteria(Integer id, String nombre, String direccionImagenPaqueteria, Double cobroKg, Double cobroHora) {
        this.id = id;
        this.nombre = nombre;
        this.direccionImagenPaqueteria = direccionImagenPaqueteria;
        this.cobroKg = cobroKg;
        this.cobroHora = cobroHora;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccionImagenPaqueteria() {
        return direccionImagenPaqueteria;
    }

    public Double getCobroKg() {
        return cobroKg;
    }

    public Double getCobroHora() {
        return cobroHora;
    }
}
