package by.bsu.salatmachine.model.pool;

import by.bsu.salatmachine.controller.manager.ConfigurationManager;
import by.bsu.salatmachine.exceptions.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 04.12.11
 * Time: 10:48
 */
public final class ConnectionManager {
    private static final String ERROR_MESSAGE = "Exception in preparing for connection" +
            " to database. Please come later.";
    private static final String DATABASE_DRIVER_NAME = "DATABASE_DRIVER_NAME";
    private static final String DATABASE_URL = "DATABASE_URL";
    private static final String DATABASE_USER = "DATABASE_USER";
    private static final String DATABASE_PASSWORD = "DATABASE_PASSWORD";
    private static final String DATABASE_CONNECTION = "DATABASE_CONNECTION";
    private String con;
    private static ConnectionManager instance;

    public static ConnectionManager getInstance() throws DatabaseConnectionException {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    private ConnectionManager() throws DatabaseConnectionException {
        try {
            //организация простейшего соединения с базой данных
            String driver = ConfigurationManager.getInstance()
                    .getProperty(DATABASE_DRIVER_NAME);
            String url = ConfigurationManager.getInstance()
                    .getProperty(DATABASE_URL);
            String user = ConfigurationManager.getInstance()
                    .getProperty(DATABASE_USER);
            String pass = ConfigurationManager.getInstance()
                    .getProperty(DATABASE_PASSWORD);
            con = ConfigurationManager.getInstance()
                    .getProperty(DATABASE_CONNECTION);
            new JDCConnectionDriver(driver, url, user, pass);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            throw new DatabaseConnectionException(e + ERROR_MESSAGE);
        }
    }

    public JDCConnection getConnection() throws DatabaseConnectionException {
        try {
            return (JDCConnection) DriverManager.getConnection(con);
        } catch (SQLException e) {
            throw (DatabaseConnectionException) e;
        }
    }

    public void closeConnection(Connection cn) throws DatabaseConnectionException {
        try {
            cn.close();
        } catch (SQLException e) {
            throw (DatabaseConnectionException) e;
        }
    }
}