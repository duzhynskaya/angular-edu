package edu.tech.sample.web;

import edu.tech.sample.dto.CompanyDto;
import edu.tech.sample.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/companies")
public class CompanyRestController {

    private final CompanyService companyService;


    @RequestMapping(method = RequestMethod.GET)
    public Page<CompanyDto> list() {
        return companyService.findAll(new PageRequest(0, 100));
    }


    @RequestMapping(value = "/{companyId}", method = RequestMethod.GET)
    public CompanyDto get(@PathVariable Long companyId) {
        return companyService.findOne(companyId);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Long add(@RequestBody @Valid CompanyDto company, BindingResult result) {
        if (result.hasErrors()) {
            return null;
        }
        return companyService.save(company);
    }


    @RequestMapping(value = "/edit/{companyId}", method = RequestMethod.PUT)
    public Long edit(@PathVariable Long companyId,
                     @RequestBody @Valid CompanyDto company, BindingResult result) {
        if (result.hasErrors()) {
            return null;
        }
        return companyService.save(company);
    }


    @RequestMapping(value = "/delete/{companyId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long companyId) {
        companyService.delete(companyId);
    }


    @Autowired
    CompanyRestController(CompanyService companyService) {
        this.companyService = companyService;
    }
}
