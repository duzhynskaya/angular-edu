package edu.tech.sample.service;

import edu.tech.sample.dao.CompanyRepository;
import edu.tech.sample.model.CompanyDto;
import edu.tech.sample.entity.Company;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final Mapper dozerBean;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, Mapper dozerBean) {
        this.companyRepository = companyRepository;
        this.dozerBean = dozerBean;
    }

    /**
     * Finds all companies.
     * @return companies' DTOs
     */
    @Override
    public List<CompanyDto> findAll() {
        return companyRepository.findAllAsStream().
                map((e) -> dozerBean.map(e, CompanyDto.class)).
                collect(Collectors.toList());
    }

    /**
     * Finds all companies on the requested page.
     * @return companies' DTOs
     */
    @Override
    public Page<CompanyDto> findAll(Pageable pageable) {
        return companyRepository.findAll(pageable).
                map((e) -> dozerBean.map(e, CompanyDto.class));
    }

    /**
     * Finds the company by the given id.
     * @return a company DTO
     * @throws CompanyNotFoundException in case company with given id does not exist
     */
    @Override
    public CompanyDto findOne(Long id) {
        Company company = companyRepository.findById(id).
                orElseThrow(() -> new CompanyNotFoundException(id));

        return dozerBean.map(company, CompanyDto.class);
    }

    /**
     * Saves a new company or modified company to repo.
     * @return company id
     */
    @Override
    public Long save(CompanyDto companyDto) {
        Assert.notNull(companyDto);

        Company company = dozerBean.map(companyDto, Company.class);

        return companyRepository.save(company).getId();
    }

    /**
     * Deletes the entity with the given id.
     * @throws CompanyNotFoundException in case company with given id does not exist
     */
    @Override
    public void delete(Long id) {
        if (!companyRepository.exists(id))
            throw new CompanyNotFoundException(id);

        companyRepository.delete(id);
    }
}
