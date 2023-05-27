/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.Database;
import java.sql.Types;

import entity.Petugas;

/**
 *
 * @author Hafidz Fadhillah
 */
public class PetugasRepository implements Repository<Petugas> {

    private static String tableName = Petugas.tableName;

    public List<Petugas> get() {
        String sql = "SELECT * FROM " + tableName;
        List<Petugas> petugass = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                petugass.add(mapToEntity(results));
            }
        } catch (SQLException e) {

        }

        return petugass;
    }

    public Petugas get(Integer id) {
        String sql = "SELECT * FROM " + tableName + " WHERE kode_petugas = ?";
        Petugas petugas = new Petugas();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (id != null) {
                stmt.setInt(1, id);
            } else {
                stmt.setNull(1, Types.INTEGER);
            }

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapToEntity(rs);
            }
        } catch (SQLException e) {

        }

        return petugas;
    }

    public List<Petugas> get(Map<String, Object> values) {
        int iterate = 0;
        String sql = "SELECT * FROM " + tableName + " WHERE ";
        List<Petugas> petugass = new ArrayList<>();

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
                petugass.add(mapToEntity(rs));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return petugass;
    }

    public List<Petugas> search(Map<String, Object> values) {
        int iterate = 0;
        String sql = "SELECT * FROM " + tableName + " WHERE ";
        List<Petugas> petugass = new ArrayList<>();

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
                petugass.add(mapToEntity(rs));
            }
        } catch (SQLException e) {

        }

        return petugass;
    }

    public Integer add(Petugas ptg) {
        String sql = "INSERT INTO " + tableName + " (`email`, `username`, `password`, `nama`, `tgl_lahir`) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, ptg.getEmail());
            stmt.setString(2, ptg.getUsername());
            stmt.setString(3, ptg.getPassword());
            stmt.setString(4, ptg.getNama());
            stmt.setDate(5, new Date(ptg.getTgl_lahir().getTime()));
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

    public boolean update(Petugas ptg) {
        Petugas petugas = this.get(ptg.getId());
        boolean isChangePass = !petugas.getPassword().equals(ptg.getPassword());
        String sql = "UPDATE " + tableName + " SET nama = ?, email = ?, username = ?, tgl_lahir = ? ";

        if (isChangePass) {
            sql += ", password = ? ";
        }
        sql += "WHERE kode_petugas = " + ptg.getId();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ptg.getNama());
            stmt.setString(2, ptg.getEmail());
            stmt.setString(3, ptg.getUsername());
            stmt.setDate(4, new Date(ptg.getTgl_lahir().getTime()));

            if (isChangePass) {
                stmt.setString(5, ptg.getPassword());
            }
            stmt.executeUpdate();

            return stmt.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM " + tableName + " WHERE kode_petugas = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

            return stmt.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Petugas mapToEntity(ResultSet result) throws SQLException {
        Petugas petugas = new Petugas(
                result.getString("email"),
                result.getString("username"),
                result.getString("password"),
                result.getString("nama"),
                result.getDate("tgl_lahir")
        );

        petugas.setId(result.getInt("kode_petugas"));
        return petugas;
    }
}
