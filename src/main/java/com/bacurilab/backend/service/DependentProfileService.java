package com.bacurilab.backend.service;

import com.bacurilab.backend.model.DependentProfile;
import com.bacurilab.backend.model.User;
import com.bacurilab.backend.repository.DependentProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class DependentProfileService {

    private UserService userService;
    private DependentProfileRepository dependentProfileRepository;

    public DependentProfileService(DependentProfileRepository dependentProfileRepository, UserService userService) {
        this.dependentProfileRepository = dependentProfileRepository;
        this.userService = userService;
    }

    public User associate(User user, DependentProfile associated) {
        try {
            User updatedUser = this.userService.findByEmail(user.getUsername()).get();
            updatedUser.getDependentProfiles().add(associated);
            return this.userService.save(updatedUser);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public User deassociate(User user, DependentProfile associated) {
        try {
            user.getDependentProfiles().remove(associated);
            return this.userService.save(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public DependentProfile updateAssociation(User user, DependentProfile associated) {
        try {
            user.getDependentProfiles().forEach(dependentProfile -> {
                if (dependentProfile.getId().equals(associated.getId())) {
                    dependentProfile.setCic(ObjectUtils.defaultIfNull(associated.getCic(), dependentProfile.getCic()));
                    dependentProfile.setImage(ObjectUtils.defaultIfNull(associated.getImage(), dependentProfile.getImage()));
                    dependentProfile.setFirstName(ObjectUtils.defaultIfNull(associated.getFirstName(), dependentProfile.getFirstName()));
                    dependentProfile.setDateOfBirth(ObjectUtils.defaultIfNull(associated.getDateOfBirth(), dependentProfile.getDateOfBirth()));
                    dependentProfile.setGender(ObjectUtils.defaultIfNull(associated.getGender(), dependentProfile.getGender()));
                }
            });
            this.userService.save(user);
            return associated;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public DependentProfile getById(Long profile){
        Optional<DependentProfile> optional = this.dependentProfileRepository.findById(profile);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new RuntimeException();
    }
}
