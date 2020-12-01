package com.bacurilab.backend.service;

import com.bacurilab.backend.model.Role;
import com.bacurilab.backend.model.User;
import com.bacurilab.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userDetailsService")
public class CustomUserDetailService implements UserDetailsService {

    //get user from the database, via Hibernate
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        //CUSTOM USER HERE vvv
        User user = userRepository.findByEmail(username).get();

        List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());

        //if you're implementing UserDetails you wouldn't need to call this method and instead return the User as it is
        return buildUserForAuthentication(user, authorities);
//        return user;

    }

    // Converts user to spring.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        User newUser = user;
        newUser.setAuthorities(authorities);
        return newUser;
    }

    private List<GrantedAuthority> buildUserAuthority(List<Role> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // add user's authorities
        for (Role userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority("ROLE_"+userRole.getName()));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }

}