package com.github_jr_jerry.JobSearch.repository;

import com.github_jr_jerry.JobSearch.model.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Jobs,Integer> {
}
