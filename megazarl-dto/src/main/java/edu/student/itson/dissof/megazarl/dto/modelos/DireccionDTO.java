package edu.student.itson.dissof.megazarl.dto.modelos;

/**
 * Direccion.java
 *
 * Clase record que representa una dirección completa, incluyendo datos
 * de numeración, calle, colonia, ciudad, estado y código postal.
 * Utilizada como modelo de datos inmutable para transferir información
 * de direcciones en el sistema.
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
public record Direccion(
        String numeroExterior,
        String calle,
        String numeroInterior,
        String colonia,
        String ciudad,
        String estado,
        String codigoPostal) {

}
