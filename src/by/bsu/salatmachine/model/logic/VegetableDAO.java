package by.bsu.salatmachine.model.logic;

import by.bsu.salatmachine.exceptions.DatabaseConnectionException;
import by.bsu.salatmachine.model.entity.EntityIF;
import by.bsu.salatmachine.model.entity.NullEntity;
import by.bsu.salatmachine.model.entity.Vegetable;
import by.bsu.salatmachine.model.pool.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 15.12.11
 * Time: 23:24
 */
public class VegetableDAO extends AbstractDAO<Vegetable> {
    public final static String IDINCLUDINGS = "IDINCLUDINGS";
    public final static String IDVEGETABLE = "IDVEGETABLE";
    public final static String IDVEGETENUM = "IDVEGETENUM";

    private final static String VEGET_GETENTITY_ENUM = "SELECT * FROM Vegetdb.vegetable WHERE idVegetEnum = ? and inUse = 1";
    private final static String VEGET_GETENTITY_INCLUD = "SELECT * FROM Vegetdb.vegetable WHERE idIncludings = ? and inUse = 1";
    private final static String VEGET_GETENTITY_VEGET = "SELECT * FROM Vegetdb.vegetable WHERE IdVegetable = ? and inUse = 1";
    private final static String VEGET_GETENTITY = "SELECT * FROM Vegetdb.vegetable WHERE idVegetable = ? ";
    private final static String VEGET_UNUSE = "UPDATE vegetdb.vegetable SET inUse=0 WHERE idVegetable=?";

    private PreparedStatement parametrChoose(String parametr, Connection cn) throws SQLException {
        PreparedStatement st1;
        switch (parametr.toUpperCase()) {
            case IDINCLUDINGS: {
                st1 = cn.prepareStatement(VEGET_GETENTITY_INCLUD);
                break;
            }
            case IDVEGETABLE: {
                st1 = cn.prepareStatement(VEGET_GETENTITY_VEGET);
                break;
            }
            case IDVEGETENUM: {
                st1 = cn.prepareStatement(VEGET_GETENTITY_ENUM);
                break;
            }
            default: {
                st1 = cn.prepareStatement(VEGET_GETENTITY);
                break;
            }
        }
        return st1;
    }

    @Override
    public EntityIF getEntity(Object... objects) throws DatabaseConnectionException {
        String parametr = (String) objects[0];
        Integer value = (Integer) objects[1];
        EntityIF retn = new NullEntity();
        Vegetable vegetable;
        try (Connection cn = ConnectionManager.getInstance().getConnection()) {
            try (PreparedStatement statement = parametrChoose(parametr, cn)) {
                statement.setInt(1, value);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        vegetable = new Vegetable(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getDouble(5), rs.getInt(6), rs.getString(7));
                        retn = vegetable;
                    } else {
                        return retn;
                    }
                    PreparedStatement st = cn.prepareStatement(VEGET_UNUSE);
                    st.setInt(1, vegetable.getId());
                    st.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }
        return retn;
    }


    @Override
    public boolean create(EntityIF entity) {
        return false;
    }

    @Override
    public boolean refresh(Vegetable entity) {
        return false;
    }

    @Override
    public boolean delete(Vegetable entity) {
        return false;
    }

    @Override
    public boolean store(Vegetable entity) {
        return false;
    }
}
