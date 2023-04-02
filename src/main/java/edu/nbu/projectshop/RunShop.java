package edu.nbu.projectshop;


import edu.nbu.projectshop.employee.Cashier;
import edu.nbu.projectshop.factories.FoodItemsFactory;
import edu.nbu.projectshop.factories.NonFoodItemFactory;
import edu.nbu.projectshop.goods.FoodItems;
import edu.nbu.projectshop.goods.NonFoodItems;
import edu.nbu.projectshop.stores.NonFoodStore;
import edu.nbu.projectshop.tools.GenerateIdNumber;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;



public class RunShop {
    private static final Logger logger = Logger.getLogger(RunShop.class);
    public static void main(String...arg){

        FoodItems milk =  FoodItemsFactory.createInstance("milk");
        NonFoodItems nails = NonFoodItemFactory.createInstance("nails");
        nails.setQuantity(100);
        nails.setIdNum(GenerateIdNumber.generateId());
        System.out.println(nails.getItemName() + " instance was created!");
        logger.info(nails.getItemName() + " instance was created!");
        logger.info("Init non food store");
        HashMap <Integer, NonFoodItems> storeMap = new HashMap<>();
        NonFoodStore nonFoodStore = new NonFoodStore(storeMap);
        nonFoodStore.addItem(nails.getIdNum(), nails);
        nonFoodStore.listItem();
    }

    private static @NotNull HashMap<Integer, Cashier> initCasse(@NotNull HashMap<Integer, Cashier> cashiers){
        HashMap<Integer, Cashier> casse = new HashMap<>();
        cashiers.forEach((k,v)-> {

        });
        return casse;
    }
}
