package com.bacurilab.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class HttpResponse {
    private Object content;
    private boolean success;
    private List<String> messages;

    public HttpResponse() {
        this.success = true;
    }
}
