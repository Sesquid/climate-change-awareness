package com.example.Climate.repositories;

import com.example.Climate.dto.RegionInformation;
import com.example.Climate.dto.SimilarRegion;
import com.example.Climate.dto.YearRange;
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
    List<Temperature> getTemperatureListByCountry(@Param("countryName") String countryName);

    @Query("SELECT new com.example.Climate.dto.YearRange(MIN(t.year), MAX(t.year)) FROM Temperature t")
    YearRange findTemperatureYearRange();

    @Query("SELECT t.countryName FROM Temperature t WHERE t.year = 2013 AND t.stateName IS NULL AND t.cityName IS NULL ORDER BY t.avgTemp ASC")
    List<String> getAllCountriesOrderByTemperatureAsc();

    @Query("SELECT t.countryName FROM Temperature t WHERE t.year = 2013 AND t.stateName IS NULL AND t.cityName IS NULL ORDER BY t.avgTemp DESC")
    List<String> getAllCountriesOrderByTemperatureDesc();

    @Query("SELECT t FROM Temperature t " +
            "WHERE t.countryName = :#{#region.countryName} " +
            "AND t.stateName IS NULL AND t.cityName IS NULL " +
            "AND (t.year = :#{#region.startYear} OR t.year = :#{#region.endYear}) ORDER BY t.year DESC")
    List<Temperature> getCountryTemperatureByYearRange(@Param("region") RegionInformation region);

    @Query("SELECT t FROM Temperature t " +
            "WHERE t.cityName = :#{#region.regionName} " +
            "AND t.countryName = :#{#region.countryName} " +
            "AND t.latitude = :#{#region.latitude} " +
            "AND t.longitude = :#{#region.longitude} " +
            "AND (t.year = :#{#region.startYear} OR t.year = :#{#region.endYear}) ORDER BY t.year DESC")
    List<Temperature> getCityTemperatureByYearRange(@Param("region") RegionInformation region);

    @Query("SELECT t FROM Temperature t " +
            "WHERE t.stateName = :#{#region.regionName} " +
            "AND t.countryName = :#{#region.countryName} " +
            "AND (t.year = :#{#region.startYear} OR t.year = :#{#region.endYear}) ORDER BY t.year DESC")
    List<Temperature> getStateTemperatureByYearRange(@Param("region") RegionInformation region);

    @Query("SELECT " +
            "t1.avgTemp - t2.avgTemp, " +
            "t1.minTemp - t2.minTemp, " +
            "t1.maxTemp - t2.maxTemp " +
            "FROM Temperature t1, Temperature t2 " +
            "WHERE t1.year = :#{#region.endYear} AND t1.countryName = :#{#region.countryName} " +
            "AND t2.year = :#{#region.startYear} AND t2.countryName = :#{#region.countryName} " +
            "AND t1.stateName IS NULL AND t1.cityName IS NULL " +
            "AND t2.stateName IS NULL AND t2.cityName IS NULL")
    Tuple getCountryTemperatureDiff(@Param("region") RegionInformation region);


    @Query("SELECT " +
            "t1.avgTemp - t2.avgTemp, " +
            "t1.minTemp - t2.minTemp, " +
            "t1.maxTemp - t2.maxTemp " +
            "FROM Temperature t1, Temperature t2 " +
            "WHERE t1.cityName = :#{#region.regionName} AND t2.cityName = :#{#region.regionName} " +
            "AND t1.year = :#{#region.endYear} AND t1.countryName = :#{#region.countryName} " +
            "AND t2.year = :#{#region.startYear} AND t2.countryName = :#{#region.countryName} " +
            "AND t1.latitude = :#{#region.latitude} AND t1.longitude = :#{#region.longitude} " +
            "AND t2.latitude = :#{#region.latitude} AND t2.longitude = :#{#region.longitude}")
    Tuple getCityTemperatureDiff(@Param("region") RegionInformation region);


    @Query("SELECT " +
            "t1.avgTemp - t2.avgTemp, " +
            "t1.minTemp - t2.minTemp, " +
            "t1.maxTemp - t2.maxTemp " +
            "FROM Temperature t1, Temperature t2 " +
            "WHERE t1.stateName = :#{#region.regionName} AND t2.stateName = :#{#region.regionName} " +
            "AND t1.year = :#{#region.endYear} AND t1.countryName = :#{#region.countryName} " +
            "AND t2.year = :#{#region.startYear} AND t2.countryName = :#{#region.countryName}")
    Tuple getStateTemperatureDiff(@Param("region") RegionInformation region);


    @Query("SELECT AVG(t.avgTemp), AVG(t.minTemp), AVG(t.maxTemp) " +
            "FROM Temperature t WHERE t.countryName = :#{#region.countryName} " +
            "AND t.year >= :#{#region.startYear} AND t.year <= :#{#region.endYear}")
    Tuple getCountryAverageTemperatureInTimePeriod(@Param("region") RegionInformation region);

    @Query("SELECT AVG(t.avgTemp), AVG(t.minTemp), AVG(t.maxTemp) " +
            "FROM Temperature t WHERE t.countryName = :#{#region.countryName} " +
            "AND t.stateName = :#{#region.regionName} " +
            "AND t.year >= :#{#region.startYear} AND t.year <= :#{#region.endYear}")
    Tuple getStateAverageTemperatureInTimePeriod(@Param("region") RegionInformation region);

    @Query("SELECT AVG(t.avgTemp), AVG(t.minTemp), AVG(t.maxTemp) " +
            "FROM Temperature t WHERE t.countryName = :#{#region.countryName} " +
            "AND t.cityName = :#{#region.regionName} " +
            "AND t.latitude = :#{#region.latitude} " +
            "AND t.longitude = :#{#region.longitude} " +
            "AND t.year >= :#{#region.startYear} AND t.year <= :#{#region.endYear}")
    Tuple getCityAverageTemperatureInTimePeriod(@Param("region") RegionInformation region);

    @Query("SELECT t FROM Temperature t " +
            "WHERE t.countryName = :#{#region.countryName} " +
            "AND t.stateName IS NULL AND t.cityName IS NULL ")
    List<Temperature> getTemperatureListByCountry(@Param("region") RegionInformation region);

    @Query("SELECT t FROM Temperature t " +
            "WHERE t.cityName = :#{#region.regionName} " +
            "AND t.countryName = :#{#region.countryName} " +
            "AND t.latitude = :#{#region.latitude} " +
            "AND t.longitude = :#{#region.longitude} ")
    List<Temperature> getTemperatureListByCity(@Param("region") RegionInformation region);

    @Query("SELECT t FROM Temperature t " +
            "WHERE t.stateName = :#{#region.regionName} " +
            "AND t.countryName = :#{#region.countryName} ")
    List<Temperature> getTemperatureListByState(@Param("region") RegionInformation region);


    @Query(value = """
            SELECT\s
              p.start_year AS periodStartYear,\s
              p.end_year AS periodEndYear,\s
              temperature.country_id AS country,\s
              temperature.city_id AS city,\s
              temperature.state_id AS state,\s
              AVG(
                temperature.average_temperature
              ) AS averageTemperature,\s
              MAX(temperature.max_temperature) AS maxTemperature,\s
              MIN(temperature.min_temperature) AS minTemperature,\s
              ABS(
                AVG(
                  temperature.average_temperature
                ) - (
                  SELECT\s
                    AVG(average_temperature)\s
                  FROM\s
                    temperature\s
                  WHERE\s
                    temperature.country_id = :countryId\s
                    AND temperature.city_id IS NULL\s
                    AND temperature.state_id IS NULL\s
                    AND temperature.year >= :startYear\s
                    AND temperature.year <= :endYear
                )
              ) AS temperatureDifference\s
            FROM\s
              (
                SELECT\s
                  year as start_year,\s
                  year + :timePeriod AS end_year\s
                FROM\s
                  (
                    SELECT\s
                      DISTINCT year\s
                    FROM\s
                      temperature
                  ) AS start_years\s
                WHERE\s
                  year + :timePeriod <= (
                    SELECT\s
                      MAX(year)\s
                    FROM\s
                      temperature
                  )
              ) AS p\s
              JOIN temperature ON temperature.year >= p.start_year\s
              AND temperature.year < p.end_year\s
            GROUP BY\s
              p.start_year,\s
              p.end_year,\s
              temperature.country_id,\s
              temperature.state_id,\s
              temperature.city_id\s
            HAVING\s
              ABS(
                AVG(
                  temperature.average_temperature
                ) - (
                  SELECT\s
                    AVG(average_temperature)\s
                  FROM\s
                    temperature\s
                  WHERE\s
                    temperature.country_id = :countryId\s
                    AND temperature.city_id IS NULL\s
                    AND temperature.state_id IS NULL\s
                    AND temperature.year >= :startYear\s
                    AND temperature.year <= :endYear
                )
              ) <= 1\s
            ORDER BY\s
              temperatureDifference\s
            LIMIT\s
              :limit
            """, nativeQuery = true)
    List<SimilarRegion> getSimilarRegionByCountryTemperature(@Param("countryId") int countryId,
                                                             @Param("startYear") int startYear,
                                                             @Param("endYear") int endYear,
                                                             @Param("timePeriod") int timePeriod,
                                                             @Param("limit") int limit);

    @Query(value = """
            SELECT\s
              p.start_year AS periodStartYear,\s
              p.end_year AS periodEndYear,\s
              temperature.country_id AS country,\s
              temperature.city_id AS city,\s
              temperature.state_id AS state,\s
              AVG(
                temperature.average_temperature
              ) AS averageTemperature,\s
              MAX(temperature.max_temperature) AS maxTemperature,\s
              MIN(temperature.min_temperature) AS minTemperature,\s
              ABS(
                AVG(
                  temperature.average_temperature
                ) - (
                  SELECT\s
                    AVG(average_temperature)\s
                  FROM\s
                    temperature\s
                  WHERE\s
                    temperature.country_id = :countryId\s
                    AND temperature.city_id IS NULL\s
                    AND temperature.state_id = :stateId\s
                    AND temperature.year >= :startYear\s
                    AND temperature.year <= :endYear
                )
              ) AS temperatureDifference\s
            FROM\s
              (
                SELECT\s
                  year as start_year,\s
                  year + :timePeriod AS end_year\s
                FROM\s
                  (
                    SELECT\s
                      DISTINCT year\s
                    FROM\s
                      temperature
                  ) AS start_years\s
                WHERE\s
                  year + :timePeriod <= (
                    SELECT\s
                      MAX(year)\s
                    FROM\s
                      temperature
                  )
              ) AS p\s
              JOIN temperature ON temperature.year >= p.start_year\s
              AND temperature.year < p.end_year\s
            GROUP BY\s
              p.start_year,\s
              p.end_year,\s
              temperature.country_id,\s
              temperature.state_id,\s
              temperature.city_id\s
            HAVING\s
              ABS(
                AVG(
                  temperature.average_temperature
                ) - (
                  SELECT\s
                    AVG(average_temperature)\s
                  FROM\s
                    temperature\s
                  WHERE\s
                    temperature.country_id = :countryId\s
                    AND temperature.city_id IS NULL\s
                    AND temperature.state_id = :state_id\s
                    AND temperature.year >= :startYear\s
                    AND temperature.year <= :endYear
                )
              ) <= 1\s
            ORDER BY\s
              temperatureDifference\s
            LIMIT\s
              :limit
            """, nativeQuery = true)
    List<SimilarRegion> getSimilarRegionByCountryStateTemperature(@Param("countryId") int countryId,
                                                                  @Param("stateId") int stateId,
                                                                  @Param("startYear") int startYear,
                                                                  @Param("endYear") int endYear,
                                                                  @Param("timePeriod") int timePeriod,
                                                                  @Param("limit") int limit);

    @Query(value = """
            SELECT\s
              p.start_year AS periodStartYear,\s
              p.end_year AS periodEndYear,\s
              temperature.country_id AS country,\s
              temperature.city_id AS city,\s
              temperature.state_id AS state,\s
              AVG(
                temperature.average_temperature
              ) AS averageTemperature,\s
              MAX(temperature.max_temperature) AS maxTemperature,\s
              MIN(temperature.min_temperature) AS minTemperature,\s
              ABS(
                AVG(
                  temperature.average_temperature
                ) - (
                  SELECT\s
                    AVG(average_temperature)\s
                  FROM\s
                    temperature\s
                  WHERE\s
                    temperature.country_id = :countryId\s
                    AND temperature.city_id = :cityId\s
                    AND temperature.state_id IS NULL\s
                    AND temperature.year >= :startYear\s
                    AND temperature.year <= :endYear
                )
              ) AS temperatureDifference\s
            FROM\s
              (
                SELECT\s
                  year as start_year,\s
                  year + :timePeriod AS end_year\s
                FROM\s
                  (
                    SELECT\s
                      DISTINCT year\s
                    FROM\s
                      temperature
                  ) AS start_years\s
                WHERE\s
                  year + :timePeriod <= (
                    SELECT\s
                      MAX(year)\s
                    FROM\s
                      temperature
                  )
              ) AS p\s
              JOIN temperature ON temperature.year >= p.start_year\s
              AND temperature.year < p.end_year\s
            GROUP BY\s
              p.start_year,\s
              p.end_year,\s
              temperature.country_id,\s
              temperature.state_id,\s
              temperature.city_id\s
            HAVING\s
              ABS(
                AVG(
                  temperature.average_temperature
                ) - (
                  SELECT\s
                    AVG(average_temperature)\s
                  FROM\s
                    temperature\s
                  WHERE\s
                    temperature.country_id = :countryId\s
                    AND temperature.city_id = :cityId\s
                    AND temperature.state_id IS NULL\s
                    AND temperature.year >= :startYear\s
                    AND temperature.year <= :endYear
                )
              ) <= 1\s
            ORDER BY\s
              temperatureDifference\s
            LIMIT\s
              :limit
            """, nativeQuery = true)
    List<SimilarRegion> getSimilarRegionByCountryCityTemperature(@Param("countryId") int countryId,
                                                                  @Param("cityId") int cityId,
                                                                  @Param("startYear") int startYear,
                                                                  @Param("endYear") int endYear,
                                                                  @Param("timePeriod") int timePeriod,
                                                                  @Param("limit") int limit);
}
