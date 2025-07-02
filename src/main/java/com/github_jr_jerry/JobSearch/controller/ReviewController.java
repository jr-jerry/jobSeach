package com.github_jr_jerry.JobSearch.controller;
import com.github_jr_jerry.JobSearch.model.Jobs;
import com.github_jr_jerry.JobSearch.model.Review;
import com.github_jr_jerry.JobSearch.services.Job_Services;
import com.github_jr_jerry.JobSearch.services.ReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/company")
@RestController
public class ReviewController {
    @Autowired
    ReviewServices reviewService;

    @GetMapping("/{id}/review")
    public ResponseEntity<?> getReviewEndpoint(@PathVariable int id){
        try{
            return new ResponseEntity<>(reviewService.findAllReview(id),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/review")
    public ResponseEntity<?> createReviewEndpoint(@PathVariable int id,@RequestBody Review reviewData){
        try{
            return new ResponseEntity<>(reviewService.createReview(id,reviewData),HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{companyId}/review/{reviewId}")
    public ResponseEntity<?> getReviewByIdEndpoint(@PathVariable int companyId, @PathVariable Long reviewId ){
        Review review=reviewService.findReviewById(companyId,reviewId);
       try{
           if(review ==null){
               return new ResponseEntity<>(" No Review Exist ",HttpStatus.NOT_FOUND);
           }
           return new ResponseEntity<>(review,HttpStatus.OK);
       } catch (RuntimeException e) {
           return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
       }
    }

    @PutMapping("/{companyId}/review/{reviewId}")
    public ResponseEntity<?> updateReviewByIdEndpoint(@PathVariable int companyId, @PathVariable Long reviewId ,@RequestBody Review updatedReview){
        try {
            return new ResponseEntity<>(reviewService.updateReviewById(companyId, reviewId, updatedReview), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


}
