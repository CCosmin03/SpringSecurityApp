package com.ccosmin.spring_security_app.repository;



import com.ccosmin.spring_security_app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String role);
}