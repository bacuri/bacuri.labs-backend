package com.bacurilab.backend.service;

import com.bacurilab.backend.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegisterService {
    private UserService userService;
    private RoleService roleService;

    public RegisterService(UserService userService, RoleService roleService) {

        this.userService = userService;
        this.roleService = roleService;
    }

    public User register(User user, String role) {
        try {

            DependentProfile associated = new DependentProfile();
            associated.setFirstName(user.getFirstName());
            associated.setLastName(user.getLastName());
            associated.setGender(user.getGender());
            associated.setDateOfBirth(LocalDateTime.now());
            associated.setImage(Image.DEFAULT);

            user.getDependentProfiles().add(associated);
            user.getRole().add(this.roleService.getRoleById(role));

            return this.userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User update(User user) {
        try {
            return this.userService.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(User user) throws Exception {
        try {
            this.userService.delete(user);
        } catch (Exception e) {
            throw new Exception(e);

        }
    }
}
