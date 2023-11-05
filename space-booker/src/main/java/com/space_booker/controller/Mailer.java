package com.space_booker.controller;

import com.space_booker.model.Booking;
import com.space_booker.model.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mailer {
    private static final String from = "spacebooker.mailer@gmail.com";
    private static final String host = "smtp.gmail.com";

    /*
    Send a notification about the status of a request to
    the user who made that request
     */
    public static void sendNotification(User user, Booking request) {
        String to = user.getEmail();

        //Based on code from https://netcorecloud.com/tutorials/send-email-in-java-using-gmail-smtp/
        //Get System properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("spacebooker.mailer@gmail.com", "fuorzgsbcgpuiviq");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("USSU space booker notification");

            // Generate a notification message containing Booking info
            message.setText(String.format("Your booking for table %d at %s on %s has been %s.\n",
                    request.getTableID(), request.getTime(), request.getDate(), request.getStatus()));
            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
