package Diapositivas;

import javax.swing.ImageIcon;

import java.sql.*;
import Atajos.Conexion;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;

import javax.swing.JOptionPane;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Atajos.Salir;

/**
 *
 * @author Daniela
 */
public class Familias extends javax.swing.JFrame {

    String usuario;
    public static int sesionUsuario;
    /**
     * Creates new form Familias
     */
    public Familias() {
        initComponents();
        setSize(900, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        
        usuario = Login.usuario;
        sesionUsuario = 1;
        
        setTitle("Familias - " + usuario);
        
        ImageIcon wallpaper = new ImageIcon("src/refugio/luces-azules.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabelWallpaper.getHeight(), jLabelWallpaper.getWidth(), Image.SCALE_DEFAULT));
        jLabelWallpaper.setIcon(icono);
        this.repaint();
        
        
        // paneles visibilidad
        jPanelNormal.setVisible(true);
        jPanelRegistro.setVisible(false);
        jPanelVerFamilias.setVisible(false);
        jPanelSeguimiento.setVisible(false);
        
    }

    public Image getLogo(){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("refugio/loguito.png"));
        return retValue;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTituloCentral = new javax.swing.JLabel();
        jLabelGatoColgando = new javax.swing.JLabel();
        jPanelMenu = new javax.swing.JPanel();
        jButtonRegistrarGatoFamilia = new javax.swing.JButton();
        jButtonVerFamiliasRegistradas = new javax.swing.JButton();
        jButtonSeguimiento = new javax.swing.JButton();
        jButtonInfo = new javax.swing.JButton();
        jButtonVolverAtras = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();
        jPanelNormal = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelGato1 = new javax.swing.JLabel();
        jPanelRegistro = new javax.swing.JPanel();
        jLabelTitulo1 = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        textNombreFamilia = new javax.swing.JTextField();
        jLabelNombreGato = new javax.swing.JLabel();
        textNombreNuevoGato = new javax.swing.JTextField();
        jLabelFechaIngreso = new javax.swing.JLabel();
        jLabelDireccion = new javax.swing.JLabel();
        textDireccion = new javax.swing.JTextField();
        jButtonRegistrarFamilia = new javax.swing.JButton();
        jLabelImagen = new javax.swing.JLabel();
        jLabelNombreAnterior = new javax.swing.JLabel();
        textNombreIngreso = new javax.swing.JTextField();
        jLabelIDIngreso = new javax.swing.JLabel();
        textIDRegistro = new javax.swing.JTextField();
        jDateIngresoHogar = new com.toedter.calendar.JDateChooser();
        jButtonVerNombre = new javax.swing.JButton();
        jPanelVerFamilias = new javax.swing.JPanel();
        jLabelTitulo2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFamilias = new javax.swing.JTable();
        jPanelSeguimiento = new javax.swing.JPanel();
        jLabelTitulo3 = new javax.swing.JLabel();
        jLabelNombreF = new javax.swing.JLabel();
        textFamilia = new javax.swing.JTextField();
        jLabelNombreN = new javax.swing.JLabel();
        textNuevo = new javax.swing.JTextField();
        jLabelControl = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textControl = new javax.swing.JTextArea();
        jLabelAdaptacion = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textAdaptacion = new javax.swing.JTextArea();
        jButtonSubirControl = new javax.swing.JButton();
        jLabelID = new javax.swing.JLabel();
        textIDFamilia = new javax.swing.JTextField();
        jButtonListaSeg = new javax.swing.JButton();
        jLabelWallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getLogo());
        setIconImages(getIconImages());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTituloCentral.setFont(new java.awt.Font("Yu Gothic UI", 0, 36)); // NOI18N
        jLabelTituloCentral.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloCentral.setText("Familias");
        getContentPane().add(jLabelTituloCentral, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 410, 110));

