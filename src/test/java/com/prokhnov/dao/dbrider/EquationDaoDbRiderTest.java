package com.prokhnov.dao.dbrider;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.prokhnov.math_helper.dao.impl.EquationDaoImpl;
import com.prokhnov.math_helper.dao.impl.RootDaoImpl;
import com.prokhnov.math_helper.entity.Equation;
import com.prokhnov.math_helper.entity.Root;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class EquationDaoDbRiderTest extends DbRiderConfig {

    @Test
    @DataSet(executeScriptsBefore = "dml.sql", executeScriptsAfter = "ddl.sql")
    @ExpectedDataSet(value = "dbrider/create_equation.yml")
    public void testShouldCheckForCreatingNewEquationInDataBase() {
        EquationDaoImpl equationDao = new EquationDaoImpl();
        equationDao.create(new Equation("1+x=10"));
    }

    @Test
    @DataSet(executeScriptsBefore = "dml.sql", executeScriptsAfter = "ddl.sql")
    public void testShouldCheckRootDaoMethodThatFindEquationByRoot() {

        String expectedEquationValue = "1+x=1";
        int expectedEquationId = 1;

        EquationDaoImpl equationDao = new EquationDaoImpl();
        Equation equation = equationDao.findByRoot("0").get(0);
        Assertions.assertEquals(expectedEquationId, equation.getEquation_id());
        Assertions.assertEquals(expectedEquationValue, equation.getEquation_value());

    }

    @Test
    @DataSet(executeScriptsBefore = "dml.sql", executeScriptsAfter = "ddl.sql")
    public void testShouldCheckRootDaoMethodThatFindEquationByValue() {
        EquationDaoImpl equationDao = new EquationDaoImpl();
        Equation actualEquationById = equationDao.findByValue("1+x=1");

        int expectedId = 1;
        String expectedValue = "1+x=1";

        Assertions.assertEquals(expectedId, actualEquationById.getEquation_id());
        Assertions.assertEquals(expectedValue, actualEquationById.getEquation_value());
    }

    @Test
    @DataSet(executeScriptsBefore = "dml.sql", executeScriptsAfter = "ddl.sql")
    public void testShouldCheckRootDaoMethodThatFindEquationById() {
        EquationDaoImpl equationDao = new EquationDaoImpl();
        Equation actualEquationById = equationDao.findById(1);

        int expectedId = 1;
        String expectedValue = "1+x=1";

        Assertions.assertEquals(expectedId, actualEquationById.getEquation_id());
        Assertions.assertEquals(expectedValue, actualEquationById.getEquation_value());
    }

    @Test
    @DataSet(executeScriptsBefore = "dml.sql")
    @ExpectedDataSet(value = "dbrider/create_root.yml")
    public void testShouldCheckForCreatingNewRootInDataBase() {
        RootDaoImpl rootDao = new RootDaoImpl();
        rootDao.create(new Root("2",2));
    }

    @Test
    @DataSet(executeScriptsBefore = "dml.sql", executeScriptsAfter = "ddl.sql")
    public void testShouldCheckRootDaoMethodThatFindRootByEquation() {
        int expectedRootId = 1;
        String expectedRootValue = "0";

        RootDaoImpl rootDao = new RootDaoImpl();
        Root root = rootDao.findAllByEquationId(1).get(0);
        Assertions.assertEquals(expectedRootId, root.getRoot_id());
        Assertions.assertEquals(expectedRootValue, root.getRoot_value());
    }

    @Test
    @DataSet(executeScriptsBefore = "dml.sql", executeScriptsAfter = "ddl.sql")
    public void testShouldCheckRootDaoMethodThatFindRootByValue() {
        int expectedRootId = 1;
        String expectedRootValue = "0";

        RootDaoImpl rootDao = new RootDaoImpl();
        Root root = rootDao.findAllByRootValue("0").get(0);
        Assertions.assertEquals(expectedRootId, root.getRoot_id());
        Assertions.assertEquals(expectedRootValue, root.getRoot_value());
    }
}
