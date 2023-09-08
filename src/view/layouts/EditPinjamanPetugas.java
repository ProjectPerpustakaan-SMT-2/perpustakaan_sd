/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view.layouts;

import customUI.TableCustom;
import data.BukuStatus;
import data.ComboItem;
import data.TransaksiStatus;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import entity.Buku;
import entity.DetailTransaksi;
import entity.DetailTransaksiSiswa;
import entity.Kerusakan;
import entity.Petugas;
import entity.Transaksi;
import jakarta.validation.ConstraintViolation;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import repository.Repository;
import repository.BukuRepository;
import repository.ComboBukuRepository;
import repository.DetailTransaksiRepository;
import repository.KerusakanRepository;
import repository.PetugasRepository;
import static repository.Repository.conn;
import repository.TransaksiRepository;
import service.TransaksiValidasi;
import util.SearchableComboBox;
import util.ValidasiUtil;
import util.ViewUtil;
import view.popup.PopupViewDataBerhasil;
import view.popup.PopupViewHapusData;

/**
 *
 * @author Hafidz Fadhillah
 */
public class EditPinjamanPetugas extends javax.swing.JInternalFrame {

    private String username;

    private Transaksi transaksi;

    private Repository<Petugas> ptgRepo = new PetugasRepository();
    private Repository<Buku> bukuRepo = new BukuRepository();
    private Repository<Buku> comboBukuRepo = new ComboBukuRepository();
    private Repository<Kerusakan> kerusakanRepo = new KerusakanRepository();
    private Repository<Transaksi> transRepo = new TransaksiRepository();
    private Repository<DetailTransaksi> detailTransRepo = new DetailTransaksiRepository();

