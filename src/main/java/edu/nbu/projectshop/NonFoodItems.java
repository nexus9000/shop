package edu.nbu.projectshop;

public abstract class NonFoodItems implements Items{
    public final String typeItems = "Goods";
    @Override
    public Double calculateProfitMargin(String typeItems){
        return 0.0;
    }
}
