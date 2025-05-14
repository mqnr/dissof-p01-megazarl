
package edu.student.itson.dissof.megazarl.administrador.auxiliares.ventas;

import edu.student.itson.dissof.megazarl.dto.infraestructura.IdAuxiliarVentasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoAuxiliarVentasDTO;

/**
 *
 * @author Manuel Romo López
 */
public class AdministradorAuxiliaresVentas {
    
    public NombresApellidoAuxiliarVentasDTO obtenerNombreApellidoAuxiliarVentas(IdAuxiliarVentasDTO idAuxiliarVentasDTO){
        return new NombresApellidoAuxiliarVentasDTO("María", "González");
    }
    
}
