package edu.nbu.projectshop.tools;

import java.util.Calendar;
import java.util.Date;


public class PriceGoodsCalculations implements PriceCalculations {
    private final double deliveryPrice;
    private final double percentage;
    private final Date productionDate;
    private Date expDate;

    public PriceGoodsCalculations(double deliveryPrice, double percentage, Date productionDate) {
        this.deliveryPrice = deliveryPrice;
        this.percentage = percentage;
        this.productionDate = productionDate;

    }

    @Override
    public double priceCalculation(double percentage, double deliveryPrice) {
        return deliveryPrice * percentage;
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
    public double calculateRealPrice(Date currentDate,Date productionDate, int period)throws Exception{
        double price = 0.0;
        if(checkExpirationDate(currentDate,productionDate,period)){
            price = priceCalculation(price, percentage);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.DATE, -3);
            Date mark = calendar.getTime();
            if(currentDate.compareTo(mark) == 0){
                price *= 0.8;
            }
        }else{
            throw new RuntimeException("Expired product");
        }
        return price;
    }
}
