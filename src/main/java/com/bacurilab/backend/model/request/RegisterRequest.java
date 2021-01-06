package com.bacurilab.backend.model.request;

import com.bacurilab.backend.model.Platform;
import com.bacurilab.backend.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class RegisterRequest {

    @NonNull
    private Platform platform;

    @NonNull
    private User user;

    @NonNull
    private String role;

}
