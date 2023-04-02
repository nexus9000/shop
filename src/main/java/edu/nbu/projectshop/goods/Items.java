package edu.nbu.projectshop.goods;

import java.util.Date;

public interface Items {
    public Integer getIndentificationNumber();
    public Double getPrice (Double price);
    public Date getExpirationDate(Date expireDate);
    public String getCategory();
    public String getItemName();
    public Double calculateProfitMargin(String typeCategory);


}
