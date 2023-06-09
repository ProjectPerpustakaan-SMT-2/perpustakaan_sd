/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.pages;

import entity.Petugas;
import javax.swing.JOptionPane;
import repository.PetugasRepository;
import repository.Repository;
import service.AuthService;
import view.popup.PopupViewLoginError;
import view.popup.PopupViewLoginSucces;

/**
 *
 * @author Hafidz Fadhillah
 */
public class LoginView extends javax.swing.JFrame {

    private Repository<Petugas> ptgRepo = new PetugasRepository();

    /**
     * Creates new form LoginView
     */
    public LoginView() {
        initComponents();

        btnLoginRFID.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tUsername = new javax.swing.JTextField();
        tPassword = new javax.swing.JPasswordField();
        btnLupaPassword = new javax.swing.JLabel();
        btnSiswa = new javax.swing.JLabel();
        btnLogin = new javax.swing.JLabel();
        btnLoginRFID = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        tUsername.setFont(new java.awt.Font("Calisto MT", 0, 22)); // NOI18N
        tUsername.setBorder(null);
        tUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tUsernameActionPerformed(evt);
            }
        });
        getContentPane().add(tUsername);
        tUsername.setBounds(740, 288, 397, 48);

        tPassword.setFont(new java.awt.Font("Calisto MT", 0, 22)); // NOI18N
        tPassword.setBorder(null);
        getContentPane().add(tPassword);
        tPassword.setBounds(740, 420, 397, 48);

        btnLupaPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLupaPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLupaPasswordMouseClicked(evt);
            }
        });
        getContentPane().add(btnLupaPassword);
        btnLupaPassword.setBounds(855, 495, 170, 20);

        btnSiswa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSiswaMouseClicked(evt);
            }
        });
        getContentPane().add(btnSiswa);
        btnSiswa.setBounds(736, 560, 165, 45);

        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginMouseClicked(evt);
            }
        });
        getContentPane().add(btnLogin);
        btnLogin.setBounds(975, 561, 170, 45);

        btnLoginRFID.setText("RFID");
        btnLoginRFID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginRFIDActionPerformed(evt);
            }
        });
        getContentPane().add(btnLoginRFID);
        btnLoginRFID.setBounds(1070, 530, 72, 23);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pages/Login.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 1366, 768);

        setSize(new java.awt.Dimension(1366, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLupaPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLupaPasswordMouseClicked
        // TODO add your handling code here:
        new LupaPasswordView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLupaPasswordMouseClicked

    private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseClicked
        // TODO add your handling code here:
        if (!new AuthService().login(tUsername.getText(), tPassword.getText())) {
            new PopupViewLoginError().setVisible(true);
        } else {
            PopupViewLoginSucces loginSucces = new PopupViewLoginSucces();
            loginSucces.setUsername(tUsername.getText());
            loginSucces.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnLoginMouseClicked

    private void btnSiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSiswaMouseClicked
        // TODO add your handling code here:
        new DashboardSiswa().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSiswaMouseClicked

    private void tUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tUsernameActionPerformed
        // TODO add your handling code here:
        btnLoginRFID.doClick();
    }//GEN-LAST:event_tUsernameActionPerformed

    private void btnLoginRFIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginRFIDActionPerformed
        // TODO add your handling code here:
        if (!new AuthService().loginRFID(tUsername.getText())) {
            new PopupViewLoginError().setVisible(true);
        } else {
            PopupViewLoginSucces loginSucces = new PopupViewLoginSucces();
            loginSucces.setUsername(tUsername.getText());
            loginSucces.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnLoginRFIDActionPerformed

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
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel btnLogin;
    private javax.swing.JButton btnLoginRFID;
    private javax.swing.JLabel btnLupaPassword;
    private javax.swing.JLabel btnSiswa;
    private javax.swing.JPasswordField tPassword;
    private javax.swing.JTextField tUsername;
    // End of variables declaration//GEN-END:variables
}
