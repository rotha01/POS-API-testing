package co.istad.demproductapisimple.mapper;

import co.istad.demproductapisimple.dto.user.CreateUserRequest;
import co.istad.demproductapisimple.dto.user.UserResponse;
import co.istad.demproductapisimple.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "profileUrl", source = "profile.profileUrl")
    @Mapping(target="bio", source = "profile.bio")
    UserResponse toUserResponse(User user);
    User toUser(CreateUserRequest request);
}
