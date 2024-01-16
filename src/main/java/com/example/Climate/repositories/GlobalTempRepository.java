package com.example.Climate.repositories;

import com.example.Climate.models.GlobalTemperature;
import com.example.Climate.models.GlobalTemperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface GlobalTempRepository extends JpaRepository<GlobalTemperature, Integer> {

    @Query("SELECT g FROM GlobalTemperature g WHERE g.year = :year")
    List<GlobalTemperature> getGlobalTempByYear(@Param("year") int year);

    @Query("SELECT MIN(g.year) AS start, MAX(g.year) AS end FROM GlobalTemperature g")
    Tuple findYearRange();

}
