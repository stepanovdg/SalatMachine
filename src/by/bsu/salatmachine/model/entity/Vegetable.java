package by.bsu.salatmachine.model.entity;

import java.sql.Date;

/**
 * @author Stepanov Dmitriy
 */

public class Vegetable extends AbstractEntity{
    private Integer id;
    private Integer vegetEnumId;
    private String produceCountry;
    private Date colectDate;
    private Double weightPerKg;
    private Integer pricePerKg;
    private String uniqParameter;



    public Vegetable(int id, int vegetId, String produceCountry,
                        Date colectDate, Double weightPerKg, int pricePerKg,
                        String uniqParameter) {
        this.setId(id);
        this.setVegetEnumId(vegetId);
        this.setProduceCountry(produceCountry);
        this.setColectDate(colectDate);
        this.setWeightPerKg(weightPerKg);
        this.setPricePerKg(pricePerKg);
        this.setUniqParameter(uniqParameter);
    }

    @Override
    public int hashCode() {
        int result = 7;
        int hashId = this.getId();
        result = 17 * result + hashId;
        int hashVegEnId = this.getVegetEnumId();
        result = 17 * result + hashVegEnId;
        int hashCountry = this.getProduceCountry().hashCode();
        result = 17 * result + hashCountry;
        int hashDate = this.getColectDate().hashCode();
        result = 17 * result + hashDate;
        long lon = this.getWeightPerKg().longValue();
        int hashWeight = (int) (lon - (lon >>> 32));
        result = 17 * result + hashWeight;
        lon = this.getPricePerKg().longValue();
        int hashPrice = (int) (lon - (lon >>> 32));
        result = 17 * result + hashPrice;
        int hashUniq = this.getUniqParameter().hashCode();
        result = 17 * result + hashUniq;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Vegetable))
            return false;
        Vegetable otherVeg = (Vegetable) obj;
        return (this.getId().equals(otherVeg.getId()));
    }
     public String getUniqParameter() {
        return uniqParameter;
    }

    public void setUniqParameter(String uniqParameter) {
        this.uniqParameter = uniqParameter;
    }


    /**
     * @param id the id to set
     */
    protected void setId(int id) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    protected void setVegetEnumId(int name) {
        this.vegetEnumId = name;
    }

    /**
     * @param produceCountry the produceCountry to set
     */
    protected void setProduceCountry(String produceCountry) {
        this.produceCountry = produceCountry;
    }

    /**
     * @param colectDate the colectDate to set
     */
    protected void setColectDate(Date colectDate) {
        this.colectDate =  colectDate;
    }
    /**
     * @param weightPerKg the weightPerKg to set
     */
    protected void setWeightPerKg(Double weightPerKg) {
        this.weightPerKg = weightPerKg;
    }

    /**
     * @param pricePerKg the pricePerKg to set
     */
    protected void setPricePerKg(Integer pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the name
     */
    public Integer getVegetEnumId() {
        return vegetEnumId;
    }

    /**
     * @return the produceCountry
     */
    public String getProduceCountry() {
        return produceCountry;
    }

    /**
     * @return the colectDate
     */
    public Date getColectDate() {
        return colectDate;
    }

    /**
     * @return the weightPerKg
     *
     */

    public Double getWeightPerKg() {
        return weightPerKg;
    }



    /**
     * @return the pricePerKg
     */
    public Integer getPricePerKg() {
        return pricePerKg;
    }

    @Override
    public int compareTo(Object o) {
        Vegetable veg1 = (Vegetable) o;
        return this.getId().compareTo(veg1.getId());
    }
}
