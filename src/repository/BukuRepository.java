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

import entity.Buku;
/**
 *
 * @author Hafidz Fadhillah
 */
public class BukuRepository implements Repository<Buku>{
    private static String tableName = Buku.tableName;
    
    public List<Buku> get() {
        String sql = "SELECT * FROM " + tableName;
        List<Buku> bukus = new ArrayList<>();
        
        try(PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet results = statement.executeQuery();
            
            while(results.next()) {
                bukus.add(mapToEntity(results));
            }
        } catch (SQLException e) {
            
        }
        
        return bukus;
    }
    
    public Buku get(Integer id) {
        String sql = "SELECT * FROM " + tableName + " WHERE kode_buku = ?";
        Buku buku = new Buku();
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapToEntity(rs);
            }
        } catch (SQLException e) {
            
        }
        
        return buku;
    }
    
    public List<Buku> get(Map<String, Object> values) {
        int iterate = 0;
        String sql = "SELECT * FROM " + tableName + " WHERE ";
        List<Buku> bukus = new ArrayList<>();
        
        for(String valueKey: values.keySet()) {
            if(iterate > 0) sql += " AND ";
            sql += valueKey + " = ?";
            
            iterate++;
        }
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            Database.prepareStmt(stmt, values);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                bukus.add(mapToEntity(rs));
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        
        return bukus;
    }
    
    public List<Buku> search(Map<String, Object> values) {
        int iterate = 0;
        String sql = "SELECT * FROM " + tableName + " WHERE ";
        List<Buku> bukus = new ArrayList<>();
        
        for(String valueKey: values.keySet()) {
            if(iterate > 0) sql += " OR ";
            sql += valueKey + " LIKE CONCAT( '%',?,'%')";
            
            iterate++;
        }
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            Database.prepareStmt(stmt, values);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                bukus.add(mapToEntity(rs));
            }
        } catch (SQLException e) {
            
        }
        
        return bukus;
    }
    
    public Integer add(Buku bku) {
        String sql = "INSERT INTO " + tableName + " (`judul_buku`, `nama_pengarang`, `isbn`, `kode_penerbit`, `sumber`, `halaman`, `jumlah`, `kode_ddc`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, bku.getJudul_buku());
            stmt.setString(2, bku.getNama_pengarang());
            stmt.setInt(3, bku.getIsbn());
            stmt.setInt(4, bku.getKode_penerbit());
            stmt.setString(5, bku.getSumber());
            stmt.setInt(6, bku.getHalaman());
            stmt.setInt(7, bku.getJumlah());
            stmt.setInt(8, bku.GetKode_ddc());
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
    
    public boolean update(Buku bku) {
        String sql = "UPDATE "+ tableName +" SET judul_buku = ?, nama_pengarang = ?, isbn = ?, kode_penerbit = ?, sumber = ?, halaman = ?, jumlah = ?, kode_ddc = ? WHERE kode_buku = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bku.getJudul_buku());
            stmt.setString(2, bku.getNama_pengarang());
            stmt.setInt(3, bku.getIsbn());
            stmt.setInt(4, bku.getKode_penerbit());
            stmt.setString(5, bku.getSumber());
            stmt.setInt(6, bku.getHalaman());
            stmt.setInt(7, bku.getJumlah());
            stmt.setInt(8, bku.GetKode_ddc());
            stmt.setInt(9, bku.getKode_buku());

            stmt.executeUpdate();
            return stmt.getUpdateCount() > 0;
        } catch(SQLException e) { e.printStackTrace(); }

        return false;
    }
    
    public boolean delete(int id) {
        String sql = "DELETE FROM " + tableName + " WHERE kode_buku = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
            return stmt.getUpdateCount() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    private Buku mapToEntity(ResultSet result) throws SQLException {
        Buku buku = new Buku(
                result.getString("judul_buku"),
                result.getString("nama_pengarang"),
                result.getInt("isbn"),
                result.getInt("kode_penerbit"),
                result.getString("sumber"),
                result.getInt("halaman"),
                result.getInt("jumlah"),
                result.getInt("kode_ddc")
        );
        
        buku.setKode_buku(result.getInt("kode_buku"));
        return buku;
    }
}
