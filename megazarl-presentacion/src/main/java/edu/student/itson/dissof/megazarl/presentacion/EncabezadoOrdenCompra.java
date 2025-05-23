package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import edu.student.itson.dissof.megazarl.presentacion.utils.ButtonBuilder;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * EncabezadoOrdenCompra.java
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * 
 */
public class EncabezadoOrdenCompra extends JPanel {
    
    private IVista vistaPadre;
    private JPanel panelFila1;
    private JPanel panelFila2;
    private JPanel panelImagenNombreGerenteVentas;
    private JPanel panelLogotipo;
    private JPanel panelBusqueda;
    private JPanel panelSalir;
    
    private JPanel panelImagenNombreGerenteVentas2;
    private JPanel panelLogotipo2;
    private JPanel panelBusqueda2;
    private JPanel panelSalir2;
    
    private JLabel etqNombreGerenteVentas;
    private JLabel etqImagenGerenteVentas;

    private JLabel etqLogotipoEmpresa;
    
    private JTextField campoBusquedaProductosOfrecidos;
    
    private Color COLOR_FONDO = new Color(78, 122, 64);
    
    private Color COLOR_BOTONES = new Color(255, 247, 190);
    
    private final int ANCHO_LOGOTIPO_EMPRESA = 220;
    private final int ALTO_LOGOTIPO_EMPRESA = 100;
    
    private final int ANCHO_BOTON_BUSCAR = 70;
    private final int ALTO_BOTON_BUSCAR = 30;
    
    private final int ANCHO_BOTON_SALIR = 80;
    private final int ALTO_BOTON_SALIR = 30;
    
    private final int ANCHO_BOTON_REALIZAR_ORDEN = 230;
    private final int ALTO_BOTON_REALIZAR_ORDEN = 30;

    private final int MARGEN_VERTICAL_COMPONENTES = 40;
    
    private final Font FUENTE_BOTONES = new Font("Segoe UI Emoji", Font.BOLD, 16);
    
    private final String EMOJI_LUPA = new String(Character.toChars(0x1F50D));
    
    private IdEntidadGenericoNegocios idGerenteVentas;
    
    JButton botonBusqueda;
    JButton btnSalir;
    JButton btnRealizarOrdenCompra;
    
    private ControlOrdenCompra control;
    
    public EncabezadoOrdenCompra(ControlOrdenCompra control, IdEntidadGenericoNegocios idGerenteVentas, IVista vistaPadre) {
        this.control = control;
        this.idGerenteVentas = idGerenteVentas;
        this.vistaPadre = vistaPadre;
        this.initComponents();
    }
    
    private void initComponents(){
        this.setBackground(COLOR_FONDO);
        panelFila1 = new JPanel();
        panelFila2 = new JPanel();
        panelFila1.setLayout(new GridLayout(1, 2));
        panelFila2.setLayout(new GridLayout(1, 3));
        panelFila1.setOpaque(false);
        panelFila2.setOpaque(false);
        this.add(panelFila1);
        this.add(panelFila2);
        
        panelImagenNombreGerenteVentas = new JPanel();
        panelLogotipo = new JPanel();
        panelBusqueda = new JPanel();
        panelSalir = new JPanel();
        
        panelImagenNombreGerenteVentas.setOpaque(false);
        panelLogotipo.setOpaque(false);
        panelBusqueda.setOpaque(false);
        panelSalir.setOpaque(false);
        
        panelImagenNombreGerenteVentas.setLayout(new BoxLayout(panelImagenNombreGerenteVentas, BoxLayout.Y_AXIS));
        panelLogotipo.setLayout(new BoxLayout(panelLogotipo, BoxLayout.Y_AXIS));
        panelBusqueda.setLayout(new BoxLayout(panelBusqueda, BoxLayout.Y_AXIS));
        
        panelImagenNombreGerenteVentas.add(Box.createVerticalStrut(MARGEN_VERTICAL_COMPONENTES));
        
        panelFila1.add(panelImagenNombreGerenteVentas);
        panelFila1.add(panelLogotipo);
        panelFila2.add(panelBusqueda);
        panelFila2.add(panelSalir);
        
        panelImagenNombreGerenteVentas2= new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelLogotipo2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBusqueda2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSalir2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        panelImagenNombreGerenteVentas2.setOpaque(false);
        panelLogotipo2.setOpaque(false);
        panelBusqueda2.setOpaque(false);
        panelSalir2.setOpaque(false);
        
        panelImagenNombreGerenteVentas.add(panelImagenNombreGerenteVentas2);
        panelLogotipo.add(panelLogotipo2);
        panelBusqueda.add(panelBusqueda2);
        panelSalir.add(panelSalir2);
        
        // Imagen e icono de usuario
        ImageIcon iconoUsuario = new ImageIcon(this.getClass().getResource("/usuarioIcono.png"));
        Image imagenUsuario = iconoUsuario.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

        ImageIcon nuevoInconoUsuario = new ImageIcon(imagenUsuario);

        etqImagenGerenteVentas = new JLabel(nuevoInconoUsuario);
        etqNombreGerenteVentas = new JLabel();
        Font fuenteEtqNombreUsuario = new Font("Arial", Font.BOLD, 20);
        etqNombreGerenteVentas.setFont(fuenteEtqNombreUsuario);
        etqNombreGerenteVentas.setForeground(Color.WHITE);

        panelImagenNombreGerenteVentas2.add(etqImagenGerenteVentas);
        panelImagenNombreGerenteVentas2.add(etqNombreGerenteVentas);
        
        // Logotipo de empresa
        ImageIcon iconoLogoEmpresa = new ImageIcon(this.getClass().getResource("/logotipoEmpresa.png"));
        Image imagenLogoEmpresa = iconoLogoEmpresa.getImage().getScaledInstance(ANCHO_LOGOTIPO_EMPRESA, ALTO_LOGOTIPO_EMPRESA, Image.SCALE_SMOOTH);
        ImageIcon nuevoIconoLogoEmpresa = new ImageIcon(imagenLogoEmpresa);

        etqLogotipoEmpresa = new JLabel(nuevoIconoLogoEmpresa);

        panelLogotipo2.add(etqLogotipoEmpresa);
        
        // Campo de texto para búsqueda:
        campoBusquedaProductosOfrecidos = new JTextField();

        Font fuenteCampoBusqueda = new Font("Arial", Font.PLAIN, 20);

        campoBusquedaProductosOfrecidos.setFont(fuenteCampoBusqueda);

        campoBusquedaProductosOfrecidos.setColumns(16);
        
        panelBusqueda2.add(campoBusquedaProductosOfrecidos);
        
        panelBusqueda2.add(cargarBtnBuscar());
        
        // Botones "Realizar orden" y "Salir"
        panelSalir2.add(cargarBtnRealizarOrden());
        panelSalir2.add(Box.createHorizontalStrut(10)); // Espaciado entre botones
        panelSalir2.add(cargarBtnSalir());

    }
    
