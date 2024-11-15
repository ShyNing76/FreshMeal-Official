/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ADMIN
 */
public class Email implements Serializable {

     private final String eFrom = "freshmeal05072004@gmail.com";
     private final String ePass = "ossh cptm obpd gmol";

    public void sendEmail(String subject, String messgage, String to) {
        // Properties : Khai báo các thuộc tính
        Properties props = new Properties();

        //Su dung server nao de gui mail- smtp host
        props.put("mail.smtp.host", "smtp.gmail.com");

        // TLS 587 SSL 465
//        props.put("mail.smtp.port", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // yêu cầu đăng nhập
        props.put("mail.smtp.auth", "true");

        props.put("mail.smtp.starttls.enable", "true");

        // tạo Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(eFrom, ePass);
            }
        };

        // tạo Phiên làm việc cho tài khoản
        Session session = Session.getInstance(props, auth);

        // tạo một tin nhắn
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.setFrom(new InternetAddress(eFrom));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            // tiêu đề
            msg.setSubject(subject, "UTF-8");
            // nội dung
            msg.setContent(messgage, "text/html; charset=UTF-8");
            // gửi mail
            Transport.send(msg);
        } catch (Exception e) {
            System.out.println("Send Email Failed!");
            e.printStackTrace();
        }
    }

    public String subjectNewOrder() {
        return "F.R.E.S.H MEAL - Đặt hàng thành công";
    }

    public String subjectApproveOrder() {
        return "F.R.E.S.H MEAL - Đơn đặt hàng đã được chấp nhận";
    }

    public String subjectFinishOrder() {
        return "F.R.E.S.H MEAL - Đơn hàng đã vận chuyển thành công";
    }

    public String subjectCancelOrder() {
        return "F.R.E.S.H MEAL - Xác nhận hủy đơn hàng";
    }
    
    public String subjectDeliveryOrder() {
        return "F.R.E.S.H MEAL - Đơn hàng đang giao đến bạn.";
    }

    public String subjectRefurnOrder() {
        return "F.R.E.S.H MEAL - Đơn hàng đã được yêu cầu trả lại";
    }

    public String subjectFinishRefurnOrder() {
        return "F.R.E.S.H MEAL - Đơn hàng đã được chấp nhận trả lại";
    }

    public String subjectFillUserInfor() {
        return "F.R.E.S.H MEAL - Điền thông tin cá nhân";
    }

}
