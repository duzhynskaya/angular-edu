package edu.tech.sample.service;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
class CompanyNotFoundException extends RuntimeException {

    CompanyNotFoundException(Long companyId) {
        super("Could not find company '" + companyId + "'.");
    }
}
