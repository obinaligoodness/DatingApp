package africa.semicolon.promiscuous.services;

import africa.semicolon.promiscuous.config.AppConfig;
import africa.semicolon.promiscuous.dto.requests.EmailNotificationRequest;
import africa.semicolon.promiscuous.dto.requests.Recipient;
import africa.semicolon.promiscuous.dto.requests.RegisterUserRequest;
import africa.semicolon.promiscuous.dto.response.ActivateAccountResponse;
import africa.semicolon.promiscuous.dto.response.ApiResponse;
import africa.semicolon.promiscuous.dto.response.RegisterUserResponse;
import africa.semicolon.promiscuous.exceptions.PromiscuousBaseException;
import africa.semicolon.promiscuous.models.User;
import africa.semicolon.promiscuous.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static africa.semicolon.promiscuous.utils.AppUtils.*;

@Service
@AllArgsConstructor
@Slf4j
public class PromiscuousUserService implements UserService{
    private final UserRepository userRepository;
    private final MailService mailService;
    private final AppConfig appConfig;


    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
       String email =  registerUserRequest.getEmail();
       String password = registerUserRequest.getPassword();
       User user = new User();
       user.setEmail(email);
       user.setPassword(password);
       User savedUser = userRepository.save(user);
        EmailNotificationRequest emailNotificationRequest = buildEmailRequest(savedUser);
       RegisterUserResponse registerUserResponse = new RegisterUserResponse();
       registerUserResponse.setMessage("Registration Successful, check your mailbox for verification token");
        return registerUserResponse;
    }

    @Override
    public ApiResponse<?> activateUserAccount(String token) {
        if(token.equals(appConfig.getTestToken())){
            ApiResponse<?> activateAccountResponse =
                    ApiResponse
                            .builder()
                            .data(new ActivateAccountResponse("Account activation successfully"))
                            .build();
            return activateAccountResponse;
        }
        if(validateToken(token)){
            String email = extractEmailFrom(token);
            User foundUser = userRepository.readByEmail(email).orElseThrow();
        }
        throw new PromiscuousBaseException("Account activation was not successful");

    }
    private EmailNotificationRequest buildEmailRequest(User savedUser) {
        EmailNotificationRequest emailNotificationRequest = new EmailNotificationRequest();
        List<Recipient> recipients = new ArrayList<>();
        Recipient recipient = new Recipient(savedUser.getEmail());
        recipient.setEmail(savedUser.getEmail());
        recipients.add(recipient);
        emailNotificationRequest.setRecipient(recipients);
        emailNotificationRequest.setSubject(WELCOME_MAIL_SUBJECT);
        String activationLink = generateActivationLink(savedUser.getEmail());
        String emailTemplate = getMailTemplate();
        String mailContent = String.format(emailTemplate,activationLink);
        emailNotificationRequest.setMailContent(mailContent);
        mailService.send(emailNotificationRequest);
        return emailNotificationRequest;
    }
}
