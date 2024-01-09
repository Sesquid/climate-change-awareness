package com.example.Climate.advice;

import com.example.Climate.exception.GlobalTempSQLException;
import com.example.Climate.exception.QueryDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(GlobalTempSQLException.class)
    public ResponseEntity<?> handleGlobalTempSQLExceptionException(GlobalTempSQLException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(QueryDataException.class)
    public ResponseEntity<?> handleQueryDataExceptionException(QueryDataException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
