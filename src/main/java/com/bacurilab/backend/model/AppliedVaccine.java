package com.bacurilab.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppliedVaccine implements Serializable {

    private Vaccine vaccine;
    private Boolean applied;
    private LocalDateTime applicationDate;

}
