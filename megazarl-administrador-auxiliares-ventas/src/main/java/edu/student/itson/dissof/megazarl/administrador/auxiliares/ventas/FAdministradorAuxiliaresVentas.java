
package edu.student.itson.dissof.megazarl.administrador.auxiliares.ventas;

import edu.student.itson.dissof.megazarl.dto.negocios.IdAuxiliarVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoAuxiliarVentasDTONegocios;

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
    public NombresApellidoAuxiliarVentasDTONegocios obtenerNombreApellidoAuxiliarVentas(IdAuxiliarVentasDTONegocios idAuxiliarVentasDTONegocios) {
        return administradorAuxiliaresVentas.obtenerNombreApellidoAuxiliarVentas(idAuxiliarVentasDTONegocios);
    }
    
}
