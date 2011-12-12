package by.bsu.salatmachine.model.entity;

import by.bsu.salatmachine.enums.VegetEnum;

/**
 * Created by IntelliJ IDEA.
 * UserDAO: Stepanov Dmitriy
 * Date: 10.12.11
 * Time: 13:04
 * To change this template use File | Settings | File Templates.
 */
public class VegetStorageDAO extends AbstractDAO{
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
