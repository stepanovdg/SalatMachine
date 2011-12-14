package by.bsu.salatmachine.model.logic;

import by.bsu.salatmachine.controller.manager.ConfigurationManager;
import by.bsu.salatmachine.model.entity.ReceptStorage;
import by.bsu.salatmachine.model.pool.ConnectionManager;

import java.rmi.RemoteException;
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
    private PreparedStatement st1;

   /* public static ReceptStorage getRecept(
            String login) throws RemoteException {
        ReceptStorage receptStorage = new ReceptStorage();
        try {
            Connection cn = ConnectionManager.getConnection();
            try (PreparedStatement st = cn.prepareStatement(
                    "SELECT * FROM vegetdb.receptstorage WHERE visibility=? OR visibility=?")) {
                st.setString(1, login);
                st.setString(2, ConfigurationManager.getInstance().getProperty("ADMIN_LOGIN"));
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        do {
                            receptStorage.addRecept(rs.getInt(1), rs.getString(2));
                        } while (rs.next());
                    } else {
                        throw new RemoteException("Reading: the list of recepts is empty");
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
        return receptStorage;

    }

    public static ReceptStorage getAllRecept(
            String login) throws RemoteException {
        ReceptStorage receptStorage = new ReceptStorage();
        try {
            Connection cn = ConnectionManager.getConnection();
            try (PreparedStatement st = cn.prepareStatement(
                    "SELECT * FROM vegetdb.receptstorage ")) {
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        do {
                            receptStorage.addRecept(rs.getInt(1), rs.getString(2));
                        } while (rs.next());
                    } else {
                        throw new RemoteException("Reading: the list of recepts is empty");
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
        return receptStorage;

    }*/

    @Override
    public ReceptStorage getEntity(Object... objects) throws RemoteException {
        String login = (String) objects[0];
        String admin = ConfigurationManager.getInstance().getProperty("ADMIN_LOGIN");
        ReceptStorage receptStorage = new ReceptStorage();
        try {
            Connection cn = ConnectionManager.getInstance().getConnection();
            try {
                if (login.toLowerCase().equals(admin.toLowerCase())) {
                    st1 = cn.prepareStatement(ConfigurationManager.getInstance().getProperty("RECEPTSTORAGE_GETENTITY"));
                    st1.setString(1, login);
                    st1.setString(2, admin);
                }else{
                    st1 = cn.prepareStatement(
                    "SELECT * FROM vegetdb.receptstorage ");
                }
                try (ResultSet rs = st1.executeQuery()) {
                    if (rs.next()) {
                        do {
                            receptStorage.addRecept(rs.getInt(1), rs.getString(2));
                        } while (rs.next());
                    } else {
                        throw new RemoteException("Reading: the list of recepts is empty"); //TODO DELETE ALL REMOTE
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
        return receptStorage;
    }

    @Override
    public boolean create(ReceptStorage entity) throws RemoteException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean refresh(ReceptStorage entity) throws RemoteException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean store(ReceptStorage entity) throws RemoteException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
