/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.fawwaz;

import database.Koneksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mojave
 */
public class Transaksi extends javax.swing.JInternalFrame {
    
    private String namaProduk = "";
    private String kategoriProduk = "";
    private String satuanProduk = "";
    private String stokProduk = "";
    private String hargaProduk = "";
    
    private static int displayMenuBayar = 0;
    
    ResultSet resultSet;
    Koneksi connection;
    
    static int hargaTotal = 0;
    
    
    /**
     * Creates new form kategori_produk
     */
    public Transaksi() {
        connection = new Koneksi();
        initComponents();
        populateBarcodeComboBox();
        getTable();
        generateUniqueID();
    }
    
    private void populateBarcodeComboBox(){
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
        model.addColumn("Nomor Barcode");
        model.addColumn("Nama Produk");
        model.addColumn("Kategori Produk");
        model.addColumn("Satuan Produk");
        model.addColumn("Harga");
        model.addColumn("jumlah");
        
        String tableName = "transaksi_sementara";
        String[] column = {
            "nomer_barcode",
            "nama_produk",
            "kategori_produk",
            "satuan_produk",
            "harga_produk",
            "jumlah"
        };
        
        resultSet = connection.querySellect(column, tableName);
        
        try{
            int i = 0;
            
            while(resultSet.next()){
                model.addRow(new Object[]{
                    i++,
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
                });
            }
            
            connection.closeDatabase();
            tblMenuTransaksi.setModel(model);
        }catch(SQLException e){
            System.out.println("Error try to poppulate table transaksi : " 
                    + e.getMessage());
        }
        
    }
    
    private void setValueView(String nama, String jumlah){
        tvNamaProduk.setText("Nama : " + nama);
        tvSisa.setText(jumlah);
    }
    
    private int subtotalHarga(int harga, int jumlah){
        return harga * jumlah;
    }
    
    public static int getTotalHarga(){
        return hargaTotal;
        
    }
    
    public void closeDatabase(){
        connection.closeDatabase();
    }
    
    private void refreshAll(){
        cbxBarcode.setSelectedItem("Pilih");
        tfJumlah.setText(null);
        tvNamaProduk.setText("Nama : ");
        tvSisa.setText("0");
        tvSubtotal.setText("0");
    }
    
    private void substractStockFromTableStock(){
        
        String barcode = cbxBarcode.getSelectedItem().toString();
        
        String tableName = "databarang";
        String[] column = {"No_id","stok"};
        String condition = "barcode = " + barcode;
        
        resultSet = connection.selectCommand(column, tableName, condition);
        
        String idBarang = "";
        String stockBarang = "";
        try{
            
            while(resultSet.next()){
                idBarang = resultSet.getString(1);
                stockBarang = resultSet.getString(2);
            }
            
            connection.closeDatabase();
        }catch(SQLException e){
            System.out.println("Error trying get value from database : " 
                    + e.getMessage());
        }
        
        int stokKeluar = Integer.parseInt(tfJumlah.getText());
        int jumlahStokBaru = Integer.parseInt(stockBarang) - stokKeluar;
        
        String  query = "UPDATE `databarang` SET stok = " + jumlahStokBaru 
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
        
        int hapusStok =  stokBarang + jumlahBarang;
        
        String query = "UPDATE `databarang` SET stok = " + hapusStok
                + " WHERE No_id = " + idBarang;
        
        connection.eksekusiUpdate(query);
        connection.closeDatabase();
        
    }
    
    private void generateUniqueID(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Date date = new Date();
        int angkaRandom = (int)(Math.random() * (100 - 50 + 1) + 50);
        String uniqueId = 
                "D" + dateFormat.format(date)+"A"
                +100+angkaRandom+"F"+20+angkaRandom;
        tvID.setText(uniqueId);
    }
    
    public static void setDisplayMenuBayar(int displayMenuBayar){
        Transaksi.displayMenuBayar = displayMenuBayar;
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
        tblMenuTransaksi = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnHapus = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btnBayar = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfJumlah = new javax.swing.JTextField();
        tvSubtotal = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tvID = new javax.swing.JLabel();
        tvNamaProduk = new javax.swing.JLabel();
        tvSisa = new javax.swing.JLabel();
        cbxBarcode = new javax.swing.JComboBox<>();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Poppins SemiBold", 1, 18)); // NOI18N
        jLabel1.setText("Menu Transaksi");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel2.setText("Barcode");

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

        tblMenuTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMenuTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMenuTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMenuTransaksi);

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

