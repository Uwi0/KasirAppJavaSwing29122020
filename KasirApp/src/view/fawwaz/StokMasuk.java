/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.fawwaz;

import database.Koneksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Mojave
 */
public class StokMasuk extends javax.swing.JInternalFrame {
    
    int jumlahStokSebelumDiEdit = 0;
    Koneksi connection;
    ResultSet resultSet;
    /**
     * Creates new form kategori_produk
     */
    public StokMasuk() {
        
        connection = new Koneksi();
        initComponents();
        getTable();
        populateComboBoxBarcode();
        
    }
    
    private void populateComboBoxBarcode(){
        
        ArrayList barcode;
        barcode = new ArrayList<String>();
        String query = "SELECT barcode FROM `databarang`";
        
        resultSet = connection.eksekusiQuery(query);
        
        try{
            while(resultSet.next()){
                barcode.add(resultSet.getString(1));
            }
        }catch(SQLException e){
            System.out.println("Error try to populate combobox barcode" 
                    + e.getMessage());
        }
        
        connection.closeDatabase();
        barcode.forEach((i) -> cbxBarcode.addItem(i.toString()));
    }
    
    private void getTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("ID");
        model.addColumn("Tanggal");
        model.addColumn("Nomor Barcode");
        model.addColumn("Jumlah");
        model.addColumn("Keterangan");
        
        String[] column = {
            "no_id",
            "tanggal_masuk",
            "barcode",
            "jumlah",
            "keterangan"
        };
        String tableName = "stokmasuk";
        
        resultSet = connection.querySellect(column, tableName);
        
