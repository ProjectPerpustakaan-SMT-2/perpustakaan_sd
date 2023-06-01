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
import entity.TransaksiPengembalian;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Hafidz Fadhillah
 */
public class TransaksiPengembalianRepository implements Repository<TransaksiPengembalian> {

    private static String tableName = TransaksiPengembalian.tableName;
    private static Calendar cal = Calendar.getInstance();

    public List<TransaksiPengembalian> get() {
        cal.setTime(new Date());

        String sql = "SELECT DISTINCT transaksi.*, detail_transaksi.kode_transaksi FROM " + tableName
                + " INNER JOIN detail_transaksi ON detail_transaksi.kode_transaksi = transaksi.kode_transaksi "
                + "WHERE transaksi.status = 'dipinjam' AND detail_transaksi.tgl_kembali < ?";

        List<TransaksiPengembalian> transaksis = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(cal.getTime().getTime()));
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                transaksis.add(mapToEntity(results));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return transaksis;
    }

    public TransaksiPengembalian get(Integer id) {
        String sql = "SELECT * FROM " + tableName + " WHERE kode_transaksi = ?";
        TransaksiPengembalian transaksi = new TransaksiPengembalian();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapToEntity(rs);
            }
        } catch (SQLException e) {

        }

        return transaksi;
    }

    public List<TransaksiPengembalian> get(Map<String, Object> values) {
        int iterate = 0;
        String sql = "SELECT * FROM " + tableName + " WHERE ";
        List<TransaksiPengembalian> transaksis = new ArrayList<>();

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
                transaksis.add(mapToEntity(rs));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return transaksis;
    }

    public List<TransaksiPengembalian> search(Map<String, Object> values) {
        int iterate = 0;
        cal.setTime(new Date());

        String sql = "SELECT DISTINCT transaksi.*, detail_transaksi.kode_transaksi FROM " + tableName
                + " INNER JOIN detail_transaksi ON detail_transaksi.kode_transaksi = transaksi.kode_transaksi "
                + "WHERE transaksi.status = 'dipinjam' AND detail_transaksi.tgl_kembali < ? AND ";

        List<TransaksiPengembalian> transaksis = new ArrayList<>();

        List<String> valueKeys = new ArrayList<>(values.keySet());
        int totalKeys = valueKeys.size();

        for (int i = 0; i < totalKeys; i++) {
            String valueKey = valueKeys.get(i);

            if (iterate > 0) {
                sql += " OR ";
            }

            sql += valueKey + " LIKE CONCAT('%', ?, '%')";
            iterate++;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(cal.getTime().getTime()));

            for (int i = 0; i < totalKeys; i++) {
                String valueKey = valueKeys.get(i);
                String value = (String) values.get(valueKey);
                stmt.setString(i + 2, value);
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                transaksis.add(mapToEntity(rs));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return transaksis;
    }

    public Integer add(TransaksiPengembalian trans) {
        String sql = "INSERT INTO " + tableName + " (`nama_peminjam`, `kelas`, `status`, `total_pinjam`, `total_denda`, `kode_petugas`) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, trans.getNama_peminjam());
            stmt.setString(2, trans.getKelas());
            stmt.setString(3, trans.getStatus().toString());
            stmt.setInt(4, trans.getTotal_pinjam());
            stmt.setInt(5, trans.getTotal_denda());
            stmt.setInt(6, trans.getPetugas().getId());

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

    public boolean update(TransaksiPengembalian trans) {
        String sql = "UPDATE " + tableName + " SET nama_peminjam = ?, kelas = ?, status = ?, total_pinjam = ?, total_denda = ?, kode_petugas = ? WHERE kode_transaksi = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, trans.getNama_peminjam());
            stmt.setString(2, trans.getKelas());
            stmt.setString(3, trans.getStatus().toString());
            stmt.setInt(4, trans.getTotal_pinjam());
            stmt.setInt(5, trans.getTotal_denda());
            stmt.setInt(6, trans.getPetugas().getId());
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

    private TransaksiPengembalian mapToEntity(ResultSet result) throws SQLException {
        int ptgId = result.getInt("kode_petugas");

        TransaksiPengembalian transaksi = new TransaksiPengembalian(
                result.getString("nama_peminjam"),
                result.getString("kelas"),
                TransaksiStatus.valueOf(result.getString("status")),
                result.getInt("total_pinjam"),
                result.getInt("total_denda"),
                new PetugasRepository().get(ptgId)
        );

        transaksi.setKode_transaksi(result.getInt("kode_transaksi"));
        return transaksi;
    }
}
