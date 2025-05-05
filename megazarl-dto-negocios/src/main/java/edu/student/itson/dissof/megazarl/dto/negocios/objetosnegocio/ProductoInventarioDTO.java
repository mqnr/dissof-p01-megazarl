
package edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio;

import java.util.Objects;

/**
 * ProveedorInventarioDTO.java
 * 
 * Clase DTO representa una unidad de un producto en el inventario de una sucursal
 * específica

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

public class ProductoInventarioDTO {

    /**
     * Objeto Long que representa el id del producto en inventario
     */
    private Long id;

    /**
     * Objeto ProductoDTO que representa el producto asociado a este producto en inventario
     */
    private ProductoDTO producto;

    /**
     * Objeto SucursalDTO que representa la sucursal a la que pertenece este producto en inventario
     */
    private SucursalDTO sucursal;

    /**
     * Constructor que permite instanciar un objeto de tipo ProductoInventarioDTO.
     *
     * @param id       Objeto Long que representa el id del producto en inventario
     * @param producto Objeto ProductoDTO que representa el producto asociado a este producto en inventario
     * @param sucursal Objeto SucursalDTO que representa la sucursal a la que pertenece este producto en inventario
     */
    public ProductoInventarioDTO(
            Long id, 
            ProductoDTO producto,
            SucursalDTO sucursal) {
        this.id = id;
        this.producto = producto;
        this.sucursal = sucursal;
    }

    /**
     * Método que permite obtener el id del producto en inventario
     * @return Objeto Integer que representa el id del producto en inventario
     */
    public Long getId() {
        return id;
    }

    /**
     * Método que permite obtener el producto asociado a este producto en inventario
     * @return Objeto ProductoDTO que representa el producto asociado a este producto en inventario
     */
    public ProductoDTO getProducto() {
        return producto;
    }

    /**
     * Método que permite obtener la sucursal a la que pertenece este producto en inventario
     * @return Objeto SucursalDTO que representa la sucursal a la que pertenece este producto en inventario
     */
    public SucursalDTO getSucursal() {
        return sucursal;
    }

    /**
     * Método que permite obtener el hash code del producto en inventario, a partir de su Id
     * @return Dato int que representa el hash code del producto en inventario
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Método que permite verificar si el objeto del parámetro es igual 
     * a este producto en inventario, basándose en su id
     * @param obj Objeto a determinar si es igual a este producto en inventario
     * @return Dato boolean, true si el objeto es igual al producto en inventario, false en
     * caso contrario
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
        final ProductoInventarioDTO other = (ProductoInventarioDTO) obj;
        return Objects.equals(this.id, other.id);
    }
}
