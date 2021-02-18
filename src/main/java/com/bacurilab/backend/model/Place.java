package com.bacurilab.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "place")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class Place extends DefaultDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Integer amount;

    @Column
    private Integer applied;

    @Column
    private String latitude;

    @Column
    private String longitude;

}
