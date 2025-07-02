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

    @Autowired
    CompanyServices companyServices;

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

    public Review findReviewById(int companyId,Long reviewId){
        List<Review> exist=reviewRepo.findByCompanyId(companyId);
        if(!exist.isEmpty()){
            return exist.stream()
                    .filter(item->item.getId().equals(reviewId)).findFirst()
                    .orElse(null);
        }else{
            throw new RuntimeException(" No Review find with this Id ");
        }
    }

    public Review updateReviewById(int companyId,Long reviewId ,Review updatedReview){
        List<Review> exist=reviewRepo.findByCompanyId(companyId);
        Review review=exist.stream().filter(item->item.getId().equals(reviewId)).findFirst().orElse(null);
        if(review!=null){
            review.setDescription(updatedReview.getDescription());
            return reviewRepo.save(review);

        }else{
            throw new RuntimeException("no review exist with this id ");
        }

    }

    public Boolean delReviewById(int companyId, Long reviewId) {
        Optional<Company> companyBox = companyRepo.findById(companyId);
        Optional<Review> reviewBox = reviewRepo.findById(reviewId);

        if (companyBox.isPresent() && reviewBox.isPresent()) {
            Company company = companyBox.get();
            Review review = reviewBox.get();

            // Check if the review belongs to this company
            boolean matched = company.getReviewList().removeIf(r -> r.getId().equals(reviewId));

            if (matched) {
                reviewRepo.delete(review); // delete from DB
                companyServices.updateById(companyId, company); // update the company review list
                return true;
            }
        }
        return false;
    }


}
