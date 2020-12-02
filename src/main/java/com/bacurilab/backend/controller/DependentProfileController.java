package com.bacurilab.backend.controller;

import com.bacurilab.backend.model.HttpResponse;
import com.bacurilab.backend.model.request.DependentProfileRequest;
import com.bacurilab.backend.model.request.RegisterRequest;
import com.bacurilab.backend.service.ContextService;
import com.bacurilab.backend.service.DependentProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping("/dependent-profile")
public class DependentProfileController {

    private ContextService contextService;
    private DependentProfileService dependentProfileService;

    public DependentProfileController(ContextService contextService, DependentProfileService dependentProfileService) {
        this.contextService = contextService;
        this.dependentProfileService = dependentProfileService;
    }

    @PostMapping
    public ResponseEntity<HttpResponse> associate(@RequestBody DependentProfileRequest dependentProfileRequest){
        HttpResponse response = new HttpResponse();
        try{
            response.setContent(this.dependentProfileService.associate(contextService.getPrincipal(), dependentProfileRequest.getProfile()));
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping
    public ResponseEntity<HttpResponse> deassociate(@RequestBody DependentProfileRequest dependentProfileRequest){
        HttpResponse response = new HttpResponse();
        try{
            response.setContent(this.dependentProfileService.deassociate(contextService.getPrincipal(), dependentProfileRequest.getProfile()));
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping
    public ResponseEntity<HttpResponse> updateAssociate(@RequestBody DependentProfileRequest dependentProfileRequest){
        HttpResponse response = new HttpResponse();
        try{
            response.setContent(this.dependentProfileService.updateAssociation(contextService.getPrincipal(), dependentProfileRequest.getProfile()));
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

}
