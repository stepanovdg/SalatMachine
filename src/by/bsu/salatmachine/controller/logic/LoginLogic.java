package by.bsu.salatmachine.controller.logic;

import by.bsu.salatmachine.controller.manager.ConfigurationManager;

import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 28.11.11
 * Time: 23:15
 */
public class LoginLogic {
    public static boolean checkLogin(
            String login, String password) {
            // проверка логина и пароля
        try {
            //организация простейшего соединения с базой данных
            String driver = ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.DATABASE_DRIVER_NAME);

            Class.forName(driver);
            Connection cn = null;
            try {
                String url = ConfigurationManager.getInstance()
                        .getProperty(ConfigurationManager.DATABASE_URL);
                cn = DriverManager.getConnection(url);
                try (PreparedStatement st = cn.prepareStatement(
                        "SELECT * FROM vegetdb.user WHERE LOGIN = ? AND PASSWORD = ?")) {
                    st.setString(1, login);
                    st.setString(2, password);
                    try (ResultSet rs = st.executeQuery()) {
                        /* проверка, существует ли пользователь
                        с указанным логином и паролем */
                        return rs.next();
                    }


                }

            } finally {

                if (cn != null)
                    cn.close();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
