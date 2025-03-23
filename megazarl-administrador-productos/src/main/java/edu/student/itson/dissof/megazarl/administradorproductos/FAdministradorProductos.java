package edu.student.itson.dissof.megazarl.administradorproductos;

import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoDTO;
import edu.student.itson.dissof.megazarl.dto.ProductoInicioDTO;
import java.util.LinkedList;
import java.util.List;
import edu.student.itson.dissof.megazarl.objetosnegocio.Producto;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoInventario;
import java.util.Arrays;
import java.util.HashMap;

public class FAdministradorProductos implements IAdministradorProductos {

    private HashMap<Integer, Integer> mapaIdsProductosCantidades;
             
    
    public FAdministradorProductos(HashMap<Integer, Integer> listaProductos){
        this.mapaIdsProductosCantidades = mapaIdsProductosCantidades;
    }

    public void cosultarInventarioProducto(Integer idProducto){
        
    }
    
    @Override
    public List<ProductoInicioDTO> obtenerProductosVenta() {

        List<ProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        for(HashMap.Entry<Integer, Integer> productoDisponibiliad: mapaIdsProductosCantidades.entrySet()){

            int idProducto = productoDisponibiliad.getKey();
            int cantidadProducto = productoDisponibiliad.getValue();

            if(idProducto == 1 && cantidadProducto > 0){
                listaProductoInicioDTO.add(
                new ProductoInicioDTO(
                    1, 
                    "Sandía", 
                    "Summer Breeze", 
                    9400d,
                    5, 
                    "/sandiaSummerBreeze.png", 
                    "/seminis.png")

                );


            } else if(idProducto == 6 && cantidadProducto > 0){
                listaProductoInicioDTO.add(
                    new ProductoInicioDTO(
                        6, 
                        "Chile", 
                        "Mixteco", 
                        24300d,
                        25, 
                        "/chileMixteco.png", 
                        "/harrisMoran.png")

                );
            } else if(idProducto == 2 && cantidadProducto > 0){
                listaProductoInicioDTO.add(
                    new ProductoInicioDTO(
                        2, 
                        "Melón", 
                        "Híbrido Cruiser", 
                        7200d,
                        10, 
                        "/melonHibridoCruiser.png", 
                        "/enzaZaden.png")

                    );
            }
 
        }
 

        return listaProductoInicioDTO;
    }
    
    
    @Override
    
