package by.bsu.salatmachine.model.entity;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 15.12.11
 * Time: 15:12
 */
public class NullEntity extends AbstractEntity {
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
