package edu.student.itson.dissof.megazarl.administrador.sql.administradorsql.clasesmapeadas;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * Pedido.java
 *
 * Clase ENTITIY que representa el mapeo de la entidad Pedido 
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
 * Creación de la tabla pedidos
 */
@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {

    /**
     * Constructor por defecto
     */
    public Pedido() {
    }

    /**
     * Constructor con referencia a todos sus atributos
     * @param idCliente Representa el id del cliente
     * @param carritoCompras representa el carrito de compras que tenga asociado el cliente
     */
    public Pedido(Long idCliente, CarritoCompras carritoCompras) {
        this.idCliente = idCliente;
        this.carritoCompras = carritoCompras;
    }

    /**
     * Creación de la columna id_pedido dentro de la tabla pedidos
     * Genera un id_pedido autoincremental
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long id;

    /**
     * Creación de la columna id_cliente dentro de la tabla pedidos
     */
    @Column(name = "id_cliente") //TODO es nullable false o true
    private Long idCliente;
    
    /**
     * Representación de una relación 1 a 1 entre pedido y carrito de compras
     */
    @OneToOne
    @JoinColumn(name = "id_carrito", nullable = false, unique = true)
    private CarritoCompras carritoCompras;
    
    /**
     * Getters y Setters para cada atributo de la clase
     */
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public CarritoCompras getCarritoCompras() {
        return carritoCompras;
    }

    public void setCarritoCompras(CarritoCompras carritoCompras) {
        this.carritoCompras = carritoCompras;
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
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
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
        return "edu.student.itson.dissof.megazarl.administrador.sql.administradorsql.clasesmapeadas.Pedido[ id=" + id + " ]";
    }
    
}