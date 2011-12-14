package by.bsu.salatmachine.model.logic;

import by.bsu.salatmachine.controller.manager.ConfigurationManager;
import by.bsu.salatmachine.model.entity.User;
import by.bsu.salatmachine.model.pool.ConnectionManager;

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
public class LoginDAO extends AbstractDAO<User> {
    @Override
    public User getEntity(Object... objects) throws RemoteException {
        String login = (String) objects[0];
        String password = (String) objects[1];
        User user = null;
        // проверка логина и пароля
        try {
            Connection cn = ConnectionManager.getInstance().getConnection();
            try (PreparedStatement st = cn.prepareStatement(ConfigurationManager.
                    getInstance().getProperty("USER_GETENTITY"))) {
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

    @Override
    public boolean refresh(User user) throws RemoteException {
        if (user == null) {
            throw new RemoteException("user cannot be null");
        }
        try (Connection cn = ConnectionManager.getInstance().getConnection()) {

            try (PreparedStatement st = cn.prepareStatement(ConfigurationManager.
                    getInstance().getProperty("USER_REFRESH"))) {
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

    @Override
    public boolean store(User user) throws RemoteException {
        if (user == null) {
            throw new RemoteException("user cannot be null");
        }
        try (Connection cn = ConnectionManager.getInstance().getConnection()) {

            try (PreparedStatement st = cn.prepareStatement(ConfigurationManager.
                    getInstance().getProperty("USER_STORE"))) {
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


    @Override
    public boolean create(User user) throws RemoteException {
        if (user == null) {
            throw new RemoteException("user cannot be null");
        }
        try (Connection cn = ConnectionManager.getInstance().getConnection()) {

            try (PreparedStatement st = cn.prepareStatement(ConfigurationManager.
                    getInstance().getProperty("USER_CREATE"))) {
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
