package com.jnvconnect.iam_authentication.dtos;

import org.springframework.http.HttpStatus;

public record ErrorResponse(String message, HttpStatus status, int statusCode) {}
