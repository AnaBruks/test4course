package com.manukhina.test4course.Dto;

import com.manukhina.test4course.entity.Root;

import java.util.ArrayList;

public class EquationDto {
    private Long id;
    private String equation;
    private ArrayList<Root> roots;

    public EquationDto() {
    }

    public EquationDto(Long id, String equation,  ArrayList<Root> roots) {
        this.id = id;
        this.equation = equation;
        this.roots = roots;
    }

    public <E> EquationDto(Long id, String equation, boolean correct, ArrayList<E> es) {
        this.id = id;
        this.equation = equation;
        this.roots = (ArrayList<Root>) es;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equationString) {
        this.equation = equationString;
    }

    public ArrayList<Root> getRoots() {
        return roots;
    }

    public void setRoots(ArrayList<Root> roots) {
        this.roots = roots;
    }

    public void addRoot(Root root) {
        if (roots == null) {
            roots = new ArrayList<Root>();
        }
        roots.add(root);
    }

    public void removeRoot(Double root) {
        if (roots != null) {
            roots.remove(root);
        }
    }
}
