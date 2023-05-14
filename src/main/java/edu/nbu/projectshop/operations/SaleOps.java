package edu.nbu.projectshop.operations;

import edu.nbu.projectshop.employee.Cashier;
import edu.nbu.projectshop.goods.Items;
import edu.nbu.projectshop.goods.NonFoodItems;
import edu.nbu.projectshop.stores.FoodStore;
import edu.nbu.projectshop.stores.NonFoodStore;
import edu.nbu.projectshop.tools.PriceGoodsCalculations;
import edu.nbu.projectshop.tools.Receipt;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class SaleOps {
    private final Cashier cashier;

    private final HashMap<Items, HashMap<BigDecimal, Integer>> listPurchased;
    private NonFoodStore nonFoodStore;
    private FoodStore foodStore;
    private ArrayList<PriceGoodsCalculations> pgc;
    private BigDecimal percentage;//markup 20%
    private Date productionDate;
    private int period;
    private BigDecimal profit = new BigDecimal(0.0);

    public SaleOps(Cashier cashier, HashMap<Items, HashMap<BigDecimal, Integer>> listPurchased,
                   NonFoodStore nonFoodStore, ArrayList<PriceGoodsCalculations> pgc, BigDecimal percentage, Date productionDate, int period) {
        this.cashier = cashier;
        this.listPurchased = listPurchased;
        this.nonFoodStore = nonFoodStore;
        this.pgc = pgc;
        this.percentage = percentage;
        this.productionDate = productionDate;
        this.period = period;
    }

    public SaleOps(Cashier cashier, HashMap<Items, HashMap<BigDecimal, Integer>> listPurchased, FoodStore foodStore) {
        this.cashier = cashier;
        this.listPurchased = listPurchased;
        this.foodStore = foodStore;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    @Contract(pure = true)
    private @NotNull BigDecimal calculateFinalPrice(HashMap<Items, HashMap<BigDecimal, Integer>> listPurchased) {
        Date currentDate = (new Date());
        BigDecimal finalPrice = new BigDecimal(0.0);
        for (PriceGoodsCalculations p : pgc) {
            profit = profit.add(p.getPercentage()
                            .multiply(p.getDeliveryPrice())
                            .multiply(new BigDecimal(p.getNumber())))
                    .setScale(2, RoundingMode.HALF_UP);
            finalPrice = finalPrice.add(p.calculateRealPrice
                            (currentDate, productionDate, period)
                    .multiply(new BigDecimal(p.getNumber()))).setScale(2);
        }

        return finalPrice;
    }

    public AtomicReference<Receipt> generateReceipt(Optional<Long> seqNumber) {
        Date timeStamp = new Date();
        BigDecimal finalPrice = calculateFinalPrice(listPurchased);
        AtomicReference<Receipt> receipt = new AtomicReference<>();
        listPurchased.forEach((p, v) -> {

                    switch (p.getCategory()) {
                        case "Foods" -> {

                        }
                        case "Goods" -> {
                            String itemName = p.getItemName();

                            for (Map.Entry<BigDecimal, Integer> entry : v.entrySet()) {
                                if (nonFoodStore.checkItemAvailability((NonFoodItems) p, entry.getValue())) {
                                    //reduce quantity
                                    nonFoodStore.decreaseQuantity(entry.getValue(), (NonFoodItems) p);

                                    //generate receipt
                                    receipt.set(new Receipt(seqNumber, cashier, timeStamp, listPurchased, finalPrice));

                                }
                            }//end (Map.Entry<BigDecimal, Integer> entry : v.entrySet())
                        }
                    }
                }
        );
        return receipt;
    }

    @Override
    public String toString() {
        return "SaleOps{" +
                "cashier=" + cashier +
                ", listPurchased=" + listPurchased +
                ", nonFoodStore=" + nonFoodStore +
                ", foodStore=" + foodStore +
                '}';
    }
}
