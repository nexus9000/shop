package edu.nbu.projectshop.stores;

import edu.nbu.projectshop.goods.FoodItems;
import edu.nbu.projectshop.goods.NonFoodItems;
import org.apache.log4j.Logger;

import java.util.HashMap;

public class FoodStore {
    Logger logger = Logger.getLogger(FoodStore.class);
    private HashMap<Integer, FoodItems> storageMap;
    public FoodStore(HashMap<Integer,FoodItems> storageMap) {
        this.storageMap = storageMap;
    }
}
