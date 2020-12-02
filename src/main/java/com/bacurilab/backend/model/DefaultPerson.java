package com.bacurilab.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class DefaultPerson extends DefaultDTO {

    @Column(nullable = false)
    @JsonInclude
    private String firstName;

    @Column(nullable = false)
    @JsonInclude
    private String lastName;

    @Column(nullable = false)
    @JsonInclude
    private Gender gender;

    @Column(nullable = false)
    @JsonInclude
    private LocalDateTime dateOfBirth;
}