    public List<ProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto){
        
         List<ProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();
         
        String nombreProductoMinusculas = nombreProducto.toLowerCase();
        
        for(HashMap.Entry<Integer, Integer> productoDisponibiliad: mapaIdsProductosCantidades.entrySet()){

            int idProducto = productoDisponibiliad.getKey();
            int cantidadProducto = productoDisponibiliad.getValue();

            if(idProducto == 1 && cantidadProducto > 0 && nombreProductoMinusculas.contains("sandia")){
                listaProductoInicioDTO.add(
                new ProductoInicioDTO(
                    1, 
                    "Sandía", 
                    "Summer Breeze", 
                    9400d,
                    5, 
                    "/sandiaSummerBreeze.png", 
                    "/seminis.png")

                );

            } else if(idProducto == 6 && cantidadProducto > 0 && nombreProductoMinusculas.contains("chile")){
                listaProductoInicioDTO.add(
                    new ProductoInicioDTO(
                        6, 
                        "Chile", 
                        "Mixteco", 
                        24300d,
                        25, 
                        "/chileMixteco.png", 
                        "/harrisMoran.png")

                );
            } else if(idProducto == 2 && cantidadProducto > 0 && nombreProductoMinusculas.contains("melon")) {
                listaProductoInicioDTO.add(
                    new ProductoInicioDTO(
                        2, 
                        "Melón", 
                        "Híbrido Cruiser", 
                        7200d,
                        10, 
                        "/melonHibridoCruiser.png", 
                        "/enzaZaden.png")

                    );
            }
 
        }

        return listaProductoInicioDTO;
    }
    
    
    @Override
    public List<ProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto, String variedadProducto){
        
        String nombreProductoMinusculas = nombreProducto.toLowerCase();
        String variedadProductoMinusculas = variedadProducto.toLowerCase();

        List<ProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        for(HashMap.Entry<Integer, Integer> productoDisponibiliad: mapaIdsProductosCantidades.entrySet()){

            int idProducto = productoDisponibiliad.getKey();
            int cantidadProducto = productoDisponibiliad.getValue();

            if(idProducto == 1 && cantidadProducto > 0 && nombreProductoMinusculas.contains("sandia") 
                    && variedadProductoMinusculas.equals("summer breeze")){
                listaProductoInicioDTO.add(
                new ProductoInicioDTO(
                    1, 
                    "Sandía", 
                    "Summer Breeze", 
                    9400d,
                    5, 
                    "/sandiaSummerBreeze.png", 
                    "/seminis.png")

                );

            } else if(idProducto == 6 && cantidadProducto > 0 && nombreProductoMinusculas.contains("chile") 
                    && variedadProductoMinusculas.equals("Mixtecos")){
                listaProductoInicioDTO.add(
                    new ProductoInicioDTO(
                        6, 
                        "Chile", 
                        "Mixteco", 
                        24300d,
                        25, 
                        "/chileMixteco.png", 
                        "/harrisMoran.png")

                );
            } else if(idProducto == 2 && cantidadProducto > 0 && nombreProductoMinusculas.contains("melon")
                    && variedadProductoMinusculas.contains("hibrido cruiser")){

                listaProductoInicioDTO.add(
                    new ProductoInicioDTO(
                        2, 
                        "Melón", 
                        "Híbrido Cruiser", 
                        7200d,
                        10, 
                        "/melonHibridoCruiser.png", 
                        "/enzaZaden.png")

                    );
            }
 
        }
        
        return listaProductoInicioDTO;
            

    }
    
    
    @Override
    public List<ProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto, String variedadProducto, String nombreProveedor){
        
        String nombreProductoMinusculas = nombreProducto.toLowerCase();
        String variedadProductoMinusculas = variedadProducto.toLowerCase();
        String nombreProveedorMinusculas = nombreProveedor.toLowerCase(); 

        List<ProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        for(HashMap.Entry<Integer, Integer> productoDisponibiliad: mapaIdsProductosCantidades.entrySet()){

            int idProducto = productoDisponibiliad.getKey();
            int cantidadProducto = productoDisponibiliad.getValue();

            if(idProducto == 1 && cantidadProducto > 0 && nombreProductoMinusculas.contains("sandia")
                    && variedadProductoMinusculas.contains("summer breeze")
                    && nombreProveedorMinusculas.contains("seminis")){

                listaProductoInicioDTO.add(
                new ProductoInicioDTO(
                    1, 
                    "Sandía", 
                    "Summer Breeze", 
                    9400d,
                    5, 
                    "/sandiaSummerBreeze.png", 
                    "/seminis.png")

                );

            } else if(idProducto == 6 && cantidadProducto > 0 && nombreProductoMinusculas.contains("chile") 
                    && nombreProductoMinusculas.contains("mixteco")
                    && variedadProductoMinusculas.equals("Mixtecos")
                    && nombreProveedorMinusculas.equals("harris moran")){
                listaProductoInicioDTO.add(
                    new ProductoInicioDTO(
                        6, 
                        "Chile", 
                        "Mixteco", 
                        24300d,
                        25, 
                        "/chileMixteco.png", 
                        "/harrisMoran.png")

                );
            } else if(idProducto == 2 && cantidadProducto > 0 && nombreProductoMinusculas.contains("melon")
                    && variedadProductoMinusculas.contains("hirido cruiser")
                    && nombreProveedorMinusculas.contains("enza zaden")){

                listaProductoInicioDTO.add(
                    new ProductoInicioDTO(
                        2, 
                        "Melón", 
                        "Híbrido Cruiser", 
                        7200d,
                        10, 
                        "/melonHibridoCruiser.png", 
                        "/enzaZaden.png")

                    );
            }
 
        }

        return listaProductoInicioDTO;
            
    }
    
    
    @Override 
    public InformacionProductoDTO obtenerInformacionProducto(Integer idProducto) {

        InformacionProductoDTO informacionProductoDTO = null;

        for(HashMap.Entry<Integer, Integer> productoDisponibiliad: mapaIdsProductosCantidades.entrySet()){

            int idProductoActual = productoDisponibiliad.getKey();
            int cantidadProducto = productoDisponibiliad.getValue();

            if(idProductoActual == 1 && cantidadProducto > 0){

                informacionProductoDTO = new InformacionProductoDTO(
                    1, 
                    "Sandía", 
                    "Summer Breeze", 
                    "Summer Breeze es una Sandia Triploide o sin semilla de madurez "
                    + "intermedio precoz y buena capacidad y amarre de frutos de alta calidad para el mercado de exportacion.",
                    9400d,
                    5, 
                    "Seminis",
                    "/sandiaSummerBreeze.png", 
                    "/seminis.png");

            } else if(idProducto == 6 && cantidadProducto > 0){
                informacionProductoDTO = new InformacionProductoDTO(
                        6, 
                        "Chile", 
                        "Mixteco", 
                        "Planta de porte vigoroso, potencial de rebrote que le brinda alto potencial de rendimiento."
                        + " Forma de fruto ligeramente conca de color oscuro de 4 1/2 a 5 pulgada. Frutos de paredes gruesas con buen llenado. Variedad con "
                        + "alto potencial de rendimiento, resistencia a BLS y planta vigorosa.",
                        24300d,
                        25, 
                        "Harris Moran",
                        "/chileMixteco.png", 
                        "/harrisMoran.png"

                );
                
            } else if(idProducto == 2 && cantidadProducto > 0){

                informacionProductoDTO = new InformacionProductoDTO(
                        2, 
                        "Melón", 
                        "Híbrido Cruiser", 
                        "Semilla de melón híbrido Cruiser F1, de amplia adaptabilidad y altos rendimientos, "
                        + "frutos grandes (9) y muy uniformes, de alta calidad de empaque. Mantiene tamaños en bajas temperaturas. Su pulpa es firme y crujiente "
                        + "de excelente color. De madurez relativa precoz.",
                        7200d,
                        10, 
                        "Enza Zaden",
                        "/melonHibridoCruiser.png", 
                        "/enzaZaden.png");
            }
        }
        return informacionProductoDTO;
        
    }
   
    @Override
    public List<ProductoInventario> obtenerListaProductoInventario(Integer idProducto){
        
        Producto producto = this.obtenerProductoPorId(idProducto);
        
        return producto.getListaProductoInventario();
    }
    
     @Override
    public double obtenerCostoProducto(Integer idProducto){
        Producto producto = this.obtenerProductoPorId(idProducto);
        
        return producto.getPrecio();
    }
    
    private Producto obtenerProductoPorId(Integer idProducto){
        Producto producto = null;
        switch (idProducto) {
            case 1:
                producto = new Producto(
                        1,
                        3,
                        "Sandía",
                        "Summer Breeze",
                        "Summer Breeze es una Sandia Triploide o sin semilla de madurez "
                                + "intermedio precoz y buena capacidad y amarre de frutos de alta calidad para el mercado de exportacion.",
                        5,
                        9400d,
                        5d,
                        "Seminis",
                        "/sandiaSummerBreeze.png",
                        "/seminis.png",
                        Arrays.asList(
                                new ProductoInventario(1, 1, 1, 4.5f, 10),
                                new ProductoInventario(2, 1, 2, 2f, 3),
                                new ProductoInventario(3, 1, 3, 10f, 0),
                                new ProductoInventario(1, 1, 4, 3f, 0)));
                break;
            case 6:
                producto = new Producto(
                        6,
                        1,
                        "Chile",
                        "Mixteco",
                        "Planta de porte vigoroso, potencial de rebrote que le brinda alto potencial de rendimiento."
                                + " Forma de fruto ligeramente conca de color oscuro de 4 1/2 a 5 pulgada. Frutos de paredes gruesas con buen llenado. Variedad con "
                                + "alto potencial de rendimiento, resistencia a BLS y planta vigorosa.",
                        25,
                        24300d,
                        7d,
                        "Harris Moran",
                        "/chileMixteco.png",
                        "/harrisMoran.png",
                        Arrays.asList(
                                new ProductoInventario(6, 6, 1, 12.3f, 15),
                                new ProductoInventario(7, 6, 2, 5.4f, 20),
                                new ProductoInventario(8, 6, 3, 15f, 2),
                                new ProductoInventario(9, 6, 4, 5.6f, 0)));
                break;
            case 2:
                producto = new Producto(
                        2,
                        4,
                        "Melón",
                        "Híbrido Cruiser", "Semilla de melón híbrido Cruiser F1, de amplia adaptabilidad y altos rendimientos, "
                                + "frutos grandes (9) y muy uniformes, de alta calidad de empaque. Mantiene tamaños en bajas temperaturas. Su pulpa es firme y crujiente "
                                + "de excelente color. De madurez relativa precoz.", 10, 7200d, 2d, "Enza Zaden",
                        "/melonHibridoCruiser.png",
                        "/enzaZaden.png",
                        Arrays.asList(
                                new ProductoInventario(15, 2, 1, 10.4f, 0),
                                new ProductoInventario(16, 2, 2, 5.5f, 1),
                                new ProductoInventario(17, 2, 3, 4.5f, 3),
                                new ProductoInventario(18, 2, 4, 2.3f, 12)));
                break;
        }
        
        return producto;
    }

}
