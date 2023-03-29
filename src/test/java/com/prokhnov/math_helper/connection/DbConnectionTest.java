package com.prokhnov.math_helper.connection;

import com.prokhnov.math_helper.Main;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class DbConnectionTest {

    private static final Logger LOGGER = Logger.getLogger(DbConnectionTest.class.getName());

    @Test
    public void testShouldGetConnectionAndCheckThatItNotNull() {
        try {
            Assert.assertNotNull(DbConnection.getConnection());
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
    }
}