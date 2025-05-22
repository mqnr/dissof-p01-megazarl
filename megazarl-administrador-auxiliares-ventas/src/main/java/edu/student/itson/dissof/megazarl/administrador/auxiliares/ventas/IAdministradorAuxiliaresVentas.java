
package edu.student.itson.dissof.megazarl.administrador.auxiliares.ventas;

import edu.student.itson.dissof.megazarl.dto.negocios.IdAuxiliarVentasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoAuxiliarVentasDTO;

/**
 *
 * @author Manuel Romo López
 */
public interface IAdministradorAuxiliaresVentas {
    
    public abstract NombresApellidoAuxiliarVentasDTO obtenerNombreApellidoAuxiliarVentas(IdAuxiliarVentasDTO idAuxiliarVentasDTO);
    
}
