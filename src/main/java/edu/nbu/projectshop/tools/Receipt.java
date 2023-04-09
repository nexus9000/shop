package edu.nbu.projectshop.tools;

import edu.nbu.projectshop.employee.Cashier;
import edu.nbu.projectshop.goods.Items;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

public record Receipt(Optional<Long> seqNumber, Cashier cashier, Date date, HashMap<Items, HashMap<BigDecimal, Integer>> listItems,
                      BigDecimal price) {
    public Receipt {
        Objects.requireNonNull(seqNumber);
        Objects.requireNonNull(cashier);
        Objects.requireNonNull(date);
        Objects.requireNonNull(listItems);
        Objects.requireNonNull(price);

    }
}
