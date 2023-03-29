package com.prokhnov.math_helper.entity;

import java.util.Objects;

/**
 * The {@code Root} class.<br/>
 * Root entity class
 *
 * @author Ihor Prokhnov
 * @version 1.0
 */
public class Root {
    int root_id;
    String root_value;
    int equation_id;

    public Root() {

    }

    public Root(String root_value, int equation_id) {
        this.root_value = root_value;
        this.equation_id = equation_id;
    }

    public int getRoot_id() {
        return root_id;
    }

    public int getEquation_id() {
        return equation_id;
    }

    public void setEquation_id(int equation_id) {
        this.equation_id = equation_id;
    }

    public void setRoot_id(int root_id) {
        this.root_id = root_id;
    }

    public String getRoot_value() {
        return root_value;
    }

    public void setRoot_value(String root_value) {
        this.root_value = root_value;
    }

    @Override
    public String toString() {
        return "Root{" +
                "root_id=" + root_id +
                ", root_value='" + root_value + '\'' +
                '}';
    }
}
