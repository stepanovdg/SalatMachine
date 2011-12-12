package by.bsu.salatmachine.model.logic;

import by.bsu.salatmachine.model.entity.ReceptStorageDAO;
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
 *
 */
public class ReceptStorageLogic {
     public static ReceptStorageDAO getRecept(
            String login) throws RemoteException {
        ReceptStorageDAO receptStorageDAO = new ReceptStorageDAO();
        try {
            Connection cn = ConnectionManager.getConnection();
            try (PreparedStatement st = cn.prepareStatement(
                    "SELECT * FROM vegetdb.receptstorage WHERE visibility=? OR visibility=?")) {
                st.setString(1, login);
                st.setString(2, "admin");
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        receptStorageDAO.addRecept(rs.getInt(1),rs.getString(2));
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
        return receptStorageDAO;

    }
    public static ReceptStorageDAO getAllRecept(
            String login) throws RemoteException {
        ReceptStorageDAO receptStorageDAO = new ReceptStorageDAO();
        try {
            Connection cn = ConnectionManager.getConnection();
            try (PreparedStatement st = cn.prepareStatement(
                    "SELECT * FROM vegetdb.receptstorage ")) {
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        receptStorageDAO.addRecept(rs.getInt(1),rs.getString(2));
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
        return receptStorageDAO;

    }
}
