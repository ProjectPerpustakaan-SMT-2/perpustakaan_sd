/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import service.AuthService;
import view.popup.PopupViewDataTidakDitemukan;
import view.popup.PopupViewKodeOTPTerkirim;

/**
 *
 * @author Hafidz Fadhillah
 */
public class LupaPasswordView extends javax.swing.JFrame {

    /**
     * Creates new form LupaPasswordView
     */
    public LupaPasswordView() {
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
        tUsername = new javax.swing.JTextField();
        tEmail = new javax.swing.JTextField();
        btnKirm = new javax.swing.JLabel();
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
        btnBack.setBounds(398, 135, 50, 50);

        tUsername.setFont(new java.awt.Font("Calisto MT", 0, 20)); // NOI18N
        tUsername.setBorder(null);
        getContentPane().add(tUsername);
        tUsername.setBounds(450, 338, 470, 48);

        tEmail.setFont(new java.awt.Font("Calisto MT", 0, 20)); // NOI18N
        tEmail.setBorder(null);
        getContentPane().add(tEmail);
        tEmail.setBounds(450, 465, 470, 50);

        btnKirm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKirmMouseClicked(evt);
            }
        });
        getContentPane().add(btnKirm);
        btnKirm.setBounds(608, 540, 155, 60);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pages/Lupa Password.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 1366, 768);

        setSize(new java.awt.Dimension(1366, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        // TODO add your handling code here:
        new LoginView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackMouseClicked

    private void btnKirmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKirmMouseClicked
        // TODO add your handling code here:
        try {
            if(new AuthService().forgetPassword(tUsername.getText(), tEmail.getText())) {
                new PopupViewKodeOTPTerkirim().setVisible(true);
                this.dispose();
            } else {
                new PopupViewDataTidakDitemukan().setVisible(true);
            }
        } catch (MessagingException ex) {
            Logger.getLogger(LupaPasswordView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnKirmMouseClicked

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
            java.util.logging.Logger.getLogger(LupaPasswordView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LupaPasswordView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LupaPasswordView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LupaPasswordView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LupaPasswordView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnKirm;
    private javax.swing.JTextField tEmail;
    private javax.swing.JTextField tUsername;
    // End of variables declaration//GEN-END:variables
}
