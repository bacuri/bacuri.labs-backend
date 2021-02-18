package com.bacurilab.backend.repository;

import com.bacurilab.backend.model.Campaign;
import com.bacurilab.backend.model.DependentProfile;
import com.bacurilab.backend.model.History;
import com.bacurilab.backend.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    @Query(" select c from Campaign as c left join fetch c.vaccines as v " +
           " where v.initialRange <= :age and v.finalRange <= :age " +
           " and ((:gender = 'MALE' and v.requirement <> 'GIRL') or (:gender = 'FEMALE' and v.requirement <> 'BOY')) order by v.createAt desc ")
    List<Campaign> getMyCampaigns(@Param("age") Integer age, @Param("gender") String gender);
}
