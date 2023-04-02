package edu.nbu.projectshop.factories;

import edu.nbu.projectshop.goods.NonFoodItems;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class NonFoodItemFactory {
    public static @Nullable NonFoodItems createInstance(String itemName){
        if(Optional.ofNullable(itemName).isPresent()){
            return new NonFoodItems(itemName);
        }else return null;
    }
}
