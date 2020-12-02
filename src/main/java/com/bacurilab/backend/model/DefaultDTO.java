package com.bacurilab.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class DefaultDTO {

    @Column
    private LocalDateTime createAt;

    @Column
    private Character situation;

    @PrePersist
    private void preSetCreateAt(){
        this.createAt = LocalDateTime.now();
        this.situation = 'A';
    }

}
