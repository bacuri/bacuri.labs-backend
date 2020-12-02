package com.bacurilab.backend.service;

import com.bacurilab.backend.model.User;
import com.bacurilab.backend.repository.UserRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public void delete(User user){
        this.userRepository.delete(user);;
    }

    public User update(User user){
        Optional<User> optional = this.userRepository.findById(user.getId());

        if(optional.isPresent()){
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

}
