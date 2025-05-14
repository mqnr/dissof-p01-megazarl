
package edu.student.itson.dissof.megazarl.presentacion;

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


public class EncabezadoRegistroCliente extends JPanel{
    
    private IVista vistaPadre;
    private JPanel panelFila1;
    private JPanel panelFila2;
    private JPanel panelImagenNombreUsuario;
    private JPanel panelLogotipo;
    private JPanel panelSalir;

    private JPanel panelImagenNombreUsuario2;
    private JPanel panelLogotipo2;
    private JPanel panelSalir2;

    private JLabel etqNombreUsuario;
    private JLabel etqImagenUsuario;

    private JLabel etqLogotipoEmpresa;
      
    private Color COLOR_FONDO = new Color(78, 122, 64);
    
    private Color COLOR_BOTONES = new Color(255, 247, 190);
    
    private final int ANCHO_LOGOTIPO_EMPRESA = 220;
    private final int ALTO_LOGOTIPO_EMPRESA = 100;
    
    private final int ANCHO_BOTON_CARRITO = 100;
    private final int ALTO_BOTON_CARRITO = 40;
    
    private final int ANCHO_BOTON_ACTUALIZAR_DIRECCION = 170;
    private final int ALTO_BOTON_ACTUALIZAR_DIRECCION = 40;
    
    private final int ANCHO_BOTON_BUSCAR = 70;
    private final int ALTO_BOTON_BUSCAR = 30;
    
    private final int ANCHO_BOTON_SALIR = 80;
    private final int ALTO_BOTON_SALIR = 30;

    private final int MARGEN_VERTICAL_COMPONENTES = 40;
    
    private final Font FUENTE_BOTONES = new Font("Segoe UI Emoji", Font.BOLD, 16);
    
    private Long idUsuario;
    
    JButton btnSalir;

    private ControlRegistroCliente control;

    public EncabezadoRegistroCliente(
            ControlRegistroCliente control,
            Long idUsuario, 
            IVista vistaPadre) {
        
        this.control = control;
        this.idUsuario = idUsuario;
        this.vistaPadre = vistaPadre;
        this.initCompoents();
    }

    private void initCompoents() {
        this.setBackground(COLOR_FONDO);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        panelFila1 = new JPanel();
        panelFila2 = new JPanel();
        panelFila1.setLayout(new GridLayout(1, 3));
        panelFila2.setLayout(new GridLayout(1, 3));
        panelFila1.setOpaque(false);
        panelFila2.setOpaque(false);
        this.add(panelFila1);
        this.add(panelFila2);

        panelImagenNombreUsuario = new JPanel();
        panelLogotipo = new JPanel();
        panelSalir = new JPanel();

        panelImagenNombreUsuario.setOpaque(false);
        panelLogotipo.setOpaque(false);
        panelSalir.setOpaque(false);

        panelImagenNombreUsuario.setLayout(new BoxLayout(panelImagenNombreUsuario, BoxLayout.Y_AXIS));
        panelLogotipo.setLayout(new BoxLayout(panelLogotipo, BoxLayout.Y_AXIS));

        panelImagenNombreUsuario.add(Box.createVerticalStrut(MARGEN_VERTICAL_COMPONENTES));

        panelFila1.add(panelImagenNombreUsuario);
        panelFila1.add(panelLogotipo);
        panelFila2.add(panelSalir);

        panelImagenNombreUsuario2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelLogotipo2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSalir2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panelImagenNombreUsuario2.setOpaque(false);
        panelLogotipo2.setOpaque(false);;
        panelSalir2.setOpaque(false);

        panelImagenNombreUsuario.add(panelImagenNombreUsuario2);
        panelLogotipo.add(panelLogotipo2);
        panelSalir.add(panelSalir2);
        
        // Imagen e icono de usuario
        ImageIcon iconoUsuario = new ImageIcon(this.getClass().getResource("/usuarioIcono.png"));
        Image imagenUsuario = iconoUsuario.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

        ImageIcon nuevoInconoUsuario = new ImageIcon(imagenUsuario);

        etqImagenUsuario = new JLabel(nuevoInconoUsuario);
        etqNombreUsuario = new JLabel();
        Font fuenteEtqNombreUsuario = new Font("Arial", Font.BOLD, 20);
        etqNombreUsuario.setFont(fuenteEtqNombreUsuario);
        etqNombreUsuario.setForeground(Color.WHITE);

        panelImagenNombreUsuario2.add(etqImagenUsuario);
        panelImagenNombreUsuario2.add(etqNombreUsuario);

        // Logotipo de empresa
        ImageIcon iconoLogoEmpresa = new ImageIcon(this.getClass().getResource("/logotipoEmpresa.png"));
        Image imagenLogoEmpresa = iconoLogoEmpresa.getImage().getScaledInstance(ANCHO_LOGOTIPO_EMPRESA, ALTO_LOGOTIPO_EMPRESA, Image.SCALE_SMOOTH);
        ImageIcon nuevoIconoLogoEmpresa = new ImageIcon(imagenLogoEmpresa);

        etqLogotipoEmpresa = new JLabel(nuevoIconoLogoEmpresa);

        panelLogotipo2.add(etqLogotipoEmpresa);
        
        cargarBtnSalir();

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


    public void mostrarNombreApellidoAuxiliarVentas() {
        String[] datosApellidoNombreCliente = this.control.obtenerNombreApellidoAuxiliarVentas(this.idUsuario);
        String nombreApellidoCliente = datosApellidoNombreCliente[0] + " " + datosApellidoNombreCliente[1];
        this.etqNombreUsuario.setText(nombreApellidoCliente);
    }
    
    public void ocultarBtnSalir() {
        btnSalir.setVisible(false);
    }
}
