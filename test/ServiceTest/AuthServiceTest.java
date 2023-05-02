/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceTest;

import entity.Petugas;
import org.junit.Assert;
import service.AuthService;
import repository.PetugasRepository;
import repository.Repository;

/**
 *
 * @author Hafidz Fadhillah
 */
public class AuthServiceTest {
    private static Repository<Petugas> repo = new PetugasRepository();
    private static AuthService srv = new AuthService();
    private static Petugas petugas;
    
    public static void main(String[] args) {
        petugas = repo.get(1);
        
        Assert.assertTrue(srv.login(petugas.getUsername(), petugas.getPassword()));
        Assert.assertFalse(srv.login("adm00n", "admin123"));
    }
}
