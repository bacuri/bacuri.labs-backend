package com.bacurilab.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "campaign")
@Data
@NoArgsConstructor
public class Campaign extends DefaultDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private LocalDateTime effectiveDate;

    @Column
    private LocalDateTime expireDate;

    @Column
    private String image;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.JOIN)
    @JoinTable(name = "CAMPAIGN_PLACE", joinColumns = {
            @JoinColumn(name = "CAMPAIGN_ID", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "PLACE_ID", referencedColumnName = "id")})
    private Set<Place> places = new HashSet<>();


    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "CAMPAIGN_VACCINE", joinColumns = {
            @JoinColumn(name = "CAMPAIGN_ID", referencedColumnName = "id", insertable = false, updatable = false)}, inverseJoinColumns = {
            @JoinColumn(insertable = false, updatable = false, name = "VACCINE_ID", referencedColumnName = "id")})
    private Set<Vaccine> vaccines = new HashSet<>();


    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "CAMPAIGN_PATIENT", joinColumns = {
            @JoinColumn(name = "CAMPAIGN_ID", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(insertable = false, updatable = false, name = "PATIENT_ID", referencedColumnName = "id")})
    private Set<DependentProfile> patients = new HashSet<>();


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PROFESSIONAL_PROFILE_ID")
    private DependentProfile professional;

}
