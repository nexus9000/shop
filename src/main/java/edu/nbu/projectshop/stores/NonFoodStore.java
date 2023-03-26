package edu.nbu.projectshop.stores;

import edu.nbu.projectshop.NonFoodItems;
import edu.nbu.projectshop.factories.NonFoodItemFactory;

import java.util.HashMap;

public class NonFoodStore {
    private HashMap<Integer, NonFoodItems> storageMap;




    public NonFoodStore(HashMap<Integer,NonFoodItems> storageMap) {
       this.storageMap = storageMap;
    }
    public void addItem(NonFoodItems item){
        storageMap.put(item.getIndentificationNumber(), item);
    }
}
