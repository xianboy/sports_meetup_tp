package com.sports.meetup.field.papi.exception;

import javax.ws.rs.BadRequestException;

import org.omg.CORBA.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.sports.common.constant.ConstantFields;
import com.sports.common.domain.ApiDefaultResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = { BadRequestException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiDefaultResponse badRequestException(BadRequestException ex) {
        return new ApiDefaultResponse(ConstantFields.getBadRequestCode(), ex.getMessage());
    }
    
    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiDefaultResponse illegalArgumentException(IllegalArgumentException ex) {
        return new ApiDefaultResponse(ConstantFields.getBadRequestCode(), ex.getMessage());
    }
    
    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiDefaultResponse methodArgumentNotValidException(MethodArgumentNotValidException ex) {
    	return new ApiDefaultResponse(ConstantFields.getBadRequestCode(), ex.getMessage());
    }
    
    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiDefaultResponse noHandlerFoundException(Exception ex) {
        return new ApiDefaultResponse(ConstantFields.getNotFoundCode(), ConstantFields.getNotFoundMsg(), null);
    }

    
    @ExceptionHandler(value = { UserException.class })
    public ApiDefaultResponse loginFaildException(UserException ex) {
    	return new ApiDefaultResponse(ConstantFields.getUserError502Code(), ex.getMessage());
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiDefaultResponse unknownException(Exception ex) {
        return new ApiDefaultResponse(ConstantFields.getSapiError509Code(), ex.getMessage());
    }
}
