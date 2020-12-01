package com.bacurilab.backend.repository;

import com.bacurilab.backend.model.Role;
import com.bacurilab.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}
