package by.bsu.salatmachine.model.logic;

import by.bsu.salatmachine.model.entity.User;
import by.bsu.salatmachine.model.pool.ConnectionManager;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 28.11.11
 * Time: 23:15
 */
public class LoginLogic {
    public static User getLogin(
            String login, String password) throws  RemoteException {
        User user = null;
        // проверка логина и пароля
        try {
            Connection cn = ConnectionManager.getConnection();
            try (PreparedStatement st = cn.prepareStatement(
                    "SELECT * FROM vegetdb.user WHERE LOGIN = ? AND PASSWORD = ?")) {
                st.setString(1, login);
                st.setString(2, password);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        user = new User();
                        user.setLogin(login);
                        user.setPassword(password);
                        user.setMoney(rs.getInt(3));
                        user.setType(rs.getBoolean(4));
                    } else {
                        throw new RemoteException("Refresh: Registration ("
                                + login + ") not found");
                    }
                }


            } finally {

                try {
                    cn.close();
                } catch (Exception ignore) {
                }
            }
        } catch (SQLException e) {
            throw new RemoteException(e.getMessage());
        }
        return user;

    }

    public static boolean refresh(User user) throws RemoteException, FinderException {
        if (user == null) {
            throw new RemoteException("user cannot be null");
        }
        try (Connection cn = ConnectionManager.getConnection()) {

            try (PreparedStatement st = cn.prepareStatement(
                    "select password,moneyAmount,accountType," +
                            " from vegetdb.user where login = ?")) {
                st.setString(1, user.getLogin());

                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        user.setPassword(rs.getString(1));
                        user.setMoney(rs.getInt(2));
                        user.setType(rs.getBoolean(2));
                        return true;
                    } else {
                        return false;
                    }
                }


            } finally {

                try {
                    cn.close();
                } catch (Exception ignore) {
                }
            }
        } catch (SQLException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public static boolean store(User user) throws RemoteException {
        if (user == null) {
            throw new RemoteException("user cannot be null");
        }
        try (Connection cn = ConnectionManager.getConnection()) {

            try (PreparedStatement st = cn.prepareStatement("update VEGETDB.USER set PASSWORD = ?,MONEYACOUNT = ?, " +
                    "ACOUNTTYPE = ? where LOGIN = ?")) {
                st.setString(1, user.getPassword());
                st.setInt(2, user.getMoney());
                st.setBoolean(3, user.isType());
                return st.executeUpdate() == 1;

            } finally {
                try {
                    cn.close();
                } catch (Exception ignore) {
                }
            }
        } catch (SQLException re) {
            throw new RemoteException(re.getMessage());
        }
    }

    public static boolean create(User user) throws RemoteException, CreateException {
        if (user == null) {
            throw new RemoteException("user cannot be null");
        }
        try (Connection cn = ConnectionManager.getConnection()) {

            try (PreparedStatement st = cn.prepareStatement("insert into VEGETDB.USER (LOGIN,PASSWORD,MONEYAMOUNT," +
                    "ACOUNTTYPE)values (?, ?, ?, ?)")) {
                st.setString(1, user.getLogin());
                st.setString(2, user.getPassword());
                st.setInt(3, user.getMoney());
                st.setBoolean(4, user.isType());
                return st.executeUpdate() == 1;

            } finally {
                try {
                    cn.close();
                } catch (Exception ignore) {
                }
            }
        } catch (SQLException re) {
            throw new RemoteException(re.getMessage());
        }

    }

}
