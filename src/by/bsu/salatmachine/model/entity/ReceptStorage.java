package by.bsu.salatmachine.model.entity;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 10.12.11
 * Time: 13:07
 */
public class ReceptStorage extends AbstractEntity {
    private Integer idRecept;

    public ReceptStorage(Integer idRecept, String name, String visibility) {
        this.setIdRecept(idRecept);
        this.setName(name);
        this.setVisibility(visibility);
    }

    private String name;

    private String visibility;

    public Integer getIdRecept() {
        return idRecept;
    }

    public void setIdRecept(Integer idRecept) {
        this.idRecept = idRecept;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @Override
    public int compareTo(Object o) {
        return (this.getIdRecept() - ((ReceptStorage) o).getIdRecept());
    }
}
