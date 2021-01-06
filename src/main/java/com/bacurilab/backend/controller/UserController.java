package com.bacurilab.backend.controller;

import com.bacurilab.backend.model.HttpResponse;
import com.bacurilab.backend.model.request.PasswordRecoveryRequest;
import com.bacurilab.backend.model.request.UserRequest;
import com.bacurilab.backend.service.ContextService;
import com.bacurilab.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private ContextService contextService;

    public UserController(ContextService contextService, UserService userService) {
        this.contextService = contextService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<HttpResponse> info(){
        HttpResponse response = new HttpResponse();
        try{
            response.setContent(this.userService.info(contextService.getPrincipal()));
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
            response.setContent(this.userService.update(userRequest.getUser()));
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
            this.userService.delete(contextService.getPrincipal());
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/change-password")
    public ResponseEntity<HttpResponse> recovery(@RequestBody PasswordRecoveryRequest passwordRecoveryRequest){
        HttpResponse response = new HttpResponse();
        try{
            response.setContent(this.userService.changePassword(passwordRecoveryRequest.getUser(), passwordRecoveryRequest.getUser().getPassword(), passwordRecoveryRequest.getNewPassword()));
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

}
