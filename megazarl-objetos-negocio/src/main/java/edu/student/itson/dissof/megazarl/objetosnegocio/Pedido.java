package edu.student.itson.dissof.megazarl.objetosnegocio;

import java.util.HashMap;
import java.util.Objects;

/**
 * Pedido.java
 *
 * Clase que representa un pedido realizado en el sistema, conteniendo los productos
 * solicitados, la paquetería seleccionada y el estado actual del envío.
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
public class Pedido {
    
    private Integer id;
    HashMap<ProductoInventario, Boolean> mapaProductosRequeridos;
    private Paqueteria paqueteria;
    private EstadoPedido estado;

    private Integer PROXIMO_ID_PEDIDO = 1;

    /**
     * Constructor que inicializa un pedido con todos sus atributos.
     * @param mapaProductosRequeridos Objeto HashMap que mapea productos de inventario con un booleano que indica si ya fueron entregados a la matriz.
     * @param paqueteria Objeto Paqueteria que representa la paquetería seleccionada para el envío.
     * @param estado Objeto EstadoPedido que representa el estado actual del pedido.
     */
    public Pedido(HashMap<ProductoInventario, Boolean> mapaProductosRequeridos, Paqueteria paqueteria, EstadoPedido estado) {
        this.id = PROXIMO_ID_PEDIDO++;
        this.mapaProductosRequeridos = mapaProductosRequeridos;
        this.paqueteria = paqueteria;
        this.estado = estado;
    }

    /**
     * Método que permite obtener el ID del pedido.
     * @return Objeto Integer que representa el ID único del pedido.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Método que permite establecer el ID del pedido.
     * @param id Objeto Integer que representa el ID único del pedido.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Método que permite obtener el mapa de productos requeridos y su estado de entrega a la matriz.
     * @return Objeto HashMap que mapea productos de inventario con un booleano que indica si ya fueron entregados a la matriz.
     */
    public HashMap<ProductoInventario, Boolean> getMapaProductosRequeridos() {
        return mapaProductosRequeridos;
    }

    /**
     * Método que permite establecer el mapa de productos requeridos y su estado de entrega a la matriz.
     * @param mapaProductosRequeridos Objeto HashMap que mapea productos de inventario con un booleano que indica si ya fueron entregados a la matriz.
     */
    public void setMapaProductosRequeridos(HashMap<ProductoInventario, Boolean> mapaProductosRequeridos) {
        this.mapaProductosRequeridos = mapaProductosRequeridos;
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
     * Método que permite obtener el estado actual del pedido.
     * @return Objeto EstadoPedido que representa el estado actual del pedido.
     */
    public EstadoPedido getEstado() {
        return estado;
    }

    /**
     * Método que permite establecer el estado actual del pedido.
     * @param estado Objeto EstadoPedido que representa el estado actual del pedido.
     */
    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    /**
     * Método que permite obtener el próximo ID disponible para un pedido.
     * @return Objeto Integer que representa el próximo ID disponible para un pedido.
     */
    public Integer getPROXIMO_ID_PEDIDO() {
        return PROXIMO_ID_PEDIDO;
    }

    /**
     * Método que permite establecer el próximo ID disponible para un pedido.
     * @param PROXIMO_ID_PEDIDO Objeto Integer que representa el próximo ID disponible para un pedido.
     */
    public void setPROXIMO_ID_PEDIDO(Integer PROXIMO_ID_PEDIDO) {
        this.PROXIMO_ID_PEDIDO = PROXIMO_ID_PEDIDO;
    }

    /**
     * Método que calcula el código hash del objeto, basado en su ID.
     * @return Valor int que representa el código hash del pedido.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Método que compara este pedido con otro objeto para determinar igualdad.
     * @param obj Objeto a comparar con este pedido.
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
        final Pedido other = (Pedido) obj;
        return Objects.equals(this.id, other.id);
    }
}
