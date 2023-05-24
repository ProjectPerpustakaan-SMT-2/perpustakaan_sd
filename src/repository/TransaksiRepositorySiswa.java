/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import data.TransaksiStatus;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.Database;
import entity.TransaksiSiswa;

/**
 *
 * @author Hafidz Fadhillah
 */
public class TransaksiRepositorySiswa implements Repository<TransaksiSiswa> {

    private static String tableName = TransaksiSiswa.tableName;

    public List<TransaksiSiswa> get() {
        String sql = "SELECT * FROM " + tableName;
        List<TransaksiSiswa> detailTransaksis = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                detailTransaksis.add(mapToEntity(results));
            }
        } catch (SQLException e) {

        }

        return detailTransaksis;
    }

    public TransaksiSiswa get(Integer id) {
        String sql = "SELECT * FROM " + tableName + " WHERE kode_detailTransaksi = ?";
        TransaksiSiswa detailTransaksi = new TransaksiSiswa();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapToEntity(rs);
            }
        } catch (SQLException e) {

        }

        return detailTransaksi;
    }

    public List<TransaksiSiswa> get(Map<String, Object> values) {
        int iterate = 0;
        String sql = "SELECT * FROM " + tableName + " WHERE ";
        List<TransaksiSiswa> detailTransaksis = new ArrayList<>();

        for (String valueKey : values.keySet()) {
            if (iterate > 0) {
                sql += " AND ";
            }
            sql += valueKey + " = ?";

            iterate++;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            Database.prepareStmt(stmt, values);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                detailTransaksis.add(mapToEntity(rs));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return detailTransaksis;
    }

    public List<TransaksiSiswa> search(Map<String, Object> values) {
        int iterate = 0;
        String sql = "SELECT * FROM " + tableName + " WHERE ";
        List<TransaksiSiswa> detailTransaksis = new ArrayList<>();

        for (String valueKey : values.keySet()) {
            if (iterate > 0) {
                sql += " OR ";
            }
            sql += valueKey + " LIKE CONCAT( '%',?,'%')";

            iterate++;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            Database.prepareStmt(stmt, values);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                detailTransaksis.add(mapToEntity(rs));
            }
        } catch (SQLException e) {

        }

        return detailTransaksis;
    }

    public Integer add(TransaksiSiswa trans) {
        String sql = "INSERT INTO " + tableName + " (`nama_peminjam`, `kelas`, `status`, `total_pinjam`, `total_denda`) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, trans.getNama_peminjam());
            stmt.setString(2, trans.getKelas());
            stmt.setString(3, trans.getStatus().toString());
            stmt.setInt(4, trans.getTotal_pinjam());
            stmt.setInt(5, trans.getTotal_denda());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public boolean update(TransaksiSiswa trans) {
        String sql = "UPDATE " + tableName + " SET nama_peminjam = ?, kelas = ?, status = ?, total_pinjam = ?, total_denda = ? WHERE kode_detailTransaksi = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, trans.getNama_peminjam());
            stmt.setString(2, trans.getKelas());
            stmt.setString(3, trans.getStatus().toString());
            stmt.setInt(4, trans.getTotal_pinjam());
            stmt.setInt(5, trans.getTotal_denda());

            stmt.executeUpdate();
            return stmt.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM " + tableName + " WHERE kode_detailTransaksi = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

            return stmt.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private TransaksiSiswa mapToEntity(ResultSet result) throws SQLException {
        int ptgId = result.getInt("kode_petugas");

        TransaksiSiswa detailTransaksi = new TransaksiSiswa(
                result.getString("nama_peminjam"),
                result.getString("kelas"),
                TransaksiStatus.valueOf(result.getString("status")),
                result.getInt("total_pinjam"),
                result.getInt("total_denda")
        );

        detailTransaksi.setKode_transaksi(result.getInt("kode_detailTransaksi"));
        return detailTransaksi;
    }
}
