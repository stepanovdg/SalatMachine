package by.bsu.salatmachine.model.logic;

import by.bsu.salatmachine.model.entity.UserDAO;
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
 * UserDAO: Stepanov Dmitriy
 * Date: 28.11.11
 * Time: 23:15
 */
public class LoginLogic {
    public static UserDAO getLogin(
            String login, String password) throws  RemoteException {
        UserDAO userDAO = null;
        // проверка логина и пароля
        try {
            Connection cn = ConnectionManager.getConnection();
            try (PreparedStatement st = cn.prepareStatement(
                    "SELECT * FROM vegetdb.user WHERE LOGIN = ? AND PASSWORD = ?")) {
                st.setString(1, login);
                st.setString(2, password);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        userDAO = new UserDAO();
                        userDAO.setLogin(login);
                        userDAO.setPassword(password);
                        userDAO.setMoney(rs.getInt(3));
                        userDAO.setType(rs.getBoolean(4));
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
        return userDAO;

    }

    public static boolean refresh(UserDAO userDAO) throws RemoteException, FinderException {
        if (userDAO == null) {
            throw new RemoteException("userDAO cannot be null");
        }
        try (Connection cn = ConnectionManager.getConnection()) {

            try (PreparedStatement st = cn.prepareStatement(
                    "select password,moneyAmount,accountType," +
                            " from vegetdb.user where login = ?")) {
                st.setString(1, userDAO.getLogin());

                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        userDAO.setPassword(rs.getString(1));
                        userDAO.setMoney(rs.getInt(2));
                        userDAO.setType(rs.getBoolean(2));
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

    public static boolean store(UserDAO userDAO) throws RemoteException {
        if (userDAO == null) {
            throw new RemoteException("userDAO cannot be null");
        }
        try (Connection cn = ConnectionManager.getConnection()) {

            try (PreparedStatement st = cn.prepareStatement("update VEGETDB.USER set PASSWORD = ?,MONEYACOUNT = ?, " +
                    "ACOUNTTYPE = ? where LOGIN = ?")) {
                st.setString(1, userDAO.getPassword());
                st.setInt(2, userDAO.getMoney());
                st.setBoolean(3, userDAO.isType());
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

    public static boolean create(UserDAO userDAO) throws RemoteException, CreateException {
        if (userDAO == null) {
            throw new RemoteException("userDAO cannot be null");
        }
        try (Connection cn = ConnectionManager.getConnection()) {

            try (PreparedStatement st = cn.prepareStatement("insert into VEGETDB.USER (LOGIN,PASSWORD,MONEYAMOUNT," +
                    "ACOUNTTYPE)values (?, ?, ?, ?)")) {
                st.setString(1, userDAO.getLogin());
                st.setString(2, userDAO.getPassword());
                st.setInt(3, userDAO.getMoney());
                st.setBoolean(4, userDAO.isType());
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
