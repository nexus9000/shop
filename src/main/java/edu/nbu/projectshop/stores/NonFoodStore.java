package edu.nbu.projectshop.stores;

import edu.nbu.projectshop.goods.NonFoodItems;
import org.apache.log4j.Logger;

import java.util.HashMap;

public class NonFoodStore {
    Logger logger = Logger.getLogger(NonFoodStore.class);
    private HashMap<Integer, NonFoodItems> storageMap;




    public NonFoodStore(HashMap<Integer,NonFoodItems> storageMap) {
       this.storageMap = storageMap;
    }
    public void addItem( Integer idNum,NonFoodItems item){
        storageMap.put(idNum, item);
    }
    public void listItem(){
        storageMap.forEach((key, value) ->logger.info(key + " "+value));
    }
}
