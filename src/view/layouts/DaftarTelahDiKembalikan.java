/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view.layouts;

import customUI.TableCustom;
import javax.swing.BorderFactory;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import entity.TransaksiPengembalian;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import repository.Repository;
import static repository.Repository.conn;
import repository.TransaksiPengembalianRepository;
import util.NumberFormatUtil;
import util.ViewUtil;
import view.popup.PopupViewDetailPengembalian;

/**
 *
 * @author Hafidz Fadhillah
 */
public class DaftarTelahDiKembalikan extends javax.swing.JInternalFrame {

    private String username;

    private Repository<TransaksiPengembalian> transRepo = new TransaksiPengembalianRepository();

    /**
     * Creates new form TambahPeminjamanPetugas
     */
    public DaftarTelahDiKembalikan() {
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI BUI = (BasicInternalFrameUI) this.getUI();
        BUI.setNorthPane(null);

        jam();
        loadData();
        TableCustom.apply(jScrollPane2, TableCustom.TableType.DEFAULT);
    }

    public void setUsername(String username) {
        this.username = username;

        if (username != null) {
            username = username.trim();
            // Split the username into words
            String[] words = username.split("\\s+");
            // Get the first word
            String firstWord = words[0];
            // Capitalize the first letter of the first word
            String capitalizedFirstWord = firstWord.substring(0, 1).toUpperCase() + firstWord.substring(1);
            tUserLogin.setText("Selamat Datang " + capitalizedFirstWord + " !");
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

        tJam = new javax.swing.JLabel();
        tUserLogin = new javax.swing.JLabel();
        btnTambahPeminjaman = new javax.swing.JLabel();
        tCari = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabel = new javax.swing.JTable();
        background = new javax.swing.JLabel();

        setBorder(null);
        setPreferredSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(null);

        tJam.setFont(new java.awt.Font("Calisto MT", 1, 20)); // NOI18N
        getContentPane().add(tJam);
        tJam.setBounds(670, 8, 110, 40);

        tUserLogin.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tUserLogin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(tUserLogin);
        tUserLogin.setBounds(1105, 15, 200, 23);

        btnTambahPeminjaman.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambahPeminjaman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTambahPeminjamanMouseClicked(evt);
            }
        });
        getContentPane().add(btnTambahPeminjaman);
        btnTambahPeminjaman.setBounds(443, 148, 175, 34);

        tCari.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tCari.setBorder(null);
        tCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tCariKeyReleased(evt);
            }
        });
        getContentPane().add(tCari);
        tCari.setBounds(980, 145, 270, 40);

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
        jScrollPane2.setBounds(437, 200, 860, 520);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/layouts/Daftar Buku Yang Telah Dikembalikan.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 1366, 768);

        setBounds(0, 0, 1366, 768);
    }// </editor-fold>//GEN-END:initComponents

    private void tCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCariKeyReleased
        // TODO add your handling code here:
        String value = tCari.getText();

        String sql = "SELECT DISTINCT transaksi.*, detail_transaksi.kode_transaksi FROM transaksi "
                + "INNER JOIN detail_transaksi ON detail_transaksi.kode_transaksi = transaksi.kode_transaksi "
                + "WHERE transaksi.status = 'dikembalikan' AND transaksi.nama_peminjam LIKE ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, "%" + value + "%");
            ResultSet results = statement.executeQuery();

            List<TransaksiPengembalian> transList = new ArrayList<>();

            while (results.next()) {
                TransaksiPengembalian transaksiPengembalian = new TransaksiPengembalian();

                transaksiPengembalian.setNama_peminjam(results.getString("nama_peminjam"));
                transaksiPengembalian.setKelas(results.getString("kelas"));
                transaksiPengembalian.setTotal_pinjam(results.getInt("total_pinjam"));
                transaksiPengembalian.setTotal_denda(results.getInt("total_denda"));
                transaksiPengembalian.setKode_transaksi(results.getInt("kode_transaksi"));

                transList.add(transaksiPengembalian);
            }

            loadDataTable(transList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tCariKeyReleased

    private void TabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelMouseClicked
        // TODO add your handling code here:
        int row = Tabel.getSelectedRow();
        String value = Tabel.getModel().getValueAt(row, 5).toString();
        TransaksiPengembalian transaksi = transRepo.get(Integer.valueOf(value));

        new PopupViewDetailPengembalian(transaksi).setVisible(true);
    }//GEN-LAST:event_TabelMouseClicked

    private void btnTambahPeminjamanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahPeminjamanMouseClicked
        // TODO add your handling code here:
        DaftarPengembalian daftarPengembalian = new DaftarPengembalian();
        daftarPengembalian.setUsername(username);
        JDesktopPane desktopPane = getDesktopPane();
        desktopPane.add(daftarPengembalian);
        daftarPengembalian.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_btnTambahPeminjamanMouseClicked

    private void loadData() {
        String sql = "SELECT DISTINCT transaksi.*, detail_transaksi.kode_transaksi FROM transaksi "
                + "INNER JOIN detail_transaksi ON detail_transaksi.kode_transaksi = transaksi.kode_transaksi "
                + "WHERE transaksi.status = 'dikembalikan'";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet results = statement.executeQuery();

            List<TransaksiPengembalian> transList = new ArrayList<>();

            while (results.next()) {
                TransaksiPengembalian transaksiPengembalian = new TransaksiPengembalian();

                transaksiPengembalian.setNama_peminjam(results.getString("nama_peminjam"));
                transaksiPengembalian.setKelas(results.getString("kelas"));
                transaksiPengembalian.setTotal_pinjam(results.getInt("total_pinjam"));
                transaksiPengembalian.setTotal_denda(results.getInt("total_denda"));
                transaksiPengembalian.setKode_transaksi(results.getInt("kode_transaksi"));

                transList.add(transaksiPengembalian); // Add the buku object to the list
            }

            loadDataTable(transList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadDataTable(List<TransaksiPengembalian> trans) {
        int no = 1;
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable cell editing
            }
        };

        model.addColumn("No");
        model.addColumn("Nama Peminjam");
        model.addColumn("Kelas");
        model.addColumn("Total Denda");
        model.addColumn("Total Buku Yang Dipinjam");
        model.addColumn("ID");

        for (TransaksiPengembalian transaksi : trans) {

            model.addRow(new Object[]{
                no++,
                transaksi.getNama_peminjam(),
                transaksi.getKelas(),
                "Rp. " + NumberFormatUtil.formatDec(transaksi.getTotal_denda()),
                transaksi.getTotal_pinjam(),
                transaksi.getKode_transaksi()
            });
        }

        Tabel.setModel(model);
        ViewUtil.hideTableColumn(Tabel, 5);
        customStyleTable();
    }

    private void customStyleTable() {
        Tabel.getColumnModel().getColumn(0).setMaxWidth(40);
    }

    private void jam() {
        try {
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    String finalJam;
                    String noljam = "";
                    String nolmenit = "";
                    String noldetik = "";
                    Calendar dt = Calendar.getInstance();

                    int jam = dt.get(Calendar.HOUR_OF_DAY);
                    int menit = dt.get(Calendar.MINUTE);
                    int detik = dt.get(Calendar.SECOND);

                    if (jam < 10) {
                        noljam = "0";
                    }

                    if (menit < 10) {
                        nolmenit = "0";
                    }

                    if (detik < 10) {
                        noldetik = "0";
                    }

                    String Sjam = noljam + Integer.toString(jam);
                    String Smenit = nolmenit + Integer.toString(menit);
                    String Sdetik = noldetik + Integer.toString(detik);
                    finalJam = Sjam + ":" + Smenit + ":" + Sdetik;

                    tJam.setText(finalJam);
                }
            };
            new javax.swing.Timer(1000, taskPerformer).start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabel;
    private javax.swing.JLabel background;
    private javax.swing.JLabel btnTambahPeminjaman;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField tCari;
    private javax.swing.JLabel tJam;
    private javax.swing.JLabel tUserLogin;
    // End of variables declaration//GEN-END:variables
}
