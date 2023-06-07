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
import entity.DetailTransaksiSiswa;
import entity.Kerusakan;
import entity.Petugas;
import entity.TransaksiSiswa;
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
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import repository.Repository;
import repository.BukuRepository;
import repository.ComboBukuRepository;
import repository.DetailTransaksiRepositorySiswa;
import repository.KerusakanRepository;
import repository.PetugasRepository;
import static repository.Repository.conn;
import repository.TransaksiRepositorySiswa;
import service.TransaksiValidasi;
import util.SearchableComboBox;
import util.ValidasiUtil;
import view.popup.PopupViewDataBerhasil;
import view.popup.PopupViewDataGagal;
import view.popup.PopupViewHapusData;

/**
 *
 * @author Hafidz Fadhillah
 */
public class TambahVerifikasiPeminjaman extends javax.swing.JInternalFrame {

    private String username;
    private TransaksiSiswa transaksi;

    private Repository<Petugas> ptgRepo = new PetugasRepository();
    private Repository<Buku> bukuRepo = new BukuRepository();
    private Repository<Buku> comboBukuRepo = new ComboBukuRepository();
    private Repository<Kerusakan> kerusakanRepo = new KerusakanRepository();
    private Repository<TransaksiSiswa> transRepo = new TransaksiRepositorySiswa();
    private Repository<DetailTransaksiSiswa> detailTransRepo = new DetailTransaksiRepositorySiswa();

