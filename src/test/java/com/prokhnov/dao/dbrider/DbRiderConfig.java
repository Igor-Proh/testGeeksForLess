package com.prokhnov.dao.dbrider;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.junit5.api.DBRider;

import com.prokhnov.math_helper.connection.DbConnection;
import org.apache.log4j.Logger;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;

@DBUnit(url = "jdbc:h2:mem:default;DB_CLOSE_DELAY=-1")
@DBRider
public class DbRiderConfig {

    private static final Logger LOGGER = Logger.getLogger(DbRiderConfig.class.getName());

    @BeforeAll
    static void initializeTables() {
        try {
            RunScript.execute(DbConnection.getConnection(), new FileReader("src/test/resources/ddl.sql"));
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
            throw new IllegalArgumentException();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File cannot found", e);
        }
    }
}
