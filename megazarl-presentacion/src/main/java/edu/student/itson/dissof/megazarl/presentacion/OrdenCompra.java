package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IOrdenCompra;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
/**
 * OrdenCompra.java
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * 
 */
public class OrdenCompra extends JFrame implements IOrdenCompra, IVista{
    
    private final ControlOrdenCompra control;
    private IdEntidadGenericoNegocios idGerenteVentas;
    
    private Map<String, EstadoProductoSeleccionado> estadoProductosSeleccionados = new HashMap<>();
    
    private EncabezadoOrdenCompra encabezado;
    
    private JPanel panelGeneral;
    private JPanel panelProveedores;
    private JPanel panelProductosOfrecidos;
    private JPanel panelSucursales;
    
    private final Font FUENTE_ENCABEZADO_PANELES = new Font("Segoe UI", Font.BOLD, 20);
    private final Font FUENTE_PANEL_INFORMACION_PROVEEDORES = new Font("Segoe UI", Font.BOLD, 14);
    private final Font FUENTE_AVISO_SIN_PRODUCTOS_OFRECIDOS = new Font("Segoe UI", Font.BOLD, 24);
    private final Font FUENTE_AVISO_SIN_PROVEEDORES_DISPONIBLES = new Font("Segoe UI", Font.BOLD, 24);
    private final Font FUENTE_AVISO_SIN_SUCURSALES_DISPONIBLES = new Font("Segoe UI", Font.BOLD, 24);
    private final Font FUENTE_PANEL_INFORMACION_PRODUCTOS_NOMBRE = new Font("Segoe UI", Font.BOLD, 14);
    private final Font FUENTE_PANEL_INFORMACION_PRODUCTOS_PROVEEDOR = new Font("Segoe UI", Font.PLAIN, 14);
    private final Font FUENTE_PANEL_INFORMACION_PRODUCTOS_VARIEDAD = new Font("Segoe UI", Font.BOLD, 14);
    private final Font FUENTE_PANEL_INFORMACION_PRODUCTOS_SELECCIONAR = new Font("Segoe UI", Font.BOLD, 14);
    private final Font FUENTE_PANEL_INFORMACION_PRODUCTOS_CANTIDAD= new Font("Segoe UI", Font.BOLD, 14);
    
    private final int MARGEN_REDONDEO_PANEL = 10;
    
    private final int MARGEN_HORIZONTAL_FILA_SUPERIOR = 1160;
    private final int MARGEN_VERTICAL_FILA_SUPERIOR = 600;
    private final int MARGEN_HORIZONTAL_PANEL_PROVEEDORES = 560;
    private final int MARGEN_VERTICAL_PANEL_PROVEEDORES = 600;
    private final int MARGEN_HORIZONTAL_PANEL_PRODUCTOS_OFRECIDOS = 560;
    private final int MARGEN_VERTICAL_PANEL_PRODUCTOS_OFRECIDOS = 600;
    private final int MARGEN_HORIZONTAL_PANEL_SUCURSAL_ENVIO = 1160;
    private final int MARGEN_VERTICA_PANEL_SUCURSAL_ENVIO = 185;
    
    private final Color COLOR_FONDO = new Color(88, 69, 50);
    private final Color COLOR_FONDO_PANEL_PROVEEDORES = new Color(245, 240, 215);
    private final Color COLOR_FONDO_PANEL_PRODUCTOS_OFRECIDOS = new Color(245, 240, 215);
    private final Color COLOR_FONDO_PANEL_SUCURSALES_ENVIO = new Color(245, 240, 215);
    private final Color COLOR_FONDO_PANEL_INFORMACION_PROVEEDOR = new Color(217, 217, 217);
    private final Color COLOR_FONDO_PANEL_SIN_PRODUCTOS_OFRECIDOS = new Color(250, 255, 230);
    private final Color COLOR_FONDO_PANEL_SIN_SUCURSALES_DISPONIBLES = new Color(250, 255, 230);
    private final Color COLOR_FONDO_PANEL_SIN_PROVEEDORES_DISPONIBLES = new Color(250, 255, 230);
    
