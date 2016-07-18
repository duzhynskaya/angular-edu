package edu.tech.sample.controller;

import edu.tech.sample.model.CompanyDto;
import edu.tech.sample.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/companies")
public class CompanyRestController {

    private final CompanyService companyService;


    @RequestMapping(method = RequestMethod.GET)
    public Page<CompanyDto> listAll(Pageable pageable) {
        return companyService.findAll(pageable);
    }

    @RequestMapping(value = "/{companyId}", method = RequestMethod.GET)
    public CompanyDto getCompany(@PathVariable Long companyId) {
        return companyService.findOne(companyId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long addCompany(@RequestBody @Valid CompanyDto company) {
        return companyService.save(company);
    }

    @RequestMapping(value = "/{companyId}", method = RequestMethod.PUT)
    public Long editCompany(@PathVariable Long companyId, @RequestBody @Valid CompanyDto company) {
        return companyService.save(company);
    }

    @RequestMapping(value = "/{companyId}", method = RequestMethod.DELETE)
    public void deleteCompany(@PathVariable Long companyId) {
        companyService.delete(companyId);
    }


    @Autowired
    CompanyRestController(CompanyService companyService) {
        this.companyService = companyService;
    }
}
