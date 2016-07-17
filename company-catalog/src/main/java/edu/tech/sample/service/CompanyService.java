package edu.tech.sample.service;


import edu.tech.sample.model.CompanyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService {
    CompanyDto findOne(Long id);

    List<CompanyDto> findAll();

    Page<CompanyDto> findAll(Pageable pageable);

    Long save(CompanyDto company);

    void delete(Long id);
}