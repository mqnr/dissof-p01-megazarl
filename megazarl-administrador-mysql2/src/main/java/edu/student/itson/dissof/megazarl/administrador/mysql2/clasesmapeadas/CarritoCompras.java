package edu.student.itson.dissof.megazarl.administrador.mysql2.clasesmapeadas;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
    public CarritoCompras(
            Boolean esVigente,
            Cliente cliente,
            Paqueteria paqueteria){
        
        this.esVigente = esVigente;
        this.cliente = cliente;
        this.paqueteria = paqueteria;
    }
    
    public CarritoCompras(
            Boolean esVigente,
            Cliente cliente){
       
        this.esVigente = esVigente;
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
    
    @Column(name = "es_vigente")
    private Boolean esVigente;

    
    @ManyToOne()
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    
    @ManyToOne()
    @JoinColumn(name = "id_paqueteria", nullable = true)
    private Paqueteria paqueteria;
    

    @OneToMany(mappedBy = "carritoCompras", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE}) 
    private List<ProductoCarrito> productosCarritoCompras = new ArrayList();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEsVigente() {
        return esVigente;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Paqueteria getPaqueteria() {
        return paqueteria;
    }

    public void setPaqueteria(Paqueteria paquteria) {
        this.paqueteria = paquteria;
    }
    

    public List<ProductoCarrito> getProductosCarritoCompras() {
        return productosCarritoCompras;
    }

    public void agregarProductoCarritoCompras(ProductoCarrito productoCarritoCompras) {

        if(productoCarritoCompras != null 
                && productosCarritoCompras != null 
                && !productosCarritoCompras.contains(productoCarritoCompras)){
            
            productosCarritoCompras.add(productoCarritoCompras);
            
            if(productoCarritoCompras.getCarritoCompras() == null){
                productoCarritoCompras.setCarritoCompras(this);
            }
            
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