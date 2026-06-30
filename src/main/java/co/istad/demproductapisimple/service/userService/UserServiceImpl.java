package co.istad.demproductapisimple.service.userService;

import co.istad.demproductapisimple.dto.user.CreateUserRequest;
import co.istad.demproductapisimple.dto.user.UserResponse;
import co.istad.demproductapisimple.entity.Profile;
import co.istad.demproductapisimple.mapper.UserMapper;
import co.istad.demproductapisimple.repository.ProfileRepository;
import co.istad.demproductapisimple.repository.UserRepository;
import co.istad.demproductapisimple.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @Override
    public UserResponse createUser(CreateUserRequest request) {

        var user = userMapper.toUser(request);
        var profile = new Profile();

        profile.setBio(request.bio());
        profile.setProfileUrl(request.profileUrl());
        // linked profile to user
        profile.setUser(user);
        user.setProfile(profile);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream().map(userMapper::toUserResponse)
                .toList();
    }
}