        jLabelGatoColgando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/refugio/sonrisa1.png"))); // NOI18N
        getContentPane().add(jLabelGatoColgando, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, -30, 310, 250));

        jPanelMenu.setBackground(new java.awt.Color(0, 102, 153));
        jPanelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonRegistrarGatoFamilia.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 16)); // NOI18N
        jButtonRegistrarGatoFamilia.setText("Registrar gato en familia.");
        jButtonRegistrarGatoFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarGatoFamiliaActionPerformed(evt);
            }
        });
        jPanelMenu.add(jButtonRegistrarGatoFamilia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 210, 50));

        jButtonVerFamiliasRegistradas.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 16)); // NOI18N
        jButtonVerFamiliasRegistradas.setText("Ver familias registradas.");
        jButtonVerFamiliasRegistradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerFamiliasRegistradasActionPerformed(evt);
            }
        });
        jPanelMenu.add(jButtonVerFamiliasRegistradas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 210, 50));

        jButtonSeguimiento.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 16)); // NOI18N
        jButtonSeguimiento.setText("Control familias y cuidados");
        jButtonSeguimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeguimientoActionPerformed(evt);
            }
        });
        jPanelMenu.add(jButtonSeguimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 210, 50));

        jButtonInfo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 16)); // NOI18N
        jButtonInfo.setText("Información familia.");
        jButtonInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInfoActionPerformed(evt);
            }
        });
        jPanelMenu.add(jButtonInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 210, 50));

        jButtonVolverAtras.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 16)); // NOI18N
        jButtonVolverAtras.setText("Volver atrás.");
        jButtonVolverAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverAtrasActionPerformed(evt);
            }
        });
        jPanelMenu.add(jButtonVolverAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 210, 50));

        jButtonSalir.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 16)); // NOI18N
        jButtonSalir.setText("Salir.");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        jPanelMenu.add(jButtonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 210, 50));

        getContentPane().add(jPanelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 230, 500));

        jPanelNormal.setBackground(new java.awt.Color(0, 51, 102));
        jPanelNormal.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelNormal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitulo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitulo.setText("Por favor, elige una opción...");
        jPanelNormal.add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 490, 110));

        jLabelGato1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/refugio/atigrado1.png"))); // NOI18N
        jPanelNormal.add(jLabelGato1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 240, 260));

        getContentPane().add(jPanelNormal, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 650, 500));

        jPanelRegistro.setBackground(new java.awt.Color(0, 51, 102));
        jPanelRegistro.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelRegistro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitulo1.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
        jLabelTitulo1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitulo1.setText("Registro de familias adoptantes.");
        jPanelRegistro.add(jLabelTitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 410, 110));

        jLabelNombre.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabelNombre.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNombre.setText("Nombre de la familia adoptante:");
        jPanelRegistro.add(jLabelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        textNombreFamilia.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        textNombreFamilia.setForeground(new java.awt.Color(0, 102, 153));
        jPanelRegistro.add(textNombreFamilia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 200, 30));

        jLabelNombreGato.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabelNombreGato.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNombreGato.setText("Nombre nuevo del gato:");
        jPanelRegistro.add(jLabelNombreGato, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        textNombreNuevoGato.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        textNombreNuevoGato.setForeground(new java.awt.Color(0, 102, 153));
        jPanelRegistro.add(textNombreNuevoGato, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 200, 30));

        jLabelFechaIngreso.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabelFechaIngreso.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFechaIngreso.setText("Fecha de ingreso al hogar:");
        jPanelRegistro.add(jLabelFechaIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jLabelDireccion.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabelDireccion.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDireccion.setText("Dirección del hogar:");
        jPanelRegistro.add(jLabelDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        textDireccion.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        textDireccion.setForeground(new java.awt.Color(0, 102, 153));
        jPanelRegistro.add(textDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 200, 30));

        jButtonRegistrarFamilia.setBackground(new java.awt.Color(102, 102, 255));
        jButtonRegistrarFamilia.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        jButtonRegistrarFamilia.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRegistrarFamilia.setText("Registrar nueva salida");
        jButtonRegistrarFamilia.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonRegistrarFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarFamiliaActionPerformed(evt);
            }
        });
        jPanelRegistro.add(jButtonRegistrarFamilia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 200, 100));

        jLabelImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/refugio/familiaGato.png"))); // NOI18N
        jPanelRegistro.add(jLabelImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, 200, 230));

        jLabelNombreAnterior.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabelNombreAnterior.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNombreAnterior.setText("Nombre del gato en el refugio:");
        jPanelRegistro.add(jLabelNombreAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, -1, -1));

        textNombreIngreso.setEditable(false);
        textNombreIngreso.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        textNombreIngreso.setForeground(new java.awt.Color(0, 102, 153));
        jPanelRegistro.add(textNombreIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, 200, 30));

        jLabelIDIngreso.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabelIDIngreso.setForeground(new java.awt.Color(255, 255, 255));
        jLabelIDIngreso.setText("ID con el que fue registrado el gato:");
        jPanelRegistro.add(jLabelIDIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 160, -1, -1));

        textIDRegistro.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        textIDRegistro.setForeground(new java.awt.Color(0, 102, 153));
        jPanelRegistro.add(textIDRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 200, 30));

        jDateIngresoHogar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jDateIngresoHogar.setDateFormatString("yyyy/MM/dd HH:mm:ss\n");
        jPanelRegistro.add(jDateIngresoHogar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 200, 30));

        jButtonVerNombre.setBackground(new java.awt.Color(102, 102, 255));
        jButtonVerNombre.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        jButtonVerNombre.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVerNombre.setText("Relacionar ID con nombre");
        jButtonVerNombre.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonVerNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerNombreActionPerformed(evt);
            }
        });
        jPanelRegistro.add(jButtonVerNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 200, 40));

        getContentPane().add(jPanelRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 650, 500));

        jPanelVerFamilias.setBackground(new java.awt.Color(0, 51, 102));
        jPanelVerFamilias.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelVerFamilias.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitulo2.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
        jLabelTitulo2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitulo2.setText("Familias registradas");
        jPanelVerFamilias.add(jLabelTitulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, -10, 410, 110));

        jTableFamilias.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTableFamilias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID familia", "Familia", "Nombre", "Fecha Ingreso", "Direccion", "ID Gato"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
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
        jScrollPane1.setViewportView(jTableFamilias);

        jPanelVerFamilias.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 630, 380));

        getContentPane().add(jPanelVerFamilias, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 650, 500));

        jPanelSeguimiento.setBackground(new java.awt.Color(0, 51, 102));
        jPanelSeguimiento.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelSeguimiento.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitulo3.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
        jLabelTitulo3.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitulo3.setText("Seguimiento y control ");
        jPanelSeguimiento.add(jLabelTitulo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, -10, 410, 110));

        jLabelNombreF.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabelNombreF.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNombreF.setText("Familia:");
        jPanelSeguimiento.add(jLabelNombreF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        textFamilia.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        textFamilia.setForeground(new java.awt.Color(0, 102, 153));
        jPanelSeguimiento.add(textFamilia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 200, 30));

        jLabelNombreN.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabelNombreN.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNombreN.setText("Nombre nuevo del gato:");
        jPanelSeguimiento.add(jLabelNombreN, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        textNuevo.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        textNuevo.setForeground(new java.awt.Color(0, 102, 153));
        jPanelSeguimiento.add(textNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 200, 30));

        jLabelControl.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabelControl.setForeground(new java.awt.Color(255, 255, 255));
        jLabelControl.setText("Control veterinario:");
        jPanelSeguimiento.add(jLabelControl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jScrollPane2.setForeground(new java.awt.Color(0, 102, 204));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        textControl.setColumns(20);
        textControl.setForeground(new java.awt.Color(0, 102, 153));
        textControl.setRows(5);
        jScrollPane2.setViewportView(textControl);

        jPanelSeguimiento.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 280, 220));

        jLabelAdaptacion.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabelAdaptacion.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAdaptacion.setText("Adaptacion al hogar:");
        jPanelSeguimiento.add(jLabelAdaptacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, -1, -1));

        jScrollPane3.setForeground(new java.awt.Color(0, 102, 204));
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        textAdaptacion.setColumns(20);
        textAdaptacion.setForeground(new java.awt.Color(0, 102, 153));
        textAdaptacion.setRows(5);
        jScrollPane3.setViewportView(textAdaptacion);

        jPanelSeguimiento.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 240, 200));

        jButtonSubirControl.setBackground(new java.awt.Color(102, 102, 255));
        jButtonSubirControl.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        jButtonSubirControl.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSubirControl.setText("Subir nuevo control");
        jButtonSubirControl.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonSubirControl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubirControlActionPerformed(evt);
            }
        });
        jPanelSeguimiento.add(jButtonSubirControl, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 400, 200, 40));

        jLabelID.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabelID.setForeground(new java.awt.Color(255, 255, 255));
        jLabelID.setText("ID Familia:");
        jPanelSeguimiento.add(jLabelID, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, -1, -1));

        textIDFamilia.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        textIDFamilia.setForeground(new java.awt.Color(0, 102, 153));
        jPanelSeguimiento.add(textIDFamilia, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 60, 30));

        jButtonListaSeg.setBackground(new java.awt.Color(102, 102, 255));
        jButtonListaSeg.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        jButtonListaSeg.setForeground(new java.awt.Color(255, 255, 255));
        jButtonListaSeg.setText("Lista de seguimientos");
        jButtonListaSeg.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonListaSeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListaSegActionPerformed(evt);
            }
        });
        jPanelSeguimiento.add(jButtonListaSeg, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 340, 200, 40));

        getContentPane().add(jPanelSeguimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 650, 500));

        jLabelWallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/refugio/luces-azules.jpg"))); // NOI18N
        getContentPane().add(jLabelWallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, -20, 930, 900));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRegistrarGatoFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarGatoFamiliaActionPerformed
       
        // cambio panel visible
        jPanelNormal.setVisible(false);
        jPanelRegistro.setVisible(true);
        jPanelVerFamilias.setVisible(false);
        jPanelSeguimiento.setVisible(false);

    }//GEN-LAST:event_jButtonRegistrarGatoFamiliaActionPerformed

    private void jButtonVerFamiliasRegistradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerFamiliasRegistradasActionPerformed
       
        // cambio panel visible
        jPanelNormal.setVisible(false);
        jPanelRegistro.setVisible(false);
        jPanelVerFamilias.setVisible(true);
        jPanelSeguimiento.setVisible(false);

        // creación de tabla para mostrar familias
        DefaultTableModel modelo = new DefaultTableModel();
        
        try{
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select * from adopciones");
            ResultSet rs = pst.executeQuery();

            jTableFamilias = new JTable (modelo);
            jScrollPane1.setViewportView(jTableFamilias);

            modelo.addColumn("ID familia");
            modelo.addColumn("Familia");
            modelo.addColumn("Nombre nuevo");
            modelo.addColumn("Fecha ingreso");
            modelo.addColumn("Direccion");
            modelo.addColumn("ID Gato");
                
            while(rs.next()){
                Object [] fila = new Object[6];
                for(int i = 0; i <6; i++){
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            cn.close();
                
        }catch(SQLException e){
            System.err.println("No se pueden cargar las familias " + e);
            JOptionPane.showMessageDialog(null, "No se pudo cargar los datos "
                    + "correspondientes a las familias registradas");
        }
    }//GEN-LAST:event_jButtonVerFamiliasRegistradasActionPerformed

    private void jButtonVolverAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverAtrasActionPerformed
        
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jButtonVolverAtrasActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
      
        Salir salir = new Salir();
        salir.botonCerrar();
        
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jButtonRegistrarFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarFamiliaActionPerformed

        // declaración variables
        int avanzar = 0;
        String nombreFamilia, nombreNuevo, fechaIngreso, direccion, ID;
      
        nombreFamilia = textNombreFamilia.getText().trim();
        nombreNuevo = textNombreNuevoGato.getText().trim();
        fechaIngreso = ((JTextField) jDateIngresoHogar.getDateEditor().getUiComponent()).getText();
        direccion = textDireccion.getText().trim();
        ID = textIDRegistro.getText().trim();
        
        
        // verificación de que no haya campos sin completador.
        if(nombreFamilia.equals("")){
            textNombreFamilia.setBackground(Color.red);
            avanzar++;
        }
         if(nombreNuevo.equals("")){
            textNombreNuevoGato.setBackground(Color.red);
            avanzar++;
        }
          if(fechaIngreso.equals("")){
            jDateIngresoHogar.setBackground(Color.red);
            avanzar++;
        }
           if(direccion.equals("")){
            textDireccion.setBackground(Color.red);
            avanzar++;
        }
            if(ID.equals("")){
                textIDRegistro.setBackground(Color.red);
                avanzar++;
            }
  
            // conexión a la base si se completaron todos los campos
            if(avanzar == 0){
                
                try{
                    Connection cn = Conexion.conectar();
                    PreparedStatement pst = cn.prepareStatement("insert into adopciones values (?, ?, ?, ?, ?, ?)");
                    
                    pst.setInt(1, 0);
                    pst.setString(2, nombreFamilia);
                    pst.setString(3, nombreNuevo);
                    pst.setString(4, fechaIngreso);
                    pst.setString(5, direccion);
                    pst.setString(6, ID);
                    
                    pst.executeUpdate();

                    cn.close();
                    LimpiarCampos();
                    
                    JOptionPane.showMessageDialog(null, "Registro exitoso");
                    
                }catch(SQLException e){
                    System.err.println("No hemos podido registrar los datos " + e);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Es necesario que completes"
                        + "todos los campos");
            }
    }//GEN-LAST:event_jButtonRegistrarFamiliaActionPerformed

    private void jButtonVerNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerNombreActionPerformed

        String ID;
        
        ID = textIDRegistro.getText().trim();
        
        // si esta vacio se pone en rojo.
        if(ID.equals("")){
            textIDRegistro.setBackground(Color.red);

            // si esta completo, se muestra el nombre con el que se registro.
        } else {
                    try{
                        Connection cn1 = Conexion.conectar();
                        PreparedStatement pst1 = cn1.prepareStatement("select NombreGato from gatitos where IDGato = '" + ID + "'");
                        ResultSet rs1 = pst1.executeQuery();
                        
                        if(rs1.next()){
                            textNombreIngreso.setText(rs1.getString("NombreGato"));
                        }
                        
                        cn1.close();
                    }catch(SQLException e1){
                        System.err.println("No se pudo recuperar el nombre " + e1);
                        JOptionPane.showMessageDialog(null, "No se pudo recuperar "
                                + "el nombre de registro del gato");
                    }
        }
    }//GEN-LAST:event_jButtonVerNombreActionPerformed

    private void jButtonInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInfoActionPerformed
       
       InformacionFamilia infoFamilia = new InformacionFamilia();
       infoFamilia.setVisible(true);
       this.dispose();
        
    }//GEN-LAST:event_jButtonInfoActionPerformed

    private void jButtonSeguimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeguimientoActionPerformed

        // cambio panel visible
        jPanelNormal.setVisible(false);
        jPanelRegistro.setVisible(false);
        jPanelVerFamilias.setVisible(false);
        jPanelSeguimiento.setVisible(true);
        
    }//GEN-LAST:event_jButtonSeguimientoActionPerformed

    private void jButtonSubirControlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubirControlActionPerformed
      
        String familia, nombre, IDFam, control, adaptacion;
        int validar = 0;
        
        familia = textFamilia.getText().trim();
        nombre = textNuevo.getText().trim();
        IDFam = textIDFamilia.getText().trim();
        control = textControl.getText().trim();
        adaptacion = textAdaptacion.getText().trim();
        
        // verificación de que no haya campos vacios
        if(familia.equals("")){
            textFamilia.setBackground(Color.red);
            validar++;
        }
        if(nombre.equals("")){
            textNuevo.setBackground(Color.red);
            validar++;
        }
        if(IDFam.equals("")){
            textIDFamilia.setBackground(Color.red);
            validar++;
        }
        if(control.equals("")){
            textControl.setBackground(Color.red);
            validar++;
        }
        if(adaptacion.equals("")){
            textAdaptacion.setBackground(Color.red);
            validar++;
        }
        
        if(validar == 0){
            
            try{
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement("insert into seguimiento "
                        + "values (?, ?, ?, ?, ?)");
                
                pst.setString(1, familia);
                pst.setString(2, nombre);
                pst.setString(3, IDFam);
                pst.setString(4, control);
                pst.setString(5, adaptacion);
                
                pst.executeQuery();
                cn.close();
                JOptionPane.showMessageDialog(null, "Registro exitoso");
                
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "No se pudo registrar el "
                        + "seguimiento del estado del gato adoptado");
                System.err.println("Error al registrar el seguimiento " + e);
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Es necesario que completes todos "
                    + "los campos solicitados");
        }
        
    }//GEN-LAST:event_jButtonSubirControlActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       
        Salir salir = new Salir();
        salir.botonCerrar();
        
    }//GEN-LAST:event_formWindowClosing

    private void jButtonListaSegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListaSegActionPerformed
        
        VerSeguimiento seguimiento = new VerSeguimiento();
        seguimiento.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jButtonListaSegActionPerformed

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
            java.util.logging.Logger.getLogger(Familias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Familias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Familias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Familias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Familias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonInfo;
    private javax.swing.JButton jButtonListaSeg;
    private javax.swing.JButton jButtonRegistrarFamilia;
    private javax.swing.JButton jButtonRegistrarGatoFamilia;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JButton jButtonSeguimiento;
    private javax.swing.JButton jButtonSubirControl;
    private javax.swing.JButton jButtonVerFamiliasRegistradas;
    private javax.swing.JButton jButtonVerNombre;
    private javax.swing.JButton jButtonVolverAtras;
    private com.toedter.calendar.JDateChooser jDateIngresoHogar;
    private javax.swing.JLabel jLabelAdaptacion;
    private javax.swing.JLabel jLabelControl;
    private javax.swing.JLabel jLabelDireccion;
    private javax.swing.JLabel jLabelFechaIngreso;
    private javax.swing.JLabel jLabelGato1;
    private javax.swing.JLabel jLabelGatoColgando;
    private javax.swing.JLabel jLabelID;
    private javax.swing.JLabel jLabelIDIngreso;
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelNombreAnterior;
    private javax.swing.JLabel jLabelNombreF;
    private javax.swing.JLabel jLabelNombreGato;
    private javax.swing.JLabel jLabelNombreN;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelTitulo1;
    private javax.swing.JLabel jLabelTitulo2;
    private javax.swing.JLabel jLabelTitulo3;
    private javax.swing.JLabel jLabelTituloCentral;
    private javax.swing.JLabel jLabelWallpaper;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelNormal;
    private javax.swing.JPanel jPanelRegistro;
    private javax.swing.JPanel jPanelSeguimiento;
    private javax.swing.JPanel jPanelVerFamilias;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableFamilias;
    private javax.swing.JTextArea textAdaptacion;
    private javax.swing.JTextArea textControl;
    private javax.swing.JTextField textDireccion;
    private javax.swing.JTextField textFamilia;
    private javax.swing.JTextField textIDFamilia;
    private javax.swing.JTextField textIDRegistro;
    private javax.swing.JTextField textNombreFamilia;
    private javax.swing.JTextField textNombreIngreso;
    private javax.swing.JTextField textNombreNuevoGato;
    private javax.swing.JTextField textNuevo;
    // End of variables declaration//GEN-END:variables

    public void LimpiarCampos(){
        textNombreFamilia.setText("");
        textNombreNuevoGato.setText("");
        jDateIngresoHogar.setDate(null);
        textDireccion.setText("");
        textIDRegistro.setText("");
    }
}
