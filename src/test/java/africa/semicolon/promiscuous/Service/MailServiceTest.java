package africa.semicolon.promiscuous.Service;

import africa.semicolon.promiscuous.dto.requests.EmailNotificationRequest;
import africa.semicolon.promiscuous.dto.requests.Recipient;
import africa.semicolon.promiscuous.dto.requests.Sender;
import africa.semicolon.promiscuous.dto.response.EmailNotificationResponse;
import africa.semicolon.promiscuous.services.MailService;
import africa.semicolon.promiscuous.services.MockEmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MailServiceTest {
    @Autowired
    private  MailService mailService;
    @Test
    public void testThatEmailSendingWorks(){
        String recipient = "xocoma3144@viperace.com";
        String message = "<p>testing our email service</p>";
        String sender = "noreply@promiscous.com";
        String subject = "test email";

        Recipient recipient1 = new Recipient();
        recipient1.setEmail(recipient);

        List<Recipient> recipients = new ArrayList<>();
        recipients.add(recipient1);

        Sender sender1 = new Sender();
        sender1.setEmail(sender);

        EmailNotificationRequest request = new EmailNotificationRequest();
        request.setMailContent(message);
        request.setRecipient(recipients);
        request.setSubject(subject);
//        request.setSender(sender1);
        EmailNotificationResponse emailNotificationResponse = mailService.send(request);
        assertNotNull(emailNotificationResponse);
    }

}
