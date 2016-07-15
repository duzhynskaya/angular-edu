package edu.tech.sample.service;

import edu.tech.sample.dao.CompanyRepository;
import edu.tech.sample.domain.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    /**
     * Find company by id.
     * @return a persistent instance or null
     */
    @Override
    public Company findOne(Long id) {
        return companyRepository.findOne(id);
    }

    @Override
    public Page<Company> findAll(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    /**
     * Create or update method.
     * @param company a transient instance to be made persistent OR
     *                a detached instance with state to be copied
     * @return an updated managed instance
     */
    @Override
    public Company save(Company company) {
        Assert.notNull(company);
        return companyRepository.save(company);
    }
}
