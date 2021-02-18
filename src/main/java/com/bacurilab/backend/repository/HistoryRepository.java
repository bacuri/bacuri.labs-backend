package com.bacurilab.backend.repository;

import com.bacurilab.backend.model.DependentProfile;
import com.bacurilab.backend.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findCampaignsByProfessionalOrderByCreateAtDesc(DependentProfile professional);
}
