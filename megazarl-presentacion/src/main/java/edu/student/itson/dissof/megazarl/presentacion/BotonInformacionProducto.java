package edu.student.itson.dissof.megazarl.presentacion;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class BotonInformacionProducto extends JButton {
    private final int ALTO_BOTON;
    private final int ANCHO_BOTON;
    private final int MARGEN_VERTICAL_COMPONENTES;
    private final int MARGEN_HORIZONTAL_COMPONENTES;
    private final Long ID_PRODUCTO;
    private final String DIRECCION_IMAGEN_PRODUCTO;
    private final String NOMBRE_PRODUCTO;
    private final String VARIEDAD_PRODUCTO;
    private final Integer MILES_SEMILLAS;
    private final Double PRECIO_PRODUCTO;
    private final String DIRECCION_IMAGEN_PROVEEDOR;
    private final int ANCHO_IMAGEN_PROVEEDOR;
    private final int ALTO_IMAGEN_PROVEEDOR;
    private final Color COLOR_FONDO;
    private final Color COLOR_FONDO_SELECCIONADO;
    private final Color COLOR_FONDO_SOBRE;
    private final Color COLOR_FONDO_SEPARADOR;
    private Color colorFondoActual;

    
    
    public BotonInformacionProducto(
            int altoBoton, 
            int anchoBoton, 
            Color colorFondo,
            Color colorFondoSeleccionado,
            Color colorFondoSobre,
            Color colorFondoSeparador,
            int margenVerticalComponentes, 
            int margenHorizontalComponentes, 
            Long idProducto, 
            String direccionImagenProducto,
            String nombreProducto,
            String variedadProducto, 
            Integer milesSemillas, 
            Double precioProducto, 
            String direccionImagenProveedor, 
            int anchoImagenProveedor,
            int altoImagenProveedor) {
        
        this.ALTO_BOTON = altoBoton;
        this.ANCHO_BOTON = anchoBoton;
        this.MARGEN_VERTICAL_COMPONENTES = margenVerticalComponentes;
        this.MARGEN_HORIZONTAL_COMPONENTES = margenHorizontalComponentes;
        this.ID_PRODUCTO = idProducto;
        this.DIRECCION_IMAGEN_PRODUCTO = direccionImagenProducto;
        this.NOMBRE_PRODUCTO = nombreProducto;
        this.VARIEDAD_PRODUCTO = variedadProducto;
        this.MILES_SEMILLAS = milesSemillas;
        this.PRECIO_PRODUCTO = precioProducto;
        this.DIRECCION_IMAGEN_PROVEEDOR = direccionImagenProveedor;
        this.ANCHO_IMAGEN_PROVEEDOR = anchoImagenProveedor;
        this.ALTO_IMAGEN_PROVEEDOR = altoImagenProveedor;
        this.COLOR_FONDO = colorFondo;
        this.COLOR_FONDO_SELECCIONADO = colorFondoSeleccionado;
        this.COLOR_FONDO_SOBRE = colorFondoSobre;
        this.COLOR_FONDO_SEPARADOR = colorFondoSeparador;
        this.colorFondoActual = colorFondo;
        this.initComponentes();
    }

    private void initComponentes() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.setPreferredSize(new Dimension(ANCHO_BOTON, ALTO_BOTON));
        this.setBackground(COLOR_FONDO);

        FlowLayout layoutComponentesInformacionProducto = new FlowLayout(FlowLayout.LEFT);
        layoutComponentesInformacionProducto.setHgap(MARGEN_VERTICAL_COMPONENTES);
        layoutComponentesInformacionProducto.setVgap(MARGEN_HORIZONTAL_COMPONENTES);

        // Imagen de producto.
        ImageIcon iconoProducto = new ImageIcon(this.getClass().getResource(DIRECCION_IMAGEN_PRODUCTO));
        Image imagenProducto = iconoProducto.getImage().getScaledInstance(ANCHO_BOTON - 15, ALTO_BOTON / 2, Image.SCALE_SMOOTH);
        ImageIcon nuevoIconoProducto = new ImageIcon(imagenProducto);

        JLabel etqImagenProducto = new JLabel(nuevoIconoProducto);

        JPanel panelImagenProducto = new JPanel();
        panelImagenProducto.setOpaque(false);
        panelImagenProducto.add(etqImagenProducto);

        this.add(panelImagenProducto);

        Font fuenteEtqNombreVariedadCantidadProducto = new Font("Arial", Font.BOLD, 17);

        // Nombre y variedad del producto.
        String nombreVariedadProducto = NOMBRE_PRODUCTO + " " + VARIEDAD_PRODUCTO;

        JPanel panelNombreVariedadProducto1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelNombreVariedadProducto1.setOpaque(false);
        
        JPanel panelNombreVariedadProducto2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelNombreVariedadProducto2.setOpaque(false);
        
        JLabel etqNombreVariedad1 = new JLabel();
        JLabel etqNombreVariedad2 = new JLabel();
        
        if(nombreVariedadProducto.length() > 20){
            
            String[] palabrasNombreVariedad = nombreVariedadProducto.split(" ");
            
            int cantidadCaracteresAcumulados = 0;
            
            for(String palabra: palabrasNombreVariedad){
                
                if(cantidadCaracteresAcumulados + palabra.length() + 1 > 20){

                    break;
                    
                } else{
                    
                    cantidadCaracteresAcumulados += palabra.length() + 1;
                    
                }

            }
            
            etqNombreVariedad1.setText(nombreVariedadProducto.substring(0, cantidadCaracteresAcumulados));
            etqNombreVariedad2.setText(nombreVariedadProducto.substring(cantidadCaracteresAcumulados));
            
        } else{
            
            etqNombreVariedad1.setText(nombreVariedadProducto);
            etqNombreVariedad2.setText(" ");
            
        }

        etqNombreVariedad1.setFont(fuenteEtqNombreVariedadCantidadProducto);
        etqNombreVariedad2.setFont(fuenteEtqNombreVariedadCantidadProducto);
        
        panelNombreVariedadProducto1.add(etqNombreVariedad1);
        panelNombreVariedadProducto2.add(etqNombreVariedad2);
        
        
        this.add(panelNombreVariedadProducto1);
        this.add(panelNombreVariedadProducto2);
        
        // Separador
        JSeparator separador = new JSeparator();
        separador.setBackground(COLOR_FONDO_SEPARADOR);
        this.add(separador);
        
        // Panel separador
        JPanel panelSeparador = new JPanel();
        panelSeparador.setOpaque(false);
        this.add(panelSeparador);
        
        // Cantidad de semillas de producto.
        JLabel etqCantidadSemillas = new JLabel(MILES_SEMILLAS + "k semillas");
        etqCantidadSemillas.setFont(fuenteEtqNombreVariedadCantidadProducto);

        JPanel panelCantidadSemillasProducto = new JPanel(layoutComponentesInformacionProducto);
        panelCantidadSemillasProducto.setOpaque(false);
        panelCantidadSemillasProducto.add(etqCantidadSemillas);
        this.add(panelCantidadSemillasProducto);

        // Precio de producto.
        String precioFormato = String.format("%,.2f", PRECIO_PRODUCTO);

        String partesPrecio[] = precioFormato.split("\\.");
        String parteEnteraPrecioProdcuto = partesPrecio[0];
        String parteDecimalPrecioProducto = partesPrecio[1];

        JLabel etqParteEnteraPrecioProducto = new JLabel("$" + parteEnteraPrecioProdcuto + ".");
        Font fuenteEtqParteEnteraPrecioProducto = new Font("Arial", Font.BOLD, 20);
        etqParteEnteraPrecioProducto.setFont(fuenteEtqParteEnteraPrecioProducto);

        JLabel etqParteDecimalPrecioProducto = new JLabel(parteDecimalPrecioProducto);
        Font fuenteEtqParteDecimalPrecioProducto = new Font("Arial", Font.BOLD, 15);
        etqParteDecimalPrecioProducto.setFont(fuenteEtqParteDecimalPrecioProducto);

        JPanel panelPrecioProducto = new JPanel(layoutComponentesInformacionProducto);
        panelPrecioProducto.setOpaque(false);

        panelPrecioProducto.add(etqParteEnteraPrecioProducto);
        panelPrecioProducto.add(etqParteDecimalPrecioProducto);

        this.add(panelPrecioProducto);

        // ImagenProveedor
        ImageIcon iconoProveedor = new ImageIcon(this.getClass().getResource(DIRECCION_IMAGEN_PROVEEDOR));
        Image imagenProveedor = iconoProveedor.getImage().getScaledInstance(ANCHO_IMAGEN_PROVEEDOR, ALTO_IMAGEN_PROVEEDOR, Image.SCALE_SMOOTH);
        ImageIcon nuevoIconoProveedor = new ImageIcon(imagenProveedor);

        JLabel etqImagenProveedor = new JLabel(nuevoIconoProveedor);

        JPanel panelImagenProveedor = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelImagenProveedor.setOpaque(false);
        panelImagenProveedor.add(etqImagenProveedor);

        this.add(panelImagenProveedor);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                colorFondoActual = COLOR_FONDO_SELECCIONADO;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                colorFondoActual = COLOR_FONDO_SOBRE;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                colorFondoActual = COLOR_FONDO;
                repaint();
            }
        });

    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        this.setBorderPainted(false);
        this.setContentAreaFilled(false);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RoundRectangle2D bordeBoton = new RoundRectangle2D.Float(0, 0, this.getWidth(), this.getHeight(), 20, 20);

        g2d.setPaint(colorFondoActual);

        g2d.fill(bordeBoton);

        g2d.draw(bordeBoton);

    }

    public Long getIdProducto() {
        return ID_PRODUCTO;
    }
}
