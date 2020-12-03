package com.bacurilab.backend.controller;

import com.bacurilab.backend.repository.RoleRepository;
import com.bacurilab.backend.repository.UserRepository;
import com.bacurilab.backend.service.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test2")
public class Meu2Test {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ContextService contextService;

    @GetMapping("/tester")
    String minhaString() {
        return "ok";
    }
}
