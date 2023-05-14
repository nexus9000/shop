package edu.nbu.projectshop.operations;

import edu.nbu.projectshop.employee.Cashier;
import edu.nbu.projectshop.employee.ShopCashiers;
import edu.nbu.projectshop.factories.NonFoodItemFactory;
import edu.nbu.projectshop.goods.Items;
import edu.nbu.projectshop.goods.NonFoodItems;
import edu.nbu.projectshop.stores.NonFoodStore;
import edu.nbu.projectshop.tools.GenerateIdNumber;
import edu.nbu.projectshop.tools.GenerateSequence;
import edu.nbu.projectshop.tools.PriceGoodsCalculations;
import edu.nbu.projectshop.tools.Receipt;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Test sale ops")
class SaleOpsTest {
   private Logger logger = Logger.getLogger(SaleOpsTest.class);

    private SaleOps saleOps;
    private ShopCashiers shopCashiers;

    @BeforeEach
    void setUp() throws ParseException {
        Cashier cashier = new Cashier(101, "John", "Smith", 1000.00);
        Cashier cashier2 = new Cashier(102,"Peter", "Donovan" , 1100);
        HashMap<Items, HashMap<BigDecimal, Integer>> listPurchased = new HashMap<>();
        NonFoodItems nails = NonFoodItemFactory.createInstance("nails");
        Objects.requireNonNull(nails).setPrice(new BigDecimal("10.00").setScale(3, RoundingMode.HALF_UP));//set price for single item nails
        Objects.requireNonNull(nails).setQuantity(100);//set nails quantity 100 units
        nails.setIdNum(GenerateIdNumber.generateId());
        NonFoodItems socks = NonFoodItemFactory.createInstance("socks");
        Objects.requireNonNull(socks).setQuantity(1000);//set sockets quantity 1000 units
        Objects.requireNonNull(socks).setPrice(new BigDecimal("2.05").setScale(3, RoundingMode.HALF_UP));
        socks.setIdNum(GenerateIdNumber.generateId());
        //init non-food item store
        HashMap <Integer, NonFoodItems> storeMap = new HashMap<>();
        NonFoodStore nonFoodStore = new NonFoodStore(storeMap);
        nonFoodStore.addItem(nails.getIdNum(), nails);
        nonFoodStore.addItem(socks.getIdNum(), socks);
        //make purchased for quantity 10 units for all
        int units_num = 10;
        HashMap<BigDecimal, Integer> purchasedQ1 = new HashMap<>();
        purchasedQ1.put(nails.getPrice(),units_num);
        listPurchased.put(nails,purchasedQ1);
        HashMap<BigDecimal, Integer> purchasedQ2 = new HashMap<>();
        purchasedQ2.put(socks.getPrice(),units_num);

        listPurchased.put(socks,purchasedQ2);
        //parse production date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String productionDate = "2023-02-02";
        Date prDate = sdf.parse(productionDate);
       //percentage markup
        BigDecimal percentage = BigDecimal.valueOf(0.30).setScale(2);
        int period = 120;//product  in days

        PriceGoodsCalculations pgcNails = new PriceGoodsCalculations(nails.getPrice(), percentage, prDate, units_num);
        PriceGoodsCalculations pgcSocks = new PriceGoodsCalculations(socks.getPrice(), percentage, prDate,units_num);
        ArrayList<PriceGoodsCalculations> listPrices = new ArrayList<>();
        listPrices.add(pgcNails);
        listPrices.add(pgcSocks);


        HashMap<Integer, Cashier> listCashier = new HashMap<>();
        //init cashiers list
        listCashier.put(cashier.getId(),cashier);
        listCashier.put(cashier2.getId(),cashier2);
        shopCashiers = new ShopCashiers(listCashier);
        //run single once purchase
        saleOps = new SaleOps(listCashier.get(cashier.getId()), listPurchased, nonFoodStore, listPrices, percentage, prDate, period);
    }

    @Test
    @DisplayName("list cashiers")
    void testCashierList(){
       assertEquals(new Cashier(101, "John" ,"Smith", 1000),
               shopCashiers.getListCashier().get(101) );
       shopCashiers.showListCashies();

    }
    @Test
    @DisplayName("test profit for one month")
    void testProfit()throws Exception{
        int number_invoices = 100;

    }
    @Test
    void testReceipt()throws Exception{
        Optional<Long> currentSeq = GenerateSequence.generateSeq("shop.properties");
        AtomicReference<Receipt> receipt = saleOps.generateReceipt(
                currentSeq);
        logger.info("profit for this receipt is: "+saleOps.getProfit());
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