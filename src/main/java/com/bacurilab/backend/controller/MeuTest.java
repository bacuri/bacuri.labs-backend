package com.bacurilab.backend.controller;

import com.bacurilab.backend.model.DependentProfile;
import com.bacurilab.backend.model.Gender;
import com.bacurilab.backend.model.User;
import com.bacurilab.backend.repository.DependentProfileRepository;
import com.bacurilab.backend.repository.RoleRepository;
import com.bacurilab.backend.repository.UserRepository;
import com.bacurilab.backend.service.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/test")
public class MeuTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DependentProfileRepository dependentProfileRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ContextService contextService;

//    @Autowired
//    EmailService emailService;

    @GetMapping("/tester")
    String minhaString() {
        User u = userRepository.findAll().get(0);
        DependentProfile dp = new DependentProfile();
        dp.setDateOfBirth(LocalDateTime.now());
        dp.setGender(Gender.MALE);
        dp.setLastName("teste");
        dp.setFirstName("setFirstName");
//        associatedDependencyRepository.save(dp);
//
        u.getDependentProfiles().add(dp);
        userRepository.save(u);
        //emailService.sendSimpleMessage("jherson_k-f@hotmail.com", "teste01", "teste02");
        return "ok";
    }
}
