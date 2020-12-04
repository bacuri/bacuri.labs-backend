package com.bacurilab.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class Vaccine extends DefaultDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private AgeRange range;

    @Column
    private String name;

    @Column
    private String preventedDiseases;

    @Column
    @Enumerated(EnumType.STRING)
    private Dosage dosage;

    @Column
    private Integer initialRange;

    @Column
    private Integer finalRange;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "NEXT_VACCINE_ID")
    private Vaccine nextVaccine;

    @Column
    private Integer nextDosage;

    @Column
    private Integer frequency;

    @Column(length = 500)
    private String observation;
}
