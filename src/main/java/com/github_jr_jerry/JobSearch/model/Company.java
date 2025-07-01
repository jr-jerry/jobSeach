package com.github_jr_jerry.JobSearch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "company_db")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
    @OneToMany(mappedBy = "company")
    @JsonIgnore//this annotation ignore call of job value when company object call in json by job object
    private List<Jobs> jobsList;
    @OneToMany(mappedBy ="company")
    @JsonIgnore
    private List<Review> reviewList;
}
