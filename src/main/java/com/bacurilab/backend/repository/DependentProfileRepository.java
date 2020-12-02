package com.bacurilab.backend.repository;

import com.bacurilab.backend.model.DependentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DependentProfileRepository extends JpaRepository<DependentProfile, Long> {

}
