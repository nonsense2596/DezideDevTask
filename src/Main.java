import Model.Models;
import Model.Model;
import Model.CustomCost;

import java.util.ArrayList;

/*
 We assume that the inputs are correct, and in the correct order and format,
 otherwise a potential solution would be wrapping the (args to variables) assignments
 in try-catch blocks and checking for correctness there as well as checking whether a given id or value
 returns null when searched in a table or list.
 */

/**
 * Main class and main function being the entry points of the application/module.
 */
public class Main {
    public static void main(String[] args) {

        float time = Float.parseFloat(args[0]);
        float money = Float.parseFloat(args[1]);
        String modelId = args[2];


        // set the values for the example model named "model1234"
        Models models = new Models();
        ArrayList<CustomCost> model1234Costs = new ArrayList<CustomCost>(){{
            add(new CustomCost("Risk","high"));
            add(new CustomCost("Inconvenience","medium"));
        }};
        models.put("model1234",
                new Model.ModelBuilder("model1234")
                        .timeFactor(500)
                        .customCosts(model1234Costs)
                        .build()
        );

        // if we have more than 3 arguments, they are the custom cost attributes
        if(args.length>3){
            for(int i=3;i<args.length;i++) {
                String type = args[i].split(":")[0];
                String value = args[i].split(":")[1];
                models.get(modelId).addCustomCosts(new CustomCost(type,value));
            }
        }

        // calculate the total cost and print out the result
        var totalCost = Calculator.calculateTotalCost(time, models.get(modelId), money);
        System.out.println(totalCost);
    }
}