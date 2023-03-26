package edu.nbu.projectshop;


import edu.nbu.projectshop.factories.FoodItemsFactory;
import edu.nbu.projectshop.factories.NonFoodItemFactory;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class RunShop {
    private static final Logger logger = Logger.getLogger(RunShop.class);
    public static void main(String...arg){
        List<String> foodItems = Arrays.asList("milk", "meat", "bread");
        FoodItemsFactory fif = new FoodItemsFactory(foodItems);
        Milk milk = (Milk) fif.createInstance("milk");
        Meat meat = (Meat)fif.createInstance("meat");
        NonFoodItems nails = NonFoodItemFactory.createInstance("nails");
        System.out.println(nails.getItemName() + " instance was created!");
        logger.info(nails.getItemName() + " instance was created!");
        logger.info(milk.getCategory());
    }
}
