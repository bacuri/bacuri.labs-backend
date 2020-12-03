package com.bacurilab.backend.service;

import com.bacurilab.backend.model.User;
import com.bacurilab.backend.repository.UserRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User save(User user) {
        this.userRepository.saveAndFlush(user);
        return user;
    }

    public void delete(User user) {
        this.userRepository.delete(user);
    }

    public User findByEmail(String email) throws RuntimeException {
        Optional<User> optional = this.userRepository.findByEmail(email);
        if (optional.isPresent()) {
            User user = optional.get();
            user.setPassword(null);
            return user;
        }
        return null;
    }

    public User info(User user) throws RuntimeException {
        Optional<User> optional = this.userRepository.findById(user.getId());
        if (optional.isPresent()) {
            User userInfo = optional.get();
            userInfo.setPassword(null);
            return userInfo;
        }
        throw new RuntimeException();
    }

    public User update(User user) {
        Optional<User> optional = this.userRepository.findById(user.getId());

        if (optional.isPresent()) {
            User oldUser = optional.get();

            User newUser = new User();
            newUser.setDateOfBirth(ObjectUtils.defaultIfNull(user.getDateOfBirth(), oldUser.getDateOfBirth()));
            newUser.setCic(ObjectUtils.defaultIfNull(user.getCic(), oldUser.getCic()));
            newUser.setGender(ObjectUtils.defaultIfNull(user.getGender(), oldUser.getGender()));
            newUser.setFirstName(ObjectUtils.defaultIfNull(user.getFirstName(), oldUser.getFirstName()));
            newUser.setLastName(ObjectUtils.defaultIfNull(user.getLastName(), oldUser.getLastName()));

            return this.userRepository.save(newUser);
        }

        return null;
    }

    public User changePassword(User user, String oldPassword, String newPassword) throws PasswordException {

        if (passwordEncoder.encode(oldPassword).equals(passwordEncoder.encode(user.getPassword()))) {
            user.setPassword(passwordEncoder.encode(newPassword));
            return this.userRepository.save(user);
        }
        throw new PasswordException("");
    }

}
