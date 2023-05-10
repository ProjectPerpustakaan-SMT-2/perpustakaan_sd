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

import entity.Klasifikasi;
/**
 *
 * @author Hafidz Fadhillah
 */
public class KlasifikasiRepository implements Repository<Klasifikasi>{
    private static String tableName = Klasifikasi.tableName;
    
    public List<Klasifikasi> get() {
        String sql = "SELECT * FROM " + tableName;
        List<Klasifikasi> klasifikasis = new ArrayList<>();
        
        try(PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet results = statement.executeQuery();
            
            while(results.next()) {
                klasifikasis.add(mapToEntity(results));
            }
        } catch (SQLException e) {
            
        }
        
        return klasifikasis;
    }
    
    public Klasifikasi get(Integer id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id_klasifikasi = ?";
        Klasifikasi klasifikasi = new Klasifikasi();
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapToEntity(rs);
            }
        } catch (SQLException e) {
            
        }
        
        return klasifikasi;
    }
    
    public List<Klasifikasi> get(Map<String, Object> values) {
        int iterate = 0;
        String sql = "SELECT * FROM " + tableName + " WHERE ";
        List<Klasifikasi> klasifikasis = new ArrayList<>();
        
        for(String valueKey: values.keySet()) {
            if(iterate > 0) sql += " AND ";
            sql += valueKey + " = ?";
            
            iterate++;
        }
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            Database.prepareStmt(stmt, values);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                klasifikasis.add(mapToEntity(rs));
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        
        return klasifikasis;
    }
    
    public List<Klasifikasi> search(Map<String, Object> values) {
        int iterate = 0;
        String sql = "SELECT * FROM " + tableName + " WHERE ";
        List<Klasifikasi> klasifikasis = new ArrayList<>();
        
        for(String valueKey: values.keySet()) {
            if(iterate > 0) sql += " OR ";
            sql += valueKey + " LIKE CONCAT( '%',?,'%')";
            
            iterate++;
        }
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            Database.prepareStmt(stmt, values);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                klasifikasis.add(mapToEntity(rs));
            }
        } catch (SQLException e) {
            
        }
        
        return klasifikasis;
    }
    
    public boolean isKodeDdcExists(Integer id) {
        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE kode_ddc = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            
        }
        
        return false;
    }
    
    public Integer add(Klasifikasi klsf) {
        String sql = "INSERT INTO " + tableName + " (`kode_ddc`, `nama_klasifikasi`) VALUES (?, ?)";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, klsf.getKode_ddc());
            stmt.setString(2, klsf.getNama_klasifikasi());
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
    
    public boolean update(Klasifikasi klsf) {
        Klasifikasi klasifikasi = this.get(klsf.getId_klasifikasi());
        boolean isChangeKodeDDC = !klasifikasi.getKode_ddc().equals(klsf.getKode_ddc());

        if (isChangeKodeDDC && isKodeDdcExists(klsf.getKode_ddc())) {
            throw new IllegalArgumentException("Kode DDC " + klsf.getKode_ddc() + " telah digunakan!");
        }

        String sql = "UPDATE " + tableName + " SET kode_ddc = ?, nama_klasifikasi = ? WHERE id_klasifikasi = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, klsf.getKode_ddc());
            stmt.setString(2, klsf.getNama_klasifikasi());
            stmt.setInt(3, klsf.getId_klasifikasi());

            stmt.executeUpdate();
            return stmt.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean delete(int id) {
        String sql = "DELETE FROM " + tableName + " WHERE id_klasifikasi = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
            return stmt.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    private Klasifikasi mapToEntity(ResultSet result) throws SQLException {
        Klasifikasi klasifikasi = new Klasifikasi(
            result.getInt("kode_ddc"),
            result.getString("nama_klasifikasi")
        );
        
        klasifikasi.setId_klasifikasi(result.getInt("id_klasifikasi"));
        return klasifikasi;
    }
}
