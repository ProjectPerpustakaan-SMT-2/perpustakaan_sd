/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Hafidz Fadhillah
 */
public class Klasifikasi implements Entity {

    public final static String tableName = "klasifikasi";

    private Integer id_klasifikasi;
    private String nama_klasifikasi;
    private Integer kode_ddc;

    public Klasifikasi() {

    }

    public Klasifikasi(Integer kode_ddc, String nama_klasifikasi) {
        this.kode_ddc = kode_ddc;
        this.nama_klasifikasi = nama_klasifikasi;
    }

    public Integer getId_klasifikasi() {
        return id_klasifikasi;
    }

    public void setId_klasifikasi(Integer id_klasifikasi) {
        this.id_klasifikasi = id_klasifikasi;
    }

    public Integer getKode_ddc() {
        return kode_ddc;
    }

    public void setKode_ddc(Integer kode_ddc) {
        this.kode_ddc = kode_ddc;
    }

    public String getNama_klasifikasi() {
        return nama_klasifikasi;
    }

    public void setNama_klasifiksi(String nama_klasifikasi) {
        this.nama_klasifikasi = nama_klasifikasi;
    }
}
