
package edu.student.itson.dissof.megazarl.administradorsql;

import edu.student.itson.dissof.megazarl.administrador.sql.administradorsql.manejadorconexiones.ManejadorConexiones;
import edu.student.itson.dissof.megazarl.administradorsql.clasesmapeadas.CarritoCompras;
import edu.student.itson.dissof.megazarl.administradorsql.excepciones.RegistroIdNuloException;
import edu.student.itson.dissof.megazarl.administradorsql.excepciones.RegistroInexistenteException;
import edu.student.itson.dissof.megazarl.dto.infraestructura.CarritoComprasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdCarritoComprasDTO;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * CarritoComprasDAO.java
 * 
 * 
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 * 
 */

public class CarritoComprasDAO {
    
//    public static CarritoComprasDTO recuperarPorId(IdCarritoComprasDTO idCarritoComprasDTO) 
//            throws RegistroIdNuloException,
//            RegistroInexistenteException {
//        
////        // Validación de entrada
////        if (idCarritoComprasDTO == null || idCarritoComprasDTO.getIdCarritoCompras() == null) {
////            throw new RegistroIdNuloException("El ID de Carrito de compras recibido es nulo.");
////        }
////
////        Long idCarritoCompra = idCarritoComprasDTO.getIdCarritoCompras();
////
////        // Se obtiene el EntityManager para interactuar con la base de datos
////        EntityManager entityManager = ManejadorConexiones.getEntityManager();
////
////        String jpqlQuery = """
////                            SELECT new edu.student.itson.dissof.megazarl.dto.infraestructura.CarritoComprasDTO(
////
////                                CC.id, 
////
////                                new edu.student.itson.dissof.megazarl.dto.infraestructura.ClienteDTO(
////                                    CL.id,
////                                    CL.nombres, 
////                                    CL.apellidoPaterno,
////                                    CL.apellidoMaterno,
////                                    CL.telefono,
////                                    CL.correoElectronico,
////                                    CL.direccionEnvio
////                                )
////                                
////                                new edu.student.itson.dissof.megazarl.dto.infraestructura.PaqueteriaDTO(
////                                    PA.id,
////                                    PA.nombre,
////                                    PA.cobroKg,
////                                    PA.cobroHora,
////                                    PA.direccionImagenPaqueteria
////                                )
////                                L.id, 
////                                L.nombre
////
////                            )
////                           
////                           
////                           CC FROM Logro L
////                           JOIN L.videjuego V
////                           """;
////        
////        TypedQuery<LogroVideojuegoDTO> query = entityManager.createQuery(jpqlQuery, LogroVideojuegoDTO.class);
////        
////        return query.getResultList();
////        
//        
//    }
}
