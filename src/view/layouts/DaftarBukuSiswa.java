/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view.layouts;

import customUI.TableCustom;
import javax.swing.BorderFactory;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import entity.Buku;

import repository.BukuRepository;
import repository.Repository;
import util.ViewUtil;
import view.popup.PopupViewDetailBuku;

/**
 *
 * @author Hafidz Fadhillah
 */
public class DaftarBukuSiswa extends javax.swing.JInternalFrame {

    private Repository<Buku> bkuRepo = new BukuRepository();

    /**
     * Creates new form TambahBuku
     */
    public DaftarBukuSiswa() {
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI BUI = (BasicInternalFrameUI) this.getUI();
        BUI.setNorthPane(null);

        loadDataTable(bkuRepo.get());
        TableCustom.apply(jScrollPane2, TableCustom.TableType.DEFAULT);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tCari = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabel = new javax.swing.JTable();
        background = new javax.swing.JLabel();

        setBorder(null);
        setPreferredSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(null);

        tCari.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tCari.setBorder(null);
        tCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tCariKeyReleased(evt);
            }
        });
        getContentPane().add(tCari);
        tCari.setBounds(973, 147, 280, 40);

        Tabel.setFont(new java.awt.Font("Calisto MT", 0, 14)); // NOI18N
        Tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "Data", "Data", "Data"
            }
        ));
        Tabel.getTableHeader().setReorderingAllowed(false);
        Tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Tabel);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(437, 210, 863, 495);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/layouts/Daftar Buku Siswa.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 1366, 768);

        setBounds(0, 0, 1366, 768);
    }// </editor-fold>//GEN-END:initComponents

    private void tCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCariKeyReleased
        // TODO add your handling code here:
        String value = tCari.getText();
        List<Buku> bukus = bkuRepo.search(new HashMap<>() {
            {
                put("isbn", value);
                put("judul_buku", value);
                put("nama_pengarang", value);
            }
        });

        loadDataTable(bukus);
    }//GEN-LAST:event_tCariKeyReleased

    private void TabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelMouseClicked
        // TODO add your handling code here:
        int row = Tabel.getSelectedRow();
        String value = Tabel.getModel().getValueAt(row, 6).toString();
        Buku buku = bkuRepo.get(Integer.valueOf(value));

        new PopupViewDetailBuku(buku).setVisible(true);
    }//GEN-LAST:event_TabelMouseClicked

    private void loadDataTable(List<Buku> bukus) {
        int no = 1;
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable cell editing
            }
        };

        model.addColumn("No");
        model.addColumn("ISBN");
        model.addColumn("Judul Buku");
        model.addColumn("Nama Pengarang");
        model.addColumn("Halaman");
        model.addColumn("Jumlah");
        model.addColumn("ID");

        for (Buku buku : bukus) {

            model.addRow(new Object[]{
                no++,
                buku.getIsbn(),
                buku.getJudul_buku(),
                buku.getNama_pengarang(),
                buku.getHalaman(),
                buku.getJumlah(),
                buku.getKode_buku()
            });
        }

        Tabel.setModel(model);
        ViewUtil.hideTableColumn(Tabel, 6);
        customStyleTable();
    }

    private void customStyleTable() {
        Tabel.getColumnModel().getColumn(0).setMaxWidth(40);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabel;
    private javax.swing.JLabel background;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField tCari;
    // End of variables declaration//GEN-END:variables
}
