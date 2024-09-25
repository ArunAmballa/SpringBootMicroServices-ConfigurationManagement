package com.eazybytes.accounts.advice;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiResponse<T>{

    private T data;

    private ApiError error;

    private LocalDateTime timestamp;

    public ApiResponse(){
        this.timestamp=LocalDateTime.now();
    }
    public ApiResponse(T data){
        this();
        this.data = data;
    }

    public ApiResponse(ApiError error){
        this();
        this.error = error;
    }
    // Constructor with T data, ApiError, and timestamp
    public ApiResponse(T data, ApiError error, LocalDateTime timestamp){
        this.data = data;
        this.error = error;
        this.timestamp = timestamp;
    }
}
