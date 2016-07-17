package edu.tech.sample.dao;

import edu.tech.sample.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;


@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    @Query("select c from Company c")
    Stream<Company> findAllAsStream();

    Page<Company> findAll(Pageable pageable);

    /**
     * Finds a company by id.
     * @return a managed instance
     */
    Optional<Company> findById(Long id);

    /**
     * Create or update method.
     * @param company a transient instance to be made persistent OR
     *                a detached instance with state to be copied
     * @return an updated managed instance
     */
    Company save(Company company);
}