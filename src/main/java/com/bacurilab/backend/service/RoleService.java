package com.bacurilab.backend.service;

import com.bacurilab.backend.model.Role;
import com.bacurilab.backend.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Role getRoleById(String name) throws Exception {
        Optional<Role> role = this.roleRepository.findById(name);
        if (role.isPresent())
            return role.get();
        throw new Exception();
    }

    public List<Role> getRoles() {
        return this.roleRepository.findAll();
    }

}
