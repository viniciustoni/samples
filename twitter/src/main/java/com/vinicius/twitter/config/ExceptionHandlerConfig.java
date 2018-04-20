package com.vinicius.twitter.config;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vinicius.twitter.exceptions.RelationshipAlreadyExistException;
import com.vinicius.twitter.exceptions.UserNotFoundException;

@ControllerAdvice
public class ExceptionHandlerConfig
{
    @ExceptionHandler(UserNotFoundException.class)
    public @ResponseBody ResponseEntity<String> userNotFound(HttpServletRequest req, UserNotFoundException ex)
    {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(RelationshipAlreadyExistException.class)
    public @ResponseBody ResponseEntity<String> relationshipAlreadyExistException(HttpServletRequest req,
            RelationshipAlreadyExistException ex)
    {
        return new ResponseEntity<>(ex.getMessage(), CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody ResponseEntity<String> validationExceptionHandler(HttpServletRequest req,
            MethodArgumentNotValidException ex)
    {
        return new ResponseEntity<>(ex.getMessage(), BAD_REQUEST);
    }
}
