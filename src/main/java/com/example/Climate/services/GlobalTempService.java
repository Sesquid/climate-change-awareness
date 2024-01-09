package com.example.Climate.services;

import com.example.Climate.exception.GlobalTempSQLException;
import com.example.Climate.models.GlobalTemp;
import com.example.Climate.repositories.GlobalTempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class GlobalTempService {
    @Autowired
    GlobalTempRepository repo;

    public List<GlobalTemp> getAllGlobalTemp() {
        List<GlobalTemp> globalTempList = repo.findAll();
        if (globalTempList.isEmpty()) {
            throw new GlobalTempSQLException("All globalTemp list is empty!");
        }
        return globalTempList;
    }

    public List<GlobalTemp> getGlobalTempByYear(int year) {
        List<GlobalTemp> globalTempList = repo.getGlobalTempByYear(year);
        if (globalTempList.isEmpty()) {
            throw new GlobalTempSQLException("GlobalTempList by year is empty!");
        }
        return globalTempList;
    }
}
