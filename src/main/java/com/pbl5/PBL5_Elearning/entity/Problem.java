package com.pbl5.PBL5_Elearning.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "problem")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "problem_id")
    private int id;

    @Column(name = "test_id")
    private int testId;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "homework_id")
    private Homework homework;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Problem_Type problemType;

    @OneToMany(mappedBy = "problem")
    private Set<Question> listQuestions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public Problem_Type getProblemType() {
        return problemType;
    }

    public void setProblemType(Problem_Type problemType) {
        this.problemType = problemType;
    }
}
