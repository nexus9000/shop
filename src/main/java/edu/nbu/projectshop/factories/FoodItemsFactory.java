package edu.nbu.projectshop.factories;

import edu.nbu.projectshop.goods.FoodItems;
import edu.nbu.projectshop.goods.Items;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FoodItemsFactory {




    public static @Nullable FoodItems createInstance(String itemName) {
        if(Optional.ofNullable(itemName).isPresent()){
            return new FoodItems(itemName);
        }
        return null;

    }
}
