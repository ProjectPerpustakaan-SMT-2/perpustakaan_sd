/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import data.TransaksiStatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import repository.TransaksiRepository;

/**
 *
 * @author Hafidz Fadhillah
 */
public class LaporanService {

    private Date bulan;
    private String transaksiStatus;
    private TransaksiRepository repo;
    private List<Object> values;
    private StringBuilder qb;

    public LaporanService(String transaksiStatus, Date bulan) {
        this.transaksiStatus = transaksiStatus;
        this.bulan = bulan;

        values = new ArrayList<>();

        qb = new StringBuilder();
        qb.append("SELECT * FROM transaksi");
    }

    public void generate() {
//String query = composeQuery(true);
    }

    private String composeQuery(boolean isJoined) {
        if (isJoined) {
            qb.append(" JOIN detail_transaksi ON transaksi.kode_transaksi = detail_transaksi.kode_transaksi");
        }

        qb.append(" WHERE");

        if ((bulan != null)) {
            qb.append(" ")
        }
    }
}
