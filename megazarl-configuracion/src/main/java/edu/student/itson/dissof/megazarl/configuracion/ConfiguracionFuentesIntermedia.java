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
    public FuenteDatos gerenteVentas;
    public FuenteDatos productoCarrito;
    public FuenteDatos coordinadorLogistica;
    public FuenteDatos auxiliarVentas;
    public FuenteDatos productoPedido;

    public void todos(FuenteDatos fuente) {
        cliente = fuente;
        paqueteria = fuente;
        producto = fuente;
        productoInventario = fuente;
        carritoCompras = fuente;
        pedido = fuente;
        sucursal = fuente;
        proveedor = fuente;
        direccion = fuente;
        gerenteVentas = fuente;
        productoCarrito = fuente;
        coordinadorLogistica = fuente;
        auxiliarVentas = fuente;
        productoPedido = fuente;
    }
}
