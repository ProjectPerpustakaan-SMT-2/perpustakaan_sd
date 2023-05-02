/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import entity.Verifikasi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.Database;

/**
 *
 * @author Hafidz Fadhillah
 */
public class VerifikasiRepository {
    private static String tableName = Verifikasi.tableName;
    private static Connection conn = Database.getConnection();
    
    public List<Verifikasi> get() {
        String sql = "SELECT * FROM " + tableName;
        List<Verifikasi> verifikasis = new ArrayList<>();
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet results = stmt.executeQuery();
            
            while(results.next()) {
                verifikasis.add(mapToEntity(results));
            }
        } catch (SQLException e) {
        }
        
        return verifikasis;
    }
    
    public List<Verifikasi> get(Map<String, Object> values) {
        int iterate = 0;
        String sql = "SELECT * FROM " + tableName + " WHERE ";
        List<Verifikasi> verifikasis = new ArrayList<>();
        
        for(String valueKey: values.keySet()) {
           if(iterate > 0) sql += " AND ";
           sql += valueKey +" = ?";
           
           iterate++;
        }
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            Database.prepareStmt(stmt, values);
            ResultSet results = stmt.executeQuery();
            
            while(results.next()) {
                verifikasis.add(mapToEntity(results));
            }
        } catch (SQLException e) {
        }
        
        return verifikasis;
    }
    
    public Integer add(Verifikasi verif) {
        String sql = "INSERT INTO verifikasi (`kode_petugas`, `kode`) VALUES (?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, verif.getPetugas().getId());
            stmt.setString(2, verif.getKode());
            stmt.executeUpdate();
            
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
    
    public boolean update(Verifikasi verif) {
        String sql = "UPDATE " + tableName + " SET kode = ? WHERE kode_petugas = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, verif.getKode());
            stmt.setInt(2, verif.getPetugas().getId());
            stmt.executeUpdate();
            
            return stmt.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return false;
    }
    
    public boolean delete(Verifikasi verif) {
        String sql = "DELETE FROM " + tableName + " WHERE kode_petugas = ? AND kode = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, verif.getPetugas().getId());
            stmt.setString(2, verif.getKode());           
            stmt.executeUpdate();
            
            return stmt.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return false;
    }
    
     public boolean delete(int ptgKode) {
        String sql = "DELETE FROM " + tableName + " WHERE kode_petugas = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, ptgKode);        
            stmt.executeUpdate();
            
            return stmt.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return false;
    }
    
    private Verifikasi mapToEntity(ResultSet rs) throws SQLException {
        return new Verifikasi(
                new PetugasRepository().get(rs.getInt("kode_petugas")),
                rs.getString("kode")
        );
    }
}
