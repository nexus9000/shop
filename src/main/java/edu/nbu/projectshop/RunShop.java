package edu.nbu.projectshop;


import java.util.Arrays;
import java.util.List;

public class RunShop {
    public static void main(String...arg){
        List<String> foodItems = Arrays.asList("milk", "meat", "bread");
        FoodItemsFactory fif = new FoodItemsFactory(foodItems);
        Milk milk = (Milk) fif.createInstance("milk");
        Meat meat = (Meat)fif.createInstance("meat");
        System.out.println(milk.getCategory());
    }
}
