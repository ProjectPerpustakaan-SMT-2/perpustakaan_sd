/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 *
 * @author Hafidz Fadhillah
 */
public class ChartData {

    private String month;
    private int buku, peminjam;

    public ChartData(String month, int buku, int peminjam) {
        this.month = month;
        this.buku = buku;
        this.peminjam = peminjam;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getBuku() {
        return buku;
    }

    public void setBuku(Integer buku) {
        this.buku = buku;
    }

    public Integer getPeminjam() {
        return peminjam;
    }

    public void setPeminjam(Integer peminjam) {
        this.peminjam = peminjam;
    }
}
