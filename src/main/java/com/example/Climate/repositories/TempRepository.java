package com.example.Climate.repositories;

import com.example.Climate.models.Temp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempRepository extends JpaRepository<Temp, Integer> {

}
