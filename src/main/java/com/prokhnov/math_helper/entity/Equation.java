package com.prokhnov.math_helper.entity;

import java.util.List;
import java.util.Objects;

/**
 * The {@code Equation} class.<br/>
 * Equation entity class
 *
 * @author Ihor Prokhnov
 * @version 1.0
 */
public class Equation {
    private int equation_id;
    private String equation_value;
    private List<Root> equation_roots;

    public Equation() {
    }

    public Equation(String equation_value) {
        this.equation_value = equation_value;
    }

    public int getEquation_id() {
        return equation_id;
    }

    public void setEquation_id(int equation_id) {
        this.equation_id = equation_id;
    }

    public String getEquation_value() {
        return equation_value;
    }

    public void setEquation_value(String equation_value) {
        this.equation_value = equation_value;
    }

    public List<Root> getEquation_roots() {
        return equation_roots;
    }

    public void setEquation_roots(List<Root> equation_roots) {
        this.equation_roots = equation_roots;
    }

    @Override
    public String toString() {
        return "Equation{" +
                "equation_id=" + equation_id +
                ", equation_value='" + equation_value + '\'' +
                ", equation_roots=" + equation_roots +
                '}';
    }
}
