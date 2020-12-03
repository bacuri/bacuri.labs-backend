package com.bacurilab.backend.service;

import com.bacurilab.backend.model.DependentProfile;
import com.bacurilab.backend.model.Image;
import com.bacurilab.backend.model.User;
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

    public User register(User user, String role) throws Exception {
        try {
            DependentProfile associated = new DependentProfile();

            associated.setFirstName(user.getFirstName());
            associated.setLastName(user.getLastName());
            associated.setGender(user.getGender());
            associated.setDateOfBirth(LocalDateTime.now());
            associated.setImage(Image.DEFAULT);

            user.getDependentProfiles().add(associated);
            user.getRole().add(this.roleService.getRoleById(role));
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            return this.userService.save(user);
        } catch (Exception e) {
            throw new Exception("User already exists");
        }
    }
}
