package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.presentacion.interfaces.IInformacionProducto;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import edu.student.itson.dissof.megazarl.presentacion.utils.ImagenesUtils;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

/**
 * Clase que permite visualizar la información detallada de un producto, así como
 * seleccionar una cantidad de unidades para añadirlas al carrito de compras.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 * 
 */
public class InformacionProducto extends JFrame implements IInformacionProducto, IVista {

    private ControlCompra control;
    private int cantidad;
    private JLabel cantidadLabel;
    private Object idCliente;
    private Object idProducto;

    private EncabezadoCompra encabezado;

    private JPanel panelGeneral;
    private JPanel panelProducto;
    private JPanel panelContador;
    private JPanel panelImagen;
    private JPanel panelProducto2;
    private JPanel panelDetalles;
    private JLabel avisoPedidoAnticipado1;
    private JLabel avisoCantidadPedidoAnticipado;
    private JLabel avisoPedidoAnticipado2;
    private JLabel avisoPedidoAnticipado3;

    private JButton botonAgregarCarrito;
    private JButton btnMenos;
    private JButton btnMas;

    private final int MARGEN_VERTICAL_PANELS = 250;
    
    private final int ANCHO_PANEL_IMAGEN_PRODUCTO = 450;
    private final int ALTO_PANEL_IMAGEN_PRODUCTO = 520;
    
    private final int ANCHO_IMAGEN_PRODUCTO = 440;
    private final int ALTO_IMAGEN_PRODUCTO = 400;
    
    private final int ANCHO_IMAGEN_PROVEEDOR = 200;
    private final int ALTO_IMAGEN_PROVEEDOR = 90;
   
    private final Font FUENTE_AVISO_PEDIDO_ANTICIPADO = new Font("Segoe UI", Font.BOLD, 15);
    private final Color COLOR_FONDO = new Color(88, 69, 50);
    private final Color COLOR_FONDO_INFORMACION_PRODUCTO = new Color(245, 240, 215);
    private final Color COLOR_FONDO_BOTONES = new Color(0, 204, 0);
    
    private final Color COLOR_AVISO_PEDIDO_ANTICIPADO = new Color(231, 42, 0);
    
    private final Color COLOR_FONDO_IMAGEN_PRODUCTO = new Color(229, 221, 204);

    public InformacionProducto(ControlCompra control, Object idCliente) {
        setTitle("Semillas MEGAZARL - Información de producto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        Image iconoPropio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconoApp.png")).getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        setIconImage(iconoPropio);
        
