/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import data.BukuStatus;

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
    private Penerbit penerbit;
    private String sumber;
    private Integer halaman;
    private BukuStatus bukuStatus;
    private Integer jumlah;
    private Klasifikasi klasifikasi;

    public Buku() {

    }

    public Buku(String judul_buku, String nama_pengarang, Integer isbn,
            Penerbit penerbit, String sumber, Integer halaman, BukuStatus bukuStatus,
            Integer jumlah, Klasifikasi klasifikasi) {
        this.judul_buku = judul_buku;
        this.nama_pengarang = nama_pengarang;
        this.isbn = isbn;
        this.penerbit = penerbit;
        this.sumber = sumber;
        this.halaman = halaman;
        this.bukuStatus = bukuStatus;
        this.jumlah = jumlah;
        this.klasifikasi = klasifikasi;
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

    public Penerbit getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(Penerbit penerbit) {
        this.penerbit = penerbit;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }

    public Integer getHalaman() {
        return halaman;
    }

    public void setHalaman(Integer halaman) {
        this.halaman = halaman;
    }

    public BukuStatus getBukuStatus() {
        return bukuStatus;
    }

    public void setBukuStatus(BukuStatus bukuStatus) {
        this.bukuStatus = bukuStatus;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Klasifikasi getKlasifikasi() {
        return klasifikasi;
    }

    public void setKlasifikasi(Klasifikasi klasifikasi) {
        this.klasifikasi = klasifikasi;
    }
}
