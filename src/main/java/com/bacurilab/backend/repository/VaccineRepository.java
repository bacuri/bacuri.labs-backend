package com.bacurilab.backend.repository;

import com.bacurilab.backend.model.User;
import com.bacurilab.backend.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.w3c.dom.ranges.Range;

import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    @Query("select v from Vaccine v order by v.id asc")
    List<Vaccine> findAllByIdAsc();

    @Query("select v from Vaccine v order by v.createAt desc")
    List<Vaccine> findAllOrderByCreateAtDesc();

    @Query("select v from Vaccine v where v.initialRange <= :age and v.finalRange <= :age order by v.createAt desc")
    List<Vaccine> findAllByAge(@Param("age") Integer age);
}
