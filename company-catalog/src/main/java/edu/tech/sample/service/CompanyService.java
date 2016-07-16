package edu.tech.sample.service;


import edu.tech.sample.dto.CompanyDto;
import edu.tech.sample.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyService {
    CompanyDto findOne(Long id);

    Page<CompanyDto> findAll(Pageable pageable);

    Long save(CompanyDto company);

    void delete(Long id);
}