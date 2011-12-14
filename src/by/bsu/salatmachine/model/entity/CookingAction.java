package by.bsu.salatmachine.model.entity;

import by.bsu.salatmachine.enums.VegetMethodOfPreparationEnum;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 13.12.11
 * Time: 9:38
 */
public class CookingAction {
    private VegetMethodOfPreparationEnum process;
    private Integer time,idVegetEnum;
    private Double count;

    public VegetMethodOfPreparationEnum getProcess() {
        return process;
    }

    public void setProcess(VegetMethodOfPreparationEnum process) {
        this.process = process;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getIdVegetEnum() {
        return idVegetEnum;
    }

    public void setIdVegetEnum(Integer idVegetEnum) {
        this.idVegetEnum = idVegetEnum;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }



}
