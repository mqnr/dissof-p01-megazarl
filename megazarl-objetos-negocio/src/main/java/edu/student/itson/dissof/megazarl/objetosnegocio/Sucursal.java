package edu.student.itson.dissof.megazarl.objetosnegocio;

import java.util.Objects;

public class Sucursal {

    private Integer id;
    private Boolean esMatriz;
    private Float tiempoMatriz;
    private String codigoPostal;
    private String calle;
    private String numero;

    public Sucursal(Integer id, Boolean esMatriz, Float tiempoMatriz, String codigoPostal, String calle, String numero) {
        this.id = id;
        this.esMatriz = esMatriz;
        this.tiempoMatriz = tiempoMatriz;
        this.codigoPostal = codigoPostal;
        this.calle = calle;
        this.numero = numero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEsMatriz() {
        return esMatriz;
    }

    public void setEsMatriz(Boolean esMatriz) {
        this.esMatriz = esMatriz;
    }

    public Float getTiempoMatriz() {
        return tiempoMatriz;
    }

    public void setTiempoMatriz(Float tiempoMatriz) {
        this.tiempoMatriz = tiempoMatriz;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    

    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

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
        final Sucursal other = (Sucursal) obj;
        return Objects.equals(this.id, other.id);
    }

    
    
}
