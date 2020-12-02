package com.bacurilab.backend.model.request;

import com.bacurilab.backend.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class UserRequest {

    @NonNull
    private User user;

}
