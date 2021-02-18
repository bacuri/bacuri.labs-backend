package com.bacurilab.backend.controller;

import com.bacurilab.backend.model.History;
import com.bacurilab.backend.model.HttpResponse;
import com.bacurilab.backend.service.HistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping("/history")
public class HistoryController {

    private HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/all")
    public ResponseEntity<HttpResponse> listAll() {
        HttpResponse response = new HttpResponse();
        try {
            response.setContent(this.historyService.listAll());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping
    public ResponseEntity<HttpResponse> list() {
        HttpResponse response = new HttpResponse();
        try {
            response.setContent(this.historyService.list());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping
    public ResponseEntity<HttpResponse> create(@RequestBody History campaign) {
        HttpResponse response = new HttpResponse();
        try {
            response.setContent(this.historyService.save(campaign));
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessages(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }


}
