package edu.student.itson.dissof.megazarl.administradorproductos;

import edu.student.itson.dissof.megazarl.dto.InformacionProductoDTO;
import edu.student.itson.dissof.megazarl.dto.ProductoInicioDTO;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import edu.student.itson.dissof.megazarl.objetosnegocio.Producto;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoInventario;

public class FAdministradorProductos implements IAdministradorProductos {
    private List<Producto> listaProductos
            = Arrays.asList(
                    new Producto(1, 3, "Sandía", "Summer Breeze", "Summer Breeze es una Sandia Triploide o sin semilla de madurez "
                            + "intermedio precoz y buena capacidad y amarre de frutos de alta calidad para el mercado de exportacion.",
                            5000, 9400f, "Seminis", "/sandiaSummerBreeze.png", "/seminis.png",
                            new ProductoInventario[]{new ProductoInventario(1, 1, 1, 10),
                                new ProductoInventario(2, 1, 2, 3), new ProductoInventario(3, 1, 3, 0),
                                new ProductoInventario(1, 1, 4, 0)}),
                    new Producto(6, 1, "Chile", "Mixteco", "Planta de porte vigoroso, potencial de rebrote que le brinda alto potencial de rendimiento."
                            + " Forma de fruto ligeramente conca de color oscuro de 4 1/2 a 5 pulgada. Frutos de paredes gruesas con buen llenado. Variedad con "
                            + "alto potencial de rendimiento, resistencia a BLS y planta vigorosa.", 25, 24300f, "Harris Moran",
                            "/chileMixteco.png", "/harrisMoran.png", new ProductoInventario[]{new ProductoInventario(6, 6, 1, 15),
                        new ProductoInventario(7, 6, 2, 20), new ProductoInventario(8, 6, 3, 2),
                        new ProductoInventario(9, 6, 4, 0)}),
                    new Producto(2, 4, "Melón", "Híbrido Cruiser", "Semilla de melón híbrido Cruiser F1, de amplia adaptabilidad y altos rendimientos, "
                            + "frutos grandes (9) y muy uniformes, de alta calidad de empaque. Mantiene tamaños en bajas temperaturas. Su pulpa es firme y crujiente "
                            + "de excelente color. De madurez relativa precoz.", 10, 7200f, "Enza Zaden",
                            "/melonHibridoCruiser.png", "/enzaZaden.png", new ProductoInventario[]{new ProductoInventario(15, 2, 1, 0),
                        new ProductoInventario(16, 2, 2, 1), new ProductoInventario(17, 2, 3, 3),
                        new ProductoInventario(18, 2, 4, 12)})
            );

    @Override
    public List<ProductoInicioDTO> obtenerProductosVenta() {
        List<ProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        for (Producto producto : listaProductos) {
            ProductoInventario[] arregloProductoInventario = producto.getArregloProductoInventario();
            boolean hayExistencias = false;
            for (int i = 0; i < arregloProductoInventario.length; i++) {
                if (arregloProductoInventario[i].getCantidad() != 0) {
                    hayExistencias = true;
                }
            }

            if (hayExistencias) {
                listaProductoInicioDTO.add(new ProductoInicioDTO(producto.getId(), producto.getNombre(), producto.getVariedad(), producto.getPrecio(),
                        producto.getMilesSemillas(), producto.getDireccionImagenProducto(), producto.getDireccionImagenProveedor()));
            }

        }

        return listaProductoInicioDTO;
    }

    @Override
    public InformacionProductoDTO obtenerInformacionProducto(Integer idProducto) {
        InformacionProductoDTO informacionProductoDTO = null;

        for (Producto producto : listaProductos) {
            if (producto.getId().equals(idProducto)) {
                ProductoInventario[] arregloProductoInventario = producto.getArregloProductoInventario();
                boolean hayExistencias = false;
                for (int i = 0; i < arregloProductoInventario.length; i++) {
                    if (arregloProductoInventario[i].getCantidad() != 0) {
                        hayExistencias = true;
                    }
                }
                if (hayExistencias) {
                    return new InformacionProductoDTO(producto.getId(), producto.getNombre(), producto.getVariedad(),
                            producto.getDescripcion(), producto.getPrecio(), producto.getMilesSemillas(), producto.getNombreProveedor(),
                            producto.getDireccionImagenProducto(), producto.getDireccionImagenProveedor());
                }
            }
        }

        return informacionProductoDTO;
    }

    @Override
    public Producto obtenerProductoPorId(Integer idProducto) {
        for (Producto producto : listaProductos) {
            if (producto.getId().equals(idProducto)) {
                return producto;
            }
        }

        return null;
    }
}
