package com.bacurilab.backend.model.request;

import com.bacurilab.backend.model.Vaccine;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class VaccineRequest {

    @NonNull
    private Vaccine vaccine;


}
