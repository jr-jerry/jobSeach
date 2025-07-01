package com.github_jr_jerry.JobSearch.repository;

import com.github_jr_jerry.JobSearch.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRespository extends JpaRepository<Company,Integer> {
}
