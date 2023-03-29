package com.prokhnov.math_helper.connection;

import com.prokhnov.math_helper.Main;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The {@code DbConnection} class.<br/>
 * Class that make connection to DB.
 *
 * @author Ihor Prokhnov
 * @version 1.0
 */
public class DbConnection {

    /**
     * Initialize Logger (log4j)
     */
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private static final String PROPERTIES = "connection.properties";
    private static final String DB_URL = "database.url";
    private static final String DB_USERNAME = "database.user";
    private static final String DB_PASSWORD = "database.password";
    private static final String DB_DRIVER = "database.driver";

    /**
     * @return connection to DB
     * @throws SQLException - failed to load DB
     */
    public static Connection getConnection() throws SQLException {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES)) {

            Properties properties = new Properties();
            properties.load(inputStream);

            Class.forName(properties.getProperty(DB_DRIVER));

            return DriverManager.getConnection(
                    properties.getProperty(DB_URL),
                    properties.getProperty(DB_USERNAME),
                    properties.getProperty(DB_PASSWORD));

        } catch (IOException e) {
            LOGGER.error("File does not exist");
            throw new UncheckedIOException("File does not exist", e);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Driver not found");
            throw new IllegalArgumentException("Driver not found", e);
        } catch (SQLException e) {
            LOGGER.error("Failed to load DB");
            throw new SQLException("Failed to load DB");
        }
    }

}
