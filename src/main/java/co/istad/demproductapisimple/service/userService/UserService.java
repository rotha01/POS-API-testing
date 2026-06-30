package co.istad.demproductapisimple.service.userService;

import co.istad.demproductapisimple.dto.user.CreateUserRequest;
import co.istad.demproductapisimple.dto.user.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest request );
    List<UserResponse> getAllUsers();
}