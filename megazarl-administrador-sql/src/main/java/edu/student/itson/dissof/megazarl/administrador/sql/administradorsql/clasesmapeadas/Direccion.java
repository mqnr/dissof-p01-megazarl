package edu.student.itson.dissof.megazarl.administrador.sql.administradorsql.clasesmapeadas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * Direccion.java
 *
 * Clase ENTITIY que representa el mapeo de la entidad Direccion 
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
 * Creación de la tabla direcciones
 */
@Entity
@Table(name = "direcciones")
public class Direccion implements Serializable {

    /**
     * Constructor por defecto
     */
    public Direccion() {
    }

    /**
     * Constructor con referencia a todos sus atributos
     * @param codigoPostal Representa el código postal de alguna dirección
     * @param colonia Representa la colonia de alguna dirección
     * @param calle Representa la calle de alguna dirección
     * @param numero Representa el número de alguna dirección
     */
    public Direccion(String codigoPostal, String colonia, String calle, String numero) {
        this.codigoPostal = codigoPostal;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
    }

    /**
     * Creación de la columna id_cliente dentro de la tabla clientes
     * Genera un id_direccion autoincremental
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_direccion")
    private Long id;

    /**
     * Creación de la columna codigo_postal dentro de la tabla direcciones
     * NO admite valores nulos
     */
    @Column(name = "codigo_postal", nullable = false)
    private String codigoPostal;
    
    /**
     * Creación de la columna colonia dentro de la tabla direcciones
     * NO admite valores nulos
     */
    @Column(name = "colonia", nullable = false)
    private String colonia;
    
    /**
     * Creación de la columna calle dentro de la tabla direcciones
     * NO admite valores nulos
     */
    @Column(name = "calle", nullable = false)
    private String calle;
    
    /**
     * Creación de la columna numero dentro de la tabla direcciones
     * NO admite valores nulos
     */
    @Column(name = "numero", nullable = false)
    private String numero;
    
    /**
     * Representación de una relación 1 a Muchos entre dirección y 
     * paquetería
     */
    @OneToMany(mappedBy = "direccion")
    private List<Paqueteria> paqueterias = new ArrayList();
    
    /**
     * Representación de una relación 1 a Muchos entre dirección y 
     * cliente
     */
    @OneToMany(mappedBy = "direccion")
    private List<Cliente> clientes = new ArrayList();

    /**
     * Representación de una relación 1 a Mucho entre direccion y 
     * proveedor
     */
    @OneToMany(mappedBy = "direccion")
    private List<Proveedor> proveedor = new ArrayList();
    
    /**
     * Getter y Setters para cada atributo de la clase
     */
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<Paqueteria> getPaqueterias() {
        return paqueterias;
    }

    public void setPaqueterias(List<Paqueteria> paqueterias) {
        this.paqueterias = paqueterias;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Proveedor> getProveedor() {
        return proveedor;
    }

    public void setProveedor(List<Proveedor> proveedor) {
        this.proveedor = proveedor;
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
        if (!(object instanceof Direccion)) {
            return false;
        }
        Direccion other = (Direccion) object;
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
        return "edu.student.itson.dissof.megazarl.administrador.sql.administradorsql.clasesmapeadas.Direccion[ id=" + id + " ]";
    }
    
}