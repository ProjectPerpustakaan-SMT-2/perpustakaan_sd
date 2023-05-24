/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Hafidz Fadhillah
 */
public class Kerusakan implements Entity {

    public final static String tableName = "kerusakan";

    private Integer kode_kerusakan;
    private String jenis_kerusakan;
    private String deskripsi_kerusakan;
    private Integer nominal_denda;

    public Kerusakan() {

    }

    public Kerusakan(String jenis_kerusakan, String deskripsi_kerusakan, Integer nominal_denda) {
        this.jenis_kerusakan = jenis_kerusakan;
        this.deskripsi_kerusakan = deskripsi_kerusakan;
        this.nominal_denda = nominal_denda;
    }

    public Integer getKode_kerusakan() {
        return kode_kerusakan;
    }

    public void setKode_kerusakan(Integer kode_kerusakan) {
        this.kode_kerusakan = kode_kerusakan;
    }

    public String getDeskripsi_kerusakan() {
        return deskripsi_kerusakan;
    }

    public void setDeskripsi_kerusakan(String deskripsi_kerusakan) {
        this.deskripsi_kerusakan = deskripsi_kerusakan;
    }

    public String getJenis_kerusakan() {
        return jenis_kerusakan;
    }

    public void setJenis_kerusakan(String jenis_kerusakan) {
        this.jenis_kerusakan = jenis_kerusakan;
    }

    public Integer getNominal_denda() {
        return nominal_denda;
    }

    public void setNominal_denda(Integer nominal_denda) {
        this.nominal_denda = nominal_denda;
    }
}
