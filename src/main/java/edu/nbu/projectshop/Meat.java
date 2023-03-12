package edu.nbu.projectshop;

import java.util.Date;

public class Meat extends FoodItems{
    private final Integer identificationNumber;
    private double deliveryPrice;
    private double finalPrice;

    public Meat(Integer identificationNumber){
        this.identificationNumber = identificationNumber;
    }

    @Override
    public Integer getIndentificationNumber() {
        return identificationNumber;
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
}
