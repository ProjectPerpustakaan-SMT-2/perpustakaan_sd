/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RepositoryTest;

import data.Jabatan;
import entity.Petugas;
import java.util.Date;
import java.util.HashMap;
import junit.framework.Assert;

import repository.Repository;
import repository.PetugasRepository;
/**
 *
 * @author Hafidz Fadhillah
 */
public class PetugasRepositoryTest {
    private static Repository<Petugas> repo = new PetugasRepository();
    private static Integer petugasId = 1;
      
//    public static void main(String[] args) {
//    // Test Add
//            Petugas ptg = new Petugas(
//                "johndoe@gmail.com",
//                "johndoe", 
//                "John123", 
//                   "John Doe", 
//                        new Date(), 
//                    Jabatan.Admin
//            );
//
//            petugasId = repo.add(ptg);
//            Assert.assertTrue(petugasId > 0);
//    }
    
//    public static void main(String[] args) {
//    // Test Get
//        Petugas ptg = repo.get(petugasId);
//        Petugas ptg2 = repo.get(new HashMap<String, Object>() {{
//            put("kode_petugas", petugasId);
//        }}).get(0);
//        
//        Assert.assertEquals("John Doe", ptg.getNama());
//        Assert.assertEquals("johndoe", ptg.getUsername());
//        Assert.assertEquals("John123", ptg2.getPassword());
//    }
    
//    public static void main(String[] args) {
//    // Test Get All
//        Petugas ptg = new Petugas(
//            "safira@gmail.com",
//            "safira", 
//            "Safira123", 
//            "Safira", 
//                     new Date(), 
//            Jabatan.Petugas
//        );
//        
//        Assert.assertTrue(repo.add(ptg) > 0);
//        Assert.assertTrue(repo.get().size() > 1);
//    }
    
//    public static void main(String[] args) {
//    // Test Update   
//        Petugas petugas = repo.get(petugasId);
//        petugas.setNama("John Lenon");
//        
//        repo.update(petugas);
//        petugas = repo.get(petugasId);
//        
//        Assert.assertNotSame("John", petugas.getNama());
//        Assert.assertEquals("John Lenon", petugas.getNama());
//    }
    
//    public static void main(String[] args) {
//    // Test Delete
//        Assert.assertTrue(repo.delete(2));
//    }
}
