
package edu.student.itson.dissof.megazarl.mapas;

import edu.student.itson.dissof.megazarl.dto.infraestructura.DatosTiempoTrasladoUbicacionesDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.TiempoTrasladoDTO;


public class FAdministradorMapas implements IAdministradorMapas{

    private final AdministradorMapas mapas;
    
    public FAdministradorMapas(){
        this.mapas = new AdministradorMapas();
    }
    
    @Override
    public TiempoTrasladoDTO calcularTiempoTraslado(DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoUbicacionesDTO) {
        
        return mapas.calcularTiempoTraslado(datosTiempoTrasladoUbicacionesDTO);
        
    }
    
}
