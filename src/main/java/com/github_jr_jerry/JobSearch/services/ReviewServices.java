package com.github_jr_jerry.JobSearch.services;

import com.github_jr_jerry.JobSearch.model.Company;
import com.github_jr_jerry.JobSearch.model.Jobs;
import com.github_jr_jerry.JobSearch.model.Review;
import com.github_jr_jerry.JobSearch.repository.CompanyRespository;
import com.github_jr_jerry.JobSearch.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReviewServices {
    @Autowired
    ReviewRepo reviewRepo;
    @Autowired
    CompanyRespository companyRepo;

    public List<Review> findAllReview(int id){
       return reviewRepo.findByCompanyId(id);

    }

    public Review createReview(int id,Review review){
        Optional<Company> box=companyRepo.findById(id);
        if(box.isPresent()){
            try{
                review.setCompany(box.get());
                return reviewRepo.save(review);

            }
            catch (Exception e){
                throw new RuntimeException("error in saving review");
            }
        }else{
            throw new RuntimeException(" No Company with this id");
        }
    }

}
