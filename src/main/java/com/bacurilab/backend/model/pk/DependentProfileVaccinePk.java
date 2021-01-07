package com.bacurilab.backend.model.pk;

import com.bacurilab.backend.model.DependentProfile;
import com.bacurilab.backend.model.Vaccine;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
@Embeddable
public class DependentProfileVaccinePk implements Serializable {
    @ManyToOne
    @JoinColumn(name = "DEPENDENT_PROFILE_ID", nullable = false)
    private DependentProfile dependentProfileId;

    @ManyToOne
    @JoinColumn(name = "VACCINE_ID", nullable = false)
    private Vaccine vaccineId;
}