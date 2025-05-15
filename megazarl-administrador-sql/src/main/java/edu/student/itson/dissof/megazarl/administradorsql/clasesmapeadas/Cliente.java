package edu.student.itson.dissof.megazarl.administradorsql.clasesmapeadas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * Cliente.java
 *
 * Clase ENTITIY que representa el mapeo de la entidad Cliente 
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Manuel Romo López
 * ID: 00000253080
 *
 */

/**
 * Creación de la tabla clientes
 */
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    /**
     * Constructor por defecto
     */
    public Cliente() {
    }

    /**
     * Constructor con referencia a todos sus atributos
     * @param nombres Representa los nombres del cliente
     * @param apellidoPaterno Representa el apellido paterno del cliente
     * @param apellidoMaterno Representa el apellido materno del cliente
     * @param direccionEnvio Representa la dirección de envío del cliente
     */
    public Cliente(
            String nombres,
            String apellidoPaterno, 
            String apellidoMaterno,
            Direccion direccionEnvio) {
        
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccionEnvio = direccionEnvio;
    }
    
    /**
     * Constructor con referencia a todos sus atributos, excepto la dirección.
     * @param nombres Representa los nombres del cliente
     * @param apellidoPaterno Representa el apellido paterno del cliente
     * @param apellidoMaterno Representa el apellido materno del cliente
     */
    public Cliente(
            String nombres,
            String apellidoPaterno, 
            String apellidoMaterno) {
        
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }
    
    /**
     * Creación de la columna id_cliente dentro de la tabla clientes
     * Genera un id_cliente autoincremental
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    /**
     * Creación de la columna nombres dentro de la tabla clientes
     * NO admite valores nulos
     */
    @Column(name = "nombres", nullable = false)
    private String nombres;
    
    /**
     * Creación de la columna apellido_paterno dentro de la tabla clientes
     * NO admite valores nulos
     */
    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;
    
    /**
     * Creación de la columna apellido_materno dentro de la tabla clientes
     * NO admite valores nulos
     */
    @Column(name = "apellido_materno", nullable = false)
    private String apellidoMaterno;

    /**
     * Representación de una llave foránea con referencia
     * a la tabla dirección
     */
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_direccion", nullable = true)
    private Direccion direccionEnvio;
    
    /**
     * Representación de una relación 1 a 1 entre cliente y 
     * carrito de compras
     */
    @OneToMany(mappedBy = "cliente")
    private List<CarritoCompras> listaCarritosCompras = new ArrayList();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Direccion getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(Direccion direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public List<CarritoCompras> getCarritoCompras() {
        return listaCarritosCompras;
    }
    
    public void agregarCarritoCompras(CarritoCompras carritoCompras){
        
        if(listaCarritosCompras != null && !listaCarritosCompras.contains(carritoCompras)){
            
            listaCarritosCompras.add(carritoCompras);
            
        }
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
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
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
        return "edu.student.itson.dissof.megazarl.administrador.sql.administradorsql.clasesmapeadas.Cliente[ id=" + id + " ]";
    }
    
}