
package edu.student.itson.dissof.megazarl.dto.negocios.identidad;

import java.util.Objects;


public class IdEntidadGenerico<T> {
    
    protected final T id;

    public IdEntidadGenerico(T id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo.");
        }
        this.id = id;
    }

    public T getId() {
        return id;
    }
    
    public Long getLong() {
        if (id instanceof Long) {
            return (Long) id;
        }
        try {
            return Long.valueOf(id.toString());
        } catch (NumberFormatException e) {
            throw new IllegalStateException("El ID no se puede convertir a Long: " + id);
        }
    }

    public String getString() {
        if (id instanceof String) {
            return (String) id;
        }
        // Si no es String, se convierte a String utilizando toString()
        return id.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final IdEntidadGenerico<?> other = (IdEntidadGenerico<?>) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}