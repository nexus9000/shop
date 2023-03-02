package edu.nbu.projectshop;

public  abstract class FoodItems implements Items {
    public final String typeItems = "Foods";
    @Override
    public  Double calculateProfitMargin(String typeItems){
        return 0.0;
    }
}