        btnBayar.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        btnBayar.setForeground(new java.awt.Color(255, 255, 255));
        btnBayar.setText("     Bayar");
        btnBayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBayarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(btnBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBayar, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel7.setText("Jumlah");

        tfJumlah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 29, 88), 2));
        tfJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfJumlahActionPerformed(evt);
            }
        });
        tfJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfJumlahKeyReleased(evt);
            }
        });

        tvSubtotal.setFont(new java.awt.Font("Poppins", 1, 36)); // NOI18N
        tvSubtotal.setForeground(new java.awt.Color(255, 0, 0));
        tvSubtotal.setText("32.000");

        jLabel10.setFont(new java.awt.Font("Poppins", 3, 12)); // NOI18N
        jLabel10.setText("Sisa");

        jLabel5.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel5.setText("ID Nota");

        tvID.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        tvID.setText("D431F231A231A12");

        tvNamaProduk.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        tvNamaProduk.setText("Roti Coklat");

        tvSisa.setText("15");

        cbxBarcode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        cbxBarcode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 29, 88), 2));
        cbxBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxBarcodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tvID))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cbxBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tvNamaProduk))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tvSisa)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tvSubtotal))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(341, 341, 341))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(tvID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tvSubtotal)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tvNamaProduk)
                                .addGap(0, 32, Short.MAX_VALUE))
                            .addComponent(cbxBarcode, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(tvSisa))))
                .addGap(21, 21, 21)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
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
        
        
    }//GEN-LAST:event_tfJumlahActionPerformed

    private void cbxBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxBarcodeActionPerformed

        String barcodeValue = cbxBarcode.getSelectedItem().toString();
        
        String query = 
                "SELECT * FROM `databarang` WHERE barcode = " + barcodeValue;
        
        resultSet = connection.eksekusiQuery(query);
        
        try{
            
            while(resultSet.next()){
                namaProduk = resultSet.getString("nama_barang");
                kategoriProduk = resultSet.getString("kategori_produk");
                satuanProduk = resultSet.getString("satuan");
                stokProduk = resultSet.getString("stok");
                hargaProduk = resultSet.getString("harga");
            }
            
            connection.closeDatabase();
        }catch(SQLException e){
            System.out.println("Error get value from database: " +
                    e.getMessage());
        }
        
        tfJumlah.setText(null);
        setValueView(namaProduk, stokProduk);
        
        
    }//GEN-LAST:event_cbxBarcodeActionPerformed

    private void tfJumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfJumlahKeyReleased
        int harga = Integer.parseInt(hargaProduk);
        int jumlah = Integer.parseInt(tfJumlah.getText());
        int subtotal = subtotalHarga(harga, jumlah);
        
        tvSubtotal.setText(String.valueOf(subtotal));
    }//GEN-LAST:event_tfJumlahKeyReleased

    private void btnTambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseClicked
        
        String jumlah = tfJumlah.getText();
        String barcodeValue = cbxBarcode.getSelectedItem().toString();
        String hargatSubtotal = tvSubtotal.getText();
        int subtotal = Integer.parseInt(hargatSubtotal);
        
        hargaTotal+= subtotal;
        System.out.println(hargaTotal);
        
        if(jumlah.isEmpty() || barcodeValue.equals("Pilih")){
            
             JOptionPane.showMessageDialog(this, "Data Masih ada yang kosong");
            
        }else{
            
            System.out.println("test");
            String tableName = "transaksi_sementara";
            String[] column = {
                "nomer_barcode",
                "nama_produk",
                "kategori_produk",
                "satuan_produk",
                "harga_produk",
                "jumlah"
            };
             
            String[] value = {
                barcodeValue,
                namaProduk,
                kategoriProduk, 
                satuanProduk, 
                hargaProduk,
                jumlah
            };
            
            connection.queryInsert(tableName, column, value);
            connection.closeDatabase();
            getTable();
            substractStockFromTableStock();
            tfJumlah.setText(null);
            refreshAll();
        }
        
    }//GEN-LAST:event_btnTambahMouseClicked

    private void btnHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseClicked
        
        String subtotal = tvSubtotal.getText();
        String barcode = cbxBarcode.getSelectedItem().toString();
        boolean confirmation = JOptionPane.showConfirmDialog(
                this,
                "Apakah anda yakin ingin menghapus data ini",
                "Peringatan!!!",
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION;
        
        
        if(confirmation){
            
            String nameTable = "transaksi_sementara";
            String condition = "nomer_barcode = " + barcode;
            
            connection.queryDelete(nameTable, condition);
            connection.closeDatabase();
            
            int subtotalHarga = Integer.parseInt(subtotal);
            hargaTotal -= subtotalHarga;
            
            deleteStokFromTableStock();
        }
        
        getTable();
        tfJumlah.setText(null);
        refreshAll();
    }//GEN-LAST:event_btnHapusMouseClicked

    private void tblMenuTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMenuTransaksiMouseClicked
        int baris = tblMenuTransaksi.getSelectedRow();
        int kolomBarcode = 1;
        int kolomNamaProduk = 2;
        int kolomHarga = 5;
        int kolomJumlah = 6;
        
        String barcode = 
                String.valueOf(tblMenuTransaksi.getValueAt(baris, kolomBarcode));
        String namaBarang = 
                String.valueOf(tblMenuTransaksi.getValueAt(baris, kolomNamaProduk));
        String subotalHarga = 
                String.valueOf(tblMenuTransaksi.getValueAt(baris, kolomHarga));
        String jumlahBarang = 
                String.valueOf(tblMenuTransaksi.getValueAt(baris, kolomJumlah));
        
        cbxBarcode.setSelectedItem(barcode);
        tvNamaProduk.setText("Nama : " + namaBarang);
        tvSubtotal.setText(subotalHarga);
        tfJumlah.setText(jumlahBarang);
        
    }//GEN-LAST:event_tblMenuTransaksiMouseClicked

    private void btnBayarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBayarMouseClicked
        
        MenuBayar bayar = new MenuBayar();
        
        if(displayMenuBayar < 1){
            displayMenuBayar++;
            bayar.setVisible(true);
            generateUniqueID();
        }
    }//GEN-LAST:event_btnBayarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBayar;
    private javax.swing.JLabel btnHapus;
    private javax.swing.JLabel btnTambah;
    private javax.swing.JComboBox<String> cbxBarcode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMenuTransaksi;
    private javax.swing.JTextField tfJumlah;
    private javax.swing.JLabel tvID;
    private javax.swing.JLabel tvNamaProduk;
    private javax.swing.JLabel tvSisa;
    private javax.swing.JLabel tvSubtotal;
    // End of variables declaration//GEN-END:variables
}
