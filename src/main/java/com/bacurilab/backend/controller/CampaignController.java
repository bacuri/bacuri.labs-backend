package com.bacurilab.backend.controller;

import com.bacurilab.backend.model.Campaign;
import com.bacurilab.backend.model.HttpResponse;
import com.bacurilab.backend.service.CampaignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping("/campaign")
public class CampaignController {

    private CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @GetMapping
    public ResponseEntity<HttpResponse> listAll() {
        HttpResponse response = new HttpResponse();
        try {
            response.setContent(this.campaignService.listAll());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/my-campaigns")
    public ResponseEntity<HttpResponse> myCampaigns(@RequestParam("profileId") Long profileId) {
        HttpResponse response = new HttpResponse();
        try {
            response.setContent(this.campaignService.getMyCampaigns(profileId));
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping
    public ResponseEntity<HttpResponse> create(@RequestBody Campaign campaign) {
        HttpResponse response = new HttpResponse();
        try {
            response.setContent(this.campaignService.save(campaign));
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping
    public ResponseEntity<HttpResponse> delete(@RequestBody Long campaignId) {
        HttpResponse response = new HttpResponse();
        try {
            this.campaignService.delete(campaignId);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping
    public ResponseEntity<HttpResponse> update(@RequestBody Campaign campaign) {
        HttpResponse response = new HttpResponse();
        try {
            this.campaignService.update(campaign);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }
}
