package com.prokhnov.math_helper.dao;

/**
 * The {@code Dao} interface.<br/>
 *
 * @author Ihor Prokhnov
 * @version 1.0
 */
public interface Dao<E> {

    /**
     * Create a given entity.
     *
     * @param entity - must not be null.
     */
    void create(E entity);

}