package com.ccosmin.spring_security_app.mapper;


import com.ccosmin.spring_security_app.dto.RoleDto;
import com.ccosmin.spring_security_app.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RoleMapper {

    public RoleDto roleEntityToDto(Role role){
        return RoleDto.builder()
                .role(role.getRole())
                .build();
    }

    public List<RoleDto> roleListEntityToDto(List<Role> roles){
        return roles.stream()
                .map(role -> roleEntityToDto(role))
                .toList();
    }

    public Role roleDtoToEntity(RoleDto roleDto){
        return Role.builder()
                .role(roleDto.role())
                .build();
    }

    public List<Role> roleListDtoToEntity(List<RoleDto> roleDtos){
        return roleDtos.stream()
                .map(roleDto -> roleDtoToEntity(roleDto))
                .toList();
    }
}