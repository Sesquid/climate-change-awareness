package com.example.Climate.repositories;

import com.example.Climate.dto.CompareTemperature;
import com.example.Climate.dto.TemperatureDTO;
import com.example.Climate.models.GlobalTemperature;
import com.example.Climate.dto.YearRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GlobalTempRepository extends JpaRepository<GlobalTemperature, Integer> {

//    @Query("SELECT t FROM GlobalTemperature t " +
//            "WHERE t.year = :#{#yearRange.startYear} " +
//            "OR t.year = :#{#yearRange.endYear} ORDER BY t.year DESC")
//    List<GlobalTemperature> getGlobalTemperatureByYearRange(@Param("yearRange") YearRange yearRange);

    @Query("SELECT new com.example.Climate.dto.YearRange(MIN(g.year), MAX(g.year)) FROM GlobalTemperature g")
    YearRange findGlobalTemperatureYearRange();

    @Query("""
            SELECT
            t1.avgTemp AS endYearAvgTemperature,
            t1.minTemp AS endYearMinTemperature,
            t1.maxTemp AS endYearMaxTemperature,
            t2.avgTemp AS startYearAvgTemperature,
            t2.minTemp AS startYearMinTemperature,
            t2.maxTemp AS startYearMaxTemperature,
            t1.avgTemp - t2.avgTemp AS avgTemperatureDifference,
            t1.minTemp - t2.minTemp AS minTemperatureDifference,
            t1.maxTemp - t2.maxTemp AS maxTemperatureDifference
            FROM GlobalTemperature t1, GlobalTemperature t2
            WHERE t1.year = :endYear
            AND t2.year = :startYear
            """)
    CompareTemperature findWorldTemperatureDifference(@Param("startYear") int startYear,
                                                      @Param("endYear") int endYear);


}
