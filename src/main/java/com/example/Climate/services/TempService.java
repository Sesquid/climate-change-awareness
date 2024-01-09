package com.example.Climate.services;

import com.example.Climate.exception.QueryDataException;
import com.example.Climate.models.Temp;
import com.example.Climate.repositories.TempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempService {
    @Autowired
    TempRepository repo;

    public List<Temp> getAllTemp() {
        List<Temp> tempList = repo.findAll();
        if (tempList.isEmpty()) {
            throw new QueryDataException("All temp list is empty!");
        }
        return tempList;
    }
}
