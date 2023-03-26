package edu.nbu.projectshop.factories;

import edu.nbu.projectshop.Items;
import edu.nbu.projectshop.Meat;
import edu.nbu.projectshop.Milk;
import edu.nbu.projectshop.tools.GenerateIdNumber;

import java.util.ArrayList;
import java.util.List;

public class FoodItemsFactory {
    private List<String> foodLists;

    public FoodItemsFactory(List<String> foodLists) {
        this.foodLists = foodLists;
    }

    public Items createInstance(String typeItem) {
        if (foodLists.contains(typeItem)) {
            switch (typeItem) {
                case "milk":
                    return new Milk();
                case "meat":
                    return new Meat(GenerateIdNumber.generateId());
            }
        }
        return null;

    }
}
