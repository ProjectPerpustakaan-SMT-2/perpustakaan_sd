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

import entity.Sanksi;
/**
 *
 * @author Hafidz Fadhillah
 */
public class SanksiRepository implements Repository<Sanksi>{
    private static String tableName = Sanksi.tableName;
    
    public List<Sanksi> get() {
        String sql = "SELECT * FROM " + tableName;
        List<Sanksi> sanksis = new ArrayList<>();
        
        try(PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet results = statement.executeQuery();
            
            while(results.next()) {
                sanksis.add(mapToEntity(results));
            }
        } catch (SQLException e) {
            
        }
        
        return sanksis;
    }
    
    public Sanksi get(Integer id) {
        String sql = "SELECT * FROM " + tableName + " WHERE kode_sanksi = ?";
        Sanksi sanksi = new Sanksi();
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapToEntity(rs);
            }
        } catch (SQLException e) {
            
        }
        
        return sanksi;
    }
    
    public List<Sanksi> get(Map<String, Object> values) {
        int iterate = 0;
        String sql = "SELECT * FROM " + tableName + " WHERE ";
        List<Sanksi> sanksis = new ArrayList<>();
        
        for(String valueKey: values.keySet()) {
            if(iterate > 0) sql += " AND ";
            sql += valueKey + " = ?";
            
            iterate++;
        }
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            Database.prepareStmt(stmt, values);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                sanksis.add(mapToEntity(rs));
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        
        return sanksis;
    }
    
    public List<Sanksi> search(Map<String, Object> values) {
        int iterate = 0;
        String sql = "SELECT * FROM " + tableName + " WHERE ";
        List<Sanksi> sanksis = new ArrayList<>();
        
        for(String valueKey: values.keySet()) {
            if(iterate > 0) sql += " OR ";
            sql += valueKey + " LIKE CONCAT( '%',?,'%')";
            
            iterate++;
        }
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            Database.prepareStmt(stmt, values);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                sanksis.add(mapToEntity(rs));
            }
        } catch (SQLException e) {
            
        }
        
        return sanksis;
    }
       
    public Integer add(Sanksi snks) {
        String sql = "INSERT INTO " + tableName + " (`jenis_kerusakan`, `deskripsi_sanksi`, `jumlah_denda`) VALUES (?, ?, ?)";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, snks.getJenis_kerusakan());
            stmt.setString(2, snks.getDeskripsi_sanksi());
            stmt.setInt(3, snks.getJumlah_denda());
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
    
    public boolean update(Sanksi snks) {
        String sql = "UPDATE " + tableName + " SET jenis_kerusakan = ?, deskripsi_sanksi = ?, jumlah_denda = ? WHERE kode_sanksi = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, snks.getJenis_kerusakan());
            stmt.setString(2, snks.getDeskripsi_sanksi());
            stmt.setInt(3, snks.getJumlah_denda());
            stmt.setInt(4, snks.getKode_sanksi());

            stmt.executeUpdate();
            return stmt.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean delete(int id) {
        String sql = "DELETE FROM " + tableName + " WHERE kode_sanksi = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
            return stmt.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    private Sanksi mapToEntity(ResultSet result) throws SQLException {
        Sanksi sanksi = new Sanksi(
            result.getString("jenis_kerusakan"),
            result.getString("deskripsi_sanksi"),
            result.getInt("jumlah_denda")
        );
        
        sanksi.setKode_sanksi(result.getInt("kode_sanksi"));
        return sanksi;
    }
}
