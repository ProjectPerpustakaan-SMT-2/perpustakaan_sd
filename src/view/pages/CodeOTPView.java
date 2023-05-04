/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.pages;

import data.SessionData;
import service.AuthService;
import javax.swing.JOptionPane;
import view.popup.PopupViewKodeOTPTidakCocok;

/**
 *
 * @author Hafidz Fadhillah
 */
public class CodeOTPView extends javax.swing.JFrame {

    /**
     * Creates new form CodeOTPView
     */
    public CodeOTPView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack = new javax.swing.JLabel();
        tKode = new javax.swing.JTextField();
        btnKirim = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });
        getContentPane().add(btnBack);
        btnBack.setBounds(305, 190, 43, 40);

        tKode.setFont(new java.awt.Font("Calisto MT", 0, 20)); // NOI18N
        tKode.setBorder(null);
        getContentPane().add(tKode);
        tKode.setBounds(350, 373, 380, 40);

        btnKirim.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKirim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKirimMouseClicked(evt);
            }
        });
        getContentPane().add(btnKirim);
        btnKirim.setBounds(480, 440, 125, 50);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pages/Code OTP.png"))); // NOI18N
        background.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        background.setMinimumSize(new java.awt.Dimension(1080, 763));
        background.setPreferredSize(new java.awt.Dimension(1080, 763));
        getContentPane().add(background);
        background.setBounds(0, 0, 1080, 763);

        setSize(new java.awt.Dimension(1080, 763));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        // TODO add your handling code here:
        new LupaPasswordView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackMouseClicked

    private void btnKirimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKirimMouseClicked
        // TODO add your handling code here:
        if (new AuthService().resetPassword(SessionData.petugas, tKode.getText())) {
            new PasswordBaruView().setVisible(true);
            this.dispose();
        } else {
            new PopupViewKodeOTPTidakCocok().setVisible(true);
        }
    }//GEN-LAST:event_btnKirimMouseClicked

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
            java.util.logging.Logger.getLogger(CodeOTPView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CodeOTPView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CodeOTPView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CodeOTPView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CodeOTPView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnKirim;
    private javax.swing.JTextField tKode;
    // End of variables declaration//GEN-END:variables
}
