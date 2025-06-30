package com.github_jr_jerry.JobSearch.services;

import com.github_jr_jerry.JobSearch.model.Jobs;
import com.github_jr_jerry.JobSearch.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
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
    public void postJobs(Jobs job) {
        try{
            jobRepo.save(job);
        }
        catch (Exception e){
            throw new RuntimeException("error in saving job");
        }
    }
    @Override
    public Jobs getById(int id){
        Optional<Jobs> jobBox=jobRepo.findById(id);
        if(jobBox.isPresent()){
            return jobBox.get();
        }
        return null;
    }

    @Override
    public void delJobs(int id) {
//        for(Jobs item:jobList) {
//            if (item.getId() == id) jobList.remove(item);
//        }currentModification Exception
//        Iterator<Jobs> iterator=jobList.iterator();
//        while(iterator.hasNext()){
//            Jobs job=iterator.next();
//            if (job.getId()==id){
//                iterator.remove();
//            }
//        }
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
        Iterator<Jobs> itr=jobList.iterator();
        while(itr.hasNext()){
            Jobs job=itr.next();
            if(job.getId()==id){

                job.setTitle(jobData.getTitle());
                job.setDescription(jobData.getDescription());
                job.setMinSalary(jobData.getMinSalary());
                job.setLocation(jobData.getLocation());
                job.setMaxSalary(jobData.getMaxSalary());
                return true;
            }
        }
        return false;
    }

}
