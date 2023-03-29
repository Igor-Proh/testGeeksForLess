package com.prokhnov.math_helper.dao;

import com.prokhnov.math_helper.entity.Root;

import java.util.List;

/**
 * The {@code RootDao} interface.<br/>
 * Interface that have specific methods for {@code Root}.
 *
 * @author Ihor Prokhnov
 * @version 1.0
 */
public interface RootDao extends Dao<Root> {

    /**
     * Create a given entity.
     *
     * @param entity - must not be null.
     */
    @Override
    void create(Root entity);

    /**
     * Retrieves all roots by equation id.
     *
     * @param id input {@link Integer} type parameter. Must not be null
     * @return all roots by the given equation ud
     */
    List<Root> findAllByEquationId(int id);

    /**
     * Retrieves all roots by root value.
     *
     * @param value input {@link String} type parameter. Must not be null
     * @return all roots by the given root value
     */
    List<Root> findAllByRootValue(String value);
}
