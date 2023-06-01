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
import java.sql.Types;

/**
 *
 * @author Hafidz Fadhillah
 */
public class TransaksiRepositorySiswa implements Repository<TransaksiSiswa> {

    private static String tableName = TransaksiSiswa.tableName;

    public List<TransaksiSiswa> get() {
        String sql = "SELECT * FROM " + tableName + " WHERE kode_petugas IS NULL";
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
        String sql = "SELECT * FROM " + tableName + " WHERE kode_transaksi = ?";
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
        String sql = "SELECT * FROM " + tableName + " WHERE kode_petugas IS NULL AND ";
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
        String sql = "INSERT INTO " + tableName + " (`nama_peminjam`, `kelas`, `status`, `total_pinjam`, `total_denda`, `kode_petugas`) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, trans.getNama_peminjam());
            stmt.setString(2, trans.getKelas());
            stmt.setString(3, trans.getStatus().toString());
            stmt.setInt(4, trans.getTotal_pinjam());
            stmt.setInt(5, trans.getTotal_denda());

            Integer id = trans.getKode_petugas() != null ? trans.getKode_petugas().getId() : null;
            if (id != null) {
                stmt.setInt(6, id);
            } else {
                stmt.setNull(6, Types.INTEGER);
            }

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
        String sql = "UPDATE " + tableName + " SET nama_peminjam = ?, kelas = ?, status = ?, total_pinjam = ?, total_denda = ?, kode_petugas = ? WHERE kode_transaksi = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, trans.getNama_peminjam());
            stmt.setString(2, trans.getKelas());
            stmt.setString(3, trans.getStatus().toString());
            stmt.setInt(4, trans.getTotal_pinjam());
            stmt.setInt(5, trans.getTotal_denda());

            Integer id = trans.getKode_petugas() != null ? trans.getKode_petugas().getId() : null;
            if (id != null) {
                stmt.setInt(6, id);
            } else {
                stmt.setNull(6, Types.INTEGER);
            }

            stmt.setInt(7, trans.getKode_transaksi());

            stmt.executeUpdate();
            return stmt.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM " + tableName + " WHERE kode_transaksi = ?";

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

        TransaksiSiswa transaksiSiswa = new TransaksiSiswa(
                result.getString("nama_peminjam"),
                result.getString("kelas"),
                TransaksiStatus.valueOf(result.getString("status")),
                result.getInt("total_pinjam"),
                result.getInt("total_denda"),
                new PetugasRepository().get(ptgId)
        );

        transaksiSiswa.setKode_transaksi(result.getInt("kode_transaksi"));
        return transaksiSiswa;
    }
}
