package edu.nbu.projectshop;

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
        FoodItemsFactory fif = new FoodItemsFactory(foodLists);
        Milk milk = (Milk) fif.createInstance("milk");
        Assertions.assertTrue(Items.class.isAssignableFrom(Milk.class));
        Assertions.assertTrue(Objects.nonNull(milk));
    }
}