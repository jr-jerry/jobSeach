package com.github_jr_jerry.JobSearch.services;

import com.github_jr_jerry.JobSearch.model.Company;
import com.github_jr_jerry.JobSearch.repository.CompanyRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CompanyServices {
    @Autowired
    CompanyRespository companyRepo;

    public List<Company> getAllCompany(){
        return companyRepo.findAll();
    }

    public Company createCompany(Company companyData){
        try{
            return companyRepo.save(companyData);
        } catch (RuntimeException e) {
            throw new RuntimeException("error in saving "+e.getMessage());
        }
    }
    public void delCompany(int id){
        try{
            companyRepo.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("company not found related with Id "+id+" "+e.getMessage());
        }
    }
    public Optional<Company> getById(int id){
        return companyRepo.findById(id);
    }

}
