package edu.student.itson.dissof.administradorproveedores;

import edu.student.itson.dissof.administradorproveedores.excepciones.ProveedoresIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProveedorON;

import java.util.List;

class AdministradorProveedores implements IAdministradorProveedores {
    private final List<ProveedorON> listaProveedores;

    public AdministradorProveedores(List<ProveedorON> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }

    @Override
    public boolean validarProveedor(Integer idProveedor){
        for (ProveedorON proveedor: listaProveedores) {
            if (proveedor.getId().equals(idProveedor)) {
                return true;
            }
        }

        return false;
    }

    public ProveedorON obtenerProveedor(Integer idProveedor){
        ProveedorON proveedorRecuperado = null;
        for (ProveedorON proveedor: listaProveedores) {
            if (proveedor.getId().equals(idProveedor)) {
                proveedorRecuperado = proveedor;
            }
        }
        return proveedorRecuperado;
    }

    @Override
    public String obtenerDireccionImagenProveedor(Integer idProveedor) throws ProveedoresIdProveedorInvalidoException{
        // Se valida el ID del proevedor.
        if (!validarProveedor(idProveedor)) {
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor: " + idProveedor + " es inválido.");
        }

        ProveedorON proveedor = obtenerProveedor(idProveedor);

        if (proveedor == null) {
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor: " + idProveedor + " es inválido.");
        }

        // Se obtiene y devuelve la imagen del proveedor.
        return proveedor.getDireccionImagenProveedor();
    }

    @Override
    public String obtenerNombreProveedor(Integer idProveedor) throws ProveedoresIdProveedorInvalidoException{
        // Se valida el ID del proevedor.
        if (!validarProveedor(idProveedor)) {
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor: " + idProveedor + " es inválido.");
        }

        ProveedorON proveedor = obtenerProveedor(idProveedor);

        if (proveedor == null) {
            throw new ProveedoresIdProveedorInvalidoException("El ID de proveedor: " + idProveedor + " es inválido.");
        }

        // Se obtiene y devuelve el nombre del proveedor.
        return proveedor.getNombre();
    }
}
