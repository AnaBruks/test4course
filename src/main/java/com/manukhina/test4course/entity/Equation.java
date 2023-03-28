package com.manukhina.test4course.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Equation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String equation;

    private boolean correct;

    @OneToMany(mappedBy = "equation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Root> roots = new ArrayList<>();

    public Equation() {
    }

    public Equation(String equation) {
        this.equation = equation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public boolean getCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public List<Root> getRoots() {
        return roots;
    }

    public void addRoot(Root root) {
        roots.add(root);
        root.setEquation(this);
    }

    public void removeRoot(Root root) {
        roots.remove(root);
        root.setEquation(null);
    }
}
