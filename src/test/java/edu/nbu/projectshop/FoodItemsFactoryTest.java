package edu.nbu.projectshop;

import edu.nbu.projectshop.factories.FoodItemsFactory;
import edu.nbu.projectshop.goods.Items;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class FoodItemsFactoryTest {
    private List<String> foodLists;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        foodLists = Arrays.asList("milk", "meat", "bread");
    }

    @org.junit.jupiter.api.Test
    void createInstance() {

    }
}