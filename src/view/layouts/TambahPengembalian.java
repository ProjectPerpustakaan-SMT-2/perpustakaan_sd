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
import entity.DetailTransaksiPengembalian;
import entity.Kerusakan;
import entity.Petugas;
import entity.TransaksiPengembalian;
import jakarta.validation.ConstraintViolation;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import repository.DetailTransaksiPengembalianRepository;
import repository.KerusakanRepository;
import repository.PetugasRepository;
import static repository.Repository.conn;
import repository.TransaksiPengembalianRepository;
import util.NumberFormatUtil;
import util.SearchableComboBox;
import util.ValidasiUtil;
import view.popup.PopupViewDataBerhasil;
import view.popup.PopupViewDataGagal;

/**
 *
 * @author Hafidz Fadhillah
 */
public class TambahPengembalian extends javax.swing.JInternalFrame {

    private String username;
    private TransaksiPengembalian transaksi;

    private Repository<Petugas> ptgRepo = new PetugasRepository();
    private Repository<Buku> bukuRepo = new BukuRepository();
    private Repository<Buku> comboBukuRepo = new ComboBukuRepository();
    private Repository<Kerusakan> kerusakanRepo = new KerusakanRepository();
    private Repository<TransaksiPengembalian> transRepo = new TransaksiPengembalianRepository();
    private Repository<DetailTransaksiPengembalian> detailTransRepo = new DetailTransaksiPengembalianRepository();

    private List<DetailTransaksiPengembalian> details = new ArrayList<>();
    private Integer activeDetail, totalDenda, totalBuku;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Creates new form TambahBuku
     */
    public TambahPengembalian() {
        fillComboBox();
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI BUI = (BasicInternalFrameUI) this.getUI();
        BUI.setNorthPane(null);

        tNamaPetugas.setVisible(false);
        tJumlahBuku.setVisible(false);
        tNominalDenda.setText("0");

        loadTable();
        setNominal();
        customColumnTable();

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
        List<Kerusakan> kerusakans = kerusakanRepo.get();

        items = new ComboItem[bukus.size()];
        for (int i = 0; i < bukus.size(); i++) {
            Buku buku = bukus.get(i);
            items[i] = new ComboItem(buku.getKode_buku(), buku.getJudul_buku() + " - " + buku.getNama_pengarang());
        }

        bukuInput = new SearchableComboBox(items);
        bukuInput.setFont(new java.awt.Font("Calisto MT", 0, 16));
        bukuInput.setBorder(null);
        bukuInput.setBounds(469, 348, 373, 35);
        bukuInput.setBackground(new Color(0, 0, 0, 0));

        items = new ComboItem[kerusakans.size()];
        for (int i = 0; i < kerusakans.size(); i++) {
            Kerusakan kerusakan = kerusakans.get(i);
            items[i] = new ComboItem(kerusakan.getKode_kerusakan(), kerusakan.getJenis_kerusakan());
        }

        kerusakanInput = new SearchableComboBox(items);
        kerusakanInput.setFont(new java.awt.Font("Calisto MT", 0, 16));
        kerusakanInput.setBorder(null);
        kerusakanInput.setBounds(867, 348, 175, 35);
        kerusakanInput.setBackground(new Color(0, 0, 0, 0));

        getContentPane().add(kerusakanInput);
        getContentPane().add(bukuInput);
    }

