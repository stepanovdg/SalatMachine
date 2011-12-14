package by.bsu.salatmachine.enums;

/**
 * @author Stepanov Dmitriy
 */
public enum VegetEnum {
    CABBAGE(4, 15, 0.5, "LV"), DILL(4, 7, 0.1, "OV"), MINT(4, 6, 0.1, "PV"), POTATOES(
            7, 15, 1, "RV"), CARROTS(8, 16, 0.1, "RV"), BEETS(8, 20, 0.2, "RV"), BEANS(
            7, 14, 1, "BV"), TOMATO(8, 16, 0.1, "PV"), CUCUMBER(8, 16, 0.1,
            "PV"), ONION(8, 16, 0.1, "LV"), HORSERADISH(8, 16, 0.1, "RV"), GARLIC(
            8, 16, 0.1, "LV");
    private final double idealTemperature;// in curtsies
    private final double parseWeight;// in kilograms
    private final double parseCalorie;// in calories
    private final String vegetKind;// in two letters example "LV"

    // private final Class<?> vegetKindClass;// in two letters example "LV"

    private VegetEnum(double idealTemperature, double parseWeight,
                      double parseCalorie, String vegetKind) {
        this.idealTemperature = idealTemperature;
        this.parseCalorie = parseCalorie;
        this.parseWeight = parseWeight;
        this.vegetKind = vegetKind;
        // this.vegetKindClass = vegetKindClass;
    }

    public double idealTemperature() {
        return idealTemperature;
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
    /*
      * public Class<?> vegetKindClass() { return vegetKindClass; }
      */
}
