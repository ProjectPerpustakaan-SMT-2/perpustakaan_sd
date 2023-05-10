/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.Year;

/**
 *
 * @author Hafidz Fadhillah
 */
public class Penerbit implements Entity {
    public final static String tableName = "penerbit";
    
    private Integer kode_penerbit;
    private String penerbit;
    private String kota_penerbit;
    private Year tahun_terbit;
    
    public Penerbit() {
        
    }
    
    public Penerbit(Integer kode_penerbit, String penerbit, String kota_penerbit, Year tahun_terbit) {
        this.kode_penerbit = kode_penerbit;
        this.penerbit = penerbit;
        this.kota_penerbit = kota_penerbit;
        this.tahun_terbit = tahun_terbit;
    }
    
    public Integer getKode_penerbit() {
        return kode_penerbit;
    }
    
    public void setKode_penerbit(Integer kode_penerbit) {
        this.kode_penerbit = kode_penerbit;
    }
    
    public String getPenerbit() {
        return penerbit;
    }
    
    public void setPenerbit(String Penerbit) {
        this.penerbit = Penerbit;
    }
    
    public String getKota_penerbit() {
        return kota_penerbit;
    }
    
    public void setKota_penerbit(String kota_penerbit) {
        this.kota_penerbit = kota_penerbit;
    }
    
    public Year getTahun_tebit() {
        return tahun_terbit;
    }
    
    public void setTahun_terbit(Year tahun_terbit) {
        this.tahun_terbit = tahun_terbit;
    }
}
