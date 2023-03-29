package com.prokhnov.math_helper.dao.impl;

import com.prokhnov.math_helper.common.RootQueries;
import com.prokhnov.math_helper.connection.DbConnection;
import com.prokhnov.math_helper.dao.RootDao;
import com.prokhnov.math_helper.entity.Root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code RootDaoImpl} class.<br/>
 *
 * @author Ihor Prokhnov
 * @version 1.0
 */
public class RootDaoImpl implements RootDao {

    @Override
    public void create(Root root) {

        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(RootQueries.CREATE);

            connection.setAutoCommit(false);

            ps.setString(1, root.getRoot_value());
            ps.setInt(2, root.getEquation_id());

            try {
                ps.executeUpdate();
                connection.commit();
            } catch (SQLException exception) {
                connection.rollback();
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Root> findAllByEquationId(int id) {
        List<Root> roots = new ArrayList<>();

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(RootQueries.FIND_ALL_BY_EQUATION_ID)) {

            ps.setInt(1, id);
            extractResult(roots, ps);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return roots;
    }

    @Override
    public List<Root> findAllByRootValue(String value) {
        List<Root> roots = new ArrayList<>();

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(RootQueries.FIND_ALL_BY_VALUE)) {

            ps.setString(1, value);
            extractResult(roots, ps);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return roots;
    }

    private void extractResult(List<Root> roots, PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int root_id = rs.getInt("root_id");
            String root_value = rs.getString("root_value");
            int equation_id = rs.getInt("equation_id");
            Root root = new Root();
            root.setRoot_id(root_id);
            root.setRoot_value(root_value);
            root.setEquation_id(equation_id);
            roots.add(root);
        }
    }

}
