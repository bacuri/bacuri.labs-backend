package com.bacurilab.backend.controller;

import com.bacurilab.backend.model.HttpResponse;
import com.bacurilab.backend.model.User;
import com.bacurilab.backend.model.request.RegisterRequest;
import com.bacurilab.backend.model.request.UserRequest;
import com.bacurilab.backend.service.ContextService;
import com.bacurilab.backend.service.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping("/register")
public class RegisterController {

    private RegisterService registerService;
    private ContextService contextService;

    public RegisterController(ContextService contextService, RegisterService registerService) {
        this.contextService = contextService;
        this.registerService = registerService;
    }

    @PostMapping
    public ResponseEntity<HttpResponse> register(@RequestBody RegisterRequest registerRequest){
        HttpResponse response = new HttpResponse();
        try{
            response.setContent(this.registerService.register(registerRequest.getUser(),registerRequest.getRole()));
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping
    public ResponseEntity<HttpResponse> update(@RequestBody UserRequest userRequest){
        HttpResponse response = new HttpResponse();
        try{
            response.setContent(this.registerService.update(userRequest.getUser()));
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping
    public ResponseEntity<HttpResponse> delete(){
        HttpResponse response = new HttpResponse();
        try{
            this.registerService.delete(contextService.getPrincipal());
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

}
