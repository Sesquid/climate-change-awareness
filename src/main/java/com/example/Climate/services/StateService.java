package com.example.Climate.services;

import com.example.Climate.exception.QueryDataException;
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
        List<State> stateList = repo.findAll();
        if (stateList.isEmpty()) {
            throw new QueryDataException("All states list is empty!");
        }
        return stateList;
    }

    public List<State> getStateByCountryName(String countryName) {
        List<State> stateList = repo.getStateByCountryName(countryName);
        if (stateList.isEmpty()) {
            throw new QueryDataException("State list by country name is empty!");
        }
        return stateList;
    }

    public int getNumberOfStates () {
        int numberOfStates = repo.getNumberOfState();
        if(numberOfStates == 0) {
            throw new QueryDataException("Number of states is zero!");
        }
        return numberOfStates;
    }
}
