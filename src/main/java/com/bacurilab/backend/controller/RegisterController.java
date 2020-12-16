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

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public ResponseEntity<HttpResponse> register(@RequestBody RegisterRequest registerRequest){
        HttpResponse response = new HttpResponse();
        try{
            response.setContent(this.registerService.register(registerRequest.getUser(), registerRequest.getRole(), registerRequest.getPlatform()));
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

}
