/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import data.BukuStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Hafidz Fadhillah
 */
public class Buku implements Entity {

    public final static String tableName = "buku";

    private Integer kode_buku;

    @NotBlank(message = "Judul Buku Harus Diisi")
    private String judul_buku;

    @NotBlank(message = "Nama Pengarang Harus Diisi")
    private String nama_pengarang;

    private Long isbn;
    private Penerbit penerbit;
    private String sumber;
    private Integer halaman;

    @NotNull(message = "Pilih Status Buku")
    private BukuStatus bukuStatus;

    private Integer jumlah;
    private String note;
    private Klasifikasi klasifikasi;

    public Buku() {

    }

    public Buku(String judul_buku, String nama_pengarang, Long isbn,
            Penerbit penerbit, String sumber, Integer halaman, BukuStatus bukuStatus,
            Integer jumlah, String note, Klasifikasi klasifikasi) {
        this.judul_buku = judul_buku;
        this.nama_pengarang = nama_pengarang;
        this.isbn = isbn;
        this.penerbit = penerbit;
        this.sumber = sumber;
        this.halaman = halaman;
        this.bukuStatus = bukuStatus;
        this.jumlah = jumlah;
        this.note = note;
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

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
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

    public String getCatatan() {
        return note;
    }

    public void setCatatan(String note) {
        this.note = note;
    }

    public Klasifikasi getKlasifikasi() {
        return klasifikasi;
    }

    public void setKlasifikasi(Klasifikasi klasifikasi) {
        this.klasifikasi = klasifikasi;
    }
}
