
package edu.student.itson.dissof.megazarl.presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IProductosVenta;


public class ProductosVenta extends JFrame implements IProductosVenta{
    
    private final ControlCompra control;
    private Integer idCliente;
    
    private Encabezado encabezado;
    
    private JPanel panelGeneral;
    private JPanel panelProductos;
    
    private JScrollPane scrollPaneProductos;
    
    private final int MARGEN_VERTICAL_PRODUCTOS = 10;
    private final int MARGEN_HORIZONTAL_PRODUCTOS = 10;
    
    
    private final int ANCHO_IMAGEN_PROVEEDOR = 90;
    private final int ALTO_IMAGEN_PROVEEDOR = 40;
    
    private final int ALTO_BOTON_INFORMACION_PRODUCTO = 300;
    private final int ANCHO_BOTON_INFORMACION_PRODUCTO = 221;
    
    private final Color COLOR_BOTON_FONDO =  new Color(255, 254, 246);
    private final Color COLOR_BOTON_FONDO_SELECCIONADO =  new Color(211, 207, 174);
    private final Color COLOR_BOTON_FONDO_SOBRE =  new Color(211, 207, 174);
    
    private final Color COLOR_PANEL_PRODUCTOS = new Color(227, 227, 227);
    
    private final int MARGEN_VERTICAL_COMPONENTES = 1;
    private final int MARGEN_HORIZONTAL_COMPONENTES = 1;
    
    
    public ProductosVenta(ControlCompra control, Integer idCliente){
        this.initComponents();
        this.control = control;
        this.idCliente = idCliente;
    }
    
    private void initComponents(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,800);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
 
        encabezado = new Encabezado();      
        this.add(encabezado, BorderLayout.NORTH);
        
        panelGeneral = new JPanel(new BorderLayout());
        this.add(panelGeneral, BorderLayout.CENTER);
        
        
        FlowLayout layoutPanelProductos = new FlowLayout(FlowLayout.LEFT);
        layoutPanelProductos.setVgap(MARGEN_VERTICAL_PRODUCTOS);
        layoutPanelProductos.setHgap(MARGEN_HORIZONTAL_PRODUCTOS);
        
        panelProductos = new JPanel(layoutPanelProductos);
        
        panelProductos.setBackground(COLOR_PANEL_PRODUCTOS);
        
        scrollPaneProductos = new JScrollPane(panelProductos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        panelGeneral.add(scrollPaneProductos, BorderLayout.CENTER);
    }
    
    @Override
    public void setProductos(List<HashMap<String, Object>> listaInformacionProductos){
        panelProductos.removeAll();
        
        ActionListener listenerBotonInformacionProducto = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BotonInformacionProducto botonInformacionProducto =  (BotonInformacionProducto) e.getSource();
                mostrarInformacionProducto(botonInformacionProducto.getId_Producto());
            }
        };
        
        panelProductos.setPreferredSize(new Dimension(this.getWidth(), Math.ceilDiv(listaInformacionProductos.size(), 5) * 
                ALTO_BOTON_INFORMACION_PRODUCTO + MARGEN_VERTICAL_PRODUCTOS * (Math.ceilDiv(listaInformacionProductos.size(), 5) + 1)));
        
        for(HashMap<String, Object> informacionProductoInicio: listaInformacionProductos){
            
            BotonInformacionProducto botonInformacionProducto = new BotonInformacionProducto(ALTO_BOTON_INFORMACION_PRODUCTO, 
                    ANCHO_BOTON_INFORMACION_PRODUCTO,COLOR_BOTON_FONDO, COLOR_BOTON_FONDO_SELECCIONADO, 
                    COLOR_BOTON_FONDO_SOBRE, MARGEN_VERTICAL_COMPONENTES,
                    MARGEN_HORIZONTAL_COMPONENTES, (Integer)informacionProductoInicio.get("Id"), 
                    (String) informacionProductoInicio.get("DireccionImagenProducto"), 
                    (String) informacionProductoInicio.get("Nombre"), (String) informacionProductoInicio.get("Variedad"), 
                    (Integer) informacionProductoInicio.get("MilesSemillas"), (Float) informacionProductoInicio.get("Precio"), 
                    (String) informacionProductoInicio.get("DireccionImagenProveedor"),
                    ANCHO_IMAGEN_PROVEEDOR, ALTO_IMAGEN_PROVEEDOR);

            botonInformacionProducto.addActionListener(listenerBotonInformacionProducto);
            panelProductos.add(botonInformacionProducto);
            
        }
        
    }
    
    
    @Override
    public void mostrarInformacionProducto(Integer idProducto){
        this.control.mostrarInformacionProducto(idProducto, this);
    }
    
    @Override
    public void hacerVisible(boolean visible){
        setVisible(visible);
    }
}
