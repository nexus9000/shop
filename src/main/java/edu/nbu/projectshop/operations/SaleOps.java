package edu.nbu.projectshop.operations;

import edu.nbu.projectshop.employee.Cashier;
import edu.nbu.projectshop.goods.Items;
import edu.nbu.projectshop.goods.NonFoodItems;
import edu.nbu.projectshop.stores.NonFoodStore;
import edu.nbu.projectshop.tools.Receipt;

import java.util.Date;
import java.util.List;

public class SaleOps {
    private final Cashier cashier;

    private final List<Items> listPurchased;
    private final NonFoodStore nonFoodStore;

    public SaleOps(Cashier cashier, List<Items> listPurchased, NonFoodStore nonFoodStore) {
        this.cashier = cashier;
        this.listPurchased = listPurchased;
        this.nonFoodStore = nonFoodStore;
    }

    public Receipt generateReceipt(Long seqNumber) {
        Date timeStamp = new Date();

        listPurchased.forEach(p -> {
                    Receipt receipt;
                    switch (p.getCategory()) {
                        case "Foods": {

                        }
                        case "Goods":
                            String itemName = p.getItemName();
                            if (nonFoodStore.checkItemAvailability((NonFoodItems) p, 10)) {
                                //reduce quantity
                                nonFoodStore.decreaseQuantity(10, (NonFoodItems) p);
                                //generate receipt
                                receipt = new Receipt(seqNumber, cashier, timeStamp, );
                                return receipt;
                            }
                    }
                }
        );

    }
}
