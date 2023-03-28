package com.manukhina.test4course.entity;

import jakarta.persistence.*;

@Entity
public class Root {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Root value;

    @ManyToOne
    @JoinColumn(name = "equation_id")
    private Equation equation;

    public Root() {
    }

    public Root(Root value, Equation equation) {
        this.value = value;
        this.equation = equation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Root getValue() {
        return value;
    }

    public void setValue(Root value) {
        this.value = value;
    }

    public Equation getEquation() {
        return equation;
    }

    public void setEquation(Equation equation) {
        this.equation = equation;
    }
}

