package edu.nbu.projectshop.tools;

import edu.nbu.projectshop.exceptions.ExpiredProductException;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;


public class PriceGoodsCalculations implements PriceCalculations {
    private final BigDecimal deliveryPrice;
    private final BigDecimal percentage;
    private final Date productionDate;
    private Date expDate;
    private int number;
    public PriceGoodsCalculations(BigDecimal deliveryPrice, BigDecimal percentage, Date productionDate,int number) {
        this.deliveryPrice = deliveryPrice;
        this.percentage = percentage;
        this.productionDate = productionDate;
        this.number = number;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public BigDecimal priceCalculation(@NotNull BigDecimal percentage, BigDecimal deliveryPrice) {

        return (percentage.multiply(deliveryPrice)).add(deliveryPrice);
    }



    @Override
    public boolean checkExpirationDate(Date currentDate, Date productionDate, int period) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(productionDate);
        calendar.add(Calendar.DATE, period);
        expDate = calendar.getTime();
        return expDate.before(currentDate) ? false : true;

    }

    public BigDecimal calculateRealPrice(Date currentDate, Date productionDate, int period)throws ExpiredProductException{
        BigDecimal price ;
        if(checkExpirationDate(currentDate,productionDate,period)){
            price = priceCalculation(percentage, deliveryPrice);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.DATE, -3);
            Date mark = calendar.getTime();
            if(currentDate.compareTo(mark) == 0){
              return price.multiply(BigDecimal.valueOf(0.8));
            }else{
                return price;
            }
        }else{
            throw new ExpiredProductException("Expired product");
        }

    }

    @Override
    public String toString() {
        return "PriceGoodsCalculations{" +
                "deliveryPrice=" + deliveryPrice +
                ", percentage=" + percentage +
                ", productionDate=" + productionDate +
                ", expDate=" + expDate +
                '}';
    }
}
