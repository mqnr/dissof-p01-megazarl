package edu.student.itson.dissof.megazarl.configuracion;

public class ConfiguracionFuentesIntermedia {
    public FuenteDatos cliente;
    public FuenteDatos paqueteria;
    public FuenteDatos producto;
    public FuenteDatos productoInventario;
    public FuenteDatos carritoCompras;
    public FuenteDatos pedido;
    public FuenteDatos sucursal;
    public FuenteDatos proveedor;
    public FuenteDatos direccion;

    public void todos(FuenteDatos fuente) {
        cliente = fuente;
        paqueteria = fuente;
        producto = fuente;
        productoInventario = fuente;
        pedido = fuente;
        sucursal = fuente;
        proveedor = fuente;
        direccion = fuente;
    }
}
