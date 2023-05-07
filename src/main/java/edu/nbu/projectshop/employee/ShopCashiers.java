package edu.nbu.projectshop.employee;

import org.apache.log4j.Logger;

import java.util.HashMap;

public class ShopCashiers {
    private final HashMap<Integer, Cashier> listCashier ;
    private Logger logger = Logger.getLogger(ShopCashiers.class);
    public ShopCashiers(HashMap<Integer, Cashier> listCashier){
        this.listCashier = listCashier;

    }

    public void showListCashies(){
        listCashier.forEach((k,v)-> logger.info(k + " " +v));
    }

    public HashMap<Integer, Cashier> getListCashier() {
        return listCashier;
    }
}
