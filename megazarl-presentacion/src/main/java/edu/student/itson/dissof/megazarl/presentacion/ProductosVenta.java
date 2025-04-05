package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.presentacion.interfaces.IProductosVenta;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class ProductosVenta extends JFrame implements IProductosVenta, IVista {

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

    private final Color COLOR_FONDO_PANEL_SIN_PRODUCTOS = new Color(250, 255, 230);
    
    private final Color COLOR_BOTON_FONDO = new Color(255, 254, 246);
    private final Color COLOR_BOTON_FONDO_SELECCIONADO = new Color(211, 207, 174);
    private final Color COLOR_BOTON_FONDO_SOBRE = new Color(211, 207, 174);

    private final Color COLOR_PANEL_PRODUCTOS = new Color(227, 227, 227);
    
    private final Color COLOR_PANELES_FILTRO = new Color(243, 243, 243);

    private final int MARGEN_VERTICAL_COMPONENTES = 1;
    private final int MARGEN_HORIZONTAL_COMPONENTES = 1;

    public ProductosVenta(ControlCompra control, Integer idCliente) {
        setTitle("Semillas MEGAZARL - Productos en venta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        Image iconoPropio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logoApp.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        setIconImage(iconoPropio);
        this.control = control;
        this.idCliente = idCliente;
        this.initComponents();
    }

    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        
        encabezado = new Encabezado(control, idCliente, this);
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

            if(!informacionProductoInicio.isEmpty()){
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
        labelFiltrarVariedad.setFont(new Font("Segoe UI", Font.BOLD, 15));
        labelFiltrarVariedad.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel labelFiltrarProveedor = new JLabel("Selecciona un proveedor");
        labelFiltrarProveedor.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelFiltrarProveedor.setFont(new Font("Segoe UI", Font.BOLD, 15));

        this.variedadSeleccionada = "----------";
        this.proveedorSeleccionado = "----------";

        // Ciclo para obtener variedades de productos:
        List<String> listaVariedadesProductos = new LinkedList<>();
        listaVariedadesProductos.add("----------");

        // Ciclo para obener variedades de productos:
        List<String> listaProveedoresProductos = new LinkedList<>();
        listaProveedoresProductos.add("----------");

        for (Map<String, Object> informacionProducto : listaInformacionProductos) {
            
            String variedad = (String)informacionProducto.get("Variedad");
            if (!listaVariedadesProductos.contains(variedad)) {
                listaVariedadesProductos.add(variedad);
            }

            String proveedor = (String)informacionProducto.get("NombreProveedor");
            if (!listaProveedoresProductos.contains(proveedor)) {
                listaProveedoresProductos.add(proveedor);
            }
        }

        Object[] arregloVariedades = listaVariedadesProductos.toArray();
        Object[] arregloProveedores = listaProveedoresProductos.toArray();

        JComboBox comboBoxOpcionesFiltroVariedad = new JComboBox(arregloVariedades);
        comboBoxOpcionesFiltroVariedad.setFont(new Font("Segoe UI", Font.BOLD, 15));
        
        JComboBox comboBoxOpcionesFiltroProveedor = new JComboBox(arregloProveedores);
        comboBoxOpcionesFiltroProveedor.setFont(new Font("Segoe UI", Font.BOLD, 15));
        

        // Panel de filtro por variedad
        
        JPanel panelFiltroVariedad = new JPanel();
        panelFiltroVariedad.setLayout(new BoxLayout(panelFiltroVariedad, BoxLayout.Y_AXIS));
        panelFiltroVariedad.setOpaque(false);
        
        JPanel panelContenedorComboBoxVariedad = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelContenedorComboBoxVariedad.setOpaque(false);
        
        JPanel panelSeparadorFiltroVariedad = new JPanel();
        panelSeparadorFiltroVariedad.setOpaque(false);
        
        panelFiltroVariedad.add(panelSeparadorFiltroVariedad);
        panelFiltroVariedad.add(labelFiltrarVariedad);
        panelContenedorComboBoxVariedad.add(comboBoxOpcionesFiltroVariedad);
        panelFiltroVariedad.add(panelContenedorComboBoxVariedad);

        
        // Panel de filtro por proveedor
        JPanel panelFiltroProveedor = new JPanel();
        panelFiltroProveedor.setLayout(new BoxLayout(panelFiltroProveedor, BoxLayout.Y_AXIS));
        panelFiltroProveedor.setOpaque(false);
       
        JPanel panelContenedorComboBoxProveedor = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelContenedorComboBoxProveedor.setOpaque(false);
        
        JPanel panelSeparadorFiltroProveedor = new JPanel();
        panelSeparadorFiltroProveedor.setOpaque(false);
        
        panelFiltroProveedor.add(panelSeparadorFiltroProveedor);
        panelFiltroProveedor.add(labelFiltrarProveedor);
        panelContenedorComboBoxProveedor.add(comboBoxOpcionesFiltroProveedor);
        panelFiltroProveedor.add(panelContenedorComboBoxProveedor);

        panelFiltro.add(panelFiltroVariedad);
        panelFiltro.add(panelFiltroProveedor);
        panelFiltro.setBackground(COLOR_PANELES_FILTRO);

        comboBoxOpcionesFiltroVariedad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                variedadSeleccionada = (String) comboBoxOpcionesFiltroVariedad.getSelectedItem();

                if (variedadSeleccionada.equals("----------")
                        && variedadSeleccionada.equals("----------")) {

                    control.obtenerProductosBusqueda(encabezado.getTextoCampoBusqueda());

                } else if (!variedadSeleccionada.equals("----------")
                        && proveedorSeleccionado.equals("----------")) {

                    control.obtenerProductosBusqueda(encabezado.getTextoCampoBusqueda(), proveedorSeleccionado);
                    
                } else if (variedadSeleccionada.equals("----------")
                        && !proveedorSeleccionado.equals("----------")){
                    
                     control.obtenerProductosBusqueda(encabezado.getTextoCampoBusqueda(), variedadSeleccionada);
                    
                } else{
                    control.obtenerProductosBusqueda(encabezado.getTextoCampoBusqueda(), variedadSeleccionada,
                            variedadSeleccionada);
                }
            }
        });

        comboBoxOpcionesFiltroProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proveedorSeleccionado = (String) comboBoxOpcionesFiltroVariedad.getSelectedItem();

                if (variedadSeleccionada.equals("----------")
                        && proveedorSeleccionado.equals("----------")) {

                    control.obtenerProductosBusqueda(encabezado.getTextoCampoBusqueda());

                } else if (!variedadSeleccionada.equals("----------")
                        && proveedorSeleccionado.equals("----------")) {

                    control.obtenerProductosBusqueda(encabezado.getTextoCampoBusqueda(), proveedorSeleccionado);
                    
                } else if (variedadSeleccionada.equals("Selecciones una variedad")
                        && !proveedorSeleccionado.equals("Seleccione un proveedor")){
                    
                    control.obtenerProductosBusqueda(encabezado.getTextoCampoBusqueda(), proveedorSeleccionado,
                            variedadSeleccionada);
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
    public void mostrarAvisoSinProductosDisponibles(){
        panelGeneral.removeAll();
        JLabel etqSinProductosDisponibles = new JLabel("No hay existencias de productos en este momento");
        etqSinProductosDisponibles.setFont(new Font("Segoe UI", Font.BOLD, 24));
        
        JPanel panelSinProductosDisponibles = new JPanel();
        panelSinProductosDisponibles.setBackground(COLOR_FONDO_PANEL_SIN_PRODUCTOS);
        panelSinProductosDisponibles.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        panelSinProductosDisponibles.add(etqSinProductosDisponibles);
        etqSinProductosDisponibles.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        panelGeneral.add(panelSinProductosDisponibles, BorderLayout.CENTER);
        
        
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
    public void cerrar() {
        dispose();
    }
    
    @Override
    public void actualizarDatosEncabezado() {
        encabezado.mostrarBarraBusqueda();
        encabezado.mostrarDireccionCliente();
        encabezado.mostrarNombreApellidoCliente();
        encabezado.mostrarBtnNumeroCarritoCompras(); 
    }
}
