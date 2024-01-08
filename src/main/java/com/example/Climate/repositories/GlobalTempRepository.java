package com.example.Climate.repositories;

import com.example.Climate.models.GlobalTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GlobalTempRepository extends JpaRepository<GlobalTemp, Integer> {

    @Query("SELECT g FROM GlobalTemp g WHERE g.year = :year")
    List<GlobalTemp> getGlobalTempByYear(@Param("year") int year);
}