    private void loadTable() {
        int no = 1;
        int totalBuku = 0, totalDenda = 0;
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable cell editing
            }
        };

        model.addColumn("No");
        model.addColumn("Nama Buku");
        model.addColumn("Tgl Pinjam");
        model.addColumn("Tgl Kembali");
        model.addColumn("Kerusakan Buku");
        model.addColumn("Jumlah Denda");
        model.addColumn("Jumlah Buku");

        for (DetailTransaksiPengembalian detail : details) {
            int jumlahDenda = detail.getNominal_denda();
            totalDenda += jumlahDenda;

            int jumlahBuku = detail.getJumlah();
            totalBuku += jumlahBuku;

            model.addRow(new Object[]{
                no++,
                detail.getKode_buku().getJudul_buku(),
                sdf.format(detail.getTgl_pinjam()),
                sdf.format(detail.getTgl_kembali()),
                detail.getKodeKerusakan().getJenis_kerusakan(),
                "Rp. " + NumberFormatUtil.formatDec(detail.getNominal_denda()),
                detail.getJumlah()
            });
        }

        this.totalDenda = totalDenda;
        this.totalBuku = totalBuku;
        Tabel.setModel(model);
        tJumlahDenda.setText(String.valueOf(totalDenda));
        tJumlahBuku.setText(String.valueOf(totalBuku));
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
        tRp = new javax.swing.JTextField();
        tNominalDenda = new javax.swing.JTextField();
        tRp1 = new javax.swing.JTextField();
        tJumlahBuku = new javax.swing.JLabel();
        tJumlahDenda = new javax.swing.JTextField();
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
        tKodeBarcode.setBounds(469, 188, 770, 35);

        tPeminjam.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tPeminjam.setBorder(null);
        getContentPane().add(tPeminjam);
        tPeminjam.setBounds(469, 269, 588, 35);

        tKelas.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tKelas.setBorder(null);
        getContentPane().add(tKelas);
        tKelas.setBounds(1082, 269, 160, 35);

        tRp.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tRp.setText("Rp. ");
        tRp.setBorder(null);
        getContentPane().add(tRp);
        tRp.setBounds(1070, 348, 30, 35);

        tNominalDenda.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tNominalDenda.setBorder(null);
        getContentPane().add(tNominalDenda);
        tNominalDenda.setBounds(1100, 348, 140, 35);

        tRp1.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tRp1.setText("Rp. ");
        tRp1.setBorder(null);
        getContentPane().add(tRp1);
        tRp1.setBounds(1115, 615, 30, 35);

        tJumlahBuku.setText("0");
        getContentPane().add(tJumlahBuku);
        tJumlahBuku.setBounds(1260, 630, 37, 16);

        tJumlahDenda.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tJumlahDenda.setBorder(null);
        getContentPane().add(tJumlahDenda);
        tJumlahDenda.setBounds(1142, 615, 100, 35);

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
        jScrollPane2.setBounds(469, 433, 770, 157);

        btnTambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTambahMouseClicked(evt);
            }
        });
        getContentPane().add(btnTambah);
        btnTambah.setBounds(470, 620, 90, 26);

        btnHapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHapusMouseClicked(evt);
            }
        });
        getContentPane().add(btnHapus);
        btnHapus.setBounds(587, 620, 90, 26);

        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
        });
        getContentPane().add(btnReset);
        btnReset.setBounds(468, 675, 135, 37);

        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimpanMouseClicked(evt);
            }
        });
        getContentPane().add(btnSimpan);
        btnSimpan.setBounds(1100, 675, 138, 36);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/layouts/Tambah Pengembalian.png"))); // NOI18N
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
        transaksi.setStatus(TransaksiStatus.dikembalikan);
        transaksi.setTotal_pinjam(Integer.valueOf(tJumlahBuku.getText()));
        transaksi.setTotal_denda(Integer.valueOf(tJumlahDenda.getText()));
        transaksi.setPetugas(ptgRepo.get(Integer.valueOf(tNamaPetugas.getText())));

        Set<ConstraintViolation<TransaksiPengembalian>> transaksiSiswaValidasi = ValidasiUtil.validate(transaksi);

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

        if (Integer.parseInt(tJumlahDenda.getText()) == 0) {
            int clicked = JOptionPane.showOptionDialog(
                    this, // Parent component
                    "Apakah Anda telah memilih kondisi buku ?",
                    "Konfirmasi",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Ya", "Tidak"},
                    "Ya"
            );

            if (clicked == JOptionPane.YES_OPTION) {
                transRepo.update(transaksi);
                addDetails(transaksi);

                setStatusBuku();

                DaftarPengembalian daftarPengembalian = new DaftarPengembalian();
                daftarPengembalian.setUsername(username);
                JDesktopPane desktopPane = getDesktopPane();
                desktopPane.add(daftarPengembalian);
                daftarPengembalian.setVisible(true);

                new PopupViewDataBerhasil().setVisible(true);

                this.dispose();
                resetPinjamBuku();
            }
        } else {
            transRepo.update(transaksi);
            addDetails(transaksi);

            setStatusBuku();

            DaftarPengembalian daftarPengembalian = new DaftarPengembalian();
            daftarPengembalian.setUsername(username);
            JDesktopPane desktopPane = getDesktopPane();
            desktopPane.add(daftarPengembalian);
            daftarPengembalian.setVisible(true);

            new PopupViewDataBerhasil().setVisible(true);

            this.dispose();
            resetPinjamBuku();
        }
    }//GEN-LAST:event_btnSimpanMouseClicked

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        // TODO add your handling code here:
        tKodeBarcode.setText("");
        tPeminjam.setText("");
        tKelas.setText("");
        tNominalDenda.setText("0");
        tJumlahDenda.setText("");
        tJumlahBuku.setText("");

        DefaultTableModel model = (DefaultTableModel) Tabel.getModel();
        model.setRowCount(0);
        details.clear();

        bukuInput.setSelectedIndex(0);
        kerusakanInput.setSelectedIndex(0);
    }//GEN-LAST:event_btnResetMouseClicked

    private void TabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelMouseClicked
        // TODO add your handling code here:
        int index = Tabel.rowAtPoint(evt.getPoint());
        DetailTransaksiPengembalian detail = details.get(index);
        activeDetail = index;

        bukuInput.setSelectedItem(new ComboItem(
                detail.getKode_buku().getKode_buku(),
                detail.getKode_buku().getJudul_buku()
        ));

        kerusakanInput.setSelectedItem(new ComboItem(
                detail.getKodeKerusakan().getKode_kerusakan(),
                detail.getKodeKerusakan().getJenis_kerusakan()
        ));
    }//GEN-LAST:event_TabelMouseClicked

    private void btnTambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseClicked
        // TODO add your handling code here:
        Map<Integer, DetailTransaksiPengembalian> validasiItems = new HashMap<>();

        DetailTransaksiPengembalian detail = (activeDetail == null) ? new DetailTransaksiPengembalian() : details.get(activeDetail);
        ComboItem bukuComboItem = (ComboItem) bukuInput.getSelectedItem();
        Buku buku = bukuRepo.get(bukuComboItem.getKey());

        ComboItem kerusComboItem = (ComboItem) kerusakanInput.getSelectedItem();
        Kerusakan kerusakan = kerusakanRepo.get(kerusComboItem.getKey());

        int jumlah = 1;
        int denda = Integer.parseInt(tNominalDenda.getText());

        detail.setKode_buku(buku);
        detail.setTgl_kembali(new Date());
        detail.setJumlah(jumlah);
        detail.setKodeKerusakan(kerusakan);
        detail.setNominal_denda(denda);

        Set<ConstraintViolation<DetailTransaksiPengembalian>> detailTransaksi = ValidasiUtil.validate(detail);

        Set<ConstraintViolation<?>> allViolations = new HashSet<>();
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
                DetailTransaksiPengembalian dts = details.get(i);

                if (i > 0 && validasiItems.containsKey(dts.getKode_buku().getKode_buku())) {
                    DetailTransaksiPengembalian availableDts = validasiItems.get(dts.getKode_buku().getKode_buku());

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

    private TransaksiPengembalian mapToEntity(ResultSet result) throws SQLException {
        int ptgId = result.getInt("kode_petugas");

        TransaksiPengembalian transaksi = new TransaksiPengembalian(
                result.getString("nama_peminjam"),
                result.getString("kelas"),
                TransaksiStatus.valueOf(result.getString("status")),
                result.getInt("total_pinjam"),
                result.getInt("total_denda"),
                new PetugasRepository().get(ptgId)
        );

        transaksi.setKode_transaksi(result.getInt("kode_transaksi"));
        return transaksi;
    }

    private void resetBuku() {
        bukuInput.setSelectedIndex(0);
        kerusakanInput.setSelectedIndex(0);
        tNominalDenda.setText("0");
    }

    private void resetPinjamBuku() {
        tKodeBarcode.setText("");
        tPeminjam.setText("");
        tKelas.setText("");
        tNominalDenda.setText("0");
        tJumlahDenda.setText("");
        tJumlahBuku.setText("");

        DefaultTableModel model = (DefaultTableModel) Tabel.getModel();
        model.setRowCount(0);
        details.clear();

        bukuInput.setSelectedIndex(0);
        kerusakanInput.setSelectedIndex(0);
    }

    private void setNominal() {
        kerusakanInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComboItem kerusComboItem = (ComboItem) kerusakanInput.getSelectedItem();
                Kerusakan kerusakan = kerusakanRepo.get(kerusComboItem.getKey());

                tNominalDenda.setText(String.valueOf(kerusakan.getNominal_denda()));
            }
        });
    }

    private void customColumnTable() {
        Tabel.getColumnModel().getColumn(0).setMaxWidth(40);
    }

    private void addDetails(TransaksiPengembalian transaksiPengembalian) {
        for (DetailTransaksiPengembalian detail : details) {
            detail.setKode_transaksi(transaksiPengembalian);
            detailTransRepo.update(detail);
        }
    }

    private void setStatusBuku() {
        for (DetailTransaksiPengembalian detail : details) {

            String sql = "UPDATE buku SET status = ? WHERE kode_buku = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                switch (detail.getKodeKerusakan().getJenis_kerusakan()) {
                    case "Layak":
                        stmt.setString(1, BukuStatus.Layak.toString());
                        break;
                    case "Sobek":
                        stmt.setString(1, BukuStatus.Rusak.toString());
                        break;
                    case "Terlipat":
                        stmt.setString(1, BukuStatus.Rusak.toString());
                        break;
                    case "Hilang":
                        stmt.setString(1, BukuStatus.Hilang.toString());
                        break;
                    default:
                        break;
                }

                stmt.setInt(2, detail.getKode_buku().getKode_buku());

                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private javax.swing.JComboBox bukuInput;
    private javax.swing.JComboBox kerusakanInput;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabel;
    private javax.swing.JLabel background;
    private javax.swing.JLabel btnHapus;
    private javax.swing.JLabel btnReset;
    private javax.swing.JLabel btnSimpan;
    private javax.swing.JLabel btnTambah;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel tJam;
    private javax.swing.JLabel tJumlahBuku;
    private javax.swing.JTextField tJumlahDenda;
    private javax.swing.JTextField tKelas;
    private javax.swing.JTextField tKodeBarcode;
    private javax.swing.JLabel tNamaPetugas;
    private javax.swing.JTextField tNominalDenda;
    private javax.swing.JTextField tPeminjam;
    private javax.swing.JTextField tRp;
    private javax.swing.JTextField tRp1;
    private javax.swing.JLabel tUserLogin;
    // End of variables declaration//GEN-END:variables
}
