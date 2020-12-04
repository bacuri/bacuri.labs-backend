package com.bacurilab.backend.model;

import com.bacurilab.backend.model.pk.DependentProfileVaccinePk;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties

public class DependentProfileVaccine extends DefaultDTO implements Serializable {

    @EmbeddedId
    private DependentProfileVaccinePk dependentProfileVaccinePk;

}