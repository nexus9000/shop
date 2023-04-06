package edu.nbu.projectshop.goods;

import edu.nbu.projectshop.goods.Items;

import java.math.BigDecimal;
import java.util.Date;

public   class FoodItems implements Items {
    public final String typeItems = "Foods";
    private final String itemName;
    public FoodItems(String itemName){
        this.itemName = itemName;
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public Integer getIndentificationNumber() {
        return null;
    }

    @Override
    public BigDecimal getPrice() {
        return null;
    }

    @Override
    public Date getExpirationDate(Date expireDate) {
        return null;
    }

    @Override
    public String getCategory() {
        return typeItems;
    }

    @Override
    public  Double calculateProfitMargin(String typeItems){
        return 0.0;
    }
}
