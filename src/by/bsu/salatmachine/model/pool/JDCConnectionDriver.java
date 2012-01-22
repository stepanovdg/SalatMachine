package by.bsu.salatmachine.model.pool;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 01.12.11
 * Time: 12:44
 *
 */
import by.bsu.salatmachine.controller.manager.ConfigurationManager;
import by.bsu.salatmachine.exceptions.DatabaseConnectionException;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;


public class JDCConnectionDriver implements Driver {

    public static final String URL_PREFIX = ConfigurationManager.getInstance().getProperty("DATABASE_URL_PREFIX");
    private static final int MAJOR_VERSION = 1;
    private static final int MINOR_VERSION = 0;
    private JDCConnectionPool pool;

    public JDCConnectionDriver(String driver, String url,
                                 String user, String password)
                            throws ClassNotFoundException,
                               InstantiationException, IllegalAccessException,
                                SQLException
    {
        DriverManager.registerDriver(this);
        pool = new JDCConnectionPool(url, user, password);
    }

    public Connection connect(String url, Properties props)
                                       throws DatabaseConnectionException {
        if(!url.startsWith(URL_PREFIX)) {
             return null;
        }
        return pool.getConnection();
    }

    public boolean acceptsURL(String url) {
        return url.startsWith(URL_PREFIX);
    }

    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    public int getMinorVersion() {
        return MINOR_VERSION;
    }

    public DriverPropertyInfo[] getPropertyInfo(String str, Properties props) {
        return new DriverPropertyInfo[0];
    }

    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

}
