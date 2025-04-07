package edu.student.itson.dissof.megazarl.administradorpaqueterias;


import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.dto.DireccionClientePesoTiempoEnvioPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionSeleccionPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.PaqueteriaON;
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

    private final AdministradorPaqueterias administrador;


    /**
     * Constructor que inicializa un administrador de paqueterías con todos sus atributos.
     *
     * @param listaPaqueterias Objeto List que contiene las paqueterías disponibles en el sistema.
     */

    public FAdministradorPaqueterias(List<PaqueteriaON> listaPaqueterias){
        administrador = new AdministradorPaqueterias(listaPaqueterias);
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
        return administrador.obtenerPaqueterias();
    }

    /**
     * Método que permite calcular el costo de envío de un producto considerando
     * las direcciones del cliente y la matriz, el peso del producto y el tiempo
     * estimado de envío.
     *
     * @param direccionClienteProductosEnvioDTO Objeto DireccionClientePesoTiempoEnvioPaqueteriaDTO
 que contiene la información necesaria para el cálculo del costo de envío.
     * @return Objeto Float que representa el costo de envío calculado.
     * @throws PaqueteriasIdPaqueteriaInvalidoException Se lanza si se comprueba que el ID
     * de la paquetería es inválido o no existe en el sistema.
     */
    @Override
    public Float obtenerCostoEnvioProducto(DireccionClientePesoTiempoEnvioPaqueteriaDTO direccionClienteProductosEnvioDTO) throws PaqueteriasIdPaqueteriaInvalidoException {
        return administrador.obtenerCostoEnvioProducto(direccionClienteProductosEnvioDTO);
    }

    /**
     * Método que permite verificar si el ID de una paquetería es válido.
     *
     * @param idPaqueteria Objeto Integer que representa el ID de la paquetería a validar.
     * @return true si el ID de la paquetería es válido y existe en el sistema, false en caso contrario.
     */
    @Override
    public boolean validarPaqueteria(Integer idPaqueteria) {
        return administrador.validarPaqueteria(idPaqueteria);
    }

    /**
     * Método que permite obtener una paquetería a partir de su ID.
     *
     * @param idPaqueteria Objeto Integer que representa el ID de la paquetería a obtener.
     * @return Objeto Paqueteria que representa la paquetería con el ID especificado,
     * o null si no se encuentra una paquetería con ese ID.
     */
    @Override
    public PaqueteriaON obtenerPaqueteria(Integer idPaqueteria) {
        return administrador.obtenerPaqueteria(idPaqueteria);
    }
}
