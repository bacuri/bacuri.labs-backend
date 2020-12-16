package com.bacurilab.backend.repository;

import com.bacurilab.backend.model.AppliedVaccine;
import com.bacurilab.backend.model.DependentProfileVaccine;
import com.bacurilab.backend.model.pk.DependentProfileVaccinePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DependentProfileVaccineRepository extends JpaRepository<DependentProfileVaccine, DependentProfileVaccinePk> {

    @Query(" select new com.bacurilab.backend.model.AppliedVaccine( vaccine, ( case dependent.dependentProfileVaccinePk.vaccineId when vaccine.id then true else false end ), dependent.createAt) " +
            " from Vaccine vaccine " +
            " left join DependentProfileVaccine dependent on dependent.dependentProfileVaccinePk.vaccineId.id = vaccine.id " +
            " where dependent.dependentProfileVaccinePk.dependentProfileId.id = :profile " +
            " or dependent.dependentProfileVaccinePk.dependentProfileId = null " +
            " order by vaccine.id")
    List<AppliedVaccine> getTimeline(@Param("profile") Long profile);

}
