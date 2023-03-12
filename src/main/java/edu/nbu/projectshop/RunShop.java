package edu.nbu.projectshop;


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
        logger.info(milk.getCategory());
    }
}
