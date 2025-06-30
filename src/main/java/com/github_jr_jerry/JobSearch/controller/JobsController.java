package com.github_jr_jerry.JobSearch.controller;

import com.github_jr_jerry.JobSearch.model.Jobs;
import com.github_jr_jerry.JobSearch.services.Job_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Boolean> createJob(@RequestBody Jobs jobData){
        jobServices.postJobs(jobData);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Jobs> findById(@PathVariable int id){
        if(jobServices.getById(id)==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(jobServices.getById(id));
    }
    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> DelById(@PathVariable int id){
        if(jobServices.getById(id)!=null) {
            jobServices.delJobs(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable int id,@RequestBody Jobs jobData){
        boolean updated=jobServices.upDateById_S(id,jobData);
        if(updated)
            return new ResponseEntity<>("Successfully Updated",HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
