package com.bacurilab.backend.service;

import com.bacurilab.backend.model.AppliedVaccine;
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

    public VaccineService(VaccineRepository vaccineRepository, DependentProfileVaccineService dependentProfileVaccineService) {
        this.vaccineRepository = vaccineRepository;
        this.dependentProfileVaccineService = dependentProfileVaccineService;
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

    public Vaccine info(Vaccine vaccine) throws RuntimeException {
        Optional<Vaccine> optional = this.vaccineRepository.findById(vaccine.getId());
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

    public DependentProfileVaccine registerApplication(Long profileId, Long vaccineId, Long professionalProfileId) {
        return this.dependentProfileVaccineService.save(profileId, vaccineId, professionalProfileId);
    }

    public List<AppliedVaccine> getTimeline(Long profileId) {
        return this.dependentProfileVaccineService.getTimeline(profileId);
    }
}
