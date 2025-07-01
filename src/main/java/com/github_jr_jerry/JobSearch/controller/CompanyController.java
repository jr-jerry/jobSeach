package com.github_jr_jerry.JobSearch.controller;

import com.github_jr_jerry.JobSearch.model.Company;
import com.github_jr_jerry.JobSearch.services.CompanyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/company")
@RestController
public class CompanyController {
    @Autowired
    CompanyServices companySer;

    @GetMapping
    public ResponseEntity<?> getAllEndpoint(){
       List<Company> list= companySer.getAllCompany();
       return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postEndpoint(@RequestBody Company companyData){
        try{
            return new ResponseEntity<>(companySer.createCompany(companyData),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delEndpoint(@PathVariable int id){
        try{
            companySer.delCompany(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


}
