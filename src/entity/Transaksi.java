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
public class Transaksi implements Entity {
    private final static String tableName = "transaksi";
    
    private Integer kode_transaksi;
    private String nama_peminjam;
    private String kelas;
    private Integer kode_buku;
    private Date tgl_pinjam;
    private Date tgl_kembali;
    private Integer lama_pinjam;
    private Integer kode_petugas;
    private Integer kode_sanksi;
    
    public Transaksi() {
        
    }
    
    public Transaksi(String nama_peminjam, String kelas, Integer kode_buku, Date tgl_pinjam,
                     Date tgl_kembali, Integer lama_pinjam, Integer kode_petugas, Integer kode_sanksi) {
        this.nama_peminjam = nama_peminjam;
        this.kelas = kelas;
        this.kode_buku = kode_buku;
        this.tgl_pinjam = tgl_pinjam;
        this.tgl_kembali = tgl_kembali;
        this.lama_pinjam = lama_pinjam;
        this.kode_petugas = kode_petugas;
        this.kode_sanksi = kode_sanksi;
    }
    
    public Integer getKode_transaksi() {
        return kode_transaksi;
    }
    
    public void setKode_transaksi(Integer kode_transaksi) {
        this.kode_transaksi = kode_transaksi;
    }
    
    public String getNama_peminjam() {
        return nama_peminjam;
    }
    
    public void setNama_peminjam(String nama_peminjam) {
        this.nama_peminjam = nama_peminjam;
    }
    
    public String getKelas() {
        return kelas;
    }
    
    public void setKelas(String kelas) {
        this.kelas = kelas;
    }
    
    public Integer getKode_buku() {
        return kode_buku;
    }
    
    public void setKode_buku(Integer kode_buku) {
        this.kode_buku = kode_buku;
    }
    
    public Date getTgl_pinjam() {
        return tgl_pinjam;
    }
    
    public void setTgl_pinjam(Date tgl_pinjam) {
        this.tgl_pinjam = tgl_pinjam;
    }
    
    public Date getTgl_kembali() {
        return tgl_kembali;
    }
    
    public void setTgl_kembali(Date tgl_kembali) {
        this.tgl_kembali = tgl_kembali;
    }
    
    public Integer getLama_pinjam() {
        return lama_pinjam;
    }
    
    public void setLama_pinjam(Integer lama_pinjam) {
        this.lama_pinjam = lama_pinjam;
    }
    
    public Integer getKode_petugas() {
        return kode_petugas;
    }
    
    public void setKode_petugas(Integer kode_petugas) {
        this.kode_petugas = kode_petugas;
    }
    
    public Integer getKode_sanksi() {
        return kode_sanksi;
    }
    
    public void setKode_sanksi(Integer kode_sanksi) {
        this.kode_sanksi = kode_sanksi;
    }
}
