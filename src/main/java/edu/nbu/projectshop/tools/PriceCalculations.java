package edu.nbu.projectshop.tools;
import java.math.BigDecimal;
import java.util.Date;
public interface PriceCalculations {
    BigDecimal priceCalculation(BigDecimal percentage, BigDecimal deliveryPrice);
    boolean checkExpirationDate(Date currentDate, Date productionDate, int period);
}
