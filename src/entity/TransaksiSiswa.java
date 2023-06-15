/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import data.TransaksiStatus;
import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author Hafidz Fadhillah
 */
public class TransaksiSiswa implements Entity {

    public final static String tableName = "transaksi";

    private Integer kode_transaksi;

    @NotBlank(message = "Nama Harus Diisi")
    private String nama_peminjam;

    @NotBlank(message = "Kelas Harus Diisi")
    private String kelas;

    private TransaksiStatus status;
    private Integer total_pinjam;
    private Integer total_denda;
    private Petugas kode_petugas;

    public TransaksiSiswa() {

    }

    public TransaksiSiswa(String nama_peminjam, String kelas, TransaksiStatus status, Integer total_pinjam, Integer total_denda, Petugas kode_petugas) {
        this.nama_peminjam = nama_peminjam;
        this.kelas = kelas;
        this.status = status;
        this.total_pinjam = total_pinjam;
        this.total_denda = total_denda;
        this.kode_petugas = kode_petugas;
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

    public TransaksiStatus getStatus() {
        return status;
    }

    public void setStatus(TransaksiStatus status) {
        this.status = status;
    }

    public Integer getTotal_pinjam() {
        return total_pinjam;
    }

    public void setTotal_pinjam(Integer total_pinjam) {
        this.total_pinjam = total_pinjam;
    }

    public Integer getTotal_denda() {
        return total_denda;
    }

    public void setTotal_denda(Integer total_denda) {
        this.total_denda = total_denda;
    }

    public Petugas getKode_petugas() {
        return kode_petugas;
    }

    public void setKode_petugas(Petugas kode_petugas) {
        this.kode_petugas = kode_petugas;
    }
}
