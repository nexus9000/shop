package edu.nbu.projectshop.goods;

import edu.nbu.projectshop.goods.Items;

import java.math.BigDecimal;
import java.util.Date;

public  class NonFoodItems implements Items {
    private final String typeItems = "Goods";
    private final String itemName;
    private Integer idNum;
    private BigDecimal price;

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getIdNum() {
        return idNum;
    }

    public void setIdNum(Integer idNum) {
        this.idNum = idNum;
    }

    private  long quantity;

    public String getItemName() {
        return itemName;
    }
    public void setQuantity(long quantity){
        this.quantity = quantity;
    }

    public long getQuantity() {
        return quantity;
    }

    @Override
    public Integer getIndentificationNumber() {
        return null;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public Date getExpirationDate(Date expireDate) {
        return null;
    }

    @Override
    public String getCategory() {
        return typeItems;
    }

    @Override
    public Double calculateProfitMargin(String typeItems){
        return 0.0;
    }
    public NonFoodItems(String itemName){
        this.itemName = itemName;
    }

    @Override
    public int hashCode() {
        return (int) idNum * itemName.hashCode() * typeItems.hashCode();
    }
    @Override
    public String toString() {
        return "NonFoodItems{" +
                "typeItems='" + typeItems + '\'' +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
