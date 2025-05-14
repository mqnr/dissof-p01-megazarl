
package edu.student.itson.dissof.megazarl.administrador.auxiliares.ventas;

import edu.student.itson.dissof.megazarl.dto.infraestructura.IdAuxiliarVentasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoAuxiliarVentasDTO;

/**
 *
 * @author Manuel Romo López
 */
public class FAdministradorAuxiliaresVentas implements IAdministradorAuxiliaresVentas{

    private final AdministradorAuxiliaresVentas administradorAuxiliaresVentas;
    
    public FAdministradorAuxiliaresVentas(){
        
        this.administradorAuxiliaresVentas = new AdministradorAuxiliaresVentas();
 
    }
    
    @Override
    public NombresApellidoAuxiliarVentasDTO obtenerNombreApellidoAuxiliarVentas(IdAuxiliarVentasDTO idAuxiliarVentasDTO) {
        return administradorAuxiliaresVentas.obtenerNombreApellidoAuxiliarVentas(idAuxiliarVentasDTO);
    }
    
}
