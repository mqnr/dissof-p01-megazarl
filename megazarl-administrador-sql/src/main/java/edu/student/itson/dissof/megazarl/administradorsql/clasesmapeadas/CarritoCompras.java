package edu.student.itson.dissof.megazarl.administradorsql.clasesmapeadas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * CarritoCompras.java
 *
 * Clase ENTITIY que representa el mapeo de la entidad CarritoCompras 
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
 * Creación de la tabla carrito_compras
 */
@Entity
@Table(name = "carrito_compras")
public class CarritoCompras implements Serializable {

    /**
     * Constructor por defecto
     */
    public CarritoCompras() {
    }

    /**
     * Constructor con referencia a todos sus atributos
     * @param cliente Representa el id del cliente asociado al carrito de compras
     */
    public CarritoCompras(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Creación de la columna id_carrito dentro de la tabla carrito_compras
     * Genera un id_carrito autoincremental
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    private Long id;

    /**
     * 
     */
    @ManyToOne()
    @JoinColumn(name = "id_cliente", nullable = true)
    private Cliente cliente;
    
    /**
     * 
     */
    @OneToMany(mappedBy = "carritoCompras") 
    private List<ProductoCarritoCompras> productoCarritoCompras = new ArrayList();
    
    /**
     * Getter y Setters para cada atributo de la clase
     */
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ProductoCarritoCompras> getProductoCarritoCompras() {
        return productoCarritoCompras;
    }

    public void setProductoCarritoCompras(List<ProductoCarritoCompras> productoCarritoCompras) {
        this.productoCarritoCompras = productoCarritoCompras;
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
        if (!(object instanceof CarritoCompras)) {
            return false;
        }
        CarritoCompras other = (CarritoCompras) object;
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
        return "edu.student.itson.dissof.megazarl.administrador.sql.administradorsql.clasesmapeadas.CarritoCompras[ id=" + id + " ]";
    }
    
}