package edu.nbu.projectshop.goods;

import edu.nbu.projectshop.goods.Items;

import java.util.Date;

public   class FoodItems implements Items {
    public final String typeItems = "Foods";
    private final String itemName;
    public FoodItems(String itemName){
        this.itemName = itemName;
    }

    @Override
    public Integer getIndentificationNumber() {
        return null;
    }

    @Override
    public Double getPrice(Double price) {
        return null;
    }

    @Override
    public Date getExpirationDate(Date expireDate) {
        return null;
    }

    @Override
    public String getCategory() {
        return null;
    }

    @Override
    public  Double calculateProfitMargin(String typeItems){
        return 0.0;
    }
}
