package edu.student.itson.dissof.megazarl.administradorproductos;

import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosProductoSinInventarioException;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoInicioDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoVentaDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoInventarioON;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoON;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

class AdministradorProductos implements IAdministradorProductos {
    private final List<ProductoON> listaProductos;

    public AdministradorProductos(List<ProductoON> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @Override
    public int cosultarInventarioProducto(Integer idProducto) throws ProductosIdProductoInvalidoException{
        // Se valida el ID del producto.
        if (!validarProducto(idProducto)) {
            throw new ProductosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }

        ProductoON producto = obtenerProducto(idProducto);

        if (producto == null){
            throw new ProductosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }

        // Se obtiene la cantidad de objetos de tipo ProductoInventario de la lista
        // del objeto Producto con el ID del parámetro que no están apartados.
        int disponibilidadProducto
                = (int) producto.getListaProductoInventario().stream().filter(productoInventario -> !productoInventario.getApartado()).count();

        return disponibilidadProducto;
    }

    @Override
    public void apartarProductoInventario(Integer idProducto, int cantidad)
            throws ProductosIdProductoInvalidoException, ProductosProductoSinInventarioException{

        // Se valida el ID del producto.
        if(!validarProducto(idProducto)){
            throw new ProductosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }

        ProductoON producto = obtenerProducto(idProducto);

        if(producto == null){
            throw new ProductosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }

        if(cosultarInventarioProducto(idProducto) - cantidad < 0){
            throw new ProductosProductoSinInventarioException("El producto con ID: " + idProducto + " no cuenta con inventario suficiente.");
        }

        List<ProductoInventarioON> productosInventarioProductoApartar = producto.getListaProductoInventario();

        productosInventarioProductoApartar.sort(
                Comparator.comparing((ProductoInventarioON productoOrdenar) -> productoOrdenar.getSucursal().getTiempoMatriz())
        );

        int cantidadProductosApartados = 0;

        for(ProductoInventarioON productoInventario: productosInventarioProductoApartar){
            if(!productoInventario.getApartado()){
                productoInventario.setApartado(true);
                cantidadProductosApartados++;
            }
            if(cantidadProductosApartados >= cantidad){
                break;
            }
        }
    }

    @Override
    public void desapartarProductoInventario(Integer idProducto, int cantidad)
            throws ProductosIdProductoInvalidoException, ProductosProductoSinInventarioException{

        // Se valida el ID del producto.
        if (!validarProducto(idProducto)) {
            throw new ProductosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }

        ProductoON producto = obtenerProducto(idProducto);

        if (producto == null) {
            throw new ProductosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }

        List<ProductoInventarioON> productosInventarioProductoApartar = producto.getListaProductoInventario();

        productosInventarioProductoApartar.sort(
                Comparator.comparing((ProductoInventarioON productoOrdenar) -> productoOrdenar.getSucursal().getTiempoMatriz()).reversed()
        );

        int cantidadProductosDesapartados = 0;

        for (ProductoInventarioON productoInventario: productosInventarioProductoApartar) {
            if (productoInventario.getApartado()) {
                productoInventario.setApartado(false);
                cantidadProductosDesapartados++;
            }
            if (cantidadProductosDesapartados >= cantidad) {
                break;
            }
        }

    }

    @Override
    public boolean validarProducto(Integer idProducto){
        for (ProductoON producto: listaProductos) {
            if (producto.getId().equals(idProducto)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<InformacionProductoInicioDTO> obtenerProductosVenta() {
        List<InformacionProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        // Se recorre la lista de Productos y se añade la información a la lista
        // de DTOs, de aquellos que tengan existencias.
        for (ProductoON producto: listaProductos) {

            Integer idProducto = producto.getId();
            int cantidadProducto = 0;

            try {
                cantidadProducto = cosultarInventarioProducto(idProducto);
            } catch (ProductosIdProductoInvalidoException ex) {}

            if(cantidadProducto > 0){
                listaProductoInicioDTO.add(new InformacionProductoInicioDTO(
                        producto.getId(),
                        producto.getNombre(),
                        producto.getVariedad(),
                        producto.getPrecio(),
                        producto.getMilesSemillas(),
                        producto.getDireccionImagenProducto(),
                        producto.getProveedor().getId())

                );
            }
        }

        return listaProductoInicioDTO;
    }

    @Override
    public List<InformacionProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto){

        List<InformacionProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        // Se convierte el nombre recibido a letras minusculas.
        String nombreProductoMinusculas = nombreProducto.toLowerCase();

        // Se recorre la lista de productos para almacenar los datos de aquellos
        // que tengan existencias y su nombre este contenido dentro del nombre del parámetro.
        for(ProductoON producto: listaProductos){
            int idProducto = producto.getId();

            int cantidadProducto = 0;
            try {
                cantidadProducto = cosultarInventarioProducto(idProducto);
            } catch (ProductosIdProductoInvalidoException ex) {}

            if(nombreProductoMinusculas.contains(producto.getNombre().toLowerCase()) && cantidadProducto > 0){
                listaProductoInicioDTO.add(new InformacionProductoInicioDTO(
                        producto.getId(),
                        producto.getNombre(),
                        producto.getVariedad(),
                        producto.getPrecio(),
                        producto.getMilesSemillas(),
                        producto.getDireccionImagenProducto(),
                        producto.getProveedor().getId())
                );
            }
        }

        return listaProductoInicioDTO;
    }

    @Override
    public List<InformacionProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto, String variedadProducto){
        // El nombre y la variedada del parámetro se conviertene a letras minúsculas.
        String nombreProductoMinusculas = nombreProducto.toLowerCase();
        String variedadProductoMinusculas = variedadProducto.toLowerCase();

        List<InformacionProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        // Se recorre la lista de productos para almacenar los datos de aquellos
        // que tengan existencias, cuyo su nombre este contenido dentro del nombre del parámetro,
        // y cuya variedad esté contenida detro de la variedad del parámetro.
        for(ProductoON producto: listaProductos){
            int idProducto = producto.getId();

            int cantidadProducto = 0;
            try {
                cantidadProducto = cosultarInventarioProducto(idProducto);
            } catch (ProductosIdProductoInvalidoException ex) {}

            if(cantidadProducto > 0 && nombreProductoMinusculas.contains(producto.getNombre().toLowerCase())
                    && variedadProductoMinusculas.contains(producto.getVariedad().toLowerCase())){
                listaProductoInicioDTO.add(new InformacionProductoInicioDTO(
                        producto.getId(),
                        producto.getNombre(),
                        producto.getVariedad(),
                        producto.getPrecio(),
                        producto.getMilesSemillas(),
                        producto.getDireccionImagenProducto(),
                        producto.getProveedor().getId())
                );
            }
        }

        return listaProductoInicioDTO;
    }

    @Override
    public List<InformacionProductoInicioDTO> obtenerProductosBusqueda(String nombreProducto, String variedadProducto, String nombreProveedor){
        // El nombre, variedad y nombre de proveedor del producto son convertidos a cadenas
        // de letras minúsculas.
        String nombreProductoMinusculas = nombreProducto.toLowerCase();
        String variedadProductoMinusculas = variedadProducto.toLowerCase();
        String nombreProveedorMinusculas = nombreProveedor.toLowerCase();

        List<InformacionProductoInicioDTO> listaProductoInicioDTO = new LinkedList<>();

        // Se recorre la lista de productos para almacenar los datos de aquellos que tengan existencias,
        // cuyo su nombre este contenido dentro del nombre del parámetro, cuya variedad esté contenida detro
        // de la variedad del parámetro y cuyo nombre de proveedor esté contenido dentro del nombre de
        // proveedor del parámetro.
        for (ProductoON producto: listaProductos) {
            int idProducto = producto.getId();

            int cantidadProducto = 0;
            try {
                cantidadProducto = cosultarInventarioProducto(idProducto);
            } catch (ProductosIdProductoInvalidoException ex) {}

            if(cantidadProducto > 0 && nombreProductoMinusculas.contains(producto.getNombre().toLowerCase())
                    && variedadProductoMinusculas.contains(producto.getVariedad().toLowerCase())
                    && nombreProveedorMinusculas.contains(producto.getProveedor().getNombre().toLowerCase())){

                listaProductoInicioDTO.add(new InformacionProductoInicioDTO(
                        producto.getId(),
                        producto.getNombre(),
                        producto.getVariedad(),
                        producto.getPrecio(),
                        producto.getMilesSemillas(),
                        producto.getDireccionImagenProducto(),
                        producto.getProveedor().getId())
                );
            }
        }

        return listaProductoInicioDTO;
    }

    @Override
    public InformacionProductoVentaDTO obtenerInformacionProductoVenta(Integer idProducto)
            throws ProductosIdProductoInvalidoException{

        // Se valida el ID del Producto.
        if (!validarIdProductoInventario(idProducto)) {
            throw new ProductosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }

        ProductoON productoRecuperado = obtenerProducto(idProducto);

        if (productoRecuperado == null) {
            throw new ProductosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }

        // Se crea una DTO de tipo InformacionProductoVentaDTO, con la información del Producto con el ID
        // del parámetro.
        InformacionProductoVentaDTO informacionProductoDTO = new InformacionProductoVentaDTO(
                productoRecuperado.getId(),
                productoRecuperado.getNombre(),
                productoRecuperado.getVariedad(),
                productoRecuperado.getDescripcion(),
                productoRecuperado.getPrecio(),
                productoRecuperado.getMilesSemillas(),
                productoRecuperado.getDireccionImagenProducto(),
                productoRecuperado.getProveedor().getId());

        return informacionProductoDTO;

    }

    @Override
    public ProductoON obtenerProducto(Integer idProducto){
        ProductoON productoBuscar = null;

        for (ProductoON producto: listaProductos) {
            if (producto.getId().equals(idProducto)) {
                productoBuscar = producto;
            }
        }

        return productoBuscar;
    }

    @Override
    public boolean validarIdProductoInventario(Integer idProductoInventario) {
        // Se recorre la lista de productos.
        for (ProductoON producto: listaProductos) {
            // Se recorre la lista de productos en inventario de cada producto,
            // se verifica si su ID es igual al ID del parámetro.
            for (ProductoInventarioON productoInventario: producto.getListaProductoInventario()) {
                if (productoInventario.getId().equals(idProductoInventario)) {
                    return true;
                }
            }
        }

        return false;
    }
}
