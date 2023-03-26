package edu.nbu.projectshop.tools;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;


public class PriceGoodsCalculations implements PriceCalculations {
    private final BigDecimal deliveryPrice;
    private final BigDecimal percentage;
    private final Date productionDate;
    private Date expDate;

    public PriceGoodsCalculations(BigDecimal deliveryPrice, BigDecimal percentage, Date productionDate) {
        this.deliveryPrice = deliveryPrice;
        this.percentage = percentage;
        this.productionDate = productionDate;

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
        boolean mark = expDate.before(currentDate) ? false : true;
        return mark;
    }

    @Override
    public String toString() {
        return "PriceGoodsCalculations{}";
    }
    public BigDecimal calculateRealPrice(Date currentDate,Date productionDate, int period)throws RuntimeException{
        BigDecimal price = BigDecimal.valueOf(0.0);
        if(checkExpirationDate(currentDate,productionDate,period)){
            price = priceCalculation(deliveryPrice, percentage);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.DATE, -3);
            Date mark = calendar.getTime();
            if(currentDate.compareTo(mark) == 0){
               price.multiply(BigDecimal.valueOf(0.8));
            }else{
                return price;
            }
        }else{
            throw new RuntimeException("Expired product");
        }
        return price;
    }
}
