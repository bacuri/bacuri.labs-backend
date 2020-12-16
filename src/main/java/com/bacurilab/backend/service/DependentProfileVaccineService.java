package com.bacurilab.backend.service;

import com.bacurilab.backend.model.AppliedVaccine;
import com.bacurilab.backend.model.DependentProfile;
import com.bacurilab.backend.model.DependentProfileVaccine;
import com.bacurilab.backend.model.Vaccine;
import com.bacurilab.backend.model.pk.DependentProfileVaccinePk;
import com.bacurilab.backend.repository.DependentProfileVaccineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DependentProfileVaccineService {
    private DependentProfileVaccineRepository dependentProfileVaccineRepository;


    public DependentProfileVaccineService(DependentProfileVaccineRepository dependentProfileVaccineRepository) {
        this.dependentProfileVaccineRepository = dependentProfileVaccineRepository;
    }

    public DependentProfileVaccine save(Long profileId, Long vaccineId, Long professionalProfileId) {
        Vaccine vaccine = new Vaccine();
        vaccine.setId(vaccineId);

        DependentProfile dependent = new DependentProfile();
        dependent.setId(profileId);

        DependentProfile professional = new DependentProfile();
        professional.setId(professionalProfileId);

        DependentProfileVaccine register = new DependentProfileVaccine(new DependentProfileVaccinePk(dependent, vaccine), professional);
        return dependentProfileVaccineRepository.save(register);
    }


    public List<AppliedVaccine> getTimeline(Long profileId){
        return dependentProfileVaccineRepository.getTimeline(profileId);
    }
}
