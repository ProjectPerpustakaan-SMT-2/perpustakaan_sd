/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view.layouts;

import data.ComboItem;
import javax.swing.BorderFactory;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import jakarta.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

import entity.Buku;
import entity.Klasifikasi;
import entity.Penerbit;
import java.text.SimpleDateFormat;
import util.ValidasiUtil;
import repository.Repository;
import repository.BukuRepository;
import repository.KlasifikasiRepository;
import repository.PenerbitRepository;
import util.SearchableComboBox;
import view.popup.PopupViewDataDiubah;
import view.popup.PopupViewHapusData;

/**
 *
 * @author Hafidz Fadhillah
 */
public class EditBuku extends javax.swing.JInternalFrame {

    private Buku buku;
    private String username;

    private Repository<Buku> bkuRepo = new BukuRepository();
    private Repository<Penerbit> pnbtRepo = new PenerbitRepository();
    private Repository<Klasifikasi> klsfRepo = new KlasifikasiRepository();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    /**
     * Creates new form TambahBuku
     */
    public EditBuku(Buku buku) {
        fillComboBox();
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI BUI = (BasicInternalFrameUI) this.getUI();
        BUI.setNorthPane(null);

        this.buku = buku;

        fillForm();

        jam();
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
        tISBN = new javax.swing.JTextField();
        tJudulBuku = new javax.swing.JTextField();
        tNamaPengarang = new javax.swing.JTextField();
        tSumber = new javax.swing.JTextField();
        tHalaman = new javax.swing.JTextField();
        tJumlah = new javax.swing.JTextField();
        btnHapus = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JLabel();
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

        tISBN.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tISBN.setBorder(null);
        getContentPane().add(tISBN);
        tISBN.setBounds(447, 186, 840, 35);

        tJudulBuku.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tJudulBuku.setBorder(null);
        getContentPane().add(tJudulBuku);
        tJudulBuku.setBounds(447, 267, 403, 35);

        tNamaPengarang.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tNamaPengarang.setBorder(null);
        getContentPane().add(tNamaPengarang);
        tNamaPengarang.setBounds(882, 267, 403, 35);

        tSumber.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tSumber.setBorder(null);
        getContentPane().add(tSumber);
        tSumber.setBounds(447, 429, 840, 35);

        tHalaman.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tHalaman.setBorder(null);
        getContentPane().add(tHalaman);
        tHalaman.setBounds(447, 510, 404, 35);

        tJumlah.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tJumlah.setBorder(null);
        getContentPane().add(tJumlah);
        tJumlah.setBounds(882, 510, 404, 35);

        btnHapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHapusMouseClicked(evt);
            }
        });
        getContentPane().add(btnHapus);
        btnHapus.setBounds(443, 595, 130, 40);

        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimpanMouseClicked(evt);
            }
        });
        getContentPane().add(btnSimpan);
        btnSimpan.setBounds(1160, 595, 130, 40);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/layouts/Edit Buku.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 1366, 768);

        setBounds(0, 0, 1366, 768);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseClicked
        // TODO add your handling code here:
        ComboItem pnbtItem = (ComboItem) penerbitInput.getSelectedItem();
        ComboItem kodeDDCItem = (ComboItem) klasifikasiInput.getSelectedItem();

        buku.setJudul_buku(tJudulBuku.getText());
        buku.setNama_pengarang(tNamaPengarang.getText());
        buku.setIsbn(Integer.valueOf(tISBN.getText()));
        buku.setPenerbit(pnbtRepo.get(pnbtItem.getKey()));
        buku.setSumber(tSumber.getText());
        buku.setHalaman(Integer.valueOf(tHalaman.getText()));
        buku.setJumlah(Integer.valueOf(tJumlah.getText()));
        buku.setKlasifikasi(klsfRepo.get(kodeDDCItem.getKey()));

        Set<ConstraintViolation<Buku>> vols = ValidasiUtil.validate(buku);

        if (vols.size() > 0) {
            JOptionPane.showMessageDialog(this, ValidasiUtil.getErrorsAsString(vols, "\n"));
            return;
        } else {
            bkuRepo.update(buku);

            ManajemenBuku manajemenBuku = new ManajemenBuku();
            manajemenBuku.setUsername(username);
            JDesktopPane desktopPane = getDesktopPane();
            desktopPane.add(manajemenBuku);
            manajemenBuku.setVisible(true);

            this.dispose();

            new PopupViewDataDiubah().setVisible(true);
        }
    }//GEN-LAST:event_btnSimpanMouseClicked

    private void btnHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseClicked
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
            bkuRepo.delete(buku.getKode_buku());

            ManajemenBuku manajemenBuku = new ManajemenBuku();
            manajemenBuku.setUsername(username);
            JDesktopPane desktopPane = getDesktopPane();
            desktopPane.add(manajemenBuku);
            manajemenBuku.setVisible(true);

            this.dispose();

            new PopupViewHapusData().setVisible(true);
        }
    }//GEN-LAST:event_btnHapusMouseClicked

    private void fillForm() {
        tISBN.setText(String.valueOf(buku.getIsbn()));
        tJudulBuku.setText(buku.getJudul_buku());
        tNamaPengarang.setText(buku.getNama_pengarang());
        tSumber.setText(buku.getSumber());
        tHalaman.setText(String.valueOf(buku.getHalaman()));
        tJumlah.setText(String.valueOf(buku.getJumlah()));

        penerbitInput.setSelectedItem(new ComboItem(
                buku.getPenerbit().getKode_penerbit(),
                buku.getPenerbit().getPenerbit() + " - " + buku.getPenerbit().getKota_penerbit() + " - " + sdf.format(buku.getPenerbit().getTahun_tebit()))
        );

        klasifikasiInput.setSelectedItem(new ComboItem(
                buku.getKlasifikasi().getId_klasifikasi(),
                buku.getKlasifikasi().getKode_ddc() + " - " + buku.getKlasifikasi().getNama_klasifikasi())
        );
    }

    private void fillComboBox() {
        ComboItem[] items;
        List<Penerbit> penerbits = pnbtRepo.get();
        List<Klasifikasi> klasifikasis = klsfRepo.get();

        items = new ComboItem[penerbits.size()];
        for (int i = 0; i < penerbits.size(); i++) {
            Penerbit penerbit = penerbits.get(i);
            items[i] = new ComboItem(penerbit.getKode_penerbit(), penerbit.getPenerbit() + " - " + penerbit.getKota_penerbit() + " - " + sdf.format(penerbit.getTahun_tebit()));
        }

        penerbitInput = new SearchableComboBox(items);
        penerbitInput.setFont(new java.awt.Font("Calisto MT", 0, 16));
        penerbitInput.setBorder(null);
        penerbitInput.setBounds(448, 349, 403, 35);

        items = new ComboItem[klasifikasis.size()];
        for (int i = 0; i < klasifikasis.size(); i++) {
            Klasifikasi klasifikasi = klasifikasis.get(i);
            items[i] = new ComboItem(klasifikasi.getId_klasifikasi(), klasifikasi.getKode_ddc() + " - " + klasifikasi.getNama_klasifikasi());
        }

        klasifikasiInput = new SearchableComboBox(items);
        klasifikasiInput.setFont(new java.awt.Font("Calisto MT", 0, 16));
        klasifikasiInput.setBorder(null);
        klasifikasiInput.setBounds(883, 349, 404, 35);

        getContentPane().add(penerbitInput);
        getContentPane().add(klasifikasiInput);
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

    private javax.swing.JComboBox penerbitInput;
    private javax.swing.JComboBox klasifikasiInput;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel btnHapus;
    private javax.swing.JLabel btnSimpan;
    private javax.swing.JTextField tHalaman;
    private javax.swing.JTextField tISBN;
    private javax.swing.JLabel tJam;
    private javax.swing.JTextField tJudulBuku;
    private javax.swing.JTextField tJumlah;
    private javax.swing.JTextField tNamaPengarang;
    private javax.swing.JTextField tSumber;
    private javax.swing.JLabel tUserLogin;
    // End of variables declaration//GEN-END:variables
}
