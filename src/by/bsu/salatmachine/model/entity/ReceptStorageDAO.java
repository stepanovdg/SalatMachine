package by.bsu.salatmachine.model.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * UserDAO: Stepanov Dmitriy
 * Date: 10.12.11
 * Time: 13:07
 * To change this template use File | Settings | File Templates.
 */
public class ReceptStorageDAO extends AbstractDAO{
    private Map<Integer,String> storage;


    public ReceptStorageDAO() {
        this.storage = new HashMap<>();
    }

    public void addRecept(Integer id,String name){
        this.storage.put(id,name);
    }
    public Map<Integer, String> getStorage() {
        return storage;
    }

    public void setStorage(Map<Integer, String> storage) {
        this.storage = storage;
    }
}
