package edu.student.itson.dissof.megazarl.objetosnegocio;

import java.util.HashMap;
import java.util.Objects;

/**
 * CarritoCompras.java
 *
 * Clase que representa un carrito de compras en el sistema, que pertenece a un Cliente
 * y contiene productos con sus cantidades respectivas, así como una paquetería
 * seleccionada para el envío.
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
public class CarritoCompras {
    private Integer id;
    private Cliente cliente;
    private Paqueteria paqueteria;
    private Boolean pedidoRealizado;
    private HashMap<Producto, Integer> productosCantidades;
    
    private static Integer ID_PROXIMO_CARRITO_COMPRAS = 1;

    /**
     * Constructor que inicializa un carrito de compras con todos sus atributos.
     * @param cliente Objeto Cliente que representa al cliente dueño del carrito.
     * @param paqueteria Objeto Paqueteria que representa la paquetería seleccionada para el envío.
     * @param pedidoRealizado Objeto Boolean que indica si el pedido ya fue realizado (true) o no (false).
     * @param productosCantidades Objeto HashMap que mapea productos con sus cantidades respectivas en el carrito.
     */
    public CarritoCompras(Cliente cliente, Paqueteria paqueteria, Boolean pedidoRealizado, HashMap<Producto, Integer> productosCantidades) {
        this.id = ID_PROXIMO_CARRITO_COMPRAS++;
        this.cliente = cliente;
        this.paqueteria = paqueteria;
        this.pedidoRealizado = pedidoRealizado;
        this.productosCantidades = productosCantidades;
    }

    /**
     * Constructor que inicializa un carrito de compras sin paquetería seleccionada.
     * @param cliente Objeto Cliente que representa al cliente dueño del carrito.
     * @param pedidoRealizado Objeto Boolean que indica si el pedido ya fue realizado (true) o no (false).
     * @param productosCantidades Objeto HashMap que mapea productos con sus cantidades respectivas en el carrito.
     */
    public CarritoCompras(Cliente cliente, Boolean pedidoRealizado, HashMap<Producto, Integer> productosCantidades) {
        this.id = ID_PROXIMO_CARRITO_COMPRAS++;
        this.cliente = cliente;
        this.pedidoRealizado = pedidoRealizado;
        this.productosCantidades = productosCantidades;
    }

    /**
     * Método que permite obtener el ID del carrito.
     * @return Objeto Integer que representa el ID único del carrito.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Método que permite establecer el ID del carrito.
     * @param id Objeto Integer que representa el ID único del carrito.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Método que permite obtener el cliente dueño del carrito.
     * @return Objeto Cliente que representa al cliente dueño del carrito.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Método que permite establecer el cliente dueño del carrito.
     * @param cliente Objeto Cliente que representa al cliente dueño del carrito.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Método que permite obtener la paquetería seleccionada para el envío.
     * @return Objeto Paqueteria que representa la paquetería seleccionada para el envío.
     */
    public Paqueteria getPaqueteria() {
        return paqueteria;
    }

    /**
     * Método que permite establecer la paquetería seleccionada para el envío.
     * @param paqueteria Objeto Paqueteria que representa la paquetería seleccionada para el envío.
     */
    public void setPaqueteria(Paqueteria paqueteria) {
        this.paqueteria = paqueteria;
    }

    /**
     * Método que permite saber si el pedido ya fue realizado.
     * @return Objeto Boolean que indica si el pedido ya fue realizado (true) o no (false).
     */
    public Boolean getPedidoRealizado() {
        return pedidoRealizado;
    }

    /**
     * Método que permite establecer si el pedido ya fue realizado.
     * @param pedidoRealizado Objeto Boolean que indica si el pedido ya fue realizado (true) o no (false).
     */
    public void setPedidoRealizado(Boolean pedidoRealizado) {
        this.pedidoRealizado = pedidoRealizado;
    }

    /**
     * Método que permite obtener los productos y sus cantidades en el carrito.
     * @return Objeto HashMap que mapea productos con sus cantidades respectivas en el carrito.
     */
    public HashMap<Producto, Integer> getProductosCantidades() {
        return productosCantidades;
    }

    /**
     * Método que permite establecer los productos y sus cantidades en el carrito.
     * @param productosCantidades Objeto HashMap que mapea productos con sus cantidades respectivas en el carrito.
     */
    public void setProductosCantidades(HashMap<Producto, Integer> productosCantidades) {
        this.productosCantidades = productosCantidades;
    }

    /**
     * Método que calcula el código hash del objeto, basado en su ID.
     * @return Valor int que representa el código hash del carrito.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Método que compara este carrito con otro objeto para determinar igualdad.
     * @param obj Objeto a comparar con este carrito.
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
        final CarritoCompras other = (CarritoCompras) obj;
        return Objects.equals(this.id, other.id);
    }
}
