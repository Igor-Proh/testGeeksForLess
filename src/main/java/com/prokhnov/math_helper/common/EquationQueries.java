package com.prokhnov.math_helper.common;

/**
 * The {@code EquationQueries} class.<br/>
 * Class that provide queries for Equation class.
 *
 * @author Ihor Prokhnov
 * @version 1.0
 */
public class EquationQueries {

    /**
     * SQL queries for equations
     */
    public final static String CREATE = "INSERT INTO equation(equation_value) VALUES (?)";
    public final static String FIND_BY_VALUE = "SELECT * FROM equation WHERE equation_value=?";
    public final static String FIND_BY_ID = "SELECT * FROM equation WHERE equation_id=?";

}
