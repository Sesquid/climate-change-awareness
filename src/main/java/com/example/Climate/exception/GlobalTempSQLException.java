package com.example.Climate.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class GlobalTempSQLException extends RuntimeException {

    public GlobalTempSQLException(String message) {
        super(message);
    }
}
