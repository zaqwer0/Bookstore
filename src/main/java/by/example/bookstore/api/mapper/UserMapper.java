package by.example.bookstore.api.mapper;

import by.example.bookstore.api.model.dto.request.UserRequestDto;
import by.example.bookstore.api.model.dto.response.UserResponseDto;
import by.example.bookstore.api.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
  User toUser(UserResponseDto userResponseDto);

  User toUser(UserRequestDto userRequestDto);

  List<UserResponseDto> toUserResponse(List<User> userDtos);

  UserResponseDto toUserDto(User user);
}
