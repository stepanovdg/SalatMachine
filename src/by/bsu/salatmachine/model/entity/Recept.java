package by.bsu.salatmachine.model.entity;

import by.bsu.salatmachine.enums.VegetEnum;
import by.bsu.salatmachine.enums.VegetMethodOfPreparationEnum;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 13.12.11
 * Time: 9:38
 */
public class Recept extends AbstractEntity {
    private Integer idRecept;
    private Integer idOrder;
    private Integer idVegetEnum;
    private Double count;
    private VegetMethodOfPreparationEnum process;
    private Integer time;


    public Recept(Integer idRecept, Integer idOrder, Integer idVegetEnum, Double count, String  process, Integer time) {
         this.setProcess(VegetMethodOfPreparationEnum.valueOf(process.toUpperCase()));
        this.setTime(time);
        this.setIdVegetEnum(idVegetEnum);
        this.setIdOrder(idOrder);
        this.setIdRecept(idRecept);
        this.setCount(count);
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getIdRecept() {
        return idRecept;
    }

    public void setIdRecept(Integer idRecept) {
        this.idRecept = idRecept;
    }



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
    public VegetEnum getVegetEnum() {
        return VegetEnum.values()[idVegetEnum];
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



    @Override
    public int compareTo(Object o) {
        Recept recept = (Recept) o;
        if (this.getIdRecept().equals(recept.getIdRecept())){
            if (this.getIdOrder().equals(recept.getIdOrder())) {
                this.setIdOrder(this.getIdOrder()+1);
                return this.getIdOrder().compareTo(recept.getIdOrder());
            } else {
                return this.getIdOrder().compareTo(recept.getIdOrder());
            }
        }else {
            return (this.getIdRecept() - ((Recept) o).getIdRecept());
        }
    }
}
