package edu.nbu.projectshop;

import java.util.Date;

public class Milk extends FoodItems{
   private Long idNum;

   public void setIdNum(Long idNum){
       this.idNum = idNum;
   }
    @Override
    public Long getIndemnificationNumber() {
        return idNum;
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
        return super.typeItems;
    }

    @Override
    public Double calculateProfitMargin(String typeCategory) {
        return null;
    }
}
