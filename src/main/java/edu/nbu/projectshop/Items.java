package edu.nbu.projectshop;

import java.util.Date;

public interface Items {
    public Integer getIndentificationNumber();
    public Double getPrice (Double price);
    public Date getExpirationDate(Date expireDate);
    public String getCategory();
    public Double calculateProfitMargin(String typeCategory);


}
