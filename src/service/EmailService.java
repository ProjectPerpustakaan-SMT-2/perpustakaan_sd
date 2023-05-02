/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Hafidz Fadhillah
 */
public class EmailService {
    public static int generateOtp() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000);
        return otp;
    }    

    public boolean sendEmail(String ToEmail, Integer otp) throws MessagingException {
        final String username = "akunverifikasi211@gmail.com";
        final String password = "yvwgmvoxoqgkxvmt";
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
           }
        });
        
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ToEmail));
        message.setSubject("KODE OTP VERIFIKASI AKUN");
        String otpMessage = "Kode OTP Anda adalah: " + otp;
        message.setText(otpMessage);
        
        try {
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            System.out.println(e);
            return false;
        }        
    }
}
