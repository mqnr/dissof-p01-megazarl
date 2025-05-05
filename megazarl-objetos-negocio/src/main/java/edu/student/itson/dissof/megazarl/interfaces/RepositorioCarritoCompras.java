
package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionCarritoComprasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdCarritoComprasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.CarritoComprasDTO;

public interface RepositorioCarritoCompras extends RepositorioInmutable<CarritoComprasDTO>{
    
    public abstract CarritoComprasDTO recuperarPorId(IdCarritoComprasDTO idCarritoComprasDTO);
    
    public abstract boolean existePorId(IdCarritoComprasDTO idCarritoComprasDTO);
    
    public abstract CarritoComprasDTO actualizar(ActualizacionCarritoComprasDTO actualizacionCarritoComprasDTO);
    
}
