package com.bacurilab.backend.service;

import com.bacurilab.backend.model.AppliedVaccine;
import com.bacurilab.backend.model.DependentProfile;
import com.bacurilab.backend.model.DependentProfileVaccine;
import com.bacurilab.backend.model.Vaccine;
import com.bacurilab.backend.repository.VaccineRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineService {

    private VaccineRepository vaccineRepository;
    private DependentProfileVaccineService dependentProfileVaccineService;
    private DependentProfileService dependentProfileService;

    public VaccineService(VaccineRepository vaccineRepository, DependentProfileVaccineService dependentProfileVaccineService, DependentProfileService dependentProfileService) {
        this.vaccineRepository = vaccineRepository;
        this.dependentProfileVaccineService = dependentProfileVaccineService;
        this.dependentProfileService = dependentProfileService;
    }


    public Vaccine save(Vaccine vaccine) {
        this.vaccineRepository.saveAndFlush(vaccine);
        return vaccine;
    }

    public void delete(Vaccine vaccine) {
        this.vaccineRepository.delete(vaccine);
    }

    public List<Vaccine> listAll() {
        return this.vaccineRepository.findAllByIdAsc();
    }

    public List<Vaccine> listAllByAge(Integer age) {
        return this.vaccineRepository.findAllByAge(age);
    }

    public Vaccine getById(Long vaccine) throws RuntimeException {
        Optional<Vaccine> optional = this.vaccineRepository.findById(vaccine);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new RuntimeException();
    }

    public Vaccine update(Vaccine vaccine) {
        Optional<Vaccine> optional = this.vaccineRepository.findById(vaccine.getId());

        if (optional.isPresent()) {
            Vaccine oldVaccine = optional.get();

            Vaccine newVaccine = new Vaccine();
            newVaccine.setName(ObjectUtils.defaultIfNull(vaccine.getName(), oldVaccine.getName()));
            newVaccine.setInitialRange(ObjectUtils.defaultIfNull(vaccine.getInitialRange(), oldVaccine.getInitialRange()));
            newVaccine.setFinalRange(ObjectUtils.defaultIfNull(vaccine.getFinalRange(), oldVaccine.getFinalRange()));
            newVaccine.setPreventedDiseases(ObjectUtils.defaultIfNull(vaccine.getPreventedDiseases(), oldVaccine.getPreventedDiseases()));
            newVaccine.setDosage(ObjectUtils.defaultIfNull(vaccine.getDosage(), oldVaccine.getDosage()));
            newVaccine.setNextVaccine(ObjectUtils.defaultIfNull(vaccine.getNextVaccine(), oldVaccine.getNextVaccine()));
            newVaccine.setNextDosage(ObjectUtils.defaultIfNull(vaccine.getNextDosage(), oldVaccine.getNextDosage()));
            newVaccine.setFrequency(ObjectUtils.defaultIfNull(vaccine.getNextDosage(), oldVaccine.getNextDosage()));
            newVaccine.setObservation(ObjectUtils.defaultIfNull(vaccine.getObservation(), oldVaccine.getObservation()));
            newVaccine.setRange(ObjectUtils.defaultIfNull(vaccine.getRange(), oldVaccine.getRange()));

            return this.vaccineRepository.save(newVaccine);
        }

        return null;
    }

    public Vaccine findById(Long vaccine) throws RuntimeException {
        Optional<Vaccine> optional = this.vaccineRepository.findById(vaccine);
        return optional.orElse(null);
    }

    public DependentProfileVaccine registerApplication(Long profileId, Long vaccineId, Long professionalProfileId) {
        Vaccine vaccine = this.findById(vaccineId);

        DependentProfile profile = dependentProfileService.getById(profileId);

        DependentProfile professional =  dependentProfileService.getById(professionalProfileId);

        return this.dependentProfileVaccineService.save(profile, vaccine, professional);
    }

    public List<AppliedVaccine> getTimeline(Long profileId) {
        return this.dependentProfileVaccineService.getTimeline(profileId);
    }
}
