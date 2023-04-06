package edu.nbu.projectshop.goods;

import java.math.BigDecimal;
import java.util.Date;

public interface Items {
    Integer getIndentificationNumber();
    BigDecimal getPrice ();
    Date getExpirationDate(Date expireDate);
    String getCategory();
    String getItemName();
     Double calculateProfitMargin(String typeCategory);


}
