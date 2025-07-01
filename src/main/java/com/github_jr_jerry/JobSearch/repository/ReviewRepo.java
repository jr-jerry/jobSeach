package com.github_jr_jerry.JobSearch.repository;

import com.github_jr_jerry.JobSearch.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review,Long> {
    List<Review> findByCompanyId(int id);
}
