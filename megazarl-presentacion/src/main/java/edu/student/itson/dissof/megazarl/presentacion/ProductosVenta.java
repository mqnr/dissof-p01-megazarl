package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.presentacion.interfaces.IProductosVenta;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class ProductosVenta extends JFrame implements IProductosVenta {

    private final ControlCompra control;
    private Integer idCliente;

    private Encabezado encabezado;

    private JPanel panelGeneral;
    private JPanel panelProductos;

    private JScrollPane scrollPaneProductos;

    private String variedadSeleccionada;
    private String proveedorSeleccionado;

    private final int MARGEN_VERTICAL_PRODUCTOS = 10;
    private final int MARGEN_HORIZONTAL_PRODUCTOS = 10;

    private final int ANCHO_IMAGEN_PROVEEDOR = 90;
    private final int ALTO_IMAGEN_PROVEEDOR = 40;

    private final int ALTO_BOTON_INFORMACION_PRODUCTO = 300;
    private final int ANCHO_BOTON_INFORMACION_PRODUCTO = 221;

    private final Color COLOR_BOTON_FONDO = new Color(255, 254, 246);
    private final Color COLOR_BOTON_FONDO_SELECCIONADO = new Color(211, 207, 174);
    private final Color COLOR_BOTON_FONDO_SOBRE = new Color(211, 207, 174);

    private final Color COLOR_PANEL_PRODUCTOS = new Color(227, 227, 227);

    private final int MARGEN_VERTICAL_COMPONENTES = 1;
    private final int MARGEN_HORIZONTAL_COMPONENTES = 1;

    public ProductosVenta(ControlCompra control, Integer idCliente) {
        this.control = control;
        this.initComponents();
        this.idCliente = idCliente;
    }

    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        encabezado = new Encabezado(this.control);
        this.add(encabezado, BorderLayout.NORTH);

        panelGeneral = new JPanel(new BorderLayout());
        this.add(panelGeneral, BorderLayout.CENTER);

    }

    @Override
    public void setProductosTodos(List<Map<String, Object>> listaInformacionProductos) {
        panelGeneral.removeAll();
        FlowLayout layoutPanelProductos = new FlowLayout(FlowLayout.LEFT);
        layoutPanelProductos.setVgap(MARGEN_VERTICAL_PRODUCTOS);
        layoutPanelProductos.setHgap(MARGEN_HORIZONTAL_PRODUCTOS);

        panelProductos = new JPanel(layoutPanelProductos);

        panelProductos.setBackground(COLOR_PANEL_PRODUCTOS);

        scrollPaneProductos = new JScrollPane(panelProductos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panelGeneral.add(scrollPaneProductos, BorderLayout.CENTER);

        ActionListener listenerBotonInformacionProducto = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BotonInformacionProducto botonInformacionProducto = (BotonInformacionProducto) e.getSource();
                mostrarInformacionProducto(botonInformacionProducto.getId_Producto());
            }
        };

        panelProductos.setPreferredSize(new Dimension(this.getWidth(), Math.ceilDiv(listaInformacionProductos.size(), 5)
                * ALTO_BOTON_INFORMACION_PRODUCTO + MARGEN_VERTICAL_PRODUCTOS * (Math.ceilDiv(listaInformacionProductos.size(), 5) + 1)));

        for (Map<String, Object> informacionProductoInicio : listaInformacionProductos) {

            BotonInformacionProducto botonInformacionProducto = new BotonInformacionProducto(ALTO_BOTON_INFORMACION_PRODUCTO,
                    ANCHO_BOTON_INFORMACION_PRODUCTO, COLOR_BOTON_FONDO, COLOR_BOTON_FONDO_SELECCIONADO,
                    COLOR_BOTON_FONDO_SOBRE, MARGEN_VERTICAL_COMPONENTES,
                    MARGEN_HORIZONTAL_COMPONENTES, (Integer) informacionProductoInicio.get("Id"),
                    (String) informacionProductoInicio.get("DireccionImagenProducto"),
                    (String) informacionProductoInicio.get("Nombre"), (String) informacionProductoInicio.get("Variedad"),
                    (Integer) informacionProductoInicio.get("MilesSemillas"), (Double) informacionProductoInicio.get("Precio"),
                    (String) informacionProductoInicio.get("DireccionImagenProveedor"),
                    ANCHO_IMAGEN_PROVEEDOR, ALTO_IMAGEN_PROVEEDOR);

            botonInformacionProducto.addActionListener(listenerBotonInformacionProducto);
            panelProductos.add(botonInformacionProducto);
        }
    }

    @Override
    public void setProductosBusqueda(List<Map<String, Object>> listaInformacionProductos) {
        panelGeneral.removeAll();

        FlowLayout layoutPanelProductos = new FlowLayout(FlowLayout.LEFT);
        layoutPanelProductos.setVgap(MARGEN_VERTICAL_PRODUCTOS);
        layoutPanelProductos.setHgap(MARGEN_HORIZONTAL_PRODUCTOS);

        panelProductos = new JPanel(layoutPanelProductos);

        panelProductos.setBackground(COLOR_PANEL_PRODUCTOS);

        scrollPaneProductos = new JScrollPane(panelProductos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panelGeneral.add(scrollPaneProductos, BorderLayout.CENTER);

        JPanel panelFiltro = new JPanel();

        panelFiltro.setLayout(new BoxLayout(panelFiltro, BoxLayout.Y_AXIS));

        JLabel labelFiltrarVariedad = new JLabel("Selecciona una variedad");
        JLabel labelFiltrarProveedor = new JLabel("Selecciona un proveedor");

        this.variedadSeleccionada = "----------";
        this.proveedorSeleccionado = "----------";

        // Ciclo para obtener variedades de productos:
        List<String> listaVariedadesProductos = new LinkedList<>();
        listaVariedadesProductos.add("Seleccione una variedad");

        // Ciclo para obener variedades de productos:
        List<String> listaProveedoresProductos = new LinkedList<>();
        listaProveedoresProductos.add("Seleccione un proveedor");

        for (Map<String, Object> informacionProducto : listaInformacionProductos) {
            if (!listaVariedadesProductos.contains(informacionProducto.get("Variedad"))) {
                listaVariedadesProductos.add((String) informacionProducto.get("Variedad"));
            }

            if (!listaProveedoresProductos.contains(informacionProducto.get("Proveedor"))) {
                listaProveedoresProductos.add((String) informacionProducto.get("Proveedor"));
            }
        }

        Object[] arregloVariedades = listaVariedadesProductos.toArray();
        Object[] arregloProveedores = listaProveedoresProductos.toArray();

        JComboBox comboBoxOpcionesFiltroVariedad = new JComboBox(arregloVariedades);
        JComboBox comboBoxOpcionesFiltroProveedor = new JComboBox(arregloProveedores);

        JPanel panelFiltro1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltro1.add(labelFiltrarVariedad);
        panelFiltro1.add(comboBoxOpcionesFiltroVariedad);

        JPanel panelFiltro2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltro2.add(labelFiltrarProveedor);
        panelFiltro2.add(comboBoxOpcionesFiltroProveedor);

        panelFiltro.add(panelFiltro1);
        panelFiltro.add(panelFiltro2);

        String nombreProductoActual = encabezado.getTextoCampoBusqueda();

        comboBoxOpcionesFiltroVariedad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                variedadSeleccionada = (String) comboBoxOpcionesFiltroVariedad.getSelectedItem();

                if (variedadSeleccionada.equals("----------")
                        && proveedorSeleccionado.equals("----------")) {

                    control.obtenerProductosBusqueda(encabezado.getTextoCampoBusqueda());

                } else if (!variedadSeleccionada.equals("----------")
                        && proveedorSeleccionado.equals("----------")) {

                    control.obtenerProductosBusqueda(encabezado.getTextoCampoBusqueda(), variedadSeleccionada);
                } else {
                    control.obtenerProductosBusqueda(encabezado.getTextoCampoBusqueda(), variedadSeleccionada,
                            proveedorSeleccionado);
                }
            }
        });

        comboBoxOpcionesFiltroProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proveedorSeleccionado = (String) comboBoxOpcionesFiltroVariedad.getSelectedItem();

                if (variedadSeleccionada.equals("Selecciones una variedad")
                        && proveedorSeleccionado.equals("Seleccione un proveedor")) {

                    control.obtenerProductosBusqueda(encabezado.getTextoCampoBusqueda());

                } else if (!variedadSeleccionada.equals("Selecciones una variedad")
                        && proveedorSeleccionado.equals("Seleccione un proveedor")) {

                    control.obtenerProductosBusqueda(encabezado.getTextoCampoBusqueda(), variedadSeleccionada);
                } else {
                    control.obtenerProductosBusqueda(encabezado.getTextoCampoBusqueda(), variedadSeleccionada,
                            proveedorSeleccionado);
                }
            }
        });

        panelGeneral.add(panelFiltro, BorderLayout.WEST);

        // Productos
        ActionListener listenerBotonInformacionProducto = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BotonInformacionProducto botonInformacionProducto = (BotonInformacionProducto) e.getSource();
                mostrarInformacionProducto(botonInformacionProducto.getId_Producto());
            }
        };

        panelProductos.setPreferredSize(new Dimension(this.getWidth(), Math.ceilDiv(listaInformacionProductos.size(), 3)
                * ALTO_BOTON_INFORMACION_PRODUCTO + MARGEN_VERTICAL_PRODUCTOS * (Math.ceilDiv(listaInformacionProductos.size(), 3) + 1)));

        for (Map<String, Object> informacionProductoInicio : listaInformacionProductos) {

            BotonInformacionProducto botonInformacionProducto = new BotonInformacionProducto(ALTO_BOTON_INFORMACION_PRODUCTO,
                    ANCHO_BOTON_INFORMACION_PRODUCTO, COLOR_BOTON_FONDO, COLOR_BOTON_FONDO_SELECCIONADO,
                    COLOR_BOTON_FONDO_SOBRE, MARGEN_VERTICAL_COMPONENTES,
                    MARGEN_HORIZONTAL_COMPONENTES, (Integer) informacionProductoInicio.get("Id"),
                    (String) informacionProductoInicio.get("DireccionImagenProducto"),
                    (String) informacionProductoInicio.get("Nombre"), (String) informacionProductoInicio.get("Variedad"),
                    (Integer) informacionProductoInicio.get("MilesSemillas"), (Double) informacionProductoInicio.get("Precio"),
                    (String) informacionProductoInicio.get("DireccionImagenProveedor"),
                    ANCHO_IMAGEN_PROVEEDOR, ALTO_IMAGEN_PROVEEDOR);

            botonInformacionProducto.addActionListener(listenerBotonInformacionProducto);
            panelProductos.add(botonInformacionProducto);
        }
    }

    @Override
    public void mostrarInformacionProducto(Integer idProducto) {
        this.control.mostrarInformacionProducto(idProducto, this);
    }

    @Override
    public void hacerVisible(boolean visible) {
        setVisible(visible);
    }

    @Override
    public void actualizarBtnCarritoEncabezado() {
        encabezado.actualizarCantidadProductosBtnCarrito(String.valueOf(this.control.obtenerNumeroProductosCarrito(idCliente)));
    }

    @Override
    public void mostrarNombreApellidoClienteEncabezado() {
        String[] nombreApellidoCliente = this.control.obtenerNombreApellidoCliente(this.idCliente);

        encabezado.setNombreApellidoCliente(nombreApellidoCliente[0] + " " + nombreApellidoCliente[1]);
    }
}
