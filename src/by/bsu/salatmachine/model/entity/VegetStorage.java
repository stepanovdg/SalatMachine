package by.bsu.salatmachine.model.entity;

import by.bsu.salatmachine.enums.VegetEnum;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 10.12.11
 * Time: 13:04
 * To change this template use File | Settings | File Templates.
 */
public class VegetStorage extends AbstractEntity {
    private VegetEnum idVegetEnum;
    private int idIncludings,idVegetable;

    public VegetEnum getIdVegetEnum() {
        return idVegetEnum;
    }

    public void setIdVegetEnum(VegetEnum idVegetEnum) {
        this.idVegetEnum = idVegetEnum;
    }

    public int getIdIncludings() {
        return idIncludings;
    }

    public void setIdIncludings(int idIncludings) {
        this.idIncludings = idIncludings;
    }

    public int getIdVegetable() {
        return idVegetable;
    }

    public void setIdVegetable(int idVegetable) {
        this.idVegetable = idVegetable;
    }
}
