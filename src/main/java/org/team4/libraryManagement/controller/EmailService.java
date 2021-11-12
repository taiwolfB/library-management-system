package org.team4.libraryManagement.controller;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;
import org.team4.libraryManagement.ConnectionFactory;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EmailService {

    private static final Logger LOGGER = Logger.getLogger(EmailService.class.getName());

    public void sendEmail(String fromEmail, String toEmail, String subject, String content) throws IOException {
        Email from = new Email(fromEmail);
        Email to = new Email(toEmail);

        Mail mail = new Mail(from, subject, to, new Content("text/html", content));

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        Response response = sg.api(request);

        LOGGER.log(Level.INFO, "Email response code: " + response.getStatusCode());
        LOGGER.log(Level.INFO, "Email response header: " + response.getHeaders());
        LOGGER.log(Level.INFO, "Email response body: " + response.getBody());
    }
}