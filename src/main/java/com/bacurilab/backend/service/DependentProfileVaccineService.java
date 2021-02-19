package com.bacurilab.backend.service;

import com.bacurilab.backend.model.*;
import com.bacurilab.backend.model.pk.DependentProfileVaccinePk;
import com.bacurilab.backend.repository.DependentProfileVaccineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DependentProfileVaccineService {
    private DependentProfileVaccineRepository dependentProfileVaccineRepository;
    private DependentProfileService dependentProfileService;
    private HistoryService historyService;
    private CampaignService campaignService;


    public DependentProfileVaccineService(DependentProfileVaccineRepository dependentProfileVaccineRepository,
                                          DependentProfileService dependentProfileService,
                                          HistoryService historyService,
                                          CampaignService campaignService) {
        this.dependentProfileVaccineRepository = dependentProfileVaccineRepository;
        this.dependentProfileService = dependentProfileService;
        this.historyService = historyService;
        this.campaignService = campaignService;
    }


    public DependentProfileVaccine save(DependentProfile profile, Vaccine vaccine, DependentProfile professionalProfile) {
        DependentProfileVaccine register = new DependentProfileVaccine(new DependentProfileVaccinePk(profile, vaccine), professionalProfile);
        return dependentProfileVaccineRepository.save(register);
    }


    public List<AppliedVaccine> getTimeline(Long profileId) {
        return dependentProfileVaccineRepository.getTimeline(profileId);
    }

    public List<AppliedVaccine> getTimelineByGender(Long profileId) {
        DependentProfile profile = this.dependentProfileService.getById(profileId);
        Gender gender = profile.getGender();
        return dependentProfileVaccineRepository.getTimeline(profileId, gender.equals(Gender.FEMALE) ? Requirement.GIRL.name() : Requirement.BOY.name());
    }

    public DependentProfileVaccine save(Long profileId, Vaccine vaccine, Long professionalProfileId, Long campaignId, String lot, String transactionId) {
        History history = new History();
        history.setCampaign(campaignId == null ? null : this.campaignService.findById(campaignId));
        history.setPatient(this.dependentProfileService.getById(profileId));
        history.setProfessional(this.dependentProfileService.getById(professionalProfileId));
        history.setVaccine(vaccine);
        history.setTransactionId(transactionId);


        try {
            this.historyService.save(history);

            if (history.getCampaign() != null)
                this.campaignService.registerPatientInCampaign(history.getCampaign(), history.getPatient());

            return this.save(history.getPatient(), history.getVaccine(), history.getProfessional());
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
