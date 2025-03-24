package edu.student.itson.dissof.megazarl.administradorproductos;

import edu.student.itson.dissof.megazarl.dto.InformacionProductoDTO;
import edu.student.itson.dissof.megazarl.dto.ProductoInicioDTO;
import java.util.LinkedList;
import java.util.List;
import edu.student.itson.dissof.megazarl.objetosnegocio.Producto;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoInventario;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.util.Collections;

public class FAdministradorProductos implements IAdministradorProductos {


    private List<Integer> listaCodigosProductos;
    private List<Integer> listaCantidadProductosInventario;
    private List<List<Boolean>> listasProductosApartados;
    
    
    public FAdministradorProductos(List<Integer> listaCodigosProductos, 
            List<Integer> listaCantidadProductosInventario, 
            List<List<Boolean>> listasFechasHoraApartado){
        
        this.listaCodigosProductos = listaCodigosProductos;
        this.listaCantidadProductosInventario = listaCantidadProductosInventario;
        this.listasProductosApartados = listasFechasHoraApartado;
    }

    @Override
    public int cosultarInventarioProducto(Integer idProducto){
        
        if(validarProducto(idProducto)){
            
            int indiceIdProducto = listaCodigosProductos.indexOf(idProducto);
            int disponibilidadProducto = listaCantidadProductosInventario.get(indiceIdProducto);
            
            int cantitadProductosApartados = 0;
            
            for(Boolean productoApartado: listasProductosApartados.get(indiceIdProducto)){
                
                if(productoApartado != null && productoApartado){
                    cantitadProductosApartados++;
                }   
            }
            
            return disponibilidadProducto - cantitadProductosApartados;
            
        } else{
            return -1;
        }
    }
    
    
    @Override
    public boolean apartarProductoInventario(Integer idProducto){
        
        boolean apartadoExitoso = false;
    
        if (validarProducto(idProducto)) {
            
            if(cosultarInventarioProducto(idProducto) > 0){
                int indiceIdProducto = listaCodigosProductos.indexOf(idProducto);

                if (listaCantidadProductosInventario.get(indiceIdProducto) > 0) {

                    List<Boolean> productosApartados = listasProductosApartados.get(indiceIdProducto);


                    for (int i = 0; i < productosApartados.size(); i++) {
                        
                        Boolean productoApartado = productosApartados.get(i);

                        if (productoApartado != null && !productoApartado) {

                            productosApartados.set(i, true);

                            apartadoExitoso = true;

                            break;
                        }
                    }
                }
            }
            
        }

        return apartadoExitoso;
    }
    
    
    @Override
    public boolean desapartarProductoInventario(Integer idProducto){
        
        boolean desapartadoExitoso = false;
    
        if (validarProducto(idProducto)) {
            
            int indiceIdProducto = listaCodigosProductos.indexOf(idProducto);

            List<Boolean> productosApartados = listasProductosApartados.get(indiceIdProducto);

            for (int i = 0; i < productosApartados.size(); i++) {

                Boolean productoApartado = productosApartados.get(i);


                if (productoApartado != null && productoApartado) {

                    productosApartados.set(i, false);

                    desapartadoExitoso = true;

                    break;
                }
            }

        }


        return desapartadoExitoso;
    }
   
    
    @Override
    public boolean validarProducto(Integer idProducto){
        return (idProducto == 1 || idProducto == 6 || idProducto == 2);    
    }
    
