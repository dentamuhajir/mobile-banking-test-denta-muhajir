package com.bank.mobilebanking.web;

import com.bank.mobilebanking.domain.exception.BusinessException;
import com.bank.mobilebanking.web.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<?>> handleBusiness(BusinessException ex) {

        return ResponseEntity
                .status(ex.getStatus())
                .body(ApiResponse.error(
                        ex.getCode(),
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneric(Exception ex) {

        return ResponseEntity
                .status(500)
                .body(ApiResponse.error(
                        "INTERNAL_SERVER_ERROR",
                        ex.getMessage()
                ));
    }
}