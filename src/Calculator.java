import Model.Model;

public class Calculator {
    /**
     * The static functions that calculates the cost of actions given the parameters it receives.
     * @param time  Time measured in minutes.
     * @param model The model containing the TimeFactor and an array of CustomCosts that are needed for the calculation.
     * @param money Monetary value of spare parts or other direct costs.
     * @return      The cost of the actions given the parameters rounded to the nearest decimal.
     */
    public static int calculateTotalCost(float time, Model model, float money)
    {
        var unadjustedCost =  (time/60 * model.getTimeFactor() + money);
        var customCostMultiplier = model.getCumulativeCustomCosts();
        return Math.round(unadjustedCost*customCostMultiplier);
    }
}
