package com.example.Climate.services;

import com.example.Climate.models.State;
import com.example.Climate.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    @Autowired
    StateRepository repo;

    public List<State> getAllStates() {
        return repo.findAll();
    }

    public List<State> getStateByCountryCode(String countryCode) {
        return repo.getStateByCountryCode(countryCode);
    }

    public int getNumberOfStates() {
        return repo.getNumberOfState();
    }
}
