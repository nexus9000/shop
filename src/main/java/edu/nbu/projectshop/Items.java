package edu.nbu.projectshop;

import java.util.Date;

public interface Items {
    public Long getIndemnificationNumber ();
    public Double getPrice (Double price);
    public Date getExpirationDate(Date expireDate);
    public String getCategory();
    public Double calculateProfitMargin(String typeCategory);


}
