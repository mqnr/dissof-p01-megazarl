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
import javax.swing.border.EmptyBorder;

public class ProductosVenta extends JFrame implements IProductosVenta, IVista {

    private final ControlCompra control;
    private Object idCliente;

    private EncabezadoCompra encabezado;

    private JPanel panelGeneral;
    private JPanel panelProductos;
    private JPanel panelFiltro;
    
    private JScrollPane scrollPaneProductos;

    private String nombreProductoBuscado;
    private String nombreVariedadSeleccionado;
    private String nombreProveedorSeleccionado;

    
    private final int MARGEN_VERTICAL_PRODUCTOS = 10;
    private final int MARGEN_HORIZONTAL_PRODUCTOS = 10;

    private final int ANCHO_IMAGEN_PROVEEDOR = 90;
    private final int ALTO_IMAGEN_PROVEEDOR = 40;

    private final int ALTO_BOTON_INFORMACION_PRODUCTO = 375;
    private final int ANCHO_BOTON_INFORMACION_PRODUCTO = 221;

    private final Color COLOR_FONDO_PANEL_SIN_PRODUCTOS = new Color(250, 255, 230);
    
    private final Color COLOR_BOTON_FONDO = new Color(245, 240, 215);
    private final Color COLOR_BOTON_FONDO_SELECCIONADO = new Color(219, 207, 174);
    private final Color COLOR_BOTON_FONDO_SOBRE = new Color(219, 207, 174);

    private final Color COLOR_PANEL_PRODUCTOS = new Color(88, 69, 50);
    
    private final Color COLOR_PANELES_FILTRO = new Color(243, 243, 243);
    
    private final Color COLOR_FONDO_SEPARADOR = new Color(113, 70, 46);
    
    private final Color COLOR_FONDO_FILTRO_PRODUCTOS = new Color(255, 249, 239);

    private final int MARGEN_VERTICAL_COMPONENTES = 0;
    private final int MARGEN_HORIZONTAL_COMPONENTES = 0;
    
    private final String INDICADOR_SIN_SELECCION_VARIEDAD = "Todas";
    private final String INDICADOR_SIN_SELECCION_PROVEEDOR = "Todos";
    
    private JComboBox comboBoxFiltroVariedad;
    private JComboBox comboBoxFiltroProveedor;

