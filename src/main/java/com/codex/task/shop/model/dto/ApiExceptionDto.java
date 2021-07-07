package com.codex.task.shop.model.dto;

import com.codex.task.shop.exception.ApiException;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
@Builder
public class ApiExceptionDto {

    private HttpStatus status;
    private String message;
    @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public static ApiExceptionDto of(ApiException ex) {
        return ApiExceptionDto.builder()
                .status(ex.getStatus())
                .message(ex.getMessage())
                .timestamp(ex.getTimestamp())
                .build();
    }

}