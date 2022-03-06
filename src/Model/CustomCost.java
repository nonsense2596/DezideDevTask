package Model;

import java.util.Hashtable;

/**
 * Represents custom costs that can be present in a Model.
 */
public class CustomCost {
    private String name;
    private String value;

    private static final Hashtable<String,Float> adjustmentValues;

    /**
     * Static initializer block storing the numerical representations of various risk values.
     */
    static{
        adjustmentValues = new Hashtable<String,Float>(){{
            put("none",1.0f);
            put("low", 1.1f);
            put("medium", 1.3f);
            put("high", 2.0f);
        }};
    }

    /**
     * Constructor of the class, initializes the parameters of a CustomCost object.
     * @param name  Name of a custom cost.
     * @param value String value of a custom cost.
     */
    public CustomCost(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Gets and returns the name of the custom cost.
     * @return Name of the custom cost.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the custom cost to that received as the parameter.
     * @param name  The new name of the custom cost.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets and returns the value of the custom cost.
     * @return  The string representation of the value of the custom cost.
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the string representation of the value of the custom cost.
     * @param value The new value of the string representation of the value.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets and returns the numerical representation of the custom cost value.
     * @return  The float value of the custom cost.
     */
    public float getAdjustmentCost(){
        return adjustmentValues.get(value);
    }
}
