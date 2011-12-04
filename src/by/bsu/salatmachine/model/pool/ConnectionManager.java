package by.bsu.salatmachine.model.pool;

import by.bsu.salatmachine.controller.manager.ConfigurationManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 04.12.11
 * Time: 10:48
 *
 */
public class ConnectionManager {
    public static Connection getConnection(){
          try {
            //организация простейшего соединения с базой данных
            String driver = ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.DATABASE_DRIVER_NAME);
            String url = ConfigurationManager.getInstance()
                        .getProperty(ConfigurationManager.DATABASE_URL);
            String user = ConfigurationManager.getInstance()
                        .getProperty(ConfigurationManager.DATABASE_USER);
            String pass = ConfigurationManager.getInstance()
                        .getProperty(ConfigurationManager.DATABASE_PASSWORD);
            String con = ConfigurationManager.getInstance()
                        .getProperty(ConfigurationManager.DATABASE_CONNECTION);
            new JDCConnectionDriver(driver,url,user,pass);
            return DriverManager.getConnection(con);
    } catch (ClassNotFoundException e) {
              e.printStackTrace();  //todo To change body of catch statement use File | Settings | File Templates.
          } catch (SQLException e) {
              e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
          } catch (InstantiationException e) {
              e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
          } catch (IllegalAccessException e) {
              e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
          }

        return null;   //todo may be change
    }
}