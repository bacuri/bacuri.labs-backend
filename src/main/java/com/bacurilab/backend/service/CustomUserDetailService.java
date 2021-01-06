package com.bacurilab.backend.service;

import com.bacurilab.backend.model.Role;
import com.bacurilab.backend.model.User;
import com.bacurilab.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("userDetailsService")
public class CustomUserDetailService implements UserDetailsService {

    //get user from the database, via Hibernate
    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username) {

        //CUSTOM USER HERE vvv
        Optional<User> optional = userRepository.findByEmail(username);
        if (optional.isPresent()) {
            User user = optional.get();

            List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());

            //if you're implementing UserDetails you wouldn't need to call this method and instead return the User as it is
            return buildUserForAuthentication(user, authorities);
        }
        return null;

    }

    // Converts user to spring.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        User newUser = user;
        newUser.setAuthorities(authorities);
        return newUser;
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<>();

        // add user's authorities
        for (Role userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority("ROLE_" + userRole.getName()));
        }

        List<GrantedAuthority> result = new ArrayList<>(setAuths);

        return result;
    }

}