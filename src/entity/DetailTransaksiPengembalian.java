/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 *
 * @author Hafidz Fadhillah
 */
public class DetailTransaksiPengembalian implements Entity {

    public final static String tableName = "detail_transaksi";

    private Integer kode_detail_transaksi;
    private Buku kode_buku;
    private Date tgl_pinjam;
    private Date tgl_kembali;

    @NotNull(message = "Pilih Jenis Kerusakan Buku")
    private Kerusakan kode_kerusakan;
    private Integer jumlah;
    private Integer nominal_denda;
    private TransaksiPengembalian kode_transaksi;

    public DetailTransaksiPengembalian() {

    }

    public DetailTransaksiPengembalian(Buku kode_buku, Date tgl_pinjam, Date tgl_kembali, Integer jumlah, Kerusakan kode_kerusakan, Integer nominal_denda, TransaksiPengembalian kode_transaksi) {
        this.kode_buku = kode_buku;
        this.tgl_pinjam = tgl_pinjam;
        this.tgl_kembali = tgl_kembali;
        this.jumlah = jumlah;
        this.kode_kerusakan = kode_kerusakan;
        this.nominal_denda = nominal_denda;
        this.kode_transaksi = kode_transaksi;
    }

    public Integer getKode_Detailtransaksi() {
        return kode_detail_transaksi;
    }

    public void setKode_Detailtransaksi(Integer kode_detail_transaksi) {
        this.kode_detail_transaksi = kode_detail_transaksi;
    }

    public Buku getKode_buku() {
        return kode_buku;
    }

    public void setKode_buku(Buku kode_buku) {
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

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Kerusakan getKodeKerusakan() {
        return kode_kerusakan;
    }

    public void setKodeKerusakan(Kerusakan kode_kerusakan) {
        this.kode_kerusakan = kode_kerusakan;
    }

    public Integer getNominal_denda() {
        return nominal_denda;
    }

    public void setNominal_denda(Integer nominal_denda) {
        this.nominal_denda = nominal_denda;
    }

    public TransaksiPengembalian getKode_transaksi() {
        return kode_transaksi;
    }

    public void setKode_transaksi(TransaksiPengembalian kode_transaksi) {
        this.kode_transaksi = kode_transaksi;
    }
}
