/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import util.Database;

/**
 *
 * @author Hafidz Fadhillah
 */
public class DashboardPRespository {

    private static Connection conn = Database.getConnection();
    private static Calendar cal = Calendar.getInstance();

    public int getTotalPetugas() {
        int totalPetugas = 0;
        String sql = "SELECT COUNT(*) AS totalPetugas from petugas";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalPetugas = rs.getInt("totalPetugas");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalPetugas;
    }

    public int getBukuDipinjam(Date date) {
        cal.setTime(date);

        int totalBukuDipinjam = 0;
        String sql = "SELECT SUM(jumlah) AS jumlahPinjam FROM detail_transaksi "
                + "INNER JOIN transaksi ON detail_transaksi.kode_transaksi = transaksi.kode_transaksi "
                + "WHERE transaksi.status = 'dipinjam' AND MONTH(detail_transaksi.tgl_pinjam) = ? AND YEAR(detail_transaksi.tgl_pinjam) = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cal.get(Calendar.MONTH) + 1);
            stmt.setInt(2, cal.get(Calendar.YEAR));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalBukuDipinjam = rs.getInt("jumlahPinjam");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalBukuDipinjam;
    }

    public int getBukuBelumKembali(Date date) {
        cal.setTime(date);

        int totalBelumKembali = 0;

        String sql = "SELECT SUM(jumlah) AS jumlahPinjam FROM detail_transaksi "
                + "INNER JOIN transaksi ON detail_transaksi.kode_transaksi = transaksi.kode_transaksi "
                + "WHERE transaksi.status = 'dipinjam' AND detail_transaksi.tgl_kembali < ? "
                + "AND MONTH(detail_transaksi.tgl_kembali) = ? AND YEAR(detail_transaksi.tgl_kembali) = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(cal.getTime().getTime()));
            stmt.setInt(2, cal.get(Calendar.MONTH) + 1);
            stmt.setInt(3, cal.get(Calendar.YEAR));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalBelumKembali = rs.getInt("jumlahPinjam");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalBelumKembali;
    }

    public int getTotalBuku() {
        int totalBuku = 0;
        String sql = "SELECT COUNT(*) AS totalBuku from buku";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalBuku = rs.getInt("totalBuku");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalBuku;
    }
}
