package com.ccosmin.spring_security_app.controller;


import com.ccosmin.spring_security_app.dto.RoleDto;
import com.ccosmin.spring_security_app.dto.UserDto;
import com.ccosmin.spring_security_app.mapper.UserMapper;
import com.ccosmin.spring_security_app.model.User;
import com.ccosmin.spring_security_app.service.RoleService;
import com.ccosmin.spring_security_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final RoleService roleService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public String viewUsers(Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/users";
    }

    @GetMapping("/add")
    @Secured("ROLE_ADMIN")
    public String addUserForm(Model model) {
        model.addAttribute("user", new UserDto(null, "", List.of(), "", "", ""));
        List<RoleDto> availableRoles = roleService.getAllRoles();
        model.addAttribute("availableRoles", availableRoles);
        return "users/add_user";  // Return add user form
    }


    @PostMapping("/add")
    @Secured("ROLE_ADMIN")
    public String createUser(@ModelAttribute("user") UserDto userDto, RedirectAttributes redirectAttributes) {
        if (userDto.roles() == null) {
            userDto = new UserDto(userDto.id(), userDto.username(), List.of(), userDto.firstName(), userDto.lastName(), userDto.emailAddress());
        }
        User userEntity = userMapper.userDtoToEntity(userDto, "defaultPassword123");
        userService.createUser(userEntity);
        redirectAttributes.addFlashAttribute("successMessage", "User added successfully!");
        return "redirect:/admin/users";
    }


    @GetMapping("/edit/{id}")
    @Secured("ROLE_ADMIN")
    public String editUserForm(@PathVariable Integer id, Model model) {
        UserDto userDto = userService.getUserById(id);
        if (userDto == null) {
            throw new IllegalArgumentException("User not found for ID: " + id);
        }
        model.addAttribute("user", userDto);
        return "users/edit_user";
    }


    @PostMapping("/edit/{id}")
    @Secured("ROLE_ADMIN")
    public String updateUser(@PathVariable Integer id, @ModelAttribute("user") UserDto userDto, RedirectAttributes redirectAttributes) {
        if (userDto.roles() == null) {
            userDto = new UserDto(userDto.id(), userDto.username(), List.of(), userDto.firstName(), userDto.lastName(), userDto.emailAddress());
        }
        User userEntity = userMapper.userDtoToEntity(userDto, "unchangedPassword");
        userEntity.setId(id);
        userService.updateUser(userEntity);
        redirectAttributes.addFlashAttribute("successMessage", "User updated successfully!");
        return "redirect:/admin/users";
    }


    @GetMapping("/delete/{id}")
    @Secured("ROLE_ADMIN")
    public String deleteUserForm(@PathVariable Integer id, Model model) {
        UserDto userDto = userService.getUserById(id);
        model.addAttribute("user", userDto);
        return "users/delete_user";
    }

    @PostMapping("/delete/{id}")
    @Secured("ROLE_ADMIN")
    public String deleteUser(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        userService.deleteUserById(id);
        redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully!");
        return "redirect:/admin/users";
    }
}
