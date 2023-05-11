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

import entity.Penerbit;
import java.sql.Date;
/**
 *
 * @author Hafidz Fadhillah
 */
public class PenerbitRepository implements Repository<Penerbit>{
    private static String tableName = Penerbit.tableName;
    
    public List<Penerbit> get() {
        String sql = "SELECT * FROM " + tableName;
        List<Penerbit> penerbits = new ArrayList<>();
        
        try(PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet results = statement.executeQuery();
            
            while(results.next()) {
                penerbits.add(mapToEntity(results));
            }
        } catch (SQLException e) {
            
        }
        
        return penerbits;
    }
    
    public Penerbit get(Integer id) {
        String sql = "SELECT * FROM " + tableName + " WHERE kode_penerbit = ?";
        Penerbit penerbit = new Penerbit();
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapToEntity(rs);
            }
        } catch (SQLException e) {
            
        }
        
        return penerbit;
    }
    
    public List<Penerbit> get(Map<String, Object> values) {
        int iterate = 0;
        String sql = "SELECT * FROM " + tableName + " WHERE ";
        List<Penerbit> penerbits = new ArrayList<>();
        
        for(String valueKey: values.keySet()) {
            if(iterate > 0) sql += " AND ";
            sql += valueKey + " = ?";
            
            iterate++;
        }
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            Database.prepareStmt(stmt, values);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                penerbits.add(mapToEntity(rs));
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        
        return penerbits;
    }
    
    public List<Penerbit> search(Map<String, Object> values) {
        int iterate = 0;
        String sql = "SELECT * FROM " + tableName + " WHERE ";
        List<Penerbit> penerbits = new ArrayList<>();
        
        for(String valueKey: values.keySet()) {
            if(iterate > 0) sql += " OR ";
            sql += valueKey + " LIKE CONCAT( '%',?,'%')";
            
            iterate++;
        }
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            Database.prepareStmt(stmt, values);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                penerbits.add(mapToEntity(rs));
            }
        } catch (SQLException e) {
            
        }
        
        return penerbits;
    }
    
    
    public Integer add(Penerbit pnrb) {
        String sql = "INSERT INTO " + tableName + " (`penerbit`, `kota_penerbit`, `tahun_terbit`) VALUES (?, ?, ?)";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, pnrb.getPenerbit());
            stmt.setString(2, pnrb.getKota_penerbit());
            stmt.setDate(3, new Date(pnrb.getTahun_tebit().getTime()));
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
    
    public boolean update(Penerbit pnrb) {
        String sql = "UPDATE " + tableName + " SET penerbit = ?, kota_penerbit = ?, tahun_terbit = ?  WHERE kode_penerbit = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pnrb.getPenerbit());
            stmt.setString(2, pnrb.getKota_penerbit());
            stmt.setDate(3, new Date(pnrb.getTahun_tebit().getTime()));
            stmt.setInt(4, pnrb.getKode_penerbit());

            stmt.executeUpdate();
            return stmt.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean delete(int id) {
        String sql = "DELETE FROM " + tableName + " WHERE kode_penerbit = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
            return stmt.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    private Penerbit mapToEntity(ResultSet result) throws SQLException {
        Penerbit penerbit = new Penerbit(
            result.getString("penerbit"),
            result.getString("kota_penerbit"),
            result.getDate("tahun_terbit")
        );
        
        penerbit.setKode_penerbit(result.getInt("kode_penerbit"));
        return penerbit;
    }
}
