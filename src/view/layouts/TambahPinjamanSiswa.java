/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view.layouts;

import customUI.TableCustom;
import data.ComboItem;
import data.TransaksiStatus;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import entity.Buku;
import entity.DetailTransaksiSiswa;
import entity.Petugas;
import entity.TransaksiSiswa;
import jakarta.validation.ConstraintViolation;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import repository.Repository;
import repository.BukuRepository;
import repository.DetailTransaksiRepositorySiswa;
import repository.PetugasRepository;
import repository.TransaksiRepositorySiswa;
import service.TransaksiValidasi;
import util.SearchableComboBox;
import util.ValidasiUtil;
import view.popup.PopupViewDataBerhasil;

/**
 *
 * @author Hafidz Fadhillah
 */
public class TambahPinjamanSiswa extends javax.swing.JInternalFrame {

    private Repository<Petugas> ptgRepo = new PetugasRepository();
    private Repository<Buku> bukuRepo = new BukuRepository();
    private Repository<TransaksiSiswa> transRepo = new TransaksiRepositorySiswa();
    private Repository<DetailTransaksiSiswa> detailTransRepo = new DetailTransaksiRepositorySiswa();

    private List<DetailTransaksiSiswa> details = new ArrayList<>();
    private Integer activeDetail, totalPinjam;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Creates new form TambahBuku
     */
    public TambahPinjamanSiswa() {
        fillComboBox();
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI BUI = (BasicInternalFrameUI) this.getUI();
        BUI.setNorthPane(null);
        loadTable();
        customColumnTable();

        customJDateChooser();
        TableCustom.apply(jScrollPane2, TableCustom.TableType.DEFAULT);
    }

    private void fillComboBox() {
        ComboItem[] items;
        List<Buku> bukus = bukuRepo.get();

        items = new ComboItem[bukus.size()];
        for (int i = 0; i < bukus.size(); i++) {
            Buku buku = bukus.get(i);
            items[i] = new ComboItem(buku.getKode_buku(), buku.getJudul_buku() + " - " + buku.getNama_pengarang());
        }

        bukuInput = new SearchableComboBox(items);
        bukuInput.setFont(new java.awt.Font("Calisto MT", 0, 16));
        bukuInput.setBorder(null);
        bukuInput.setBounds(469, 281, 450, 35);
        bukuInput.setBackground(new Color(0, 0, 0, 0));

        getContentPane().add(bukuInput);
    }

