package edu.tech.sample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;


@ControllerAdvice
public class FormNotValidHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ModelMap processValidationError(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        ModelMap errorMap = new ModelMap();
        for (FieldError fieldError : errors) {
            errorMap.addAttribute(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ModelMap map = new ModelMap();
        map.addAttribute("hasErrors", true);
        map.addAttribute("bindingErrors", errorMap);

        return map;
    }
}