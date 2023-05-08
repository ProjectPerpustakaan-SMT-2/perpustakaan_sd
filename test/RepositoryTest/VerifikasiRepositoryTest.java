/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RepositoryTest;


import entity.Petugas;
import entity.Verifikasi;

import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import repository.PetugasRepository;
import repository.VerifikasiRepository;
/**
 *
 * @author Hafidz Fadhillah
 */
public class VerifikasiRepositoryTest {
    private static VerifikasiRepository VerifRepo = new VerifikasiRepository();
    private static PetugasRepository ptgRepo = new PetugasRepository();
    private static Map<String, Object> keywords;
    
    private static Petugas petugas;
    private static Integer petugasId;
    private static String kode = "2302"; 
    
    
//    public static void main(String[] args) {
//        // Test Add
//        petugasId = ptgRepo.add(new Petugas(
//                "hafidz@gmail.com", "hafidz", "hafidz123", "Hafidz", new Date(), Jabatan.Admin
//        ));
//        
//        petugas = ptgRepo.get(petugasId);
//        keywords = new HashMap<String, Object>() {{
//            put("kode_petugas", petugasId);
//            put("kode", kode);
//        }};
//        
//        Verifikasi verif = new Verifikasi(
//                petugas,
//                "2302"
//        );
//        
//        Assert.assertTrue(VerifRepo.add(verif) != 0);
//    }
    
//    public static void main(String[] args) {
//        // Test Get        
//        Verifikasi verif = VerifRepo.get(new HashMap<String, Object>() {{
//            put("kode_petugas", 3);
//            put("kode", kode);
//        }}).get(0);
//        
//        Assert.assertEquals(kode, verif.getKode());
//    }
    
//    public static void main(String[] args) {
//        // Test Get All
//        String kode = "2311";
//        
//        petugasId = ptgRepo.add(new Petugas(
//                "hafidz@gmail.com", "hafidz", "hafidz123", "Hafidz", new Date(), Jabatan.Admin
//        ));
//        
//        petugas = ptgRepo.get(petugasId);
//        Verifikasi verif = new Verifikasi(petugas, kode);
//        
//        VerifRepo.add(verif);
//        List<Verifikasi> verifikasis = VerifRepo.get();
//        
//        Assert.assertTrue(verifikasis.size() > 1);
//        Assert.assertEquals(kode, verifikasis.get(1).getKode());
//    }
    
//    public static void main(String[] args) {
//        // Test Update
//        String kodeBaru = "1121";
//        
//        keywords = new HashMap<String, Object>() {{
//            put("kode_petugas", 3);
//            put("kode", 2302);
//        }};
//        
//        Verifikasi verif = VerifRepo.get(keywords).get(0);
//        
//        verif.setKode(kodeBaru);
//        keywords.put("kode", kodeBaru);
//        VerifRepo.update(verif);
//        verif = VerifRepo.get(keywords).get(0);
//        
//        Assert.assertEquals(kodeBaru, verif.getKode());
//        kode = kodeBaru;
//    }
    
//    public static void main(String[] args) {
//        // Test Delete
//        
//        keywords = new HashMap<String, Object>() {{
//            put("kode_petugas", 5);
//            put("kode", 2311);
//        }};
//        
//        Verifikasi verif = VerifRepo.get(keywords).get(0);
//        Assert.assertTrue(VerifRepo.delete(verif));
//    }
}
