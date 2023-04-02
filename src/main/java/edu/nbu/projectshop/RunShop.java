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
import java.util.Map;


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
        Cashier cashier = new Cashier(101, "John", "Smith", 1000.00);
        Cashier cashier2 = new Cashier(102, "Pit", "Smith", 1000.00);
        HashMap<Integer, Cashier> listCashier = new HashMap<>();
        listCashier.put(cashier.getId(), cashier);
        listCashier.put(cashier2.getId(), cashier2);
        HashMap<Integer, Cashier> casse = initCasse(listCashier);
        casse.forEach((k,v)-> System.out.println(k + " "+v));
    }

    private static @NotNull HashMap<Integer, Cashier> initCasse(@NotNull HashMap<Integer, Cashier> cashiers){
        HashMap<Integer, Cashier> casse = new HashMap<>();
        Integer numCasse = 1;
        for(Map.Entry<Integer,Cashier> key   : cashiers.entrySet()){
            casse.put(numCasse,(Cashier) key.getValue());
            numCasse ++;
        }

        return casse;
    }
}
