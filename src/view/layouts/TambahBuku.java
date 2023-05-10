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

import entity.Buku;
import java.util.Set;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import util.ValidasiUtil;
import repository.Repository;
import repository.BukuRepository;
import view.popup.PopupViewDataBerhasil;
import view.popup.PopupViewDataGagal;

/**
 *
 * @author Hafidz Fadhillah
 */
public class TambahBuku extends javax.swing.JInternalFrame {
    private Repository<Buku> bkuRepo = new BukuRepository();
    
    /**
     * Creates new form TambahBuku
     */
    public TambahBuku() {
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI BUI = (BasicInternalFrameUI) this.getUI();
        BUI.setNorthPane(null);
        
        jam();
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
        tISBN = new javax.swing.JTextField();
        tJudulBuku = new javax.swing.JTextField();
        tNamaPengarang = new javax.swing.JTextField();
        tKodePenerbit = new javax.swing.JTextField();
        tKodeDDC = new javax.swing.JTextField();
        tSumber = new javax.swing.JTextField();
        tHalaman = new javax.swing.JTextField();
        tJumlah = new javax.swing.JTextField();
        btnReset = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setBorder(null);
        setPreferredSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(null);

        tJam.setFont(new java.awt.Font("Calisto MT", 1, 20)); // NOI18N
        getContentPane().add(tJam);
        tJam.setBounds(670, 8, 110, 40);

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

        tKodePenerbit.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tKodePenerbit.setBorder(null);
        getContentPane().add(tKodePenerbit);
        tKodePenerbit.setBounds(447, 348, 403, 35);

        tKodeDDC.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tKodeDDC.setBorder(null);
        getContentPane().add(tKodeDDC);
        tKodeDDC.setBounds(882, 348, 404, 35);

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

        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
        });
        getContentPane().add(btnReset);
        btnReset.setBounds(443, 595, 130, 40);

        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimpanMouseClicked(evt);
            }
        });
        getContentPane().add(btnSimpan);
        btnSimpan.setBounds(1160, 595, 130, 40);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/layouts/Tambah Buku.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 1366, 768);

        setBounds(0, 0, 1366, 768);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseClicked
        // TODO add your handling code here:
        Buku buku = new Buku(
            tJudulBuku.getText(),
            tNamaPengarang.getText(),
            Integer.valueOf(tISBN.getText()),
            Integer.valueOf(tKodePenerbit.getText()),
            tSumber.getText(),
            Integer.valueOf(tHalaman.getText()),
            Integer.valueOf(tJumlah.getText()),
            Integer.valueOf(tKodeDDC.getText())
        );
        
        Set<ConstraintViolation<Buku>> vols = ValidasiUtil.validate(buku);
        
        if (vols.size() < 1) {
            bkuRepo.add(buku);
            
            ManajemenBuku manajemenBuku = new ManajemenBuku();
            JDesktopPane desktopPane = getDesktopPane();
            desktopPane.add(manajemenBuku);
            manajemenBuku.setVisible(true);

            this.dispose();
            
            new PopupViewDataBerhasil().setVisible(true);
        } else {
            new PopupViewDataGagal().setVisible(true);
            JOptionPane.showMessageDialog(this, ValidasiUtil.getErrorsAsString(vols, "\n"));
        }
    }//GEN-LAST:event_btnSimpanMouseClicked

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        // TODO add your handling code here:
        tISBN.setText("");
        tJudulBuku.setText("");
        tNamaPengarang.setText("");
        tKodePenerbit.setText("");
        tSumber.setText("");
        tHalaman.setText("");
        tJumlah.setText("");
        tKodeDDC.setText("");
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
    private javax.swing.JTextField tHalaman;
    private javax.swing.JTextField tISBN;
    private javax.swing.JLabel tJam;
    private javax.swing.JTextField tJudulBuku;
    private javax.swing.JTextField tJumlah;
    private javax.swing.JTextField tKodeDDC;
    private javax.swing.JTextField tKodePenerbit;
    private javax.swing.JTextField tNamaPengarang;
    private javax.swing.JTextField tSumber;
    // End of variables declaration//GEN-END:variables
}
