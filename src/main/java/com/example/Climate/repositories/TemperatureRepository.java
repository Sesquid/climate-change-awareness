package com.example.Climate.repositories;

import com.example.Climate.models.RegionInformation;
import com.example.Climate.models.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Integer> {

    @Query("SELECT t FROM Temperature t WHERE t.countryName = :countryName AND t.stateName IS NULL AND t.cityName IS NULL")
    List<Temperature> getTempByCountry(@Param("countryName") String countryName);

    @Query("SELECT t FROM Temperature t " +
            "WHERE t.countryName = :#{#region.countryName} " +
            "AND t.stateName IS NULL AND t.cityName IS NULL " +
            "AND (t.year = :#{#region.startYear} OR t.year = :#{#region.endYear}) ORDER BY t.year DESC")
    List<Temperature> getCountryTempByYearRange(@Param("region") RegionInformation region);

    @Query("SELECT t FROM Temperature t " +
            "WHERE t.cityName = :#{#region.regionName} " +
            "AND t.countryName = :#{#region.countryName} " +
            "AND t.latitude = :#{#region.latitude} " +
            "AND t.longtitude = :#{#region.longtitude} " +
            "AND (t.year = :#{#region.startYear} OR t.year = :#{#region.endYear}) ORDER BY t.year DESC")
    List<Temperature> getCityTempByYearRange(@Param("region") RegionInformation region);

    @Query("SELECT t FROM Temperature t " +
            "WHERE t.stateName = :#{#region.regionName} " +
            "AND t.countryName = :#{#region.countryName} " +
            "AND (t.year = :#{#region.startYear} OR t.year = :#{#region.endYear}) ORDER BY t.year DESC")
    List<Temperature> getStateTempByYearRange(@Param("region") RegionInformation region);

    @Query("SELECT MIN(t.year) AS start, MAX(t.year) AS end FROM Temperature t")
    Tuple findYearRange();

    @Query("SELECT t.countryName FROM Temperature t WHERE t.year = 2013 AND t.stateName IS NULL AND t.cityName IS NULL ORDER BY t.avgTemp ASC")
    List<String> getAllCountriesOrderByTemperatureAsc();

    @Query("SELECT t.countryName FROM Temperature t WHERE t.year = 2013 AND t.stateName IS NULL AND t.cityName IS NULL ORDER BY t.avgTemp DESC")
    List<String> getAllCountriesOrderByTemperatureDesc();

    @Query("SELECT " +
            "t1.avgTemp - t2.avgTemp AS avgTemp, " +
            "t1.minTemp - t2.minTemp AS minTemp, " +
            "t1.maxTemp - t2.maxTemp AS maxTemp " +
            "FROM Temperature t1, Temperature t2 " +
            "WHERE t1.year = :#{#region.endYear} AND t1.countryName = :#{#region.countryName} " +
            "AND t2.year = :#{#region.startYear} AND t2.countryName = :#{#region.countryName} " +
            "AND t1.stateName IS NULL AND t1.cityName IS NULL " +
            "AND t2.stateName IS NULL AND t2.cityName IS NULL")
    Tuple getCountryTemperatureDiff(@Param("region") RegionInformation region);

    @Query("SELECT " +
            "t1.avgTemp - t2.avgTemp AS avgTemp, " +
            "t1.minTemp - t2.minTemp AS minTemp, " +
            "t1.maxTemp - t2.maxTemp AS maxTemp " +
            "FROM Temperature t1, Temperature t2 " +
            "WHERE t1.cityName = :#{#region.regionName} AND t2.cityName = :#{#region.regionName} " +
            "AND t1.year = :#{#region.endYear} AND t1.countryName = :#{#region.countryName} " +
            "AND t2.year = :#{#region.startYear} AND t2.countryName = :#{#region.countryName} " +
            "AND t1.latitude = :#{#region.latitude} AND t1.longtitude = :#{#region.longtitude} " +
            "AND t2.latitude = :#{#region.latitude} AND t2.longtitude = :#{#region.longtitude}")
    Tuple getCityTemperatureDiff(@Param("region") RegionInformation region);

    @Query("SELECT " +
            "t1.avgTemp - t2.avgTemp AS avgTemp, " +
            "t1.minTemp - t2.minTemp AS minTemp, " +
            "t1.maxTemp - t2.maxTemp AS maxTemp " +
            "FROM Temperature t1, Temperature t2 " +
            "WHERE t1.stateName = :#{#region.regionName} AND t2.stateName = :#{#region.regionName} " +
            "AND t1.year = :#{#region.endYear} AND t1.countryName = :#{#region.countryName} " +
            "AND t2.year = :#{#region.startYear} AND t2.countryName = :#{#region.countryName} ")
    Tuple getStateTemperatureDiff(@Param("region") RegionInformation region);
}
