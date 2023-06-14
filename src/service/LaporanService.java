/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import repository.TransaksiRepository;
import util.Database;

/**
 *
 * @author Hafidz Fadhillah
 */
public class LaporanService {

    private static Connection conn = Database.getConnection();

    private Integer bulan;
    private String transaksiStatus, namaBulan;
    private TransaksiRepository repo;
    private List<Object> values;
    private StringBuilder qb;

    public LaporanService(String transaksiStatus, Integer bulan, String namaBulan) {
        this.transaksiStatus = transaksiStatus;
        this.bulan = bulan;
        this.namaBulan = namaBulan;

        values = new ArrayList<>();

        qb = new StringBuilder();
        qb.append("SELECT transaksi.*, detail_transaksi.*, buku.*, kerusakan.* FROM transaksi");
    }

    public void generate() {
        String query = composeQuery(true);

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            Database.prepareStmt(stmt, values);
            ResultSet rs = stmt.executeQuery();

            InputStream jasperFile = getClass().getResourceAsStream("LaporanPerpustakaan.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(jasperFile);

            HashMap<String, Object> nL = new HashMap<>();
            nL.put("namaLaporan", transaksiStatus);
            nL.put("namaBulan", namaBulan);

            JRResultSetDataSource rsDataSource = new JRResultSetDataSource(rs);
            JasperPrint jp = JasperFillManager.fillReport(jr, nL, rsDataSource);

            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String composeQuery(boolean isJoined) {
        if (isJoined) {
            qb.append(" INNER JOIN detail_transaksi ON detail_transaksi.kode_transaksi = transaksi.kode_transaksi "
                    + "INNER JOIN buku ON detail_transaksi.kode_buku = buku.kode_buku "
                    + "INNER JOIN kerusakan ON detail_transaksi.kode_kerusakan = kerusakan.kode_kerusakan");
        }

        qb.append(" WHERE");

        if ((bulan != null) && (transaksiStatus.equals("Peminjaman"))) {
            qb.append(" transaksi.status = 'dipinjam' AND MONTH(detail_transaksi.tgl_pinjam) = ?");
            values.add(bulan);
        } else if ((bulan != null) && (transaksiStatus.equals("Pengembalian"))) {
            qb.append(" transaksi.status = 'dikembalikan' AND MONTH(detail_transaksi.tgl_kembali) = ?");
            values.add(bulan);
        } else if ((bulan != null) && (transaksiStatus.equals("Sanksi"))) {
            qb.append(" detail_transaksi.kode_kerusakan IN (2,3,4) AND MONTH(detail_transaksi.tgl_kembali) = ?");
            values.add(bulan);
        }

        return qb.toString();
    }
}
