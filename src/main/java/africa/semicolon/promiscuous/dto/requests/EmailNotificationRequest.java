package africa.semicolon.promiscuous.dto.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static africa.semicolon.promiscuous.utils.AppUtils.APP_EMAIL;
import static africa.semicolon.promiscuous.utils.AppUtils.APP_NAME;

@Setter
@Getter
public class EmailNotificationRequest {
    private final Sender sender = new Sender(APP_EMAIL, APP_NAME);
    @JsonProperty("to")
    private List<Recipient> recipient;
    @JsonProperty("cc")
    private List<String> copiedEmails;
    @JsonProperty("htmlContent")
    private String mailContent;
    private String textContent;
    private String subject;
}
