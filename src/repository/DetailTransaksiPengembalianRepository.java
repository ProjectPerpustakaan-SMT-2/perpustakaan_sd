/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.Database;
import entity.DetailTransaksiPengembalian;
import entity.Kerusakan;
import entity.TransaksiPengembalian;
import java.sql.Date;
import java.sql.Types;

/**
 *
 * @author Hafidz Fadhillah
 */
public class DetailTransaksiPengembalianRepository implements Repository<DetailTransaksiPengembalian> {

    private static String tableName = DetailTransaksiPengembalian.tableName;

    public List<DetailTransaksiPengembalian> get() {
        String sql = "SELECT * FROM " + tableName;
        List<DetailTransaksiPengembalian> detailTransaksis = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                detailTransaksis.add(mapToEntity(results));
            }
        } catch (SQLException e) {

        }

        return detailTransaksis;
    }

    public DetailTransaksiPengembalian get(Integer id) {
        String sql = "SELECT * FROM " + tableName + " WHERE kode_detail_transaksi = ?";
        DetailTransaksiPengembalian detailTransaksi = new DetailTransaksiPengembalian();

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

    public List<DetailTransaksiPengembalian> get(Map<String, Object> values) {
        int iterate = 0;
        String sql = "SELECT * FROM " + tableName + " WHERE ";
        List<DetailTransaksiPengembalian> detailTransaksis = new ArrayList<>();

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

    public List<DetailTransaksiPengembalian> search(Map<String, Object> values) {
        int iterate = 0;
        String sql = "SELECT * FROM " + tableName + " WHERE ";
        List<DetailTransaksiPengembalian> detailTransaksis = new ArrayList<>();

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

    public Integer add(DetailTransaksiPengembalian detTrans) {
        String sql = "INSERT INTO " + tableName + " (`kode_buku`, `tgl_pinjam`, `tgl_kembali`, `jumlah`, `kode_kerusakan`, `nominal_denda`, `kode_transaksi`) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, detTrans.getKode_buku().getKode_buku());
            stmt.setDate(2, new Date(detTrans.getTgl_pinjam().getTime()));
            stmt.setDate(3, new Date(detTrans.getTgl_kembali().getTime()));
            stmt.setInt(4, detTrans.getJumlah());

            Integer id = detTrans.getKodeKerusakan() != null ? detTrans.getKodeKerusakan().getKode_kerusakan() : null;
            if (id != null) {
                stmt.setInt(5, id);
            } else {
                stmt.setNull(5, Types.INTEGER);
            }

            stmt.setInt(6, detTrans.getNominal_denda());
            stmt.setInt(7, detTrans.getKode_transaksi().getKode_transaksi());

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

    public boolean update(DetailTransaksiPengembalian detTrans) {
        String sql = "UPDATE " + tableName + " SET kode_buku = ?, tgl_pinjam = ?, tgl_kembali = ?, jumlah = ?, kode_kerusakan = ?,nominal_denda = ?, kode_transaksi = ? WHERE kode_detail_transaksi = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, detTrans.getKode_buku().getKode_buku());
            stmt.setDate(2, new Date(detTrans.getTgl_pinjam().getTime()));
            stmt.setDate(3, new Date(detTrans.getTgl_kembali().getTime()));
            stmt.setInt(4, detTrans.getJumlah());

            Integer id = detTrans.getKodeKerusakan() != null ? detTrans.getKodeKerusakan().getKode_kerusakan() : null;
            if (id != null) {
                stmt.setInt(5, id);
            } else {
                stmt.setNull(5, Types.INTEGER);
            }

            stmt.setInt(6, detTrans.getNominal_denda());
            stmt.setInt(7, detTrans.getKode_transaksi().getKode_transaksi());
            stmt.setInt(8, detTrans.getKode_Detailtransaksi());

            stmt.executeUpdate();
            return stmt.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM " + tableName + " WHERE kode_detail_transaksi = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

            return stmt.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<DetailTransaksiPengembalian> customQuery(String query, List<Object> values) {
        List<DetailTransaksiPengembalian> detailTransaksis = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            Database.prepareStmt(stmt, values);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                detailTransaksis.add(mapToEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detailTransaksis;
    }

    private DetailTransaksiPengembalian mapToEntity(ResultSet result) throws SQLException {
        int bukuId = result.getInt("kode_buku");
        int kerusakanId = result.getInt("kode_kerusakan");
        int transaksiId = result.getInt("kode_transaksi");

        DetailTransaksiPengembalian detailTransaksiPengembalian = new DetailTransaksiPengembalian(
                new BukuRepository().get(bukuId),
                result.getDate("tgl_pinjam"),
                result.getDate("tgl_kembali"),
                result.getInt("jumlah"),
                new KerusakanRepository().get(kerusakanId),
                result.getInt("nominal_denda"),
                new TransaksiPengembalianRepository().get(transaksiId)
        );

        detailTransaksiPengembalian.setKode_Detailtransaksi(result.getInt("kode_detail_transaksi"));
        return detailTransaksiPengembalian;
    }
}
