package com.example.Climate.repositories;

import com.example.Climate.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

    @Query("SELECT s FROM State s WHERE s.country.countryCode = :countryCode")
    List<State> getStateByCountryCode(@Param("countryCode") String countryCode);

    @Query("SELECT COUNT(*) From State")
    int getNumberOfState();

}
