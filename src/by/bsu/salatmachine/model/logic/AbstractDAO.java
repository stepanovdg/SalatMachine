package by.bsu.salatmachine.model.logic;

import by.bsu.salatmachine.exceptions.DatabaseConnectionException;
import by.bsu.salatmachine.model.entity.AbstractEntity;
import by.bsu.salatmachine.model.entity.EntityIF;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 13.12.11
 * Time: 18:12
 */
public abstract class AbstractDAO<E extends AbstractEntity> {
    public abstract EntityIF getEntity(Object... objects) throws DatabaseConnectionException;

    public abstract boolean create(EntityIF entity)  throws DatabaseConnectionException;

    public abstract boolean refresh(E entity)  throws DatabaseConnectionException;

    public abstract boolean delete(E entity)  throws DatabaseConnectionException;

    public abstract boolean store(E entity)  throws DatabaseConnectionException;

}