    private List<DetailTransaksi> details, deleteDetails = new ArrayList<>();
    private Integer activeDetail, totalPinjam;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Creates new form TambahBuku
     */
    public EditPinjamanPetugas(Transaksi transaksi) {
        this.transaksi = transaksi;
        this.details = detailTransRepo.get(new HashMap<>() {
            {
                put("kode_transaksi", transaksi.getKode_transaksi());
            }
        });

        fillComboBox();
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI BUI = (BasicInternalFrameUI) this.getUI();
        BUI.setNorthPane(null);

        jam();

        tNamaPetugas.setVisible(false);

        fillInput();
        loadTable();
        customColumnTable();

        customJDateChooser();
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

            // Get Kode Petugas From Name User Login
            String sql = "SELECT kode_petugas FROM petugas WHERE nama = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, this.username);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    tNamaPetugas.setText(rs.getString("kode_petugas"));
                }

                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void fillComboBox() {
        ComboItem[] items;
        List<Buku> bukus = comboBukuRepo.get();

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
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable cell editing
            }
        };

        model.addColumn("No");
        model.addColumn("Nama Buku");
        model.addColumn("Tanggal Pinjam");
        model.addColumn("Tanggal Kembali");
        model.addColumn("Jumlah");
        model.addColumn("ID");

        for (DetailTransaksi detail : details) {
            int jumlahPinjam = detail.getJumlah();
            totalPinjam += jumlahPinjam;

            model.addRow(new Object[]{
                no++,
                detail.getKode_buku().getJudul_buku(),
                sdf.format(detail.getTgl_pinjam()),
                sdf.format(detail.getTgl_kembali()),
                detail.getJumlah(),
                detail.getKode_Detailtransaksi()
            });
        }

        this.totalPinjam = totalPinjam;
        Tabel.setModel(model);
        ViewUtil.hideTableColumn(Tabel, 5);
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

        tJam = new javax.swing.JLabel();
        tNamaPetugas = new javax.swing.JLabel();
        tUserLogin = new javax.swing.JLabel();
        tPeminjam = new javax.swing.JTextField();
        tKelas = new javax.swing.JComboBox<>();
        tJumlahBuku = new javax.swing.JTextField();
        tKalender = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabel = new javax.swing.JTable();
        btnTambah = new javax.swing.JLabel();
        btnHapus = new javax.swing.JLabel();
        btnHapusData = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setBorder(null);
        setPreferredSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(null);

        tJam.setFont(new java.awt.Font("Calisto MT", 1, 20)); // NOI18N
        getContentPane().add(tJam);
        tJam.setBounds(670, 8, 110, 40);
        getContentPane().add(tNamaPetugas);
        tNamaPetugas.setBounds(980, 17, 100, 20);

        tUserLogin.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tUserLogin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(tUserLogin);
        tUserLogin.setBounds(1105, 15, 200, 23);

        tPeminjam.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tPeminjam.setBorder(null);
        getContentPane().add(tPeminjam);
        tPeminjam.setBounds(469, 202, 588, 35);

        tKelas.setBackground(new Color(0,0,0,0));
        tKelas.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tKelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6" }));
        tKelas.setSelectedIndex(-1);
        tKelas.setBorder(null);
        getContentPane().add(tKelas);
        tKelas.setBounds(1082, 200, 160, 35);

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

        btnHapusData.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapusData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHapusDataMouseClicked(evt);
            }
        });
        getContentPane().add(btnHapusData);
        btnHapusData.setBounds(468, 624, 135, 37);

        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimpanMouseClicked(evt);
            }
        });
        getContentPane().add(btnSimpan);
        btnSimpan.setBounds(1100, 624, 138, 36);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/layouts/Edit Peminjaman Petugas.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 1366, 768);

        setBounds(0, 0, 1366, 768);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseClicked
        // TODO add your handling code here:
        ComboItem buku = (ComboItem) bukuInput.getSelectedItem();

        transaksi.setNama_peminjam(tPeminjam.getText());
        transaksi.setKelas(tKelas.getSelectedItem().toString());
        transaksi.setStatus(TransaksiStatus.dipinjam);
        transaksi.setTotal_pinjam(Integer.valueOf(tJumlahBuku.getText()));
        transaksi.setTotal_denda(0);
        transaksi.setPetugas(ptgRepo.get(Integer.valueOf(tNamaPetugas.getText())));

        manageDetail();

        transRepo.update(transaksi);

        setStatusBuku();

        DaftarPeminjaman daftarPeminjaman = new DaftarPeminjaman();
        daftarPeminjaman.setUsername(username);
        JDesktopPane desktopPane = getDesktopPane();
        desktopPane.add(daftarPeminjaman);
        daftarPeminjaman.setVisible(true);

        new PopupViewDataBerhasil().setVisible(true);

        this.dispose();
        resetPinjamBuku();
    }//GEN-LAST:event_btnSimpanMouseClicked

    private void btnHapusDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusDataMouseClicked
        // TODO add your handling code here:
        int clicked = JOptionPane.showOptionDialog(
                this, // Parent component
                "Apakah Anda yakin ?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Ya", "Tidak"},
                "Tidak"
        );

        if (clicked == JOptionPane.YES_OPTION) {
            detailTransRepo.delete(transaksi.getKode_transaksi());
            transRepo.delete(transaksi.getKode_transaksi());

            DaftarPeminjaman daftarPeminjaman = new DaftarPeminjaman();
            daftarPeminjaman.setUsername(username);
            JDesktopPane desktopPane = getDesktopPane();
            desktopPane.add(daftarPeminjaman);
            daftarPeminjaman.setVisible(true);

            this.dispose();

            new PopupViewHapusData().setVisible(true);
        }
    }//GEN-LAST:event_btnHapusDataMouseClicked

    private void TabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelMouseClicked
        // TODO add your handling code here:
        int index = Tabel.rowAtPoint(evt.getPoint());
        DetailTransaksi detail = details.get(index);
        activeDetail = index;

        bukuInput.setSelectedItem(new ComboItem(
                detail.getKode_buku().getKode_buku(),
                detail.getKode_buku().getJudul_buku()
        ));

        tKalender.setDate(detail.getTgl_kembali());
    }//GEN-LAST:event_TabelMouseClicked

    private void btnTambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseClicked
        // TODO add your handling code here:
        Map<Integer, DetailTransaksi> validasiItems = new HashMap<>();

        DetailTransaksi detail = (activeDetail == null) ? new DetailTransaksi() : details.get(activeDetail);
        ComboItem bukuComboItem = (ComboItem) bukuInput.getSelectedItem();
        Buku buku = bukuRepo.get(bukuComboItem.getKey());
        Date tglKembali = tKalender.getDate();
        int jumlah = 1;
        Integer kode_kerusakan = null;
        int denda = 0;

        detail.setKode_buku(buku);
        detail.setTgl_pinjam(new Date());
        detail.setTgl_kembali(tglKembali);
        detail.setJumlah(jumlah);
        detail.setKodeKerusakan(kerusakanRepo.get(kode_kerusakan));
        detail.setNominal_denda(denda);
        detail.setKode_transaksi(transRepo.get(transaksi.getKode_transaksi()));
        TransaksiValidasi comboValidasi = new TransaksiValidasi(bukuComboItem);

        Set<ConstraintViolation<TransaksiValidasi>> comboItemValidasi = ValidasiUtil.validate(comboValidasi);

        Set<ConstraintViolation<DetailTransaksi>> detailTransaksi = ValidasiUtil.validate(detail);

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

            for (int i = 0; i < details.size(); i++) {
                DetailTransaksi dts = details.get(i);

                if (i > 0 && validasiItems.containsKey(dts.getKode_buku().getKode_buku())) {
                    DetailTransaksi availableDts = validasiItems.get(dts.getKode_buku().getKode_buku());

                    int jumlahBuku = availableDts.getJumlah() + dts.getJumlah();

                    if (jumlahBuku > detail.getKode_buku().getJumlah()) {
                        JOptionPane.showMessageDialog(this, "Stok Buku Tidak Tersedia");
                        continue;
                    }

                    validasiItems.get(dts.getKode_buku().getKode_buku()).setJumlah(availableDts.getJumlah() + dts.getJumlah());

                    continue;
                }

                validasiItems.put(dts.getKode_buku().getKode_buku(), dts);
            }

            details = new ArrayList<>(validasiItems.values());

        }

        activeDetail = null;

        loadTable();
        customColumnTable();
    }//GEN-LAST:event_btnTambahMouseClicked

    private void btnHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseClicked
        // TODO add your handling code here:
        if (activeDetail == null) {
            JOptionPane.showMessageDialog(this, "Pilih Data Terlebih Dahulu");
            return;
        }

        DetailTransaksi detail = details.get(activeDetail);

        if (detail.getKode_Detailtransaksi() != null) {
            deleteDetails.add(detail);
        }

        details.remove((int) activeDetail);
        activeDetail = null;

        loadTable();
        customColumnTable();
    }//GEN-LAST:event_btnHapusMouseClicked

    private void fillInput() {
        tPeminjam.setText(transaksi.getNama_peminjam());
        tKelas.setSelectedItem(transaksi.getKelas());
    }

    private void manageDetail() {
        // Edit or add details
        for (DetailTransaksi detail : details) {
            if (detail.getKode_Detailtransaksi() == null) {
                detail.setKode_transaksi(transaksi);
                detailTransRepo.add(detail);
                continue;
            }

            detailTransRepo.update(detail);
        }

        // Delete details
        for (DetailTransaksi detail : deleteDetails) {
            detailTransRepo.delete(detail.getKode_Detailtransaksi());
        }
    }

    private void resetPinjamBuku() {
        tPeminjam.setText("");
        tKelas.setSelectedIndex(-1);
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

    private void setStatusBuku() {
        for (DetailTransaksi detail : details) {

            String sql = "UPDATE buku SET status = ? WHERE kode_buku = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, BukuStatus.Dipinjam.toString());
                stmt.setInt(2, detail.getKode_buku().getKode_buku());

                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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

    private javax.swing.JComboBox bukuInput;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabel;
    private javax.swing.JLabel background;
    private javax.swing.JLabel btnHapus;
    private javax.swing.JLabel btnHapusData;
    private javax.swing.JLabel btnSimpan;
    private javax.swing.JLabel btnTambah;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel tJam;
    private javax.swing.JTextField tJumlahBuku;
    private com.toedter.calendar.JDateChooser tKalender;
    private javax.swing.JComboBox<String> tKelas;
    private javax.swing.JLabel tNamaPetugas;
    private javax.swing.JTextField tPeminjam;
    private javax.swing.JLabel tUserLogin;
    // End of variables declaration//GEN-END:variables
}
