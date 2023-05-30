/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.popup;

import entity.Buku;
import java.awt.Color;
import java.text.SimpleDateFormat;

/**
 *
 * @author Hafidz Fadhillah
 */
public class PopupViewDetailBuku extends javax.swing.JFrame {

    private Buku buku;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    /**
     * Creates new form PopupViewDetailBuku
     */
    public PopupViewDetailBuku(Buku buku) {
        initComponents();
        this.setBackground(new Color(0, 0, 0, 0));

        this.buku = buku;

        fillForm();
    }

    private void fillForm() {
        tJudul.setText(buku.getJudul_buku());
        tPengarang.setText(buku.getNama_pengarang());
        tISBN.setText(String.valueOf(buku.getIsbn()));
        tPenerbit.setText(buku.getPenerbit().getPenerbit());
        tKota.setText(buku.getPenerbit().getKota_penerbit());
        tTahun.setText(sdf.format(buku.getPenerbit().getTahun_tebit()));
        tSumber.setText(buku.getSumber());
        tHalaman.setText(String.valueOf(buku.getHalaman()));

        if (buku.getJumlah() > 0) {
            tStatusBuku.setText("Tersedia");
        } else {
            tStatusBuku.setText(String.valueOf(buku.getBukuStatus()));
        }

        tJumlah.setText(String.valueOf(buku.getJumlah()));
        tKlasifikasi.setText(buku.getKlasifikasi().getNama_klasifikasi());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tJudul = new javax.swing.JTextField();
        tPengarang = new javax.swing.JTextField();
        tISBN = new javax.swing.JTextField();
        tKota = new javax.swing.JTextField();
        tTahun = new javax.swing.JTextField();
        tPenerbit = new javax.swing.JTextField();
        tSumber = new javax.swing.JTextField();
        tJumlah = new javax.swing.JTextField();
        tHalaman = new javax.swing.JTextField();
        tStatusBuku = new javax.swing.JTextField();
        tKlasifikasi = new javax.swing.JTextField();
        btnReset = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        tJudul.setEditable(false);
        tJudul.setBackground(new java.awt.Color(255, 255, 255));
        tJudul.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tJudul.setBorder(null);
        tJudul.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        getContentPane().add(tJudul);
        tJudul.setBounds(266, 169, 403, 35);

        tPengarang.setEditable(false);
        tPengarang.setBackground(new java.awt.Color(255, 255, 255));
        tPengarang.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tPengarang.setBorder(null);
        tPengarang.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        getContentPane().add(tPengarang);
        tPengarang.setBounds(703, 169, 403, 35);

        tISBN.setEditable(false);
        tISBN.setBackground(new java.awt.Color(255, 255, 255));
        tISBN.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tISBN.setBorder(null);
        tISBN.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        getContentPane().add(tISBN);
        tISBN.setBounds(266, 250, 840, 35);

        tKota.setEditable(false);
        tKota.setBackground(new java.awt.Color(255, 255, 255));
        tKota.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tKota.setBorder(null);
        tKota.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        getContentPane().add(tKota);
        tKota.setBounds(557, 331, 260, 35);

        tTahun.setEditable(false);
        tTahun.setBackground(new java.awt.Color(255, 255, 255));
        tTahun.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tTahun.setBorder(null);
        tTahun.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        getContentPane().add(tTahun);
        tTahun.setBounds(847, 331, 260, 35);

        tPenerbit.setEditable(false);
        tPenerbit.setBackground(new java.awt.Color(255, 255, 255));
        tPenerbit.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tPenerbit.setBorder(null);
        tPenerbit.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        getContentPane().add(tPenerbit);
        tPenerbit.setBounds(265, 331, 260, 35);

        tSumber.setEditable(false);
        tSumber.setBackground(new java.awt.Color(255, 255, 255));
        tSumber.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tSumber.setBorder(null);
        tSumber.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        getContentPane().add(tSumber);
        tSumber.setBounds(266, 413, 400, 35);

        tJumlah.setEditable(false);
        tJumlah.setBackground(new java.awt.Color(255, 255, 255));
        tJumlah.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tJumlah.setBorder(null);
        tJumlah.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        getContentPane().add(tJumlah);
        tJumlah.setBounds(703, 493, 404, 35);

        tHalaman.setEditable(false);
        tHalaman.setBackground(new java.awt.Color(255, 255, 255));
        tHalaman.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tHalaman.setBorder(null);
        tHalaman.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        getContentPane().add(tHalaman);
        tHalaman.setBounds(703, 413, 404, 35);

        tStatusBuku.setEditable(false);
        tStatusBuku.setBackground(new java.awt.Color(255, 255, 255));
        tStatusBuku.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tStatusBuku.setBorder(null);
        tStatusBuku.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        getContentPane().add(tStatusBuku);
        tStatusBuku.setBounds(266, 493, 404, 35);

        tKlasifikasi.setEditable(false);
        tKlasifikasi.setBackground(new java.awt.Color(255, 255, 255));
        tKlasifikasi.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tKlasifikasi.setBorder(null);
        getContentPane().add(tKlasifikasi);
        tKlasifikasi.setBounds(266, 575, 840, 35);

        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
        });
        getContentPane().add(btnReset);
        btnReset.setBounds(620, 640, 130, 43);

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/layouts/Detail Data Buku.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 1366, 768);

        setSize(new java.awt.Dimension(1366, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnResetMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PopupViewDetailBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopupViewDetailBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopupViewDetailBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopupViewDetailBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Buku buku = new Buku();
                new PopupViewDetailBuku(buku).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel btnReset;
    private javax.swing.JTextField tHalaman;
    private javax.swing.JTextField tISBN;
    private javax.swing.JTextField tJudul;
    private javax.swing.JTextField tJumlah;
    private javax.swing.JTextField tKlasifikasi;
    private javax.swing.JTextField tKota;
    private javax.swing.JTextField tPenerbit;
    private javax.swing.JTextField tPengarang;
    private javax.swing.JTextField tStatusBuku;
    private javax.swing.JTextField tSumber;
    private javax.swing.JTextField tTahun;
    // End of variables declaration//GEN-END:variables
}
