package edu.nbu.projectshop.operations;

import edu.nbu.projectshop.employee.Cashier;
import edu.nbu.projectshop.goods.Items;
import edu.nbu.projectshop.goods.NonFoodItems;
import edu.nbu.projectshop.stores.NonFoodStore;
import edu.nbu.projectshop.tools.Receipt;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class SaleOps {
    private final Cashier cashier;

    private final HashMap<Items, HashMap<BigDecimal, Integer>> listPurchased;
    private final NonFoodStore nonFoodStore;

    public SaleOps(Cashier cashier, HashMap<Items, HashMap<BigDecimal, Integer>> listPurchased, NonFoodStore nonFoodStore) {
        this.cashier = cashier;
        this.listPurchased = listPurchased;
        this.nonFoodStore = nonFoodStore;
    }

    @Contract(pure = true)
    private @NotNull BigDecimal calculateFinalPrice(HashMap<Items, HashMap<BigDecimal, Integer>> listPurchased) {
        BigDecimal finalPrice = new BigDecimal(0.0);
        return finalPrice;
    }

    public AtomicReference<Receipt>generateReceipt(Long seqNumber) {
        Date timeStamp = new Date();
        BigDecimal finalPrice = calculateFinalPrice(listPurchased);
        AtomicReference<Receipt> receipt = null;
        listPurchased.forEach((p,v) -> {

                    switch (p.getCategory()) {
                        case "Foods": {
                        break;
                        }
                        case "Goods":
                            String itemName = p.getItemName();
                            if (nonFoodStore.checkItemAvailability((NonFoodItems) p, 10)) {
                                //reduce quantity
                                nonFoodStore.decreaseQuantity(10, (NonFoodItems) p);
                                //generate receipt
                                receipt.set(new Receipt(seqNumber, cashier, timeStamp, listPurchased, finalPrice));
                                break;
                            }
                    }
                }
        );
       return receipt;
    }
}
