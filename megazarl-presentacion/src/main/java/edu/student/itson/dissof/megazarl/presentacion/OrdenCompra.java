package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.presentacion.interfaces.IOrdenCompra;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
/**
 * OrdenCompra.java
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * 
 */
public class OrdenCompra extends JFrame implements IOrdenCompra, IVista{ //TODO implements IVista
    
    private final ControlOrdenCompra control;
    private Long idGerenteVentas;
    
    private EncabezadoOrdenCompra encabezado;
    
    private JPanel panelGeneral;
    private JPanel panelProveedores;
    private JPanel panelProductosOfrecidos;
    
    private final Font FUENTE_ENCABEZADO_PANELES = new Font("Segoe UI", Font.BOLD, 20);
    
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
    
    public OrdenCompra(ControlOrdenCompra control, Long idGerenteVentas){
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
        filaSuperior.setMaximumSize(new Dimension(MARGEN_HORIZONTAL_FILA_SUPERIOR, MARGEN_VERTICAL_FILA_SUPERIOR)); // Tamaño total que tendrá la fila superior
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
        filaSuperior.add(Box.createHorizontalStrut(20)); // Espacio asignado entre las columnas
        filaSuperior.add(panelProductosOfrecidos);

        // Panel inferior: Incluye las sucursales de envío registradas
        JPanel panelInferior = new PanelRedondeado(MARGEN_REDONDEO_PANEL, COLOR_FONDO_PANEL_SUCURSALES_ENVIO);
        panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.Y_AXIS));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelInferior.setPreferredSize(new Dimension(MARGEN_HORIZONTAL_PANEL_SUCURSAL_ENVIO, MARGEN_VERTICA_PANEL_SUCURSAL_ENVIO));
        panelInferior.setMaximumSize(new Dimension(MARGEN_HORIZONTAL_PANEL_SUCURSAL_ENVIO, MARGEN_VERTICA_PANEL_SUCURSAL_ENVIO));
        panelInferior.setMinimumSize(new Dimension(MARGEN_HORIZONTAL_PANEL_SUCURSAL_ENVIO, MARGEN_VERTICA_PANEL_SUCURSAL_ENVIO));
        panelInferior.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Agregar componentes al panel general
        panelGeneral.add(filaSuperior);
        panelGeneral.add(Box.createVerticalStrut(20));
        panelGeneral.add(panelInferior);

        // 
        panelProveedores.add(
            crearEncabezadoSeccion("Proveedores disponibles", COLOR_FONDO, panelProveedores.getPreferredSize().width)
        );
        
        //
        panelProductosOfrecidos.add(
            crearEncabezadoSeccion("Productos ofrecidos", COLOR_FONDO, panelProductosOfrecidos.getPreferredSize().width)
        );

        //
        panelInferior.add(
            crearEncabezadoSeccion("Sucursal de envío", COLOR_FONDO, panelInferior.getPreferredSize().width)
        );
        
        this.add(panelGeneral, BorderLayout.CENTER);
        
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
    public void setProveedoresTodos(List<Map<String, Object>> listaInformacionProveedores) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setSucursalesEnvio(List<Map<String, Object>> listaInformacionSucursales) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setProductosOfrecidosBusqueda(List<Map<String, Object>> listaInformacionProductosOfrecidos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrarAvisoSinProductosOfrecidosDisponibles() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarDatosEncabezado() {
        encabezado.mostrarNombreApellidoGerenteVentas();
        encabezado.mostrarBarraBusqueda();
        //encabezado.ocultarBtnSalir();
    }
    
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