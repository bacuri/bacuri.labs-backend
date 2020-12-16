package com.bacurilab.backend.service;

import com.bacurilab.backend.model.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegisterService {
    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    public RegisterService(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
    }

    public User register(User user, String role, Enum<Platform> platform) throws Exception {
        try {
            DependentProfile associated = new DependentProfile();

            associated.setFirstName(user.getFirstName());
            associated.setLastName(user.getLastName());
            associated.setGender(user.getGender());
            associated.setDateOfBirth(LocalDateTime.now());
            associated.setImage(Image.DEFAULT);
            associated.setProfile("APP".equals(platform.name()) ? ProfileType.PATIENT : ProfileType.PROFESSIONAL);

            user.getDependentProfiles().add(associated);
            user.getRole().add(this.roleService.getRoleById(role));
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            return this.userService.save(user);
        } catch (Exception e) {
            throw new Exception("User already exists");
        }
    }
}
