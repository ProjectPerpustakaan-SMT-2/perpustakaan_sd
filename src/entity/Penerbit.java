/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 *
 * @author Hafidz Fadhillah
 */
public class Penerbit implements Entity {

    public final static String tableName = "penerbit";

    private Integer kode_penerbit;

    @NotBlank(message = "Penerbit Harus Diisi")
    private String penerbit;

    @NotBlank(message = "Kota Penerbit Harus Diisi")
    private String kota_penerbit;

    @NotNull(message = "Tahun Terbit Harus Diisi")
    private Date tahun_terbit;

    public Penerbit() {

    }

    public Penerbit(String penerbit, String kota_penerbit, Date tahun_terbit) {
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

    public Date getTahun_tebit() {
        return tahun_terbit;
    }

    public void setTahun_terbit(Date tahun_terbit) {
        this.tahun_terbit = tahun_terbit;
    }
}
