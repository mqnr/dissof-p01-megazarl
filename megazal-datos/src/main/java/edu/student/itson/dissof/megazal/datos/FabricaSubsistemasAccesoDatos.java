
package edu.student.itson.dissof.megazal.datos;

import com.mycompany.megazarl.administrador.mongodb.FAdministradorMongodb;
import com.mycompany.megazarl.administrador.mongodb.IAdministradorMongodb;

/**
 * Fábrica que permite crear subsistemas de la capa de acceso a datos.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class FabricaSubsistemasAccesoDatos {
    
    public static IAdministradorMongodb obtenerAdministradorMongodb(){
        
        IAdministradorMongodb administradorMongodb = new FAdministradorMongodb();
        
        return administradorMongodb;
        
    }
    
    
}
