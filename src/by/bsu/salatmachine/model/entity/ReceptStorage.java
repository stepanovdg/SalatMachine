package by.bsu.salatmachine.model.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 10.12.11
 * Time: 13:07
 *
 */
public class ReceptStorage extends AbstractEntity {
    private Map<Integer,String> storage;


    public ReceptStorage() {
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

    @Override
    public String toString() {

        return storage.toString();
               //todo
    }
}
