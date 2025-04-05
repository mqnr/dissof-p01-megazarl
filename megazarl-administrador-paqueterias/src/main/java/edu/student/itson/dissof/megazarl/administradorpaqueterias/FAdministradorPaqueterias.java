package edu.student.itson.dissof.megazarl.administradorpaqueterias;

import edu.student.itson.dissof.megazarl.administradorsucursales.IAdministradorSucursales;
import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.dto.DireccionClientePesoTiempoProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionSeleccionPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.Paqueteria;
import java.util.LinkedList;
import java.util.List;

/**
 * FAdministradorPaqueterias.java
 *
 * Clase que implementa la interfaz IAdministradorPaqueterias, proporcionando
 * la funcionalidad para administrar las paqueterías disponibles en el sistema
 * y calcular costos de envío.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 *
 */
public class FAdministradorPaqueterias implements IAdministradorPaqueterias {

    private List<Paqueteria> listaPaqueterias;
    
    private IAdministradorClientes administradorClientes;
    private IAdministradorSucursales administradorSucursales;
    private IAdministradorProductos administradorProductos;

    /**
     * Constructor que inicializa un administrador de paqueterías con todos sus atributos.
     *
     * @param listaPaqueterias Objeto List que contiene las paqueterías disponibles en el sistema.
     * @param administradorClientes Objeto IAdministradorClientes que representa el subsistema de
     * administración de clientes.
     * @param administradorSucursales Objeto IAdministradorSucursales que representa el subsistema
     * de administración de sucursales.
     * @param administradorProductos Objeto IAdministradorProductos que representa el subsistema
     * de administración de productos.
     */
    public FAdministradorPaqueterias(List<Paqueteria> listaPaqueterias, IAdministradorClientes administradorClientes, IAdministradorSucursales administradorSucursales, IAdministradorProductos administradorProductos) {
        this.listaPaqueterias = listaPaqueterias;
        this.administradorClientes = administradorClientes;
        this.administradorSucursales = administradorSucursales;
        this.administradorProductos = administradorProductos;
    }

    /**
     * Método que permite obtener la lista de todas las paqueterías disponibles
     * en el sistema con información básica para su selección.
     *
     * @return Objeto List de InformacionSeleccionPaqueteriaDTO que contiene la información
     * básica de las paqueterías disponibles.
     */

    @Override
    public List<InformacionSeleccionPaqueteriaDTO> obtenerPaqueterias() {
        List<InformacionSeleccionPaqueteriaDTO> listaInformacionSeleccionPaqueteriaDTO = new LinkedList<>();
        
        for(Paqueteria paqueteria: listaPaqueterias){
            listaInformacionSeleccionPaqueteriaDTO.add(
                    new InformacionSeleccionPaqueteriaDTO(paqueteria.getId(), paqueteria.getDireccionImagenPaqueteria()));
        }

        return listaInformacionSeleccionPaqueteriaDTO;
    }

    /**
     * Método que permite calcular el costo de envío de un producto considerando
     * las direcciones del cliente y la matriz, el peso del producto y el tiempo
     * estimado de envío.
     *
     * @param direccionClienteProductosEnvioDTO Objeto DireccionClientePesoTiempoProductoInventarioDTO
     * que contiene la información necesaria para el cálculo del costo de envío.
     * @return Objeto Float que representa el costo de envío calculado.
     * @throws PaqueteriasIdPaqueteriaInvalidoException Se lanza si se comprueba que el ID
     * de la paquetería es inválido o no existe en el sistema.
     */
    @Override
    public Float obtenerCostoEnvioProducto(DireccionClientePesoTiempoProductoInventarioDTO direccionClienteProductosEnvioDTO) 
            throws PaqueteriasIdPaqueteriaInvalidoException{
        
        Integer idPaqueteria = direccionClienteProductosEnvioDTO.getCodigoPaqueteria();
        
        // Se valida el ID de Paquteria:
        if(!validarPaqueteria(idPaqueteria)){
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería: " + idPaqueteria + " no existe.");
        }
        
        Paqueteria paqueteriaRecuperada = obtenerPaqueteria(idPaqueteria);
        
        if(paqueteriaRecuperada == null){
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería: " + idPaqueteria + " no existe.");
        }
        
        // Se recuperan los cobros por Kg y hora de envío de la paquetería recuperada.
        Float cobroKg = paqueteriaRecuperada.getCobroKg();
        Float cobroHora = paqueteriaRecuperada.getCobroHora();
        
        // Se obtiene el peso del peso en Kg del producto de la DTO del parámetro y su tiempo de envío a Matriz.
        Double pesoKgProducto = direccionClienteProductosEnvioDTO.getPesoKgProductoInventario();
        Float tiempoEnvioHoras = direccionClienteProductosEnvioDTO.getTiempoEnvioMatrizHorasProductoInventario();
        
        if(tiempoEnvioHoras == 0){
            return 0F;
        }

        return (float)(cobroKg * pesoKgProducto) + (cobroHora * tiempoEnvioHoras);
             
        
    }

    /**
     * Método que permite verificar si el ID de una paquetería es válido.
     *
     * @param idPaqueteria Objeto Integer que representa el ID de la paquetería a validar.
     * @return true si el ID de la paquetería es válido y existe en el sistema, false en caso contrario.
     */
    @Override
    public boolean validarPaqueteria(Integer idPaqueteria){
        for(Paqueteria paqueteria: listaPaqueterias){
            if(paqueteria.getId() == idPaqueteria){
                return true;
            }
        }
        
        return false;
    }

    /**
     * Método que permite obtener una paquetería a partir de su ID.
     *
     * @param idPaqueteria Objeto Integer que representa el ID de la paquetería a obtener.
     * @return Objeto Paqueteria que representa la paquetería con el ID especificado,
     * o null si no se encuentra una paquetería con ese ID.
     */
    @Override
    public Paqueteria obtenerPaqueteria(Integer idPaqueteria){
        Paqueteria paqueteriaBuscar = null;
        
        for(Paqueteria paqueteria: listaPaqueterias){
            if(paqueteria.getId() == idPaqueteria){
                paqueteriaBuscar = paqueteria;
            }
        }
        
        return paqueteriaBuscar;
    }
}
