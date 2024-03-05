package com.example.Climate.repositories;

import com.example.Climate.dto.CompareTemperature;
import com.example.Climate.dto.SimilarRegion;
import com.example.Climate.dto.TemperatureDTO;
import com.example.Climate.dto.YearRange;
import com.example.Climate.models.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Integer> {


    @Query("SELECT new com.example.Climate.dto.YearRange(MIN(t.year), MAX(t.year)) FROM Temperature t")
    YearRange findTemperatureYearRange();

    //
//    @Query("""
//            SELECT t FROM Temperature t
//            WHERE t.country.id = :countryId
//            AND t.stateName IS NULL
//            AND t.cityName IS NULL
//            AND (t.year = :startYear OR t.year = :endYear)
//            ORDER BY t.year DESC
//            """)
//    List<Temperature> getCountryTemperatureByYearRange(@Param("countryId") int countryId,
//                                                       @Param("startYear") int startYear,
//                                                       @Param("endYear") int endYear);
//
//    @Query("""
//            SELECT t FROM Temperature t
//            WHERE t.country.id = :countryId
//            AND t.city.id = :cityId
//            AND (t.year = :startYear OR t.year = :endYear)
//            ORDER BY t.year DESC
//            """)
//    List<Temperature> getCityTemperatureByYearRange(@Param("countryId") int countryId,
//                                                    @Param("cityId") int cityId,
//                                                    @Param("startYear") int startYear,
//                                                    @Param("endYear") int endYear);
//
//    @Query("""
//            SELECT t FROM Temperature t
//            WHERE t.country.id = :countryId
//            AND t.state.id = :stateId
//            AND (t.year = :startYear OR t.year = :endYear)
//            ORDER BY t.year DESC
//            """)
//    List<Temperature> getStateTemperatureByYearRange(@Param("countryId") int countryId,
//                                                     @Param("countryId") int stateId,
//                                                     @Param("startYear") int startYear,
//                                                     @Param("endYear") int endYear);
//
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
            FROM Temperature t1, Temperature t2
            WHERE t1.year = :endYear AND t1.country.id = :countryId
            AND t2.year = :startYear AND t2.country.id = :countryId
            AND t1.state.id IS NULL AND t1.city.id IS NULL
            AND t2.state.id IS NULL AND t2.city.id IS NULL
            """)
    CompareTemperature getCountryTemperatureDiff(@Param("countryId") int countryId,
                                                 @Param("startYear") int startYear,
                                                 @Param("endYear") int endYear);


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
            FROM Temperature t1, Temperature t2
            WHERE t1.year = :endYear AND t1.country.id = :countryId
            AND t2.year = :startYear AND t2.country.id = :countryId
            AND t1.state.id IS NULL AND t1.city.id = :cityId
            AND t2.state.id IS NULL AND t2.city.id = :cityId
            """)
    CompareTemperature getCityTemperatureDiff(@Param("countryId") int countryId,
                                              @Param("cityId") int cityId,
                                              @Param("startYear") int startYear,
                                              @Param("endYear") int endYear);


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
            FROM Temperature t1, Temperature t2
            WHERE t1.year = :endYear AND t1.country.id = :countryId
            AND t2.year = :startYear AND t2.country.id = :countryId
            AND t1.state.id = :stateId AND t1.city.id IS NULL
            AND t2.state.id = :stateId AND t2.city.id IS NULL
            """)
    CompareTemperature getStateTemperatureDiff(@Param("countryId") int countryId,
                                               @Param("stateId") int stateId,
                                               @Param("startYear") int startYear,
                                               @Param("endYear") int endYear);

    //
//
    @Query("""
            SELECT AVG(t.avgTemp) AS avgTemp,
            AVG(t.minTemp) AS minTemp,
            AVG(t.maxTemp) AS maxTemp
            FROM Temperature t
            WHERE t.country.id = :countryId
            AND t.state.id IS NULL
            AND t.city.id IS NULL
            AND t.year >= :startYear
            AND t.year <= :startYear + :timePeriod
            """)
    TemperatureDTO getCountryAverageTemperatureInTimePeriod(@Param("countryId") int countryId,
                                                            @Param("startYear") int startYear,
                                                            @Param("timePeriod") int timePeriod);

    @Query("""
            SELECT AVG(t.avgTemp) AS avgTemp,
            AVG(t.minTemp) AS minTemp,
            AVG(t.maxTemp) AS maxTemp
            FROM Temperature t
            WHERE t.country.id = :countryId
            AND t.state.id = :stateId
            AND t.year >= :startYear
            AND t.year <= :startYear + :timePeriod
            """)
    TemperatureDTO getStateAverageTemperatureInTimePeriod(@Param("countryId") int countryId,
                                                          @Param("stateId") int stateId,
                                                          @Param("startYear") int startYear,
                                                          @Param("timePeriod") int timePeriod);

    @Query("""
            SELECT AVG(t.avgTemp) AS avgTemp,
            AVG(t.minTemp) AS minTemp,
            AVG(t.maxTemp) AS maxTemp
            FROM Temperature t
            WHERE t.country.id = :countryId
            AND t.city.id = :cityId
            AND t.year >= :startYear
            AND t.year <= :startYear + :timePeriod
            """)
    TemperatureDTO getCityAverageTemperatureInTimePeriod(@Param("countryId") int countryId,
                                                         @Param("cityId") int cityId,
                                                         @Param("startYear") int startYear,
                                                         @Param("timePeriod") int timePeriod);

    //
    @Query("SELECT t FROM Temperature t " +
            "WHERE t.country.id = :countryId " +
            "AND t.state.id IS NULL " +
            "AND t.city.id IS NULL")
    List<Temperature> getTemperatureListByCountry(@Param("countryId") int countryId);

    @Query("""
            SELECT t FROM Temperature t
            WHERE t.country.id = :countryId
            AND t.city.id = :cityId
            """)
    List<Temperature> getTemperatureListByCity(@Param("countryId") int countryId,
                                               @Param("cityId") int cityId);

    @Query("""
            SELECT t FROM Temperature t
            WHERE t.country.id = :countryId
            AND t.state.id = :stateId
            """)
    List<Temperature> getTemperatureListByState(@Param("countryId") int countryId,
                                                @Param("stateId") int stateId);

    //
//
    @Query(value = """
            SELECT\s
              p.start_year AS periodStartYear,
              p.end_year AS periodEndYear,
              temperature.country_id AS country,
              temperature.city_id AS city,
              temperature.state_id AS state,
              AVG(
                temperature.average_temperature
              ) AS averageTemperature,
              MAX(temperature.max_temperature) AS maxTemperature,
              MIN(temperature.min_temperature) AS minTemperature,
              ABS(
                AVG(
                  temperature.average_temperature
                ) - (
                  SELECT
                    AVG(average_temperature)
                  FROM
                    temperature
                  WHERE
                    temperature.country_id = :countryId
                    AND temperature.city_id IS NULL
                    AND temperature.state_id IS NULL
                    AND temperature.year >= :startYear
                    AND temperature.year <= :startYear + :timePeriod
                )
              ) AS temperatureDifference
            FROM
              (
                SELECT
                  year as start_year,
                  year + :timePeriod AS end_year
                FROM
                  (
                    SELECT
                      DISTINCT year
                    FROM
                      temperature
                  ) AS start_years
                WHERE
                  year + :timePeriod <= (
                    SELECT
                      MAX(year)
                    FROM
                      temperature
                  )
              ) AS p
              JOIN temperature ON temperature.year >= p.start_year
              AND temperature.year < p.end_year
            GROUP BY
              p.start_year,
              p.end_year,
              temperature.country_id,
              temperature.state_id,
              temperature.city_id
            HAVING
              ABS(
                AVG(
                  temperature.average_temperature
                ) - (
                  SELECT
                    AVG(average_temperature)
                  FROM
                    temperature
                  WHERE
                    temperature.country_id = :countryId
                    AND temperature.city_id IS NULL
                    AND temperature.state_id IS NULL
                    AND temperature.year >= :startYear
                    AND temperature.year <= :startYear + :timePeriod
                )
              ) <= 1
            ORDER BY
              temperatureDifference
            LIMIT
              :limit
            """, nativeQuery = true)
    List<SimilarRegion> getSimilarRegionByCountryTemperature(@Param("countryId") int countryId,
                                                             @Param("startYear") int startYear,
                                                             @Param("timePeriod") int timePeriod,
                                                             @Param("limit") int limit);

    @Query(value = """
            SELECT
              p.start_year AS periodStartYear,
              p.end_year AS periodEndYear,
              temperature.country_id AS country,
              temperature.city_id AS city,
              temperature.state_id AS state,
              AVG(
                temperature.average_temperature
              ) AS averageTemperature,
              MAX(temperature.max_temperature) AS maxTemperature,
              MIN(temperature.min_temperature) AS minTemperature,
              ABS(
                AVG(
                  temperature.average_temperature
                ) - (
                  SELECT
                    AVG(average_temperature)
                  FROM
                    temperature
                  WHERE
                    temperature.country_id = :countryId
                    AND temperature.state_id = :stateId
                    AND temperature.city_id IS NULL
                    AND temperature.year >= :startYear
                    AND temperature.year <= :startYear + :timePeriod
                )
              ) AS temperatureDifference
            FROM
              (
                SELECT
                  year as start_year,
                  year + :timePeriod AS end_year
                FROM
                  (
                    SELECT
                      DISTINCT year
                    FROM
                      temperature
                  ) AS start_years
                WHERE
                  year + :timePeriod <= (
                    SELECT
                      MAX(year)
                    FROM
                      temperature
                  )
              ) AS p
              JOIN temperature ON temperature.year >= p.start_year
              AND temperature.year < p.end_year
            GROUP BY
              p.start_year,
              p.end_year,
              temperature.country_id,
              temperature.state_id,
              temperature.city_id
            HAVING
              ABS(
                AVG(
                  temperature.average_temperature
                ) - (
                  SELECT
                    AVG(average_temperature)
                  FROM
                    temperature
                  WHERE
                    temperature.country_id = :countryId
                    AND temperature.state_id = :stateId
                    AND temperature.city_id IS NULL
                    AND temperature.year >= :startYear
                    AND temperature.year <= :startYear + :timePeriod
                )
              ) <= 1
            ORDER BY
              temperatureDifference
            LIMIT
              :limit
            """, nativeQuery = true)
    List<SimilarRegion> getSimilarRegionByCountryStateTemperature(@Param("countryId") int countryId,
                                                                  @Param("stateId") int stateId,
                                                                  @Param("startYear") int startYear,
                                                                  @Param("timePeriod") int timePeriod,
                                                                  @Param("limit") int limit);

    @Query(value = """
            SELECT
              p.start_year AS periodStartYear,
              p.end_year AS periodEndYear,
              temperature.country_id AS country,
              temperature.city_id AS city,
              temperature.state_id AS state,
              AVG(
                temperature.average_temperature
              ) AS averageTemperature,
              MAX(temperature.max_temperature) AS maxTemperature,
              MIN(temperature.min_temperature) AS minTemperature,
              ABS(
                AVG(
                  temperature.average_temperature
                ) - (
                  SELECT
                    AVG(average_temperature)
                  FROM
                    temperature
                  WHERE
                    temperature.country_id = :countryId
                    AND temperature.city_id = :cityId
                    AND temperature.state_id IS NULL
                    AND temperature.year >= :startYear
                    AND temperature.year <= :startYear + :timePeriod
                )
              ) AS temperatureDifference
            FROM
              (
                SELECT
                  year as start_year,
                  year + :timePeriod AS end_year
                FROM
                  (
                    SELECT
                      DISTINCT year
                    FROM
                      temperature
                  ) AS start_years
                WHERE
                  year + :timePeriod <= (
                    SELECT
                      MAX(year)
                    FROM
                      temperature
                  )
              ) AS p
              JOIN temperature ON temperature.year >= p.start_year
              AND temperature.year < p.end_year
            GROUP BY
              p.start_year,
              p.end_year,
              temperature.country_id,
              temperature.state_id,
              temperature.city_id
            HAVING
              ABS(
                AVG(
                  temperature.average_temperature
                ) - (
                  SELECT
                    AVG(average_temperature)
                  FROM
                    temperature
                  WHERE
                    temperature.country_id = :countryId
                    AND temperature.city_id = :cityId
                    AND temperature.state_id IS NULL
                    AND temperature.year >= :startYear
                    AND temperature.year <= :startYear + :timePeriod
                )
              ) <= 1
            ORDER BY
              temperatureDifference
            LIMIT
              :limit
            """, nativeQuery = true)
    List<SimilarRegion> getSimilarRegionByCountryCityTemperature(@Param("countryId") int countryId,
                                                                 @Param("cityId") int cityId,
                                                                 @Param("startYear") int startYear,
                                                                 @Param("timePeriod") int timePeriod,
                                                                 @Param("limit") int limit);
}
