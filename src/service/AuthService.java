/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import data.SessionData;
import entity.Petugas;
import entity.Verifikasi;
import java.util.HashMap;
import java.util.List;
import javax.mail.MessagingException;

import repository.Repository;
import repository.PetugasRepository;
import repository.VerifikasiRepository;

/**
 *
 * @author Hafidz Fadhillah
 */
public class AuthService {

    private VerifikasiRepository verifRepo = new VerifikasiRepository();
    private Repository<Petugas> ptgRepo = new PetugasRepository();
    private EmailService mailService = new EmailService();

    public boolean login(String username, String password) {
        List<Petugas> ptgs = ptgRepo.get(new HashMap<String, Object>() {
            {
                put("username", username);
                put("password", password);
            }
        });

        if (ptgs.size() < 1) {
            return false;
        }

        SessionData.petugas = ptgs.get(0);
        return true;
    }

    public boolean loginRFID(String username) {
        List<Petugas> ptgs = ptgRepo.get(new HashMap<String, Object>() {
            {
                put("username", username);
            }
        });

        if (ptgs.size() < 1) {
            return false;
        }

        SessionData.petugas = ptgs.get(0);
        return true;
    }

    public boolean forgetPassword(String username, String email) throws MessagingException {
        List<Petugas> ptgs = ptgRepo.get(new HashMap<String, Object>() {
            {
                put("username", username);
                put("email", email);
            }
        });

        if (ptgs.size() < 1) {
            return false;
        }

        String code = Integer.toString(EmailService.generateOtp());

        if (verifRepo.add(new Verifikasi(ptgs.get(0), code)) == 0) {
            return false;
        }
        if (!mailService.sendEmail(email, Integer.valueOf(code))) {
            return false;
        }
        SessionData.petugas = ptgs.get(0);

        return true;
    }

    public boolean resetPassword(Petugas petugas, String code) {
        List<Verifikasi> accs = verifRepo.get(new HashMap<String, Object>() {
            {
                put("kode_petugas", petugas.getId());
                put("kode", code);
            }
        });

        if (accs.size() < 1) {
            return false;
        }
        if (!ptgRepo.update(petugas)) {
            return false;
        }

        return true;
    }

    public void resetPassword(Petugas petugas) {
        ptgRepo.update(petugas);
        SessionData.petugas = petugas;
        verifRepo.delete(petugas.getId());
    }
}
