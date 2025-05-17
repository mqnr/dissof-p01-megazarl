
package edu.student.itson.dissof.megazarl.administradormysql;

import edu.student.itson.dissof.megazarl.administradormysql.clasesmapeadas.CarritoCompras;
import edu.student.itson.dissof.megazarl.administradormysql.clasesmapeadas.ProductoCarrito;
import edu.student.itson.dissof.megazarl.administradormysql.excepciones.RegistroIdNuloException;
import edu.student.itson.dissof.megazarl.administradormysql.excepciones.RegistroInexistenteException;
import edu.student.itson.dissof.megazarl.administradormysql.manejadorconexiones.ManejadorConexiones;
import edu.student.itson.dissof.megazarl.dto.infraestructura.CarritoComprasIdsRelacionesDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdCarritoComprasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdClienteDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdProductoCarritoDTO;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


public class CarritoComprasDAO {
    
    public CarritoComprasIdsRelacionesDTO recuperarPorId(IdCarritoComprasDTO idCarritoComprasDTO) 
            throws RegistroIdNuloException,
            RegistroInexistenteException{
        
        if(idCarritoComprasDTO == null || idCarritoComprasDTO.getIdCarritoCompras() == null){
            throw new RegistroIdNuloException("El ID recibido para relaizar la consulta del carrito de compras es nulo.");
        }
        
        Long idCarritoCompras = idCarritoComprasDTO.getIdCarritoCompras();

        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        try {
            String jpqlQueryRecuperarCarritoCompras = """
                                                  SELECT DISTINCT CC           
                                                  FROM CarritoCompras CC
                                                  JOIN FETCH CC.cliente
                                                  JOIN FETCH CC.paqueteria
                                                  JOIN FETCH CC.productosCarritoCompras
                                                  WHERE CC.id = :idCarritoCompras
                                                  """;
        
            TypedQuery<CarritoCompras> queryRecuperarCarritoCompras = entityManager.createQuery(
                    jpqlQueryRecuperarCarritoCompras, 
                    CarritoCompras.class);

            queryRecuperarCarritoCompras.setParameter("idCarritoCompras", idCarritoCompras);

            CarritoCompras carritoCompras;
            try{
                carritoCompras = queryRecuperarCarritoCompras.getSingleResult();
            } catch(NoResultException ex){
                throw new  RegistroInexistenteException("No existe una carrito de compras con el ID: " + idCarritoCompras);
            }

            List<IdProductoCarritoDTO> idsProductosCarrito = new LinkedList<>();

            for(ProductoCarrito productoCarrito: carritoCompras.getProductosCarritoCompras()){
                idsProductosCarrito.add(new IdProductoCarritoDTO(productoCarrito.getId()));
            }

            IdClienteDTO idClienteDTO = new IdClienteDTO(carritoCompras.getCliente().getId());

            CarritoComprasIdsRelacionesDTO carritoComprasIdsRelacionesDTO = new CarritoComprasIdsRelacionesDTO(
                    carritoCompras.getId(), 
                    carritoCompras.getEsVigente(),
                    idClienteDTO,
                    idsProductosCarrito
            );

            if(!carritoCompras.getEsVigente() && carritoCompras.getPaqueteria() != null){
                IdPaqueteriaDTO idPaqueteriaDTO = new IdPaqueteriaDTO(carritoCompras.getPaqueteria().getId());

                carritoComprasIdsRelacionesDTO.setIdPaqueteria(idPaqueteriaDTO);
            }

            return carritoComprasIdsRelacionesDTO;
            
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
  
    }
    
}