    private List<DetailTransaksiSiswa> details = new ArrayList<>();
    private Integer activeDetail, totalPinjam;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Creates new form TambahBuku
     */
    public TambahVerifikasiPeminjaman() {
        fillComboBox();
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI BUI = (BasicInternalFrameUI) this.getUI();
        BUI.setNorthPane(null);

        tNamaPetugas.setVisible(false);

        setLoadData();

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
        bukuInput.setBounds(469, 335, 490, 37);
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

        for (DetailTransaksiSiswa detail : details) {
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
        tKodeBarcode = new javax.swing.JTextField();
        tPeminjam = new javax.swing.JTextField();
        tKelas = new javax.swing.JTextField();
        tJumlahBuku = new javax.swing.JTextField();
        tKembali = new com.toedter.calendar.JDateChooser();
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

        tKodeBarcode.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tKodeBarcode.setBorder(null);
        tKodeBarcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tKodeBarcodeKeyReleased(evt);
            }
        });
        getContentPane().add(tKodeBarcode);
        tKodeBarcode.setBounds(469, 176, 765, 35);

        tPeminjam.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tPeminjam.setBorder(null);
        getContentPane().add(tPeminjam);
        tPeminjam.setBounds(469, 257, 585, 35);

        tKelas.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tKelas.setBorder(null);
        getContentPane().add(tKelas);
        tKelas.setBounds(1082, 257, 160, 35);

        tJumlahBuku.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tJumlahBuku.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tJumlahBuku.setBorder(null);
        getContentPane().add(tJumlahBuku);
        tJumlahBuku.setBounds(1112, 604, 130, 35);

        tKembali.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        getContentPane().add(tKembali);
        tKembali.setBounds(988, 335, 250, 37);

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
        jScrollPane2.setBounds(469, 420, 770, 157);

        btnTambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTambahMouseClicked(evt);
            }
        });
        getContentPane().add(btnTambah);
        btnTambah.setBounds(470, 609, 90, 26);

        btnHapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHapusMouseClicked(evt);
            }
        });
        getContentPane().add(btnHapus);
        btnHapus.setBounds(587, 609, 90, 26);

        btnHapusData.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapusData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHapusDataMouseClicked(evt);
            }
        });
        getContentPane().add(btnHapusData);
        btnHapusData.setBounds(468, 680, 135, 37);

        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimpanMouseClicked(evt);
            }
        });
        getContentPane().add(btnSimpan);
        btnSimpan.setBounds(1090, 680, 150, 36);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/layouts/Form Verifikasi.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 1366, 768);

        setBounds(0, 0, 1366, 768);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseClicked
        // TODO add your handling code here:
        if (tKodeBarcode.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mohon Masukan Kode Transaksi!");
        }

        transaksi.setNama_peminjam(tPeminjam.getText());
        transaksi.setKelas(tKelas.getText());
        transaksi.setStatus(TransaksiStatus.dipinjam);
        transaksi.setTotal_pinjam(Integer.valueOf(tJumlahBuku.getText()));
        transaksi.setTotal_denda(0);
        transaksi.setKode_petugas(ptgRepo.get(Integer.valueOf(tNamaPetugas.getText())));

        Set<ConstraintViolation<TransaksiSiswa>> transaksiSiswaValidasi = ValidasiUtil.validate(transaksi);

        Set<ConstraintViolation<?>> allViolations = new HashSet<>();
        allViolations.addAll(transaksiSiswaValidasi);

        if (allViolations.size() > 0) {
            Set<String> errorMessages = new HashSet<>();

            for (ConstraintViolation<?> violation : allViolations) {
                errorMessages.add(violation.getMessage());
            }

            String errorMessageString = String.join("\n", errorMessages);
            JOptionPane.showMessageDialog(this, errorMessageString);

            new PopupViewDataGagal().setVisible(true);
            return;
        }

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
        DetailTransaksiSiswa detail = details.get(index);
        activeDetail = index;

        bukuInput.setSelectedItem(new ComboItem(
                detail.getKode_buku().getKode_buku(),
                detail.getKode_buku().getJudul_buku()
        ));

        tKembali.setDate(detail.getTgl_kembali());
    }//GEN-LAST:event_TabelMouseClicked

    private void btnTambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseClicked
        // TODO add your handling code here:
        Map<Integer, DetailTransaksiSiswa> validasiItems = new HashMap<>();

        DetailTransaksiSiswa detail = (activeDetail == null) ? new DetailTransaksiSiswa() : details.get(activeDetail);
        ComboItem bukuComboItem = (ComboItem) bukuInput.getSelectedItem();
        Buku buku = bukuRepo.get(bukuComboItem.getKey());
        Date tglKembali = tKembali.getDate();
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

            for (int i = 0; i < details.size(); i++) {
                DetailTransaksiSiswa dts = details.get(i);

                if (i > 0 && validasiItems.containsKey(dts.getKode_buku().getKode_buku())) {
                    DetailTransaksiSiswa availableDts = validasiItems.get(dts.getKode_buku().getKode_buku());

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

    List<DetailTransaksiSiswa> deleteDetails = new ArrayList<>();

    private void btnHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseClicked
        // TODO add your handling code here:
        if (activeDetail == null) {
            JOptionPane.showMessageDialog(this, "Pilih Data Terlebih Dahulu");
            return;
        }

        DetailTransaksiSiswa detail = details.get(activeDetail);

        if (detail.getKode_Detailtransaksi() != null) {
            deleteDetails.add(detail);
        }

        details.remove((int) activeDetail);
        activeDetail = null;

        loadTable();
        customColumnTable();
    }//GEN-LAST:event_btnHapusMouseClicked

    private void setLoadData() {
        final String kode_transaksi = tKodeBarcode.getText();

        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "SELECT DISTINCT transaksi.*, detail_transaksi.kode_transaksi FROM transaksi "
                        + "INNER JOIN detail_transaksi ON detail_transaksi.kode_transaksi = transaksi.kode_transaksi "
                        + "WHERE transaksi.status = 'dipinjam' AND transaksi.kode_transaksi = ?";

                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setInt(1, Integer.parseInt(kode_transaksi));
                    ResultSet results = statement.executeQuery();

                    if (results.next()) {
                        transaksi = mapToEntity(results);
                        details = detailTransRepo.get(new HashMap<>() {
                            {
                                put("kode_transaksi", transaksi.getKode_transaksi());
                            }
                        });

                        tKodeBarcode.setText(String.valueOf(transaksi.getKode_transaksi()));
                        tPeminjam.setText(transaksi.getNama_peminjam());
                        tKelas.setText(transaksi.getKelas());

                        loadTable();
                        customColumnTable();
                    } else {
                        tKodeBarcode.setText("");
                        resetPinjamBuku();
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        });

        // Start the timer
        timer.setRepeats(false);
        timer.start();
    }

    private void tKodeBarcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tKodeBarcodeKeyReleased
        // TODO add your handling code here:
        final String kode_transaksi = tKodeBarcode.getText();

        if ("".equals(kode_transaksi)) {
            resetPinjamBuku();
        } else {
            Timer timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String sql = "SELECT DISTINCT transaksi.*, detail_transaksi.kode_transaksi FROM transaksi "
                            + "INNER JOIN detail_transaksi ON detail_transaksi.kode_transaksi = transaksi.kode_transaksi "
                            + "WHERE transaksi.status = 'dipinjam' AND transaksi.kode_transaksi = ?";

                    try (PreparedStatement statement = conn.prepareStatement(sql)) {
                        statement.setInt(1, Integer.parseInt(kode_transaksi));
                        ResultSet results = statement.executeQuery();

                        if (results.next()) {
                            transaksi = mapToEntity(results);
                            details = detailTransRepo.get(new HashMap<>() {
                                {
                                    put("kode_transaksi", transaksi.getKode_transaksi());
                                }
                            });

                            tKodeBarcode.setText(String.valueOf(transaksi.getKode_transaksi()));
                            tPeminjam.setText(transaksi.getNama_peminjam());
                            tKelas.setText(transaksi.getKelas());

                            loadTable();
                            customColumnTable();
                        } else {
                            tKodeBarcode.setText("");
                            resetPinjamBuku();
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            });

            // Start the timer
            timer.setRepeats(false);
            timer.start();
        }
    }//GEN-LAST:event_tKodeBarcodeKeyReleased

    private TransaksiSiswa mapToEntity(ResultSet result) throws SQLException {
        int ptgId = result.getInt("kode_petugas");

        TransaksiSiswa transaksiSiswa = new TransaksiSiswa(
                result.getString("nama_peminjam"),
                result.getString("kelas"),
                TransaksiStatus.valueOf(result.getString("status")),
                result.getInt("total_pinjam"),
                result.getInt("total_denda"),
                new PetugasRepository().get(ptgId)
        );

        transaksiSiswa.setKode_transaksi(result.getInt("kode_transaksi"));
        return transaksiSiswa;
    }

    private void manageDetail() {
        // Edit or add details
        for (DetailTransaksiSiswa detail : details) {
            if (detail.getKode_Detailtransaksi() == null) {
                detail.setKode_transaksi(transaksi);
                detailTransRepo.add(detail);
                continue;
            }

            detailTransRepo.update(detail);
        }

        // Delete details
        for (DetailTransaksiSiswa detail : deleteDetails) {
            detailTransRepo.delete(detail.getKode_Detailtransaksi());
        }
    }

    private void resetPinjamBuku() {
        tKodeBarcode.setText("");
        tKelas.setText("");
        tKembali.setDate(Calendar.getInstance().getTime());
        bukuInput.setSelectedIndex(0);
        tJumlahBuku.setText("");

        DefaultTableModel model = (DefaultTableModel) Tabel.getModel();
        model.setRowCount(0);
        details.clear();
    }

    private void customJDateChooser() {
        tKembali.setDateFormatString("dd-MM-yyyy");

        // Set Now Date
        tKembali.setDate(Calendar.getInstance().getTime());

        // Add minimum days selected
        Date date = new Date();
        tKembali.setMinSelectableDate(date);

        // Add 3 days to the current date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        Date maxDate = calendar.getTime();
        tKembali.setMaxSelectableDate(maxDate);
    }

    private void customColumnTable() {
        Tabel.getColumnModel().getColumn(0).setMaxWidth(40);
    }

    private void setStatusBuku() {
        for (DetailTransaksiSiswa detail : details) {

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
    private javax.swing.JTextField tKelas;
    private com.toedter.calendar.JDateChooser tKembali;
    private javax.swing.JTextField tKodeBarcode;
    private javax.swing.JLabel tNamaPetugas;
    private javax.swing.JTextField tPeminjam;
    private javax.swing.JLabel tUserLogin;
    // End of variables declaration//GEN-END:variables
}
