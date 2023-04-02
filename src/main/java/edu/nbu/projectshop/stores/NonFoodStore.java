package edu.nbu.projectshop.stores;

import edu.nbu.projectshop.exceptions.GoodsMissingException;
import edu.nbu.projectshop.goods.NonFoodItems;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

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
    public void decreaseQuantity(long numberToBuy, NonFoodItems item){
        long currentQuantity = item.getQuantity();
        long realQuantity = currentQuantity - numberToBuy;
        item.setQuantity(realQuantity);
    }
    public void listItem(){
        storageMap.forEach((key, value) ->logger.info(key + " "+value));
    }
    public boolean checkItemAvailability(@NotNull NonFoodItems item, int numbers) throws GoodsMissingException{
        if(item.getQuantity() >= numbers)return true;
        else throw new GoodsMissingException(item.getItemName() + " hasn't enough quantity!");
    }
}
