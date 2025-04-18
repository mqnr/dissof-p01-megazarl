package edu.student.itson.dissof.megazarl.administradorpaqueterias;

import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.dto.DireccionClientePesoTiempoEnvioPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionSeleccionPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.modelos.PaqueteriaDTO;

import java.util.List;

/**
 * IAdministradorPaqueterias.java
 *
 * Interfaz del subsistema AdministradorPaqueterias, que administra las paqueterías
 * disponibles en el sistema, permitiendo obtener información sobre ellas y calcular
 * costos de envío basados en características del producto y distancias.
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
public interface IAdministradorPaqueterias {
    /**
     * Método que permite verificar si el ID de una paquetería es válido.
     *
     * @param idPaqueteria Objeto Integer que representa el ID de la paquetería a validar.
     * @return true si el ID de la paquetería es válido, false en caso contrario.
     */
    public abstract boolean validarId(Integer idPaqueteria);

    /**
     * Método que permite obtener una paquetería a partir de su ID.
     *
     * @param id Objeto Integer que representa el ID de la paquetería a obtener.
     * @return Objeto Paqueteria que representa la paquetería con el ID especificado.
     */
    public abstract PaqueteriaDTO obtenerPaqueteria(Integer id);

    /**
     * Método que permite obtener la lista de todas las paqueterías disponibles
     * en el sistema con información básica para su selección.
     *
     * @return Objeto List de InformacionSeleccionPaqueteriaDTO que contiene la información
     * básica de las paqueterías disponibles.
     */
    public abstract List<InformacionSeleccionPaqueteriaDTO> obtenerPaqueterias();

    /**
     * Método que permite calcular el costo de envío de un producto considerando
     * las direcciones del cliente y la matriz, el peso del producto y el tiempo
     * estimado de envío.
     *
     * @param direccionClienteProductosEnvioDTO Objeto DireccionClientePesoTiempoEnvioPaqueteriaDTO
 que contiene la información necesaria para el cálculo del costo de envío.
     * @return Objeto Float que representa el costo de envío calculado.
     * @throws PaqueteriasIdPaqueteriaInvalidoException Se lanza si se comprueba que el ID
     * de la paquetería es inválido, dentro de este subsistema.
     */
    public abstract Float obtenerCostoEnvioProducto(DireccionClientePesoTiempoEnvioPaqueteriaDTO direccionClienteProductosEnvioDTO) 
            throws PaqueteriasIdPaqueteriaInvalidoException;
}
