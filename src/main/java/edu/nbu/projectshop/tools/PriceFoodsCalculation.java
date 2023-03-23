package edu.nbu.projectshop.tools;

import java.math.BigDecimal;
import java.util.Date;

public class PriceFoodsCalculation implements PriceCalculations{


    @Override
    public BigDecimal priceCalculation(BigDecimal percentage, BigDecimal deliveryPrice) {
        return null;
    }

    @Override
    public boolean checkExpirationDate(Date currentDate, Date productionDate, int period) {
        return false;
    }
}
