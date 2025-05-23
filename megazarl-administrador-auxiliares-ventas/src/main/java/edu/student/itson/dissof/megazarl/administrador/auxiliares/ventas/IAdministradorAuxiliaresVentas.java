
package edu.student.itson.dissof.megazarl.administrador.auxiliares.ventas;

import edu.student.itson.dissof.megazarl.dto.negocios.IdAuxiliarVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoAuxiliarVentasDTONegocios;

/**
 *
 * @author Manuel Romo López
 */
public interface IAdministradorAuxiliaresVentas {
    
    public abstract NombresApellidoAuxiliarVentasDTONegocios obtenerNombreApellidoAuxiliarVentas(IdAuxiliarVentasDTONegocios idAuxiliarVentasDTO);
    
}
