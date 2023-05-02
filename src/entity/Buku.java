/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Hafidz Fadhillah
 */
public class Buku implements Entity {
    public final static String tableName = "buku";
    
    private Integer kode_buku;
    private String judul_buku;
    private String nama_pengarang;
    private Integer isbn;
    private Integer kode_penerbit;
    private String sumber;
    private Integer halaman;
    private Integer jumlah;
    private Integer kode_ddc;
    
    public Buku() {
        
    }
    
    public Buku(Integer kode_buku, String judul_buku, String nama_pengarang, Integer isbn,
                Integer kode_penerbit, String sumber, Integer halaman, Integer jumlah, Integer kode_ddc) {
        this.kode_buku = kode_buku;
        this.judul_buku = judul_buku;
        this.nama_pengarang = nama_pengarang;
        this.isbn = isbn;
        this.kode_penerbit = kode_penerbit;
        this.sumber = sumber;
        this.halaman = halaman;
        this.jumlah = jumlah;
        this.kode_ddc = kode_ddc;
    }
    
    public Integer getKode_buku() {
        return kode_buku;
    }
    
    public void setKode_buku(Integer kode_buku) {
        this.kode_buku = kode_buku;
    }
    
    public String getJudul_buku() {
        return judul_buku;
    }
    
    public void setJudul_buku(String judul_buku) {
        this.judul_buku = judul_buku;
    }
    
    public String getNama_pengarang() {
        return nama_pengarang;
    }
    
    public void setNama_pengarang(String nama_pengarang) {
        this.nama_pengarang = nama_pengarang;
    }
    
    public Integer getIsbn() {
        return isbn;
    }
    
    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }
    
    public Integer getKode_penerbit() {
        return kode_penerbit;
    }
    
    public void setKode_penerbit(Integer kode_penerbit) {
        this.kode_penerbit = kode_penerbit;
    }
    
    public String getSumber() {
        return sumber;
    }
    
    public void getSumber(String sumber) {
        this.sumber = sumber;
    }
    
    public Integer getHalaman() {
        return halaman;
    }
    
    public void setHalaman(Integer halaman) {
        this.halaman = halaman;
    }
    
    public Integer getJumlah() {
        return jumlah;
    }
    
    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }
    
    public Integer GetKode_ddc() {
        return kode_ddc;
    }
    
    public void setKode_ddc(Integer kode_ddc) {
        this.kode_ddc = kode_ddc;
    }
}
