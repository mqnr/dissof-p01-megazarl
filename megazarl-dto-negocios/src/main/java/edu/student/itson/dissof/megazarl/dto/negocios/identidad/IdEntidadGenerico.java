
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