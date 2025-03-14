package com.ccosmin.spring_security_app.dto;


import lombok.Builder;
import java.util.List;
import java.time.LocalDate;

@Builder
public record UserDto(
        Integer id,
        String username,
        List<RoleDto> roles,
        String firstName,
        String lastName,
        String emailAddress) {}
