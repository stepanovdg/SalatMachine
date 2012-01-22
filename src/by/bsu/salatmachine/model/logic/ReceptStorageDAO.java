package by.bsu.salatmachine.model.logic;

import by.bsu.salatmachine.controller.manager.ConfigurationManager;
import by.bsu.salatmachine.exceptions.DatabaseConnectionException;
import by.bsu.salatmachine.model.entity.*;
import by.bsu.salatmachine.model.pool.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 10.12.11
 * Time: 13:28
 */
public class ReceptStorageDAO extends AbstractDAO<ReceptStorage> {
    private final static String RECEPTSTORAGE_GETENTITY = "SELECT * FROM vegetdb.receptstorage WHERE visibility=? OR visibility=? and inUse = 1";
    private final static String RECEPTSTORAGE_CREATE = "INSERT INTO vegetdb.receptstorage (nameRecept, visibility) VALUES (?,?)";
    private final static String RECECPTSTORAGE_GETENTITY_ALL = "SELECT * FROM vegetdb.receptstorage ";
    private final static String RECECPTSTORAGE_VISIBILITY_CHANGE = "UPDATE vegetdb.receptstorage SET visibility = 'admin' WHERE idRecept=?; ";
    private final static String RECECPTSTORAGE_DELETE = "DELETE FROM vegetdb.receptstorage WHERE idrecept=?";

    private PreparedStatement loginType(String login, Connection cn) throws SQLException {
        PreparedStatement st1;
        String admin = ConfigurationManager.getInstance().getProperty("ADMIN_LOGIN");
        if (!login.toLowerCase().equals(admin.toLowerCase())) {
            st1 = cn.prepareStatement(RECEPTSTORAGE_GETENTITY);
            st1.setString(1, login);
            st1.setString(2, admin);
        } else {
            st1 = cn.prepareStatement(RECECPTSTORAGE_GETENTITY_ALL);
        }
        return st1;
    }

    @Override
    public EntityIF getEntity(Object... objects) throws DatabaseConnectionException {
        String login = (String) objects[0];

        EntityIF retn = new NullEntity();
        ReceptStorage receptStorage;
        TreeSetOfEntity<ReceptStorage> receptStorageTree = new TreeSetOfEntity<>();
        try (Connection cn = ConnectionManager.getInstance().getConnection()) {
            try (PreparedStatement statement = loginType(login, cn)) {
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {

                        do {
                            receptStorage = new ReceptStorage(rs.getInt(1), rs.getString(2), rs.getString(3));
                            receptStorageTree.add(receptStorage);
                        } while (rs.next());
                        retn = receptStorageTree;
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
    public boolean create(EntityIF entity) throws DatabaseConnectionException {
        ReceptStorage receptStorage = (ReceptStorage) entity;

        try (Connection cn = ConnectionManager.getInstance().getConnection()) {
            try (PreparedStatement st = cn.prepareStatement(RECEPTSTORAGE_CREATE)) {
                st.setString(1, receptStorage.getName());
                st.setString(2, receptStorage.getVisibility());
                st.execute();
                return true;
            }

        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }


    }

    public boolean visibilityChange(Integer idRecept) throws DatabaseConnectionException {
        try (Connection cn = ConnectionManager.getInstance().getConnection()) {
            try (PreparedStatement st = cn.prepareStatement(RECECPTSTORAGE_VISIBILITY_CHANGE)) {
                st.setInt(1, idRecept);
                st.execute();
                return true;
            }

        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }
    }

    @Override
    public boolean refresh(ReceptStorage entity) throws DatabaseConnectionException {
        return false;
    }

    @Override
    public boolean delete(ReceptStorage entity) throws DatabaseConnectionException {
        try (Connection cn = ConnectionManager.getInstance().getConnection()) {
            try (PreparedStatement st = cn.prepareStatement(RECECPTSTORAGE_DELETE)) {
                st.setInt(1, entity.getIdRecept());
                return st.execute();
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }
    }

    public boolean delete(Integer idRecept) throws DatabaseConnectionException {
            try (Connection cn = ConnectionManager.getInstance().getConnection()) {
                try (PreparedStatement st = cn.prepareStatement(RECECPTSTORAGE_DELETE)) {
                    st.setInt(1, idRecept);
                    st.execute();
                    return true;
                }
            } catch (SQLException e) {
                throw new DatabaseConnectionException(e);
            }

        }

    @Override
    public boolean store(ReceptStorage entity) throws DatabaseConnectionException {
        return false;
    }
}
