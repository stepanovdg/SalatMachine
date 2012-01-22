package by.bsu.salatmachine.model.logic;

import by.bsu.salatmachine.exceptions.DatabaseConnectionException;
import by.bsu.salatmachine.model.entity.EntityIF;
import by.bsu.salatmachine.model.entity.NullEntity;
import by.bsu.salatmachine.model.entity.User;
import by.bsu.salatmachine.model.pool.ConnectionManager;

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
    private final static String USER_GETENTITY = "SELECT * FROM Vegetdb.user WHERE Login = ? AND Password = ? AND inUse = 1";
    private final static String USER_REFRESH = "SELECT Password,MoneyAmount,AccountType FROM Vegetdb.user WHERE Login = ?";
    private final static String USER_STORE = "update VEGETDB.USER set PASSWORD = ?,MONEYAMOUNT = ?,ACCOUNTTYPE = ? where LOGIN = ?";
    private final static String USER_CREATE = "insert into VEGETDB.USER (LOGIN,PASSWORD,MONEYAMOUNT,ACCOUNTTYPE)values (?, ?, ?, ?)";

    @Override
    public EntityIF getEntity(Object... objects) throws DatabaseConnectionException {
        String login = (String) objects[0];
        String password = (String) objects[1];
        EntityIF retn = new NullEntity();

        // проверка логина и пароля
        try (Connection cn = ConnectionManager.getInstance().getConnection()) {
            try (PreparedStatement st = cn.prepareStatement(USER_GETENTITY)) {
                st.setString(1, login);
                st.setString(2, password);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        User user = new User();
                        user.setLogin(login);
                        user.setPassword(password);
                        user.setMoney(rs.getInt(3));
                        user.setType(rs.getBoolean(4));
                        retn = user;
                    } else {
                        return retn;
                    }
                }
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }
        return retn;

    }

    @Override
    public boolean refresh(User user) throws DatabaseConnectionException {
        if (user == null) {
            return false;
        }
        try (Connection cn = ConnectionManager.getInstance().getConnection()) {
            try (PreparedStatement st = cn.prepareStatement(USER_REFRESH)) {
                st.setString(1, user.getLogin());
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        user.setPassword(rs.getString(1));
                        user.setMoney(rs.getInt(2));
                        user.setType(rs.getBoolean(3));
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public boolean store(User user) throws DatabaseConnectionException {
        if (user == null) {
            return false;
        }
        try (Connection cn = ConnectionManager.getInstance().getConnection()) {
            try (PreparedStatement st = cn.prepareStatement(USER_STORE)) {
                st.setString(1, user.getPassword());
                st.setInt(2, user.getMoney());
                st.setBoolean(3, user.isType());
                st.setString(4, user.getLogin());
                return st.executeUpdate() == 1;
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }
    }


    @Override
    public boolean create(EntityIF userEnt) throws DatabaseConnectionException {
        User user = (User) userEnt;
        if (user == null) {
            return false;
        }
        try (Connection cn = ConnectionManager.getInstance().getConnection()) {
            try (PreparedStatement st = cn.prepareStatement(USER_CREATE)) {
                st.setString(1, user.getLogin());
                st.setString(2, user.getPassword());
                st.setInt(3, user.getMoney());
                st.setBoolean(4, user.isType());
                return st.executeUpdate() == 1;
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }
    }
}