        try{
            int i = 1;
            
            while(resultSet.next()){
                model.addRow(new Object[]{
                    i++,
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
                });
            }
            
            connection.closeDatabase();
            tblStokmasuk.setModel(model);
        }catch(SQLException e){
            System.out.println("Error try to populate table Stok Masuk :" 
                    + e.getMessage());
        }
    }
    
    private void getRefresh(){
        dcTanggal.setDate(null);
        cbxBarcode.setSelectedItem("pilih");
        tfJumlah.setText(null);
        tfKeterangan.setText(null);
    }
    
    public void closeDatabase(){
        connection.closeDatabase();
    }
    
    private void addStockToTableStock(){
        
        String barcodeValue = cbxBarcode.getSelectedItem().toString();
        
        String tableName = "databarang";
        String[] column = {"No_id", "stok"};
        String condition = "barcode = " + barcodeValue;
        
        resultSet = connection.selectCommand(column, tableName, condition);
        
        String idBarang = "";
        String stokBarang = "";
        try{
            
            while(resultSet.next()){
                idBarang = resultSet.getString(1);
                stokBarang = resultSet.getString(2);
            }
            
            connection.closeDatabase();
        }catch(SQLException e){
            System.out.println("Error try to get value from table user : " + 
                    e.getMessage());
        }
        
        int jumlahStokMasuk = Integer.parseInt(tfJumlah.getText());
        int jumlahStokBaru = Integer.parseInt(stokBarang) + jumlahStokMasuk;
        
        String query = "UPDATE `databarang` SET stok = " + jumlahStokBaru 
                + " WHERE No_id = " + idBarang;
        
        connection.eksekusiUpdate(query);
        connection.closeDatabase();
    }
    
    
    private void editStockFromTableStock(){
        
        String barcode = cbxBarcode.getSelectedItem().toString();
        
        String tableName = "databarang";
        String[] column = {"No_id", "stok"};
        String condition = "barcode = " + barcode;
        
        resultSet = connection.selectCommand(column, tableName, condition);
        
        String idBarang = "";
        String stokBarang = "";
        
        try{
            
            while(resultSet.next()){
                idBarang = resultSet.getString(1);
                stokBarang = resultSet.getString(2);
            }
            
            connection.closeDatabase();
        }catch(SQLException e){
            System.out.println("Error try to get value from table user : " + 
                    e.getMessage());
        }
        
        int stockLama = jumlahStokSebelumDiEdit;
        int stokSaatIni = Integer.parseInt(stokBarang);
        int stokBaru = Integer.parseInt(tfJumlah.getText());
        
        int stokUpdate = (stokSaatIni - stockLama) + stokBaru;
        
        String query = "UPDATE `databarang` SET stok = " + stokUpdate
                + " WHERE No_id = " + idBarang;
        
        connection.eksekusiUpdate(query);
        connection.closeDatabase();
    }
    
    private void deleteStokFromTableStock(){
        
        String barcode = cbxBarcode.getSelectedItem().toString();
        String jumlah = tfJumlah.getText();
        int jumlahBarang = Integer.parseInt(jumlah);
        
        String tableName = "databarang";
        String[] column = {"No_id", "stok"};
        String condition = "barcode = " + barcode;
        
        resultSet = connection.selectCommand(column, tableName, condition);
        
        String idBarang = "";
        int stokBarang = 0;
        
        try{
            
            while(resultSet.next()){
                idBarang = resultSet.getString(1);
                stokBarang = resultSet.getInt(2);
            }
            
            connection.closeDatabase();
        }catch(SQLException e){
            System.out.println("Error try to get value from table user : " + 
                    e.getMessage());
        }
        
        int hapusStok =  stokBarang - jumlahBarang;
        
        String query = "UPDATE `databarang` SET stok = " + hapusStok
                + " WHERE No_id = " + idBarang;
        
        connection.eksekusiUpdate(query);
        connection.closeDatabase();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnTambah = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStokmasuk = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnHapus = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btnEdit = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfJumlah = new javax.swing.JTextField();
        tfKeterangan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbxBarcode = new javax.swing.JComboBox<>();
        dcTanggal = new com.toedter.calendar.JDateChooser();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Poppins SemiBold", 1, 18)); // NOI18N
        jLabel1.setText("Menu Stok Masuk");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel2.setText("Tanggal");

        jPanel2.setBackground(new java.awt.Color(1, 126, 250));

        btnTambah.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setText("  Tambah");
        btnTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTambahMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        tblStokmasuk.setModel(new javax.swing.table.DefaultTableModel(
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
        tblStokmasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStokmasukMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblStokmasuk);

        jPanel3.setBackground(new java.awt.Color(255, 36, 36));

        btnHapus.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setText("    Hapus");
        btnHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHapusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(250, 135, 1));

        btnEdit.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("       Edit");
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel5.setText("Jumlah");

        tfJumlah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 29, 88), 2));
        tfJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfJumlahActionPerformed(evt);
            }
        });

        tfKeterangan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 29, 88), 2));
        tfKeterangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKeteranganActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel6.setText("Keterangan");

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel7.setText("Nomor Barcode");

        cbxBarcode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pilih" }));
        cbxBarcode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 29, 88), 2));

        dcTanggal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(cbxBarcode, 0, 286, Short.MAX_VALUE)
                            .addComponent(dcTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(tfKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dcTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxBarcode)
                    .addComponent(tfKeterangan, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfJumlahActionPerformed

    private void tfKeteranganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKeteranganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKeteranganActionPerformed

    private void btnTambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseClicked
        
        Date date = (Date) dcTanggal.getDate();
        
        String tanggal = new java.sql.Date(date.getTime()).toString();
        String barcode = cbxBarcode.getSelectedItem().toString();
        String jumlah = tfJumlah.getText();
        String keterangan = tfKeterangan.getText();
        
        if(tanggal.isEmpty() || "pilih".equals(barcode) 
                || jumlah.isEmpty() || keterangan.isEmpty()){
            
            JOptionPane.showMessageDialog(this, "Data Masih ada yang kosong");
            
        }else{
            
            String nameTable = "stokmasuk";
            String[] column = {"tanggal_masuk", "barcode", "jumlah", "keterangan"};
            String[] value = {tanggal, barcode, jumlah, keterangan};
            connection.queryInsert(nameTable, column, value);
            
            connection.closeDatabase();
            getTable();
            addStockToTableStock(); 
            getRefresh();
        }
    }//GEN-LAST:event_btnTambahMouseClicked

    private void btnEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseClicked
        
        Date date = (Date) dcTanggal.getDate();
        
        String tanggal = new java.sql.Date(date.getTime()).toString();
        String barcode = cbxBarcode.getSelectedItem().toString();
        String jumlah = tfJumlah.getText();
        String keterangan = tfKeterangan.getText();
        
        if(tanggal.isEmpty() || "pilih".equals(barcode) 
                || jumlah.isEmpty() || keterangan.isEmpty()){
            
            JOptionPane.showMessageDialog(this, "Data Masih ada yang kosong");
            
        }else{
            
            int baris = tblStokmasuk.getSelectedRow();
            int kolom = 1;
            
            String id = String.valueOf(tblStokmasuk.getValueAt(baris, kolom));
            String nameTable = "stokmasuk";
            String[] column = {"tanggal_masuk", "barcode", "jumlah", "keterangan"};
            String[] value = {tanggal, barcode, jumlah, keterangan};
            String condition = "no_id = " + id;
            connection.queryUppdate(nameTable, column, value, condition);
            
            connection.closeDatabase();
            getTable();
            editStockFromTableStock();
            getRefresh();
        }
    }//GEN-LAST:event_btnEditMouseClicked

    private void tblStokmasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStokmasukMouseClicked
        
        int baris = tblStokmasuk.getSelectedRow();
        int kolomTanggal = 2;
        int kolomBarcode = 3;
        int kolomJumlah = 4;
        int kolomKeterangan = 5;
        
        String dateValue = 
                String.valueOf(tblStokmasuk.getValueAt(baris, kolomTanggal));
        String barcode = 
                String.valueOf(tblStokmasuk.getValueAt(baris, kolomBarcode));
        String jumlah = 
                String.valueOf(tblStokmasuk.getValueAt(baris, kolomJumlah));
        jumlahStokSebelumDiEdit = Integer.parseInt(jumlah);
        String keterangan = 
                String.valueOf(tblStokmasuk.getValueAt(baris, kolomKeterangan));
        Date date = null;
        try{
           date = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
        }catch(ParseException e){
            System.out.println("Error try to parse format" + e.getMessage());
        }
        
        dcTanggal.setDate(date);
        cbxBarcode.setSelectedItem(barcode);
        tfJumlah.setText(jumlah);
        tfKeterangan.setText(keterangan);
        
    }//GEN-LAST:event_tblStokmasukMouseClicked

    private void btnHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseClicked
        
        int baris = tblStokmasuk.getSelectedRow();
        int kolom = 1;
        
        String id = String.valueOf(tblStokmasuk.getValueAt(baris, kolom));
        boolean confirmation = JOptionPane.showConfirmDialog(
                this,
                "Apakah anda yakin ingin menghapus data ini",
                "Peringatan!!!",
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION;
        
        if(confirmation){
            
            String nameTable = "stokmasuk";
            String condition = "no_id = " + id;
            connection.queryDelete(nameTable, condition);
            connection.closeDatabase();
        }
        
        getTable();
        deleteStokFromTableStock();
        getRefresh();
        
    }//GEN-LAST:event_btnHapusMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnEdit;
    private javax.swing.JLabel btnHapus;
    private javax.swing.JLabel btnTambah;
    private javax.swing.JComboBox<String> cbxBarcode;
    private com.toedter.calendar.JDateChooser dcTanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblStokmasuk;
    private javax.swing.JTextField tfJumlah;
    private javax.swing.JTextField tfKeterangan;
    // End of variables declaration//GEN-END:variables
}
