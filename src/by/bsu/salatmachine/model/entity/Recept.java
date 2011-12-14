package by.bsu.salatmachine.model.entity;

import by.bsu.salatmachine.enums.VegetMethodOfPreparationEnum;

import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 09.12.11
 * Time: 13:36
 *
 */
public class Recept extends AbstractEntity {
    private int idRecept;
    private TreeMap<Integer,CookingAction> process =new TreeMap<>();

    public int getIdRecept() {
        return idRecept;
    }

    public void setIdRecept(int idRecept) {
        this.idRecept = idRecept;
    }

    public TreeMap<Integer, CookingAction> getProcess() {
        return process;
    }

    public void setProcess(TreeMap<Integer, CookingAction> process) {
        this.process = process;
    }
    public void addProcess(CookingAction cookingAction){
        this.process.put(process.size()+1,cookingAction);
    }
    public void addProcess(Integer idVegetEnum,Double count,String proc,Integer time){
        CookingAction cookingAction = new CookingAction();
        VegetMethodOfPreparationEnum pr = VegetMethodOfPreparationEnum.valueOf(proc);
        cookingAction.setIdVegetEnum(idVegetEnum);
        cookingAction.setCount(count);
        cookingAction.setProcess(pr);
        cookingAction.setTime(time);
        addProcess(cookingAction);
    }
    @Override
    public String toString() {

        return idRecept+" "+process.toString()+"/n";
               //todo
    }
}
