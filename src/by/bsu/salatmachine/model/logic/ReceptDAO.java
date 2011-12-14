package by.bsu.salatmachine.model.logic;

import by.bsu.salatmachine.controller.manager.ConfigurationManager;
import by.bsu.salatmachine.model.entity.Recept;
import by.bsu.salatmachine.model.pool.ConnectionManager;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 14.12.11
 * Time: 18:03
 *
 */
public class ReceptDAO extends AbstractDAO<Recept>{
    @Override
    public Recept getEntity(Object... objects) throws RemoteException {
        Integer receptId = (Integer) objects[0];
        Recept recept = new Recept();
        recept.setIdRecept(receptId);
        try {
            Connection cn = ConnectionManager.getInstance().getConnection();
           try (PreparedStatement st = cn.prepareStatement(
                   ConfigurationManager.getInstance().getProperty("RECEPT_GETENTITY"))) {
                    st.setInt(1, receptId);

                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        do {
                            recept.addProcess(rs.getInt(2),rs.getDouble(3),rs.getString(4),rs.getInt(5));
                        } while (rs.next());
                    } else {
                        throw new RemoteException("Reading: the list of recepts is empty");  //todo delete all remote
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
        return recept;
    }

    @Override
    public boolean create(Recept entity) throws RemoteException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean refresh(Recept entity) throws RemoteException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean store(Recept entity) throws RemoteException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
