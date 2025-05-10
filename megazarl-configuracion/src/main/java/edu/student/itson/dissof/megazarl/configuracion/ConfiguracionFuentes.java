package edu.student.itson.dissof.megazarl.configuracion;

public record ConfiguracionFuentes(
        FuenteDatos cliente,
        FuenteDatos paqueteria,
        FuenteDatos producto,
        FuenteDatos productoInventario,
        FuenteDatos carritoCompras,
        FuenteDatos pedido,
        FuenteDatos sucursal,
        FuenteDatos proveedor,
        FuenteDatos direccion
){}
