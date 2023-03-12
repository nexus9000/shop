package edu.nbu.projectshop.tools;
import java.util.Date;
public interface PriceCalculations {
    double priceCalculation(double percentage, double deliveryPrice);
    boolean checkExpirationDate(Date currentDate, Date productionDate, int period);
}
