/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import database.Koneksi;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class Supplier extends javax.swing.JInternalFrame {
    
    ResultSet resultSet;
    Koneksi connection;
    /**
     * Creates new form Supplier
     */
    public Supplier() {
        connection = new Koneksi();
        initComponents();
        getTabel();
    }
    
    public final void getTabel(){
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("ID");
        model.addColumn("Nama Supplier");
        model.addColumn("Alamat");
        model.addColumn("No Telepon");
        model.addColumn("Keterangan");
        String[] namaKolom = {"id_supplier", "nama",
          "alamat", "no_hp", "keterangan"};
        
        try{
            int no = 1;
            resultSet = connection.querySellect(namaKolom, "supplier");
            
            while(resultSet.next()){
                model.addRow(new Object[]{
                no++,
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5)
                });
            }
            
            connection.closeDatabase();
            tblSupplier.setModel(model);
        }catch(SQLException e){
            System.out.println("Error table supplier" + e.getMessage());
        }
      
      
    }
    
    public void refreshAll(){
        tfNama.setText(null);
        tfAlamat.setText(null);
        tfNoTelepon.setText(null);
        tfKeterangan.setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfNama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfNoTelepon = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfAlamat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfKeterangan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSupplier = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Supplier");

        jLabel2.setText("Nama");

        jLabel3.setText("No Telepon");

        tfNoTelepon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNoTeleponActionPerformed(evt);
            }
        });

        jLabel4.setText("Alamat");

        jLabel5.setText("Keterangan");

        tblSupplier.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSupplierMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSupplier);

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(108, 108, 108))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(tfNama)
                                            .addGap(37, 37, 37)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfNoTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                    .addComponent(tfAlamat)
                                    .addComponent(tfKeterangan))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNoTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(118, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNoTeleponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNoTeleponActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNoTeleponActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        
        String nama = tfNama.getText();
        String alamat = tfAlamat.getText();
        String noTelepon = tfNoTelepon.getText();
        String keterangan = tfKeterangan.getText();
        
        if(nama.isEmpty() || alamat.isEmpty() 
                || noTelepon.isEmpty() || keterangan.isEmpty()){
            
            JOptionPane.showMessageDialog(this, "Data masih belum lengkap");
            
        }else{
            
            String[] column = {"nama", "alamat", "no_hp", "keterangan"};
            String[] value = {nama, alamat, noTelepon, keterangan};
            String nameTable = "supplier";
            
            connection.queryInsert(nameTable, column, value);
            connection.closeDatabase();
            
            getTabel();
            refreshAll();
            
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        
        String nama = tfNama.getText();
        String alamat = tfAlamat.getText();
        String noTelepon = tfNoTelepon.getText();
        String keterangan = tfKeterangan.getText();
        
         if(nama.isEmpty() || alamat.isEmpty() 
                || noTelepon.isEmpty() || keterangan.isEmpty()){
            
            JOptionPane.showMessageDialog(this, "Data masih belum lengkap");
            
        }else{
            
            int baris = tblSupplier.getSelectedRow();
            int kolom = 1;
             
            String id = String.valueOf(tblSupplier.getValueAt(baris, kolom));
            String[] column = {"nama", "alamat", "no_hp", "keterangan"};
            String[] value = {nama, alamat, noTelepon, keterangan};
            String nameTable = "supplier";
            String condition = "id_supplier = " + id;
            
            connection.queryUppdate(nameTable, column, value, condition);
            connection.closeDatabase();
            
            getTabel();
            refreshAll();
            
        }
        
    }//GEN-LAST:event_btnEditActionPerformed

    private void tblSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSupplierMouseClicked
        
        int baris = tblSupplier.getSelectedRow();
        
        String nama = String.valueOf(tblSupplier.getValueAt(baris, 2));
        String alamat = String.valueOf(tblSupplier.getValueAt(baris, 3));
        String noTelepon = String.valueOf(tblSupplier.getValueAt(baris, 4));
        String Keterangan = String.valueOf(tblSupplier.getValueAt(baris, 5));
        
        tfNama.setText(nama);
        tfNoTelepon.setText(noTelepon);
        tfKeterangan.setText(Keterangan);
        tfAlamat.setText(alamat);
        
    }//GEN-LAST:event_tblSupplierMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        
        int baris = tblSupplier.getSelectedRow();
        int kolom = 1;
        
        String id = String.valueOf(tblSupplier.getValueAt(baris, kolom));
        
        if(JOptionPane.showConfirmDialog(
                this,
                "Apakah anda yakin ingin menghapus data ini",
                "Peringatan!!!",
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
            
            connection.queryDelete("supplier", "id_supplier = " + id);
            connection.closeDatabase();
            
        }else{
            
            return;
            
        }
         
        getTabel();
        JOptionPane.showMessageDialog(this,"Data Berhasil di hapus");
        refreshAll();
         
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSupplier;
    private javax.swing.JTextField tfAlamat;
    private javax.swing.JTextField tfKeterangan;
    private javax.swing.JTextField tfNama;
    private javax.swing.JTextField tfNoTelepon;
    // End of variables declaration//GEN-END:variables
}