    private void loadTable() {
        int no = 1;
        int totalPinjam = 0;
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("No");
        model.addColumn("Nama Buku");
        model.addColumn("Tanggal Pinjam");
        model.addColumn("Tanggal Kembali");
        model.addColumn("Jumlah");

        for (DetailTransaksiSiswa detail : details) {
            int jumlahPinjam = detail.getJumlah();
            totalPinjam += jumlahPinjam;

            model.addRow(new Object[]{
                no++,
                detail.getKode_buku().getJudul_buku(),
                sdf.format(detail.getTgl_pinjam()),
                sdf.format(detail.getTgl_kembali()),
                detail.getJumlah()
            });
        }

        this.totalPinjam = totalPinjam;
        Tabel.setModel(model);
        tJumlahBuku.setText(String.valueOf(totalPinjam));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tPeminjam = new javax.swing.JTextField();
        tKelas = new javax.swing.JTextField();
        tJumlahBuku = new javax.swing.JTextField();
        tKalender = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabel = new javax.swing.JTable();
        btnTambah = new javax.swing.JLabel();
        btnHapus = new javax.swing.JLabel();
        btnReset = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setBorder(null);
        setPreferredSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(null);

        tPeminjam.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tPeminjam.setBorder(null);
        getContentPane().add(tPeminjam);
        tPeminjam.setBounds(469, 202, 588, 35);

        tKelas.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tKelas.setBorder(null);
        getContentPane().add(tKelas);
        tKelas.setBounds(1082, 202, 160, 35);

        tJumlahBuku.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tJumlahBuku.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tJumlahBuku.setBorder(null);
        getContentPane().add(tJumlahBuku);
        tJumlahBuku.setBounds(1112, 548, 130, 35);

        tKalender.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        getContentPane().add(tKalender);
        tKalender.setBounds(951, 281, 286, 36);

        Tabel.setFont(new java.awt.Font("Calisto MT", 0, 14)); // NOI18N
        Tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "Data", "Data", "Data"
            }
        ));
        Tabel.getTableHeader().setResizingAllowed(false);
        Tabel.getTableHeader().setReorderingAllowed(false);
        Tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Tabel);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(469, 365, 770, 157);

        btnTambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTambahMouseClicked(evt);
            }
        });
        getContentPane().add(btnTambah);
        btnTambah.setBounds(470, 554, 90, 26);

        btnHapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHapusMouseClicked(evt);
            }
        });
        getContentPane().add(btnHapus);
        btnHapus.setBounds(587, 553, 90, 26);

        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
        });
        getContentPane().add(btnReset);
        btnReset.setBounds(468, 624, 135, 37);

        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimpanMouseClicked(evt);
            }
        });
        getContentPane().add(btnSimpan);
        btnSimpan.setBounds(1100, 624, 138, 36);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/layouts/Tambah Peminjaman Siswa.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 1366, 768);

        setBounds(0, 0, 1366, 768);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseClicked
        // TODO add your handling code here:
        ComboItem buku = (ComboItem) bukuInput.getSelectedItem();

        TransaksiSiswa transaksiSiswa = new TransaksiSiswa(
                tPeminjam.getText(),
                tKelas.getText(),
                TransaksiStatus.dipinjam,
                Integer.valueOf(tJumlahBuku.getText()),
                0
        );

        TransaksiValidasi comboValidasi = new TransaksiValidasi(buku);

        Set<ConstraintViolation<TransaksiSiswa>> transaksiSiswaValidasi = ValidasiUtil.validate(transaksiSiswa);

        Set<ConstraintViolation<TransaksiValidasi>> comboItemValidasi = ValidasiUtil.validate(comboValidasi);

        Set<ConstraintViolation<?>> allViolations = new HashSet<>();
        allViolations.addAll(comboItemValidasi);
        allViolations.addAll(transaksiSiswaValidasi);

        if (allViolations.size() > 0) {
            Set<String> errorMessages = new HashSet<>();

            for (ConstraintViolation<?> violation : allViolations) {
                errorMessages.add(violation.getMessage());
            }

            String errorMessageString = String.join("\n", errorMessages);
            JOptionPane.showMessageDialog(this, errorMessageString);
            return;
        }

        int id = transRepo.add(transaksiSiswa);
        transaksiSiswa.setKode_transaksi(id);
        addDetails(transaksiSiswa);

        new PopupViewDataBerhasil().setVisible(true);
        resetPinjamBuku();
    }//GEN-LAST:event_btnSimpanMouseClicked

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        // TODO add your handling code here:
        tPeminjam.setText("");
        tKelas.setText("");
        tKalender.setDate(Calendar.getInstance().getTime());
        bukuInput.setSelectedIndex(0);
        tJumlahBuku.setText("");

        DefaultTableModel model = (DefaultTableModel) Tabel.getModel();
        model.setRowCount(0);
        details.clear();
    }//GEN-LAST:event_btnResetMouseClicked

    private void TabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelMouseClicked
        // TODO add your handling code here:
        int index = Tabel.rowAtPoint(evt.getPoint());
        DetailTransaksiSiswa detail = details.get(index);
        activeDetail = index;

        bukuInput.setSelectedItem(new ComboItem(
                detail.getKode_buku().getKode_buku(),
                detail.getKode_buku().getJudul_buku()
        ));

        tKalender.setDate(detail.getTgl_kembali());
    }//GEN-LAST:event_TabelMouseClicked

    private void btnTambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseClicked
        // TODO add your handling code here:
        DetailTransaksiSiswa detail = (activeDetail == null) ? new DetailTransaksiSiswa() : details.get(activeDetail);
        ComboItem bukuComboItem = (ComboItem) bukuInput.getSelectedItem();
        Buku buku = bukuRepo.get(bukuComboItem.getKey());
        Date tglKembali = tKalender.getDate();
        int jumlah = 1;
        int denda = 0;

        detail.setKode_buku(buku);
        detail.setTgl_pinjam(new Date());
        detail.setTgl_kembali(tglKembali);
        detail.setJumlah(jumlah);
        detail.setNominal_denda(denda);
        TransaksiValidasi comboValidasi = new TransaksiValidasi(bukuComboItem);

        Set<ConstraintViolation<TransaksiValidasi>> comboItemValidasi = ValidasiUtil.validate(comboValidasi);

        Set<ConstraintViolation<DetailTransaksiSiswa>> detailTransaksi = ValidasiUtil.validate(detail);

        Set<ConstraintViolation<?>> allViolations = new HashSet<>();
        allViolations.addAll(comboItemValidasi);
        allViolations.addAll(detailTransaksi);

        if (allViolations.size() > 0) {
            Set<String> errorMessages = new HashSet<>();

            for (ConstraintViolation<?> violation : allViolations) {
                errorMessages.add(violation.getMessage());
            }

            String errorMessageString = String.join("\n", errorMessages);
            JOptionPane.showMessageDialog(this, errorMessageString);
            return;
        }

        if (activeDetail == null) {
            details.add(detail);
        }

        loadTable();
        customColumnTable();
        resetBuku();
    }//GEN-LAST:event_btnTambahMouseClicked

    private void btnHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseClicked
        // TODO add your handling code here:
        if (activeDetail == null) {
            JOptionPane.showMessageDialog(this, "Pilih Data Terlebih Dahulu");
            return;
        }

        details.remove((int) activeDetail);
        activeDetail = null;

        loadTable();
        customColumnTable();
        resetBuku();
    }//GEN-LAST:event_btnHapusMouseClicked

    private void resetBuku() {
        tKalender.setDate(Calendar.getInstance().getTime());
        bukuInput.setSelectedIndex(0);
    }

    private void resetPinjamBuku() {
        tPeminjam.setText("");
        tKelas.setText("");
        tKalender.setDate(Calendar.getInstance().getTime());
        bukuInput.setSelectedIndex(0);
        tJumlahBuku.setText("");

        DefaultTableModel model = (DefaultTableModel) Tabel.getModel();
        model.setRowCount(0);
        details.clear();
    }

    private void customJDateChooser() {
        tKalender.setDateFormatString("dd-MM-yyyy");

        // Set Now Date
        tKalender.setDate(Calendar.getInstance().getTime());

        // Add minimum days selected
        Date date = new Date();
        tKalender.setMinSelectableDate(date);

        // Add 3 days to the current date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        Date maxDate = calendar.getTime();
        tKalender.setMaxSelectableDate(maxDate);
    }

    private void customColumnTable() {
        Tabel.getColumnModel().getColumn(0).setMaxWidth(40);
    }

    private void addDetails(TransaksiSiswa transaksiSiswa) {
        for (DetailTransaksiSiswa detail : details) {
            detail.setKode_transaksi(transaksiSiswa);
            detailTransRepo.add(detail);
        }
    }

    private javax.swing.JComboBox bukuInput;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabel;
    private javax.swing.JLabel background;
    private javax.swing.JLabel btnHapus;
    private javax.swing.JLabel btnReset;
    private javax.swing.JLabel btnSimpan;
    private javax.swing.JLabel btnTambah;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField tJumlahBuku;
    private com.toedter.calendar.JDateChooser tKalender;
    private javax.swing.JTextField tKelas;
    private javax.swing.JTextField tPeminjam;
    // End of variables declaration//GEN-END:variables
}
