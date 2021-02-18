package com.bacurilab.backend.controller;

import com.bacurilab.backend.model.HttpResponse;
import com.bacurilab.backend.model.request.VaccineRequest;
import com.bacurilab.backend.service.VaccineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping("/vaccine")
public class VaccineController {

    private VaccineService vaccineService;

    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @GetMapping
    public ResponseEntity<HttpResponse> listAll() {
        HttpResponse response = new HttpResponse();
        try {
            response.setContent(this.vaccineService.listAll());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping
    public ResponseEntity<HttpResponse> create(@RequestBody VaccineRequest vaccineRequest) {
        HttpResponse response = new HttpResponse();
        try {
            response.setContent(this.vaccineService.save(vaccineRequest.getVaccine()));
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping
    public ResponseEntity<HttpResponse> delete(@RequestBody VaccineRequest vaccineRequest) {
        HttpResponse response = new HttpResponse();
        try {
            this.vaccineService.delete(vaccineRequest.getVaccine());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping
    public ResponseEntity<HttpResponse> update(@RequestBody VaccineRequest vaccineRequest) {
        HttpResponse response = new HttpResponse();
        try {
            this.vaccineService.update(vaccineRequest.getVaccine());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/filter")
    public ResponseEntity<HttpResponse> getVaccinesByAge(@RequestParam Integer age) {
        HttpResponse response = new HttpResponse();
        try {
            response.setContent(this.vaccineService.listAllByAge(age));
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }


    @PostMapping("/apply")
    public ResponseEntity<HttpResponse> apply(@RequestParam("vaccineId") Long vaccineId, @RequestParam("profileId") Long profileId, @RequestParam("professionalProfileId") Long professionalProfileId, @RequestParam(name = "campaignId", required = false) Long campaignId, @RequestParam(name = "lot", required = false) String lot, @RequestParam("transactionId") String transactionId) {
        HttpResponse response = new HttpResponse();
        try {
            response.setContent(this.vaccineService.registerApplication(profileId, vaccineId, professionalProfileId, campaignId, lot, transactionId));
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/general-timeline")
    public ResponseEntity<HttpResponse> general(@RequestParam("profileId") Long profileId) {
        HttpResponse response = new HttpResponse();
        try {
            response.setContent(this.vaccineService.getGeneralTimeline(profileId));
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/timeline")
    public ResponseEntity<HttpResponse> timeline(@RequestParam("profileId") Long profileId) {
        HttpResponse response = new HttpResponse();
        try {
            response.setContent(this.vaccineService.getTimelineByGender(profileId));
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

}
