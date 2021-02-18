package com.bacurilab.backend.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class History extends DefaultDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private String transactionId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "VACCINE_ID")
    private Vaccine vaccine;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PATIENT_ID")
    private DependentProfile patient;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PROFESSIONAL_ID")
    private DependentProfile professional;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CAMPAIGN_ID")
    private Campaign campaign;

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", transactionId='" + transactionId + '\'' +
                ", vaccine=" + vaccine +
                ", patient=" + patient +
                ", professional=" + professional +
                ", campaign=" + campaign +
                '}';
    }
}
