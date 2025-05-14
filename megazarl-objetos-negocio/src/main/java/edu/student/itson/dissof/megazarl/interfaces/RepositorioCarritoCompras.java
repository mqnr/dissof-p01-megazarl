
package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.infraestructura.CarritoComprasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ActualizacionCarritoComprasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdCarritoComprasDTO;

public interface RepositorioCarritoCompras extends RepositorioInmutable<CarritoComprasDTO>{
    
    public abstract CarritoComprasDTO recuperarPorId(IdCarritoComprasDTO idCarritoComprasDTO);
    
    public abstract boolean existePorId(IdCarritoComprasDTO idCarritoComprasDTO);
    
    public abstract CarritoComprasDTO actualizar(ActualizacionCarritoComprasDTO actualizacionCarritoComprasDTO);
    
}
