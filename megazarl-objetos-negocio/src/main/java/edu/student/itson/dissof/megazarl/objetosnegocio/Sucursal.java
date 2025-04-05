package edu.student.itson.dissof.megazarl.objetosnegocio;

import java.util.Objects;

/**
 * Sucursal.java
 *
 * Clase que representa una sucursal física de la empresa en el sistema,
 * con su dirección completa y datos de identificación para la gestión de inventario.
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
public class Sucursal {

    private Integer id;
    private Boolean esMatriz;
    private Float tiempoMatriz;
    private String codigoPostal;
    private String calle;
    private String numero;

    /**
     * Constructor que inicializa una sucursal con todos sus atributos.
     * @param id Objeto Integer que representa el ID único de la sucursal.
     * @param esMatriz Objeto Boolean que indica si la sucursal es la matriz (true) o no (false).
     * @param tiempoMatriz Objeto Float que representa el tiempo en horas hasta la matriz.
     * @param codigoPostal Objeto String que representa el código postal de la sucursal.
     * @param calle Objeto String que representa la calle de la sucursal.
     * @param numero Objeto String que representa el número de domicilio de la sucursal.
     */
    public Sucursal(Integer id, Boolean esMatriz, Float tiempoMatriz, String codigoPostal, String calle, String numero) {
        this.id = id;
        this.esMatriz = esMatriz;
        this.tiempoMatriz = tiempoMatriz;
        this.codigoPostal = codigoPostal;
        this.calle = calle;
        this.numero = numero;
    }

    /**
     * Método que permite obtener el ID de la sucursal.
     * @return Objeto Integer que representa el ID único de la sucursal.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Método que permite establecer el ID de la sucursal.
     * @param id Objeto Integer que representa el ID único de la sucursal.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Método que permite saber si la sucursal es la matriz.
     * @return Objeto Boolean que indica si la sucursal es la matriz (true) o no (false).
     */
    public Boolean getEsMatriz() {
        return esMatriz;
    }

    /**
     * Método que permite establecer si la sucursal es la matriz.
     * @param esMatriz Objeto Boolean que indica si la sucursal es la matriz (true) o no (false).
     */
    public void setEsMatriz(Boolean esMatriz) {
        this.esMatriz = esMatriz;
    }

    /**
     * Método que permite obtener el tiempo en horas hasta la matriz.
     * @return Objeto Float que representa el tiempo en horas hasta la matriz.
     */

    public Float getTiempoMatriz() {
        return tiempoMatriz;
    }

    /**
     * Método que permite establecer el tiempo en horas hasta la matriz.
     * @param tiempoMatriz Objeto Float que representa el tiempo en horas hasta la matriz.
     */
    public void setTiempoMatriz(Float tiempoMatriz) {
        this.tiempoMatriz = tiempoMatriz;
    }

    /**
     * Método que permite obtener el código postal de la sucursal.
     * @return Objeto String que representa el código postal de la sucursal.
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Método que permite establecer el código postal de la sucursal.
     * @param codigoPostal Objeto String que representa el código postal de la sucursal.
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * Método que permite obtener la calle de la sucursal.
     * @return Objeto String que representa la calle de la sucursal.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Método que permite establecer la calle de la sucursal.
     * @param calle Objeto String que representa la calle de la sucursal.
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Método que permite obtener el número de domicilio de la sucursal.
     * @return Objeto String que representa el número de domicilio de la sucursal.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Método que permite establecer el número de domicilio de la sucursal.
     * @param numero Objeto String que representa el número de domicilio de la sucursal.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Método que calcula el código hash del objeto, basado en su ID.
     * @return Valor int que representa el código hash de la sucursal.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Método que compara esta sucursal con otro objeto para determinar igualdad.
     * @param obj Objeto a comparar con esta sucursal.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sucursal other = (Sucursal) obj;
        return Objects.equals(this.id, other.id);
    }
}
