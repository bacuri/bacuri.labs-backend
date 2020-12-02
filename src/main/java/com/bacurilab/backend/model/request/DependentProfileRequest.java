package com.bacurilab.backend.model.request;

import com.bacurilab.backend.model.DependentProfile;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class DependentProfileRequest {

    @NonNull
    private DependentProfile profile;

}
