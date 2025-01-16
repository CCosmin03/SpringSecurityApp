package com.ccosmin.spring_security_app.mapper;

import com.ccosmin.spring_security_app.dto.UserDto;
import com.ccosmin.spring_security_app.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.*;

@RequiredArgsConstructor
@Component
public class UserMapper {

    private final RoleMapper roleMapper;

    public UserDto userEntityToDto(User user){
        return UserDto.builder()
                .username(user.getUsername())
                .roles(roleMapper.roleListEntityToDto(user.getRoles()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .emailAddress(user.getEmailAddress())
                .build();
    }

    public List<UserDto> userListEntityToDto(List<User> users){
        return users.stream()
                .map(user -> userEntityToDto(user))
                .toList();
    }

    public User userDtoToEntity(UserDto userDto, String password){
        return User.builder()
                .username(userDto.username())
                .password(password)
                .roles(roleMapper.roleListDtoToEntity(userDto.roles()))
                .firstName(userDto.firstName())
                .lastName(userDto.lastName())
                .emailAddress(userDto.emailAddress())
                .build();
    }

    public List<User> userListDtoToEntity(List<UserDto> userDtos, String password){
        return userDtos.stream()
                .map(userDto -> userDtoToEntity(userDto, password))
                .toList();
    }
}