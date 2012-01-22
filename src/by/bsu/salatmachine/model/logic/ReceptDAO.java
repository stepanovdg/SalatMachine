package by.bsu.salatmachine.model.logic;

import by.bsu.salatmachine.exceptions.DatabaseConnectionException;
import by.bsu.salatmachine.model.entity.EntityIF;
import by.bsu.salatmachine.model.entity.NullEntity;
import by.bsu.salatmachine.model.entity.Recept;
import by.bsu.salatmachine.model.entity.TreeSetOfEntity;
import by.bsu.salatmachine.model.pool.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 14.12.11
 * Time: 18:03
 */
public class ReceptDAO extends AbstractDAO<Recept> {
    private final static String RECEPT_GETENTITY = "SELECT * FROM vegetdb.recept WHERE idrecept=? AND inUse = 1";
    private final static String RECEPT_DELETE_IDRECEPT = "DELETE FROM vegetdb.recept WHERE idrecept=?";
    private final static String RECEPT_DELETE = "DELETE FROM vegetdb.recept WHERE idrecept=? and idorder = ?";
    private final static String RECEPT_CREATE = "INSERT INTO vegetdb.recept (idRecept, idOrder, idVegetEnum, count, process, time, inUse) VALUES (?, ?, ?, ?, ?, ?, ?)";

    @Override
    public EntityIF getEntity(Object... objects) throws DatabaseConnectionException {
        Integer receptId = (Integer) objects[0];
        EntityIF retn = new NullEntity();
        Recept recept;
        TreeSetOfEntity receptTree = new TreeSetOfEntity<Recept>();
        try (Connection cn = ConnectionManager.getInstance().getConnection()) {
            try (PreparedStatement st = cn.prepareStatement(RECEPT_GETENTITY)) {
                st.setInt(1, receptId);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        do {
                            recept = new Recept(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4), rs.getString(5), rs.getInt(6));
                            receptTree.add(recept);
                        } while (rs.next());
                        retn = receptTree;
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
        try (Connection cn = ConnectionManager.getInstance().getConnection()) {
            if (entity.getClass() == TreeSetOfEntity.class) {
                for (Object e : (TreeSetOfEntity) entity) {
                    if (!create(cn, (Recept) e)) {
                        return false;
                    }
                }
                return true;
            }
            if (entity.getClass() == Recept.class) {
                return create(cn, (Recept) entity);
            }

        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }

        return false;
    }

    public boolean create(Connection cn, Recept entity) throws SQLException {
        try (PreparedStatement st = cn.prepareStatement(RECEPT_CREATE)) {
            st.setInt(1, entity.getIdRecept());
            st.setInt(2, entity.getIdOrder());
            st.setInt(3, entity.getIdVegetEnum());
            st.setDouble(4, entity.getCount());
            st.setString(5, entity.getProcess().toString().toUpperCase());
            st.setInt(6, entity.getTime());
            st.setBoolean(7, true);
            st.execute();
            return true;
        }

    }

    @Override
    public boolean refresh(Recept entity) {
        return false;
    }

    @Override
    public boolean delete(Recept entity) throws DatabaseConnectionException {
        try (Connection cn = ConnectionManager.getInstance().getConnection()) {
            try (PreparedStatement st = cn.prepareStatement(RECEPT_DELETE)) {
                st.setInt(1, entity.getIdRecept());
                st.setInt(2, entity.getIdOrder());
                return st.execute();
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }
    }

    public boolean delete(Integer idRecept) throws DatabaseConnectionException {
        try (Connection cn = ConnectionManager.getInstance().getConnection()) {
            try (PreparedStatement st = cn.prepareStatement(RECEPT_DELETE_IDRECEPT)) {
                st.setInt(1, idRecept);
                st.execute();
                return true;
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }

    }

    @Override
    public boolean store(Recept entity) {
        return false;
    }
}
