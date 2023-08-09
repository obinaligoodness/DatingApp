package africa.semicolon.promiscuous.services;

import africa.semicolon.promiscuous.dto.requests.EmailNotificationRequest;
import africa.semicolon.promiscuous.dto.response.EmailNotificationResponse;

public interface MailService {
    EmailNotificationResponse send(EmailNotificationRequest emailNotificationRequest);
}
