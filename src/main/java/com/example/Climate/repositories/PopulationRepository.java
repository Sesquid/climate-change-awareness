package com.example.Climate.repositories;

import com.example.Climate.models.Population;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopulationRepository extends JpaRepository<Population, Integer> {
}
