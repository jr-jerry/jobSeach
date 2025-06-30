package com.github_jr_jerry.JobSearch.services;

import com.github_jr_jerry.JobSearch.model.Jobs;

import java.util.List;

public interface JobServices {
    List<Jobs> getAll();
    void postJobs(Jobs job);
    Jobs getById(int id);
    void delJobs(int id);


    boolean upDateById_S(int id,Jobs jobData);
}
