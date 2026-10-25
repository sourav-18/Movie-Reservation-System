package com.ms.backend.exceptions;

import com.ms.backend.dtos.response.ApiErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponseDto> handleException(MethodArgumentNotValidException ex){
        Map<String, List<String>> errorsMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(
                                error -> Objects.requireNonNullElse(
                                        error.getDefaultMessage(),
                                        "Invalid value"
                                ),
                                Collectors.toList()
                        )
                ));
        Map.Entry<String,List<String>> entry=errorsMessages.entrySet().iterator().next();
        String errorsMessage=entry.getKey()+" "+String.join(" and ",entry.getValue());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponseDto(HttpStatus.BAD_REQUEST.value(),errorsMessage));
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponseDto> handleException(EmailAlreadyExistException ex) {
        return ResponseEntity.status(400)
                .body(new ApiErrorResponseDto(400,ex.getMessage()));
    }
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ApiErrorResponseDto> handleException(DataNotFoundException ex) {
        return ResponseEntity.status(404)
                .body(new ApiErrorResponseDto(404,ex.getMessage()));
    }

    @ExceptionHandler(InActiveStatusException.class)
    public ResponseEntity<ApiErrorResponseDto> handleException(InActiveStatusException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(new ApiErrorResponseDto(ex.getStatus().value(),ex.getMessage()));
    }

    @ExceptionHandler(InvalidUploadException.class)
    public ResponseEntity<ApiErrorResponseDto> handleException(InvalidUploadException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(new ApiErrorResponseDto(ex.getStatus().value(),ex.getMessage()));
    }

//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ApiErrorResponseDto> handleException(AccessDeniedException ex) {
//        return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                .body(new ApiErrorResponseDto(HttpStatus.FORBIDDEN.value(),"You don't have permission to access this resource."));
//    }


}
