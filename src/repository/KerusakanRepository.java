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

import entity.Kerusakan;

/**
 *
 * @author Hafidz Fadhillah
 */
public class KerusakanRepository implements Repository<Kerusakan> {

    private static String tableName = Kerusakan.tableName;

    public List<Kerusakan> get() {
        String sql = "SELECT * FROM " + tableName;
        List<Kerusakan> kerusakans = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                kerusakans.add(mapToEntity(results));
            }
        } catch (SQLException e) {

        }

        return kerusakans;
    }

    public Kerusakan get(Integer id) {
        String sql = "SELECT * FROM " + tableName + " WHERE kode_kerusakan = ?";
        Kerusakan kerusakan = new Kerusakan();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapToEntity(rs);
            }
        } catch (SQLException e) {

        }

        return kerusakan;
    }

    public List<Kerusakan> get(Map<String, Object> values) {
        int iterate = 0;
        String sql = "SELECT * FROM " + tableName + " WHERE ";
        List<Kerusakan> kerusakans = new ArrayList<>();

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
                kerusakans.add(mapToEntity(rs));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return kerusakans;
    }

    public List<Kerusakan> search(Map<String, Object> values) {
        int iterate = 0;
        String sql = "SELECT * FROM " + tableName + " WHERE ";
        List<Kerusakan> kerusakans = new ArrayList<>();

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
                kerusakans.add(mapToEntity(rs));
            }
        } catch (SQLException e) {

        }

        return kerusakans;
    }

    public Integer add(Kerusakan snks) {
        String sql = "INSERT INTO " + tableName + " (`jenis_kerusakan`, `deskripsi_kerusakan`, `nominal_denda`) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, snks.getJenis_kerusakan());
            stmt.setString(2, snks.getDeskripsi_kerusakan());
            stmt.setInt(3, snks.getNominal_denda());
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

    public boolean update(Kerusakan snks) {
        String sql = "UPDATE " + tableName + " SET jenis_kerusakan = ?, deskripsi_kerusakan = ?, nominal_denda = ? WHERE kode_kerusakan = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, snks.getJenis_kerusakan());
            stmt.setString(2, snks.getDeskripsi_kerusakan());
            stmt.setInt(3, snks.getNominal_denda());
            stmt.setInt(4, snks.getKode_kerusakan());

            stmt.executeUpdate();
            return stmt.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM " + tableName + " WHERE kode_kerusakan = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

            return stmt.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Kerusakan mapToEntity(ResultSet result) throws SQLException {
        Kerusakan kerusakan = new Kerusakan(
                result.getString("jenis_kerusakan"),
                result.getString("deskripsi_kerusakan"),
                result.getInt("nominal_denda")
        );

        kerusakan.setKode_kerusakan(result.getInt("kode_kerusakan"));
        return kerusakan;
    }
}
