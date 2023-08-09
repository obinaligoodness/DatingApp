package africa.semicolon.promiscuous.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${mail.api.key}")
    private String mailApiKey;
    @Value("abc1234.erhlvslkhsfl25r3")
    private String testToken;

    public String getMailApiKey(){
        return mailApiKey;
    }
    public String getTestToken(){
        return testToken;
    }
}