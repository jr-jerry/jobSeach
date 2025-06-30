package com.github_jr_jerry.JobSearch.services;

import com.github_jr_jerry.JobSearch.model.Jobs;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class Job_Services implements JobServices{
    List<Jobs> jobList=new ArrayList<>();
    int nextId=1;

    @Override
    public List<Jobs> getAll() {
        return jobList;
    }

    @Override
    public void postJobs(Jobs job) {
        job.setId(nextId++);
        jobList.add(job);
    }
    @Override
    public Jobs getById(int id){
        return jobList.get(id-1);
    }

    @Override
    public void delJobs(int id) {
//        for(Jobs item:jobList) {
//            if (item.getId() == id) jobList.remove(item);
//        }currentModification Exception
        Iterator<Jobs> iterator=jobList.iterator();
        while(iterator.hasNext()){
            Jobs job=iterator.next();
            if (job.getId()==id){
                iterator.remove();
            }
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
