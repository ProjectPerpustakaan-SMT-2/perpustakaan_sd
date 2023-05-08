/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author Hafidz Fadhillah
 */
public class Petugas implements Entity {
    public final static String tableName = "petugas";
    
    private Integer kode_petugas;
    private String email;
    private String nama;
    private String username;
    private String password;
    private Date tgl_lahir;
    
    public Petugas() {
        
    }
    
    public Petugas (String email, String username, String password, String nama, Date tgl_lahir) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.tgl_lahir = tgl_lahir;
    }
    
    public Integer getId() {
        return kode_petugas;
    }
    
    public void setId(Integer kode_petugas) {
        this.kode_petugas = kode_petugas;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public Date getTgl_lahir() {
        return tgl_lahir;
    }
    
    public void setTgl_lahir(Date tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }
}
