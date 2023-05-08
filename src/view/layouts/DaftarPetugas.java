/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view.layouts;

import javax.swing.BorderFactory;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entity.Petugas;
import javax.swing.JDesktopPane;
import repository.Repository;
import repository.PetugasRepository;
import util.ViewUtil;

/**
 *
 * @author Hafidz Fadhillah
 */
public class DaftarPetugas extends javax.swing.JInternalFrame {
    private Repository<Petugas> ptgRepo = new PetugasRepository();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    
    /**
     * Creates new form TambahBuku
     */
    public DaftarPetugas() {
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI BUI = (BasicInternalFrameUI) this.getUI();
        BUI.setNorthPane(null);
        
        jam();
        loadDataTable(ptgRepo.get());
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
        btnTambahData = new javax.swing.JLabel();
        tCari = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabel = new javax.swing.JTable();
        background = new javax.swing.JLabel();

        setBorder(null);
        setPreferredSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(null);

        tJam.setFont(new java.awt.Font("Calisto MT", 1, 20)); // NOI18N
        getContentPane().add(tJam);
        tJam.setBounds(670, 8, 110, 40);

        btnTambahData.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambahData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTambahDataMouseClicked(evt);
            }
        });
        getContentPane().add(btnTambahData);
        btnTambahData.setBounds(433, 145, 180, 40);

        tCari.setFont(new java.awt.Font("Calisto MT", 0, 16)); // NOI18N
        tCari.setBorder(null);
        tCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tCariKeyReleased(evt);
            }
        });
        getContentPane().add(tCari);
        tCari.setBounds(979, 145, 280, 40);

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
        jScrollPane2.setBounds(437, 210, 863, 495);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/layouts/Daftar Petugas.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 1366, 768);

        setBounds(0, 0, 1366, 768);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahDataMouseClicked
        // TODO add your handling code here:
        TambahPetugas tambahPetugas = new TambahPetugas();
        JDesktopPane desktopPane = getDesktopPane();
        desktopPane.add(tambahPetugas);
        tambahPetugas.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_btnTambahDataMouseClicked

    private void tCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCariKeyReleased
        // TODO add your handling code here:
        String value = tCari.getText();
        List<Petugas> petugass = ptgRepo.search(new HashMap<>() {{
            put("nama", value);
            put("email", value);
            put("username", value);
        }});
        
        loadDataTable(petugass);
    }//GEN-LAST:event_tCariKeyReleased

    private void TabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelMouseClicked
        // TODO add your handling code here:
        int row = Tabel.getSelectedRow();
        String value = Tabel.getModel().getValueAt(row, 5).toString();
        Petugas petugas = ptgRepo.get(Integer.valueOf(value));
        
        EditPetugas editPetugas = new EditPetugas(petugas);
        JDesktopPane desktopPane = getDesktopPane();
        desktopPane.add(editPetugas);
        editPetugas.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_TabelMouseClicked

    private void loadDataTable(List<Petugas> petugass) {
        int no = 1;
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable cell editing
            }
        };
        
        model.addColumn("No");
        model.addColumn("Nama");
        model.addColumn("Email");
        model.addColumn("Username");
        model.addColumn("Tanggal Lahir");
        model.addColumn("ID");
        
        for (Petugas petugas: petugass) {
            
            model.addRow(new Object[] {
                no++,
                petugas.getNama(),
                petugas.getEmail(),
                petugas.getUsername(),
                sdf.format(petugas.getTgl_lahir()),
                petugas.getId()
            });
        }
        
        Tabel.setModel(model);
        ViewUtil.hideTableColumn(Tabel, 5);
        customStyleTable();
    }
    
    private void customStyleTable() {        
        Tabel.getColumnModel().getColumn(0).setMaxWidth(35);
        Tabel.setRowHeight(30);
        
        Tabel.getTableHeader().setFont(new Font("Calisto MT", Font.BOLD, 14));
        Tabel.getTableHeader().setPreferredSize(new Dimension(Tabel.getTableHeader().getWidth(), 35));
        ((DefaultTableCellRenderer)Tabel.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer)Tabel.getTableHeader().getDefaultRenderer()).setVerticalAlignment(JLabel.CENTER);
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
            }};
            new javax.swing.Timer(1000, taskPerformer).start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabel;
    private javax.swing.JLabel background;
    private javax.swing.JLabel btnTambahData;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField tCari;
    private javax.swing.JLabel tJam;
    // End of variables declaration//GEN-END:variables
}