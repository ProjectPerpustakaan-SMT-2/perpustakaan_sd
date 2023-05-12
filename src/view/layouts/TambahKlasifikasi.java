/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view.layouts;

import javax.swing.BorderFactory;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import jakarta.validation.ConstraintViolation;

import entity.Klasifikasi;
import java.util.Set;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import util.ValidasiUtil;
import repository.Repository;
import repository.KlasifikasiRepository;
import view.popup.PopupViewDataBerhasil;
import view.popup.PopupViewDataGagal;

/**
 *
 * @author Hafidz Fadhillah
 */
public class TambahKlasifikasi extends javax.swing.JInternalFrame {
    private String username;
    
    private Repository<Klasifikasi> klsfRepo = new KlasifikasiRepository();
    
    /**
     * Creates new form TambahKlasifikasi
     */
    public TambahKlasifikasi() {
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI BUI = (BasicInternalFrameUI) this.getUI();
        BUI.setNorthPane(null);
        
        jam();
    }
    
    public void setUsername(String username) {
        this.username = username;
        String result = username.substring(0, 1).toUpperCase() + username.substring(1);
        tUserLogin.setText("Selamat Datang " + result + " !");
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
        tKodeDDC = new javax.swing.JTextField();
        tNamaKlasifikasi = new javax.swing.JTextField();
        btnReset = new javax.swing.JLabel();
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

        tKodeDDC.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tKodeDDC.setBorder(null);
        getContentPane().add(tKodeDDC);
        tKodeDDC.setBounds(447, 183, 850, 35);

        tNamaKlasifikasi.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tNamaKlasifikasi.setBorder(null);
        getContentPane().add(tNamaKlasifikasi);
        tNamaKlasifikasi.setBounds(447, 264, 850, 35);

        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
        });
        getContentPane().add(btnReset);
        btnReset.setBounds(443, 330, 130, 40);

        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimpanMouseClicked(evt);
            }
        });
        getContentPane().add(btnSimpan);
        btnSimpan.setBounds(1160, 330, 130, 40);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/layouts/Tambah Klasifikasi.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 1366, 768);

        setBounds(0, 0, 1366, 768);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseClicked
        // TODO add your handling code here:
        Klasifikasi klasifikasi = new Klasifikasi(
            Integer.valueOf(tKodeDDC.getText()),
            tNamaKlasifikasi.getText()
        );
        
        Set<ConstraintViolation<Klasifikasi>> vols = ValidasiUtil.validate(klasifikasi);
        
        if (vols.size() < 1) {
            klsfRepo.add(klasifikasi);
            
            DaftarKlasifikasi daftarKlasifikasii = new DaftarKlasifikasi();
            daftarKlasifikasii.setUsername(username);
            JDesktopPane desktopPane = getDesktopPane();
            desktopPane.add(daftarKlasifikasii);
            daftarKlasifikasii.setVisible(true);

            this.dispose();
            
            new PopupViewDataBerhasil().setVisible(true);
        } else {
            new PopupViewDataGagal().setVisible(true);
            JOptionPane.showMessageDialog(this, ValidasiUtil.getErrorsAsString(vols, "\n"));
        }
    }//GEN-LAST:event_btnSimpanMouseClicked

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        // TODO add your handling code here:
        tKodeDDC.setText("");
        tNamaKlasifikasi.setText("");
    }//GEN-LAST:event_btnResetMouseClicked

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
            }};
            new javax.swing.Timer(1000, taskPerformer).start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel btnReset;
    private javax.swing.JLabel btnSimpan;
    private javax.swing.JLabel tJam;
    private javax.swing.JTextField tKodeDDC;
    private javax.swing.JTextField tNamaKlasifikasi;
    private javax.swing.JLabel tUserLogin;
    // End of variables declaration//GEN-END:variables
}
