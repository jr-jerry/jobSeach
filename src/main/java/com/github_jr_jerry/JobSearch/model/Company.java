package com.github_jr_jerry.JobSearch.model;

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
    @OneToMany
    private List<Jobs> jobsList;
}
