package com.github_jr_jerry.JobSearch.services;

import com.github_jr_jerry.JobSearch.model.Jobs;
import com.github_jr_jerry.JobSearch.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Job_Services implements JobServices{
    @Autowired
    JobRepository jobRepo;

    @Override
    public List<Jobs> getAll() {
        return jobRepo.findAll();
    }

    @Override
    public Jobs postJobs(Jobs job) {
        try{
            return jobRepo.save(job);
        }
        catch (Exception e){
            throw new RuntimeException("error in saving job "+e.getMessage());
        }
    }
    @Override
    public Optional<Jobs> getById(int id){
        return jobRepo.findById(id);

    }

    @Override
    public void delJobs(int id) {
        Optional<Jobs> jobBox=jobRepo.findById(id);

        if(jobBox.isPresent()){
            jobRepo.deleteById(id);
        }
        else{
            throw new RuntimeException("no entry relatedt this id present ");
        }
    }

    @Override
    public boolean upDateById_S(int id,Jobs jobData) {
        Optional<Jobs> box=jobRepo.findById(id);
        if(box.isPresent()){
          Jobs job= box.get();
            job.setTitle(jobData.getTitle());
                job.setDescription(jobData.getDescription());
                job.setMinSalary(jobData.getMinSalary());
                job.setLocation(jobData.getLocation());
                job.setMaxSalary(jobData.getMaxSalary());
                jobRepo.save(job);
                return true;
        }
        return false;
    }

}
