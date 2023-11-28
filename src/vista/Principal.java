
package vista;

import config.Conexion;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Principal extends javax.swing.JFrame {
    Conexion con=new Conexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    int id = 0;
    
    public Principal() {
        initComponents();
        setResizable(false);//para q no se agrande
        setLocationRelativeTo(null); //para centrar
        Listar();
    }
    //Icono JFrame
    @Override
    public Image getIconImage() {
     Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imagen/logo2.png"));
     return retValue;
    
    }
    
    void Agregar() {
    String producto = txtPRODUCTO.getText();
    String precio = txtPRECIO.getText();
    String stock = txtSTOCK.getText();

    if (producto.isEmpty() || precio.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Completar los datos que faltan.");
    } else {
        String sql = "insert into productos (Producto, Precio, Stock) values ('" + producto + "', '" + precio + "', '" + stock + "')";
        try {
            cn = con.getConnection();
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Producto agregado correctamente.");
            BorrarCampos();
            Listar(); // Actualiza la tabla después de agregar
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el producto: " + e.getMessage());
        }
        
    }
}

void Listar() {
    // Limpia la tabla antes d cargar los datos
    DefaultTableModel modelo = (DefaultTableModel) tbDatos.getModel();
    modelo.setRowCount(0);

    String sql = "select * from productos";
    try {
        cn = con.getConnection();
        st = cn.createStatement();
        rs = st.executeQuery(sql);

        while (rs.next()) {
            Object[] productos = {
                rs.getInt("ID"),
                rs.getString("Producto"),
                rs.getString("Precio"),
                rs.getString("Stock")
            };
            modelo.addRow(productos);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al listar los productos: " + e.getMessage());
    }
}
        
 //para q no se duplique la tabla
    void LimpiarTabla() {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    //limpiar los campos
    void BorrarCampos(){
        txtID.setText("");
        txtPRODUCTO.setText("");
        txtPRECIO.setText("");
        txtSTOCK.setText("");
    }
    //boton modificar
    void Modificar(){
        String producto= txtPRODUCTO.getText();
        String precio= txtPRECIO.getText();
        String stock= txtSTOCK.getText();

        String sql="update productos set Producto='"+producto+"', Precio='"+precio+"', Stock='"+stock+"' where ID="+id;

        if(producto.equals("") || precio.equals("") || stock.equals("")){
            JOptionPane.showMessageDialog(null, "Completar los datos que faltan.");
        }else{
            try {
                cn=con.getConnection();
                st=cn.createStatement();
                st.executeUpdate(sql);
                       JOptionPane.showMessageDialog(null, "El producto se actualizo correctamente.");
                LimpiarTabla(); 
                BorrarCampos();
                Listar();
            } catch (Exception e) {
            }
        }   
    }
    //boton eliminar
    void Eliminar() {
        int seleccion=tbDatos.getSelectedRow();
        if(seleccion==-1){
            JOptionPane.showMessageDialog(null, "Seleccione un producto.");
        }else{
            String sql="delete from productos where ID="+id;
            try {
                cn=con.getConnection();
                st=cn.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "El producto se elimino correctamente.");
                LimpiarTabla();
                BorrarCampos();
                Listar();
            } catch (Exception e){
            }
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

        botonesPN = new javax.swing.JPanel();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        datosPN = new javax.swing.JPanel();
        IDlabel = new javax.swing.JLabel();
        ProductoLabel = new javax.swing.JLabel();
        PrecioLabel = new javax.swing.JLabel();
        StockLabel = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtPRODUCTO = new javax.swing.JTextField();
        txtPRECIO = new javax.swing.JTextField();
        txtSTOCK = new javax.swing.JTextField();
        listaPN = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDatos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sublime");
        setBackground(new java.awt.Color(139, 22, 22));
        setIconImage(getIconImage());

        botonesPN.setBackground(new java.awt.Color(255, 255, 255));
        botonesPN.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnModificar.setBackground(new java.awt.Color(204, 204, 204));
        btnModificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(204, 204, 204));
        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnAgregar.setBackground(new java.awt.Color(204, 204, 204));
        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout botonesPNLayout = new javax.swing.GroupLayout(botonesPN);
        botonesPN.setLayout(botonesPNLayout);
        botonesPNLayout.setHorizontalGroup(
            botonesPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, botonesPNLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addGap(37, 37, 37))
        );
        botonesPNLayout.setVerticalGroup(
            botonesPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botonesPNLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(botonesPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        datosPN.setBackground(new java.awt.Color(255, 255, 255));
        datosPN.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        IDlabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        IDlabel.setText("ID");

        ProductoLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ProductoLabel.setText("Producto");

        PrecioLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PrecioLabel.setText("Precio");

        StockLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        StockLabel.setText("Stock");

        txtID.setEditable(false);
        txtID.setBackground(new java.awt.Color(204, 204, 204));
        txtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtID.setSelectionColor(new java.awt.Color(0, 0, 0));

        txtPRODUCTO.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtPRECIO.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtSTOCK.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout datosPNLayout = new javax.swing.GroupLayout(datosPN);
        datosPN.setLayout(datosPNLayout);
        datosPNLayout.setHorizontalGroup(
            datosPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosPNLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(datosPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IDlabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ProductoLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PrecioLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(StockLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(48, 48, 48)
                .addGroup(datosPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtPRODUCTO, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(txtID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPRECIO, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSTOCK))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        datosPNLayout.setVerticalGroup(
            datosPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosPNLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(datosPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDlabel)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(datosPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProductoLabel)
                    .addComponent(txtPRODUCTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(datosPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PrecioLabel)
                    .addComponent(txtPRECIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(datosPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSTOCK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StockLabel))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        listaPN.setBackground(new java.awt.Color(255, 255, 255));
        listaPN.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbDatos.setBackground(new java.awt.Color(255, 255, 255));
        tbDatos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbDatos.setForeground(new java.awt.Color(0, 0, 0));
        tbDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "PRODUCTO", "PRECIO", "STOCK"
            }
        ));
        tbDatos.setSelectionBackground(new java.awt.Color(153, 0, 0));
        tbDatos.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tbDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDatos);
        if (tbDatos.getColumnModel().getColumnCount() > 0) {
            tbDatos.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbDatos.getColumnModel().getColumn(1).setPreferredWidth(400);
            tbDatos.getColumnModel().getColumn(2).setPreferredWidth(100);
            tbDatos.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        javax.swing.GroupLayout listaPNLayout = new javax.swing.GroupLayout(listaPN);
        listaPN.setLayout(listaPNLayout);
        listaPNLayout.setHorizontalGroup(
            listaPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
        );
        listaPNLayout.setVerticalGroup(
            listaPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listaPNLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonesPN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(datosPN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listaPN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(listaPN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(datosPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonesPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Modificar();
        Listar();
        BorrarCampos();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Eliminar();
        Listar();
        BorrarCampos();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
         Agregar();
         Listar();
         BorrarCampos();
    
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tbDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDatosMouseClicked
        int fila=tbDatos.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "Seleccione un producto.");
        }else{
                id=Integer.parseInt((String)tbDatos.getValueAt(fila, 0).toString());
                String producto=(String)tbDatos.getValueAt(fila, 1);
                String precio=(String)tbDatos.getValueAt(fila, 2);
                String stock=(String)tbDatos.getValueAt(fila, 3);
                txtID.setText(""+id);
                txtPRODUCTO.setText(""+producto);
                txtPRECIO.setText(""+precio);
                txtSTOCK.setText(""+stock);
        }
    }//GEN-LAST:event_tbDatosMouseClicked

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IDlabel;
    private javax.swing.JLabel PrecioLabel;
    private javax.swing.JLabel ProductoLabel;
    private javax.swing.JLabel StockLabel;
    private javax.swing.JPanel botonesPN;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JPanel datosPN;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel listaPN;
    private javax.swing.JTable tbDatos;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtPRECIO;
    private javax.swing.JTextField txtPRODUCTO;
    private javax.swing.JTextField txtSTOCK;
    // End of variables declaration//GEN-END:variables

    private void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
