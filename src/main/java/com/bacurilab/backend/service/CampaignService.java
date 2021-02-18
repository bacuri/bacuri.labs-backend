package com.bacurilab.backend.service;

import com.bacurilab.backend.model.Campaign;
import com.bacurilab.backend.model.DependentProfile;
import com.bacurilab.backend.model.Vaccine;
import com.bacurilab.backend.repository.CampaignRepository;
import com.bacurilab.backend.repository.VaccineRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CampaignService {

    private CampaignRepository campaignRepository;
    private VaccineRepository vaccineRepository;
    private DependentProfileService dependentProfileService;

    public CampaignService(CampaignRepository campaignRepository, DependentProfileService dependentProfileService, VaccineRepository vaccineRepository) {
        this.campaignRepository = campaignRepository;
        this.vaccineRepository = vaccineRepository;
        this.dependentProfileService = dependentProfileService;
    }

    public List<Campaign> listAll() {
        return this.campaignRepository.findAll();
    }

    public Campaign save(Campaign campaign) {

        DependentProfile professional = this.dependentProfileService.getById(campaign.getProfessional().getId());

        Set<Vaccine> vaccines = campaign.getVaccines().stream().map(vaccine -> this.vaccineRepository.findById(vaccine.getId()).orElse(null)).filter(Objects::nonNull).collect(Collectors.toSet());
        Set<DependentProfile> patients = campaign.getPatients().stream().map(patient -> this.dependentProfileService.getById(patient.getId())).filter(Objects::nonNull).collect(Collectors.toSet());

        campaign.setProfessional(professional);
        campaign.setVaccines(vaccines);
        campaign.setPatients(patients);

        return this.campaignRepository.save(campaign);
    }

    public void delete(Long campaignId) {
        Campaign campaign = new Campaign();
        campaign.setId(campaignId);
        this.campaignRepository.delete(campaign);
    }

    public Campaign update(Campaign campaign) {

        Optional<Campaign> optional = this.campaignRepository.findById(campaign.getId());

        if (optional.isPresent()) {
            Campaign oldCampaign = optional.get();

            Campaign newCampaign = new Campaign();

            newCampaign.setTitle(ObjectUtils.defaultIfNull(campaign.getTitle(), oldCampaign.getTitle()));
            newCampaign.setEffectiveDate(ObjectUtils.defaultIfNull(campaign.getEffectiveDate(), oldCampaign.getEffectiveDate()));
            newCampaign.setExpireDate(ObjectUtils.defaultIfNull(campaign.getExpireDate(), oldCampaign.getExpireDate()));
            newCampaign.setDescription(ObjectUtils.defaultIfNull(campaign.getDescription(), oldCampaign.getDescription()));
            newCampaign.setImage(ObjectUtils.defaultIfNull(campaign.getImage(), oldCampaign.getImage()));

            newCampaign.setPlaces(ObjectUtils.defaultIfNull(campaign.getPlaces(), oldCampaign.getPlaces()));
            newCampaign.setVaccines(ObjectUtils.defaultIfNull(campaign.getVaccines(), oldCampaign.getVaccines()));
            newCampaign.setPatients(ObjectUtils.defaultIfNull(campaign.getPatients(), oldCampaign.getPatients()));

            return this.campaignRepository.save(newCampaign);
        }
        return null;
    }

    public Campaign findById(Long campaign) {
        Optional<Campaign> optional = this.campaignRepository.findById(campaign);
        return optional.orElse(null);
    }

    public Campaign registerPatientInCampaign(Campaign campaign, DependentProfile patient) {
        campaign.getPatients().add(patient);
        return this.campaignRepository.save(campaign);
    }

    public List<Campaign> getMyCampaigns(Long profileId) {
        DependentProfile profile = this.dependentProfileService.getById(profileId);
        long age = ChronoUnit.MONTHS.between(YearMonth.from(profile.getDateOfBirth()), YearMonth.from(LocalDate.now()));
        return this.campaignRepository.getMyCampaigns(Integer.valueOf((int) age), profile.getGender().name());
    }
}
