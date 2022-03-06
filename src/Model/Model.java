package Model;

import java.util.ArrayList;

/**
 * Model class that can store the time factor, and optional related customer-specific costs belonging to a given named model.
 */
public class Model {

    private String id;
    private int timeFactor;
    private ArrayList<CustomCost> customCosts;

    /**
     * Constructor of the class, that initializes the respective values of the class from a ModelBuilder object.
     * @param modelbuilder  A ModelBuilder object storing all the mandatory and optional parameters a Model can has.
     */
    public Model(ModelBuilder modelbuilder){
        this.id = modelbuilder.id;
        this.timeFactor = modelbuilder.timeFactor;
        this.customCosts = modelbuilder.customCosts;
    }

    /**
     * Gets and returns the name of the Model.
     * @return  The string ID of the Model.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the name of the Model to that received as the parameter.
     * @param id    The new string ID of the Model.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets and returns the time factor associated with the Model.
     * If time factor was not explicitly set, it returns the global time factor of 300.
     * @return  Time factor of the Model.
     */
    public int getTimeFactor() {
        return timeFactor;
    }

    /**
     * Sets the time factor of the Model to that received as the parameter.
     * @param timeFactor    The new time factor of the Model.
     */
    public void setTimeFactor(int timeFactor) {
        this.timeFactor = timeFactor;
    }

    /**
     * Gets and returns the array containing the list of customer-specific costs of the given Model.
     * @return  List of CustomCost objects of the Model.
     */
    public ArrayList<CustomCost> getCustomCosts() {
        return customCosts;
    }

    /**
     * Sets the customer-specific cost list of the Model.
     * @param customCosts   The new list of CustomCost of the Model.
     */
    public void setCustomCosts(ArrayList<CustomCost> customCosts) {
        this.customCosts = customCosts;
    }

    /**
     * Adds a new customer-specific cost to the CustomCost list of the Model. Updates the custom cost if its name
     * already exists in the system.
     * @param customCost    A new CustomCost object to be added or updated.
     */
    public void addCustomCosts(CustomCost customCost){
        addCustomCostIfExists(customCost, this.customCosts);
    }

    /**
     * Helper function for conditionally adding or updating a customer-specific cost in the list.
     * If the given CustomCost exists, update its value. If it does not exists, add it to the list.
     * @param newCustomCost New CustomCost object to be added or updated.
     * @param customCosts   The list of CustomCost of a Model, where the new CustomCost will be added to.
     */
    private static void addCustomCostIfExists(CustomCost newCustomCost, ArrayList<CustomCost> customCosts) {
        boolean ifExists = customCosts.stream().anyMatch(x -> x.getName().equals(newCustomCost.getName()));
        if(ifExists) {
            customCosts.stream().filter(x -> x.getName().equals(newCustomCost.getName())).forEach(y -> y.setValue(newCustomCost.getValue()));
        }
        else customCosts.add(newCustomCost);
    }

    /**
     * Gets and returns the cumulative value of all the customer-specific costs in the system for the given Model.
     * @return  Float value representing the customer-specific cost adjustment values combined.
     */
    public float getCumulativeCustomCosts()
    {
        float cumulativeCustomCost = 1.0f;
        for(CustomCost customCost : customCosts){
            cumulativeCustomCost *= customCost.getAdjustmentCost();
        }
        return cumulativeCustomCost;
    }

    /**
     * Inner class implementing the Builder design pattern so the Model class can have arbitrarily parameterized
     * constructors.
     */
    public static class ModelBuilder{
        private final String id;
        private int timeFactor;
        private ArrayList<CustomCost> customCosts = new ArrayList<CustomCost>();

        /**
         * The default constructor setting the "global" default values to a new Model object. These are overwritten
         * if further builder parameters are supplied.
         * @param id    The name/ID of the Model to be created.
         */
        public ModelBuilder(String id){
            this.id = id;
            this.timeFactor = 300;
            customCosts.add(new CustomCost("Risk","low"));
            customCosts.add(new CustomCost("Inconvenience","high"));
        }

        /**
         * Adds the time factor parameter to the builder class.
         * @param timeFactor    Supplied time factor value.
         * @return  Returns a ModelBuilder instance with the time factor set.
         */
        public ModelBuilder timeFactor(int timeFactor){
            this.timeFactor = timeFactor;
            return this;
        }

        /**
         * Adds the list of custom costs to the builder class.
         * @param customCosts Supplied list of custom costs.
         * @return Returns a ModelBuilder instance with the custom cost list set.
         */
        public ModelBuilder customCosts(ArrayList<CustomCost> customCosts){
            for(var customCost : customCosts){
                addCustomCostIfExists(customCost, (ArrayList<CustomCost>) this.customCosts);
            }
            return this;
        }

        /**
         * Creates a new Model instance based on the current state and values of the ModelBuilder object.
         * @return  Created new Model instance.
         */
        public Model build()
        {
            Model model = new Model(this);
            return model;
        }
    }
}
