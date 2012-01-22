package by.bsu.salatmachine.enums;

/**
 * @author Stepanov Dmitriy
 */
public enum VegetEnum {
     TURNIP(100, 12, 0.5, "RV"),CABBAGE(100, 15, 0.5, "LV"), DILL(100, 7, 0.1, "OV"), MINT(100, 6, 0.1, "PV"), POTATOES(
            200, 15, 1, "RV"), CARROTS(300, 16, 0.1, "RV"), BEETS(300, 20, 0.2, "RV"), BEANS(
            200, 14, 1, "BV"), TOMATO(300, 16, 0.1, "PV"), CUCUMBER(300, 16, 0.1,
            "PV"), ONION(300, 16, 0.1, "LV"), HORSERADISH(300, 16, 0.1, "RV"), GARLIC(
            300, 16, 0.1, "LV");
    private final double pricePerKG;// in curtsies
    private final double parseWeight;// in kilograms
    private final double parseCalorie;// in calories
    private final String vegetKind;// in two letters example "LV"


    private VegetEnum(double pricePerKG, double parseWeight,
                      double parseCalorie, String vegetKind) {
        this.pricePerKG = pricePerKG;
        this.parseCalorie = parseCalorie;
        this.parseWeight = parseWeight;
        this.vegetKind = vegetKind;
    }

    public double pricePerKG() {
        return pricePerKG;
    }

    public double parseWeight() {
        return parseWeight;
    }

    public double parseCalorie() {
        return parseCalorie;
    }

    public String vegetKind() {
        return vegetKind;
    }

}
