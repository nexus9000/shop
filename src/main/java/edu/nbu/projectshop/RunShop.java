package edu.nbu.projectshop;


import edu.nbu.projectshop.employee.Cashier;
import edu.nbu.projectshop.employee.ShopCashiers;
import edu.nbu.projectshop.factories.FoodItemsFactory;
import edu.nbu.projectshop.factories.NonFoodItemFactory;
import edu.nbu.projectshop.goods.FoodItems;
import edu.nbu.projectshop.goods.Items;
import edu.nbu.projectshop.goods.NonFoodItems;
import edu.nbu.projectshop.operations.GetReceiptsInfo;
import edu.nbu.projectshop.operations.SaleOps;
import edu.nbu.projectshop.stores.NonFoodStore;
import edu.nbu.projectshop.tools.GenerateIdNumber;
import edu.nbu.projectshop.tools.GenerateSequence;
import edu.nbu.projectshop.tools.PriceGoodsCalculations;
import edu.nbu.projectshop.tools.Receipt;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;


public class RunShop {
    private static final Logger logger = Logger.getLogger(RunShop.class);
    private static SaleOps saleOps;
    private static ShopCashiers shopCashiers;
    public static void main(String...arg) throws IOException {
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
        Date prDate = null;
        try {
            prDate = sdf.parse(productionDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
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
        Optional<Long> currentSeq = GenerateSequence.generateSeq("shop.properties");
        AtomicReference<Receipt> receipt = saleOps.generateReceipt(
                currentSeq);


        File receiptsDir = new File("receipts");
        receiptsDir.mkdir();

        String fileName = null;
        if(currentSeq.isPresent()) {
            fileName = "receipts/" + currentSeq.get() + "receipts";
        }
        Path path = Paths.get(Objects.requireNonNull(fileName));
        Files.writeString(path, receipt.toString());
        new GetReceiptsInfo(receiptsDir.getAbsolutePath()).getInfoAllReceipts();
    }

    private static @NotNull HashMap<Integer, Cashier> initCasse(@NotNull HashMap<Integer, Cashier> cashiers){
        HashMap<Integer, Cashier> casse = new HashMap<>();
        Integer numCasse = 1;
        for(Map.Entry<Integer,Cashier> key   : cashiers.entrySet()){
            casse.put(numCasse,(Cashier) key.getValue());
            numCasse ++;
        }

        return casse;
    }
}
