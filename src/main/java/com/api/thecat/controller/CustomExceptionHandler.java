package com.api.thecat.controller;

import com.api.thecat.DTO.ErrorResponseDTO;
import com.api.thecat.DTO.EventLogDTO;
import com.api.thecat.enums.EventsEnum;
import com.api.thecat.exceptions.AlreadyExistsOrderException;
import com.api.thecat.exceptions.NotFoundException;
import com.api.thecat.exceptions.OrderException;
import com.api.thecat.exceptions.UnauthorizedException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        log.info(new EventLogDTO(EventsEnum.EXCEPTION, Map.of("exceptionMessage", Optional.ofNullable(ex.getMessage()).orElse("INTERNAL_SERVER_ERROR"))).toString());
        details.add(ex.getLocalizedMessage());
        ErrorResponseDTO error = new ErrorResponseDTO("Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(OrderException.class)
    public final ResponseEntity<Object> handleOcurrenceValidationErrors(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        log.info(new EventLogDTO(EventsEnum.EXCEPTION_VALIDATION_ERROR, Map.of("exceptionMessage", Optional.ofNullable(ex.getMessage()).orElse("BAD_REQUEST"))).toString());
        details.add(ex.getLocalizedMessage());
        ErrorResponseDTO error = new ErrorResponseDTO(ex.getMessage(), details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        log.info(new EventLogDTO(EventsEnum.EXCEPTION_ARGUMENT_NOT_VALID, Map.of("exceptionMessage", Optional.ofNullable(ex.getMessage()).orElse("BAD_REQUEST"))).toString());
        ErrorResponseDTO error = new ErrorResponseDTO("Validation Failed", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistsOrderException.class)
    public final ResponseEntity<Object> AlreadyExists(RuntimeException ex) {
        return getObjectResponseEntity(ex);
    }

    private ResponseEntity<Object> getObjectResponseEntity(Exception ex) {
        List<String> details = new ArrayList<>();
        log.info(new EventLogDTO(EventsEnum.EXCEPTION_ALREADY_EXISTS, Map.of("exceptionMessage", Optional.ofNullable(ex.getMessage()).orElse("ALREADY_EXISTS"))).toString());
        details.add(ex.getLocalizedMessage());
        ErrorResponseDTO error = new ErrorResponseDTO(ex.getMessage(), details);
        return new ResponseEntity(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(Exception ex) {
        List<String> details = new ArrayList<>();
        log.info(new EventLogDTO(EventsEnum.EXCEPTION_NOT_FOUND, Map.of("exceptionMessage",Optional.ofNullable(ex.getMessage()).orElse("NOT_FOUND"))).toString());
        details.add(ex.getLocalizedMessage());
        ErrorResponseDTO error = new ErrorResponseDTO(ex.getMessage(), details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public final ResponseEntity<Object> handleUnauthorizedException(Exception ex) {
        List<String> details = new ArrayList<>();
        log.info(new EventLogDTO(EventsEnum.EXCEPTION_UNAUTHORIZED, Map.of("exceptionMessage", Optional.ofNullable(ex.getMessage()).orElse("UNAUTHORIZED"))).toString());
        details.add(ex.getLocalizedMessage());
        ErrorResponseDTO error = new ErrorResponseDTO(ex.getMessage(), details);
        return new ResponseEntity(error, HttpStatus.UNAUTHORIZED);
    }


}