/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class EmailSendUtil {
//    
//public String ramdom() {
//        Random rd = new Random();
//        int numbercode = rd.nextInt(10000) + 1000;
//        return String.valueOf(numbercode);
//    }

//    public static void main(String[] args) {
//      // Cấu hình SMTP server của Gmail
//      String host = "smtp.office365.com";
//      String port = "587";
//      String username = "quanlydep@hotmail.com";
//      String password = "hotmail@123";
//      
//      // Cấu hình thông tin người gửi và người nhận
//      String from = "quanlydep@hotmail.com";
//      String to = "chienlai2k3@gmail.com";
//      
//      // Cấu hình nội dung email
//      String subject = "Test email";
//      String body = "This is a test email sent from Java.";
//      
//      Properties props = new Properties();
//      props.put("mail.smtp.auth", "true");
//      props.put("mail.smtp.starttls.enable", "true");
//      props.put("mail.smtp.host", host);
//      props.put("mail.smtp.port", port);
//      
//      Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//         protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//            return new javax.mail.PasswordAuthentication(username, password);
//         }
//      });
//
//      try {
//         Message message = new MimeMessage(session);
//         message.setFrom(new InternetAddress(from));
//         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//         message.setSubject(subject);
//         message.setText(body);
//         Transport.send(message);
//         System.out.println("Sent email successfully.");
//      } catch (MessagingException e) {
//         throw new RuntimeException(e);
//      }
//   }
    public static boolean sendEmail(String to, String Code) {
        // Cấu hình SMTP server của Gmail
        String host = "smtp.office365.com";
        String port = "587";
        String username = "quanlydep@hotmail.com";
        String password = "hotmail@123";

        // Cấu hình thông tin người gửi và người nhận
        String from = "quanlydep@hotmail.com";

        // Cấu hình nội dung email
        String subject = "Ma OTP";
        String body = "Xin chào\n"
                + "Chúng tôi đã nhận được yêu cầu đặt lại mật khẩu tài khoản của bạn.\n"
                + "Nhập mã OTP sau đây:" + Code;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            

            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
//            message.setText(body);
            message.setContent(body, "text/html; charset=utf-8");
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            Transport.send(message);
            System.out.println("Sent email successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
