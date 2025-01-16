package com.ccosmin.spring_security_app.service;


import com.ccosmin.spring_security_app.dto.UserDto;
import com.ccosmin.spring_security_app.mapper.RoleMapper;
import com.ccosmin.spring_security_app.mapper.UserMapper;
import com.ccosmin.spring_security_app.model.RegistrationRequest;
import com.ccosmin.spring_security_app.model.User;
import com.ccosmin.spring_security_app.repository.RoleRepository;
import com.ccosmin.spring_security_app.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    @Override
    public boolean checkEmail(String email) {
        return userRepository.existsByEmailAddress(email);
    }
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);  // Delete user by ID
    }

    @Override
    public UserDto registerUser(RegistrationRequest registrationRequest) {
        User user = User.builder()
                .username(registrationRequest.getUsername())
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .password(registrationRequest.getPassword())
                .emailAddress(registrationRequest.getEmailAddress())
                .role((roleRepository.findByRole("USER")))
                .build();




        return this.createUser(user);
    }

    public UserDto getLoginUser(){
        return userMapper.userEntityToDto(userRepository.findLoginUser().orElse(null));
    }

    public UserDto getUserById(Integer id){
        return userMapper.userEntityToDto(userRepository.findById(id).orElse(null));
    }

    public List<UserDto> getAllUsers(){
        return userMapper.userListEntityToDto(userRepository.findAll());
    }

    public UserDto createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.userEntityToDto(userRepository.save(user));
    }

    @Override
    public void updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmailAddress(user.getEmailAddress());
        existingUser.setRoles(user.getRoles());
        userRepository.save(existingUser);
    }


    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
