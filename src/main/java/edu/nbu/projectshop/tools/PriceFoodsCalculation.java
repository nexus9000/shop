package edu.nbu.projectshop.tools;

import java.util.Date;

public class PriceFoodsCalculation implements PriceCalculations{
    @Override
    public double priceCalculation(double percentage, double deliveryPrice) {
        return 0;
    }

    @Override
    public boolean checkExpirationDate(Date currentDate, Date productionDate, int period) {
        return false;
    }
}
