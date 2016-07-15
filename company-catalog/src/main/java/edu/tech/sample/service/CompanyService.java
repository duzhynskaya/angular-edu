package edu.tech.sample.service;


import edu.tech.sample.domain.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyService {
    Company findOne(Long id);

    Page<Company> findAll(Pageable pageable);

    Company save(Company company);
}