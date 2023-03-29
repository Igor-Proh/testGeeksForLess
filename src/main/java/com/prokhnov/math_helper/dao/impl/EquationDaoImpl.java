package com.prokhnov.math_helper.dao.impl;

import com.prokhnov.math_helper.common.EquationQueries;
import com.prokhnov.math_helper.connection.DbConnection;
import com.prokhnov.math_helper.dao.EquationDao;
import com.prokhnov.math_helper.entity.Equation;
import com.prokhnov.math_helper.entity.Root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code EquationDaoImpl} class.<br/>
 *
 * @author Ihor Prokhnov
 * @version 1.0
 */
public class EquationDaoImpl implements EquationDao {

    @Override
    public void create(Equation equation) {

        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(EquationQueries.CREATE);

            connection.setAutoCommit(false);

            ps.setString(1, equation.getEquation_value());

            try {
                ps.executeUpdate();
                connection.commit();
            } catch (SQLException exception) {
                connection.rollback();
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }


    @Override
    public List<Equation> findByRoot(String root) {
        List<Equation> equations = new ArrayList<>();

        RootDaoImpl rootDao = new RootDaoImpl();
        EquationDaoImpl equationDao = new EquationDaoImpl();
        List<Root> allByRootValue = rootDao.findAllByRootValue(root);
        for (Root r : allByRootValue) {
            Equation equation = equationDao.findById(r.getEquation_id());
            equation.setEquation_roots(rootDao.findAllByEquationId(r.getEquation_id()));
            equations.add(equation);
        }

        return equations;
    }

    @Override
    public Equation findByValue(String value) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(EquationQueries.FIND_BY_VALUE)) {
            ps.setString(1, value);

            Equation equation = extractResult(ps);
            if (equation != null) return equation;


        } catch (SQLException e) {
            System.err.println(e);
        }

        return new Equation();
    }

    @Override
    public Equation findById(int id) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(EquationQueries.FIND_BY_ID)) {
            ps.setInt(1, id);

            Equation equation = extractResult(ps);
            if (equation != null) return equation;


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return new Equation();
    }

    private Equation extractResult(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int equation_id = rs.getInt("equation_id");
            String equation_value = rs.getString("equation_value");
            Equation equation = new Equation();
            equation.setEquation_id(equation_id);
            equation.setEquation_value(equation_value);
            return equation;
        }
        return null;
    }

}
