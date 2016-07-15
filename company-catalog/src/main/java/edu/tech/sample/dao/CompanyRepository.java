package edu.tech.sample.dao;

import edu.tech.sample.domain.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface CompanyRepository extends CrudRepository<Company, Long> {
    Page<Company> findAll(Pageable pageable);
}