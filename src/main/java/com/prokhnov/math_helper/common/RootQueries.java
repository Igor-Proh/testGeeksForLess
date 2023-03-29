package com.prokhnov.math_helper.common;

/**
 * The {@code RootQueries} class.<br/>
 * Class that provide queries for Root class.
 *
 * @author Ihor Prokhnov
 * @version 1.0
 */
public class RootQueries {

    /**
     * SQL queries for root
     */
    public final static String CREATE = "INSERT INTO root(root_value, equation_id) VALUES (?,?)";
    public final static String FIND_ALL_BY_EQUATION_ID = "SELECT * FROM root WHERE equation_id=?";
    public final static String FIND_ALL_BY_VALUE = "SELECT * FROM root WHERE root_value=?";
}
