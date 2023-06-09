/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.pages;

/**
 *
 * @author Hafidz Fadhillah
 */
public class SplashScreenView extends javax.swing.JFrame {

    /**
     * Creates new form SplashScreenView
     */
    public SplashScreenView() {
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

        loadingPercent = new javax.swing.JLabel();
        loadingText = new javax.swing.JLabel();
        loadingBar = new javax.swing.JProgressBar();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        loadingPercent.setFont(new java.awt.Font("Calisto MT", 0, 14)); // NOI18N
        loadingPercent.setText("0%");
        getContentPane().add(loadingPercent);
        loadingPercent.setBounds(1020, 548, 40, 17);

        loadingText.setFont(new java.awt.Font("Calisto MT", 0, 14)); // NOI18N
        loadingText.setText("Loading ......");
        getContentPane().add(loadingText);
        loadingText.setBounds(10, 546, 200, 20);
        getContentPane().add(loadingBar);
        loadingBar.setBounds(0, 571, 1064, 25);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pages/SplashScreen.gif"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 1080, 595);

        setSize(new java.awt.Dimension(1064, 595));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(SplashScreenView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SplashScreenView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SplashScreenView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SplashScreenView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        LoginView loginView = new LoginView(); // create LoginView object
        loginView.setVisible(false); 
        SplashScreenView view = new SplashScreenView();
        view.setVisible(true);
        
        try {
            for (int i = 1; i <= 100; i++) {
                Thread.sleep(102);
                
                view.loadingBar.setValue(i);
                view.loadingPercent.setText(i + "%");
                view.loadingText.setText("Loading ....");
                
                if (i > 25) {
                    view.loadingText.setText("Loading Modules ....");
                } 
                
                if (i > 45) {
                    view.loadingText.setText("Connect To Database ....");
                } 
                
                if (i > 85) {
                    view.loadingText.setText("Loading Application ....");
                } 
                
                if (i == 100) {
                    new LoginView().setVisible(true);
                    view.background.setIcon(null);
                    view.setVisible(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JProgressBar loadingBar;
    private javax.swing.JLabel loadingPercent;
    private javax.swing.JLabel loadingText;
    // End of variables declaration//GEN-END:variables
}
