package edu.nbu.projectshop.operations;

import edu.nbu.projectshop.employee.Cashier;
import edu.nbu.projectshop.employee.ShopCashiers;
import edu.nbu.projectshop.factories.NonFoodItemFactory;
import edu.nbu.projectshop.goods.Items;
import edu.nbu.projectshop.goods.NonFoodItems;
import edu.nbu.projectshop.stores.NonFoodStore;
import edu.nbu.projectshop.tools.GenerateIdNumber;
import edu.nbu.projectshop.tools.GenerateSequence;
import edu.nbu.projectshop.tools.Receipt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Test sale ops")
class SaleOpsTest {


    private SaleOps saleOps;
    private ShopCashiers shopCashiers;

    @BeforeEach
    void setUp() {
        Cashier cashier = new Cashier(101, "John", "Smith", 1000.00);
        Cashier cashier2 = new Cashier(102,"Peter", "Donovan" , 1100);
        HashMap<Items, HashMap<BigDecimal, Integer>> listPurchased = new HashMap<>();
        NonFoodItems nails = NonFoodItemFactory.createInstance("nails");
        Objects.requireNonNull(nails).setPrice(new BigDecimal("10.00"));
        Objects.requireNonNull(nails).setQuantity(100);
        nails.setIdNum(GenerateIdNumber.generateId());
        NonFoodItems socks = NonFoodItemFactory.createInstance("socks");
        Objects.requireNonNull(socks).setQuantity(1000);
        Objects.requireNonNull(socks).setPrice(new BigDecimal("2.0588").setScale(3, RoundingMode.CEILING));
        socks.setIdNum(GenerateIdNumber.generateId());
        HashMap <Integer, NonFoodItems> storeMap = new HashMap<>();
        NonFoodStore nonFoodStore = new NonFoodStore(storeMap);
        nonFoodStore.addItem(nails.getIdNum(), nails);
        nonFoodStore.addItem(socks.getIdNum(), socks);
        HashMap<BigDecimal, Integer> purchasedQ1 = new HashMap<>();
        purchasedQ1.put(nails.getPrice(),10);
        listPurchased.put(nails,purchasedQ1);
        HashMap<BigDecimal, Integer> purchasedQ2 = new HashMap<>();
        purchasedQ2.put(socks.getPrice(),10);
        listPurchased.put(socks,purchasedQ2);
        saleOps = new SaleOps(cashier, listPurchased, nonFoodStore);
        HashMap<Integer, Cashier> listCashier = new HashMap<>();
        //init cashiers list
        listCashier.put(cashier.getId(),cashier);
        listCashier.put(cashier2.getId(),cashier2);
        shopCashiers = new ShopCashiers(listCashier);
    }

    @Test
    @DisplayName("list cashiers")
    void testCashierList(){
       assertEquals(new Cashier(101, "John" ,"Smith", 1000),
               shopCashiers.getListCashier().get(101) );
       shopCashiers.showListCashies();

    }
    @Test
    void testReceipt()throws Exception{
        Optional<Long> currentSeq = GenerateSequence.generateSeq("shop.properties");
        AtomicReference<Receipt> receipt = saleOps.generateReceipt(
                currentSeq);

        assertNotNull(receipt);
        File receiptsDir = new File("receipts");
        receiptsDir.mkdir();
        assertTrue(receiptsDir.exists());
        String fileName = null;
        if(currentSeq.isPresent()) {
            fileName = "receipts/" + currentSeq.get() + "receipts";
        }
        Path path = Paths.get(Objects.requireNonNull(fileName));
        Files.writeString(path, receipt.toString());
        new GetReceiptsInfo(receiptsDir.getAbsolutePath()).getInfoAllReceipts();
    }
}