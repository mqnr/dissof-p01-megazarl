
package objetosnegocio;

import java.util.Objects;


public class Sucursal {
    
    private Integer id;
    private String nombre;
    private Float tiempoMatriz;

    public Sucursal(Integer id, String nombre, Float tiempoMatriz) {
        this.id = id;
        this.nombre = nombre;
        this.tiempoMatriz = tiempoMatriz;
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

    public Float getTiempoMatriz() {
        return tiempoMatriz;
    }

    public void setTiempoMatriz(Float tiempoMatriz) {
        this.tiempoMatriz = tiempoMatriz;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
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
