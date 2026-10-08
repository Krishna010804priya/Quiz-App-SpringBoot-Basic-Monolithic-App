package com.priya.Quiz_App_SpringBoot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @ManyToMany //a quiz with multiple questions so we are doing many-to-many mapping
    // to quiz and questions tables(which forms another table called "quiz_questions"... )...
    private List<Question> questions;
}
