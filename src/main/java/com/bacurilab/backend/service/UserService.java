package com.bacurilab.backend.service;

import com.bacurilab.backend.model.User;
import com.bacurilab.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user){
        this.userRepository.save(user);
        return user;
    }

}
