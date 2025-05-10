package edu.student.itson.dissof.megazarl.administrador.sql.administradorsql.excepciones;
/**
 * ProductosInventarioIdInvalidoException.java
 *
 * Clase que representa una excepción lanzada cuando se intenta utilizar un ID
 * de productos en inventario que no existe o no es válido en el sistema, dentro del subsistema
 * administrador de sql.
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
public class ProductosInventarioIdInvalidoException extends Exception{

    public ProductosInventarioIdInvalidoException() {
    }

    public ProductosInventarioIdInvalidoException(String msg) {
        super(msg);
    }
}
