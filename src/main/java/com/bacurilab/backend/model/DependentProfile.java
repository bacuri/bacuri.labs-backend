package com.bacurilab.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class DependentProfile extends DefaultPerson implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 14)
    private String cic;

    @Column(nullable = false, length = 7)
    @Enumerated(EnumType.STRING)
    private Image image;

    @Enumerated(EnumType.STRING)
    private ProfileType profile;

}