    public ProductosVenta(ControlCompra control, Object idCliente) {
        setTitle("Semillas MEGAZARL - Productos en venta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        Image iconoPropio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconoApp.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        setIconImage(iconoPropio);
        this.control = control;
        this.idCliente = idCliente;
        this.initComponents();
    }

    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1200, 800);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        
        encabezado = new EncabezadoCompra(control, idCliente, this);
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
                mostrarInformacionProducto(botonInformacionProducto.getIdProducto());
            }
        };

        panelProductos.setPreferredSize(new Dimension(this.getWidth(), Math.ceilDiv(listaInformacionProductos.size(), 5)
                * ALTO_BOTON_INFORMACION_PRODUCTO + MARGEN_VERTICAL_PRODUCTOS * (Math.ceilDiv(listaInformacionProductos.size(), 5) + 1)));

        for (Map<String, Object> informacionProductoInicio : listaInformacionProductos) {

            if(!informacionProductoInicio.isEmpty()){
                
                BotonInformacionProducto botonInformacionProducto = 
                        new BotonInformacionProducto(
                                ALTO_BOTON_INFORMACION_PRODUCTO,
                                ANCHO_BOTON_INFORMACION_PRODUCTO, 
                                COLOR_BOTON_FONDO, 
                                COLOR_BOTON_FONDO_SELECCIONADO,               
                                COLOR_BOTON_FONDO_SOBRE,
                                COLOR_FONDO_SEPARADOR,
                                MARGEN_VERTICAL_COMPONENTES,
                                MARGEN_HORIZONTAL_COMPONENTES, 
                                (Long) informacionProductoInicio.get("Id"),
                                (String) informacionProductoInicio.get("DireccionImagenProducto"),
                                (String) informacionProductoInicio.get("Nombre"), 
                                (String) informacionProductoInicio.get("Variedad"),
                                (Integer) informacionProductoInicio.get("MilesSemillas"), 
                                (Double) informacionProductoInicio.get("Precio"),
                                (String) informacionProductoInicio.get("DireccionImagenProveedor"),
                                ANCHO_IMAGEN_PROVEEDOR, 
                                ALTO_IMAGEN_PROVEEDOR);
                
               
                
                botonInformacionProducto.addActionListener(listenerBotonInformacionProducto);
                panelProductos.add(botonInformacionProducto);
            }
            

        }
    }

    @Override
    public void setProductosBusqueda(List<Map<String, Object>> listaInformacionProductos) {
        
        panelGeneral.remove(scrollPaneProductos);

        FlowLayout layoutPanelProductos = new FlowLayout(FlowLayout.LEFT);
        layoutPanelProductos.setVgap(MARGEN_VERTICAL_PRODUCTOS);
        layoutPanelProductos.setHgap(MARGEN_HORIZONTAL_PRODUCTOS);

        panelProductos = new JPanel(layoutPanelProductos);

        panelProductos.setBackground(COLOR_PANEL_PRODUCTOS);

        scrollPaneProductos = new JScrollPane(panelProductos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panelGeneral.add(scrollPaneProductos, BorderLayout.CENTER);
        
        if(!encabezado.getTextoCampoBusqueda().equals(nombreProductoBuscado)){

            nombreProductoBuscado = encabezado.getTextoCampoBusqueda();
            
            if(panelFiltro != null){
                panelGeneral.remove(panelFiltro);
            }
            
            panelFiltro = new JPanel();

            panelFiltro.setLayout(new BoxLayout(panelFiltro, BoxLayout.Y_AXIS));

            panelFiltro.setBorder(new EmptyBorder(100,30,100,30));
            
            panelFiltro.setBackground(COLOR_FONDO_FILTRO_PRODUCTOS);
        
            JLabel labelFiltrarVariedad = new JLabel("Selecciona una variedad");
            labelFiltrarVariedad.setFont(new Font("Segoe UI", Font.BOLD, 15));
            labelFiltrarVariedad.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel labelFiltrarProveedor = new JLabel("Selecciona un proveedor");
            labelFiltrarProveedor.setAlignmentX(Component.CENTER_ALIGNMENT);
            labelFiltrarProveedor.setFont(new Font("Segoe UI", Font.BOLD, 15));

            this.nombreVariedadSeleccionado = INDICADOR_SIN_SELECCION_VARIEDAD;
            this.nombreProveedorSeleccionado = INDICADOR_SIN_SELECCION_PROVEEDOR;

            // Ciclo para obtener variedades de productos:
            List<String> listaVariedadesProductos = new LinkedList<>();
            listaVariedadesProductos.add(INDICADOR_SIN_SELECCION_VARIEDAD);

            // Ciclo para obener variedades de productos:
            List<String> listaProveedoresProductos = new LinkedList<>();
            listaProveedoresProductos.add(INDICADOR_SIN_SELECCION_PROVEEDOR);
            
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

            comboBoxFiltroVariedad = new JComboBox(arregloVariedades);
            comboBoxFiltroVariedad.setFont(new Font("Segoe UI", Font.BOLD, 15));

            comboBoxFiltroProveedor = new JComboBox(arregloProveedores);
            comboBoxFiltroProveedor.setFont(new Font("Segoe UI", Font.BOLD, 15));


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
            panelContenedorComboBoxVariedad.add(comboBoxFiltroVariedad);
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
            panelContenedorComboBoxProveedor.add(comboBoxFiltroProveedor);
            panelFiltroProveedor.add(panelContenedorComboBoxProveedor);

            panelFiltro.add(panelFiltroVariedad);
            panelFiltro.add(panelFiltroProveedor);
            panelFiltro.setBackground(COLOR_PANELES_FILTRO);

            comboBoxFiltroVariedad.addActionListener(e -> buscarProductosFiltro(comboBoxFiltroVariedad));
            comboBoxFiltroProveedor.addActionListener(e -> buscarProductosFiltro(comboBoxFiltroProveedor));

            panelGeneral.add(panelFiltro, BorderLayout.WEST);
        }
        
        

        // Productos
        ActionListener listenerBotonInformacionProducto = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BotonInformacionProducto botonInformacionProducto = (BotonInformacionProducto) e.getSource();
                mostrarInformacionProducto(botonInformacionProducto.getIdProducto());
            }
        };

        panelProductos.setPreferredSize(new Dimension(
                this.getWidth() - ANCHO_BOTON_INFORMACION_PRODUCTO, 
                Math.ceilDiv(listaInformacionProductos.size(), 3)
                * ALTO_BOTON_INFORMACION_PRODUCTO + MARGEN_VERTICAL_PRODUCTOS 
                * (Math.ceilDiv(listaInformacionProductos.size(), 3) + 1)));

        for (Map<String, Object> informacionProductoInicio : listaInformacionProductos) {

            BotonInformacionProducto botonInformacionProducto = 
                    new BotonInformacionProducto(
                            ALTO_BOTON_INFORMACION_PRODUCTO,
                            ANCHO_BOTON_INFORMACION_PRODUCTO, 
                            COLOR_BOTON_FONDO, 
                            COLOR_BOTON_FONDO_SELECCIONADO,
                            COLOR_BOTON_FONDO_SOBRE,
                            COLOR_FONDO_SEPARADOR,
                            MARGEN_VERTICAL_COMPONENTES,
                            MARGEN_HORIZONTAL_COMPONENTES, 
                            (Long) informacionProductoInicio.get("Id"),
                            (String) informacionProductoInicio.get("DireccionImagenProducto"),
                            (String) informacionProductoInicio.get("Nombre"), 
                            (String) informacionProductoInicio.get("Variedad"),
                            (Integer) informacionProductoInicio.get("MilesSemillas"), 
                            (Double) informacionProductoInicio.get("Precio"),
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
    
    
    public void buscarProductosFiltro(JComboBox comboBoxFiltro){

        nombreVariedadSeleccionado = (String) comboBoxFiltroVariedad.getSelectedItem();
        nombreProveedorSeleccionado = (String) comboBoxFiltroProveedor.getSelectedItem();

        if (nombreVariedadSeleccionado.equals(INDICADOR_SIN_SELECCION_VARIEDAD)
                && nombreProveedorSeleccionado.equals(INDICADOR_SIN_SELECCION_PROVEEDOR)) {

            control.mostrarProductosBusquedaNombre(nombreProductoBuscado);

        } else if (!nombreVariedadSeleccionado.equals(INDICADOR_SIN_SELECCION_VARIEDAD)
                && nombreProveedorSeleccionado.equals(INDICADOR_SIN_SELECCION_PROVEEDOR)) {

            control.mostrarProductosBusquedaNombreVariedad(nombreProductoBuscado, nombreVariedadSeleccionado);

        } else if (nombreVariedadSeleccionado.equals(INDICADOR_SIN_SELECCION_VARIEDAD)
                && !nombreProveedorSeleccionado.equals(INDICADOR_SIN_SELECCION_PROVEEDOR)){

            control.mostrarProductosBusquedaNombreProveedor(nombreProductoBuscado, nombreProveedorSeleccionado);
            
        } else {
            
            if(!comboBoxFiltro.equals(comboBoxFiltroVariedad)){
                
                comboBoxFiltroVariedad.setSelectedItem(INDICADOR_SIN_SELECCION_VARIEDAD);
                control.mostrarProductosBusquedaNombreProveedor(nombreProductoBuscado, nombreProveedorSeleccionado);
                
            } else{
                comboBoxFiltroProveedor.setSelectedItem(INDICADOR_SIN_SELECCION_PROVEEDOR);
                control.mostrarProductosBusquedaNombreVariedad(nombreProductoBuscado, nombreVariedadSeleccionado);
            }
            
        }
    }

    @Override
    public void mostrarInformacionProducto(Long idProducto) {
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
