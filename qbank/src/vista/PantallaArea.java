/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author richard
 */
public class PantallaArea extends javax.swing.JInternalFrame {

    /**
     * Creates new form PantallaArea
     */
    public PantallaArea() {
        initComponents();
    }
    
    public PantallaArea(String arg1, boolean arg2, boolean arg3, boolean arg4, boolean arg5) {
        super(arg1,arg2,arg3,arg4,arg5);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblPreguntas = new javax.swing.JLabel();
        txtPreguntas = new javax.swing.JTextField();
        bttnNuevo = new javax.swing.JButton();
        bttnGuardar = new javax.swing.JButton();
        bttnEliminar = new javax.swing.JButton();
        bttnModificar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        DatosArea = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setText("Nombre:");
        getContentPane().add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, -1));
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 150, 30));

        lblPreguntas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblPreguntas.setForeground(new java.awt.Color(255, 255, 255));
        lblPreguntas.setText("Preguntas:");
        getContentPane().add(lblPreguntas, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));
        getContentPane().add(txtPreguntas, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 150, 30));

        bttnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/1479709665_New.png"))); // NOI18N
        bttnNuevo.setText("Nuevo");
        getContentPane().add(bttnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, 100, 30));

        bttnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/1479702704_Save.png"))); // NOI18N
        bttnGuardar.setText("Guardar");
        getContentPane().add(bttnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 100, 30));

        bttnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/1479702714_Remove.png"))); // NOI18N
        bttnEliminar.setText("Eliminar");
        getContentPane().add(bttnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 150, 100, 30));

        bttnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/1479702744_Preview.png"))); // NOI18N
        bttnModificar.setText("Modificar");
        bttnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(bttnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 190, -1, -1));

        DatosArea.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(DatosArea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 610, 100));

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Administracion de Area");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 300, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo pantalla.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bttnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnModificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bttnModificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTable DatosArea;
    public javax.swing.JButton bttnEliminar;
    public javax.swing.JButton bttnGuardar;
    public javax.swing.JButton bttnModificar;
    public javax.swing.JButton bttnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblNombre;
    public javax.swing.JLabel lblPreguntas;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtPreguntas;
    // End of variables declaration//GEN-END:variables
}
