package by.bsu.salatmachine.model.entity;

import by.bsu.salatmachine.enums.VegetMethodOfPreparationEnum;

/**
 * Created by IntelliJ IDEA.
 * UserDAO: Stepanov Dmitriy
 * Date: 09.12.11
 * Time: 13:36
 *
 */
public class ReceptDAO extends AbstractDAO{
    private int idRecept,idOrder,idVegetEnum,time;
    private double count;
    private VegetMethodOfPreparationEnum process;

    public int getIdRecept() {
        return idRecept;
    }

    public void setIdRecept(int idRecept) {
        this.idRecept = idRecept;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdVegetEnum() {
        return idVegetEnum;
    }

    public void setIdVegetEnum(int idVegetEnum) {
        this.idVegetEnum = idVegetEnum;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public VegetMethodOfPreparationEnum getProcess() {
        return process;
    }

    public void setProcess(VegetMethodOfPreparationEnum process) {
        this.process = process;
    }
}
