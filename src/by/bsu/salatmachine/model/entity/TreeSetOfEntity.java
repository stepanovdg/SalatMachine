package by.bsu.salatmachine.model.entity;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 18.12.11
 * Time: 18:59
 */
public class TreeSetOfEntity<E extends AbstractEntity> extends TreeSet implements EntityIF {
    public Integer getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Integer currentPosition) {
        this.currentPosition = currentPosition;
    }

    private Integer currentPosition;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = this.iterator();
        while (it.hasNext()) {
            stringBuilder.append(it.next().toString());
        }
        return stringBuilder.toString();
    }


    public boolean delete(E e) {
        if (this.contains(e)) {
            return this.remove(e);
        } else {
            return false;
        }
    }

    public boolean delete(Integer number) {
        return this.remove(this.toArray()[number]);
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }
}
