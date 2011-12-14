package by.bsu.salatmachine.model.pool;

import by.bsu.salatmachine.controller.manager.ConfigurationManager;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 04.12.11
 * Time: 10:48
 */
public class ConnectionManager {
    private String con;
    private static ConnectionManager instance;

    public static ConnectionManager getInstance() {
        if (instance == null){
            instance = new ConnectionManager();
        }
        return instance;
    }

    private ConnectionManager() {
        try {
            //организация простейшего соединения с базой данных
            String driver = ConfigurationManager.getInstance()
                    .getProperty("DATABASE_DRIVER_NAME");
            String url = ConfigurationManager.getInstance()
                    .getProperty("DATABASE_URL");
            String user = ConfigurationManager.getInstance()
                    .getProperty("DATABASE_USER");
            String pass = ConfigurationManager.getInstance()
                    .getProperty("DATABASE_PASSWORD");
            con = ConfigurationManager.getInstance()
                    .getProperty("DATABASE_CONNECTION");
            new JDCConnectionDriver(driver, url, user, pass);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();  //Todo  change body of catch statement use File | Settings | File Templates.
        } catch (SQLException e1) {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e1) {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public JDCConnection getConnection() throws SQLException {
            return (JDCConnection) DriverManager.getConnection(con);
    }
}