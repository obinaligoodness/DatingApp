package africa.semicolon.promiscuous.services;

import africa.semicolon.promiscuous.dto.requests.RegisterUserRequest;
import africa.semicolon.promiscuous.dto.response.ActivateAccountResponse;
import africa.semicolon.promiscuous.dto.response.ApiResponse;
import africa.semicolon.promiscuous.dto.response.RegisterUserResponse;

public interface UserService {
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest);
    ApiResponse<ActivateAccountResponse> activateUserAccount(String token);
}