    /**
     * 
     * @param control
     * @param idGerenteVentas 
     */
    public OrdenCompra(ControlOrdenCompra control, IdEntidadGenericoNegocios idGerenteVentas){
        this.setTitle("Semillas MEGAZARL - Realizar Orden de Compra");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 1000);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        Image iconoPropio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconoApp.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        this.setIconImage(iconoPropio);
        this.control = control;
        this.idGerenteVentas = idGerenteVentas;
        this.initComponents();
    }
    
    /**
     * 
     */
    private void initComponents() { 
        this.setLayout(new BorderLayout());

        encabezado = new EncabezadoOrdenCompra(control, idGerenteVentas, this);
        this.add(encabezado, BorderLayout.NORTH);

        panelGeneral = new JPanel();
        panelGeneral.setBackground(COLOR_FONDO);
        panelGeneral.setLayout(new BoxLayout(panelGeneral, BoxLayout.Y_AXIS));
        panelGeneral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Fila superior: incluye Panel Izquierdo (Proveedores) y Panel Derecho (Productos ofrecios)
        JPanel filaSuperior = new JPanel();
        filaSuperior.setLayout(new BoxLayout(filaSuperior, BoxLayout.X_AXIS));
        filaSuperior.setOpaque(false);
        filaSuperior.setMaximumSize(new Dimension(MARGEN_HORIZONTAL_FILA_SUPERIOR, MARGEN_VERTICAL_FILA_SUPERIOR));
        filaSuperior.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Panel izquierdo: Proveedores
        panelProveedores = new PanelRedondeado(MARGEN_REDONDEO_PANEL, COLOR_FONDO_PANEL_PROVEEDORES);
        panelProveedores.setLayout(new BoxLayout(panelProveedores, BoxLayout.Y_AXIS));
        panelProveedores.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelProveedores.setPreferredSize(new Dimension(MARGEN_HORIZONTAL_PANEL_PROVEEDORES, MARGEN_VERTICAL_PANEL_PROVEEDORES));
        panelProveedores.setMaximumSize(new Dimension(MARGEN_HORIZONTAL_PANEL_PROVEEDORES, MARGEN_VERTICAL_PANEL_PROVEEDORES));
        panelProveedores.setMinimumSize(new Dimension(MARGEN_HORIZONTAL_PANEL_PROVEEDORES, MARGEN_VERTICAL_PANEL_PROVEEDORES));

        // Panel derecho: Productos ofrecidos
        panelProductosOfrecidos = new PanelRedondeado(MARGEN_REDONDEO_PANEL, COLOR_FONDO_PANEL_PRODUCTOS_OFRECIDOS);
        panelProductosOfrecidos.setLayout(new BoxLayout(panelProductosOfrecidos, BoxLayout.Y_AXIS));
        panelProductosOfrecidos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelProductosOfrecidos.setPreferredSize(new Dimension(MARGEN_HORIZONTAL_PANEL_PRODUCTOS_OFRECIDOS, MARGEN_VERTICAL_PANEL_PRODUCTOS_OFRECIDOS));
        panelProductosOfrecidos.setMaximumSize(new Dimension(MARGEN_HORIZONTAL_PANEL_PRODUCTOS_OFRECIDOS, MARGEN_VERTICAL_PANEL_PRODUCTOS_OFRECIDOS));
        panelProductosOfrecidos.setMinimumSize(new Dimension(MARGEN_HORIZONTAL_PANEL_PRODUCTOS_OFRECIDOS, MARGEN_VERTICAL_PANEL_PRODUCTOS_OFRECIDOS));

        filaSuperior.add(panelProveedores);
        filaSuperior.add(Box.createHorizontalStrut(20));
        filaSuperior.add(panelProductosOfrecidos);

        // Panel inferior: Incluye las sucursales de envío registradas
        panelSucursales = new PanelRedondeado(MARGEN_REDONDEO_PANEL, COLOR_FONDO_PANEL_SUCURSALES_ENVIO);
        panelSucursales.setLayout(new BoxLayout(panelSucursales, BoxLayout.Y_AXIS));
        panelSucursales.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelSucursales.setPreferredSize(new Dimension(MARGEN_HORIZONTAL_PANEL_SUCURSAL_ENVIO, MARGEN_VERTICA_PANEL_SUCURSAL_ENVIO));
        panelSucursales.setMaximumSize(new Dimension(MARGEN_HORIZONTAL_PANEL_SUCURSAL_ENVIO, MARGEN_VERTICA_PANEL_SUCURSAL_ENVIO));
        panelSucursales.setMinimumSize(new Dimension(MARGEN_HORIZONTAL_PANEL_SUCURSAL_ENVIO, MARGEN_VERTICA_PANEL_SUCURSAL_ENVIO));
        panelSucursales.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Agregar componentes al panel general
        panelGeneral.add(filaSuperior);
        panelGeneral.add(Box.createVerticalStrut(20));
        panelGeneral.add(panelSucursales);
        
        this.add(panelGeneral, BorderLayout.CENTER);
    }
    
    /**
     * 
     * @param visible 
     */
    @Override
    public void hacerVisible(boolean visible) {
        setVisible(visible);
    }

    /**
     * 
     */
    @Override
    public void cerrar() {
        dispose();
    }
    
    /**
     * 
     * @param listaInformacionProveedores 
     */
    @Override
    public void setProveedoresTodos(List<Map<String, Object>> listaInformacionProveedores) {
        panelProveedores.removeAll();

        JPanel contenedorEncabezado = new JPanel();
        contenedorEncabezado.setLayout(new BorderLayout());
        contenedorEncabezado.setOpaque(false);
        contenedorEncabezado.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JPanel encabezadoProveedores = crearEncabezadoSeccion(
            "Proveedores disponibles", 
            COLOR_FONDO, 
            panelProveedores.getPreferredSize().width
        );
        encabezadoProveedores.setAlignmentX(Component.LEFT_ALIGNMENT);

        contenedorEncabezado.add(encabezadoProveedores, BorderLayout.CENTER);

        // Agregar encabezado antes del scroll
        panelProveedores.removeAll(); 
        panelProveedores.add(contenedorEncabezado);

        // Panel que contendrá todos los proveedores
        JPanel panelListaProveedores = new JPanel();
        panelListaProveedores.setLayout(new BoxLayout(panelListaProveedores, BoxLayout.Y_AXIS));
        panelListaProveedores.setOpaque(false); 
        ButtonGroup grupoProveedores = new ButtonGroup();

        for (Map<String, Object> informacionProveedor : listaInformacionProveedores) {
            String nombreProveedor = (String) informacionProveedor.get("Nombre");
            
            JPanel panelProveedor = new JPanel();
            panelProveedor.setLayout(new BoxLayout(panelProveedor, BoxLayout.X_AXIS));
            panelProveedor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panelProveedor.setBackground(COLOR_FONDO_PANEL_INFORMACION_PROVEEDOR);
            panelProveedor.setMaximumSize(new Dimension(panelProveedores.getPreferredSize().width - 40, 100));
            panelProveedor.setAlignmentX(Component.LEFT_ALIGNMENT);

            // Radio Button
            JRadioButton radioButton = new JRadioButton();
            radioButton.setAlignmentY(Component.TOP_ALIGNMENT);
            radioButton.setOpaque(false);
            grupoProveedores.add(radioButton);
            
            radioButton.addActionListener(e -> {
                filtrarProductosPorProveedor(nombreProveedor);
            });
           
            // Imagen de cada proveedor
            String rutaImagen = (String) informacionProveedor.get("DireccionImagenProveedor");
            JLabel labelImagen = new JLabel();
            labelImagen.setPreferredSize(new Dimension(50, 50)); 

            try {
                File archivo = new File(rutaImagen);
                if (archivo.exists()) {
                    ImageIcon iconoOriginal = new ImageIcon(rutaImagen);
                    Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(120, 60, Image.SCALE_SMOOTH);
                    labelImagen.setIcon(new ImageIcon(imagenEscalada));
                } else {
                    labelImagen.setText("Imagen no encontrada");
                }
            } catch (Exception ex) {
                labelImagen.setText("Error al cargar imagen");
            }

            // Panel con los datos del proveedor
            JPanel panelDatos = new JPanel();
            panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
            panelDatos.setOpaque(false);
            panelDatos.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

            JLabel lblNombre = new JLabel("Nombre: " + informacionProveedor.get("Nombre"));
            JLabel lblTelefono = new JLabel("Teléfono: " + informacionProveedor.get("Telefono"));
            JLabel lblCorreo = new JLabel("Correo: " + informacionProveedor.get("Correo"));

            lblNombre.setFont(FUENTE_PANEL_INFORMACION_PROVEEDORES);
            lblTelefono.setFont(FUENTE_PANEL_INFORMACION_PROVEEDORES);
            lblCorreo.setFont(FUENTE_PANEL_INFORMACION_PROVEEDORES);

            panelDatos.add(lblNombre);
            panelDatos.add(lblTelefono);
            panelDatos.add(lblCorreo);

            // Agregar componentes al panel de cada proveedor
            panelProveedor.add(radioButton);
            panelProveedor.add(Box.createHorizontalStrut(10));
            panelProveedor.add(labelImagen);
            panelProveedor.add(Box.createHorizontalStrut(10));
            panelProveedor.add(panelDatos);

            panelListaProveedores.add(panelProveedor);
            panelListaProveedores.add(Box.createVerticalStrut(10));
        }

        // ScrollPane que contiene la lista de proveedores
        JScrollPane scroll = new JScrollPane(panelListaProveedores, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(panelProveedores.getPreferredSize());
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        panelProveedores.add(scroll);
        panelProveedores.revalidate();
        panelProveedores.repaint();
    }

    /**
     * 
     * @param listaInformacionSucursales 
     */
    @Override
    public void setSucursalesEnvio(List<Map<String, Object>> listaInformacionSucursales) {
        panelSucursales.removeAll();

        JPanel contenedorEncabezado = new JPanel();
        contenedorEncabezado.setLayout(new BorderLayout());
        contenedorEncabezado.setOpaque(false);
        contenedorEncabezado.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // separación inferior

        JPanel encabezadoSucursales = crearEncabezadoSeccion(
            "Sucursales de envío", 
            COLOR_FONDO, 
            panelSucursales.getPreferredSize().width
        );
        encabezadoSucursales.setAlignmentX(Component.LEFT_ALIGNMENT);

        contenedorEncabezado.add(encabezadoSucursales, BorderLayout.CENTER);

        panelSucursales.removeAll();
        panelSucursales.add(contenedorEncabezado);

        JPanel panelListaSucursales = new JPanel();
        panelListaSucursales.setLayout(new BoxLayout(panelListaSucursales, BoxLayout.Y_AXIS));
        panelListaSucursales.setOpaque(false);
        ButtonGroup grupoSucursales = new ButtonGroup();

        for (Map<String, Object> informacionSucursal : listaInformacionSucursales) {
            JPanel panelSucursal = new JPanel();
            panelSucursal.setLayout(new BoxLayout(panelSucursal, BoxLayout.X_AXIS));
            panelSucursal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panelSucursal.setBackground(COLOR_FONDO_PANEL_INFORMACION_PROVEEDOR); // Usa el mismo color o define uno para sucursales
            panelSucursal.setMaximumSize(new Dimension(panelSucursales.getPreferredSize().width - 40, 100));
            panelSucursal.setAlignmentX(Component.LEFT_ALIGNMENT);

            // Radio Button
            JRadioButton radioButton = new JRadioButton();
            radioButton.setAlignmentY(Component.TOP_ALIGNMENT);
            radioButton.setOpaque(false);
            grupoSucursales.add(radioButton);

            // Panel con los datos de la sucursal
            JPanel panelDatos = new JPanel();
            panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.X_AXIS));
            panelDatos.setOpaque(false);
            panelDatos.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

            JLabel lblCalle = new JLabel("C. " + informacionSucursal.get("Calle") + " ");
            JLabel lblNumero = new JLabel("#" + informacionSucursal.get("Numero") + " ");
            JLabel lblCodigoPostal = new JLabel("C.P. " + informacionSucursal.get("CodigoPostal"));
            JLabel lblCiudad = new JLabel(", " + informacionSucursal.get("Ciudad"));
            JLabel lblEstado = new JLabel(", " + informacionSucursal.get("Estado"));
            JLabel lblEsMatriz = new JLabel(". " + informacionSucursal.get("EsMatriz"));

            lblCalle.setFont(FUENTE_PANEL_INFORMACION_PROVEEDORES);
            lblNumero.setFont(FUENTE_PANEL_INFORMACION_PROVEEDORES);
            lblCodigoPostal.setFont(FUENTE_PANEL_INFORMACION_PROVEEDORES);
            lblCiudad.setFont(FUENTE_PANEL_INFORMACION_PROVEEDORES);
            lblEstado.setFont(FUENTE_PANEL_INFORMACION_PROVEEDORES);
            lblEsMatriz.setFont(FUENTE_PANEL_INFORMACION_PROVEEDORES);
            
            panelDatos.add(lblCalle);
            panelDatos.add(lblNumero);
            panelDatos.add(lblCodigoPostal);
            panelDatos.add(lblCiudad);
            panelDatos.add(lblEstado);
            panelDatos.add(lblEsMatriz);

            // Agregar componentes al panel de cada sucursal
            panelSucursal.add(radioButton);
            panelSucursal.add(Box.createHorizontalStrut(10));
            panelSucursal.add(Box.createHorizontalStrut(10));
            panelSucursal.add(panelDatos);

            panelListaSucursales.add(panelSucursal);
            panelListaSucursales.add(Box.createVerticalStrut(10));
        }

        JScrollPane scroll = new JScrollPane(panelListaSucursales, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(panelSucursales.getPreferredSize());
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        panelSucursales.add(scroll);
        panelSucursales.revalidate();
        panelSucursales.repaint();
    }

    /**
     * 
     * @param listaInformacionProductosOfrecidos 
     */
    @Override
    public void setProductosOfrecidosBusqueda(List<Map<String, Object>> listaInformacionProductosOfrecidos) {
        for (Component componente : panelProductosOfrecidos.getComponents()) {
            if (componente instanceof JScrollPane scroll) {
                JViewport viewport = scroll.getViewport();
                Component contenido = viewport.getView();
                if (contenido instanceof JPanel panelLista) {
                    for (Component panelProductoComp : panelLista.getComponents()) {
                        if (panelProductoComp instanceof JPanel panelProducto) {
                            JPanel panelCentro = (JPanel) panelProducto.getComponent(1);
                            JLabel lblNombre = (JLabel) panelCentro.getComponent(0);
                            JLabel lblVariedad = (JLabel) panelCentro.getComponent(1); // Nuevo
                            JLabel lblProveedor = (JLabel) panelCentro.getComponent(2); // Nuevo
                            JCheckBox check = (JCheckBox) panelCentro.getComponent(4);

                            JPanel panelCantidad = (JPanel) panelProducto.getComponent(3);
                            JLabel lblCantidad = (JLabel) panelCantidad.getComponent(1);

                            // 🔁 CAMBIO 2: clave compuesta única
                            String nombre = lblNombre.getText().trim();
                            String variedad = lblVariedad.getText().trim();
                            String proveedor = lblProveedor.getText().replace("Proveedor: ", "").trim();
                            String clave = generarClaveProducto(nombre, variedad, proveedor);

                            boolean seleccionado = check.isSelected();
                            int cantidad = Integer.parseInt(lblCantidad.getText());

                            estadoProductosSeleccionados.put(clave, new EstadoProductoSeleccionado(seleccionado, cantidad));
                        }
                    }
                }
            }
        }
        
        panelProductosOfrecidos.removeAll();

        JPanel contenedorEncabezado = new JPanel(new BorderLayout());
        contenedorEncabezado.setOpaque(false);
        contenedorEncabezado.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JPanel encabezadoProductos = crearEncabezadoSeccion(
            "Productos ofrecidos por proveedores",
            COLOR_FONDO,
            panelProductosOfrecidos.getPreferredSize().width
        );
        encabezadoProductos.setAlignmentX(Component.LEFT_ALIGNMENT);
        contenedorEncabezado.add(encabezadoProductos, BorderLayout.CENTER);

        panelProductosOfrecidos.add(contenedorEncabezado);

        JPanel panelListaProductos = new JPanel();
        panelListaProductos.setLayout(new BoxLayout(panelListaProductos, BoxLayout.Y_AXIS));
        panelListaProductos.setOpaque(false);

        for (Map<String, Object> producto : listaInformacionProductosOfrecidos) {
            JPanel panelProducto = new JPanel();
            panelProducto.setLayout(new BoxLayout(panelProducto, BoxLayout.X_AXIS));
            panelProducto.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panelProducto.setBackground(COLOR_FONDO_PANEL_INFORMACION_PROVEEDOR);
            panelProducto.setMaximumSize(new Dimension(panelProductosOfrecidos.getPreferredSize().width - 40, 140));
            panelProducto.setAlignmentX(Component.LEFT_ALIGNMENT);

            // Imagen del producto como JLabel
            String rutaImagenProducto = (String) producto.get("DireccionImagenProducto");
            JLabel lblImagen = new JLabel();
            lblImagen.setPreferredSize(new Dimension(120, 100));

            try {
                File archivo = new File(rutaImagenProducto);
                if (archivo.exists()) {
                    ImageIcon iconoOriginal = new ImageIcon(rutaImagenProducto);
                    Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    lblImagen.setIcon(new ImageIcon(imagenEscalada));
                } else {
                    lblImagen.setText("Sin imagen");
                    lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
                    lblImagen.setVerticalAlignment(SwingConstants.CENTER);
                }
            } catch (Exception ex) {
                lblImagen.setText("Error imagen");
                lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
                lblImagen.setVerticalAlignment(SwingConstants.CENTER);
            }

            // Panel con nombre y proveedor
            JPanel panelCentro = new JPanel();
            panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
            panelCentro.setOpaque(false);
            panelCentro.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

            JLabel lblNombre = new JLabel("" + producto.get("Nombre"));
            JLabel lblVariedad = new JLabel("" + producto.get("Variedad"));
            JLabel lblProveedor = new JLabel("Proveedor: " + producto.get("Proveedor"));
            lblNombre.setFont(FUENTE_PANEL_INFORMACION_PRODUCTOS_NOMBRE);
            lblVariedad.setFont(FUENTE_PANEL_INFORMACION_PRODUCTOS_VARIEDAD);
            lblProveedor.setFont(FUENTE_PANEL_INFORMACION_PRODUCTOS_PROVEEDOR);

            JCheckBox checkSeleccion = new JCheckBox("Seleccionar");
            checkSeleccion.setOpaque(false);
            checkSeleccion.setFont(FUENTE_PANEL_INFORMACION_PRODUCTOS_SELECCIONAR);

            panelCentro.add(lblNombre);
            panelCentro.add(lblVariedad);
            panelCentro.add(lblProveedor);
            panelCentro.add(Box.createVerticalStrut(5));
            panelCentro.add(checkSeleccion);

            // Panel cantidad con JLabel
            JPanel panelCantidad = new JPanel();
            panelCantidad.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            panelCantidad.setOpaque(false);

            JButton btnMenos = new JButton("-");
            JButton btnMas = new JButton("+");

            JLabel lblCantidad = new JLabel("0");
            lblCantidad.setFont(FUENTE_PANEL_INFORMACION_PRODUCTOS_CANTIDAD);
            lblCantidad.setPreferredSize(new Dimension(30, 25));
            lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);

            btnMenos.addActionListener(e -> {
                int cantidad = Integer.parseInt(lblCantidad.getText());
                if (cantidad > 0) {
                    lblCantidad.setText(String.valueOf(cantidad - 1));
                }
            });

            btnMas.addActionListener(e -> {
                int cantidad = Integer.parseInt(lblCantidad.getText());
                lblCantidad.setText(String.valueOf(cantidad + 1));
            });

            String nombre = (String) producto.get("Nombre");
            String variedad = (String) producto.get("Variedad");
            String proveedor = (String) producto.get("Proveedor");

            String clave = generarClaveProducto(nombre, variedad, proveedor);
            EstadoProductoSeleccionado estado = estadoProductosSeleccionados.get(clave);
            if (estado != null) {
                checkSeleccion.setSelected(estado.isSeleccionado());
                lblCantidad.setText(String.valueOf(estado.getCantidad()));
            }
            
            panelCantidad.add(btnMenos);
            panelCantidad.add(lblCantidad);
            panelCantidad.add(btnMas);

            // Agregar al panel del producto
            panelProducto.add(lblImagen);
            panelProducto.add(panelCentro);
            panelProducto.add(Box.createHorizontalGlue());
            panelProducto.add(panelCantidad);

            panelListaProductos.add(panelProducto);
            panelListaProductos.add(Box.createVerticalStrut(10));
        }

        JScrollPane scroll = new JScrollPane(panelListaProductos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(panelProductosOfrecidos.getPreferredSize());
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        panelProductosOfrecidos.add(scroll);
        panelProductosOfrecidos.revalidate();
        panelProductosOfrecidos.repaint();
    }

    /**
     * 
     */
    @Override
    public void mostrarAvisoSinProductosOfrecidosDisponibles() {
        panelGeneral.removeAll();
        JLabel etqSinProductosOfrecidosDisponibles = new JLabel("No hay existencias de productos ofrecidos en este momento");
        etqSinProductosOfrecidosDisponibles.setFont(FUENTE_AVISO_SIN_PRODUCTOS_OFRECIDOS);
        
        JPanel panelSinProductosOfrecidosDisponibles = new PanelRedondeado(10, COLOR_FONDO_PANEL_SIN_PRODUCTOS_OFRECIDOS);
        panelSinProductosOfrecidosDisponibles.setBackground(COLOR_FONDO_PANEL_SIN_PRODUCTOS_OFRECIDOS);
        panelSinProductosOfrecidosDisponibles.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        panelSinProductosOfrecidosDisponibles.add(etqSinProductosOfrecidosDisponibles);
        etqSinProductosOfrecidosDisponibles.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        panelGeneral.add(panelSinProductosOfrecidosDisponibles, BorderLayout.CENTER);
    }
    
    /**
     * 
     */
    @Override
    public void mostrarAvisoSinProveedoresDisponibles(){
        panelGeneral.removeAll();
        JLabel etqSinProveedoresDisponibles = new JLabel("No hay proveedores registrados en este momento");
        etqSinProveedoresDisponibles.setFont(FUENTE_AVISO_SIN_PROVEEDORES_DISPONIBLES);
        
        JPanel panelSinProveedoresDisponibles = new PanelRedondeado(10, COLOR_FONDO_PANEL_SIN_PROVEEDORES_DISPONIBLES);
        panelSinProveedoresDisponibles.setBackground(COLOR_FONDO_PANEL_SIN_PROVEEDORES_DISPONIBLES);
        panelSinProveedoresDisponibles.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        panelSinProveedoresDisponibles.add(etqSinProveedoresDisponibles);
        etqSinProveedoresDisponibles.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        panelGeneral.add(panelSinProveedoresDisponibles, BorderLayout.CENTER);
    }
    
    /**
     * 
     */
    @Override
    public void mostrarAvisoSinSucursalesDisponibles() {
        panelGeneral.removeAll();
        JLabel etqSinSucursalesDisponibles = new JLabel("No hay sucursales registradas en este momento");
        etqSinSucursalesDisponibles.setFont(FUENTE_AVISO_SIN_SUCURSALES_DISPONIBLES);
        
        JPanel panelSinSucursalesDisponibles = new PanelRedondeado(10, COLOR_FONDO_PANEL_SIN_SUCURSALES_DISPONIBLES);
        panelSinSucursalesDisponibles.setBackground(COLOR_FONDO_PANEL_SIN_SUCURSALES_DISPONIBLES);
        panelSinSucursalesDisponibles.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        panelSinSucursalesDisponibles.add(etqSinSucursalesDisponibles);
        etqSinSucursalesDisponibles.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        panelGeneral.add(panelSinSucursalesDisponibles, BorderLayout.CENTER);
    }

    /**
     * 
     */
    @Override
    public void actualizarDatosEncabezado() {
        encabezado.mostrarNombreApellidoGerenteVentas();
        encabezado.mostrarBarraBusqueda();
    }
    
    /**
     * 
     * @param titulo
     * @param colorLinea
     * @param width
     * @return 
     */
    private JPanel crearEncabezadoSeccion(String titulo, Color colorLinea, int width) {
        JPanel panelEncabezado = new JPanel();
        panelEncabezado.setLayout(new BoxLayout(panelEncabezado, BoxLayout.Y_AXIS));
        panelEncabezado.setOpaque(false);

        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(FUENTE_ENCABEZADO_PANELES);
        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JSeparator linea = new JSeparator(SwingConstants.HORIZONTAL);
        linea.setMaximumSize(new Dimension(width, 4));
        linea.setForeground(colorLinea);
        linea.setBackground(colorLinea);
        linea.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelEncabezado.add(labelTitulo);
        panelEncabezado.add(Box.createVerticalStrut(5));
        panelEncabezado.add(linea);

        return panelEncabezado;
    }

    private void filtrarProductosPorProveedor(String nombreProveedor) {
        List<Map<String, Object>> productosFiltrados = control.obtenerProductosOfrecidosPorProveedor(nombreProveedor);

        setProductosOfrecidosBusqueda(productosFiltrados);
    }
    
    private String generarClaveProducto(String nombre, String variedad, String proveedor) {
        return nombre.trim() + "|" + variedad.trim() + "|" + proveedor.trim();
    }
    
    private static class EstadoProductoSeleccionado {
        private boolean seleccionado;
        private int cantidad;

        public EstadoProductoSeleccionado(boolean seleccionado, int cantidad) {
            this.seleccionado = seleccionado;
            this.cantidad = cantidad;
        }

        public boolean isSeleccionado() {
            return seleccionado;
        }

        public void setSeleccionado(boolean seleccionado) {
            this.seleccionado = seleccionado;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }
    }
    
    /**
     * 
     */
    class PanelRedondeado extends JPanel {
        private int radioEsquinas;
        private Color colorFondo;

        public PanelRedondeado(int radio, Color bgColor) {
            radioEsquinas = radio;
            colorFondo = bgColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(colorFondo);
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radioEsquinas, radioEsquinas);

            g2d.dispose();
        }
    }
    
}