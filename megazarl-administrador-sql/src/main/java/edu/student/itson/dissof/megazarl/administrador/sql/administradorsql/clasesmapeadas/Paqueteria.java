package edu.student.itson.dissof.megazarl.administrador.sql.administradorsql.clasesmapeadas;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * Paqueteria.java
 *
 * Clase ENTITIY que representa el mapeo de la entidad Paqueteria 
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

/**
 * Creación de la tabla paqueterias
 */
@Entity
@Table(name = "paqueterias")
public class Paqueteria implements Serializable {

    /**
     * Constructor por defecto
     */
    public Paqueteria() {
    }

    /**
     * Constructor con referencia a todos sus atributos
     * @param nombre Representa el nombre de la paquetería
     * @param cobroKg Representa el cobro en relación a los kilogramos de la paqueterías
     * @param cobroHora Representa el cobro por hora de la paquetería
     * @param direccionImagenPaqueteria Representa la dirección o url de la imagen asociada a la paquetería
     * @param direccion Representa la dirección de donde de se encuentra la paquetería
     */
    public Paqueteria(String nombre, Float cobroKg, Float cobroHora, String direccionImagenPaqueteria, Direccion direccion) {
        this.nombre = nombre;
        this.cobroKg = cobroKg;
        this.cobroHora = cobroHora;
        this.direccionImagenPaqueteria = direccionImagenPaqueteria;
        this.direccion = direccion;
    }

    /**
     * Creación de la columna id_paqueteria dentro de la tabla paqueterias
     * Genera un id_paqueteria autoincremental
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paqueteria")
    private Long id;

    /**
     * Creación de la columna nombre dentro de la tabla paqueterias
     * NO admite valores nulos
     */
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    /**
     * Creación de la columna cobro_kg dentro de la tabla paqueterias
     * NO admite valores nulos
     */
    @Column(name = "cobro_kg", nullable = false)
    private Float cobroKg;
    
    /**
     * Creación de la columna cobro_hora dentro de la tabla paqueterias
     * NO admite valores nulos
     */
    @Column(name = "cobro_hora", nullable = false)
    private Float cobroHora;
    
    /**
     * Creación de la columna direccion_imagen dentro de la tabla paqueterias
     * NO admite valores nulos
     */
    @Column(name = "direccion_imagen", nullable = false)
    private String direccionImagenPaqueteria;
    
    /**
     * Representación de una relación Muchos a 1 entre paqueteria
     * y dirección
     */
    @ManyToOne()
    @JoinColumn(name = "id_direccion", nullable = true)
    private Direccion direccion;
    
    /**
     * Getters y Setters para cada atributo de la clase
     */
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getCobroKg() {
        return cobroKg;
    }

    public void setCobroKg(Float cobroKg) {
        this.cobroKg = cobroKg;
    }

    public Float getCobroHora() {
        return cobroHora;
    }

    public void setCobroHora(Float cobroHora) {
        this.cobroHora = cobroHora;
    }

    public String getDireccionImagenPaqueteria() {
        return direccionImagenPaqueteria;
    }

    public void setDireccionImagenPaqueteria(String direccionImagenPaqueteria) {
        this.direccionImagenPaqueteria = direccionImagenPaqueteria;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    
    /**
     * Método que genera hashCode para objetos de la clase entidad CarritoCompras
     * @return hash de los objetos de la clase
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Método que compara objetos de la clase a partir de su id
     * @param object Representa un objeto de la clase a comparar
     * @return valor booleano para identificar coincidencias
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paqueteria)) {
            return false;
        }
        Paqueteria other = (Paqueteria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Método toString que concatena e imprime los objetos de la clase
     * @return String con los atributos del carrito de compras
     */
    @Override
    public String toString() {
        return "edu.student.itson.dissof.megazarl.administrador.sql.administradorsql.clasesmapeadas.Paqueteria[ id=" + id + " ]";
    }
    
}
