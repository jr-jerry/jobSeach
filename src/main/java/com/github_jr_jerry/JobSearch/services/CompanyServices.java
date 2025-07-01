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

    public Company updateById(int id,Company updatedData){
        Optional<Company> box=companyRepo.findById(id);
            if(box.isPresent()){
                Company oldData=box.get();
                oldData.setName(updatedData.getName()!=null && updatedData.getName().isEmpty()?updatedData.getName(): oldData.getName());
                oldData.setJobsList(updatedData.getJobsList());
                return companyRepo.save(oldData);

            }
            else{
                throw new RuntimeException("error saving company with id : "+id);
            }


    }

}
