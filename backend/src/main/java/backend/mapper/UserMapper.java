package backend.mapper;

import backend.model.dto.UserDto;
import backend.model.entity.User;
import backend.model.request.CreateUserRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapUserRequestToUser(CreateUserRequest request);

    UserDto mapUserToUserDto(User user);
    List<UserDto> mapUserToUserDto(List<User> user);
}
