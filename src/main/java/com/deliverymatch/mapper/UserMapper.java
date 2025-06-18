package com.deliverymatch.mapper;

import com.deliverymatch.dto.user.UserDto;
import com.deliverymatch.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    List<UserDto> toDtoList(List<User> users);

    void updateUserFromDto(UserDto dto, @MappingTarget User user);
}

