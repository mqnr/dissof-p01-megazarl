
package edu.student.itson.dissof.megazarl.administrador.auxiliares.ventas;

import edu.student.itson.dissof.megazarl.dto.negocios.IdAuxiliarVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoAuxiliarVentasDTONegocios;

/**
 *
 * @author Manuel Romo López
 */
public class AdministradorAuxiliaresVentas {
    
    public NombresApellidoAuxiliarVentasDTONegocios obtenerNombreApellidoAuxiliarVentas(IdAuxiliarVentasDTONegocios idAuxiliarVentasDTONegocios){
        return new NombresApellidoAuxiliarVentasDTONegocios("María", "González");
    }
    
}
