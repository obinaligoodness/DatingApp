package africa.semicolon.promiscuous.util;

import africa.semicolon.promiscuous.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class AppUtilsTest {

    @Test
    public void generateToken(){
        String email = "test@email.com";
        String token = AppUtils.generateToken(email);
        log.info("generated token-->{}",token);
        assertThat(token).isNotNull();
    }
    @Test
    public void testGenerateActivationLink(){
        String activationLink = AppUtils.generateActivationLink("test@email.com");
        log.info("activation link -->{}",activationLink);
        assertThat(activationLink).isNotNull();
        assertThat(activationLink).contains("http://localhost:8080/activate");
    }
}
