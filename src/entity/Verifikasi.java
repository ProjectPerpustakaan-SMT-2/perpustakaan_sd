/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Hafidz Fadhillah
 */
public class Verifikasi {
    public final static String tableName = "verifikasi";
    
    private Petugas petugas;
    private String kode;
    
    public Verifikasi() {
        
    }
    
    public Verifikasi(Petugas petugas, String kode) {
        this.petugas = petugas;
        this.kode = kode;
    }
    
    public Petugas getPetugas() {
        return petugas;
    }
    
    public void setPetugas(Petugas petugas) {
        this.petugas = petugas;
    }
    
    public String getKode() {
        return kode;
    } 
    
    public void setKode(String kode) {
        this.kode = kode;
    }
}
