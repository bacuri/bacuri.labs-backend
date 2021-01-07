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

    public DependentProfileVaccine save(DependentProfile profile, Vaccine vaccine, DependentProfile professionalProfile) {
        DependentProfileVaccine register = new DependentProfileVaccine(new DependentProfileVaccinePk(profile, vaccine), professionalProfile);
        return dependentProfileVaccineRepository.save(register);
    }


    public List<AppliedVaccine> getTimeline(Long profileId) {
        return dependentProfileVaccineRepository.getTimeline(profileId);
    }
}
