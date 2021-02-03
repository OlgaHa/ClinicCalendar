package com.example.cliniccalendar.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseDTO<T> extends ResponseEntity<T> {

    public ResponseDTO(T data, HttpStatus status) {
        super(data, status);
    }

}
