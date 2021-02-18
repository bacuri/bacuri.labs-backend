package com.bacurilab.backend.service;

import com.bacurilab.backend.model.DependentProfile;
import com.bacurilab.backend.model.History;
import com.bacurilab.backend.repository.HistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class HistoryService {
    private ContextService contextService;
    private HistoryRepository historyRepository;
    private DependentProfileService dependentProfileService;

    public HistoryService(ContextService contextService, HistoryRepository historyRepository, DependentProfileService dependentProfileService) {
        this.contextService = contextService;
        this.historyRepository = historyRepository;
        this.dependentProfileService = dependentProfileService;
    }

    public List<History> listAll() {
        return this.historyRepository.findAll();
    }

    @Transactional
    public History save(History history) {
        log.info(history.toString());
        return this.historyRepository.save(history);
    }


    public List<History> list() {
        DependentProfile profile = this.dependentProfileService.getById(this.contextService.getPrincipal().getDependentProfiles().iterator().next().getId());
        return this.historyRepository.findCampaignsByProfessionalOrderByCreateAtDesc(profile);
    }
}
