package edu.nbu.projectshop;

import java.util.Date;

public  class NonFoodItems implements Items{
    private final String typeItems = "Goods";
    private final String itemName;

    public String getItemName() {
        return itemName;
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
    public Double calculateProfitMargin(String typeItems){
        return 0.0;
    }
    public NonFoodItems(String itemName){
        this.itemName = itemName;
    }

}
