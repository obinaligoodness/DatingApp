package africa.semicolon.promiscuous.Service;

import africa.semicolon.promiscuous.dto.requests.RegisterUserRequest;
import africa.semicolon.promiscuous.dto.response.ActivateAccountResponse;
import africa.semicolon.promiscuous.dto.response.ApiResponse;
import africa.semicolon.promiscuous.dto.response.RegisterUserResponse;
import africa.semicolon.promiscuous.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    RegisterUserRequest registerUserRequest;
    @BeforeEach
    public void setUp(){
         registerUserRequest = new RegisterUserRequest();
    }
    @Test
    public void testThatUserCanRegister(){
        RegisterUserRequest registerUserRequest =  new RegisterUserRequest();
        registerUserRequest.setEmail("calakex263@viperace.com");
        registerUserRequest.setPassword("password");
        RegisterUserResponse registerUserResponse = userService.register(registerUserRequest);
        assertNotNull(registerUserResponse);
        assertNotNull(registerUserResponse.getMessage());
    }
    @Test
    public void testActivateUserAccount(){
        RegisterUserResponse response = userService.register(registerUserRequest);
        assertNotNull(response);
        ApiResponse<ActivateAccountResponse> activateAccountResponseApiResponse = userService.activateUserAccount("abc1234.erhlvslkhsfl25r3");

    }
}
