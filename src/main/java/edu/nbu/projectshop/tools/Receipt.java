package edu.nbu.projectshop.tools;

import edu.nbu.projectshop.employee.Cashier;
import edu.nbu.projectshop.goods.Items;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public record Receipt(Long seqNumber, Cashier cashier, Date date, HashMap<Items, HashMap<Double, Integer>> listItems,
                      BigDecimal price) {
    public Receipt {
        Objects.requireNonNull(seqNumber);
        Objects.requireNonNull(cashier);
        Objects.requireNonNull(date);
        Objects.requireNonNull(listItems);
        Objects.requireNonNull(price);

    }
}
