package com.ccosmin.spring_security_app.service;


import com.ccosmin.spring_security_app.dto.UserDto;
import com.ccosmin.spring_security_app.model.RegistrationRequest;
import com.ccosmin.spring_security_app.model.User;

import java.util.List;

public interface UserService {

    boolean checkEmail(String email);
    void deleteUserById(Integer id);
    UserDto registerUser(RegistrationRequest registrationRequest);

    UserDto getLoginUser();

    UserDto getUserById(Integer id);

    List<UserDto> getAllUsers();

    UserDto createUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
