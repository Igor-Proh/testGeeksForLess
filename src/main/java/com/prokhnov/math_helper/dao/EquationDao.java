package com.prokhnov.math_helper.dao;

import com.prokhnov.math_helper.entity.Equation;

import java.util.List;

/**
 * The {@code EquationDao} interface.<br/>
 * Interface that have specific methods for {@code Equation}.
 *
 * @author Ihor Prokhnov
 * @version 1.0
 */
public interface EquationDao extends Dao<Equation> {

    /**
     * Create a given entity.
     *
     * @param entity - must not be null.
     */
    void create(Equation entity);

    /**
     * Retrieves Equations by root.
     *
     * @param root input {@link String} type parameter. Must not be null
     * @return Equation by the given root
     */
    List<Equation> findByRoot(String root);

    /**
     * Retrieves Equations by value.
     *
     * @param value input {@link String} type parameter. Must not be null
     * @return Equation by the given root
     */
    Equation findByValue(String value);

    /**
     * Retrieves Equations by id.
     *
     * @param id input {@link Integer} type parameter. Must not be null
     * @return Equation by the given id
     */
    Equation findById(int id);

}