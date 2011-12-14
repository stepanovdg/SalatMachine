package by.bsu.salatmachine.model.logic;

import by.bsu.salatmachine.model.entity.AbstractEntity;

import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 13.12.11
 * Time: 18:12
 *
 * */
public abstract class AbstractDAO<E extends AbstractEntity> {
     public abstract E getEntity(Object... objects) throws RemoteException;
     public abstract boolean create(E entity) throws RemoteException;
     public abstract boolean refresh(E entity) throws RemoteException;
     public abstract boolean store(E entity) throws RemoteException;

}