    private JButton cargarBtnSalir(){
        btnSalir = new ButtonBuilder()
                .withText("Salir")
                .withFont(FUENTE_BOTONES)
                .withBackground(COLOR_BOTONES)
                .withPreferredSize(ANCHO_BOTON_SALIR, ALTO_BOTON_SALIR)
                .onClick(e -> control.finalizarCasoUso(vistaPadre))
                .build();
        return btnSalir;
    }
    
    private JButton cargarBtnRealizarOrden(){
        btnRealizarOrdenCompra = new ButtonBuilder()
                .withText("Realizar orden de compra")
                .withFont(FUENTE_BOTONES)
                .withBackground(COLOR_BOTONES)
                .withPreferredSize(ANCHO_BOTON_REALIZAR_ORDEN, ALTO_BOTON_REALIZAR_ORDEN)
                .onClick(e -> control.realizarOrdenCompra())
                .build();
        return btnRealizarOrdenCompra;
    }
    
    private JButton cargarBtnBuscar(){
        botonBusqueda = new ButtonBuilder()
                .withText("Buscar " + EMOJI_LUPA)
                .withFont(new Font("Segoe UI Emoji", Font.PLAIN, 12))
                .withBackground(COLOR_BOTONES)
                .withPreferredSize(ANCHO_BOTON_BUSCAR, ALTO_BOTON_BUSCAR)
                .withEmptyMargin()
                .onClick(e -> control.mostrarProductosOfrecidosBusquedaNombre(campoBusquedaProductosOfrecidos.getText()))
                .build();
        return botonBusqueda;
    }
    
    public void mostrarBarraBusqueda(){
        panelBusqueda2.setVisible(true);
        botonBusqueda.setVisible(true);
        campoBusquedaProductosOfrecidos.setText("");
    }
        
    public void ocultarBarraBusqueda(){
        panelBusqueda2.setVisible(false);
        botonBusqueda.setVisible(false);
    }
    
    public String getTextoCampoBusqueda() {
        return this.campoBusquedaProductosOfrecidos.getText();
    }
    
    public void mostrarNombreApellidoGerenteVentas() {
        String[] datosApellidoNombreGerenteVentas = this.control.obtenerNombreApellidoGerenteVentas(this.idGerenteVentas);
        String nombreApellidoGerenteVentas = datosApellidoNombreGerenteVentas[0] + " " + datosApellidoNombreGerenteVentas[1];
        this.etqNombreGerenteVentas.setText(nombreApellidoGerenteVentas);
    }
    
    public void ocultarBtnSalir() {
        btnSalir.setVisible(false);
    }
    
}