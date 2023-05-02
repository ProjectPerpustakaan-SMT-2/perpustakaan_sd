/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Hafidz Fadhillah
 */
public class Sanksi implements  Entity {
    private final static String tableName = "sanksi";
    
    private Integer kode_sanksi;
    private String jenis_kerusakan;
    private String deskripsi_sanksi;
    private Integer jumlah_denda;
    private String metode_lainnya;
    
    public Sanksi() {
        
    }
    
    public Sanksi(String jenis_kerusakan, String deskripsi_sanksi, 
                  Integer jumlah_denda, String metode_lainnya) {
        this.jenis_kerusakan = jenis_kerusakan;
        this.deskripsi_sanksi = deskripsi_sanksi;
        this.jumlah_denda = jumlah_denda;
        this.metode_lainnya = metode_lainnya;
    }
    
    public Integer getKode_sanksi() {
        return kode_sanksi;
    }
    
    public void setKode_sanksi(Integer kode_sanksi) {
        this.kode_sanksi = kode_sanksi;
    }
    
    public String getDeskripsi_sanksi() {
        return deskripsi_sanksi;
    }
    
    public void setDeskripsi_sanksi(String deskripsi_sanksi) {
        this.deskripsi_sanksi = deskripsi_sanksi;
    }
    
    public String getJenis_kerusakan() {
        return jenis_kerusakan;
    }
    
    public void setJenis_kerusakan(String jenis_kerusakan) {
        this.jenis_kerusakan = jenis_kerusakan;
    }
    
    public Integer getJumlah_denda() {
        return jumlah_denda;
    }
    
    public void setJumlah_denda(Integer jumlah_denda) {
        this.jumlah_denda = jumlah_denda;
    }
    
    public String getMetode_lainnya() {
        return metode_lainnya;
    }
    
    public void setMetode_lainnya(String metode_lainnya) {
        this.metode_lainnya = metode_lainnya;
    }
}