    @Override
    public List<ProductoInicioDTO> obtenerProductosVenta() {

        List<ProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        for(int i = 0; i < listaCodigosProductos.size(); i++){
            
            int idProducto = listaCodigosProductos.get(i);
            int cantidadProducto = cosultarInventarioProducto(idProducto);

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
        
        for(int i = 0; i < listaCodigosProductos.size(); i++){
            
            int idProducto = listaCodigosProductos.get(i);
            int cantidadProducto = cosultarInventarioProducto(idProducto);

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

        for(int i = 0; i < listaCodigosProductos.size(); i++){
            
            int idProducto = listaCodigosProductos.get(i);
            int cantidadProducto = cosultarInventarioProducto(idProducto);

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

        for(int i = 0; i < listaCodigosProductos.size(); i++){
            
            int idProducto = listaCodigosProductos.get(i);
            int cantidadProducto = cosultarInventarioProducto(idProducto);

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
     
        int cantidadProducto = cosultarInventarioProducto(idProducto);

        if(idProducto == 1){

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

        } else if(idProducto == 6){
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

        } else if(idProducto == 2){

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

        return informacionProductoDTO;
        
    }
   
    @Override
    public List<Float> obtenerTiempoMatrizProductosInventario(Integer idProducto){
        
        List<Float> listaTiempoMatrizProductosInventario = new LinkedList<>(); 
        
        if(idProducto == 2){
            listaTiempoMatrizProductosInventario.add(5.4f);
            listaTiempoMatrizProductosInventario.add(5.6f);
        }
        
        if(idProducto == 6){
            listaTiempoMatrizProductosInventario.add(5.8f);
            listaTiempoMatrizProductosInventario.add(7.4f);
            listaTiempoMatrizProductosInventario.add(12.3f);
        }
        
        
        return listaTiempoMatrizProductosInventario;
    }
    
     @Override
    public double obtenerCostoProducto(Integer idProducto){
        Producto producto = this.obtenerProductoPorId(idProducto);
        
        return producto.getPrecio();
    }
    
    @Override
    public boolean eliminarProductoInventario(Integer idProducto, Integer cantidad){
        
        boolean productoEliminado =  false;
        
        if(validarProducto(idProducto)){
            
            int disponibilidadActual = cosultarInventarioProducto(idProducto);
            
            if(disponibilidadActual >= cantidad){
                
                int nuevaCantidad = disponibilidadActual - cantidad;
                
                int indiceIdProducto = listaCodigosProductos.indexOf(idProducto);
                
                listaCantidadProductosInventario.set(indiceIdProducto, nuevaCantidad);

                List<Boolean> productosApartados = listasProductosApartados.get(indiceIdProducto);
                
                for(Boolean productoApartado: productosApartados){
                    
                    if(productoApartado != null && !productoApartado){
                        
                        productosApartados.set(productosApartados.indexOf(productoApartado), null);
                        
                        listasProductosApartados.set(indiceIdProducto, productosApartados);
                        
                        productoEliminado =  true;
                        
                        break;
                        
                    }
                }
                
            }  
        }
        
        return productoEliminado;
        
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
                                new ProductoInventario(2, 1, 4, 3f, 0)));
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
                                new ProductoInventario(7, 6, 2, 7.4f, 20),
                                new ProductoInventario(8, 6, 3, 15f, 2),
                                new ProductoInventario(9, 6, 4, 5.8f, 0)));
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
                                new ProductoInventario(16, 2, 2, 5.5f, 1),
                                new ProductoInventario(17, 2, 3, 4.5f, 3),
                                new ProductoInventario(18, 2, 4, 2.3f, 12)));
                break;
        }
        
        return producto;
    }
    
    @Override
    public Double obtenerPesoProductoInventario(Integer idProductoInventario){
        if(idProductoInventario == 18){
            return 2.1;
        } else if(idProductoInventario == 17){
            return 3d;
        } else if(idProductoInventario == 16){
           return 2.78;
        } else if(idProductoInventario == 17){
           return 4.3;
        } else if(idProductoInventario == 17){
           return 1.5;
        } else if(idProductoInventario == 17){
           return 2.34;
        }
        return null;
    }
    
    public Double obtenerTiempoMatrzProductoInventario(Integer idProductoInventario){
        if(idProductoInventario == 2.3){
            return 2.1;
        } else if(idProductoInventario == 4.5){
            return 3d;
        } else if(idProductoInventario == 5.5){
           return 2.78;
        } else if(idProductoInventario == 2.1){
           return 4.3;
        } else if(idProductoInventario == 7.8){
           return 1.5;
        } else if(idProductoInventario == 5.023){
           return 2.34;
        }
        return null;
    }

    @Override
    public boolean validarProductoInventario(Integer idProductoInventario) {
        
        return (idProductoInventario == 1 || idProductoInventario == 2 || idProductoInventario == 6
                || idProductoInventario == 7 || idProductoInventario == 8 ||
                 idProductoInventario == 9 || idProductoInventario == 16 | idProductoInventario == 17 | idProductoInventario == 18);
    }

}
