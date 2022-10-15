package Diapositivas;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;

import javax.swing.JOptionPane;

import Atajos.Conexion;
import Atajos.Salir;

// pdf
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Graphics;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Daniela
 */
public class Gastos extends javax.swing.JFrame {

    String nombre, apellido, usuario;
    public static int sesionUsuario;
    
    
    /**
     * Creates new form Gastos
     */
    public Gastos() {
        initComponents();
        setSize(760, 650);
        setLocationRelativeTo(null);
        setResizable(false);
        
        usuario = Login.usuario;
        nombre = Login.nombre;
        apellido = Login.apellido;
        sesionUsuario = 1;
        
        setTitle("Control de gastos - " + usuario);
        
         // cierre de frame anterior
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        // colocar imágenes
        ImageIcon wallpaper = new ImageIcon("src/refugio/madera.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabelWallpaper.getWidth(),
                jLabelWallpaper.getHeight(), Image.SCALE_DEFAULT));
        jLabelWallpaper.setIcon(icono);
        this.repaint();

         // panels activos
         jPanelRegistro.setVisible(true);
         jPanelTabla.setVisible(false);
         jPanelGastosTotales.setVisible(false);


         cargarDatos(); 
         cargarDatosGastosTotales();
    }
    // icono
    public Image getLogo(){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("refugio/loguito.png"));
        return retValue;   
    }

    // tabla completa
    public void cargarDatos(){
        DefaultTableModel modelo = new DefaultTableModel();
        
        try{
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select NombreGato, "
                    + "IDGato, Concepto, Gasto, Gasto2, Gasto3 from gastos");
            ResultSet rs = pst.executeQuery();
            
            jTableGastos = new JTable (modelo);
            jScrollPane2.setViewportView(jTableGastos);
            
            modelo.addColumn("NombreGato");
            modelo.addColumn("IDGato");
            modelo.addColumn("Concepto");
            modelo.addColumn("Gasto");
            modelo.addColumn("Gasto2");
            modelo.addColumn("Gasto3");

            while(rs.next()){
                Object[] fila = new Object [6];
                for(int i = 0; i < 6; i++){
                    fila [i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            cn.close();
        }catch(SQLException e){
            System.err.println("Error al cargar datos en la tabla " + e);
            JOptionPane.showMessageDialog(null, nombre + " no es posible cargar "
                    + "los datos en la tabla");
        }
    }
    
    // ver tabla buscando por concepto, nombreGato o IDGato.
    public void buscarPor(){

        DefaultTableModel modelo = new DefaultTableModel();
        String buscar = textBuscar.getText();
        
        String query = "select NombreGato, IDGato, Concepto, Gasto, Gasto2, "
                + "Gasto3 from gastos where Concepto = '" + buscar + "' or "
                + "NombreGato = '" + buscar + "' or IDGato = '" + buscar + "'";
                   
        jTableGastos = new JTable (modelo);
        jScrollPane2.setViewportView(jTableGastos);
            
        modelo.addColumn("NombreGato");
        modelo.addColumn("IDGato");
        modelo.addColumn("Concepto");
        modelo.addColumn("Gasto");
        modelo.addColumn("Gasto2");
        modelo.addColumn("Gasto3");
                
        try{
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Object[] fila = new Object [6];
                for(int i = 0; i < 6; i++){
                    fila [i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            cn.close();

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo buscar por concepto");
        }
    }
    

    // tabla gastos totales
    public void cargarDatosGastosTotales(){
        DefaultTableModel modelo = new DefaultTableModel();
        
            jTableGastosTotales = new JTable(modelo);
            jScrollPane3.setViewportView(jTableGastosTotales);
            
            modelo.addColumn("Mes");
            modelo.addColumn("Año");
            modelo.addColumn("GastosTotales");
            modelo.addColumn("Faltante");
        try{
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select Mes, Año, "
                    + "GastosTotales, Faltante from gastoscubiertos");
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                Object[] fila = new Object[4];
                for(int i = 0; i < 4; i++){
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            cn.close();
        }catch(SQLException e){
            System.err.println("Error al rellenar tabla " + e);
            JOptionPane.showMessageDialog(null, "No se pudo cargar la tabla");
        }
    }
    
    // ver tabla gastos totales por mes y/o año.
    public void buscarGastosTotalesPor(){

        DefaultTableModel modelo = new DefaultTableModel();
        String buscar = textMesAño.getText();
        
        String query = "select Mes, Año, GastosTotales, Faltante from "
                + "gastoscubiertos where Mes = '" + buscar + "' or Año = '" + 
                buscar + "'";

        jTableGastosTotales = new JTable(modelo);
        jScrollPane3.setViewportView(jTableGastosTotales);
            
        modelo.addColumn("Mes");
        modelo.addColumn("Año");
        modelo.addColumn("GastosTotales");
        modelo.addColumn("Faltante");
            
        try{
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                Object[] fila = new Object[4];
                for(int i = 0; i < 4; i++){
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            cn.close();

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo buscar por concepto");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitulo = new javax.swing.JLabel();
        jPanelRegistro = new javax.swing.JPanel();
        jButtonRegistrarGastos = new javax.swing.JToggleButton();
        jButtonVerTabla = new javax.swing.JToggleButton();
        jButtonGastosTotales1 = new javax.swing.JToggleButton();
        jLabelNombre = new javax.swing.JLabel();
        textNombreGato = new javax.swing.JTextField();
        jLabelID = new javax.swing.JLabel();
        textIDGato = new javax.swing.JTextField();
        jLabelConcepto = new javax.swing.JLabel();
        cmbConcepto = new javax.swing.JComboBox<>();
        jLabelDetalle = new javax.swing.JLabel();
        textDetalle = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabelGasto = new javax.swing.JLabel();
        textGasto = new javax.swing.JTextField();
        jButtonRegistrar = new javax.swing.JToggleButton();
        jButtonInicio = new javax.swing.JToggleButton();
        jLabelGasto2 = new javax.swing.JLabel();
        textGasto2 = new javax.swing.JTextField();
        jLabelGasto3 = new javax.swing.JLabel();
        textGasto3 = new javax.swing.JTextField();
        jPanelTabla = new javax.swing.JPanel();
        jButtonRegistrarGastos1 = new javax.swing.JToggleButton();
        jButtonVerTabla1 = new javax.swing.JToggleButton();
        jButtonGastosTotales = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        textBuscar = new javax.swing.JTextField();
        jButtonBuscar = new javax.swing.JToggleButton();
        jButtonImprimirGastos = new javax.swing.JToggleButton();
        jButtonImprimirGastos1 = new javax.swing.JToggleButton();
        jButtonVerInfo1 = new javax.swing.JToggleButton();
        jLabelNoti = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableGastos = new javax.swing.JTable();
        jPanelGastosTotales = new javax.swing.JPanel();
        jButtonRegistrarGastos2 = new javax.swing.JToggleButton();
        jButtonVerTabla2 = new javax.swing.JToggleButton();
        jButtonGastosTotales3 = new javax.swing.JToggleButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableGastosTotales = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabelGastosTotales = new javax.swing.JLabel();
        textGastosTotales = new javax.swing.JTextField();
        jLabelFaltante = new javax.swing.JLabel();
        textFaltante = new javax.swing.JTextField();
        jLabelMes = new javax.swing.JLabel();
        textMes = new com.toedter.calendar.JMonthChooser();
        jLabelAño = new javax.swing.JLabel();
        textAño = new com.toedter.calendar.JYearChooser();
        jButtonAgregar = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jLabelMesAño = new javax.swing.JLabel();
        jButtonBuscarMes = new javax.swing.JButton();
        textMesAño = new javax.swing.JTextField();
        jButtonImprimir = new javax.swing.JButton();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jLabelWallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconImage(getLogo());
        setIconImages(getIconImages());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitulo.setFont(new java.awt.Font("Yu Gothic UI", 0, 36)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitulo.setText("Gastos");
        getContentPane().add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, -30, 410, 110));

        jPanelRegistro.setBackground(new java.awt.Color(255, 255, 102));
        jPanelRegistro.setPreferredSize(new java.awt.Dimension(760, 670));

        jButtonRegistrarGastos.setBackground(new java.awt.Color(255, 255, 102));
        jButtonRegistrarGastos.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        jButtonRegistrarGastos.setText("Registrar nuevos gastos.");
        jButtonRegistrarGastos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonRegistrarGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarGastosActionPerformed(evt);
            }
        });

        jButtonVerTabla.setBackground(new java.awt.Color(255, 204, 51));
        jButtonVerTabla.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        jButtonVerTabla.setText("Ver tabla de gastos");
        jButtonVerTabla.setBorder(null);
        jButtonVerTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerTablaActionPerformed(evt);
            }
        });

        jButtonGastosTotales1.setBackground(new java.awt.Color(255, 102, 102));
        jButtonGastosTotales1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        jButtonGastosTotales1.setText("Gastos totales");
        jButtonGastosTotales1.setBorder(null);
        jButtonGastosTotales1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGastosTotales1ActionPerformed(evt);
            }
        });

        jLabelNombre.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabelNombre.setText("Nombre del gato:");

        textNombreGato.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        textNombreGato.setForeground(new java.awt.Color(0, 102, 153));

        jLabelID.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabelID.setText("ID del gato:");

        textIDGato.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        textIDGato.setForeground(new java.awt.Color(0, 102, 153));

        jLabelConcepto.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabelConcepto.setText("Concepto:");

        cmbConcepto.setForeground(new java.awt.Color(0, 102, 153));
        cmbConcepto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Veterinaria", "Cuidado (alimento, piedritas, etc)", "Otros" }));

        jLabelDetalle.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabelDetalle.setText("Detalle:");

        textDetalle.setColumns(20);
        textDetalle.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        textDetalle.setForeground(new java.awt.Color(0, 102, 153));
        textDetalle.setRows(5);

        jLabelGasto.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabelGasto.setText("Gasto:");

        textGasto.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        textGasto.setForeground(new java.awt.Color(0, 102, 153));

        jButtonRegistrar.setBackground(new java.awt.Color(255, 255, 102));
        jButtonRegistrar.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        jButtonRegistrar.setText("Registrar nuevos gastos.");
        jButtonRegistrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarActionPerformed(evt);
            }
        });

        jButtonInicio.setBackground(new java.awt.Color(255, 255, 102));
        jButtonInicio.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        jButtonInicio.setText("Volver a inicio.");
        jButtonInicio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInicioActionPerformed(evt);
            }
        });

        jLabelGasto2.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabelGasto2.setText("Gasto 2:");

        textGasto2.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        textGasto2.setForeground(new java.awt.Color(0, 102, 153));
        textGasto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textGasto2ActionPerformed(evt);
            }
        });

        jLabelGasto3.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabelGasto3.setText("Gasto 3:");

        textGasto3.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        textGasto3.setForeground(new java.awt.Color(0, 102, 153));

        javax.swing.GroupLayout jPanelRegistroLayout = new javax.swing.GroupLayout(jPanelRegistro);
        jPanelRegistro.setLayout(jPanelRegistroLayout);
        jPanelRegistroLayout.setHorizontalGroup(
            jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegistroLayout.createSequentialGroup()
                .addComponent(jButtonRegistrarGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jButtonVerTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGastosTotales1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelRegistroLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRegistroLayout.createSequentialGroup()
                        .addComponent(jLabelDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128))
                    .addGroup(jPanelRegistroLayout.createSequentialGroup()
                        .addComponent(jLabelGasto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanelRegistroLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(textGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRegistroLayout.createSequentialGroup()
                        .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelRegistroLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91))
                    .addGroup(jPanelRegistroLayout.createSequentialGroup()
                        .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(textIDGato, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelConcepto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabelNombre, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textNombreGato, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(308, 308, 308)
                        .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelGasto3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelRegistroLayout.createSequentialGroup()
                                .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textGasto2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textGasto3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabelGasto2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanelRegistroLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(cmbConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanelRegistroLayout.setVerticalGroup(
            jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegistroLayout.createSequentialGroup()
                .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGastosTotales1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVerTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRegistrarGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre)
                    .addComponent(jLabelGasto2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textNombreGato, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textGasto2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelID)
                    .addComponent(jLabelGasto3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textIDGato, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textGasto3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelConcepto)
                .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRegistroLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelRegistroLayout.createSequentialGroup()
                                .addComponent(cmbConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelRegistroLayout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jButtonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGasto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 160, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 750, 650));

        jPanelTabla.setBackground(new java.awt.Color(255, 204, 51));
        jPanelTabla.setPreferredSize(new java.awt.Dimension(760, 670));

        jButtonRegistrarGastos1.setBackground(new java.awt.Color(255, 255, 102));
        jButtonRegistrarGastos1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        jButtonRegistrarGastos1.setText("Registrar nuevos gastos.");
        jButtonRegistrarGastos1.setBorder(null);
        jButtonRegistrarGastos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarGastos1ActionPerformed(evt);
            }
        });

        jButtonVerTabla1.setBackground(new java.awt.Color(255, 204, 51));
        jButtonVerTabla1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        jButtonVerTabla1.setText("Ver tabla de gastos");
        jButtonVerTabla1.setBorder(null);
        jButtonVerTabla1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerTabla1ActionPerformed(evt);
            }
        });

        jButtonGastosTotales.setBackground(new java.awt.Color(255, 102, 102));
        jButtonGastosTotales.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        jButtonGastosTotales.setText("Gastos totales");
        jButtonGastosTotales.setBorder(null);
        jButtonGastosTotales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGastosTotalesActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textBuscar.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        textBuscar.setForeground(new java.awt.Color(0, 102, 153));
        jPanel1.add(textBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 210, 30));

        jButtonBuscar.setBackground(new java.awt.Color(255, 204, 51));
        jButtonBuscar.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        jButtonBuscar.setText("Buscar palabra elegida");
        jButtonBuscar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 240, -1));

        jButtonImprimirGastos.setBackground(new java.awt.Color(255, 204, 51));
        jButtonImprimirGastos.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        jButtonImprimirGastos.setText("Imprimir tabla");
        jButtonImprimirGastos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonImprimirGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirGastosActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonImprimirGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 160, -1));

        jButtonImprimirGastos1.setBackground(new java.awt.Color(255, 204, 51));
        jButtonImprimirGastos1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        jButtonImprimirGastos1.setText("Imprimir todos los gastos");
        jButtonImprimirGastos1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonImprimirGastos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirGastos1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonImprimirGastos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 71, 220, 30));

        jButtonVerInfo1.setBackground(new java.awt.Color(255, 204, 51));
        jButtonVerInfo1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        jButtonVerInfo1.setText("Información");
        jButtonVerInfo1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jButtonVerInfo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, 0, -1));

        jLabelNoti.setText("Por favor, escribe un nombre, ID o Concepto:");
        jPanel1.add(jLabelNoti, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 240, -1));

        jTableGastos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "ID", "Concepto", "Gasto", "Gasto 2", "Gasto 3"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableGastos);

        javax.swing.GroupLayout jPanelTablaLayout = new javax.swing.GroupLayout(jPanelTabla);
        jPanelTabla.setLayout(jPanelTablaLayout);
        jPanelTablaLayout.setHorizontalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaLayout.createSequentialGroup()
                .addComponent(jButtonRegistrarGastos1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonVerTabla1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGastosTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(jPanelTablaLayout.createSequentialGroup()
                .addGroup(jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelTablaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelTablaLayout.setVerticalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaLayout.createSequentialGroup()
                .addGroup(jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRegistrarGastos1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonGastosTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVerTabla1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 134, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 750, 650));

        jPanelGastosTotales.setBackground(new java.awt.Color(255, 102, 102));
        jPanelGastosTotales.setPreferredSize(new java.awt.Dimension(760, 670));

        jButtonRegistrarGastos2.setBackground(new java.awt.Color(255, 255, 102));
        jButtonRegistrarGastos2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        jButtonRegistrarGastos2.setText("Registrar nuevos gastos.");
        jButtonRegistrarGastos2.setBorder(null);
        jButtonRegistrarGastos2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarGastos2ActionPerformed(evt);
            }
        });

        jButtonVerTabla2.setBackground(new java.awt.Color(255, 204, 51));
        jButtonVerTabla2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        jButtonVerTabla2.setText("Ver tabla de gastos");
        jButtonVerTabla2.setBorder(null);
        jButtonVerTabla2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerTabla2ActionPerformed(evt);
            }
        });

        jButtonGastosTotales3.setBackground(new java.awt.Color(255, 102, 102));
        jButtonGastosTotales3.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        jButtonGastosTotales3.setText("Gastos totales");
        jButtonGastosTotales3.setBorder(null);
        jButtonGastosTotales3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGastosTotales3ActionPerformed(evt);
            }
        });

        jTableGastosTotales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mes", "Año", "Gastos totales", "Faltante"
            }
        ));
        jScrollPane3.setViewportView(jTableGastosTotales);

        jPanel2.setBackground(new java.awt.Color(255, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro de gastos totales:", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Yu Gothic Medium", 0, 16), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelGastosTotales.setBackground(new java.awt.Color(0, 51, 153));
        jLabelGastosTotales.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLabelGastosTotales.setForeground(new java.awt.Color(255, 255, 255));
        jLabelGastosTotales.setText("Gastos totales:");
        jPanel2.add(jLabelGastosTotales, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));
        jPanel2.add(textGastosTotales, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 160, 30));

        jLabelFaltante.setBackground(new java.awt.Color(0, 51, 153));
        jLabelFaltante.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLabelFaltante.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFaltante.setText("Faltante a cubrir:");
        jPanel2.add(jLabelFaltante, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));
        jPanel2.add(textFaltante, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 160, 30));

        jLabelMes.setBackground(new java.awt.Color(0, 51, 153));
        jLabelMes.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLabelMes.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMes.setText("Mes de gastos:");
        jPanel2.add(jLabelMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, -1));
        jPanel2.add(textMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 140, 30));

        jLabelAño.setBackground(new java.awt.Color(0, 51, 153));
        jLabelAño.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLabelAño.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAño.setText("Año correspondiente:");
        jPanel2.add(jLabelAño, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, -1, -1));
        jPanel2.add(textAño, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 90, 30));

        jButtonAgregar.setForeground(new java.awt.Color(255, 102, 102));
        jButtonAgregar.setText("Agregar.");
        jButtonAgregar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 120, 40));

        jButtonModificar.setForeground(new java.awt.Color(255, 102, 102));
        jButtonModificar.setText("Modificar.");
        jButtonModificar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 120, 40));

        jLabelMesAño.setBackground(new java.awt.Color(0, 51, 153));
        jLabelMesAño.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLabelMesAño.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMesAño.setText("Mes o año:");
        jPanel2.add(jLabelMesAño, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, -1, -1));

        jButtonBuscarMes.setForeground(new java.awt.Color(255, 102, 102));
        jButtonBuscarMes.setText("Buscar");
        jButtonBuscarMes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonBuscarMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarMesActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonBuscarMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 120, 40));
        jPanel2.add(textMesAño, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, 120, 30));

        jButtonImprimir.setForeground(new java.awt.Color(255, 102, 102));
        jButtonImprimir.setText("Imprimir");
        jButtonImprimir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 120, 40));

        jCalendar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanelGastosTotalesLayout = new javax.swing.GroupLayout(jPanelGastosTotales);
        jPanelGastosTotales.setLayout(jPanelGastosTotalesLayout);
        jPanelGastosTotalesLayout.setHorizontalGroup(
            jPanelGastosTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGastosTotalesLayout.createSequentialGroup()
                .addComponent(jButtonRegistrarGastos2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonVerTabla2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGastosTotales3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGastosTotalesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGastosTotalesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelGastosTotalesLayout.setVerticalGroup(
            jPanelGastosTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGastosTotalesLayout.createSequentialGroup()
                .addGroup(jPanelGastosTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRegistrarGastos2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonGastosTotales3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVerTabla2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelGastosTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jCalendar1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
                .addGap(0, 124, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelGastosTotales, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 750, 650));

        jLabelWallpaper.setBackground(new java.awt.Color(255, 255, 102));
        jLabelWallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/refugio/madera.jpg"))); // NOI18N
        getContentPane().add(jLabelWallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, -130, 900, 900));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInicioActionPerformed
      
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jButtonInicioActionPerformed

    private void jButtonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarActionPerformed
        
        //variables
        int avanzar = 0, conceptoNivel;
        String nombreGato, IDGato, concepto = null, detalle, gasto, gastoDos, gastoTres;
        
        // obtención de datos
        nombreGato = textNombreGato.getText().trim();
        IDGato = textIDGato.getText().trim();
        conceptoNivel = cmbConcepto.getSelectedIndex() + 1;
        detalle = textDetalle.getText().trim();
        gasto = textGasto.getText().trim();
        gastoDos = textGasto2.getText().trim();
        gastoTres = textGasto3.getText().trim();
        
        // control de datos completos.
        if(nombreGato.equals("")){
            jLabelNombre.setBackground(Color.red);
            avanzar++;
        }
        if(IDGato.equals("")){
            jLabelID.setBackground(Color.red);
            avanzar++;
        }
        if(conceptoNivel == 0){
            jLabelConcepto.setBackground(Color.red);
            avanzar++;
        }
        if(gasto.equals("")){
            jLabelGasto.setBackground(Color.red);
            avanzar++;
        }
        
        
        // cmb valor
        if(conceptoNivel == 1){
            concepto = "Veterinaria";
        }else if(conceptoNivel == 2){
            concepto = "Cuidados";
        }else if(conceptoNivel == 3){
            concepto = "Otros";
        }
        

        // verificación para avanzar
        if(avanzar == 0){
            
            // conexión a base
            try{
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement("insert into gastos "
                        + "values (?, ?, ?, ?, ?, ?, ?, ?)");
            
                pst.setInt(1, 0);
                pst.setString(2, nombreGato);
                pst.setString(3, IDGato);
                pst.setString(4, concepto);
                pst.setString(5, detalle);
                pst.setString(6, gasto);
                pst.setString(7, gastoDos);
                pst.setString(8, gastoTres);
            
                pst.executeUpdate();
                cn.close();
            
                Limpiar();
                JOptionPane.showMessageDialog(null, "Registro exitoso " + nombre + " " + apellido);

            }catch(SQLException e){
                System.err.println("Error al conectar con la base de datos " + e);
                JOptionPane.showMessageDialog(null, "No se pudo conectar con la base de datos");
            }
        } else{
            JOptionPane.showMessageDialog(null, nombre + " debes completar los campos"
                    + " obligatorios para registrar un nuevo gasto");
        }
    }//GEN-LAST:event_jButtonRegistrarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        
        Salir salir = new Salir();
        salir.botonCerrar();
        
    }//GEN-LAST:event_formWindowClosing

    private void jButtonVerTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerTablaActionPerformed
      
         // panels activos
         jPanelRegistro.setVisible(false);
         jPanelTabla.setVisible(true);
         jPanelGastosTotales.setVisible(false);
         
         cargarDatos();
         
    }//GEN-LAST:event_jButtonVerTablaActionPerformed

    private void jButtonRegistrarGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarGastosActionPerformed
        
         // panels activos
         jPanelRegistro.setVisible(true);
         jPanelTabla.setVisible(false);
         jPanelGastosTotales.setVisible(false);
         
    }//GEN-LAST:event_jButtonRegistrarGastosActionPerformed

    private void jButtonGastosTotales1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGastosTotales1ActionPerformed
       
         // panels activos
         jPanelRegistro.setVisible(false);
         jPanelTabla.setVisible(false);
         jPanelGastosTotales.setVisible(true);
        
         cargarDatosGastosTotales();
        
    }//GEN-LAST:event_jButtonGastosTotales1ActionPerformed

    private void jButtonRegistrarGastos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarGastos1ActionPerformed
     
         // panels activos
         jPanelRegistro.setVisible(true);
         jPanelTabla.setVisible(false);
         jPanelGastosTotales.setVisible(false);
        
    }//GEN-LAST:event_jButtonRegistrarGastos1ActionPerformed

    private void jButtonVerTabla1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerTabla1ActionPerformed
       
         // panels activos
         jPanelRegistro.setVisible(false);
         jPanelTabla.setVisible(true);
         jPanelGastosTotales.setVisible(false);

         cargarDatos();
         
    }//GEN-LAST:event_jButtonVerTabla1ActionPerformed

    private void jButtonGastosTotalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGastosTotalesActionPerformed
        
         // panels activos
         jPanelRegistro.setVisible(false);
         jPanelTabla.setVisible(false);
         jPanelGastosTotales.setVisible(true);

         cargarDatosGastosTotales();
         
    }//GEN-LAST:event_jButtonGastosTotalesActionPerformed

    private void jButtonRegistrarGastos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarGastos2ActionPerformed
       
         // panels activos
         jPanelRegistro.setVisible(true);
         jPanelTabla.setVisible(false);
         jPanelGastosTotales.setVisible(false);
         
    }//GEN-LAST:event_jButtonRegistrarGastos2ActionPerformed

    private void jButtonVerTabla2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerTabla2ActionPerformed
        
          // panels activos
         jPanelRegistro.setVisible(false);
         jPanelTabla.setVisible(true);
         jPanelGastosTotales.setVisible(false);
         
         cargarDatos();
                   
    }//GEN-LAST:event_jButtonVerTabla2ActionPerformed

    private void jButtonGastosTotales3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGastosTotales3ActionPerformed
        
         // panels activos
         jPanelRegistro.setVisible(false);
         jPanelTabla.setVisible(false);
         jPanelGastosTotales.setVisible(true);
        
         cargarDatosGastosTotales();

    }//GEN-LAST:event_jButtonGastosTotales3ActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed

        buscarPor();

    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void textGasto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textGasto2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textGasto2ActionPerformed

    private void jButtonImprimirGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirGastosActionPerformed

        pdfGastos();
        
    }//GEN-LAST:event_jButtonImprimirGastosActionPerformed

    private void jButtonBuscarMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarMesActionPerformed
        
        buscarGastosTotalesPor();
       
    }//GEN-LAST:event_jButtonBuscarMesActionPerformed

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
     
       String gastosTotales, faltante;
       int mes, año, avanzar = 0;
       
       mes = textMes.getMonth();
       año = textAño.getYear();
       gastosTotales = textGastosTotales.getText().trim();
       faltante = textFaltante.getText().trim();
       
       
       if(mes == 0){
           textMes.setBackground(Color.red);
           avanzar++;
       } 
       if(año == 0){
           textAño.setBackground(Color.red);
           avanzar++;
       }
       if(gastosTotales.equals("")){
           textGastosTotales.setBackground(Color.red);
           avanzar++;
       }
       if(faltante.equals("")){
           textFaltante.setBackground(Color.red);
           avanzar++;
       }
       
       if(avanzar == 0){
       
           try{
               Connection cn = Conexion.conectar();
               PreparedStatement pst = cn.prepareStatement("insert into "
                       + "gastoscubiertos values(?, ?, ?, ?, ?)");
           
               pst.setInt(1, 0);
               pst.setInt(2, mes);
               pst.setInt(3, año);
               pst.setString(4, gastosTotales);
               pst.setString(5, faltante);
           
               pst.executeUpdate();
               cn.close();
               Limpiar();
               JOptionPane.showMessageDialog(null, "Registro exitoso");
           }catch(SQLException e){
               System.err.println("Error al ingresar gastos totales " + e);
               JOptionPane.showMessageDialog(null, "No se pudo registrar los gastos totales");
           }
       }

    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
     // MODIFICAR, ES EL DEL REGISTROOO
       String gastosTotales, faltante;
       int mes, año, avanzar = 0;
       
       mes = textMes.getMonth();
       año = textAño.getYear();
       gastosTotales = textGastosTotales.getText().trim();
       faltante = textFaltante.getText().trim();
       
       
       if(mes == 0){
           textMes.setBackground(Color.red);
           avanzar++;
       } 
       if(año == 0){
           textAño.setBackground(Color.red);
           avanzar++;
       }
       if(gastosTotales.equals("")){
           textGastosTotales.setBackground(Color.red);
           avanzar++;
       }
       if(faltante.equals("")){
           textFaltante.setBackground(Color.red);
           avanzar++;
       }
       
       try{
           Connection cn = Conexion.conectar();
           PreparedStatement pst = cn.prepareStatement("insert into gastos cubiertos (?, ?, ?, ?, ?)");
           
           pst.setInt(1, 0);
           pst.setInt(2, mes);
           pst.setInt(3, año);
           pst.setString(4, gastosTotales);
           pst.setString(5, faltante);
           
           pst.executeUpdate();
           cn.close();
           Limpiar();
           JOptionPane.showMessageDialog(null, "Registro exitoso");
           
       }catch(SQLException e){
           System.err.println("Error al ingresar gastos totales " + e);
           JOptionPane.showMessageDialog(null, "No se pudo registrar los gastos totales");
       }

    }//GEN-LAST:event_jButtonModificarActionPerformed

    private void jButtonImprimirGastos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirGastos1ActionPerformed
      
        pdfGastosFull();
        
    }//GEN-LAST:event_jButtonImprimirGastos1ActionPerformed

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        
        pdfGastosTotales();
        
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    /**       
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gastos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbConcepto;
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JToggleButton jButtonBuscar;
    private javax.swing.JButton jButtonBuscarMes;
    private javax.swing.JToggleButton jButtonGastosTotales;
    private javax.swing.JToggleButton jButtonGastosTotales1;
    private javax.swing.JToggleButton jButtonGastosTotales3;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JToggleButton jButtonImprimirGastos;
    private javax.swing.JToggleButton jButtonImprimirGastos1;
    private javax.swing.JToggleButton jButtonInicio;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JToggleButton jButtonRegistrar;
    private javax.swing.JToggleButton jButtonRegistrarGastos;
    private javax.swing.JToggleButton jButtonRegistrarGastos1;
    private javax.swing.JToggleButton jButtonRegistrarGastos2;
    private javax.swing.JToggleButton jButtonVerInfo1;
    private javax.swing.JToggleButton jButtonVerTabla;
    private javax.swing.JToggleButton jButtonVerTabla1;
    private javax.swing.JToggleButton jButtonVerTabla2;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabelAño;
    private javax.swing.JLabel jLabelConcepto;
    private javax.swing.JLabel jLabelDetalle;
    private javax.swing.JLabel jLabelFaltante;
    private javax.swing.JLabel jLabelGasto;
    private javax.swing.JLabel jLabelGasto2;
    private javax.swing.JLabel jLabelGasto3;
    private javax.swing.JLabel jLabelGastosTotales;
    private javax.swing.JLabel jLabelID;
    private javax.swing.JLabel jLabelMes;
    private javax.swing.JLabel jLabelMesAño;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelNoti;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelWallpaper;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelGastosTotales;
    private javax.swing.JPanel jPanelRegistro;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableGastos;
    private javax.swing.JTable jTableGastosTotales;
    private com.toedter.calendar.JYearChooser textAño;
    private javax.swing.JTextField textBuscar;
    private javax.swing.JTextArea textDetalle;
    private javax.swing.JTextField textFaltante;
    private javax.swing.JTextField textGasto;
    private javax.swing.JTextField textGasto2;
    private javax.swing.JTextField textGasto3;
    private javax.swing.JTextField textGastosTotales;
    private javax.swing.JTextField textIDGato;
    private com.toedter.calendar.JMonthChooser textMes;
    private javax.swing.JTextField textMesAño;
    private javax.swing.JTextField textNombreGato;
    // End of variables declaration//GEN-END:variables
   
// método de creación PDF de gastos
    public void pdfGastos(){
        Document documento = new Document();

        try {
            //ruta donde se guardará PDF
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + 
                    "/Desktop/" + nombre + apellido + "tablagastos.pdf"));


            // contenido del PDF. /  imagén de arriba
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/refugio/pose.png");
            header.scaleToFit(100, 100);
            header.setAlignment(Chunk.ALIGN_CENTER);

            // titulo
            Paragraph titulo = new Paragraph();
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            titulo.add("Refucat. \n \n");
            titulo.setFont(FontFactory.getFont("Tahoma", 24, Font.BOLD, BaseColor.BLUE));
            
            // parrafo 1
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Lista de gastos del refugio. \n \n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 16, Font.BOLD, BaseColor.DARK_GRAY));

            // agregar contenido al PDF.
            documento.open();
            documento.add(header);
            documento.add(titulo);
            documento.add(parrafo);
            
            // crear la tabla del PDF.
            PdfPTable tablaGastos = new PdfPTable(6);
            tablaGastos.addCell("Nombre");
            tablaGastos.addCell("ID");
            tablaGastos.addCell("Concepto");
            tablaGastos.addCell("Gasto");
            tablaGastos.addCell("Gasto2");
            tablaGastos.addCell("Gasto3");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement("select NombreGato, "
                    + "IDGato, Concepto, Gasto, Gasto2, Gasto3 from gastos");
             
                ResultSet rs = pst.executeQuery();
                
                // completar información de la tabla
                if(rs.next()){
                    do{
                        tablaGastos.addCell(rs.getString(1));
                        tablaGastos.addCell(rs.getString(2));
                        tablaGastos.addCell(rs.getString(3));
                        tablaGastos.addCell(rs.getString(4));
                        tablaGastos.addCell(rs.getString(5));
                        tablaGastos.addCell(rs.getString(6));
                    }while(rs.next());
                    documento.add(tablaGastos);   
                }
                
            } catch (SQLException e) {
                System.err.println("Error al obtener datos de los gastos " + e);
            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado correctamente");

        } catch (DocumentException | IOException e) {
            System.err.print("Error en PDF o ruta de imagen " + e);
            JOptionPane.showMessageDialog(null, "Error al generar PDF");   
        }  
    }
    
    
     // método de creación PDF de gastos totales
    public void pdfGastosTotales(){
        Document documento = new Document();

        try {
            //ruta donde se guardará PDF
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + 
                    "/Desktop/" + nombre + apellido + "tablagastostotales.pdf"));


            // contenido del PDF. /  imagén de arriba
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/refugio/pose.png");
            header.scaleToFit(100, 100);
            header.setAlignment(Chunk.ALIGN_CENTER);

            // titulo
            Paragraph titulo = new Paragraph();
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            titulo.add("Refucat. \n \n");
            titulo.setFont(FontFactory.getFont("Tahoma", 24, Font.BOLD, BaseColor.BLUE));
            
            // parrafo 1
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Lista completa de gastos del refugio. \n \n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 16, Font.BOLD, BaseColor.MAGENTA));

            // agregar contenido al PDF.
            documento.open();
            documento.add(header);
            documento.add(titulo);
            documento.add(parrafo);
    
             // crear la tabla de gastos totales en PDF.
            PdfPTable tablaGastosTotales = new PdfPTable(4);
            tablaGastosTotales.addCell("Mes");
            tablaGastosTotales.addCell("Año");
            tablaGastosTotales.addCell("GastosTotales");
            tablaGastosTotales.addCell("Faltante");

            try {
                Connection cn1 = Conexion.conectar();
                PreparedStatement pst1 = cn1.prepareStatement("select Mes, Año, "
                        + "GastosTotales, Faltante from gastoscubiertos");
             
                ResultSet rs1 = pst1.executeQuery();
                
                // completar información de la tabla
                if(rs1.next()){
                    do{
                        tablaGastosTotales.addCell(rs1.getString(1));
                        tablaGastosTotales.addCell(rs1.getString(2));
                        tablaGastosTotales.addCell(rs1.getString(3));
                        tablaGastosTotales.addCell(rs1.getString(4));
                    }while(rs1.next());
                    documento.add(tablaGastosTotales);   
                }
                
            } catch (SQLException e) {
                System.err.println("Error al obtener datos de los gastos totales" + e);
            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado correctamente");

               
        
        } catch (DocumentException | IOException e) {
            System.err.print("Error en PDF o ruta de imagen " + e);
            JOptionPane.showMessageDialog(null, "Error al generar PDF");   
        }  
    }
            
            
    
    // método de creación PDF de gastos full
    public void pdfGastosFull(){
        Document documento = new Document();

        try {
            //ruta donde se guardará PDF
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + 
                    "/Desktop/" + nombre + apellido + "tablagastoscompleta.pdf"));


            // contenido del PDF. /  imagén de arriba
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/refugio/pose.png");
            header.scaleToFit(100, 100);
            header.setAlignment(Chunk.ALIGN_CENTER);

            // titulo
            Paragraph titulo = new Paragraph();
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            titulo.add("Refucat. \n \n");
            titulo.setFont(FontFactory.getFont("Tahoma", 24, Font.BOLD, BaseColor.BLUE));
            
            // parrafo 1
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Lista completa de gastos del refugio. \n \n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 16, Font.BOLD, BaseColor.MAGENTA));

            // agregar contenido al PDF.
            documento.open();
            documento.add(header);
            documento.add(titulo);
            documento.add(parrafo);
            
            // crear la tabla del PDF.
            PdfPTable tablaGastos = new PdfPTable(6);
            tablaGastos.addCell("Nombre");
            tablaGastos.addCell("ID");
            tablaGastos.addCell("Concepto");
            tablaGastos.addCell("Gasto");
            tablaGastos.addCell("Gasto2");
            tablaGastos.addCell("Gasto3");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement("select NombreGato, "
                    + "IDGato, Concepto, Gasto, Gasto2, Gasto3 from gastos");
             
                ResultSet rs = pst.executeQuery();
                
                // completar información de la tabla
                if(rs.next()){
                    do{
                        tablaGastos.addCell(rs.getString(1));
                        tablaGastos.addCell(rs.getString(2));
                        tablaGastos.addCell(rs.getString(3));
                        tablaGastos.addCell(rs.getString(4));
                        tablaGastos.addCell(rs.getString(5));
                        tablaGastos.addCell(rs.getString(6));
                    }while(rs.next());
                    documento.add(tablaGastos);   
                }
                
            } catch (SQLException e) {
                System.err.println("Error al obtener datos de los gastos " + e);
            }

            // parrafo 2
            Paragraph parrafo2 = new Paragraph();
            parrafo2.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo2.add("Lista completa de gastos totales y faltante a pagar. \n \n");
            parrafo2.setFont(FontFactory.getFont("Tahoma", 16, Font.BOLD, BaseColor.MAGENTA));
            
            documento.add(parrafo2);
            
        // GASTOS TOTALES
            
         // crear la tabla de gastos totales en PDF.
            PdfPTable tablaGastosTotales = new PdfPTable(4);
            tablaGastosTotales.addCell("Mes");
            tablaGastosTotales.addCell("Año");
            tablaGastosTotales.addCell("GastosTotales");
            tablaGastosTotales.addCell("Faltante");

            try {
                Connection cn1 = Conexion.conectar();
                PreparedStatement pst1 = cn1.prepareStatement("select Mes, Año, "
                        + "GastosTotales, Faltante from gastoscubiertos");
             
                ResultSet rs1 = pst1.executeQuery();
                
                // completar información de la tabla
                if(rs1.next()){
                    do{
                        tablaGastosTotales.addCell(rs1.getString(1));
                        tablaGastosTotales.addCell(rs1.getString(2));
                        tablaGastosTotales.addCell(rs1.getString(3));
                        tablaGastosTotales.addCell(rs1.getString(4));

                    }while(rs1.next());
                    documento.add(tablaGastosTotales);   
                }
                
            } catch (SQLException e) {
                System.err.println("Error al obtener datos de los gastos totales" + e);
            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado correctamente");

               
        
        } catch (DocumentException | IOException e) {
            System.err.print("Error en PDF o ruta de imagen " + e);
            JOptionPane.showMessageDialog(null, "Error al generar PDF");   
        }  
    }
    
    
    public void Limpiar(){
        // tabla de registro de gastos
        textNombreGato.setText("");
        textIDGato.setText("");
        cmbConcepto.setSelectedIndex(1);
        textDetalle.setText("");
        textGasto.setText("");
        textGasto2.setText("");
        textGasto3.setText("");
        
        // tabla de gastos totales
        textMes.setMonth(0);
        textAño.setYear(0);
        textGastosTotales.setText("");
        textFaltante.setText("");
}

}

