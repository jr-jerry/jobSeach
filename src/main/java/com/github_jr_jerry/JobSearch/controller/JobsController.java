package com.github_jr_jerry.JobSearch.controller;

import com.github_jr_jerry.JobSearch.model.Jobs;
import com.github_jr_jerry.JobSearch.services.Job_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobsController {
    @Autowired
    Job_Services jobServices;

    @GetMapping()
    public ResponseEntity<List<Jobs>> findAll(){
        return new ResponseEntity<>(jobServices.getAll(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createJob(  @RequestBody Jobs jobData){
        try{
            Jobs saved=jobServices.postJobs(jobData);
            return new ResponseEntity<>(saved,HttpStatus.CREATED);

        }
        catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        return jobServices.getById(id)
                .map(job->new ResponseEntity<>(job,HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> DelById(@PathVariable int id){

        try{
            jobServices.delJobs(id);
            return new ResponseEntity<>("succesfully deleted id"+id,HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable int id,@RequestBody Jobs jobData){
        boolean updated=jobServices.upDateById_S(id,jobData);
        if(updated)
            return new ResponseEntity<>("Successfully Updated",HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