        this.idCliente = idCliente;
        this.control = control;
        initComponents();
    }

    private void initComponents() {
    
        encabezado = new EncabezadoCompra(control, idCliente, this);
        this.add(encabezado, BorderLayout.NORTH);

        panelGeneral = new JPanel();
        panelGeneral.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelGeneral.setBackground(COLOR_FONDO);
        
        this.add(panelGeneral, BorderLayout.CENTER);

        // Panel principal con borde redondeado
        panelProducto = new JPanel();
        panelProducto.setLayout(new BoxLayout(panelProducto, BoxLayout.Y_AXIS));
        panelProducto.setOpaque(false);
        
        panelGeneral.add(panelProducto);

        panelProducto2 = new PanelRedondeado(10, COLOR_FONDO_INFORMACION_PRODUCTO);
        panelProducto2.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel panelSeparador1 = new JPanel();
        JPanel panelSeparador2 = new JPanel();
        
        panelSeparador1.setOpaque(false);
        panelSeparador2.setOpaque(false);
        
        panelProducto.add(panelSeparador1);
        panelProducto.add(panelSeparador2);
        panelProducto.add(panelProducto2);

        panelImagen = new JPanel();
        panelImagen.setLayout(new BoxLayout(panelImagen, BoxLayout.Y_AXIS));
        panelImagen.setPreferredSize(new Dimension(ANCHO_PANEL_IMAGEN_PRODUCTO, ALTO_PANEL_IMAGEN_PRODUCTO));
        panelImagen.setBackground(COLOR_FONDO_IMAGEN_PRODUCTO);
        
        panelProducto2.add(panelImagen);

        panelDetalles = new JPanel();
        panelDetalles.setOpaque(false);
        panelDetalles.setLayout(new BoxLayout(panelDetalles, BoxLayout.Y_AXIS));
        panelProducto2.add(panelDetalles);

        cantidadLabel = new JLabel();
    }

    @Override
    public void setProducto(Map<String, Object> informacionProducto) {
        panelImagen.removeAll();
        panelDetalles.removeAll();
        cantidad = 0;
        cantidadLabel.setText("0");

        // Imagen principal
        ImageIcon iconoImagenProducto = ImagenesUtils.obtenerImagen((String) informacionProducto.get("DireccionImagenProducto"));
        
        ImageIcon nuevoIconoImagenProducto = ImagenesUtils.redimensionarImagen(
                iconoImagenProducto, 
                ANCHO_IMAGEN_PRODUCTO, 
                ALTO_IMAGEN_PRODUCTO);
        
        JLabel etqImagenProducto = new JLabel(nuevoIconoImagenProducto);

        JPanel panelImagenProducto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelImagenProducto.setOpaque(false);
        panelImagenProducto.add(etqImagenProducto);
        panelImagen.add(panelImagenProducto);

        // Imagen proveedor
        ImageIcon iconoImagenProveedor = ImagenesUtils.obtenerImagen((String) informacionProducto.get("DireccionImagenProveedor"));
        
        ImageIcon nuevoIconoImagenProveedor = ImagenesUtils.redimensionarImagen(
                iconoImagenProveedor, 
                ANCHO_IMAGEN_PROVEEDOR, 
                ALTO_IMAGEN_PROVEEDOR);
        
        JLabel etqImagenProveedor = new JLabel(nuevoIconoImagenProveedor);

        JPanel panelEtqImagenProveedor = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelEtqImagenProveedor.setOpaque(false);
        panelEtqImagenProveedor.add(etqImagenProveedor);
        panelImagen.add(panelEtqImagenProveedor);

        // Nombre y variedad
        
        JPanel panelNombreVariedad = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNombreVariedad.setOpaque(false);
         
        String nombreVariedadProducto = (String) informacionProducto.get("Nombre") + " " + informacionProducto.get("Variedad");
        
        JLabel etqNombreVariedadProducto = new JLabel(nombreVariedadProducto);
        Font fuenteEtqNombreVariedad = new Font("Segoe UI", Font.BOLD, 24);
        etqNombreVariedadProducto.setFont(fuenteEtqNombreVariedad);
        
        panelNombreVariedad.add(etqNombreVariedadProducto);
        
        panelDetalles.add(panelNombreVariedad);

        // Panel separador
        JPanel panelSeparador1 = new JPanel();
        panelSeparador1.setOpaque(false);
        panelDetalles.add(panelSeparador1);

        // Precio de producto.
        String cantidadSemillasProducto = "  (" + informacionProducto.get("MilesSemillas") + "k semillas)";

        String precioFormato = String.format("%,.2f", informacionProducto.get("Precio"));

        String partesPrecio[] = precioFormato.split("\\.");
        String parteEnteraPrecioProdcuto = partesPrecio[0];
        String parteDecimalPrecioProducto = partesPrecio[1];

        JLabel etqParteEnteraPrecioProducto = new JLabel("$" + parteEnteraPrecioProdcuto + ".");
        Font fuenteEtqParteEnteraPrecioProducto = new Font("Arial", Font.BOLD, 25);
        etqParteEnteraPrecioProducto.setFont(fuenteEtqParteEnteraPrecioProducto);

        JLabel etqParteDecimalPrecioProducto = new JLabel(parteDecimalPrecioProducto);
        Font fuenteEtqParteDecimalPrecioProducto = new Font("Arial", Font.BOLD, 20);
        etqParteDecimalPrecioProducto.setFont(fuenteEtqParteDecimalPrecioProducto);

        JLabel petqPrecioCantSemillas = new JLabel(cantidadSemillasProducto);
        Font fuenteEtqCantSemillas = new Font("Arial", Font.BOLD, 20);
        petqPrecioCantSemillas.setFont(fuenteEtqCantSemillas);

        JPanel panelPrecioCantSemillas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelPrecioCantSemillas.setOpaque(false);

        panelPrecioCantSemillas.add(etqParteEnteraPrecioProducto);
        panelPrecioCantSemillas.add(etqParteDecimalPrecioProducto);
        panelPrecioCantSemillas.add(petqPrecioCantSemillas);
        panelDetalles.add(panelPrecioCantSemillas);

        // Descripción
        String descripcionProducto = "<html>";

        String[] caracteresDescripcion = ((String) informacionProducto.get("Descripcion")).split("");

        Pattern patron = Pattern.compile("^[\\s]");
        Matcher matcher;

        int caracteresRenglon = 0;
        for (int i = 0; i < caracteresDescripcion.length; i++) {
            matcher = patron.matcher(caracteresDescripcion[i]);

            if (caracteresRenglon >= 48 && matcher.matches()) {
                descripcionProducto += "<br>";
                caracteresRenglon = 0;
            } else {
                descripcionProducto += caracteresDescripcion[i];
                caracteresRenglon++;
            }
        }
        descripcionProducto += "</html>";

        JLabel etqDescripcion = new JLabel(descripcionProducto);
        Font fuenteEtqDescripcion = new Font("Segoe UI", Font.PLAIN, 20);
        etqDescripcion.setFont(fuenteEtqDescripcion);

        JPanel panelDescripcion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelDescripcion.setOpaque(false);
        panelDescripcion.add(etqDescripcion);
        panelDetalles.add(panelDescripcion);

        // Nombre de Proveedor
        JLabel etqNombreProveedor = new JLabel("Proveedor: " + (String) informacionProducto.get("NombreProveedor"));
        Font fuenteEtqNombreProveedor = new Font("Segoe UI", Font.BOLD, 17);
        etqNombreProveedor.setFont(fuenteEtqNombreProveedor);

        JPanel panelProveedor = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelProveedor.setOpaque(false);
        panelProveedor.add(etqNombreProveedor);
        panelDetalles.add(panelProveedor);

        // Seleccion de cantidad
        JPanel panelSeleccionCantidad = new JPanel();
        panelSeleccionCantidad.setOpaque(false);
        panelSeleccionCantidad.setLayout(new BoxLayout(panelSeleccionCantidad, BoxLayout.Y_AXIS));
        panelDetalles.add(panelSeleccionCantidad);

        JPanel panelEtqSeleccionarCantidad = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEtqSeleccionarCantidad.setOpaque(false);

        JLabel etqSeleccionarCantidad = new JLabel("Seleccionar cantidad");
        Font fuenteEtqSeleccionarCantidad = new Font("Segoe UI", Font.BOLD, 19);
        etqSeleccionarCantidad.setFont(fuenteEtqSeleccionarCantidad);
        etqSeleccionarCantidad.setForeground(new Color(40, 101, 162));
        panelEtqSeleccionarCantidad.add(etqSeleccionarCantidad);

        panelSeleccionCantidad.add(panelEtqSeleccionarCantidad);

        JPanel panelSeleccionCantidad2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSeleccionCantidad2.setOpaque(false);

        panelContador = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelContador.setOpaque(false);

        btnMenos = new JButton("-");

        btnMenos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restarUnidad(informacionProducto); 
            }
        });

        
        btnMas = new JButton("+");
                
        btnMas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                sumarUnidad(informacionProducto);
                 
            }
        });

        panelContador.add(btnMenos);
        panelContador.add(cantidadLabel);
        panelContador.add(btnMas);

        panelSeleccionCantidad2.add(panelContador);
        
        avisoPedidoAnticipado1 = new JLabel();
        avisoPedidoAnticipado1.setFont(FUENTE_AVISO_PEDIDO_ANTICIPADO);
        avisoPedidoAnticipado1.setForeground(COLOR_AVISO_PEDIDO_ANTICIPADO);
        
        JPanel panelAvisoPedidoAnticipado1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelAvisoPedidoAnticipado1.setOpaque(false);
        
        panelAvisoPedidoAnticipado1.add(avisoPedidoAnticipado1);
        
        avisoCantidadPedidoAnticipado = new JLabel();
        avisoCantidadPedidoAnticipado.setFont(FUENTE_AVISO_PEDIDO_ANTICIPADO);
        avisoCantidadPedidoAnticipado.setForeground(COLOR_AVISO_PEDIDO_ANTICIPADO);
        
        panelAvisoPedidoAnticipado1.add(avisoCantidadPedidoAnticipado);
        
        avisoPedidoAnticipado2 = new JLabel();
        avisoPedidoAnticipado2.setFont(FUENTE_AVISO_PEDIDO_ANTICIPADO);
        avisoPedidoAnticipado2.setForeground(COLOR_AVISO_PEDIDO_ANTICIPADO);
        
        panelAvisoPedidoAnticipado1.add(avisoPedidoAnticipado2);
        
        avisoPedidoAnticipado3 = new JLabel();
        avisoPedidoAnticipado3.setFont(new Font("Segoe UI", Font.BOLD, 15));
        avisoPedidoAnticipado3.setForeground(COLOR_AVISO_PEDIDO_ANTICIPADO);
        
        JPanel panelAvisoPedidoAnticipado2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelAvisoPedidoAnticipado2.setOpaque(false);
        
        panelAvisoPedidoAnticipado2.add(avisoPedidoAnticipado3);
        
        panelDetalles.add(panelSeleccionCantidad);
        panelDetalles.add(panelSeleccionCantidad2);
        panelDetalles.add(panelAvisoPedidoAnticipado1);
        panelDetalles.add(panelAvisoPedidoAnticipado2);

        // Panel separador
        JPanel panelSeparador3 = new JPanel();
        panelSeparador3.setPreferredSize(new Dimension(panelDetalles.getWidth(), 50));
        panelSeparador3.setOpaque(false);
        panelDetalles.add(panelSeparador3);

        // Botones finales
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        panelBotones.setOpaque(false);

        JButton botonVolver = new JButton("Volver");
        botonVolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botonVolver.setBackground(COLOR_FONDO_BOTONES);
        botonVolver.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelBotones.add(botonVolver);

        botonAgregarCarrito = new JButton("Agregar al carrito");
        botonAgregarCarrito.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botonAgregarCarrito.setBackground(COLOR_FONDO_BOTONES);
        botonAgregarCarrito.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelBotones.add(botonAgregarCarrito);
        botonAgregarCarrito.setEnabled(false);

        panelDetalles.add(panelBotones);

        this.idProducto = informacionProducto.get("Id");

        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.mostrarProductosVenta(InformacionProducto.this);
            }
        });

        botonAgregarCarrito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.agregarProductoCarrito(idCliente, InformacionProducto.this.idProducto,
                        Integer.parseInt(cantidadLabel.getText()), InformacionProducto.this);
            }
        });
        
        actualizarBtnSumar(informacionProducto);
        actualizarBtnRestar();
    }
    
    private void actualizarBtnSumar(Map<String, Object> informacionProducto){
        
        int disponibilidadProducto = control.verificarExistenciasProducto(informacionProducto.get("Id"));
        int cantidadTexto = Integer.parseInt(cantidadLabel.getText());
        
        if((cantidadTexto > disponibilidadProducto)){
            
            int cantidadPedidoAnticipado = cantidadTexto - disponibilidadProducto;
            
            String palabraIncluyen;
            String palabraUnidades;
            if(cantidadPedidoAnticipado == 1){
                palabraIncluyen = "incluye";
                palabraUnidades = "unidad";
            } else{
                palabraIncluyen = "incluyen";
                palabraUnidades = "unidades";
            }
            
            String cantidadPedidoAnticipadoCadena = String.valueOf(cantidadPedidoAnticipado);
            
            avisoPedidoAnticipado1.setText("Aviso: Se " + palabraIncluyen);
            avisoCantidadPedidoAnticipado.setText(cantidadPedidoAnticipadoCadena);
            avisoPedidoAnticipado2.setText(palabraUnidades + " del producto de las que");
            avisoPedidoAnticipado3.setText("no tenemos stock, se realizará su compra lo antes posible.");

        } else if(disponibilidadProducto == 0){
            avisoPedidoAnticipado1.setText("El producto no cuenta con stock actualmente. ");
            avisoCantidadPedidoAnticipado.setText(" ");
            avisoPedidoAnticipado2.setText("");
            avisoPedidoAnticipado3.setText("Puede pedirlos y solicitaremos su compra inmediata.");
        }
        else{
            avisoPedidoAnticipado1.setText(" ");
            avisoCantidadPedidoAnticipado.setText(" ");
            avisoPedidoAnticipado2.setText(" ");
            avisoPedidoAnticipado3.setText(" ");
            
        }
        
    }
    
    private void actualizarBtnRestar(){
        
        int cantidadTexto = Integer.parseInt(cantidadLabel.getText());
        
        if(cantidadTexto > 0){
            btnMenos.setEnabled(true);
        } else{
            btnMenos.setEnabled(false);
        }
        
    }
    
    private void sumarUnidad(Map<String, Object> informacionProducto){
        
        actualizarCantidad(1);
        
        int disponibilidadProducto = control.verificarExistenciasProducto(informacionProducto.get("Id"));
        
        int cantidadTexto = Integer.parseInt(cantidadLabel.getText());
        
        if(cantidadTexto > disponibilidadProducto){
            
            int cantidadPedidoAnticipado = cantidadTexto - disponibilidadProducto;
            
            String palabraIncluyen;
            String palabraUnidades;
            if(cantidadPedidoAnticipado == 1){
                palabraIncluyen = "incluye";
                palabraUnidades = "unidad";
            } else{
                palabraIncluyen = "incluyen";
                palabraUnidades = "unidades";
            }
            
            String cantidadPedidoAnticipadoCadena = String.valueOf(cantidadPedidoAnticipado);
            
            avisoPedidoAnticipado1.setText("Aviso: Se " + palabraIncluyen);
            avisoCantidadPedidoAnticipado.setText(cantidadPedidoAnticipadoCadena);
            avisoPedidoAnticipado2.setText(palabraUnidades + " del producto de las que");
            avisoPedidoAnticipado3.setText("no tenemos stock, se realizará su compra lo antes posible.");

        } else if(disponibilidadProducto == 0){
            avisoPedidoAnticipado1.setText("El producto no cuenta con stock actualmente. ");
            avisoCantidadPedidoAnticipado.setText(" ");
            avisoPedidoAnticipado2.setText(" ");
            avisoPedidoAnticipado3.setText("Puede pedirlos y solicitaremos su compra inmediata.");
        }
        else{
            avisoPedidoAnticipado1.setText(" ");
            avisoCantidadPedidoAnticipado.setText(" ");
            avisoPedidoAnticipado2.setText(" ");
            avisoPedidoAnticipado3.setText(" ");
            actualizarBtnSumar(informacionProducto);
        }
        
        actualizarBtnRestar();
        
    }
    
    private void restarUnidad(Map<String, Object> informacionProducto){
        int cantidadTexto = Integer.parseInt(cantidadLabel.getText());
        
        if(cantidadTexto > 0){
            actualizarCantidad(-1);
            btnMenos.setEnabled(true);
            
            actualizarBtnRestar();
        } else{
            btnMenos.setEnabled(false);
        }
        
        actualizarBtnSumar(informacionProducto);
    }

    private void actualizarCantidad(int cambio) {
        cantidad = Math.max(0, cantidad + cambio);
        cantidadLabel.setText(String.valueOf(cantidad));
        if (cantidad > 0) {
            botonAgregarCarrito.setEnabled(true);
        } else {
            botonAgregarCarrito.setEnabled(false);
        }
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
        encabezado.mostrarDireccionCliente();
        encabezado.mostrarNombreApellidoCliente();
        encabezado.mostrarBtnNumeroCarritoCompras();
        encabezado.ocultarBarraBusqueda();
        encabezado.ocultarBtnSalir();
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
